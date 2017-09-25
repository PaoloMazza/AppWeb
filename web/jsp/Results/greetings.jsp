<%--
  Created by IntelliJ IDEA.
  User: paolo
  Date: 25/08/17
  Time: 16.12
  To change this template use File | Settings | File Templates.
--%>
<!DOCTYPE html>
<html>
<head>
    <link href="${pageContext.request.contextPath}/style/css/style.css" rel="stylesheet" type="text/css">
    <link href="https://fonts.googleapis.com/css?family=Macondo|Nunito|Roboto|Shadows+Into+Light" rel="stylesheet">
    <meta charset="utf-8" />
    <title>result</title>
</head>
<jsp:useBean id="login" scope="session" class="Beans.Login"/>

<%
    String redirect = new String();
    if(login.getTipo() == 1)
        redirect = "4;/jsp/Homes/HomeTitolare.jsp";
    else if(login.getTipo() == 2)
        redirect= "4;/jsp/Homes/HomeDF.jsp";
    else if (login.getTipo() == 3)
        redirect= "4;/jsp/Homes/HomeOB.jsp";
    else if(login.getTipo() == 0)
        redirect= "4;/jsp/Homes/HomeRegione.jsp";
%>
<body vlink="white">
<div>
    <ul id="UL">
        <li><a class="active" href="/jsp/Homes/HomeTitolare.jsp">Home</a></li>
        <img src="immagini/1503397977070.jpg" style="width: 4%; margin-left: 5px;padding: 1px;">
        <div id="form">
            <li><a><%= login.getNome() + " " + login.getCognome()%>
            </a>
                <form action="/logout.do" method="post" style="text-align: center">
                    <input class="button2" type="submit" value="Logout" id="invio" />
                </form>
        </div>
    </ul>
</div>


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

<div id="fine" style="margin-top: 210pt">
    Applicazione web creata da: Paolo Mazza (20010255@studenti.uniupo.it)
</div>
</body>
</html>