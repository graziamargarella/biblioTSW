<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:include page="/header.jsp">
    <jsp:param name="pageTitle" value="Acquisto"/>
</jsp:include>
<div class="container">
    <div class="text-center">
        <h2>Acquisto</h2>
        <p><i>I nostri prodotti sono interamente digitali.<br>Al termine della procedura di acquisto riceverai i prodotti ordinati nella casella di posta elettronica indicata in fase di registrazione.</i></p>
        <h3>Metodo di pagamento</h3>
        <form action="OrdineServlet" method="post">
            <div class="row mt-3 mb-4">
                <div class="col">
                    <label for="titolare"><h6>Titolare della carta</h6></label>
                    <input type="text" name="titolare" id="titolare" class="form-control" placeholder="Titolare" oninput="verificaTitolare()">
                </div>
                <div class="col">
                    <label for="numCarta"><h6>Numero di carta</h6></label>
                    <input type="text" name="numCarta" id="numCarta" class="form-control" placeholder="Numero Carta" maxlength="16" oninput="verificaNumCarta()">
                </div>
            </div>
            <div class="row">
                <div class="col">
                    <label for="CVV"><h6>CVV</h6></label>
                    <input type="text" name="CVV" id="CVV" placeholder="CVV" class="form-control" maxlength="3" oninput="verificaCVV()">
                </div>
                <div class="col">
                    <label for="anno"><h6>Mese di scadenza</h6></label>
                    <input type="number" name="meseScad" id="mese" min="1" max="12" class="form-control" placeholder="Mese" oninput="verificaMese()">
                </div>
                <div class="col">
                    <label for="anno"><h6>Anno di scadenza</h6></label>
                    <input type="number" name="annoScad" id="anno" min="2021" max="2030" class="form-control" placeholder="Anno" oninput="verificaAnno()">
                </div>
            </div>
            <button id="acquista" type="submit" class="btn btn-primary mt-5" disabled>Procedi all'acquisto</button>
        </form>
    </div>
</div>
<script>
    var titolareOk = false;
    var numCartaOk = false;
    var CVVOk = false;
    var meseOk = false;
    var annoOk = false;

    function verificaTitolare(){
        var titolare = $('#titolare').val();
        if (titolare.match(/^[ a-zA-Z\u00C0-\u00ff]+$/)){
            titolareOk = true;
        }
        aggiornaStato();
    }

    function verificaNumCarta(){
        var numCard = $('#numCarta').val();
        if (numCard.match(/^[0-9]{13}$/)){
            numCartaOk = true;
        }
        aggiornaStato();
    }

    function verificaCVV(){
        var cvv = $('#CVV').val();
        if (cvv.match(/^[0-9]{3}$/)){
            CVVOk = true;
        }
        aggiornaStato();
    }

    function verificaMese(){
        var mese = $('#mese').val();
        if (mese >= 1 && mese <= 12){
            meseOk = true;
        }
        aggiornaStato();
    }

    function verificaAnno(){
        var anno = $('#anno').val();
        if (anno >= 2021 && anno <= 2030) {
            annoOk = true;
        }
        aggiornaStato();
    }

    function aggiornaStato(){
        if(titolareOk && numCartaOk && CVVOk && meseOk && annoOk) {
            document.getElementById('acquista').disabled = false;
        }
    }
</script>
<jsp:include page="footer.html"></jsp:include>
