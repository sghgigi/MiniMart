package model;

import java.io.File;
import java.io.FileWriter;
import java.io.StringWriter;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.transform.stream.StreamResult;

import beans.CartBean;
import beans.CustomerBean;
import beans.ItemBean;
import dao.CustomerDAO;
import dao.ItemDAO;

public class MiniMart
{
	public static final int FREE_SHIPPING_CUTOFF = 100;
	public static final double FLAT_SHIPPING_FEE = 5.00;
	public static final double TAX_RATE = 0.13;
	
	private final ItemDAO itemDao;
	private final CustomerDAO customerDao;	
	
	public MiniMart()
	{
		this.itemDao = new ItemDAO();
		this.customerDao = new CustomerDAO();
	}
	
	public boolean authenticate(String account, String pass)
	{
		return customerDao.authenticate(account, pass);
	}
	public String getCustomer(String account)
	{
		return customerDao.getCustomer(account);
	}
	public List<ItemBean> retrieveItems()
	{
		return this.retrieveItems(null);
	}
	
	public List<ItemBean> retrieveItems(String searchString)
	{
		return itemDao.retrieveItems(searchString);
	}
	
	public ItemBean retrieveItem(String number)
	{
		return itemDao.retrieveItem(number);
	}

	public CartBean buildCart(Object old, String itemNumber, String qty)
	{
		// validate / initialize all parameters
		if (qty == null) qty = "1";
		int quantity = Integer.parseInt(qty);
		if (quantity < 0) throw new RuntimeException("Negative quantity!");
		ItemBean item = this.retrieveItem(itemNumber);
		if (item == null) throw new RuntimeException("No such item!");
		CartBean cart = (CartBean) old;
		if (cart == null)
		{
			cart = new CartBean(new HashMap<String, ItemBean>(), 0 , 0, 0, 0);
		}
		
		// start building
		if (quantity == 0)
		{
			cart.getContent().remove(itemNumber);
		}
		else
		{
			cart.getContent().put(itemNumber, new ItemBean(item, quantity));
		}
		cart.setTotal(this.getTotal(cart.getContent()));
		cart.setShipping(this.getShipping(cart.getContent()));
		cart.setTaxes(this.getTaxes(cart.getContent()));
		cart.setGrandTotal(this.getGrandTotal(cart.getContent()));		
		return cart;
	}
	
	private double getTotal(Map<String,ItemBean> cart)
	{
		double total = 0.0;
		for (ItemBean item : cart.values()) total += item.getExtended();
		return total;
	}
	
	public double getShipping(Map<String,ItemBean> cart)
	{
		return (this.getTotal(cart) > MiniMart.FREE_SHIPPING_CUTOFF) ? 0 : MiniMart.FLAT_SHIPPING_FEE;
	}
	
	public double getTaxes(Map<String,ItemBean> cart)
	{
		return this.getTotal(cart) * MiniMart.TAX_RATE;
	}
	
	public double getGrandTotal(Map<String,ItemBean> cart)
	{
		double gTotal = 0.0;
		gTotal = this.getTotal(cart);		
		gTotal = (gTotal > MiniMart.FREE_SHIPPING_CUTOFF) ? gTotal + 0 : gTotal + MiniMart.FLAT_SHIPPING_FEE;
		gTotal = gTotal * MiniMart.TAX_RATE + gTotal;
		return gTotal;
	}
	
	public void export(Object account, Object accountName, Object cartObj, File file) throws Exception
	{		
		CartBean cart = (CartBean) cartObj;		
		ListWrapper lw = new ListWrapper(new CustomerBean((String) account, (String) accountName), 
				cart, file.getName(), new Date());
		
		JAXBContext jc = JAXBContext.newInstance(lw.getClass());
		Marshaller marshaller = jc.createMarshaller();
		marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
		marshaller.setProperty(Marshaller.JAXB_FRAGMENT, Boolean.TRUE);
		
		StringWriter sw = new StringWriter();
		sw.write("\n<?xml-stylesheet type='text/xsl' href='PO.xsl'?>\n");
		marshaller.marshal(lw, new StreamResult(sw));
		
		//System.out.println(sw.toString()); // for debugging
		FileWriter fw = new FileWriter(file.getAbsolutePath());
		fw.write(sw.toString());
		fw.close();
	}
		
}
