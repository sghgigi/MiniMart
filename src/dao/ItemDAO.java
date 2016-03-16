package dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import beans.ItemBean;

public class ItemDAO
{
	Map<String, ItemBean> items;
	
	public ItemDAO()
	{
		this.items = new HashMap<String, ItemBean>();
		items.put("2910h714", new ItemBean("2910h714", "E7 Honey Cereal", 6.42));
		items.put("2002H712", new ItemBean("2002H712", "Semi-Monterey Cheese by GK", 5.43));
		items.put("2910h074", new ItemBean("2910h074", "L5 Oat Cereal", 4.92));
		items.put("1409S929", new ItemBean("1409S929", "Nuts Ice Cream with Vanilla by RC", 6.68));
		items.put("2002H827", new ItemBean("2002H827", "Soft Edam Cheese by OJ", 6.7));
		items.put("2002H838", new ItemBean("2002H838", "Semi-Provolone Cheese by IT", 4.41));
		items.put("1409S898", new ItemBean("1409S898", "Z6 Twirl Ice Cream", 3.2));
		items.put("1409S123", new ItemBean("1409S123", "E8 Nuts Ice Cream", 6.12));
		items.put("0905A343", new ItemBean("0905A343", "Roast Breast Meat by GH", 4.67));
		items.put("1409S811", new ItemBean("1409S811", "Brownie Ice Cream with Twirl by BJ", 3.3));
		items.put("0905A850", new ItemBean("0905A850", "I7 Sirloin Meat", 4.88));
		items.put("2910h785", new ItemBean("2910h785", "N4 Raisin Cereal", 3.98));
		items.put("2002H815", new ItemBean("2002H815", "Gouda Cheese by YD", 5.88));
		items.put("1409S918", new ItemBean("1409S918", "Peanut Ice Cream with Twirl by UU", 7.31));
		items.put("1405S898", new ItemBean("1405S898", "Z5 Twirl Ice Cream", 3.2));
		items.put("2910h087", new ItemBean("2910h087", "Wheat Fruits Cereal by FR", 3.05));
		items.put("0905A638", new ItemBean("0905A638", "Side Breast Meat by IE", 5.13));
	}
	
	public List<ItemBean> retrieveItems(String searchString)
	{
		ArrayList<ItemBean> result = new ArrayList<ItemBean>();
		
		if (searchString == null)
		{
			result = new ArrayList<ItemBean>(items.values());
		}
		else if (items.get(searchString) != null)
		{
			result.add(items.get(searchString));
		}
		else
		{
			for (ItemBean item : items.values())
			{
				if (item.getName().toUpperCase().indexOf(searchString.toUpperCase()) != -1) result.add(item);
			}
		}
		return result;
	}
	
	public ItemBean retrieveItem(String itemNumber)
	{
		return this.items.get(itemNumber);
	}

}
