package com.dj;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		String name = request.getParameter("name");
		String city = request.getParameter("city");
		String phone = request.getParameter("phone");
		String email = request.getParameter("email");
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			// Replace DB_URL, DB_USER, and DB_PASS with your own database URL, username, and password
			String DB_URL = "jdbc:mysql://localhost:3306/aswar?SSL=false";
			String DB_USER = "root";
			String DB_PASS = "Rushabh@123";
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
			
			// Prepare the SQL statement for inserting a new student record
			String sql = "INSERT INTO students (id, name, city, phone, email) VALUES (?, ?, ?, ?, ?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, id);
			pstmt.setString(2, name);
			pstmt.setString(3, city);
			pstmt.setString(4, phone);
			pstmt.setString(5, email);
			
			// Execute the SQL statement to insert the new record
			pstmt.executeUpdate();
			
			// Redirect the user to a success page
			response.sendRedirect(request.getContextPath() + "/success.html");
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			// Redirect the user to an error page
			response.sendRedirect(request.getContextPath() + "/error.html");
		} finally {
			// Close the database connection and prepared statement
			try {
				if (pstmt != null) {
					pstmt.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
