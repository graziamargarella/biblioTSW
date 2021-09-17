<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" import="control.*, model.*,java.net.*,java.text.*, java.util.*, java.sql.SQLException"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="ISO-8859-1">
    <title>Biblio - ${param.pageTitle}</title>
    <!-- import libreria bootstrap -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <!-- import file css esterno -->
    <link href="style.css" rel="stylesheet">
    <!-- jQuery -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>

</head>
<body>
<div class="navbar">
    <ul class="nav">
        <li class="nav-item">
            <a class="nav-link" href="."><img src="images/logo.png" height="70" alt="logo biblio"></a>
        </li>
        <li class="nav-item pt-3">
            <h3><a class="nav-link" href=".">Biblio</a></h3>
        </li>
        <li class="nav-item pt-3 ms-3">
            <h3><a class="nav-link" href="ShopPage.jsp">Shop</a></h3>
        </li>
    </ul>
    <ul class="nav justify-content-end">
        <% Utente user = (Utente) session.getAttribute("loggedUser");
        if(user != null && !user.checkIsAdmin()){%>
        <li class="nav-item pt-2">
            <h3><a class="nav-link" href="CarrelloServlet">Carrello</a></h3>
        </li>
        <%} if(user == null){%>
        <li class="nav-item pt-2">
            <h3><a class="nav-link" href="Login.jsp">Autenticazione</a></h3>
        </li>
        <%} else {%>
        <li class="nav-item pt-2">
            <h3><a class="nav-link" href="UtenteServlet?action=logout">Logout</a></h3>
        </li>
        <%}%>
    </ul>
</div>
