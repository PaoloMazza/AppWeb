<%@ page import="Utilities.Database" %><%--
  Created by IntelliJ IDEA.
  User: paolo
  Date: 07/09/17
  Time: 16.21
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
        </div>
    </ul>
</div>


<h1 id="titolo">
   Gestione magazzino
</h1>


<form action="/AggiuntaOrdine.do" method="post" style="width:800px; margin:0 auto;align-content: center;margin-top: 100px">
<div style="overflow-x:auto;">
    <table id="lm">
            <thead>
                <tr>
                    <th>Codice Prodottto</th>
                    <th>Nome prodotto</th>
                    <th>Prezzo</th>
                    <th>Quantit&#224;</th>
                    <th>Inserire unit&#224; da aggiungere al magazzino</th>
                </tr>
            </thead>
            <tbody>
                <% Database listamagazzino = Database.getInstance();%>
                <%=listamagazzino.fillWarehouseTable(login.getIdFarmacia())%>
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

