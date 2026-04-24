<%-- 
    Document   : dashboard
    Created on : 03 Nov 2025, 19:33:31
    Description: Dashboard where users can see avaibale books and can borrow or return books
    Author     : Tapiwa Clifford Chinyani
--%>

<%@ page session="true" %>
<%@ page import="java.sql.*, utils.DatabaseConnection" %>
<html>
<head>
    <title>Library Dashboard</title>
    <style>
        <%-- CSS section for styling my web pages --%>
        body { 
            font-family: Arial, sans-serif; 
            background-color: #ececec; 
            padding: 20px; 
        }
        h2 { color: #333; text-align: center; }
        a.button, input[type=submit] { 
            text-decoration: none; 
            padding: 7px 15px; 
            background-color: #4A90E2; 
            color: white; 
            border-radius: 3px; 
            border: none; 
            cursor: pointer; 
            margin: 3px;
        }
        input[type=submit]:disabled { 
            background-color: #ccc; 
            cursor: not-allowed; 
        }
        input[type=submit]:hover:not(:disabled), a.button:hover { 
            background-color: #357ABD; 
        }
        table { 
            width: 90%; 
            margin: 20px auto; 
            border-collapse: collapse; 
            background-color: white; 
            box-shadow: 0 0 10px #ccc; 
        }
        th, td { 
            border: 1px solid #ddd; 
            padding: 10px; 
            text-align: center; 
        }
        th { 
            background-color: #f2f2f2; 
        }
        .top-buttons { text-align: center; margin-bottom: 15px; }
    </style>
</head>
<body>
 <%-- Welcome message for the user with thier name displayed --%>
<h2>Welcome, <%= session.getAttribute("fullName") %>!</h2>

 <%-- buttons to update info or logout of the system --%>
<div class="top-buttons">
    <a href="UpdateUser.jsp" class="button">Update Profile</a>
    <a href="logout" class="button">Logout</a>
</div>

<h3 style="text-align:center;">Books in the Library</h3>

<table>
    <tr>
        <th>ID</th>
        <th>Title</th>
        <th>Author</th>
        <th>Description</th>
        <th>Available</th>
        <th>Actions</th>
    </tr>

     <%-- SQL query that displays all avaibale books from our database/ Books tabale --%>
<%
    Connection conn = null;
    Statement stmt = null;
    ResultSet rs = null;
    
    
    try {
        conn = DatabaseConnection.getConnection();
        stmt = conn.createStatement();
        rs = stmt.executeQuery("SELECT * FROM Books");

        while(rs.next()) {
            int bookID = rs.getInt("bookID");
            String title = rs.getString("Title");
            String author = rs.getString("Author");
            String description = rs.getString("bookDescription");
            boolean available = rs.getBoolean("Avaliable"); 
%>
    <tr>
        <td><%= bookID %></td>
        <td><%= title %></td>
        <td><%= author %></td>
        <td><%= description %></td>
        <td><%= available %></td>
        <td>
            <%-- Borrow Button which is blue when books Available column is 
            true/ >1 and turns gray if the column is false/0 --%>
            <form action="borrow" method="post" style="display:inline;">
                <input type="hidden" name="bookID" value="<%= bookID %>"/>
                <input type="submit" value="Borrow" <%= available ? "" : "disabled" %> />
            </form>

            <%-- Return Button Button which is blue when books Available column is 
            true/ >1 and turns gray if the column is false/0 --%>
            <form action="returnBook" method="post" style="display:inline;">
                <input type="hidden" name="bookID" value="<%= bookID %>"/>
                <input type="hidden" name="Title" value="<%= title %>"/>
                <input type="submit" value="Return" <%= !available ? "" : "disabled" %> />
            </form>
        </td>
    </tr>
    
    <%-- catch database errors that may occur --%>
<%
        }
    } catch(SQLException e) {
        out.println("<tr><td colspan='6'>Database Error: " + e.getMessage() + "</td></tr>");
    } finally {
        if(rs != null) try { rs.close(); } catch(Exception e) {}
        if(stmt != null) try { stmt.close(); } catch(Exception e) {}
        if(conn != null) try { conn.close(); } catch(Exception e) {}
    }
%>
</table>

</body>
</html>



