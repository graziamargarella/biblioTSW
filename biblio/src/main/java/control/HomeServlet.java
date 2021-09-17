package control;

import model.Libro;
import model.LibroDAO;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.LinkedList;

@WebServlet(name = "HomeServlet", urlPatterns ="", loadOnStartup = 1)
public class HomeServlet extends HttpServlet {
    private final LibroDAO libroDAO = new LibroDAO();

    public void init() throws ServletException {
        LinkedList<String> categorie = (LinkedList<String>) libroDAO.getAllCategorie();
        getServletContext().setAttribute("categorie", categorie);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            LinkedList<Libro> libri = (LinkedList<Libro>) libroDAO.doRetrieveAll();
            LinkedList<Libro> esempi = new LinkedList<>();
            for (int i = 0; i < 4; i++){
                esempi.add(libri.get(i));
            }
            request.setAttribute("libri", esempi);
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("index.jsp");
            requestDispatcher.forward(request, response);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}