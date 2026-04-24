/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utils;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
/**
 * Utils: DatabaseConnection
 * Description: This serves as a helper for our database connection setup 
 * @author chiny
 */
public class DatabaseConnection {
    
    //Used final variables to ensure our constants are not changed
    private static final String URL = "jdbc:sqlserver://localhost:1433;databaseName=LibraryDB;trustServerCertificate=true";
    private static final String USER = "Tapiwa";
    private static final String PASSWORD = "Leah";
    
    //servlets import and call the getConnection method to connect to the database
    public static Connection getConnection() throws SQLException {
        try //Try/catch to handle SQLException
        {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } 
        catch (ClassNotFoundException e) 
        {
            e.printStackTrace();
            return null;
        }
    }
}
