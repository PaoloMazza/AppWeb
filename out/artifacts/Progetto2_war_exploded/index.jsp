<%--
  Created by IntelliJ IDEA.
  User: paolo
  Date: 05/09/17
  Time: 10.35
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8" />
  <title>Inserimento Farmacia</title>
</head>

<body vlink="white">
<jsp:useBean id="CreaFarmacia" scope="session" class="Beans.Farmacia"/>
<form action="/enter.do" method="post" style="width:800px; margin:0 auto;align-content: center;margin-top: 100px">
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
</body>
</html>
