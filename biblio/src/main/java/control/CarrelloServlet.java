package control;

import model.Carrello;
import model.Libro;
import model.LibroDAO;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "CarrelloServlet", value = "/CarrelloServlet")
public class CarrelloServlet extends HttpServlet {
    private static final LibroDAO libroDAO = new LibroDAO();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();

        if (request.getSession().getAttribute("loggedUser") == null) {
            RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/Login.jsp");
            dispatcher.forward(request, response);
        }

        Carrello carrello = (Carrello) session.getAttribute("carrello");
        if (carrello == null) {
            carrello = new Carrello();
            session.setAttribute("carrello", carrello);
        }

        String action = request.getParameter("action");
        String isbn = request.getParameter("isbn");

        try {
            Libro item = libroDAO.doRetrieveByKey(isbn);
            if(action.equals("addProd")) {
                if(item != null) {
                    carrello.addItem(item);
                }
            } else if(action.equals("deleteProd")) {
                carrello.deleteItem(item);
            } else if (action.equals("procediAcquisto")) {
                RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/Acquisto.jsp");
                dispatcher.forward(request, response);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        request.getSession().setAttribute("carrello", carrello);

        RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/Carrello.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
