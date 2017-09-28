<%--
  Created by IntelliJ IDEA.
  User: paolo
  Date: 19/09/17
  Time: 10.50
  To change this template use File | Settings | File Templates.
--%>
<!DOCTYPE html>
<html>
<head>
    <link href="${pageContext.request.contextPath}/style/css/style2.css" rel="stylesheet" type="text/css">
    <link href="https://fonts.googleapis.com/css?family=Macondo|Nunito|Roboto|Shadows+Into+Light" rel="stylesheet">
    <meta charset="utf-8" />
    <title>Home</title>
</head>

<body vlink="white">

<div>
    <ul id="UL">
        <img src="/style/images/1503397977070.jpg" style="width: 4%; margin-left: 5px;padding: 1px;">
        <div id="form">
            </a>
                <form action="/logout.do" method="post" style="text-align: center">
                    <input class="button2" type="submit" value="Logout" id="invio" />
                </form>
        </div>
    </ul>
</div>

<h1 id="titolo">Account regione</h1>

<ul id="HomeList" style="margin:auto; display: table; margin-top: 50pt; list-style-type: none;" >
    <li><a class="active" href="/jsp/Forms/messages/MessagesPage_RG.jsp" style="color: black;">Messaggi</a></li>
    <li><a class="active" href="/jsp/Funtions/statistics/Statistics_RG.jsp"style="color: black;">Statistiche</a> </li>
    <li><a class="active" href="/jsp/Forms/Inserimenti/Inserimento_farmacia.jsp" style="color: black;">Registrazione nuova farmacia</a></li>
</ul>



<div id="fine" style="margin-top: 150pt">
    Applicazione web creata da: Paolo Mazza (20010255@studenti.uniupo.it)
</div>
</body>


</html>