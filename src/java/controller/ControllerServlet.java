/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import db.DBManager;
import db.Group;
import db.Invito;
import db.ModClass;
import db.User;
import java.io.IOException;
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
import java.util.Properties;
 
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
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

            List<Invito> inviti = new ArrayList<Invito>();
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


                session.setAttribute("lastLogin", user.lastLogin);
                session.setAttribute("user", user.nome_completo);
                session.setAttribute("userid", user.id);
                session.setAttribute("mod", user.moder);

                try {
                    manager.cambiaData(request);
                    inviti = manager.trovaInvito(request);
                } catch (SQLException ex) {
                    Logger.getLogger(ControllerServlet.class.getName()).log(Level.SEVERE, null, ex);
                }
                request.setAttribute("utente", user);
                session.setAttribute("listaInviti", inviti);

                RequestDispatcher rd = request.getRequestDispatcher("/LoginPage.jsp");
                rd.forward(request, response);

            }
        } else if (azione.equals("recupera")) {
            view = request.getRequestDispatcher("ForgotPassword.jsp");
            view.forward(request, response);
        } else if (azione.equals("inviamail")) {

            User userFound = null;

            final String username = "scigotunitn@gmail.com";
            final String password = "scigotunitn1";
            
            try {
                userFound = manager.trovaMailUtente(request);
            } catch (SQLException ex) {
                Logger.getLogger(ControllerServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "587");
 
		Session session2 = Session.getInstance(props,
		  new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username, password);
			}
		  });
 
		try {
 
			Message message = new MimeMessage(session2);
			message.setFrom(new InternetAddress("scigotunitn@gmail.com"));
			message.setRecipients(Message.RecipientType.TO,
				InternetAddress.parse(userFound.getEmail()));
			message.setSubject("Prova");
			message.setText("Caro " + userFound.getName() + ", la tua password e' " + userFound.getPassword());
 
			Transport.send(message);
 
			System.out.println("Done");
 
		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}
                
                request.setAttribute("message", "Password inviata alla tua mail! controlla la posta in arrivo!");
                //rimando al login

                RequestDispatcher rd = request.getRequestDispatcher("/index.jsp");
                rd.forward(request, response);
	}
        
        else if (azione.equals("modera"))
        {
            List<ModClass> table = null;
            try {
                table = manager.TabellaMod(request);
            } catch (SQLException ex) {
                Logger.getLogger(ControllerServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            request.setAttribute("modtable", table);
            
            RequestDispatcher rd = request.getRequestDispatcher("/ModerationPage.jsp");
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
