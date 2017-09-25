<%@ page import="Beans.Login" %><%--
  Created by IntelliJ IDEA.
  User: paolo
  Date: 19/09/17
  Time: 16.18
  To change this template use File | Settings | File Templates.
--%>
<!DOCTYPE html>
<html>
<head>
    <link href="${pageContext.request.contextPath}/style/css/style.css" rel="stylesheet" type="text/css">
    <link href="https://fonts.googleapis.com/css?family=Macondo|Nunito|Roboto|Shadows+Into+Light" rel="stylesheet">
    <meta charset="utf-8" />
    <title>Chi sono</title>
</head>

<%String home = new String();%>
<%
    Login log = (Login) session.getAttribute("login");
    switch (log.getTipo()){
        case 1:
            home = "/jsp/Homes/HomeTitolare.jsp";
            break;
        case 2:
            home = "/jsp/Homes/HomeOB.jsp";
            break;
        case 3:
            home = "/jsp/Homes/HomeDF.jsp";
    }

%>


<body vlink="white">
<jsp:useBean id="login" scope="session" class="Beans.Login"/>
<div>
    <ul id="UL">
        <li><a class="active" href=<%=home%>>Home</a></li>
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
    Nuovo messaggio
</h1>

<div>
    <form action="/InviareMessaggio.do" method="post" style="margin: auto; display: block; margin-left: 400pt; margin-top: 100pt">
        destinatario (intervallare ogni destinatario con il simbolo ';'):<br>
        <input type="text" name="destinatari" style="border-radius: 10px; width: 30%;"><br>
        oggetto:<br>
        <input type="text" name="oggetto" style="border-radius: 10px; width: 30%;"> <br>

        messaggio<br><textarea name="messaggio" cols="30" rows="10" id="testo" class="txt"></textarea>
        <br><br>
        <input type="submit" value="Invia messaggio!" style = "margin-top: 15px">
    </form>
</div>

<ul id="menu" style="display:table; list-style-type: none; margin: auto; margin-top: 15pt" >
    <li><a class="button" href="/jsp/Forms/messages/ListaContatti.jsp" style="color: black;">Seleziona la lista di tutti i contatti</a></li>
</ul>

<div id="fine" style="margin-top: 140pt">
    Applicazione web creata da: Paolo Mazza (20010255@studenti.uniupo.it)
</div>
</body>


</html>