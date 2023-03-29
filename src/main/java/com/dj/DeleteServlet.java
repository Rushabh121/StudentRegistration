package com.dj;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DeleteServlet extends HttpServlet  {
 public void doPost(HttpServletRequest req,HttpServletResponse res)throws ServletException,IOException{
	 try{Class.forName("com.mysql.cj.jdbc.Driver");
		String user="root";
		String password="Rushabh@123";
		String url="jdbc:mysql://localhost:3306/aswar";
		Connection con=DriverManager.getConnection(url, user, password);
		Statement smt=con.createStatement();
		while(true) {
		int id = Integer.parseInt(req.getParameter("id"));
		int rowsAffected=smt.executeUpdate("delete from students where id="+id);
		if (rowsAffected > 0) {
            // Success message
            res.sendRedirect(req.getContextPath() + "/deleteSuccess.html");
        } else {
            // Error message
            res.sendRedirect(req.getContextPath() + "/error.html");
        }
    } 
	 }catch (Exception e) {
        // Handle any exceptions
        e.printStackTrace();
    }
				
 }
}
