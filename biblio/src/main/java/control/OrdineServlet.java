package control;

import model.*;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "OrdineServlet", value = "/OrdineServlet")
public class OrdineServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try{
            if (request.getSession().getAttribute("loggedUser")==null)
            {
                throw new Exception("Utente non autenticato");
            }

            String titolare = request.getParameter("titolare");
            if(!(titolare != null && titolare.trim().length()>0 && titolare.matches("^[ a-zA-Z\u00C0-\u00ff]+$")))
            {
                throw new Exception("Nome non valido");
            }

            String carta = request.getParameter("numCarta");
            if (!(carta != null && carta.matches("^[0-9]{16}$")))
            {
                throw  new Exception("Carta non valida");
            }

            int mese = Integer.parseInt(request.getParameter("meseScad"));
            if (mese > 12 || mese < 1){
                throw new Exception("Mese non valido");
            }

            String CVV = request.getParameter("CVV");
            if (!(CVV != null && CVV.length()==3 && CVV.matches("^[0-9]{3}$")))
            {
                throw new Exception("CVV non valido");
            }

            int anno = Integer.parseInt(request.getParameter("annoScad"));
            if (anno < 2021 || anno > 2030) {
                throw new Exception("Anno non valido");
            }
            Carrello carrello = (Carrello) request.getSession().getAttribute("carrello");
            Utente utente = (Utente) request.getSession().getAttribute("loggedUser");
            OrdineDAO ordineDAO = new OrdineDAO();

            for(int i = 0; i < carrello.getItems().size(); i++){
                Ordine ordine = new Ordine();
                ordine.setCliente(utente.getMail());
                ordine.setIsbn((carrello.getItems().get(i)).getIsbn());
                ordineDAO.doSave(ordine);
            }
            carrello.deleteItems();
            request.getSession().setAttribute("carrello", carrello);

            RequestDispatcher rd = request.getRequestDispatcher("/AcquistoSuccesso.jsp");
            rd.forward(request, response);
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
