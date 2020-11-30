package lsq.Bryan.domain;

import java.util.LinkedHashMap;
import java.util.Map;

public class Cart {
	private Map<String, CartItem> map = new LinkedHashMap<String, CartItem>();
	private double price;//购物车的总价
	
	public void add(Good good) {//往购物车中添加一件商品
		//得到对应的购物项
		CartItem item = map.get(good.getId());
		
		//如果是null，说明购物车中还没有该购物项
		if (item == null) {
			item = new CartItem();
			item.setGood(good);
			item.setQuantity(1);
			
			//把购物项加到购物车中
			map.put(good.getId(), item);
		}  else {
			//如果购物车有该购物项了，那么将购物项的数量+1
			item.setQuantity(item.getQuantity() + 1);
		}
	}
	
	public Map<String, CartItem> getMap() {
		return map;
	}
	public void setMap(Map<String, CartItem> map) {
		this.map = map;
	}
	
	//购物车的价钱是购物项价钱的总和
	public double getPrice() {
		double totalprice = 0;
		for (Map.Entry<String, CartItem> entry : map.entrySet()) {
			totalprice += entry.getValue().getPrice();
		}
		this.price = totalprice;
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
}
