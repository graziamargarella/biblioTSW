<%@ page import="model.Libro" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.Set" %>
<%@ page import="java.util.LinkedList" %>
<%@ page import="model.Utente" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:include page="/header.jsp">
    <jsp:param name="pageTitle" value="ShopPage"/>
</jsp:include>
<%
    if ((request.getAttribute("libri")==null) || (request.getAttribute("categorie") == null)){
        response.sendRedirect(response.encodeRedirectURL("./ShopServlet"));
        return;
    }
    LinkedList<Libro> libri = (LinkedList<Libro>) request.getAttribute("libri");
    Set<String> categorie = (Set<String>) request.getAttribute("categorie");

    Utente user = (Utente) session.getAttribute("loggedUser");
%>
<div>
    <div class="container mt-4">
        <% if(user != null &&  user.checkIsAdmin()){%>
        <div class="text-center">
            <a href="newLibro.jsp"><button type="button" class="btn btn-primary mt-2 mb-5"><h6 style="color:white;">Aggiungi nuovo libro</h6></button></a>
        </div>
        <%}%>
        <div class="row">
            <div class="col-4">
                <div class="position-fixed categorie">
                    <h3 class="mb-4">Categorie</h3>
                    <% int i = 0;
                        Object[] cat = categorie.toArray();
                        while(i < cat.length) {
                            String s = cat[i].toString();
                    %><h5><a href="#<%= s %>"><%=s%></a></h5>
                    <% i++; } %>
                </div>
            </div>
            <div class="col-8">
                <div class="row">
                    <%
                        int j = 0;
                        while(j < libri.size()){
                            Libro libro = libri.get(j);
                    %>
                    <div id="<%= libro.getNomeCategoria() %>" style="height: auto; width: 200px; margin-bottom: 30px;">
                        <a href="LibroServlet?isbn=<%= libro.getIsbn()%>"><img src="images/copertine/<%= libro.getIsbn()%>.jpg" width="80%"></a>
                        <h5><a href="LibroServlet?isbn=<%= libro.getIsbn()%>"><%=libro.getTitolo()%></a></h5>
                    </div>
                    <%j++;}%>
                </div>
            </div>
        </div>
    </div>
</div>
<jsp:include page="footer.html"></jsp:include>