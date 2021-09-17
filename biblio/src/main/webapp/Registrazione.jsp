<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:include page="/header.jsp">
    <jsp:param name="pageTitle" value="Registrazione"/>
</jsp:include>
<div class="container">
    <div class="text-center">
        <h2>Registrazione</h2>
        <form action="UtenteServlet?action=registrazione" name="registrazione" method="post">
            <div class="row">
                <!--mail-->
                <label for="mail"><h4>Mail</h4></label>
                <input type="email" name="mail" id="mail" class="form-control m-3" oninput="validaEmail()">
                <!--password e check password-->
                <label for="pwd"><h4>Password</h4></label>
                <input type="password" name="pwd" id="pwd" class="form-control m-3" oninput="checkPassStrength()">
                <h5 id="password-strength-status"></h5>
            </div>

            <div class="row">
                <div class="col">
                    <label for="nome"><h4>Nome</h4></label>
                    <input type="text" name="nome" id="nome" class="form-control m-3" oninput="validaNome()">
                </div>
                <div class="col">
                    <label for="cognome"><h4>Cognome</h4></label>
                    <input type="text" name="cognome" id="cognome" class="form-control m-3" oninput="validaCognome()">
                </div>
            </div>

            <div class="m-3">
                <button class="btn btn-primary" type="submit" id="invia" disabled><h4 style="color:white">Continua</h4></button>
            </div>

        </form>
    </div>
    </div>
</div>

<script>
    var passwordOk = false;
    var emailOk = false;
    var nomeOk = false;
    var cognomeOK = false;

    function validaEmail() {
        var xmlHttpRequest = new XMLHttpRequest();
        xmlHttpRequest.onreadystatechange = function() {
            if (this.readyState == 4 && this.status == 200 && this.responseText == '<ok/>') {
                console.log(this.responseText);
                $('#mail').css("color", "green");
                emailOk = true;
            } else {
                $('#mail').css("color", "red");
                emailOk = false;
            }
        }

        var input = $('#mail').val();
        if (input.match(/(\w*\d*.*)@\w+([\.-]?\w+)*(\.\w+)+$/)){
            xmlHttpRequest.open("GET", "verifica-email?email=" + encodeURIComponent(input), true);
            xmlHttpRequest.send();
        } else {
            $('#mail').css("color", "red");
            emailOk = false;
        }
        cambiaStato();
    }

    function checkPassStrength() {
        var number = /([0-9])/;
        var alphabets = /([a-zA-Z])/;
        var special_characters = /([~,!,@,#,$,%,^,&,*,_,+,=,?,>,<])/;
        var pwd =$('#pwd').val()
        if (pwd.length < 8) {
            $('#password-strength-status').css('color', 'red');
            $('#password-strength-status').html("Password debole, deve avere almeno 8 caratteri");
            passwordOk = false;
        } else {
            if (pwd.match(number) && pwd.match(alphabets) && pwd.match(special_characters)&& pwd.match(/[A-Z]/)) {
                $('#password-strength-status').css('color', 'green');
                $('#password-strength-status').html("Password forte");
                passwordOk = true;
            } else {
                $('#password-strength-status').css('color', '#FF8000');
                $('#password-strength-status').html("Password media, aggiungi caratteri maiuscoli, numeri e caratteri speciali");
                passwordOk = false;
            }
        }
        cambiaStato();
    }
    function validaNome() {
        var input = $('#nome').val();
        if (input.match(/([a-zA-Z])/)) {
            $('#nome').css("color", "green");
            nomeOk = true;
        }
        else {
            $('#nome').css("color", "red");
            nomeOk = false;
        }
        cambiaStato();
    }

    function validaCognome() {
        var input = $('#cognome').val();
        if (input.match(/([a-zA-Z])/)) {
            $('#cognome').css("color", "green");
            cognomeOK = true;
        } else {
            $('#cognome').css("color", "red");
            cognomeOK = false;
        }
        cambiaStato();
    }


    function cambiaStato() {
        if (emailOk && passwordOk && nomeOk && cognomeOK)
            document.getElementById('invia').disabled = false;
    }
</script>
</body>
</html>
