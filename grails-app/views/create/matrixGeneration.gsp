<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <link rel="stylesheet" href="../css/tether.min.css">
    <link rel="stylesheet" href="../css/bootstrap.min.css">
    <link rel="stylesheet" href="../icomoon.css">
    <link rel="stylesheet" href="../css/style.css">
    <title>Generación de Matrices y Ponderación</title>
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
            <h1 class="display-4 mb-4" id="footer-title">Step 2: Do the Pairwaise Matrix</h1>
            <div class="progress">
                <div class="progress-bar progress-bar-striped" id="progress_bar" role="progressbar" style="width: 66.66%" aria-valuenow="40" aria-valuemin="0" aria-valuemax="100"></div>
            </div>
        </div>
    </footer>
    <g:form name="matrixForm" id="form-ahp" url="[action:'doTheMath',controller:'results']">
        <div class="contenedor">
            <div id="pairwaise" class="pairwaise mt-3">
                <div class="matrix-container">
                    <h1 class="mb-5">Pairwaise Comparation Matrix <small>Criteria vs Criteria</small></h1>
                    <g:each var="matrixObject" in="${pairwaiseMatrixList.CRITERIA}">

                        <div class="table-container">
                            <h2 style="text-align: center;">Level Above: <badge class="badge badge-success">${matrixObject.getCompareTo()}</badge></h2>
                            <table class="table-pairwaise">
                                <g:each var="i" in="${0..<matrixObject.getColumn()}">
                                    <g:if test="${i==0}">
                                        <tr class="first-row">
                                            <th>#</th>
                                            <g:each var="criteria" in="${matrixObject.getItems()}">
                                                <th>${criteria}</th>
                                            </g:each>
                                        </tr>
                                    </g:if>
                                    <tr>
                                        <g:each var="j" in="${0..<matrixObject.getRow()}">
                                            <g:if test="${j==0}">
                                                <th class="first-column">
                                                    ${matrixObject.getItems().get(i)}
                                                </th>
                                            </g:if>
                                            <g:if test="${i==j}">
                                                <td><input value="${matrixObject.getMatrix().get(i,j)}" name="cvc-${matrixObject.getCompareTo()}-${matrixObject.getItems().toString()}" step="0.1" min="1" max="1"></td>
                                            </g:if>
                                            <g:else>
                                                <td><input onkeyup="makeSomeMagic(${i+1},${j+1},'${"cc"+matrixObject.getCompareTo()}')" name="cvc-${matrixObject.getCompareTo()}-${matrixObject.getItems().toString()}" id="${"cc"+matrixObject.getCompareTo()}${(i+1)}${(j+1)}" value="${matrixObject.getMatrix().get(i,j)}" step="0.1" min="0" max="9"></td>
                                            </g:else>
                                        </g:each>
                                    </tr>
                                </g:each>
                            </table>
                        </div>

                    </g:each>
                </div>
            </div>

            <div id="pairwaise-a-a" class="pairwaise mt-5">
                <div class="matrix-container-a-a">
                    <h1 class="mb-5">Pairwaise Comparation Matrix <small>Alternative vs Alternative</small></h1>
                    <g:each var="matrixObject" in="${pairwaiseMatrixList.ALTERNATIVE}">


                        <div class="table-container">
                            <h2 style="text-align: center;">Level Above: <badge class="badge badge-success">${matrixObject.getCompareTo()}</badge></h2>
                            <table class="table-pairwaise">
                                <g:each var="i" in="${0..<matrixObject.getColumn()}">
                                    <g:if test="${i==0}">
                                        <tr class="first-row">
                                            <th>#</th>
                                            <g:each var="alternative" in="${matrixObject.getItems()}">
                                                <th>${alternative}</th>
                                            </g:each>
                                        </tr>
                                    </g:if>
                                    <tr>
                                        <g:each var="j" in="${0..<matrixObject.getRow()}">
                                            <g:if test="${j==0}">
                                                <th class="first-column">
                                                    ${matrixObject.getItems().get(i)}
                                                </th>
                                            </g:if>
                                            <g:if test="${i==j}">
                                                <td><input value="${matrixObject.getMatrix().get(i,j)}" name="ava-${matrixObject.getCompareTo()}-${matrixObject.getItems().toString()}" step="0.1" min="1" max="1"></td>
                                            </g:if>
                                            <g:else>
                                                <td><input onkeyup="makeSomeMagic(${i+1},${j+1},'${matrixObject.getCompareTo()}')" id="${matrixObject.getCompareTo()}${(i+1)}${(j+1)}" name="ava-${matrixObject.getCompareTo()}-${matrixObject.getItems().toString()}" value="${matrixObject.getMatrix().get(i,j)}" step="0.1" min="0" max="9"></td>
                                            </g:else>
                                        </g:each>
                                    </tr>
                                </g:each>
                            </table>
                        </div>
                    </g:each>
                </div>
            </div>
            <button type="submit" id="dothemath-button" class="btn btn-block btn-primary mt-5">Do the Math!</button>
        </div>
    </g:form>

    <script src="../js/jquery-3.2.1.min.js"></script>
    <script src="../js/tether.min.js"></script>
    <script src="../js/bootstrap.min.js"></script>
    <script src="../js/frontfunctionality.js"></script>
</body>
</html>