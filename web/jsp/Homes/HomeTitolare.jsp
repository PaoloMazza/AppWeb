<%--
  Created by IntelliJ IDEA.
  User: paolo
  Date: 06/09/17
  Time: 10.01
  To change this template use File | Settings | File Templates.
--%>
<!DOCTYPE html>
<html>
<head>
    <link href="${pageContext.request.contextPath}/style/css/style.css" rel="stylesheet" type="text/css">
    <link href="https://fonts.googleapis.com/css?family=Macondo|Nunito|Roboto|Shadows+Into+Light" rel="stylesheet">
    <meta charset="utf-8" />
    <title>Home</title>
</head>

<body vlink="white">
<jsp:useBean id="login" scope="session" class="Beans.Login"/>

<div>
    <ul id="UL">
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


<ul id="HomeList" style="margin:auto; display: table; margin-top: 100pt; list-style-type: none;" >
    <li><a class="active" href="/jsp/Forms/messages/MessagesPage.jsp" style="color: black;">Messaggistica</a></li>
    <li><a class="active" href="/jsp/Funtions/Sales.jsp" style="color: black;">Vendita Prodotti</a></li>
    <li><a class="active" href="/jsp/Forms/Inserimenti/InserimentoDipendente.jsp" style="color: black;">Registrazione Personale</a></li>
    <br>
    <li><a class="active" href="/jsp/Funtions/statistics/Statistics_TF.jsp" style="color: black;">Statistiche</a></li>
    <li><a class="active" href="/jsp/Funtions/Warehouse.jsp" style="color: black;">Gestione magazzino</a></li>
    <br>
</ul>



<div id="fine" style="margin-top: 100pt">
    Applicazione web creata da: Paolo Mazza (20010255@studenti.uniupo.it)
</div>
</body>


</html>
