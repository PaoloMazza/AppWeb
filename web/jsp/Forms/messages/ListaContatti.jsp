<%@ page import="Utilities.Database" %><%--
  Created by IntelliJ IDEA.
  User: paolo
  Date: 19/09/17
  Time: 18.22
  To change this template use File | Settings | File Templates.
--%>
<!DOCTYPE html>
<html>
<head>
    <link href="${pageContext.request.contextPath}/style/css/style.css" rel="stylesheet" type="text/css">
    <link href="https://fonts.googleapis.com/css?family=Macondo|Nunito|Roboto|Shadows+Into+Light" rel="stylesheet">
    <meta charset="utf-8" />
    <title>Warehouse</title>
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

<% int caso = login.getTipo();%>

<h1 id="titolo">
    I miei messaggi
</h1>



<div style="overflow-x:auto;">
    <table id="lm">
        <thead>
        <tr>
            <th>Nome</th>
            <th>Cognome</th>
            <th>Id farmacia</th>
            <th>Codice fiscale</th>
        </tr>
        </thead>
        <tbody>
        <% Database listamagazzino = Database.getInstance();%>
        <%=listamagazzino.fillTableContacts(login.getCodiceFiscale(),caso)%>
        </tbody>
    </table>
    <br><br>
</div>
<br>

<%
    String href;
if(login.getTipo()==0)
    href = "/jsp/Forms/messages/newMessage_RG.jsp";
else
    href = "/jsp/Forms/messages/newMessage.jsp";

%>
<ul id="menu" style="display:inline; list-style-type: none; margin: auto; display: block">
    <li><a class="active" href="<%=href%>" style="color: black;">Ritornare ai messaggi</a></li>
</ul>

<div id="fine" style="margin-top: 210pt">
    Applicazione web creata da: Paolo Mazza (20010255@studenti.uniupo.it)
</div>
</body>


</html>
