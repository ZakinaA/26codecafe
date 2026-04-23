package bts.sio.codecafe.servlet;

import bts.sio.codecafe.database.DaoSituation;
import bts.sio.codecafe.form.FormSituation;
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
@WebServlet(name = "ServletSituation", urlPatterns = {"/ServletSituation"})
public class ServletSituation extends HttpServlet {

    Connection cnx;
    private static final String ENTITY = "situation";
    private static final String ENTITY_CAPITALIZED = "Situation";
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
            ArrayList<Situation> lesElements = DaoSituation.getLesSituations(cnx, archive);
            ServletUtils.logAction("lister", ENTITY, null, lesElements);
            request.setAttribute(ATTR_LIST, lesElements);
            getServletContext().getRequestDispatcher(VUE_LISTER).forward(request, response);
        }

        if (url.equals(BASE_URL + "consulter")) {
            int idElement = Integer.parseInt(request.getParameter(ENTITY_ID));
            ServletUtils.logAction("consulter", ENTITY, idElement, null);
            Situation element = DaoSituation.getSituationById(cnx, idElement);
            request.setAttribute(ATTR, element);
            getServletContext().getRequestDispatcher(VUE_CONSULTER).forward(request, response);
        }

        if (url.equals(BASE_URL + "ajouter")) {
            this.getServletContext().getRequestDispatcher(VUE_AJOUTER).forward(request, response);
        }

        if (url.equals(BASE_URL + "modifier")) {
            int idElement = Integer.parseInt(request.getParameter(ENTITY_ID));
            ServletUtils.logAction("modifier", ENTITY, idElement, null);
            Situation element = DaoSituation.getSituationById(cnx, idElement);
            request.setAttribute(ATTR, element);
            this.getServletContext().getRequestDispatcher(VUE_MODIFIER).forward(request, response);
        }

        if (url.equals(BASE_URL + "archiver")) {
            int idElement = Integer.parseInt(request.getParameter(ENTITY_ID));
            ServletUtils.logAction("archiver", ENTITY, idElement, null);
            int archive = Integer.parseInt(request.getParameter("archive")); // 0 ou 1

            int resultatToggleArchive = DaoSituation.toggleArchiveSituation(cnx, idElement, archive);
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

        FormSituation form = new FormSituation();

        /* Appel au traitement et à la validation de la requête, et récupération du bean en résultant */
        Situation element = form.remplirSituation(request);

        /* Stockage du formulaire et de l'objet dans l'objet request */
        request.setAttribute("form", form);
        request.setAttribute(ATTR, element);

        String action = request.getParameter("action");

        HttpSession session = request.getSession();

        if (form.getErreurs().isEmpty()) {
            if ("ajouter".equals(action)) {
                Situation insere = DaoSituation.addSituation(cnx, element);
                if (insere != null) {
                    session.setAttribute("pAjoutStatut", "success");
                    response.sendRedirect(REDIRECT_CONSULTER + insere.getId());
                }
            } else if ("modifier".equals(action)) {
                int resultatModif = DaoSituation.updateSituationById(cnx, element);
                if (resultatModif == 1) {
                    session.setAttribute("pModifStatut", "success");
                    Situation modifie = DaoSituation.getSituationById(cnx, element.getId());
                    response.sendRedirect(REDIRECT_CONSULTER + modifie.getId());
                }
            }
        } else {
            if ("ajouter".equals(action)) {
                session.setAttribute("pAjoutStatut", "fail");
                this.getServletContext().getRequestDispatcher(VUE_AJOUTER).forward(request, response);
            } else {
                session.setAttribute("pModifStatut", "fail");
                response.sendRedirect(REDIRECT_MODIFIER + element.getId());
            }
        }
    }
}