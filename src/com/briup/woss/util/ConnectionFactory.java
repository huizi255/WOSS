package com.briup.woss.util;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;


public class ConnectionFactory{
	private static String driver;
	private static String url;
	private static String user;
	private static String password;
	private static Connection conn;
	
	
	static{
		Properties p = new Properties();
		try {
			p.load(ConnectionFactory.class.getResourceAsStream("propertity.properties"));
			driver = p.getProperty("driverName");
			url = p.getProperty("url");
			user = p.getProperty("username");
			password = p.getProperty("password");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static Connection getConnection(){
		try {
			Class.forName(driver);
			conn = DriverManager.getConnection(url, user, password);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return conn;
	}
	
	public static void close(ResultSet rs,Statement stmt,Connection conn){
		try {
			if(rs!=null)rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			if(stmt!=null)stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			if(conn!=null)conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
