package bts.sio.codecafe.servlet;

import bts.sio.codecafe.database.DaoCaserne;
import bts.sio.codecafe.database.DaoIntervention;
import bts.sio.codecafe.database.DaoIntervention;
import bts.sio.codecafe.database.DaoSituation;
import bts.sio.codecafe.form.FormIntervention;
import bts.sio.codecafe.model.Caserne;
import bts.sio.codecafe.model.Intervention;
import bts.sio.codecafe.model.Intervention;
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
@WebServlet(name = "ServletIntervention", urlPatterns = {"/ServletIntervention"})
public class ServletIntervention extends HttpServlet {

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
            out.println("<title>Servlet ServletIntervention</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ServletIntervention at " + request.getContextPath() + "</h1>");
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

        if (url.equals("/26CodeCafe/ServletIntervention/lister")) {
            String archiveParam = request.getParameter("archive");
            Integer archive = null;

            if (archiveParam != null && !archiveParam.isEmpty()) {
                archive = Integer.parseInt(archiveParam);
            }
            ArrayList<Intervention> lesInterventions = DaoIntervention.getLesInterventions(cnx, archive);
            request.setAttribute("pLesInterventions", lesInterventions);
            getServletContext().getRequestDispatcher("/vues/intervention/listerInterventions.jsp").forward(request, response);
        }

        if (url.equals("/26CodeCafe/ServletIntervention/consulter")) {
            int idIntervention = Integer.parseInt((String) request.getParameter("idIntervention"));
            System.out.println("intervention à afficher = " + idIntervention);
            Intervention c = DaoIntervention.getInterventionById(cnx, idIntervention);
            request.setAttribute("pIntervention", c);
            getServletContext().getRequestDispatcher("/vues/intervention/consulterIntervention.jsp").forward(request, response);
        }

        if (url.equals("/26CodeCafe/ServletIntervention/ajouter")) {
            ArrayList<Situation> lesSituations = DaoSituation.getLesSituations(cnx, 0);
            request.setAttribute("pLesSituations", lesSituations);
            this.getServletContext().getRequestDispatcher("/vues/intervention/ajouterIntervention.jsp").forward(request, response);
        }

        if (url.equals("/26CodeCafe/ServletIntervention/modifier")) {
            int idIntervention = Integer.parseInt((String) request.getParameter("idIntervention"));
            System.out.println("intervention à afficher = " + idIntervention);
            Intervention i = DaoIntervention.getInterventionById(cnx, idIntervention);
            request.setAttribute("pIntervention", i);
            ArrayList<Situation> lesSituations = DaoSituation.getLesSituations(cnx, 0);
            request.setAttribute("pLesSituations", lesSituations);
            this.getServletContext().getRequestDispatcher("/vues/intervention/modifierIntervention.jsp").forward(request, response);
        }

        if (url.equals("/26CodeCafe/ServletIntervention/archiver")) {
            int idIntervention = Integer.parseInt(request.getParameter("idIntervention"));
            int archive = Integer.parseInt(request.getParameter("archive")); // 0 ou 1

            int resultatToggleArchive = DaoIntervention.toggleArchiveIntervention(cnx, idIntervention, archive);
            String archiveStatut = resultatToggleArchive == 1 ? "success" : "fail";
            String actionLibelle = archive == 1 ? "Archivage" : "Désarchivage";
            HttpSession session = request.getSession();
            session.setAttribute("pArchiveStatut", archiveStatut);
            session.setAttribute("pArchiveAction", actionLibelle);

            // Rediriger vers la liste en conservant le filtre courant
            String retour = request.getParameter("retour");
            // Si retour est vide ou null, on redirige sans paramètre
            if (retour == null || retour.isEmpty()) {
                response.sendRedirect("/26CodeCafe/ServletIntervention/lister");
            } else {
                response.sendRedirect("/26CodeCafe/ServletIntervention/lister?archive=" + retour);
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

        FormIntervention form = new FormIntervention();

        /* Appel au traitement et à la validation de la requête, et récupération du bean en résultant */
        Intervention i = form.remplirIntervention(request);

        /* Stockage du formulaire et de l'objet dans l'objet request */
        request.setAttribute("form", form);
        request.setAttribute("pIntervention", i);

        String action = request.getParameter("action");

        if (form.getErreurs().isEmpty()) {
            ArrayList<Situation> lesSituations = DaoSituation.getLesSituations(cnx, 0);
            HttpSession session = request.getSession();
            if ( "ajouter".equals(action) ) {
                Intervention interventionInsere = DaoIntervention.addIntervention(cnx, i);
                if (interventionInsere != null) {
                    session.setAttribute("pAjoutStatut", "success");
                    response.sendRedirect("/26CodeCafe/ServletIntervention/consulter?idIntervention=" + interventionInsere.getId());
                } else {
                    // Cas oùl'insertion en bdd a échoué
                    //renvoyer vers une page d'erreur
                }
            } else if ( "modifier".equals(action) ) {
                int resultatModif = DaoIntervention.updateInterventionById(cnx, i);
                if ( resultatModif == 1 ) {
                    session.setAttribute("pModifStatut", "success");
                    Intervention interventionModifie = DaoIntervention.getInterventionById(cnx, i.getId());
                    response.sendRedirect("/26CodeCafe/ServletIntervention/consulter?idIntervention=" + interventionModifie.getId());
                } else {

                }
            } else {
                // il y a des erreurs. On réaffiche le formulaire avec des messages d'erreurs
                request.setAttribute("pLesSituations", lesSituations);
                if ( "ajouter".equals(action) ) {
                    request.setAttribute("pLesSituations", lesSituations);

                    this.getServletContext().getRequestDispatcher("/vues/intervention/ajouterIntervention.jsp").forward(request, response);
                } else {
                    request.setAttribute("pIntervention", i);
                    request.setAttribute("pLesSituations", lesSituations);
                    response.sendRedirect("/26CodeCafe/ServletIntervention/modifier?idIntervention=" + i.getId());
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