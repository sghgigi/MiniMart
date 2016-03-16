package model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import beans.CartBean;
import beans.CustomerBean;
import beans.ItemBean;

@XmlRootElement(name="order")
@XmlType(propOrder={"hash", "submitted", "customer", "items", "total", "shipping", "HST", "grandTotal"})
public class ListWrapper
{
	@XmlElement
	private CustomerBean customer;
	@XmlElement(name="item")
	private List<ItemBean> items;
	@XmlElement
	private double total;
	@XmlElement
	private double shipping;
	@XmlElement
	private double HST;
	@XmlElement
	private double grandTotal;
	@XmlAttribute
	private String hash;
	@XmlAttribute
	private Date submitted;
	
	public ListWrapper()
	{
	}

	public ListWrapper(CustomerBean customer, CartBean cart, String hash, Date submitted)
	{
		super();
		this.customer = customer;
		this.items = new ArrayList<ItemBean>(cart.getContent().values());
		this.total = cart.getTotal();
		this.shipping = cart.getShipping();
		this.HST = cart.getTaxes();
		this.grandTotal = cart.getGrandTotal();
		this.hash = hash;
		this.submitted = submitted;
	}
}
