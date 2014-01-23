/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import db.DBManager;
import db.User;
import java.io.IOException;
import static java.lang.System.out;
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
public class ControllerServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    private DBManager manager;
    private Boolean registrazione;

    @Override
    public void init() throws ServletException {
        // inizializza il DBManager dagli attributi di Application
        this.manager = (DBManager) super.getServletContext().getAttribute("dbmanager");
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
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
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();

        String azione = request.getParameter("azione");
        
        RequestDispatcher view;
        if (azione == null){
        
        }
        else if (azione.equals("registra")) {
            view = request.getRequestDispatcher("RegistrationPage.jsp");
            view.forward(request, response);
        } else if (azione.equals("login")) {
            view = request.getRequestDispatcher("login.jsp");
            view.forward(request, response);
        }

        //Inserimento dati registrazione nel DB
       
        String username= request.getParameter("username").toString();
        String password= request.getParameter("password").toString();
        String email= request.getParameter("email").toString();
        String nome_completo= request.getParameter("nome_completo").toString();
        
        User user;
        try {
            boolean registrazione1= manager.registrazione(username, password, email, nome_completo);
        } catch (SQLException ex) {
            Logger.getLogger(ControllerServlet.class.getName()).log(Level.SEVERE, null, ex);
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
