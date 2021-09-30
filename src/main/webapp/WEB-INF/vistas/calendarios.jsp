<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Matias
  Date: 27/9/2021
  Time: 14:38
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <title>Proyecto Taller Web</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-F3w7mX95PdgyTmZZMECAngseQB83DfGTowi0iMjiWaeVhAn4FJkqJByhZMI3AhiU" crossorigin="anonymous"/>
</head>
<body>
<nav class="navbar navbar-expand-lg navbar-dark bg-primary">
    <div class="container-fluid"><a class="navbar-brand" href="home">Navbar</a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation"><span class="navbar-toggler-icon"></span></button>
        <div class="collapse navbar-collapse" id="navbarNav">
            <ul class="navbar-nav">
                <li class="nav-item"><a class="nav-link active" aria-current="page" href="home">Home</a></li>
                <li class="nav-item"><a class="nav-link" href="#">Features</a></li>
                <li class="nav-item"><a class="nav-link" href="#">Pricing</a></li>
                <li class="nav-item"><a class="nav-link disabled">Disabled</a></li>
            </ul>
        </div>
    </div>
</nav>
<div class="container mt-5">
    <section>
        <h1>${titulo}</h1>
        <form method="POST" action="calendarios">
            <select name="profesion" class="form-select">
                <option selected disabled value="0">Elija una opción</option>
                <c:forEach items="${calendarios}" var="calendar">
                    <option value="${calendar.profesion}">${calendar.profesion}</option>
                </c:forEach>
            </select>
            <button type="submit" class="btn btn-primary mt-3">Aplicar filtro</button>
        </form>
<%--        <form method="POST" action="calendarios">--%>
<%--        <form:select path="profesion" cssClass="form-select">--%>
<%--            <form:option selected="true" value="0" label="Elija una opción"  disabled="true" />--%>
<%--            <form:options items="${profesiones}" />--%>
<%--        </form:select>--%>
<%--            <button type="submit" class="btn btn-primary mt-3">Aplicar filtro</button>--%>
<%--        </form>--%>
        <div class="text-center">
            <img class="img-fluid" src="https://www.formget.com/wp-content/uploads/2015/07/imageedit_30_8479321211.gif" alt="Calendario"/>
        </div>
    </section>
</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/js/bootstrap.bundle.min.js" integrity="sha384-/bQdsTh/da6pkI1MST/rWKFNjaCP5gBSY4sEBT38Q/9RBh9AH40zEOg7Hlq2THRZ" crossorigin="anonymous"></script>
</body>
</html>
