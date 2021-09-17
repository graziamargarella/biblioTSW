<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:include page="/header.jsp">
    <jsp:param name="pageTitle" value="Login"/>
</jsp:include>
<div class="container mt-5">
    <h1 class="text-center">Login</h1>
    <form class="p-5" name="login" action="UtenteServlet?action=login" method="post">
        <div class="row pb-3">
            <h3><label for="mail">Email</label></h3>
            <input type="email" name="mail" id="mail" class="form-control" oninput="validaMail()">
        </div>
        <div class="row pb-3">
            <h3><label for="password">Password</label></h3>
            <input type="password" name="password" id="password" class="form-control" oninput="validaPwd()">
        </div>
        <div class="text-center">
            <button class="btn btn-primary" type="submit" id="continua" disabled><h3 style="color:white">Continua</h3></button>
        </div>


    </form>
    <div class="m-3 text-center">
        <a href="Registrazione.jsp" class="m-5"><button class="btn btn-primary"><h4 style="color:white">Registrati</h4></button></a>
    </div>

<script type="text/javascript">
    var emailOk = false;

    function validaMail(){
        var input = $('#mail').val();
        if (input.match(/(\w*\d*.*)@\w+([\.-]?\w+)*(\.\w+)+$/)){
            emailOk = true;
        }
        aggiorna();
    }

    function aggiorna(){
        if (emailOk){
            document.getElementById('continua').disabled = false;
        }
    }

</script>
<jsp:include page="footer.html"></jsp:include>