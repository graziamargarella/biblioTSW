<%@ page import="java.util.LinkedList" %>
<%@ page import="model.Libro" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<jsp:include page="/header.jsp">
    <jsp:param name="pageTitle" value="HomePage"/>
</jsp:include>
<div class="container">
    <div class="row">
        <div class="testo-home col">
            <h1>Benvenuti su Biblio</h1>
            <h5>Il nostro è un sito che offre ebook da leggere dove e quando vuoi.<br>Gli acquisti che effettui nella nostra webapp ti saranno consegnati all'indirizzo email inserito durante la procedura di registrazione.</h5><br>
            <h5>Con passione scegliamo un catalogo ricco dai generi più disparati, per esplorarlo seleziona la pagina dello Shop in alto.</h5><br>
            <h5>Quando vorrai acquistare qualche libro, effettua la registrazione e aggiungi al carrello cosa desideri.</h5><br>
            <h5>Quando sei soddisfatto del tuo ordine, procedi all'acquisto e avrai in men che non si dica tutti i tuoi ebook.</h5>
        </div>
        <div class="col" id="immagine-home">
            <img src="images/homepage2.jpg" alt="ereader" width="100%">
        </div>
    </div>
    <div class="ultimi-arrivi">
        <h2>Ecco qualche ebook del nostro catalogo</h2>
        <div class="row-home">
            <c:forEach items="${libri}" var="libro">
                <div class="column-home">
                    <div class="card-home">
                        <a href="LibroServlet?isbn=${libro.isbn}"><img src="./images/copertine/${libro.isbn}.jpg" style="width: 35%"></a>
                        <h3>
                            <a href="LibroServlet?isbn=${libro.isbn}" style="text-align: center;">${libro.titolo}</a>
                        </h3>
                    </div>
                </div>
            </c:forEach>
        </div>
    </div>
</div>
<jsp:include page="footer.html"></jsp:include>
