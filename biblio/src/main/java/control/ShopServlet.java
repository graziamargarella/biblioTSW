package control;

import model.Libro;
import model.LibroDAO;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

@WebServlet(name = "ShopServlet", value = "/ShopServlet")
public class ShopServlet extends HttpServlet {
    private final LibroDAO libroDAO = new LibroDAO();
    private Set<String> categorie;
    private LinkedList<Libro> libri;

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        libri = null;
        categorie = new HashSet<>();
        try {
            libri = (LinkedList<Libro>) libroDAO.doRetrieveAll();
            for (Libro l : libri) {
                categorie.add(l.getNomeCategoria());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        libri = sortCategory(libri, categorie);

        request.setAttribute("libri", libri);
        request.setAttribute("categorie", categorie);
        request.getRequestDispatcher("/ShopPage.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

    private LinkedList<Libro> sortCategory(LinkedList<Libro> libri,
                                                 Set<String> categorie2) {
        LinkedList<Libro> sorted = new LinkedList<Libro>();
        Object[] cat = categorie2.toArray();

        for (int a = 0; a < cat.length; a++) {
            for (int b = 0; b < libri.size(); b++) {
                if (libri.get(b).getNomeCategoria().equals(cat[a].toString())) {
                    sorted.add(libri.get(b));
                }
            }
        }
        return sorted;
    }

}
