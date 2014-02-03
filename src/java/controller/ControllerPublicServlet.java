/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import db.DBManager;
import db.Group;
import db.Post;
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
public class ControllerPublicServlet extends HttpServlet {

    private DBManager manager;
    List<Group> groups;
    List<Group> publicGroups;

    @Override
    public void init() throws ServletException {
        // inizializza il DBManager dagli attributi di Application
        this.manager = (DBManager) super.getServletContext().getAttribute("dbmanager");
    }

    /**
     * Processes requests for both HTTP
     * <code>GET</code> and
     * <code>POST</code> methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession(true);
                   session.setAttribute("view", request.getParameter("view"));
            VisualizzaGruppoPubblico(request, response);
    }

    private void VisualizzaGruppoPubblico(HttpServletRequest request, HttpServletResponse response) {
        List<Post> posts = null;
        HttpSession session = request.getSession(true);

        session.setAttribute("idgruppo", session.getAttribute("view"));
        try {
            posts = manager.trovaPost(request);
        } catch (SQLException ex) {
            Logger.getLogger(ControllerServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        request.setAttribute("listaPost", posts);
        //rimando al login

        RequestDispatcher rd = request.getRequestDispatcher("/GroupPagePublicHome.jsp");

        try {
            rd.forward(request, response);
        } catch (Exception ex) {
            Logger.getLogger(ControllerGruppoServlet.class.getName()).log(Level.SEVERE, null, ex);
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
