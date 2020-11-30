package lsq.Bryan.utils;

import java.sql.Connection;

import javax.sql.DataSource;

import com.mchange.v2.c3p0.ComboPooledDataSource;

public class JdbcUtils {
	
	private static ThreadLocal<Connection> tl = new ThreadLocal<Connection>();
	
	private static DataSource ds;
	
	static {
		ds = new ComboPooledDataSource();
	}
	
	public static DataSource getDataSource() {
		return ds;
	}
	
	public static Connection getConnection() {
		try {
			//首先得到当前线程上绑定的连接
			Connection conn = tl.get();
			if (conn == null) {
				conn = ds.getConnection();//如果当前线程上没有绑定一个连接，则从数据库连接池拿一个连接
				conn.setAutoCommit(false);
			}
			tl.set(conn);//把连接绑定到当前线程上去
			return conn;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	public static void startTransaction() {
		try {
			Connection conn = getConnection();
			conn.setAutoCommit(false);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	public static void commitTransaction() {
		try {
			Connection conn = getConnection();
			if (conn != null) {
				conn.commit();
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	public static void closeConn() {
		Connection conn = null;
		try {
			conn = getConnection();
			if (conn != null) {
				conn.close();
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			/*
			if (conn != null) {
				tl.remove();
			}
			*/
			tl.remove();//千万要注意，解除当前线程上绑定的连接（从ThreadLocal容器中移除掉对应当前线程上的连接）
		}
	}
	
}
