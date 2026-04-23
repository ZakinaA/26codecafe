package bts.sio.codecafe.utils;

/**
 * Author: JoackimV
 * Created: 03/04/2026 11:11
 * Last modified: 03/04/2026 11:11
 */
import java.util.LinkedHashMap;
import java.util.Map;

public class MenuBuilder {

    public static Map<String, Map<String, String>> getMenu() {

        Map<String, Map<String, String>> menu = new LinkedHashMap<>();

        // Intervention
        Map<String, String> interventionMenu = new LinkedHashMap<>();
        interventionMenu.put("Lister", "lister");
        interventionMenu.put("Ajouter", "ajouter");
        menu.put("ServletIntervention", interventionMenu);

        // Caserne
        Map<String, String> caserneMenu = new LinkedHashMap<>();
        caserneMenu.put("Lister", "lister");
        caserneMenu.put("Ajouter", "ajouter");
        menu.put("ServletCaserne", caserneMenu);

        // Pompier
        Map<String, String> pompierMenu = new LinkedHashMap<>();
        pompierMenu.put("Lister", "lister");
        pompierMenu.put("Ajouter", "ajouter");
        menu.put("ServletPompier", pompierMenu);

        // Situation
        Map<String, String> situationMenu = new LinkedHashMap<>();
        situationMenu.put("Lister", "lister");
        situationMenu.put("Ajouter", "ajouter");
        menu.put("ServletSituation", situationMenu);

        // TypeEngin
        Map<String, String> typeEnginMenu = new LinkedHashMap<>();
        typeEnginMenu.put("Lister", "lister");
        typeEnginMenu.put("Ajouter", "ajouter");
        menu.put("ServletTypeEngin", typeEnginMenu);

        return menu;
    }
}