/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Servlets;

import utils.DatabaseConnection;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.*;

/**
 * Servlet Name: Login
 * Description: Allows users to log into the Library System by entering their email and password
 * @author Tapiwa Clifford Chinyani
 * JSP link: login.jsp
 */

@WebServlet("/login")
public class Login extends HttpServlet {
    
    //Handles POST request for user Login and redirects to dashboard if successful.
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        //Get form parameters from the JSP
        String userID = request.getParameter("userID");
        String email = request.getParameter("email");
        String userPassword = request.getParameter("userPassword");

        try (Connection conn = DatabaseConnection.getConnection()) 
        {
            //SQL query to fetch login data of the user (email and password)
            String sql = "SELECT * FROM Users WHERE email=? AND userPassword=?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, email);
            stmt.setString(2, userPassword);
            ResultSet rs = stmt.executeQuery();
            
            //If login was successful, create an active session for the user and redirect them to the dashboard page
            if (rs.next()) 
            {
                HttpSession session = request.getSession();
                session.setAttribute("userID", rs.getInt("userID"));
                session.setAttribute("email", rs.getString("email"));
                session.setAttribute("fullName", rs.getString("fullName"));
                response.sendRedirect("dashboard.jsp");
            } 
            else 
            {
                //Error is printed if the credientials are incorrect
                response.getWriter().println("Invalid email or password");
            }
        } 
        catch (SQLException e)
        {
            //Database error is printed
            System.out.println("Database Error!");
        }
    }
}

