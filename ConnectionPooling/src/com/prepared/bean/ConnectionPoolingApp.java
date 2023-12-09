package com.prepared.bean;

import java.beans.Statement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.microsoft.sqlserver.jdbc.SQLServerConnectionPoolDataSource;
import com.microsoft.sqlserver.jdbc.SQLServerException;


public class ConnectionPoolingApp {
	private static String jdbcURL = "jdbc:sqlserver://localhost:1433;databaseName=CIEBWEB;integratedSecurity=true\r\n"
			+ "\r\n" + "";
	private static String sql ="select * from users";
	public static void main(String[] args) throws SQLServerException, SQLException {
		//getting logical connection instead of physical connection
		SQLServerConnectionPoolDataSource dataSource=new SQLServerConnectionPoolDataSource();
		dataSource.setURL(jdbcURL);
		System.out.println("USERNAME\tPASSWORD");
		System.out.println("___________________________");
		try(Connection connection = dataSource.getConnection(); PreparedStatement statement=connection.prepareStatement(sql);
				ResultSet resultSet=statement.executeQuery();){
			while(resultSet.next()) {
				String name = resultSet.getString(1);
				String password = resultSet.getString(2);
				System.out.println(name+"\t\t"+password);
			}
			
		}
		
	}

}
