<%@ page import="Utilities.Database" %>
<%@ page import="Beans.Login" %><%--
  Created by IntelliJ IDEA.
  User: paolo
  Date: 20/09/17
  Time: 13.19
  To change this template use File | Settings | File Templates.
--%>
<!DOCTYPE html>
<html>
<head>
    <link href="${pageContext.request.contextPath}/style/css/style2.css" rel="stylesheet" type="text/css">
    <link href="https://fonts.googleapis.com/css?family=Macondo|Nunito|Roboto|Shadows+Into+Light" rel="stylesheet">
    <meta charset="utf-8" />
    <title>Messages</title>
</head>

<body vlink="white">
<jsp:useBean id="login" scope="session" class="Beans.Login"/>
<div>
    <ul id="UL">
        <li><a class="active" href="/jsp/Homes/HomeTitolare.jsp">Home</a></li>
        <img src="/style/images/1503397977070.jpg" style="width: 4%; margin-left: 5px;padding: 1px;">
        <div id="form">
            <li><a><%= login.getNome() + " " + login.getCognome()%>
            </a>
                <form action="/logout.do" method="post" style="text-align: center">
                    <input class="button2" type="submit" value="Logout" id="invio" />
                </form>
        </div>
    </ul>
</div>


<h1 id="titolo">
    I miei messaggi
</h1>



<div style="overflow-x:auto;">
    <table id="lm">
        <thead>
        <tr>
            <th>Numero messaggio</th>
            <th>Mittente</th>
            <th>Oggetto</th>
            <th>Data</th>
            <th>Azione</th>
        </tr>
        </thead>
        <tbody>
        <% Database listamagazzino = Database.getInstance();%>
        <%=listamagazzino.fillTableMessages(login.getMail())%>
        </tbody>
    </table>
    <br><br>
</div>
<br>
<ul id="menu" style="display:inline; list-style-type: none; margin: auto">
    <li><a class="active" href="/jsp/Forms/messages/newMessage_RG.jsp" style="color: black;">Inviare nuovo messaggio</a></li>
</ul>

<div id="fine" style="margin-top: 210pt">
    Applicazione web creata da: Paolo Mazza (20010255@studenti.uniupo.it)
</div>
</body>


</html>
