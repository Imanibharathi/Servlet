package org.btm.getApp;

import java.io.*;
import java.sql.*;

import javax.servlet.*;
import javax.servlet.http.*;

public class FirstServlet extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String sid =req.getParameter("i");
		int id = Integer.parseInt(sid);
		PrintWriter out=resp.getWriter();
		out.println("<html><body bgcolor='red'>"
				+ "<h1>Id is "+id+"</body><html>");
		out.close();
		
		Connection con=null;
		PreparedStatement pstmt =null;
		ResultSet rs =null;
		String qry ="select *  from btm.student101 where id=?";
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con=DriverManager.getConnection("jdbc:mysql://localhost:3306?user=root&password=admin");
			pstmt=con.prepareStatement(qry);
			pstmt.setInt(1, id);
			rs=pstmt.executeQuery();
			if(rs.next()) {
				String name =rs.getString(2);
				String dept=rs.getString(3);
				Double perc=rs.getDouble(4);
				System.out.println("name = "+name);
				System.out.println("dept = "+dept);
				System.out.println("perc = "+perc);
					} 
			else {
				System.err.println("No data found ");
			}
		}
			catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
		finally {
			if(rs!=null) {
				try {
					rs.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
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


