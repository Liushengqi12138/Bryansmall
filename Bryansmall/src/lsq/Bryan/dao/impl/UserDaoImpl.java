package lsq.Bryan.dao.impl;

import java.sql.Connection;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import lsq.Bryan.dao.UserDao;
import lsq.Bryan.domain.Privilege;
import lsq.Bryan.domain.User;
import lsq.Bryan.utils.JdbcUtils;

public class UserDaoImpl implements UserDao {
	@Override
	public void add(User user) {
		try {
			Connection conn = JdbcUtils.getConnection();
			QueryRunner runner = new QueryRunner();
			String sql = "insert into user(id,username,password,phone,cellphone,email,address) values(?,?,?,?,?,?,?)";
			Object[] params = {user.getId(), user.getUsername(), user.getPassword(), user.getPhone(), user.getCellphone(), user.getEmail(), user.getAddress()};
			runner.update(conn, sql, params);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	@SuppressWarnings("deprecation")
	@Override
	public User find(String id) {
		try {
			Connection conn = JdbcUtils.getConnection();
			QueryRunner runner = new QueryRunner();
			String sql = "select * from user where id=?";
			return runner.query(conn, sql, id, new BeanHandler<User>(User.class));
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	@SuppressWarnings("deprecation")
	@Override
	public User find(String username, String password) {
		try {
			Connection conn = JdbcUtils.getConnection();
			QueryRunner runner = new QueryRunner();
			String sql = "select * from user where username=? and password=?";
			return runner.query(conn, sql, new Object[]{username, password}, new BeanHandler<User>(User.class));
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	//得到用户拥有的所有权限
	@SuppressWarnings("deprecation")
	@Override
	public List<Privilege> getAllPrivilege(User user) {
		try {
			Connection conn = JdbcUtils.getConnection();
			QueryRunner runner = new QueryRunner();
			//多表查询
			String sql = "select p.* from user_privilege up,privilege p where up.user_id=? and p.id=up.privilege_id";
			return runner.query(conn, sql, user.getId(), new BeanListHandler<Privilege>(Privilege.class));
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}
