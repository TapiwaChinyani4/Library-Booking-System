/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Servlets;

import utils.DatabaseConnection;
import java.io.IOException;
import javax.servlet.ServletException;
import java.sql.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet Name: ResetPassword
 * Description: Handles password changes by validating the user by email and updating the password on the database if the user exists
 * @author Tapiwa Clifford Chinyani
 * JSP link: resetPassword.jsp
 */

@WebServlet(name = "ResetPassword", urlPatterns = {"/ResetPassword"})
public class ResetPassword extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String email = request.getParameter("email");
        String newPassword = request.getParameter("newPassword");

        try (Connection conn = DatabaseConnection.getConnection()) 
        {
            //SQL query to update a users password. Validate the user by email
            String sql = "UPDATE Users SET userPassword=? WHERE email=?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, newPassword);
            stmt.setString(2, email);

            int rowsUpdated = stmt.executeUpdate();//execute the update
            
            //if update is successful print meesage and redirect to login page
            if (rowsUpdated > 0) 
            {
                request.getSession().setAttribute("message", "Password reset successfully! You can now log in.");
                response.sendRedirect("login.jsp");
            } 
            else 
            {
                //dispaly error message if user is not found
                request.getSession().setAttribute("error", "Email not found. Please try again.");
                response.sendRedirect("resetPassword.jsp");
            }

        } 
        catch (SQLException e)
        {
            System.out.println("Database Error!");
            response.sendRedirect("resetPassword.jsp");
        }
    }
}

