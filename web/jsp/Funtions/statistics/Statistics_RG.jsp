<%--
  Created by IntelliJ IDEA.
  User: paolo
  Date: 24/09/17
  Time: 15.22
  To change this template use File | Settings | File Templates.
--%>
<%@ page import="Utilities.Database" %>
<%@ page import="java.util.Arrays" %><%--
  Created by IntelliJ IDEA.
  User: paolo
  Date: 23/09/17
  Time: 10.05
  To change this template use File | Settings | File Templates.
--%>
<!DOCTYPE html>
<html>
<head>
    <link href="${pageContext.request.contextPath}/style/css/style.css" rel="stylesheet" type="text/css">
    <link href="https://fonts.googleapis.com/css?family=Macondo|Nunito|Roboto|Shadows+Into+Light" rel="stylesheet">
    <meta charset="utf-8" />
    <script src="/style/js/Chart.js" type="text/javascript"></script>
    <title>Statistiche</title>
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

<%Database database = Database.getInstance();%>

<h1 id="titolo" style="margin: auto; display: table; margin-top: 30pt">
    Vendite e acquisti
</h1>

<div id="grafico1" style="margin-top: 40pt">
    <h1>Numero acquisti</h1>
    <canvas id="myChart" style="background: beige; width:300px !important;
  height:300px !important;" >
    </canvas>
    <script>
        var ctx = document.getElementById("myChart").getContext('2d');
        ctx.canvas.width =300;
        ctx.canvas.height = 300;
        var myChart = new Chart(ctx, {
            type: 'bar',
            data: {
                labels: <%=Arrays.toString(database.getPurchaseStatisticsRG("1","12","2017",1).keySet().toArray())%>,
                datasets: [{
                    label: '# di acquisti effettuati',
                    data: <%=Arrays.toString(database.getPurchaseStatisticsRG("1","12","2017",1).values().toArray())%>,
                    backgroundColor: [
                        'rgba(255, 99, 132, 0.2)',
                        'rgba(54, 162, 235, 0.2)',
                        'rgba(255, 206, 86, 0.2)',
                        'rgba(255, 99, 132, 0.2)',
                        'rgba(54, 162, 235, 0.2)',
                        'rgba(255, 206, 86, 0.2)',
                        'rgba(255, 99, 132, 0.2)',
                        'rgba(54, 162, 235, 0.2)',
                        'rgba(255, 206, 86, 0.2)',
                        'rgba(255, 99, 132, 0.2)',
                        'rgba(54, 162, 235, 0.2)',
                        'rgba(255, 206, 86, 0.2)'
                    ],
                    borderColor: [
                        'rgba(255,99,132,1)',
                        'rgba(54, 162, 235, 1)',
                        'rgba(255, 206, 86, 1)',
                        'rgba(75, 192, 192, 1)',
                        'rgba(153, 102, 255, 1)',
                        'rgba(255, 159, 64, 1)',
                        'rgba(255,99,132,1)',
                        'rgba(54, 162, 235, 1)',
                        'rgba(255, 206, 86, 1)',
                        'rgba(75, 192, 192, 1)',
                        'rgba(153, 102, 255, 1)',
                        'rgba(255, 159, 64, 1)'
                    ],
                    borderWidth: 1
                }]
            },
            options: {
                scales: {
                    yAxes: [{
                        ticks: {
                            beginAtZero:true
                        }
                    }]
                },
                responsive: false
            }
        });

    </script>
</div>

<div id="grafico2" style="margin-top: 40pt">
    <h1>Numero vendite</h1>
    <canvas id="myChart2" style="background: beige; width:300px !important;
  height:300px !important;" >
    </canvas>
    <script>
        var ctx = document.getElementById("myChart2").getContext('2d');
        ctx.canvas.width =300;
        ctx.canvas.height = 300;
        var myChart = new Chart(ctx, {
            type: 'bar',
            data: {
                labels: <%=Arrays.toString(database.getPurchaseStatisticsRG("1","12","2017",0).keySet().toArray())%>,
                datasets: [{
                    label: '# di fatture emesse',
                    data: <%=Arrays.toString(database.getPurchaseStatisticsRG("1","12","2017",0).values().toArray())%>,
                    backgroundColor: [
                        'rgba(255, 99, 132, 0.2)',
                        'rgba(54, 162, 235, 0.2)',
                        'rgba(255, 206, 86, 0.2)',
                        'rgba(255, 99, 132, 0.2)',
                        'rgba(54, 162, 235, 0.2)',
                        'rgba(255, 206, 86, 0.2)',
                        'rgba(255, 99, 132, 0.2)',
                        'rgba(54, 162, 235, 0.2)',
                        'rgba(255, 206, 86, 0.2)',
                        'rgba(255, 99, 132, 0.2)',
                        'rgba(54, 162, 235, 0.2)',
                        'rgba(255, 206, 86, 0.2)'
                    ],
                    borderColor: [
                        'rgba(255,99,132,1)',
                        'rgba(54, 162, 235, 1)',
                        'rgba(255, 206, 86, 1)',
                        'rgba(75, 192, 192, 1)',
                        'rgba(153, 102, 255, 1)',
                        'rgba(255, 159, 64, 1)',
                        'rgba(255,99,132,1)',
                        'rgba(54, 162, 235, 1)',
                        'rgba(255, 206, 86, 1)',
                        'rgba(75, 192, 192, 1)',
                        'rgba(153, 102, 255, 1)',
                        'rgba(255, 159, 64, 1)'
                    ],
                    borderWidth: 1
                }]
            },
            options: {
                scales: {
                    yAxes: [{
                        ticks: {
                            beginAtZero:true
                        }
                    }]
                },
                responsive: false
            }
        });

    </script>
