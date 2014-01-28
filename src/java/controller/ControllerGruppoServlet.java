/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import db.DBManager;
import db.Group;
import db.Invito;
import db.Post;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author ANDre1
 */
public class ControllerGruppoServlet extends HttpServlet {

    private DBManager manager;
    List<Group> groups;

    @Override
    public void init() throws ServletException {
        // inizializza il DBManager dagli attributi di Application
        this.manager = (DBManager) super.getServletContext().getAttribute("dbmanager");
    }

    /**
     * Handles the HTTP
     * <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String azione = request.getParameter("azione");

        if (azione.equals("logout")) {
            HttpSession session = request.getSession(false);

            if (session != null) {
                session.removeAttribute("user");
                session.removeAttribute("userid");
                session.invalidate();
            }

            request.setAttribute("message", "Logout effettuato con successo!");
            //rimando al login

            RequestDispatcher rd = request.getRequestDispatcher("/index.jsp");
            rd.forward(request, response);
        } else if (azione.equals("vedigruppo")) {

            try {
                groups = manager.trovaGruppo(request);
            } catch (SQLException ex) {
                 Logger.getLogger(ControllerGruppoServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
            request.setAttribute("listaGruppi", groups);
            //rimando al login

            RequestDispatcher rd = request.getRequestDispatcher("/GroupListPage.jsp");
            rd.forward(request, response);
        } else if (azione.equals("creagruppo")) {
            RequestDispatcher rd = request.getRequestDispatcher("/GroupCreationPage.jsp");
            rd.forward(request, response);
        } else if (azione.equals("confermagruppo")) {

            HttpSession session = request.getSession(false);
            Boolean fatto = false;
            Boolean fatto2 = false;
            String IDGruppo;

            try {
                fatto = manager.inserisciGruppo(request);
                IDGruppo = manager.trovaIdDaGruppo(request);
                fatto2 = manager.inserisciUtente(request, IDGruppo);

            } catch (SQLException ex) {
                Logger.getLogger(ControllerGruppoServlet.class.getName()).log(Level.SEVERE, null, ex);
            }

            if (fatto && fatto2) {
                request.setAttribute("message", "Gruppo creato con successo!");
            } else {
                request.setAttribute("message", "Gruppo non creato: sicuro non esista già?");
            }


            RequestDispatcher rd = request.getRequestDispatcher("/LoginPage.jsp");
            rd.forward(request, response);
        } else if (azione.equals("rispostainvito")) {
            HttpSession session = request.getSession(false);
            String risposta = request.getParameter("risposta");
            String idgruppo = request.getParameter("idgruppo");
            List<Invito> inviti = new ArrayList<Invito>();

            if ("Accetta".equals(risposta)) {
                session.setAttribute("message", "Invito accettato! Benvenuto nel gruppo!");
                try {

                    manager.settaInvito(request, idgruppo, "1");
                    manager.inserisciUtente(request, idgruppo);

                } catch (SQLException ex) {
                    Logger.getLogger(ControllerGruppoServlet.class.getName()).log(Level.SEVERE, null, ex);
                }

            } else if ("Rifiuta".equals(risposta)) {
                session.setAttribute("message", "Invito rifiutato correttamente");
                try {

                    manager.settaInvito(request, idgruppo, "2");

                } catch (SQLException ex) {
                    Logger.getLogger(ControllerGruppoServlet.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

            try {

                inviti = manager.trovaInvito(request);
            } catch (SQLException ex) {
                Logger.getLogger(ControllerServlet.class.getName()).log(Level.SEVERE, null, ex);
            }

            session.setAttribute("listaInviti", inviti);
            RequestDispatcher rd = request.getRequestDispatcher("/LoginPage.jsp");
            rd.forward(request, response);
        } else if (azione.equals("listapost")) {


            List<Post> posts = null;
            
            try {
                posts = manager.trovaPost(request);
            } catch (SQLException ex) {
                Logger.getLogger(ControllerServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
            request.setAttribute("listaPost", posts);
            //rimando al login

            RequestDispatcher rd = request.getRequestDispatcher("/GroupPage.jsp");
            rd.forward(request, response);
        }

    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}
