<%@ page import="model.Libro" %>
<%@ page import="model.Utente" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    Libro libro = (Libro) request.getAttribute("libro");
    Utente user = (Utente) session.getAttribute("loggedUser");
%>
<jsp:include page="/header.jsp">
    <jsp:param name="pageTitle" value="<%=libro.getTitolo()%>"/>
</jsp:include>
<div class="container mt-5">
    <div class="row mt-5">
        <div class="col">
            <img src="images/copertine/<%=libro.getIsbn()%>.jpg" style="display: block; margin-left: auto;margin-right: auto;width: 70%;">
        </div>
        <div class="col-7">
            <div class="container">
                <h1><%= libro.getTitolo() %></h1>
                <h2><%= libro.getAutore() %></h2>
                <p><i><%= libro.getDescrizione() %></i></p>
                <h5><%= libro.getEditore()%></h5>
                <h6><%= libro.getNomeCategoria() %></h6>
                <h5>Prezzo: € <%= libro.getPrezzo() %></h5>
                <% if(user != null &&  user.checkIsAdmin()){%>
                <div class="text-center">
                    <form action="AdminServlet?action=deleteProd" method="post">
                        <input type="hidden" name="isbn" value="<%= libro.getIsbn()%>">
                        <button class="btn btn-primary mt-5" type="submit"><h4 style="color:white">Rimuovi libro</h4></button>
                    </form>
                </div>
                <%} else {%>
                <div class="text-center">
                    <form action="CarrelloServlet?action=addProd" method="post">
                        <input type="hidden" name="isbn" value="<%= libro.getIsbn()%>">
                        <button class="btn btn-primary mt-5" type="submit"><h4 style="color:white">Aggiungi al carrello</h4></button>
                    </form>
                </div>
                <%}%>

            </div>
        </div>
    </div>
</div>

<jsp:include page="footer.html"></jsp:include>

