package beans;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlType(propOrder={"name"})
public class CustomerBean
{
	private String account;
	private String name;
	
	public CustomerBean(String account, String name)
	{
		this.account = account;
		this.name = name;
	}

	@XmlElement
	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}
	
	@XmlAttribute
	public String getAccount()
	{
		return account;
	}

	public void setAccount(String account)
	{
		this.account = account;
	}
}
