package lsq.Bryan.dao.impl;

import java.sql.Connection;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import lsq.Bryan.dao.CategoryDao;
import lsq.Bryan.domain.Category;
import lsq.Bryan.utils.JdbcUtils;

public class CategoryDaoImpl implements CategoryDao {
	
	@Override
	public void add(Category c) {
		try {
			Connection conn = JdbcUtils.getConnection();//获取到的是当前线程上开启事务的连接
			QueryRunner runner = new QueryRunner();
			String sql = "insert into category(id,name,description) values(?,?,?)";
			Object[] params = {c.getId(), c.getName(), c.getDescription()};
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
			String sql = "delete from category where id ='"+id+"'";
			runner.update(conn, sql);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	@SuppressWarnings("deprecation")
	@Override
	public Category find(String id) {
		try {
			Connection conn = JdbcUtils.getConnection();
			QueryRunner runner = new QueryRunner();
			String sql = "select * from category where id=?";
			return runner.query(conn, sql, id, new BeanHandler<Category>(Category.class));
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	@Override
	public List<Category> getAll() {
		try {
			Connection conn = JdbcUtils.getConnection();
			QueryRunner runner = new QueryRunner();
			String sql = "select * from category";
			return runner.query(conn, sql, new BeanListHandler<Category>(Category.class));
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
}