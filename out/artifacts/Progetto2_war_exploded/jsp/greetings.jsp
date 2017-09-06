<%--
  Created by IntelliJ IDEA.
  User: paolo
  Date: 25/08/17
  Time: 16.12
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Farmacia inserita</title>
</head>
<body>


    <% String message = null;
        try
        {
            message = (String) request.getSession().getAttribute("exitCode");
            if(message == null)
            {
                message = (String) request.getAttribute("exitCode");
                if (message == null)
                    message = "LoginAction non effettuata";
            }
        }
        catch(Exception e)
        {
            message = "LoginAction non effettuata";
        }
    %>
<h1><h1><%= message%></h1></h1>
</body>
</html>