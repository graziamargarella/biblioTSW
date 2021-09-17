<%@ page import="model.Utente" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:include page="/header.jsp">
    <jsp:param name="pageTitle" value="ShopPage"/>
</jsp:include>
<div class="container">
<div class="text-center">
    <% Utente user = (Utente) session.getAttribute("loggedUser");
        if(user != null && user.checkIsAdmin()){%>
    <h2>Inserimento nuovo libro</h2>
    <form action="AdminServlet?action=addProd" method="post">
        <label for="isbn"><h4>ISBN</h4></label>
        <input type="text" name="isbn" id="isbn" class="form-control" maxlength="13" oninput="validaIsbn()">
        <label for="titolo"><h4>Titolo</h4></label>
        <input type="text" name="titolo" id="titolo" class="form-control" maxlength="100" oninput="validaTitolo()">
        <label for="autore"><h4>Autore</h4></label>
        <input type="text" name="autore" id="autore" class="form-control" maxlength="100" oninput="validaAutore()">
        <label for="editore"><h4>Editore</h4></label>
        <input type="text" name="editore" id="editore" class="form-control" maxlength="100" oninput="validaEditore()">
        <label for="descrizione"><h4>Descrizione</h4></label>
        <input type="text" name="descrizione" id="descrizione" class="form-control" maxlength="500" oninput="validaDescrizione()">
        <label for="categoria"><h4>Categoria</h4></label>
        <input type="text" name="categoria" id="categoria" class="form-control" maxlength="50" oninput="validaCategoria()">
        <label for="prezzo"><h4>Prezzo</h4></label>
        <input type="text" name="prezzo" id="prezzo" class="form-control" oninput="validaPrezzo()">
        <!--<label for="file"><h4>Copertina</h4></label><br>
        <input type="file" name="file" id="file"/>-->
        <button id="aggiungi" type="submit" class="btn btn-primary mt-5" disabled>Aggiungi libro</button>
    </form>
    <%} else {%>
    <h1>ACCESSO NON AUTORIZZATO</h1>
    <%}%>
</div>
</div>
<script>
    var isbnOk = false;
    var titoloOk = false;
    var autoreOk = false;
    var editoreOk = false;
    var descrizioneOk = false;
    var categoriaOk = false;
    var prezzoOk = false;

    function validaIsbn(){
        var isbn = $('#isbn').val();
        if (isbn.match(/^[0-9]{13}$/)){
            isbnOk = true;
        }
        checkStato();
    }

    function validaTitolo(){
        var titolo = $('#titolo').val();
        if (titolo.match(/^[ a-zA-Z\u00C0-\u00ff]+$/)){
            titoloOk = true;
        }
        checkStato();
    }

    function validaAutore(){
        var autore = $('#autore').val();
        if (autore.match(/^[ a-zA-Z\u00C0-\u00ff]+$/)){
            autoreOk = true;
        }
        checkStato();
    }

    function validaEditore(){
        var editore = $('#editore').val();
        if (editore.match(/^[ a-zA-Z\u00C0-\u00ff]+$/)){
            editoreOk = true;
        }
        checkStato();
    }

    function validaDescrizione(){
        var desc = $('#descrizione').val();
        if (desc.match(/^[ a-zA-Z,.;\u00C0-\u00ff]+$/)){
            descrizioneOk = true;
        }
        checkStato();
    }

    function validaCategoria(){
        var categoria = $('#categoria').val();
        if (categoria.match(/^[ a-zA-Z\u00C0-\u00ff]+$/)){
            categoriaOk = true;
        }
        checkStato();
    }

    function validaPrezzo(){
        var prezzo = $('#prezzo').val();
        if (prezzo.match(/^[0-9,]+$/)){
            prezzoOk = true;
        }
        checkStato();
    }

    function checkStato(){
        if(isbnOk && titoloOk && autoreOk && editoreOk && descrizioneOk && categoriaOk && prezzoOk){
            document.getElementById('aggiungi').disabled = false;
        }
    }
</script>
<jsp:include page="footer.html"></jsp:include>