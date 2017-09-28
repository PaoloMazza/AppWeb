<%@ page import="Beans.Login" %>
<%@ page import="Beans.Messaggi" %><%--
  Created by IntelliJ IDEA.
  User: paolo
  Date: 19/09/17
  Time: 19.00
  To change this template use File | Settings | File Templates.
--%>
<jsp:useBean id="login" scope="session" class="Beans.Login"/>
<jsp:useBean id="messaggio" scope="session" class="Beans.Messaggi"/>
<%
    String css = new String();
    if(login.getTipo()== 0)
        css = "/style/css/style2.css";
    else
        css = "/style/css/style.css";


%>

<!DOCTYPE html>
<html>
<head>
    <link href="${pageContext.request.contextPath}<%=css%>" rel="stylesheet" type="text/css">
    <link href="https://fonts.googleapis.com/css?family=Macondo|Nunito|Roboto|Shadows+Into+Light" rel="stylesheet">
    <meta charset="utf-8" />
    <title>Messages</title>
</head>





<body vlink="white">

<%
    String home = new String();
    switch (login.getTipo()){
        case 0:
            home = "/jsp/Homes/HomeRegione.jsp";
            break;
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

<div>
    <ul id="UL">
        <li><a class="active" href=<%=home%>>Home</a></li>
        <img src="/style/images/1503397977070.jpg" style="width: 4%; margin-left: 5px;padding: 1px;">
        <div id="form">
            <li><a><%= login.getNome() + " " + login.getCognome()%>
            </a>
        </div>
    </ul>
</div>


<div style="margin: auto; display: block; margin-left: 400pt; margin-top: 100pt">
    mittente: <br>
    <textarea name="destinatari" style="border-radius: 10px; width: 30%;" readonly><%=messaggio.getMittente()%></textarea><br>
    oggetto:<br>
    <textarea name="oggetto" style="border-radius: 10px; width: 30%;" readonly><%=messaggio.getOggetto()%></textarea><br>

    messaggio<br><textarea name="messaggio" cols="30" rows="10" id="testo" class="txt" readonly><%=messaggio.getMessaggio()%></textarea>
    <br><br>

</div>




<div id="fine" style="margin-top: 360pt">
    Applicazione web creata da: Paolo Mazza (20010255@studenti.uniupo.it)
</div>
</body>


</html>
