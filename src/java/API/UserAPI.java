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
 * Servlet Name: UserAPI
 * Description: Handles adding and updating user information on the database
 * @author Tapiwa Clifford Chinyani
 */

@WebServlet("/userapi")
public class UserAPI extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        //request form parameters from JSP
        String email = request.getParameter("email");
        String fullName = request.getParameter("fullName");
        String userPassword = request.getParameter("userPassword");
        
        //Create JSON object
        JSONObject json = new JSONObject();
        
        try(Connection conn = DatabaseConnection.getConnection()) {
            
            //Check if the user does not exist on the database
            PreparedStatement checkStmt = conn.prepareStatement(
                    "SELECT COUNT(*) FROM Users WHERE email = ?");
            checkStmt.setString(1, email);
            ResultSet rs = checkStmt.executeQuery();
            rs.next();
            int count = rs.getInt(1);
            
            if (count > 0)
            {
                //If the user exists update thier information
                PreparedStatement updateStmt = conn.prepareStatement(
                    "UPDATE Users SET fullName = ?, userPassword =? WHERE email = ?");
                updateStmt.setString(1,fullName);
                updateStmt.setString(2,userPassword);
                updateStmt.setString(3,email);
                updateStmt.executeUpdate();
                
                //Print a JSON response if update is successful
                json.put("status", "success");
                json.put("message", "User updated!");
            }
            else
            {
                //Add the users information to the database if the users doesnt exists 
                PreparedStatement insertStmt = conn.prepareStatement(
                    "INSERT INTO Users (fullName, userPassword, email) VALUES (?, ?, ");
                insertStmt.setString(1,fullName);
                insertStmt.setString(2,userPassword);
                insertStmt.setString(3,email);
                insertStmt.executeUpdate();
                
                //Print a JSON response if addition is successful 
                json.put("status", "success");
                json.put("message", "User added!");
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
