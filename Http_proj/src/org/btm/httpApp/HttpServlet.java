package org.btm.httpApp;

import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class HttpServlet  extends javax.servlet.http.HttpServlet{
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	String sid =req.getParameter("i");
	int id = Integer.parseInt(sid);
	String name = req.getParameter("nm");
	String dept = req.getParameter("dp");
	String sperc = req.getParameter("pr");
	Double perc =Double.parseDouble(sperc);
	PrintWriter out=resp.getWriter();
	out.println("<html><body bgcolor='red'>"
			+ "<h1>Student is "
			+name+" from "
					+dept+"</h1>"+"</body><html>");
	out.close();
	
	Connection con=null;
	PreparedStatement pstmt =null;
	String qry ="insert into btm.student101 values(?,?,?,?)";
	try {
		Class.forName("com.mysql.jdbc.Driver");
		con=DriverManager.getConnection("jdbc:mysql://localhost:3306?user=root&password=admin");
		pstmt=con.prepareStatement(qry);
		pstmt.setInt(1, id);
		pstmt.setString(2, name);
		pstmt.setString(3, dept);
		pstmt.setDouble(4, perc);
		pstmt.executeUpdate();
		
		
	} catch (ClassNotFoundException | SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}  
	finally {
		if(pstmt!=null) {
			try {
				pstmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if(con!=null) {
			try {
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	}
	
	

}
