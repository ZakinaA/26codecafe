package bts.sio.codecafe.servlet;

import bts.sio.codecafe.database.DaoIntervention;
import bts.sio.codecafe.database.DaoSituation;
import bts.sio.codecafe.form.FormIntervention;
import bts.sio.codecafe.model.Intervention;
import bts.sio.codecafe.model.Situation;
import bts.sio.codecafe.utils.MenuBuilder;
import bts.sio.codecafe.utils.ServletUtils;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
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
    // Premier élement
    private static final String ENTITY = "intervention";
    private static final String ENTITY_CAPITALIZED = "Intervention";
    private static final String ENTITY_ID = "id" + ENTITY_CAPITALIZED;
    private static final String BASE_URL = "/26CodeCafe/Servlet" + ENTITY_CAPITALIZED + "/";
    private static final String CHEMIN_VUES = "/vues/" + ENTITY + "/";

    private static final String ATTR = "p" + ENTITY_CAPITALIZED;
    private static final String ATTR_LIST = "pLes" + ENTITY_CAPITALIZED + "s";

    private static final String VUE_LISTER = CHEMIN_VUES + "lister" + ENTITY_CAPITALIZED + "s.jsp";
    private static final String VUE_CONSULTER = CHEMIN_VUES + "consulter" + ENTITY_CAPITALIZED + ".jsp";
    private static final String VUE_AJOUTER = CHEMIN_VUES + "ajouter" + ENTITY_CAPITALIZED + ".jsp";
    private static final String VUE_MODIFIER = CHEMIN_VUES + "modifier" + ENTITY_CAPITALIZED + ".jsp";

    private static final String REDIRECT_CONSULTER = BASE_URL + "consulter?id" + ENTITY_CAPITALIZED + "=";
    private static final String REDIRECT_MODIFIER = BASE_URL + "modifier?id" + ENTITY_CAPITALIZED + "=";

    // Deuxième élément
    private static final String ENTITY2_CAPITALIZED = "Situation";

    private static final String ATTR2_LIST = "pLes" + ENTITY2_CAPITALIZED + "s";

    @Override
    public void init() {
        ServletContext servletContext = getServletContext();
        cnx = (Connection) servletContext.getAttribute("connection");
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
        ServletUtils.logUrl(request);

        request.setAttribute("menu", MenuBuilder.getMenu());
        HttpSession session = request.getSession();

        if (url.equals(BASE_URL + "lister")) {
            Integer archive = ServletUtils.parseArchiveParam(request);
            ArrayList<Intervention> lesElements = DaoIntervention.getLesInterventions(cnx, archive);
            System.out.println("[LISTE] nombre de intervention à lister = " + lesElements.size());
            request.setAttribute(ATTR_LIST, lesElements);
            getServletContext().getRequestDispatcher(VUE_LISTER).forward(request, response);
        }

        if (url.equals(BASE_URL + "consulter")) {
            int idElement = Integer.parseInt(request.getParameter(ENTITY_ID));
            System.out.println("[CONSULTATION] intervention à afficher = " + idElement);
            Intervention element = DaoIntervention.getInterventionById(cnx, idElement);
            request.setAttribute(ATTR, element);
            getServletContext().getRequestDispatcher(VUE_CONSULTER).forward(request, response);
        }

        if (url.equals(BASE_URL + "ajouter")) {
            ArrayList<Situation> lesElements2 = DaoSituation.getLesSituations(cnx, 0);
            request.setAttribute(ATTR2_LIST, lesElements2);
            this.getServletContext().getRequestDispatcher(VUE_AJOUTER).forward(request, response);
        }

        if (url.equals(BASE_URL + "modifier")) {
            int idElement = Integer.parseInt(request.getParameter(ENTITY_ID));
            System.out.println("[MODIFICATION] intervention à afficher = " + idElement);
            Intervention element = DaoIntervention.getInterventionById(cnx, idElement);
            ArrayList<Situation> lesElements2 = DaoSituation.getLesSituations(cnx, 0);
            request.setAttribute(ATTR2_LIST, lesElements2);
            request.setAttribute(ATTR, element);
            this.getServletContext().getRequestDispatcher(VUE_MODIFIER).forward(request, response);
        }

        if (url.equals(BASE_URL + "archiver")) {
            int idElement = Integer.parseInt(request.getParameter(ENTITY_ID));
            System.out.println("[ARCHIVATION] intervention à archiver = " + idElement);
            int archive = Integer.parseInt(request.getParameter("archive")); // 0 ou 1

            int resultatToggleArchive = DaoIntervention.toggleArchiveIntervention(cnx, idElement, archive);
            ServletUtils.setArchiveSession(session, resultatToggleArchive, archive);

            // Si retour est vide ou null, on redirige sans paramètre
            ServletUtils.redirectAvecFiltreArchive(response, BASE_URL + "lister", request);
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
        Intervention element = form.remplirIntervention(request);

        /* Stockage du formulaire et de l'objet dans l'objet request */
        request.setAttribute("form", form);

        String action = request.getParameter("action");

        HttpSession session = request.getSession();

        if (form.getErreurs().isEmpty()) {
            if ("ajouter".equals(action)) {
                Intervention insere = DaoIntervention.addIntervention(cnx, element);
                if (insere != null) {
                    session.setAttribute("pAjoutStatut", "success");
                    response.sendRedirect(REDIRECT_CONSULTER + insere.getId());
                }
            } else if ("modifier".equals(action)) {
                int resultatModif = DaoIntervention.updateInterventionById(cnx, element);
                if (resultatModif == 1) {
                    session.setAttribute("pModifStatut", "success");
                    Intervention modifie = DaoIntervention.getInterventionById(cnx, element.getId());
                    response.sendRedirect(REDIRECT_CONSULTER + modifie.getId());
                }
            }
        } else {
            if ("ajouter".equals(action)) {
                session.setAttribute("pAjoutStatut", "fail");
                this.getServletContext().getRequestDispatcher(VUE_AJOUTER).forward(request, response);
            } else {
                request.setAttribute(ATTR, element);
                session.setAttribute("pModifStatut", "fail");
                ArrayList<Situation> lesElements2 = DaoSituation.getLesSituations(cnx, 0);
                request.setAttribute(ATTR2_LIST, lesElements2);
                response.sendRedirect(REDIRECT_MODIFIER + element.getId());
            }
        }
    }
}