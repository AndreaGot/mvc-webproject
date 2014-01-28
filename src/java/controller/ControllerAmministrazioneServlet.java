/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import db.DBManager;
import db.Group;
import db.User;
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
public class ControllerAmministrazioneServlet extends HttpServlet {

    private DBManager manager;
    List<Group> groups;

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
        String azione = request.getParameter("azione");

        if (azione.equals("amministra")) {
            RequestDispatcher rd = request.getRequestDispatcher("/GroupAdminPage.jsp");
            rd.forward(request, response);
        } else if (azione.equals("cambianome")) {
            RequestDispatcher rd = request.getRequestDispatcher("/GroupNewNamePage.jsp");
            rd.forward(request, response);
        } else if (azione.equals("nuovonome")) {

            Boolean fatto = false;
            try {
                fatto = manager.settaNomeGruppo(request);
            } catch (SQLException ex) {
                Logger.getLogger(ControllerGruppoServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
            if (fatto) {
                request.setAttribute("message", "Nome cambiato con successo!");
            } else {
                request.setAttribute("message", "Nome non cambiato :(");
            }
            RequestDispatcher rd = request.getRequestDispatcher("/LoginPage.jsp");
            rd.forward(request, response);
        } else if (azione.equals("creareport")) {
            List<User> user = new ArrayList<User>();
            String posts = "";
            String data = "";

            try {
                user = manager.trovaUtente(request);
                // step 1: creation of a document-object
            } catch (SQLException ex) {
                Logger.getLogger(ControllerAmministrazioneServlet.class.getName()).log(Level.SEVERE, null, ex);
            }

            Document document = new Document();

            response.setContentType("application/pdf"); // Code 1

            try {
                PdfWriter.getInstance(document, response.getOutputStream());
                document.open();

                // Code 3
                PdfPTable table = new PdfPTable(3);
                table.addCell("Nome utente");
                table.addCell("Post");
                table.addCell("Ultimo post");
                table.addCell(" ");
                table.addCell(" ");
                table.addCell(" ");
                for (User u : user) {
                    table.addCell(u.getUserName());
                    try {
                        posts = manager.numeroPost(request, u.getId());
                        data = manager.dataPost(request, u.getId());

                    } catch (SQLException ex) {
                        Logger.getLogger(ControllerAmministrazioneServlet.class.getName()).log(Level.SEVERE, null, ex);
                    }

                    table.addCell(posts);
                    table.addCell(data);
                }

                // Code 4
                document.add(table);
                document.close();
            } catch (DocumentException e) {
                e.printStackTrace();
            }


        } else if (azione.equals("invitautente")) {
            RequestDispatcher rd = request.getRequestDispatcher("/GroupUserInvitation.jsp");
            rd.forward(request, response);
        } else if (azione.equals("nuovoinvito")) {
            Boolean fatto = false;
            String idutente;

            try {
                idutente = manager.trovaIdDaNomeUtente(request);
                fatto = manager.inserisciInvito(request, idutente);
            } catch (SQLException ex) {
                Logger.getLogger(ControllerAmministrazioneServlet.class.getName()).log(Level.SEVERE, null, ex);
            }

            if (fatto) {
                request.setAttribute("message", "Invito inviato con successo!");
            } else {
                request.setAttribute("message", "Invito non Inviato :(");
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
