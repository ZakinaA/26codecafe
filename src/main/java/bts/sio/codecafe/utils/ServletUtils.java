package bts.sio.codecafe.utils;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Author: JoackimV
 * Created: 22/04/2026 21:33
 * Last modified: 22/04/2026 21:33
 */
public class ServletUtils {

    /**
     * Parse le paramètre archive depuis la requête.
     * Retourne null si absent ou vide.
     */
    public static Integer parseArchiveParam(HttpServletRequest request) {
        String archiveParam = request.getParameter("archive");
        if (archiveParam != null && !archiveParam.isEmpty()) {
            return Integer.parseInt(archiveParam);
        }
        return null;
    }

    /**
     * Redirige vers la liste en conservant le filtre archive si présent.
     */
    public static void redirectAvecFiltreArchive(HttpServletResponse response,
                                                 String baseUrl, HttpServletRequest request)
            throws IOException {
        String retour = request.getParameter("retour");
        if (retour == null || retour.isEmpty()) {
            response.sendRedirect(baseUrl);
        } else {
            response.sendRedirect(baseUrl + "?archive=" + retour);
        }
    }

    /**
     * Stocke le statut et l'action d'archivage en session.
     */
    public static void setArchiveSession(HttpSession session,
                                         int resultat, int archive) {
        String archiveStatut = resultat == 1 ? "success" : "fail";
        String actionLibelle = archive == 1 ? "Archivage" : "Désarchivage";
        session.setAttribute("pArchiveStatut", archiveStatut);
        session.setAttribute("pArchiveAction", actionLibelle);
    }

    /**
     * Log l'URL de la requête entrante.
     */
    public static void logUrl(HttpServletRequest request) {
        System.out.println("Passage dans la Servlet avec l'URL : " + request.getRequestURI());
    }

    /**
     * Log de l'action qui est demandé au serveur
     */
    public static void logAction(String typeAction, String entity, Integer idElement, ArrayList<?> lesElements) {
        switch (typeAction) {
            case "lister":
                System.out.println("[LISTE] nombre de " + entity + " lister = " + lesElements.size());
                break;
            case "consulter":
                System.out.println("[CONSULTATION] " + entity + " afficher = " + idElement);
                break;
            case "modifier":
                System.out.println("[MODIFICATION] " + entity + " afficher = " + idElement);
                break;
            case "archiver":
                System.out.println("[ARCHIVATION] " + entity + " archiver = " + idElement);
                break;
            default:
                System.out.println("[INCONNU] action inconnue = " + typeAction);
                break;
        }
    }
}
