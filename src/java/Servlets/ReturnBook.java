/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Servlets;

import java.io.*;
import java.sql.*;
import java.time.LocalDate;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import utils.DatabaseConnection;

/**
 * Servlet Name: ReturnBook
 * Description: Handles book returns and logs return to a txt file 
 * @author Tapiwa Clifford Chinyani
 * JSP link: dashboard.jsp
 */

@WebServlet("/returnBook")
public class ReturnBook extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        //Get form parameter from the JSP
        int bookID = Integer.parseInt(request.getParameter("bookID"));
        String title = request.getParameter("Title");
        HttpSession session = request.getSession();

        try (Connection conn = DatabaseConnection.getConnection()) {
            /* 
             -SQL query to check if the book is currently borrowed (Avaliable = false)
             -"Available" is typed as "Avaliable" because a typo occurred during the 
             database creation, hence why its spelled incorrectly 
            */
            String checkSql = "SELECT Avaliable, Title FROM Books WHERE bookID=?";
            PreparedStatement checkStmt = conn.prepareStatement(checkSql);
            checkStmt.setInt(1, bookID);
            ResultSet rs = checkStmt.executeQuery();

            //Update book availability to true
            String updateSql = "UPDATE Books SET Avaliable=1 WHERE bookID=?";
            PreparedStatement updateStmt = conn.prepareStatement(updateSql);
            updateStmt.setInt(1, bookID);
            updateStmt.executeUpdate();

            // Update Borrow table to set dateReturned
            String returnSql = "UPDATE Borrow SET dateReturned=? WHERE bookID=? AND userID=? AND dateReturned IS NULL";
            PreparedStatement returnStmt = conn.prepareStatement(returnSql);
            returnStmt.setDate(1, java.sql.Date.valueOf(java.time.LocalDate.now()));
            returnStmt.setInt(2, bookID);
            returnStmt.setInt(3, (Integer) session.getAttribute("userID"));
            returnStmt.executeUpdate();

            //Log return to txt file
            try (FileWriter fw = new FileWriter("return_log.txt", true);
                BufferedWriter bw = new BufferedWriter(fw);
                PrintWriter out = new PrintWriter(bw)) 
            {
                    out.println("User: " + session.getAttribute("email") + 
                                " | " + "Returned Book: " + title + 
                                " | " + "Date: " + java.time.LocalDate.now());
            } 
            catch (Exception e) 
            {
                    //Print error if record is not logged
                    System.out.println("Error. Return not Logged");
            }
            
                //redirect to dashboard
                response.sendRedirect("dashboard.jsp");//redirect to dashboard
            
        } 
        catch (SQLException e) 
        {
            System.out.println("Database Error!");
        }
    }
}

