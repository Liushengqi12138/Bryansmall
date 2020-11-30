package lsq.Bryan.dao.impl;

import java.sql.Connection;
import java.util.List;
import java.util.Set;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import lsq.Bryan.dao.OrderDao;
import lsq.Bryan.domain.Good;
import lsq.Bryan.domain.Order;
import lsq.Bryan.domain.OrderItem;
import lsq.Bryan.domain.User;
import lsq.Bryan.utils.JdbcUtils;

//难点
public class OrderDaoImpl implements OrderDao {
	@Override
	public void add(Order o) {
		try {
			Connection conn = JdbcUtils.getConnection();
			QueryRunner runner = new QueryRunner();
			//保存订单的基本信息
			String sql = "insert into orders(id,ordertime,status,price,user_id) values(?,?,?,?,?)";
			Object[] params = {o.getId(), o.getOrdertime(), o.isStatus(), o.getPrice(), o.getUser().getId()};
			runner.update(conn, sql, params);
			
			//再保存多个订单项的基本信息
			Set<OrderItem> set = o.getOrderitems();
			for (OrderItem item : set) {
				sql = "insert into orderitem(id,quantity,price,good_id,order_id) values(?,?,?,?,?)";
				params = new Object[]{item.getId(), item.getQuantity(), item.getPrice(), item.getGood().getId(), o.getId()};
				runner.update(conn, sql, params);
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	@Override
	public Order find(String id) {
		try {
			Connection conn = JdbcUtils.getConnection();
			QueryRunner runner = new QueryRunner();
			//找出订单的基本信息
			String sql = "select * from orders where id=?";
			Order order = runner.query(conn, sql, new BeanHandler<Order>(Order.class), id);
			
			//找出订单中的每一个订单项
			sql = "select * from orderitem where order_id=?";
			List<OrderItem> list = runner.query(conn, sql, new BeanListHandler<OrderItem>(OrderItem.class), id);
			
			//找出每一个订单项代表的每件商品（涉及到多表查询）
			for (OrderItem item : list) {
				sql = "select b.* from orderitem oi,good b where oi.id=? and b.id=oi.good_id";
				Good good = runner.query(conn, sql, new BeanHandler<Good>(Good.class), item.getId());
				item.setGood(good);
			}
			order.getOrderitems().addAll(list);
			
			//找出下订单的人（涉及到多表查询）
			sql = "select u.* from orders o,user u where o.id=? and u.id=o.user_id";
			User user = runner.query(conn, sql, new BeanHandler<User>(User.class), id);
			
			order.setUser(user);
			
			return order;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	
	@Override
	public List<Order> getAll(boolean status) {
		try {
			Connection conn = JdbcUtils.getConnection();
			QueryRunner runner = new QueryRunner();
			String sql = "select * from orders where status=?";
			List<Order> list = runner.query(conn, sql, new BeanListHandler<Order>(Order.class), status);
			//找出每一个订单的下单人
			for (Order o : list) {
				sql = "select u.* from orders o,user u where o.id=? and u.id=o.user_id";
				User user = runner.query(conn, sql, new BeanHandler<User>(User.class), o.getId());
				o.setUser(user);
			}
			return list;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	@Override
	public List<Order> getAllbyUsername(String username) {
		try {
			Connection conn = JdbcUtils.getConnection();
			QueryRunner runner = new QueryRunner();
			
			String sql = "select * from user where username=?";
			User user = runner.query(conn, sql, new BeanHandler<User>(User.class), username);
			String id=user.getId();
			sql = "select * from orders where user_id=?";
			List<Order> list = runner.query(conn, sql, new BeanListHandler<Order>(Order.class), id);
			//找出每一个订单的下单人
			for (Order o : list) {
				sql = "select u.* from orders o,user u where o.id=? and u.id=o.user_id";
				user = runner.query(conn, sql, new BeanHandler<User>(User.class), o.getId());
				o.setUser(user);
			}
			return list;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	//更新订单状态
	public void update(String id, boolean status) {
		try {
			Connection conn = JdbcUtils.getConnection();
			QueryRunner runner = new QueryRunner();
			String sql = "update orders set status=? where id=?";
			Object[] parmas = {status, id};
			runner.update(conn, sql, parmas);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}
