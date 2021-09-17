package control;

import model.Libro;
import model.LibroDAO;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@WebServlet(name = "AdminServlet", value = "/AdminServlet")
public class AdminServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        final String CARTELLA_UPLOAD = "image" + File.separator + "copertine";
        if (request.getParameter("action").equals("addProd")){
            try{
                // script copertina
                /*Part filePart = request.getPart("file");
                String fileName = request.getParameter("isbn") + ".jpg";

                String destinazione = CARTELLA_UPLOAD + File.separator + fileName;
                Path pathDestinazione = Paths.get(getServletContext().getRealPath(destinazione));

                for (int i = 2; Files.exists(pathDestinazione); i++) {
                    throw new Exception("WARNING ISBN!");
                }

                InputStream fileInputStream = filePart.getInputStream();
                Files.createDirectories(pathDestinazione.getParent());
                Files.copy(fileInputStream, pathDestinazione);*/

                Libro libro = new Libro();
                libro.setIsbn(request.getParameter("isbn"));
                libro.setTitolo(request.getParameter("titolo"));
                libro.setAutore(request.getParameter("autore"));
                libro.setEditore(request.getParameter("editore"));
                libro.setDescrizione(request.getParameter("descrizione"));
                libro.setPrezzo(Float.parseFloat(request.getParameter("prezzo")));
                libro.setNomeCategoria(request.getParameter("categoria"));

                LibroDAO libroDAO = new LibroDAO();
                libroDAO.doSave(libro);

                response.sendRedirect(request.getContextPath() + "/ShopPage.jsp");

            } catch (Exception e) {
                System.out.println(request.getParameter("prezzo"));
                e.printStackTrace();
            }
        } else if (request.getParameter("action").equals("deleteProd")) {
            String isbn = request.getParameter("isbn");
            try{
                LibroDAO.doDelete(isbn);
                response.sendRedirect(request.getContextPath() + "/ShopPage.jsp");
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
