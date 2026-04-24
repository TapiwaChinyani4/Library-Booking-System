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
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Servlet Name: UpdateUser
 * Description: Allows users to update their name or password. Validates users by email.
 * @author Tapiwa Clifford Chinyani
 * JSP link: UpdateUser.jsp
 */

@WebServlet("/updateUser")
public class UpdateUser extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        //Create a session if non exists and get form parameters from the JSP
        HttpSession session = request.getSession();
        String email = (String) request.getParameter("email");
        String fullName = request.getParameter("fullName");
        String userPassword = request.getParameter("userPassword");

        try (Connection conn = DatabaseConnection.getConnection()) 
        {
            //SQL query to update the users name or password 
            String sql = "UPDATE Users SET fullName = ?, userPassword = ? WHERE email = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, fullName);
            stmt.setString(2, userPassword);
            stmt.setString(3, email);

            int rowsUpdated = stmt.executeUpdate();//execute update
            
            //If update is succesful, redirect the user to the dashboard set thier new name in the seesion
            if (rowsUpdated > 0) 
            {
                session.setAttribute("fullName", fullName);
                response.sendRedirect("dashboard.jsp");
            } 
            else 
            {
                response.getWriter().println("Update Failed!");//display error if update is unsccessful
            }

        } 
        catch (SQLException e) 
        {
            //Displays error if thier issues connecting to the database
            System.out.println("Database Error!");
        }
    }
}

