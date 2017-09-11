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
<jsp:useBean id="login" scope="session" class="Beans.Login"/>

<%
    String redirect = new String();
    if(login.getTipo() == 1)
        redirect = "4;/jsp/Homes/HomeTitolare.jsp";
    else if(login.getTipo() == 2)
        redirect= "4;/jsp/Homes/HomeDF.jsp";
    else if (login.getTipo() == 3)
        redirect= "4;/jsp/Homes/HomeOB.jsp";
%>

<meta http-equiv="refresh" content= "<%=redirect%>" />
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
<h1><h1 style="margin: auto; margin-top: 100px"><%= message%></h1></h1>
</body>
</html>