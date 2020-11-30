package lsq.Bryan.service.impl;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import lsq.Bryan.dao.CategoryDao;
import lsq.Bryan.dao.GoodDao;
import lsq.Bryan.dao.OrderDao;
import lsq.Bryan.dao.UserDao;
import lsq.Bryan.domain.Cart;
import lsq.Bryan.domain.CartItem;
import lsq.Bryan.domain.Category;
import lsq.Bryan.domain.Good;
import lsq.Bryan.domain.Order;
import lsq.Bryan.domain.OrderItem;
import lsq.Bryan.domain.PageBean;
import lsq.Bryan.domain.Privilege;
import lsq.Bryan.domain.QueryInfo;
import lsq.Bryan.domain.QueryResult;
import lsq.Bryan.domain.User;
import lsq.Bryan.factory.DaoFactory;
import lsq.Bryan.service.BusinessService;

public class BusinessServiceImpl implements BusinessService {
	
	private CategoryDao cDao = DaoFactory.getInstance().createDao(CategoryDao.class);
	private GoodDao bDao = DaoFactory.getInstance().createDao(GoodDao.class);
	private UserDao uDao = DaoFactory.getInstance().createDao(UserDao.class);
	private OrderDao oDao = DaoFactory.getInstance().createDao(OrderDao.class);
	
	/********************************************
	 * ������صķ���
	 ********************************************/
	@Override
	public void addCategory(Category c) {
		cDao.add(c);
	}
	
	@Override
	public void deleteCategory(String id) {
		cDao.dele(id);
	}
	
	@Override
	public Category findCategory(String id) {
		return cDao.find(id);
	}
	
	@Override
	public List<Category> getAllCategory() {
		return cDao.getAll();
	}
	
	/********************************************
	 * ��Ʒ��صķ���
	 ********************************************/
	@Override
	public void addGood(Good good) {
		bDao.add(good);
	}
	
	@Override
	public Good findGood(String id) {
		return bDao.find(id);
	}
	
	@Override
	public PageBean goodPageQuery(QueryInfo info) {
		QueryResult result = bDao.pageQuery(info.getStartindex(), info.getPagesize(), info.getWhere(), info.getQueryvalue());
		PageBean bean = new PageBean();
		bean.setCurrentpage(info.getCurrentpage());
		bean.setList(result.getList());
		bean.setPagesize(info.getPagesize());
		bean.setTotalrecord(result.getTotalrecord());
		return bean;
	}
	
	public List<Good> getAllGood() {
		return bDao.getAll();
	}
	
	@Override
	public void deleteGood(String id) {
		bDao.dele(id);
	}

	/********************************************
	 * �û���صķ���
	 ********************************************/
	@Override
	public void addUser(User user) {
		uDao.add(user);
	}
	
	@Override
	public User findUser(String username, String password) {
		return uDao.find(username, password);
	}
	
	@Override
	public User findUser(String id) {
		return uDao.find(id);
	}
	
	/********************************************
	 * ������صķ���
	 ********************************************/
	//ʹ���û��Ĺ��ﳵ������һ��������Ȼ��������ݿ���
	@Override
	public void saveOrder(Cart cart, User user) {
		Order order = new Order();
		order.setId(UUID.randomUUID().toString());
		order.setOrdertime(new Date());
		order.setPrice(cart.getPrice());
		order.setStatus(false);
		order.setUser(user);
		
		//����һ�����ϣ����ڱ������ж�����
		Set<OrderItem> oitems = new HashSet<OrderItem>();
		
		//�ù��ﳵ�еĹ��������ɶ�����
		Set<Map.Entry<String, CartItem>> set = cart.getMap().entrySet();
		for (Map.Entry<String, CartItem> entry : set) {
			//�õ�ÿһ��������
			CartItem citem = entry.getValue();
			OrderItem oitem = new OrderItem();
			
			//�ù��ﳵ�еĹ��������ɶ�����
			oitem.setGood(citem.getGood());
			oitem.setId(UUID.randomUUID().toString());
			oitem.setPrice(citem.getPrice());
			oitem.setQuantity(citem.getQuantity());
			
			oitems.add(oitem);
		}
		
		order.setOrderitems(oitems);
		oDao.add(order);
	}
	
	@Override
	public Order findOrder(String id) {
		return oDao.find(id);
	}
	
	@Override
	public List<Order> getOrderByStatus(boolean status) {
		return oDao.getAll(status);
	}
	
	//���¶���״̬
	public void updatOrder(String id, boolean status) {
		oDao.update(id, status);
	}
	
	//�õ��û�ӵ�е�����Ȩ��
	@Override
	public List<Privilege> getUserAllPrivilege(User user) {
		return uDao.getAllPrivilege(user);
	}
	
	//ɾ��ָ�����ﳵ�еĹ�����
	public void deleteCartItem(String id, Cart cart) {
		cart.getMap().remove(id);
	}
	
	// ��չ��ﳵ
	public void clearCart(Cart cart) {
		cart.getMap().clear();
	}
	
	// �ı乺�ﳵĳ�����������
	public void changeItemQuantity(String id, String quantity, Cart cart) {
		CartItem item = cart.getMap().get(id);
		item.setQuantity(Integer.parseInt(quantity));
	}
	
	public List<Order> getOrderByUsername(String username) {
		return oDao.getAllbyUsername(username);
	}
}
