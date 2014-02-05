/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;
import db.DBManager;
import db.Group;
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

/**
 *
 * @author ANDre1
 */
public class ControllerCambiaAvatarServlet extends HttpServlet {

    private DBManager manager;

    @Override
    public void init() throws ServletException {
        // inizializza il DBManager dagli attributi di Application
        this.manager = (DBManager) super.getServletContext().getAttribute("dbmanager");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        //Inserimento dati registrazione nel DB
        String folder;
        String dirPath;
        folder = "tempavatar";
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

        MultipartRequest multi = new MultipartRequest(request, dirPath, 10 * 1024 * 1024, "ISO-8859-1", new DefaultFileRenamePolicy());



        Enumeration files = multi.getFileNames();
        String fileintero = null;
        while (files.hasMoreElements()) {
            String name = (String) files.nextElement();
            File f = multi.getFile(name);


            fileintero = f.toString();
        }




        Boolean fatto = false;
        try {
            fatto = manager.settaAvatar(request, fileintero);
        } catch (SQLException ex) {
            Logger.getLogger(ControllerGruppoServlet.class.getName()).log(Level.SEVERE, null, ex);
        }

        if (fatto) {
            request.setAttribute("message", "Avatar cambiato con successo!");
        } else {
            request.setAttribute("message", "Avatar non cambiato :(");
        }


        RequestDispatcher rd = request.getRequestDispatcher("/LoginPage.jsp");
        rd.forward(request, response);

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
