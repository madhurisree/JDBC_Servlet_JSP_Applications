package com.request.app;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class RequestAppEx
 */
@WebServlet(urlPatterns = "/reg", loadOnStartup = 1)
public class RequestAppEx extends HttpServlet {
	private static final long serialVersionUID = 1L;
	JDBCUtility utility=null;
     
	static {
		System.out.println("Servlet loading.......");
	}
	public RequestAppEx() {
		System.out.println("Servlet instatiation.....");
		utility=new JDBCUtility();
	}
	public void init() {
		System.out.println("Servlet intialization....");
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("Request processing for POST type method....");
		System.out.println("Request object implementation calss.."+request.getClass().getName());
		System.out.println("Request Object Id :"+request.hashCode());
		System.out.println("Response object implementation calss.."+response.getClass().getName());
		System.out.println("Response Object Id :"+response.hashCode());
		System.out.println("Thread Object Id  :"+Thread.currentThread().getId());
		
		Connection connection = utility.getConnection();
		System.out.println("Connection Object "+connection);
		String sql="insert into request_information(UserName,Contact_Name,Email,Subject_Name) values(?,?,?,?)";
		PreparedStatement prepareStatement=null;
		String canName=null;
		String conNum=null;
		String cnEmail=null;
		String[] subjects=null;
		ResultSet rs=null;
		String conName1=null;
		String conNum1=null;
		String cnEmail1=null;
		String subject=null;
		int rowaffected=0;
		try {
			if(connection!= null) {
				prepareStatement = connection.prepareStatement(sql);
				canName = request.getParameter("un");
				prepareStatement.setString(1, canName);
				conNum = request.getParameter("cn");
				prepareStatement.setString(2, conNum);
				cnEmail = request.getParameter("email");
				prepareStatement.setString(3, cnEmail);
				subjects = request.getParameterValues("Subjects");
				for (String course : subjects) {
						prepareStatement.setString(4, course);
					
				}
				rowaffected=prepareStatement.executeUpdate();
				if(rowaffected>0) {
					PreparedStatement result=connection.prepareStatement("select * from request_information where UserName=?");
					result.setString(1, canName);
					boolean execute = result.execute();
					System.out.println("executed "+execute);
					ResultSetMetaData metaData = result.getMetaData();
					System.out.println("Meta Data "+metaData);
					rs = result.executeQuery();
					System.out.println("Result "+rs);
					while (rs.next()) {
						conName1 = rs.getString(1);
						System.out.println("Name"+conName1);
						conNum1=rs.getString(2);
						cnEmail1=rs.getString(3);
						subject = rs.getString(4);
						
					}
				}
					
			}
	
		} catch (SQLException e1) {
			e1.printStackTrace();
		}finally {
			try {
				rs.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
		try {
			Thread.sleep(3000);
		} catch (Exception e) {
			// TODO: handle exception
		}
	
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.println("<html><head><title>OUTPUT PAGE</html></head></title>");
		out.println("<body bgcolor='cyan' text='white'>");
		out.println("<h1 text-align='center', color='blue'>Student Details ::</align></h1>");
		out.println("<table border=1 align='center' bgcolor='blue'>");
		out.println("<tr><th>Candidate Name </th><td>"+conName1+"</td></tr>");
		out.println("<tr><th>Contact Number </th><td>"+conNum1+"</td></tr>");
		out.println("<tr><th>Candidate Email </th><td>"+cnEmail1+"</td></tr>");
		out.println("<tr>");
		out.println("<th>Course :");
		out.println("<td>");
		out.println(subject+"<br/>");
		out.println("</td>");
		out.println("</th>");
		out.println("</tr>");
		out.println("<tr><th>Request Object Id  </th><td>"+request.hashCode()+"</td></tr>");
		out.println("<tr><th>Response Object Id  </th><td>"+response.hashCode()+"</td></tr>");
		out.println("<tr><th>Thread Object id </th><td>"+Thread.currentThread().getId()+"</td></tr>");
		out.println("</table>");
		out.println("Record inserted successfully");
		out.println("</body>");
		out.println("</html>");
		
		//closing the response object
		out.close();
		
	}
	@Override
	public void destroy() {
		System.out.println("Servlet Deinstatialtion....");
	}

}
