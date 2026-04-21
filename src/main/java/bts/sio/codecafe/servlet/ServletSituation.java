package bts.sio.codecafe.servlet;

import bts.sio.codecafe.database.*;
import bts.sio.codecafe.database.DaoSituation;
import bts.sio.codecafe.database.DaoSituation;
import bts.sio.codecafe.form.FormSituation;
import bts.sio.codecafe.model.Caserne;
import bts.sio.codecafe.model.Situation;
import bts.sio.codecafe.model.Situation;
import bts.sio.codecafe.model.Situation;
import bts.sio.codecafe.utils.MenuBuilder;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.util.ArrayList;

/**
 * Author: JoackimV
 * Created: 02/04/2026 19:32
 * Last modified: 02/04/2026 19:32
 */
@WebServlet(name = "ServletSituation", urlPatterns = {"/ServletSituation"})
public class ServletSituation extends HttpServlet {

    Connection cnx;

    @Override
    public void init() {
        ServletContext servletContext = getServletContext();
        cnx = (Connection) servletContext.getAttribute("connection");
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet ServletSituation</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ServletSituation at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">

    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request  servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException      if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String url = request.getRequestURI();
        System.out.println("Passage dans la Servlet avec l'URL : " + request.getRequestURI());

        request.setAttribute("menu", MenuBuilder.getMenu());

        if (url.equals("/26CodeCafe/ServletSituation/lister")) {
            String archiveParam = request.getParameter("archive");
            Integer archive = null;

            if (archiveParam != null && !archiveParam.isEmpty()) {
                archive = Integer.parseInt(archiveParam);
            }
            ArrayList<Situation> lesSituations = DaoSituation.getLesSituations(cnx, archive);
            request.setAttribute("pLesSituations", lesSituations);
            getServletContext().getRequestDispatcher("/vues/situation/listerSituations.jsp").forward(request, response);
        }

        if (url.equals("/26CodeCafe/ServletSituation/consulter")) {
            int idSituation = Integer.parseInt((String) request.getParameter("idSituation"));
            System.out.println("situation à afficher = " + idSituation);
            Situation c = DaoSituation.getSituationById(cnx, idSituation);
            request.setAttribute("pSituation", c);
            getServletContext().getRequestDispatcher("/vues/situation/consulterSituation.jsp").forward(request, response);
        }

        if(url.equals("/26CodeCafe/ServletSituation/ajouter")) {
            ArrayList<Situation> lesSituations = DaoSituation.getLesSituations(cnx, 0);
            request.setAttribute("pLesSituations", lesSituations);
            this.getServletContext().getRequestDispatcher("/vues/situation/ajouterSituation.jsp" ).forward( request, response );
        }

        if(url.equals("/26CodeCafe/ServletSituation/modifier")) {
            int idSituation = Integer.parseInt((String) request.getParameter("idSituation"));
            System.out.println("situation à afficher = " + idSituation);
            Situation s = DaoSituation.getSituationById(cnx, idSituation);
            request.setAttribute("pSituation", s);
            this.getServletContext().getRequestDispatcher("/vues/situation/modifierSituation.jsp" ).forward( request, response );
        }
        
        if (url.equals("/26CodeCafe/ServletSituation/archiver")) {
            int idSituation = Integer.parseInt(request.getParameter("idSituation"));
            int archive = Integer.parseInt(request.getParameter("archive")); // 0 ou 1

            int resultatToggleArchive = DaoSituation.toggleArchiveSituation(cnx, idSituation, archive);
            String archiveStatut = resultatToggleArchive == 1 ? "success" : "fail";
            String actionLibelle = archive == 1 ? "Archivage" : "Désarchivage";
            HttpSession session = request.getSession();
            session.setAttribute("pArchiveStatut", archiveStatut);
            session.setAttribute("pArchiveAction", actionLibelle);

            // Rediriger vers la liste en conservant le filtre courant
            String retour = request.getParameter("retour");
            // Si retour est vide ou null, on redirige sans paramètre
            if (retour == null || retour.isEmpty()) {
                response.sendRedirect("/26CodeCafe/ServletSituation/lister");
            } else {
                response.sendRedirect("/26CodeCafe/ServletSituation/lister?archive=" + retour);
            }
        }
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request  servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException      if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        FormSituation form = new FormSituation();

        /* Appel au traitement et à la validation de la requête, et récupération du bean en résultant */
        Situation s = form.remplirSituation(request);

        /* Stockage du formulaire et de l'objet dans l'objet request */
        request.setAttribute("form", form);
        request.setAttribute("pSituation", s);

        String action = request.getParameter("action");

        if (form.getErreurs().isEmpty()) {
            if ( "ajouter".equals(action) ) {
                Situation situationInsere = DaoSituation.addSituation(cnx, s);
                if (situationInsere != null) {
                    response.sendRedirect("/26CodeCafe/ServletSituation/consulter?idSituation=" + situationInsere.getId());
                } else {
                    // Cas oùl'insertion en bdd a échoué
                    //renvoyer vers une page d'erreur
                }
            } else if ( "modifier".equals(action) ) {
                int resultatModif = DaoSituation.updateSituationById(cnx, s);
                if ( resultatModif == 1 ) {
                    Situation situationModifie = DaoSituation.getSituationById(cnx, s.getId());
                    response.sendRedirect("/26CodeCafe/ServletSituation/consulter?idSituation=" + situationModifie.getId());
                } else {

                }
            } else {
                // il y a des erreurs. On réaffiche le formulaire avec des messages d'erreurs
                if ( "ajouter".equals(action) ) {

                    this.getServletContext().getRequestDispatcher("/vues/situation/ajouterSituation.jsp").forward(request, response);
                } else {
                    request.setAttribute("pSituation", s);
                    response.sendRedirect("/26CodeCafe/ServletSituation/modifier?idSituation=" + s.getId());
                }
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
    }// </e// ditor-fold>
}