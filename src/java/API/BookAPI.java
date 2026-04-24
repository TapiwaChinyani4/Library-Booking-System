/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package API;

import utils.DatabaseConnection;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.*;
import java.sql.*;
import org.json.JSONObject;

/**
 * Servlet Name: BookAPI
 * Description: Handles adding and updating book information on the database
 * @author Tapiwa Clifford Chinyani
 * 
 */

@WebServlet("/bookapi")
public class BookAPI extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        //Request the form parameters from the JSP 
        String Title = request.getParameter("Title");
        String Author = request.getParameter("Author");
        String bookDescription = request.getParameter("bookDescription");
        int Avaliable = Integer.parseInt(request.getParameter("Avaliable"));//Available intentionally misspelled 
        
        //Create a JSON object
        JSONObject json = new JSONObject();
        
        try(Connection conn = DatabaseConnection.getConnection()) 
        {
            //Check if the book exists in the database
            PreparedStatement checkStmt = conn.prepareStatement(
                    "SELECT COUNT(*) FROM Books WHERE Title = ?");
            checkStmt.setString(1, Title);
            ResultSet rs = checkStmt.executeQuery();
            rs.next();
            int count = rs.getInt(1);
            
            if (count > 0)
            {
                //Update the books information if the book exists in the database 
                PreparedStatement updateStmt = conn.prepareStatement(
                        "UPDATE Books SET Author = ?, bookDescription = ?, Avaliable = ? WHERE Title = ?");
                updateStmt.setString(1, Author);
                updateStmt.setString(2, bookDescription);
                updateStmt.setInt(3, Avaliable);
                updateStmt.setString(4, Title);
                updateStmt.executeUpdate();
                
                //JSON reponse to a successful update
                json.put("status", "success");
                json.put("message", "Book updated!");
            
            }
            else
            {
                //Add the books informtion to the database if the book doesnt exsits
                PreparedStatement insertStmt = conn.prepareStatement(
                        "INSERT INTO Books (Title, Author, bookDescription, Avaliable) VALUES (?, ?, ?, ?)");
                insertStmt.setString(1,Title);
                insertStmt.setString(2,Author);
                insertStmt.setString(3,bookDescription);
                insertStmt.setInt(4,Avaliable);
                insertStmt.executeUpdate();
                
                //JSON response to a successful addition 
                json.put("status", "success");
                json.put("message", "Book added!");    
            }
        } 
        catch(Exception e)
        {
            //JSON response to a database error
            json.put("status", "error");
            json.put("message", e.getMessage());
        }
        //Tells client to respond in JSON
        response.setContentType("application/json");
        response.getWriter().print(json.toString());
    }
}

