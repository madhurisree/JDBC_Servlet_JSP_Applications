
package com.prepared.service;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.commons.io.IOUtils;

import com.prepared.utility.JDBCUtility;

public class SelectOperation {

	private static Connection connection = null;
	private static PreparedStatement pstmt = null;
	private static ResultSet resultSet = null;
	private static FileWriter writer=null;

	private static String selectQuerry = null;

	public static void selectOperation(String personName) throws IOException {
		connection = JDBCUtility.getConnection();

		if (connection != null) {
			// System.out.println("ID");

			selectQuerry = "select city_name,history from cities where city_name =?";
			System.out.println("FIRST_NAME" + "\t" + "LAST_NAME" + "\t" + "EMAIL");
			System.out.println("======================================");
			if (selectQuerry != null) {
				try {
					pstmt = connection.prepareStatement(selectQuerry);
					pstmt.setString(1, personName);
					if (pstmt != null) {
						resultSet = pstmt.executeQuery();
						if (resultSet.next()) {
							String person_name = resultSet.getString("city_name");
							//Blob person_image = resultSet.getBlob("person_image");
							//resultSet.getClob("per)
							Reader reader=resultSet.getCharacterStream(2);
							String fileName="Hyd_info.txt";
							File file=new File(fileName);
							writer=new FileWriter(file);
//							byte[] b=new byte[4096];
//							while(is.read(b)>0) {
//								fos.write(b);
//							}
							IOUtils.copy(reader, writer);
							
							writer.flush();
							System.out.println(person_name);
							System.out.println("File location :"+file.getAbsolutePath());
						}
					}

				} catch (SQLException e) { // TODO Auto-generated catch block
					e.printStackTrace();
				} finally {
					try {
						JDBCUtility.closeConnection(null, pstmt, connection);
					} catch (SQLException e1) {
						// TODO Auto-generated catch block e1.printStackTrace();
					}
					
					if(writer != null) {
						writer.close();
					}
				}
			}
		}

	}

}
