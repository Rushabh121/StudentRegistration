package com.dj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UpdateServlet extends HttpServlet {
    public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String user = "root";
            String password = "Rushabh@123";
            String url = "jdbc:mysql://localhost:3306/aswar";
            Connection con = DriverManager.getConnection(url, user, password);

            // Retrieve the input values from the HttpServletRequest object
            int id = Integer.parseInt(req.getParameter("id"));
            String name = req.getParameter("name");
            String city = req.getParameter("city");
            String phone = req.getParameter("phone");
            String email = req.getParameter("email");

            // Create the SQL query string for updating the student detail
            String sql = "UPDATE students SET name=?, city=?, phone=?, email=? WHERE id=?";

            // Prepare the statement object with the SQL query
            PreparedStatement statement = con.prepareStatement(sql);
            statement.setString(1, name);
            statement.setString(2, city);
            statement.setString(3, phone);
            statement.setString(4, email);
            statement.setInt(5, id);

            // Execute the update query
            int rowsAffected = statement.executeUpdate();

            // Check if any rows were affected
            if (rowsAffected > 0) {
                // Success message
                res.sendRedirect(req.getContextPath() + "/updateSuccess.html");
            } else {
                // Error message
                res.sendRedirect(req.getContextPath() + "/error.html");
            }

        } catch (Exception e) {
            // Handle any exceptions
            e.printStackTrace();
        }
    }
}
