<%@ page import="Utilities.Database" %><%--
  Created by IntelliJ IDEA.
  User: paolo
  Date: 13/09/17
  Time: 18.24
  To change this template use File | Settings | File Templates.
--%>
<!DOCTYPE html>
<html>
<head>
    <link href="${pageContext.request.contextPath}/style/css/style.css" rel="stylesheet" type="text/css">
    <link href="https://fonts.googleapis.com/css?family=Macondo|Nunito|Roboto|Shadows+Into+Light" rel="stylesheet">
    <meta charset="utf-8" />
    <title>Confirm-sales</title>
</head>

<body vlink="white">
<jsp:useBean id="login" scope="session" class="Beans.Login"/>
<jsp:useBean id="vendita" scope="session" class="Beans.Vendita"/>
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


<h1 id="titolo">
    Vuoi confermare la seguente fattura?
</h1>


<form action="/AggiuntaFattura.do" method="post" style="width:800px; margin:0 auto;align-content: center;margin-top: 100px">
    <div style="overflow-x:auto;">
        <table id="lm">
            <thead>
            <tr>
                <th>Codice Prodottto</th>
                <th>Nome prodotto</th>
                <th>Prezzo</th>
                <th>Quantit&#224;</th>
            </tr>
            </thead>
            <tbody>
            <% Database listamagazzino = Database.getInstance();%>
            <%=listamagazzino.Invoice(vendita.getProdotti())%>
            </tbody>
        </table>
        <br><br>
        <button class="button" type="Ordina">Ordina</button>
    </div>
</form>


<div id="fine" style="margin-top: 210pt">
    Applicazione web creata da: Paolo Mazza (20010255@studenti.uniupo.it)
</div>
</body>


</html>
