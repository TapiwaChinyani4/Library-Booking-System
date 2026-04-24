<%-- 
    Document   : login.jsp
    Created on : 03 Nov 2025, 19:32:08
    Description: Allows users to log into the system by connecting to the Login servlet
    Author     : Tapiwa Cliford Chinyani
--%>

<%@ page session="true" %>
<%@ page import="java.sql.*, utils.DatabaseConnection" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Login - Library Management System</title>
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

        .login-container {
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

        input[type=text],
        input[type=password] {
            width: 100%;
            padding: 10px;
            margin: 8px 0;
            border: 1px solid #ccc;
            border-radius: 5px;
            box-sizing: border-box;
        }

        input[type=submit] {
            width: 100%;
            background-color: #4A90E2;
            color: white;
            border: none;
            padding: 10px;
            margin-top: 15px;
            border-radius: 5px;
            cursor: pointer;
            font-weight: bold;
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
            margin: 0 10px;
        }

        .links a:hover {
            text-decoration: underline;
        }

        .error {
            color: red;
            margin-top: 15px;
            font-size: 14px;
        }
    </style>
</head>
<body>
    <div class="login-container"> <%-- container for the login form --%>
        <h2>Library Login</h2>
        
        <%--login form sends data to login servlet with gets data from the database --%>
        <form action="login" method="post">
            <input type="text" name="email" placeholder="Email" required />
            <input type="password" name="userPassword" placeholder="Password" required />
            <input type="submit" value="Login" />
        </form>
        
        <%-- links redirect to resgister jsp if the user does not exits or the 
             the resetPassword jsp if the user forgot thier password.
        --%>
        <div class="links">
            <a href="register.jsp">Register</a> |
            <a href="resetPassword.jsp">Reset Password</a>
        </div>

        <%
            // Display any login error messages stored in session
            String errorMessage = (String) session.getAttribute("loginError");
            if (errorMessage != null) {
        %>
            <div class="error"><%= errorMessage %></div>
        <%
                session.removeAttribute("loginError");
            }
        %>
    </div>
</body>
</html>



