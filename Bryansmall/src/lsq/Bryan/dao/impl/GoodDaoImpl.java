package lsq.Bryan.dao.impl;

import java.sql.Connection;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import lsq.Bryan.dao.GoodDao;
import lsq.Bryan.domain.Good;
import lsq.Bryan.domain.QueryResult;
import lsq.Bryan.utils.JdbcUtils;

public class GoodDaoImpl implements GoodDao {
	@Override
	public void add(Good b) {
		try {
			Connection conn = JdbcUtils.getConnection();
			QueryRunner runner = new QueryRunner();
			String sql = "insert into good(id,name,price,brand,image,description,category_id) values(?,?,?,?,?,?,?)";
			Object[] params = {b.getId(), b.getName(), b.getPrice(), b.getBrand(), b.getImage(), b.getDescription(), b.getCategory().getId()};
			runner.update(conn, sql, params);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	@Override
	public void dele(String id) {
		try {
			Connection conn = JdbcUtils.getConnection();//获取到的是当前线程上开启事务的连接
			QueryRunner runner = new QueryRunner();
			String sql = "delete from good where id ='"+id+"'";
			runner.update(conn, sql);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	@SuppressWarnings("deprecation")
	@Override
	public Good find(String id) {
		try {
			Connection conn = JdbcUtils.getConnection();
			QueryRunner runner = new QueryRunner();
			String sql = "select * from good where id=?";
			return runner.query(conn, sql, id, new BeanHandler<Good>(Good.class));
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	@SuppressWarnings("deprecation")
	private List<Good> getPageData(int startindex, int pagesize, String where, Object param) {
		try {
			Connection conn = JdbcUtils.getConnection();
			QueryRunner runner = new QueryRunner();
			
			if (where == null || where.trim().equals("")) {
				//返回所有商品的分页数据
				String sql = "select * from good limit ?,?";
				Object[] params = {startindex, pagesize};
				return runner.query(conn, sql, params, new BeanListHandler<Good>(Good.class));
			} else {
				//如果上层带了查询条件过来，那么就需要获得该查询条件下的分页数据
				String sql = "select * from good " + where + " limit ?,?";
				Object[] params = {param, startindex, pagesize};
				return runner.query(conn, sql, params, new BeanListHandler<Good>(Good.class));
			}
			
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	//分页的时候，获得总记录数
	private int getPageTotalRecord(String where, Object param) {
		try {
			Connection conn = JdbcUtils.getConnection();
			QueryRunner runner = new QueryRunner();
			
			if (where == null || where.trim().equals("")) {
				String sql = "select count(*) from good";
				return runner.query(conn, sql, new ScalarHandler<Long>()).intValue();
			} else {
				String sql = "select count(*) from good " + where;
				return runner.query(conn, sql, param, new ScalarHandler<Long>()).intValue();
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	@Override
	public QueryResult pageQuery(int startindex, int pagesize, String where, Object param) {
		List<Good> list = getPageData(startindex, pagesize, where, param);
		int totalrecord = getPageTotalRecord(where, param);
		QueryResult result = new QueryResult();
		result.setList(list);
		result.setTotalrecord(totalrecord);
		return result;
	}
	
	public List<Good> getAll() {
		try {
			Connection conn = JdbcUtils.getConnection();
			QueryRunner runner = new QueryRunner();
			String sql = "select * from good";
			return runner.query(conn, sql, new BeanListHandler<Good>(Good.class));
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}
