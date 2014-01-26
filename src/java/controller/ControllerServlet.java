/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import db.DBManager;
import db.User;
import java.io.IOException;
import java.sql.SQLException;
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
public class ControllerServlet extends HttpServlet {

    private DBManager manager;
    private Boolean esito;

    @Override
    public void init() throws ServletException {
        // inizializza il DBManager dagli attributi di Application
        this.manager = (DBManager) super.getServletContext().getAttribute("dbmanager");
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP
     * <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
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

        HttpSession session = request.getSession(true);
        String azione = request.getParameter("azione").toString();
        RequestDispatcher view;


        if (azione.equals("registra")) {
            view = request.getRequestDispatcher("RegistrationPage.jsp");
            view.forward(request, response);
        } else if (azione.equals("login")) {
            view = request.getRequestDispatcher("login.jsp");
            view.forward(request, response);
        } else if (azione.equals("SalvaDatiRegistrazione")) {
            //Inserimento dati registrazione nel DB
            try {
                esito = manager.registrazione(request);
            } catch (SQLException ex) {
                Logger.getLogger(ControllerServlet.class.getName()).log(Level.SEVERE, null, ex);
            }

            if (esito == true) {
                request.setAttribute("message", "Iscrizione avvenuta con successo!");
            } else {
                request.setAttribute("message", "C'è stato un problema :(");
            }
            view = request.getRequestDispatcher("index.jsp");
            view.forward(request, response);

        } else if (azione.equals("accedi")) {
           
            String username = request.getParameter("username");
            String password = request.getParameter("password");
            // controllo nel DB se esiste un utente con lo stesso username + password
            User user;

            try {
                user = manager.authenticate(username, password);
            } catch (SQLException ex) {
                Logger.getLogger(ControllerServlet.class.getName()).log(Level.SEVERE, null, ex);
                throw new ServletException(ex);
            }

            if (user == null) {

                request.setAttribute("message", "Credenziali errate! per favore, riprova!");
                //rimando al login

                RequestDispatcher rd = request.getRequestDispatcher("/index.jsp");
                rd.forward(request, response);

            } else {

                // imposto l'utente connesso come attributo di sessione
                // per adesso e' solo un oggetto String con il nome dell'utente, ma posso metterci anche un oggetto User
                // con, ad esempio, il timestamp di login
                session.setAttribute("user", user.nome_completo);
                session.setAttribute("userid", user.id);

                RequestDispatcher rd = request.getRequestDispatcher("/LoginPage.jsp");
                rd.forward(request, response);

            }
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
