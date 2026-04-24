<%-- 
    Document   : UpdateUser
    Created on : 03 Nov 2025, 23:05:54
    Description: Allows users to update thier information
    Author     : Tapiwa Clifford Chinyani
--%>

<%@ page session="true" %>
<html>
<head>
    <title>Update Profile</title>
    <style>
        <%-- CSS section for styling my web pages --%>
        body { 
            font-family: Arial, sans-serif; 
            background-color: #ececec; 
        }
        .container { 
            width: 350px; 
            margin: 50px auto; 
            padding: 20px; 
            background-color: white; 
            border-radius: 5px; 
            box-shadow: 0px 0px 10px #ccc; 
        }
        h2 { 
            text-align: center; 
            color: #333; 
        }
        label { 
            display: block; 
            margin-top: 10px; 
        }
        input[type=text], input[type=password] { 
            width: 100%; 
            padding: 8px; 
            margin-top: 5px; 
            border: 1px solid #ccc; 
            border-radius: 3px; 
        }
        input[type=submit] { 
            margin-top: 15px; 
            width: 100%; 
            padding: 10px; 
            background-color: #4A90E2; 
            color: white; 
            border: none; 
            border-radius: 3px; 
            cursor: pointer; 
        }
        input[type=submit]:hover { 
            background-color: #357ABD; 
        }
        a { 
            display: block; 
            text-align: center; 
            margin-top: 15px; 
            color: #555; 
            text-decoration: none; 
        }
        a:hover { 
            text-decoration: underline; 
        }
    </style>
</head>
<body>
    <div class="container">
        <h2>Update Your Profile</h2>
        
        <%--UpdateUser form send data to the updateUser servlet so as to change the users details on the database--%>
        <form action="updateUser" method="post">
            <label for="fullName">Full Name:</label>
            <input type="text" id="fullName" name="fullName" 
                   value="<%= session.getAttribute("fullName") != null ? session.getAttribute("fullName") : "" %>" required>
            <%--email is hidden as it is our identifier that tells the database which users information to change.
                the new password and/or fullname is passed to the database by the servlet.
            --%>
            <label for="password">New Password:</label>
            <input type="password" id="password" name="userPassword" placeholder="Enter new password" required>
            <input type="hidden" name="email" value="<%= session.getAttribute("email") %>"/>
            <input type="submit" value="Update Profile">
        </form>
        
        <%--redirects to the dashboard if the users info is updated successfully--%>
        <a href="dashboard.jsp">Back to Dashboard</a>
    </div>
</body>
</html>


