package lsq.Bryan.dao;

import java.util.List;

import lsq.Bryan.domain.Good;
import lsq.Bryan.domain.QueryResult;

public interface GoodDao {

	void add(Good b);
	
	void dele(String id);

	Good find(String id);

	//查找商品的分页数据
	QueryResult pageQuery(int startindex, int pagesize, String where, Object param);
	
	public List<Good> getAll();
}
