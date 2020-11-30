package lsq.Bryan.service;

import java.util.List;

import lsq.Bryan.domain.Cart;
import lsq.Bryan.domain.Category;
import lsq.Bryan.domain.Good;
import lsq.Bryan.domain.Order;
import lsq.Bryan.domain.PageBean;
import lsq.Bryan.domain.Privilege;
import lsq.Bryan.domain.QueryInfo;
import lsq.Bryan.domain.User;

public interface BusinessService {

	/********************************************
	 * ������صķ���
	 ********************************************/
	void addCategory(Category c);

	Category findCategory(String id);

	List<Category> getAllCategory();
	
	void deleteCategory(String id);

	/********************************************
	 * ��Ʒ��صķ���
	 ********************************************/
	void addGood(Good good);

	Good findGood(String id);

	PageBean goodPageQuery(QueryInfo info);
	
	public List<Good> getAllGood();
	
	void deleteGood(String id);

	/********************************************
	 * �û���صķ���
	 ********************************************/
	void addUser(User user);

	User findUser(String username, String password);

	User findUser(String id);

	/********************************************
	 * ������صķ���
	 ********************************************/
	//ʹ���û��Ĺ��ﳵ������һ��������Ȼ��������ݿ���
	void saveOrder(Cart cart, User user);

	Order findOrder(String id);

	List<Order> getOrderByStatus(boolean status);
	List<Order> getOrderByUsername(String username);
	
	//���¶���״̬
	public void updatOrder(String id, boolean status);
	
	//�õ��û�������Ȩ��
	List<Privilege> getUserAllPrivilege(User user);
	
	//ɾ��ָ�����ﳵ�еĹ�����
	public void deleteCartItem(String id, Cart cart);
	
	// ��չ��ﳵ
	public void clearCart(Cart cart) ;

	// �ı乺�ﳵĳ�����������
	public void changeItemQuantity(String id, String quantity, Cart cart);
}
