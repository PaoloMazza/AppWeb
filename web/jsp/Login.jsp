<%--
  Created by IntelliJ IDEA.
  User: paolo
  Date: 06/09/17
  Time: 9.53
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

<div>
    <ul>
        <li><a class="active" href="sito.html">Home</a></li>
        <li><a class="active" href="comeAccedere.html">Come accedere</a>
        <li><a class="active" href="sito.html">Informazioni</a></li>

        <div id="form">
            <li><a class="active" href="Login.html">Login
            </a>
        </div>
    </ul>
</div>

<h1 id="titolo">
    Effettuare il login.
</h1>

<div>
    <jsp:useBean id="Login" scope="session" class="Beans.Login"/>
    <form action="/Login.do" style="margin: auto; display: block; margin-left: 400pt; margin-top: 100pt">
        First name:<br>
        <input type="text" name="CFTitolare" style="border-radius: 10px; width: 30%;"><br>
        Last name:<br>
        <input type="text" name="Password" style="border-radius: 10px; width: 30%;"><br>
        <input type="submit" value="LOGIN" style = "margin-top: 15px">
    </form>
</div>

<div id="fine" style="margin-top: 138pt">
    Applicazione web creata da: Paolo Mazza (20010255@studenti.uniupo.it)
</div>
</body>


</html>
