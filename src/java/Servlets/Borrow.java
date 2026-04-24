/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Servlets;

import utils.DatabaseConnection;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.*;
import java.sql.*;
import java.time.LocalDate;

/**
 * Servlet Name: Borrow
 * Description: Handles book borrows and logs borrows to a txt file 
 * @author Tapiwa Clifford Chinyani
 * JSP link: dashboard.jsp
 */

@WebServlet("/borrow")
public class Borrow extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        //Create session if non exists and get form parameters from JSP
        HttpSession session = request.getSession();
        int userID = (int) session.getAttribute("userID");
        int bookID = Integer.parseInt(request.getParameter("bookID"));
        String title = request.getParameter("Title");

        try (Connection conn = DatabaseConnection.getConnection())// connect to database
        {
            // Check if book is available
            String sql = "SELECT Avaliable, Title FROM Books WHERE bookID=?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, bookID);
            stmt.executeQuery();// execute check query

            // Mark book as unavailable
            String updateSql = "UPDATE Books SET Avaliable=0 WHERE bookID=?";
            PreparedStatement updateStmt = conn.prepareStatement(updateSql);
            updateStmt.setInt(1, bookID);
            updateStmt.executeUpdate();
                    
            // Insert borrowed book record into the borrow table
            String borrowSql = "INSERT INTO Borrow (userID, bookID, dateBorrowed) VALUES (?, ?, ?)";
            PreparedStatement borrowStmt = conn.prepareStatement(borrowSql);
            borrowStmt.setInt(1, userID);
            borrowStmt.setInt(2, bookID);
            borrowStmt.setDate(3, Date.valueOf(LocalDate.now()));
            borrowStmt.executeUpdate();
                    
            // Log to file 
            try (FileWriter fw = new FileWriter("borrow_log.txt", true);
                BufferedWriter bw = new BufferedWriter(fw);
                PrintWriter out = new PrintWriter(bw)) 
            {
                    out.println("User: " + session.getAttribute("email") + 
                                " | " + "Borrowed Book: " + title + 
                                " | " + "Date: " + java.time.LocalDate.now());
            } 
            catch (Exception e) 
            {
                    //Print error if record is not logged
                    System.out.println("Error. Return not Logged");
            }
            // Redirect to dashboard
            response.sendRedirect("dashboard.jsp");
     
        } 
        catch (SQLException e)
        {
            System.out.println("Database Error!");
        }
    }
}

