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
    <title>Chi sono</title>
</head>

<body vlink="white">
<jsp:useBean id="login" scope="session" class="Beans.Login"/>

<div>
    <ul id="UL">
        <img src="immagini/1503397977070.jpg" style="width: 4%; margin-left: 5px;padding: 1px;">
        <div id="form">
            <li><a><%= login.getNome() + " " + login.getCognome()%>
            </a>
        </div>
    </ul>
</div>


<ul id="HomeList" style="margin:auto; display: table; margin-top: 100pt; list-style-type: none;" >
    <li><a class="active" href="sito.html" style="color: black;">Home</a></li>
    <li><a class="active" href="comeAccedere.html" style="color: black;">Vendite</a></li>
    <li><a class="active" href="/jsp/Forms/InserimentoDipendente.jsp" style="color: black;">Registrazione Personale</a></li>
    <br>
    <li><a class="active" href="" style="color: black;">Acquisto prodotti</a></li>
    <li><a class="active" href="" style="color: black;">Magazzino</a></li>
</ul>



<div id="fine" style="margin-top: 300pt">
    Applicazione web creata da: Paolo Mazza (20010255@studenti.uniupo.it)
</div>
</body>


</html>