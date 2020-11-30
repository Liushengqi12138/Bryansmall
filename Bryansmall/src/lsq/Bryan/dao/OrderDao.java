package lsq.Bryan.dao;

import java.util.List;

import lsq.Bryan.domain.Order;

public interface OrderDao {

	void add(Order o);
	//要把Order对象的基本信息找回来，还要把用户的信息找回来，还要把多个订单项的信息找回来，总共要查找4张表。
	Order find(String id);
	List<Order> getAll(boolean status);//查看订单发没发货
	List<Order> getAllbyUsername(String username);
	public void update(String id, boolean status);//更新订单状态
}
