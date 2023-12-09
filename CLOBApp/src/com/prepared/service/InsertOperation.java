package com.prepared.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.prepared.bean.Person;
import com.prepared.utility.JDBCUtility;


public class InsertOperation {
	private static Connection connection = null;
	private static PreparedStatement pstmt=null;
	private static String insertQuery=null;
	
	public static void insertOperation(Person person) {
		connection =JDBCUtility.getConnection();
		if(connection != null) {
			insertQuery = "insert into cities(city_name,history) values(?,?)";
			if(insertQuery != null) {
				try {
						pstmt = connection.prepareStatement(insertQuery);
						pstmt.setString(1, person.getPerson_name());
					/*
					 * File file=new File("dhoni.jpg"); FileInputStream fis = new
					 * FileInputStream(file);
					 */
						pstmt.setClob(2, person.getPerson_image());
						
					if(pstmt != null) {
						int rowCount = pstmt.executeUpdate();
						if(rowCount ==1) {
							System.out.println("Row inserted successfully "+rowCount);
						}
						else {
							System.out.println("No record inserted");
						}
					}
				} catch (SQLException e) {
					e.printStackTrace();
				} catch (Exception e) {
					e.printStackTrace();
				}
				finally {
					try {
						JDBCUtility.closeConnection(null, pstmt, connection);
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
			
		}
		
	}

}
