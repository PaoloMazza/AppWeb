<%--
  Created by IntelliJ IDEA.
  User: paolo
  Date: 06/09/17
  Time: 16.29
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

<form action="/inserimentoDipendente.do" method="post" style="width:800px; margin:0 auto;align-content: center;margin-top: 100px">
    <h1 style="margin-left: 200px;">Informazioni nuovo dipendente</h1>
    <div style="margin-left: 200px;">
        <div>
            <label for="CF">Codice fiscale dipendente</label>
            <input type="text" id="CF" name="CF" style="width: 180px;">
            <br>
            <br>
            <div>
                <label for="Nome">Nome </label>
                <input type="text" id="Nome" name="Nome" style="width: 180px;">
            </div>
            <br>
            <div>
                <label for="Cognome">Cognome </label>
                <input type="text" id="Cognome" name="Cognome" style="width: 180px;">
                <br>
            </div>
            <br>
            <div>
                <label for="Indirizzo">Indirizzo</label>
                <input type="text" id="Indirizzo" name="Indirizzo"style="width: 180px;">
            </div>
            <br>
            <div>
                <label for="Mail">Mail</label>
                <input type="text" id="Mail" name="Mail" style="width: 180px;">
            </div>
            <br>
            <div>
                <label for="Password">Password Titolare</label>
                <input type="text" id="Password" name="Password">
            </div>
        </div>
        <br><br>
        <div>
            <input type="radio" name="Tipo" value="OB" />OP
            <input type="radio" name="Tipo" value="DF"/>DF
        </div>
        <br><br><br>
        <div class="button">
            <button type="submit">Inserire nuovo dipendente</button>
        </div>
    </div>

</form>



<div id="fine" style="margin-top: 230pt">
    Applicazione web creata da: Paolo Mazza (20010255@studenti.uniupo.it)
</div>
</body>


</html>
