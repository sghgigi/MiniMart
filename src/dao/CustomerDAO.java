package dao;

import java.util.HashMap;
import java.util.Map;

import beans.CustomerBean;

public class CustomerDAO
{
	Map<String, CustomerBean> customers;
	Map<String, String> auth;
	
	public CustomerDAO()
	{
		this.customers = new HashMap<String, CustomerBean>();
		this.auth = new HashMap<String, String>();
		
		customers.put("student", new CustomerBean("student", "Jim Smith"));
		customers.put("eecs1234", new CustomerBean("eecs1234", "Natalia Sohey"));
		auth.put("student", "secret");
		auth.put("eecs1234", "BF443A");
	}
	
	public boolean authenticate(String account, String pasword)
	{
		CustomerBean customer = this.customers.get(account);
		return (customer != null && auth.get(account).equals(pasword));
	}
	
	public String getCustomer(String account)
	{
		return this.customers.get(account).getName();
	}	
}
