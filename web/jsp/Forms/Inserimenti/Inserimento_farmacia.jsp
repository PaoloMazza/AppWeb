<%--
  Created by IntelliJ IDEA.
  User: paolo
  Date: 05/09/17
  Time: 10.35
  To change this template use File | Settings | File Templates.
--%>
<!DOCTYPE html>
<html>
<head>
  <link href="/style/css/style.css" rel="stylesheet" type="text/css">
  <link href="https://fonts.googleapis.com/css?family=Macondo|Nunito|Roboto|Shadows+Into+Light" rel="stylesheet">
  <meta charset="utf-8" />
  <title>Chi sono</title>
</head>

<body vlink="white">
<jsp:useBean id="InserimentoDipendente" scope="session" class="Beans.Farmacia"/>
<div>
  <ul id="UL">
    <img src="/style/images/1503397977070.jpg" style="width: 4%; margin-left: 5px;padding: 1px;">
    <div id="form">
      <li><a class="active" href="Login.html">Login
      </a>
    </div>
  </ul>
</div>

<h1 id="titolo">
    Inserimento nuovo utente
</h1>

<body vlink="white">
<jsp:useBean id="CreaFarmacia" scope="session" class="Beans.Farmacia"/>

<form action="/enter.do" method="post" style="width:800px; margin:0 auto;align-content: center;margin-top: 50px">
  <h1 style="margin-left: 200px">Generalità Farmacia</h1>
  <div style="width: 100px; margin-left: 200px" >
    <label for="NomeFarmacia">Nome Farmacia</label>
    <input type="text" id="NomeFarmacia" name="NomeFarmacia">
    <div>
      <br>
      <label for="Indirizzo">Indirizzo</label>
      <input type="text" id="Indirizzo" name="Indirizzo">
    </div>
    <br>
    <label for="Telefono">Telefono</label>
    <input type="text" id="Telefono" name="Telefono">
  </div>
  <br>
  <h1 style="margin-left: 200px;">Informazioni titolare farmacia</h1>
  <div style="margin-left: 200px;">
    <div>
      <label for="CFtitolare">Codice fiscale titolare</label>
      <input type="text" id="CFtitolare" name="CFtitolare">
      <br><br>
      <div>
        <label for="NomeTitolare">Nome Titolare</label>
        <input type="text" id="NomeTitolare" name="NomeTitolare">
        <label for="CognomeTitolare">Cognome Titolare</label>
        <input type="text" id="CognomeTitolare" name="CognomeTitolare">
        <br>
      </div>
      <br>
      <div>
        <label for="IndirizzoTitolare">Indirizzo titolare</label>
        <input type="text" id="IndirizzoTitolare" name="IndirizzoTitolare">
      </div>
      <br>
      <div>
        <label for="MailTitolare">Mail titolare</label>
        <input type="text" id="MailTitolare" name="MailTitolare">
      </div>
      <br>
      <div>
        <label for="PasswordTitolare">Password Titolare</label>
        <input type="text" id="PasswordTitolare" name="PasswordTitolare">
      </div>
    </div>
    <br><br><br>
    <div class="button">
      <input type="submit" value="REGISTRA"/>
    </div>
  </div>
</form>

<div id="fine" style="margin-top: 120pt">
  Applicazione web creata da: Paolo Mazza (20010255@studenti.uniupo.it)
</div>

</body>
</html>
