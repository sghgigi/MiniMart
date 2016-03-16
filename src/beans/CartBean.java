package beans;

import java.util.Map;

public class CartBean
{
	Map<String, ItemBean> content;
	double total;
	double shipping;
	double taxes;
	double grandTotal;

	public CartBean(Map<String, ItemBean> content, double total,
			double shipping, double taxes, double grandTotal)
	{
		super();
		this.content = content;
		this.total = total;
		this.shipping = shipping;
		this.taxes = taxes;
		this.grandTotal = grandTotal;
	}

	public Map<String, ItemBean> getContent()
	{
		return content;
	}

	public void setContent(Map<String, ItemBean> content)
	{
		this.content = content;
	}

	public double getTotal()
	{
		return total;
	}

	public void setTotal(double total)
	{
		this.total = total;
	}

	public double getShipping()
	{
		return shipping;
	}

	public void setShipping(double shipping)
	{
		this.shipping = shipping;
	}

	public double getTaxes()
	{
		return taxes;
	}

	public void setTaxes(double taxes)
	{
		this.taxes = taxes;
	}

	public double getGrandTotal()
	{
		return grandTotal;
	}

	public void setGrandTotal(double grandTotal)
	{
		this.grandTotal = grandTotal;
	}

}
