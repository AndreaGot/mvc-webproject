/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import db.DBManager;
import db.Group;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
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
                Logger.getLogger(RisultatoCreaGruppo.class.getName()).log(Level.SEVERE, null, ex);
            }

            if (fatto && fatto2) {
                session.setAttribute("message", "Gruppo creato con successo!");
            } else {
                session.setAttribute("message", "Gruppo non creato: sicuro non esista già?");
            }


            RequestDispatcher rd = request.getRequestDispatcher("/LoginPage.jsp");
            rd.forward(request, response);
        }


    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}
