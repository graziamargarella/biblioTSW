package control;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServlet;
import javax.servlet.annotation.WebServlet;
import java.io.IOException;


import model.UtenteDAO;

@WebServlet("/verifica-email")
public class VerificaEmailServlet extends HttpServlet{
    private static final long serialVersionUID = 1L;
    private final UtenteDAO utenteDAO = new UtenteDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("mail");
        response.setContentType("text/xml");
        if(utenteDAO.doRetrieveByEmail(email) == null)
            response.getWriter().append("<ok/>");
        else
            response.getWriter().append("<no/>");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
