<%-- 
    Document   : register
    Created on : 03 Nov 2025, 19:32:52
    Descritpion: Allows user to register if they don't have login details 
    Author     : Tapiwa Clifford Chinyani
    
--%>

<%@ page session="true" %>
<%@ page import="java.sql.*, utils.DatabaseConnection" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Register - Library Management System</title>
    <style>
        <%-- CSS section for styling my web pages --%>
        body {
            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
            background-color: #ececec;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
            margin: 0;
        }

        .register-container {
            background-color: white;
            padding: 40px;
            border-radius: 10px;
            box-shadow: 0 4px 10px rgba(0, 0, 0, 0.1);
            width: 350px;
            text-align: center;
        }

        h2 {
            color: #333;
            margin-bottom: 25px;
        }

        label {
            display: block;
            text-align: left;
            margin-bottom: 5px;
            color: #555;
            font-weight: 500;
        }

        input[type=text],
        input[type=password] {
            width: 100%;
            padding: 10px;
            margin-bottom: 15px;
            border: 1px solid #ccc;
            border-radius: 5px;
            box-sizing: border-box;
            font-size: 14px;
        }

        input[type=submit] {
            width: 100%;
            background-color: #4A90E2;
            color: white;
            border: none;
            padding: 10px;
            border-radius: 5px;
            cursor: pointer;
            font-weight: bold;
            font-size: 15px;
        }

        input[type=submit]:hover {
            background-color: #357ABD;
        }

        .links {
            margin-top: 20px;
        }

        .links a {
            text-decoration: none;
            color: #4A90E2;
            font-weight: bold;
            display: block;
            margin-top: 8px;
        }

        .links a:hover {
            text-decoration: underline;
        }

        .message {
            color: red;
            margin-top: 15px;
            font-size: 14px;
        }
    </style>
</head>
<body>
    <div class="register-container">
        <h2>Create an Account</h2>
        <%-- Register form that will send data to the register servlet for information input--%>
        <form action="Register" method="post">
            <label for="fullName">Full Name:</label>
            <input type="text" id="fullName" name="fullName" placeholder="Enter your full name" required>

            <label for="email">Email:</label>
            <input type="text" id="email" name="email" placeholder="Enter your email" required>

            <label for="userPassword">Password:</label>
            <input type="password" id="userPassword" name="userPassword" placeholder="Enter password" required>

            <input type="submit" value="Register">
        </form>
        <%-- links redirect to login jsp if the user already has an account and 
             the resetPassword jsp if the user forgot thier password.
        --%>
        <div class="links">
            <a href="login.jsp">Already have an account? Login</a>
            <a href="resetPassword.jsp">Forgot password? Reset here</a>
        </div>

        <%
            String message = (String) request.getAttribute("message");
            if (message != null) {
        %>
            <div class="message"><%= message %></div>
        <%
            }
        %>
    </div>
</body>
</html>



