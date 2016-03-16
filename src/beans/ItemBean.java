package beans;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;

@XmlType(propOrder={"name", "price", "quantity", "extended"})
public class ItemBean
{
	private String number;
	private String name;
	private int quantity;
	private double price;
	private double extended;

	public ItemBean(ItemBean other, int quantity)
	{
		this(other.getNumber(), other.getName(), quantity, other.getPrice());
	}

	public ItemBean(String number, String name, double price)
	{
		this(number, name, 0, price);
	}
	
	public ItemBean(String number, String name, int quantity, double price)
	{
		super();
		this.setName(name);
		this.setNumber(number);
		this.setQuantity(quantity);
		this.setPrice(price);
	}

	public double getExtended()
	{
		return extended;
	}

	public void setExtended(double extended)
	{
		this.extended = extended;
	}

	@XmlAttribute
	public String getNumber()
	{
		return number;
	}

	public int getQuantity()
	{
		return quantity;
	}

	public String getName()
	{
		return name;
	}

	public double getPrice()
	{
		return price;
	}

	public void setNumber(String number)
	{
		this.number = number;
	}

	public void setQuantity(int quantity)
	{
		this.quantity = quantity;
		this.extended = this.quantity * this.price;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public void setPrice(double price)
	{
		this.price = price;
		this.extended = this.quantity * this.price;
	}
	
}
