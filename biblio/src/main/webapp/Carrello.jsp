<%@ page import="model.Carrello" %>
<%@ page import="model.Libro" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    Carrello carrello =(Carrello) session.getAttribute("carrello");
    if(carrello == null){
        carrello = new Carrello();
    }
%>
<jsp:include page="/header.jsp">
    <jsp:param name="pageTitle" value="Carrello"/>
</jsp:include>
<div class="container">
    <% int size = carrello.getItems().size();
        if(size > 0){%>
    <div class="row">
        <h2>Carrello</h2>
        <table>
            <tr class="p-4">
                <th></th>
                <th><h5>Prodotto</h5></th>
                <th><h5>Prezzo</h5></th>
                <th><h5>Rimuovi</h5></th>
            </tr>
            <%for(int i = 0; i < size ; i++) {
                Libro l = carrello.getItems().get(i);
            %>
            <tr>
                <td><img src="images/copertine/<%=l.getIsbn()%>.jpg" width="60px"></td>
                <td><h6><%=l.getTitolo()%></h6></td>
                <td><h6>&euro;<%=l.getPrezzo()%></h6></td>
                <td>
                    <form action="CarrelloServlet?action=deleteProd" method="post">
                        <input type="hidden" name="isbn" value="<%=l.getIsbn()%>">
                        <button class="btn btn-primary" type="submit"><h4 style="color:white">Rimuovi</h4></button>
                    </form>
                </td>
            </tr>
        <%}%>
            <tr>
                <td><h5>Totale</h5></td>
                <td></td>
                <td><h5>&euro;<%= carrello.getTotale()%></h5></td>
                <td>
                    <form action="CarrelloServlet?action=procediAcquisto" method="post">
                        <button class="btn btn-primary" type="submit"><h4 style="color:white">Acquista</h4></button>
                    </form>
                </td>
            </tr>
        </table>
    </div>
</div>

<%}else{%>
<div class="text-center">
    <h3 class="pt-5">Non ci sono prodotti nel tuo carrello</h3>
    <h4>Torna alla <a href="ShopPage.jsp">pagina dello Shop</a></h4>
</div>
<%}%>
</div>
<jsp:include page="footer.html"></jsp:include>
