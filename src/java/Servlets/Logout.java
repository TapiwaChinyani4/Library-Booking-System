/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Servlets;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

/**
 * Servlet Name: Logout
 * Description: Allows logout of the system and redirect them to the login page
 * @author Tapiwa Clifford Chinyani
 * JSP link: dashboard.jsp
 */


@WebServlet("/logout")
public class Logout extends HttpServlet {
    //Handles GET request to terminate the users session.
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false); //checks if session exists and returns null if non exists
        
       
        if (session != null)
        {
            session.invalidate();//Terminates the sessions and clears all attributes
        }
        //Redirects to the login page
        response.sendRedirect("login.jsp");
    }
}

