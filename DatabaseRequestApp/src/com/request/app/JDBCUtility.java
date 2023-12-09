package com.request.app;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JDBCUtility {
	
	private static Connection connection=null;
	private static Statement statement =null;
	private static ResultSet executeQuery = null;

	private static String jdbcURL = "jdbc:sqlserver://localhost:1433;databaseName=CIEBWEB;integratedSecurity=true\r\n"
			+ "\r\n" + "";
	public JDBCUtility() {
		
		
	}
	public static void main(String[] args) throws ClassNotFoundException {
		Connection connection2 = getConnection();
		System.out.println(connection2);
	}
	
	public static Connection getConnection()   {
		if(jdbcURL != null) {
			try {
				Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
				connection=DriverManager.getConnection(jdbcURL);
				if(connection != null) {
					System.out.println("Connection established");
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
		}
		
		return connection;
		
	}
	
	public static void closeConnection(ResultSet rs, Statement stmt, Connection con) throws SQLException {
		if(rs != null) {
			System.out.println("Result set closed");
			rs.close();
		}
		if(stmt != null) {
			System.out.println("Statement closed");
			stmt.close();
		}
		if(con != null) {
			System.out.println("Connection closed");
			con.close();
		}
	}

}
