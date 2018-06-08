<%--
  Created by IntelliJ IDEA.
  User: amarini
  Date: 31/05/2018
  Time: 17:29
--%>

<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <head>
        <link rel="stylesheet" href="../css/tether.min.css">
        <link rel="stylesheet" href="../css/bootstrap.min.css">
        <link rel="stylesheet" href="../icomoon.css">
        <link rel="stylesheet" href="../css/style.css">
        <title>Results</title>
    </head>
</head>

<body>
    <nav class="navbar navbar-expand-lg navbar-dark bg-dark">
        <div class="contenedor">
            <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarTogglerDemo01" aria-controls="navbarTogglerDemo01" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse" id="navbarTogglerDemo01">
                <a class="navbar-brand" href="#">AHP TOOL</a>
                <ul class="navbar-nav mr-auto mt-2 mt-lg-0">
                    <li class="nav-item active">
                        <a class="nav-link" href="#">Home <span class="sr-only">(current)</span></a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="#">Link</a>
                    </li>
                </ul>
            </div>
        </div>
    </nav>

    <footer>
        <div class="contenedor mt-5 mb-5">
            <h1 class="display-4 mb-4" id="footer-title">Step 3: See the Results and Take Your Own Conclusions! </h1>
            <div class="progress">
                <div class="progress-bar progress-bar-striped" id="progress_bar" role="progressbar" style="width: 100%" aria-valuenow="40" aria-valuemin="0" aria-valuemax="100"></div>
            </div>
        </div>
    </footer>
    <div id="results" class="results mb-5">
        <div class="contenedor">
            <h1 class="display-4">The results are:</h1>
            <hr>
            <table class="table">
                <thead>
                <tr>
                    <th>#</th>
                    <th>Alternative Name</th>
                    <th>Score</th>
                </tr>
                <% int cont = 1 %>
                <g:each var="resultObject" in="${resultsVector}">
                    <g:if test="${cont==1}">
                        <tr class="table-success">
                            <td><span class="icon-trophy winner"></span></td>
                            <td><b>${resultObject.key}</td>
                            <td><b>${resultObject.value}</b></td>
                        </tr>
                    </g:if>
                    <g:elseif test="${cont==resultsVector.size()}">
                        <tr class="table-danger">
                            <td>${cont}</td>
                            <td>${resultObject.key}</td>
                            <td>${resultObject.value}</td>
                        </tr>
                    </g:elseif>
                    <g:else>
                        <tr>
                            <td>${cont}</td>
                            <td>${resultObject.key}</td>
                            <td>${resultObject.value}</td>
                        </tr>
                    </g:else>
                    <% cont=cont+1 %>
                </g:each>
                </thead>
                <tbody id="results-rows">
                </tbody>
            </table>
        </div>
    </div>
</body>
</html>