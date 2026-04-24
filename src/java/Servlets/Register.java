/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Servlets;

import utils.DatabaseConnection;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Connection; 
import java.sql.PreparedStatement;   
import java.sql.SQLException; 

/**
 * Servlet Name: Register
 * Description: Handles user registration by adding users to the database 
 * @author Tapiwa Clifford Chinyani
 * JSP link: register.jsp
 */

@WebServlet(name = "Register", urlPatterns = {"/Register"})
public class Register extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
        throws ServletException, IOException {
        
        //Get form parameters from the JSP
        String fullName = request.getParameter("fullName");
        String email = request.getParameter("email");
        String userPassword = request.getParameter("userPassword");
        
        try (Connection conn = DatabaseConnection.getConnection()) 
        {
            //SQL query to insert a new user into the connected database
            String sql = "INSERT INTO Users (fullName, email, userPassword) VALUES (?, ?, ?)";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, fullName);
            ps.setString(2, email);
            ps.setString(3, userPassword);

            int row = ps.executeUpdate(); //Execute the insert query
            
            //redirect to login page if insertion was successful
            if (row > 0) 
            {
                response.sendRedirect("login.jsp");
            } 
            else
            {
                response.getWriter().println("Error registering user.");
            }

        } 
        catch (SQLException e)
        {
            System.out.println("Database Error!");
        }
    }
}