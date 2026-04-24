<%-- 
    Document   : resetPassword
    Created on : 04 Nov 2025, 00:26:45
    Description: Allows users to reset thier password if they have forgotten thier login details
    Author     : chiny
--%>

<%@ page session="true" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Reset Password - Library Management System</title>
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

        .reset-container {
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
        }

        .links a:hover {
            text-decoration: underline;
        }

        .message {
            color: green;
            margin-top: 15px;
            font-size: 14px;
        }

        .error {
            color: red;
            margin-top: 15px;
            font-size: 14px;
        }
    </style>
</head>
<body>
    <div class="reset-container">
        <h2>Reset Your Password</h2>
        
        <%-- ResetPassword form sends data to ResetPassword servlet --%>
        <form action="ResetPassword" method="post">
            <input type="text" name="email" placeholder="Email" required />
            <input type="password" name="newPassword" placeholder="New Password" required />
            <input type="submit" value="Reset Password" />
        </form>

        <div class="links">
            <a href="login.jsp">Back to Login</a>
        </div>
        
        <%--Display any any error messages that come from the connected servlet --%>
        <%
            String msg = (String) session.getAttribute("msg");
            String err = (String) session.getAttribute("error");

            if (msg != null) {
        %>
            <div class="message"><%= msg %></div>
        <%
                session.removeAttribute("msg");
            }
            if (err != null) {
        %>
            <div class="error"><%= err %></div>
        <%
                session.removeAttribute("error");
            }
        %>
    </div>
</body>
</html>


