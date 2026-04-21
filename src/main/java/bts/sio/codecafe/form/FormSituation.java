/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bts.sio.codecafe.form;

import bts.sio.codecafe.model.Situation;
import jakarta.servlet.http.HttpServletRequest;

import java.time.LocalTime;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author zakina
 */
public class FormSituation {
    
     private String resultat;
    private Map<String, String> erreurs      = new HashMap<String, String>();

    public String getResultat() {
        return resultat;
    }

    public void setResultat(String resultat) {
        this.resultat = resultat;
    }

    public Map<String, String> getErreurs() {
        return erreurs;
    }

    public void setErreurs(Map<String, String> erreurs) {
        this.erreurs = erreurs;
    }

    private void setErreur( String champ, String message ) {
    erreurs.put(champ, message );
    }    
    
    private static String getDataForm( HttpServletRequest request, String nomChamp ) {
        String valeur = request.getParameter( nomChamp );
        if ( valeur == null || valeur.trim().length() == 0 ) {
            return null;
        } else {
            return valeur.trim();
        }   
    }
    
    public Situation remplirSituation( HttpServletRequest request ) {
      
        Situation s  = new Situation();

        // Hydratation de l'id et de l'archive si c'est pour modifier
        String idStr = getDataForm(request, "id");
        if ( idStr != null && !idStr.isEmpty() ) {
            s.setId(Integer.parseInt(idStr));
        }

        String idArchive = getDataForm(request, "archive");
        if ( idArchive != null && !idArchive.isEmpty() ) {
            s.setArchive(Integer.parseInt(idArchive));
        }

        // Hydratation du reste
        String libelle = getDataForm( request, "libelle" );

        s.setLibelle(libelle);

        if ( erreurs.isEmpty() ) {
            resultat = "Succès de l'ajout.";
        } else {
            resultat = "Échec de l'ajout.";
        }
        
        return s ;
    }
    
    
}
