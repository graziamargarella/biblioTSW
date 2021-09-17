package control;

import model.Libro;
import model.LibroDAO;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/LibroServlet")
public class LibroServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("isbn");
        Libro libro = new Libro();
        try {
            libro = LibroDAO.doRetrieveByKey(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        request.setAttribute("libro", libro);

        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/Libro.jsp");
        requestDispatcher.forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
