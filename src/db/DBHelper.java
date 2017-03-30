package db;

import java.sql.*;

public class DBHelper {	
	private String dbUrl = "jdbc:mysql://localhost:3306/train_ticket_db?useUnicode=true&characterEncoding=utf-8";
	private String dbUser = "root";
	private String dbPassword = "root";
	private String jdbcName = "com.mysql.jdbc.Driver";
	
	public static final String TABLE_USERS = "users";  
    public static final String TABLE_ORDERS = "orders";  
	
	//连接数据库
	public Connection getConn(){
		Connection conn = null;
		try{
			Class.forName(jdbcName);
		}
		catch(Exception e){}
		try{
			conn=DriverManager.getConnection(dbUrl, dbUser, dbPassword);
		}
		catch(SQLException ex){}
		return conn;		
	}
}
