/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;
import db.DBManager;
import db.Group;
import db.Post;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.Enumeration;
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
public class UploadController extends HttpServlet {

    private DBManager manager;
    List<Group> groups;

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
        String dirBase;
        String folder;
        String dirPath;
        HttpSession session = request.getSession(false);


        folder = session.getAttribute("idgruppo").toString();
        //dirBase = "/Users/ANDre1/Apache_Tomcat/files/";
        dirPath = getServletContext().getRealPath("/") + folder;
        //dirPath = dirBase + folder;

        File theDir = new File(dirPath);

        // if the directory does not exist, create it
        if (!theDir.exists()) {
            System.out.println("creating directory: " + folder);
            boolean result = theDir.mkdir();

            if (result) {
                System.out.println("DIR created");
            }

        }


        // Use an advanced form of the constructor that specifies a character
        // encoding of the request (not of the file contents) and a file
        // rename policy.
        MultipartRequest multi = new MultipartRequest(request, dirPath, 10 * 1024 * 1024, "ISO-8859-1", new DefaultFileRenamePolicy());

        Enumeration params = multi.getParameterNames();
        while (params.hasMoreElements()) {
            String name = (String) params.nextElement();
            String value = multi.getParameter(name);

        }

        Enumeration files = multi.getFileNames();

        while (files.hasMoreElements()) {
            String name = (String) files.nextElement();
            String filename = multi.getFilesystemName(name);
            String originalFilename = multi.getOriginalFileName(name);
            String type = multi.getContentType(name);
            File f = multi.getFile(name);

            String indirizzo;
            String primaparte;
            String fileintero;

            primaparte = getServletContext().getRealPath("/");
            fileintero = f.toString();
            indirizzo = fileintero.substring(primaparte.length() - 1);

            try {
                manager.inserisciFile(request, indirizzo, f.getName());
            } catch (SQLException ex) {
                Logger.getLogger(UploadController.class.getName()).log(Level.SEVERE, null, ex);
            }

            VisualizzaGruppo(request, response);


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
    
    
     private void VisualizzaGruppo(HttpServletRequest request, HttpServletResponse response) {
        List<Post> posts = null;
HttpSession session = request.getSession(false);
        
        session.setAttribute("idgruppo", request.getParameter("view"));
        try {
            posts = manager.trovaPost(request);
        } catch (SQLException ex) {
            Logger.getLogger(ControllerServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        request.setAttribute("listaPost", posts);
        //rimando al login

        RequestDispatcher rd = request.getRequestDispatcher("/GroupPage.jsp");
       
            try {
                rd.forward(request, response);
            } catch (Exception ex) {
                Logger.getLogger(ControllerGruppoServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
     
    }
}
