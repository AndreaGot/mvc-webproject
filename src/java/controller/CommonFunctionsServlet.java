/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import db.DBManager;
import db.Group;
import db.Invito;
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
public class CommonFunctionsServlet extends HttpServlet {

    private DBManager manager;
    List<Group> groups;
    
    public void init() throws ServletException {
        // inizializza il DBManager dagli attributi di Application
        this.manager = (DBManager) super.getServletContext().getAttribute("dbmanager");
    }



    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String azione = request.getParameter("azione");
        
        if (azione.equals("indietro"))
        {
            
            try {
                groups = manager.trovaGruppo(request);
            } catch (SQLException ex) {
            }
            request.setAttribute("listaGruppi", groups);
            RequestDispatcher rd = request.getRequestDispatcher("/GroupListPage.jsp");
            rd.forward(request, response);
        }
        else if (azione.equals("home"))
        {
             List<Invito> inviti = new ArrayList<Invito>();
            HttpSession session = request.getSession(false);
            try {
                    manager.cambiaData(request);
                    inviti = manager.trovaInvito(request);
                } catch (SQLException ex) {
                    Logger.getLogger(ControllerServlet.class.getName()).log(Level.SEVERE, null, ex);
                }
                
                session.setAttribute("listaInviti", inviti);
                
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
