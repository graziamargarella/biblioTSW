package control;

import model.*;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.UUID;

@WebServlet(name = "UtenteServlet", value = "/UtenteServlet")
public class UtenteServlet extends HttpServlet {
    private final UtenteDAO utenteDAO = new UtenteDAO();
    private final LoginDAO loginDAO = new LoginDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        if (request.getParameter("action").equals("logout")){
            request.getSession().removeAttribute("loggedUser");
            String redirectedPage = "";
            response.sendRedirect(request.getContextPath() + redirectedPage);

        } else if (request.getParameter("action").equals("login")) {
            String email = request.getParameter("mail");
            String pwd = request.getParameter("password");

            try {
                Utente utente = new Utente();
                if (email != null && pwd != null) {
                    utente = utenteDAO.doRetrieveByEmailPassword(email, pwd);
                    if (utente == null){
                        response.sendRedirect(request.getContextPath() + "/Login.jsp");
                        throw new Exception ("Credenziali non valide");
                    }
                }
                request.getSession().setAttribute("loggedUser", utente);

                Login login = new Login();
                login.setEmail(utente.getMail());
                login.setToken(UUID.randomUUID().toString());
                login.setTime(Timestamp.from(Instant.now()));
                loginDAO.doSave(login);

                Cookie cookie = new Cookie("login", login.getId() + "_" + login.getToken());
                cookie.setMaxAge(30 * 24 * 60 * 60); //30 giorni
                response.addCookie(cookie);
                String redirectedPage = "";
                if (utente.checkIsAdmin()){
                     redirectedPage = "/ShopPage.jsp";
                } else {
                    redirectedPage = "";
                }
                response.sendRedirect(request.getContextPath() + redirectedPage);

            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (request.getParameter("action").equals("registrazione")) {
                try {
                    Utente utente = new Utente();
                    utente.setAdmin(false);
                    utente.setNome(request.getParameter("nome"));
                    utente.setCognome(request.getParameter("cognome"));
                    utente.setMail(request.getParameter("mail"));
                    utente.setPassword(request.getParameter("pwd"));
                    utenteDAO.doSave(utente);

                    request.getSession().setAttribute("loggedUser", utente);
                    Login login = new Login();
                    login.setEmail(utente.getMail());
                    login.setToken(UUID.randomUUID().toString());
                    login.setTime(Timestamp.from(Instant.now()));
                    loginDAO.doSave(login);

                    Cookie cookie = new Cookie("login", login.getId() + "_" + login.getToken());
                    cookie.setMaxAge(30 * 24 * 60 * 60); //30 giorni
                    response.addCookie(cookie);

                    String redirectedPage = "";
                    response.sendRedirect(request.getContextPath() + redirectedPage);

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
