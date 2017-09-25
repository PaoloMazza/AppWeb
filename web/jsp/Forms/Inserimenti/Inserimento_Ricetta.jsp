<%--
  Created by IntelliJ IDEA.
  User: paolo
  Date: 15/09/17
  Time: 15.16
  To change this template use File | Settings | File Templates.
--%>
<!DOCTYPE html>
<html>
<head>
    <link href="${pageContext.request.contextPath}/style/css/style.css" rel="stylesheet" type="text/css">
    <link href="https://fonts.googleapis.com/css?family=Macondo|Nunito|Roboto|Shadows+Into+Light" rel="stylesheet">
    <meta charset="utf-8" />
    <title>Registrazione ricetta</title>
</head>

<body vlink="white">

<jsp:useBean id="vendita" scope="session" class="Beans.Vendita"/>
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
    Inserimento ricetta
</h1>


<form action="/InserimentoRicetta.do" method="post" style="width:800px; margin:0 auto;align-content: center;margin-top: 100px">
<h1 style="margin-left: 200px;">Inserimento ricetta</h1>
<div style="margin-left: 200px;">
    <div>
            <label for="CodiceRicetta">Codice ricetta</label> <input id="CodiceRicetta" name="CodiceRicetta" style="width: 180px;" type="text"> <br>
        <br>
        <div>
            <label for="CodiceMedico">Codice medico</label> <input id="CodiceMedico" name="CodiceMedico" style="width: 180px;" type="text"> </div>
        <br>
        <div>
            <label for="Paziente">Codice fiscale paziente </label> <input id="Paziente" name="Paziente" style="width: 180px;" type="text"> <br>
        </div>
        <br>
        <div>
            <label for="Data">Data di emissione ricetta</label> <input id="Data" name="Data" style="width: 180px;" type="text"> </div>
        <br>
    </div>
    <h4>Nel caso il paziente non esistesse nel database, aggiungerlo tramite la scelta seguente: </h4>
    <ul id="menu" style="display:inline; list-style-type: none;">
        <li><a class="active" href="/jsp/Forms/Inserimenti/Inserimento_paziente.jsp" style="color: black;">Inserire paziente</a></li>

    </ul>
    <br>
    <br>
    <div class="button" style ="margin-top: 10px; margin: auto"> <button type="submit">Controllare ricetta</button>
    </div>
</div>
</form>



<div id="fine" style="margin-top: 210pt">
    Applicazione web creata da: Paolo Mazza (20010255@studenti.uniupo.it)
</div>
</body>


</html>