</div>

<h1 id="titolo" style="margin: auto; display: table; margin-top: 350pt" >
    Ricavi e guadagni
</h1>

<div id="grafico3">
    <h1>Numero </h1>
    <canvas id="myChart3" style="background: beige; width:300px !important;
  height:300px !important;" >
    </canvas>
    <script>
        var ctx = document.getElementById("myChart3").getContext('2d');
        ctx.canvas.width =300;
        ctx.canvas.height = 300;
        var myChart = new Chart(ctx, {
            type: 'bar',
            data: {
                labels: <%=Arrays.toString(database.ricaviEVenditeRG("1","12","2017",1).keySet().toArray())%>,
                datasets: [{
                    label: 'euro spesi per mese in acquisti',
                    data: <%=Arrays.toString(database.ricaviEVenditeRG("1","12","2017",1).values().toArray())%>,
                    backgroundColor: [
                        'rgba(255, 99, 132, 0.2)',
                        'rgba(54, 162, 235, 0.2)',
                        'rgba(255, 206, 86, 0.2)',
                        'rgba(255, 99, 132, 0.2)',
                        'rgba(54, 162, 235, 0.2)',
                        'rgba(255, 206, 86, 0.2)',
                        'rgba(255, 99, 132, 0.2)',
                        'rgba(54, 162, 235, 0.2)',
                        'rgba(255, 206, 86, 0.2)',
                        'rgba(255, 99, 132, 0.2)',
                        'rgba(54, 162, 235, 0.2)',
                        'rgba(255, 206, 86, 0.2)'
                    ],
                    borderColor: [
                        'rgba(255,99,132,1)',
                        'rgba(54, 162, 235, 1)',
                        'rgba(255, 206, 86, 1)',
                        'rgba(75, 192, 192, 1)',
                        'rgba(153, 102, 255, 1)',
                        'rgba(255, 159, 64, 1)',
                        'rgba(255,99,132,1)',
                        'rgba(54, 162, 235, 1)',
                        'rgba(255, 206, 86, 1)',
                        'rgba(75, 192, 192, 1)',
                        'rgba(153, 102, 255, 1)',
                        'rgba(255, 159, 64, 1)'
                    ],
                    borderWidth: 1
                }]
            },
            options: {
                scales: {
                    yAxes: [{
                        ticks: {
                            beginAtZero:true
                        }
                    }]
                },
                responsive: false
            }
        });

    </script>
</div>

<div id="grafico4" style="margin-top: 20pt;">
    <h1>Numero vendite</h1>
    <canvas id="myChart4" style="background: beige; width:300px !important;
  height:300px !important;" >
    </canvas>
    <script>
        var ctx = document.getElementById("myChart4").getContext('2d');
        ctx.canvas.width =300;
        ctx.canvas.height = 300;
        var myChart = new Chart(ctx, {
            type: 'bar',
            data: {
                labels: <%=Arrays.toString(database.ricaviEVenditeRG("1","12","2017",0).keySet().toArray())%>,
                datasets: [{
                    label: 'ricavi per ogni mese',
                    data: <%=Arrays.toString(database.ricaviEVenditeRG("1","12","2017",0).values().toArray())%>,
                    backgroundColor: [
                        'rgba(255, 99, 132, 0.2)',
                        'rgba(54, 162, 235, 0.2)',
                        'rgba(255, 206, 86, 0.2)',
                        'rgba(255, 99, 132, 0.2)',
                        'rgba(54, 162, 235, 0.2)',
                        'rgba(255, 206, 86, 0.2)',
                        'rgba(255, 99, 132, 0.2)',
                        'rgba(54, 162, 235, 0.2)',
                        'rgba(255, 206, 86, 0.2)',
                        'rgba(255, 99, 132, 0.2)',
                        'rgba(54, 162, 235, 0.2)',
                        'rgba(255, 206, 86, 0.2)'
                    ],
                    borderColor: [
                        'rgba(255,99,132,1)',
                        'rgba(54, 162, 235, 1)',
                        'rgba(255, 206, 86, 1)',
                        'rgba(75, 192, 192, 1)',
                        'rgba(153, 102, 255, 1)',
                        'rgba(255, 159, 64, 1)',
                        'rgba(255,99,132,1)',
                        'rgba(54, 162, 235, 1)',
                        'rgba(255, 206, 86, 1)',
                        'rgba(75, 192, 192, 1)',
                        'rgba(153, 102, 255, 1)',
                        'rgba(255, 159, 64, 1)'
                    ],
                    borderWidth: 1
                }]
            },
            options: {
                scales: {
                    yAxes: [{
                        ticks: {
                            beginAtZero:true
                        }
                    }]
                },
                responsive: false
            }
        });

    </script>
</div>

<p style="margin: auto; display: table;margin-top: 400pt;">Statistiche generali</p>

<table id = "lm" style="margin-top: 20pt">
    <thead>
    <tr>
        <th>Dipendente che ha venduto di piu' durante questo mese</th>
        <th>Percentuale di acquisti con ricetta (sul totale delle vendite)</th>
    </tr>
    </thead>
    <tbody>
    <tr><td><%=database.getMostSellerRG()%></td><td><%=database.ReceiptProductSoldRG()%></td></tr>
    </tbody>
</table>


<div id="fine" style="margin-top: 50pt">
    Applicazione web creata da: Paolo Mazza (20010255@studenti.uniupo.it)
</div>

</body>
</html>


