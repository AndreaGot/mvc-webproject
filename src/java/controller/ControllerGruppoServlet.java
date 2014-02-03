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
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
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
    List<Group> publicGroups;

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
        HttpSession session = request.getSession(false);
        if (session == null) {

            request.setAttribute("message", "non hai effettuato il login!");
            RequestDispatcher rd = request.getRequestDispatcher("/index.jsp");
            rd.forward(request, response);
        }

        String azione = request.getParameter("azione");

        if (azione.equals("logout")) {


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
                publicGroups = manager.trovaGruppoPubblico(request);
            } catch (SQLException ex) {
                Logger.getLogger(ControllerGruppoServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
            request.setAttribute("listaGruppi", groups);
            request.setAttribute("listaGruppiPubblici", publicGroups);
            //rimando al login

            RequestDispatcher rd = request.getRequestDispatcher("/GroupListPage.jsp");
            rd.forward(request, response);
        } else if (azione.equals("creagruppo")) {
            RequestDispatcher rd = request.getRequestDispatcher("/GroupCreationPage.jsp");
            rd.forward(request, response);
        } else if (azione.equals("confermagruppo")) {


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

            session.setAttribute("view", request.getParameter("view"));
            VisualizzaGruppo(request, response);

        } else if (azione.equals("listapostpubblici")) {

            session.setAttribute("view", request.getParameter("view"));
            VisualizzaGruppoPubblico(request, response);
        } else if (azione.equals("nuovopost")) {
            Boolean inserito = false;

            String str = request.getParameter("contenuto");
            String[] array = new String[500];

            Integer i;
            i = 0;

            for (i = 0; i < 500; i++) {
                array[i] = "";
            }


            Scanner s = new Scanner(str);
            String post = "";
            String link = null;
            i = 0;
            while (s.hasNext()) {
                array[i] = s.next();
                System.out.println(array[i]);
                i++;
            }
            i = 0;
            for (i = 0; i < array.length; i++) {
                if (array[i].equals("")) {
                    break;
                }
                if (array[i].startsWith("$$")) {
                    try {
                        array[i] = array[i].replace("$$", "");
                        link = manager.trovaFileLink(request, array[i]);
                    } catch (SQLException ex) {
                        Logger.getLogger(ControllerGruppoServlet.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    if (link == null) {
                        post = post + " <a href='http://" + array[i] + "'>" + array[i] + "</a>";
                    } else {
                        post = post + " <form action='DownloadController' method='POST'> <input type='hidden' name='link' value='" + link + "'> <input type='submit' value='" + array[i] + "'> </form>";

                    }

                } else if (array[i].startsWith("http://")) {
                    post = post + " <a href='" + array[i] + "'>" + array[i] + "</a>";
                } else if (array[i].startsWith("www.")) {
                    post = post + " <a href='http://" + array[i] + "'>" + array[i] + "</a>";
                } else {
                    post = post + " " + array[i];
                }



            }
            System.out.println(post);

            try {
                inserito = manager.inserisciPost(request, post);
            } catch (SQLException ex) {
                Logger.getLogger(ControllerGruppoServlet.class.getName()).log(Level.SEVERE, null, ex);
            }


            VisualizzaGruppo(request, response);

        } else if (azione.equals("listapostpubblici")) {

            session.setAttribute("view", request.getParameter("view"));
            VisualizzaGruppoPubblico(request, response);
        } else if (azione.equals("cambiacredenziali")) {

            RequestDispatcher rd = request.getRequestDispatcher("/PassAvatarChangePage.jsp");
            rd.forward(request, response);
        } else if (azione.equals("cambiapass")) {

            Boolean fatto = false;
            try {
                fatto = manager.settaPass(request);
            } catch (SQLException ex) {
                Logger.getLogger(ControllerGruppoServlet.class.getName()).log(Level.SEVERE, null, ex);
            }

            if (fatto) {
                request.setAttribute("message", "Password cambiata con successo!");
            } else {
                request.setAttribute("message", "Password non cambiata :(");
            }


            RequestDispatcher rd = request.getRequestDispatcher("/LoginPage.jsp");
            rd.forward(request, response);
        } else if (azione.equals("cambiaavatar")) {
            Boolean fatto = false;
            try {
                fatto = manager.settaAvatar(request);
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

    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    private void VisualizzaGruppo(HttpServletRequest request, HttpServletResponse response) {
        List<Post> posts = null;
        HttpSession session = request.getSession(false);

        session.setAttribute("idgruppo", session.getAttribute("view"));
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

    private void VisualizzaGruppoPubblico(HttpServletRequest request, HttpServletResponse response) {
        List<Post> posts = null;
        HttpSession session = request.getSession(false);

        session.setAttribute("idgruppo", session.getAttribute("view"));
        try {
            posts = manager.trovaPost(request);
        } catch (SQLException ex) {
            Logger.getLogger(ControllerServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        request.setAttribute("listaPost", posts);
        //rimando al login

        RequestDispatcher rd = request.getRequestDispatcher("/GroupPagePublic.jsp");

        try {
            rd.forward(request, response);
        } catch (Exception ex) {
            Logger.getLogger(ControllerGruppoServlet.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
