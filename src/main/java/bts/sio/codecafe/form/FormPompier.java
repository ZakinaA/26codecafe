/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bts.sio.codecafe.form;

import jakarta.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;
import bts.sio.codecafe.model.Caserne;
import bts.sio.codecafe.model.Pompier;
import java.time.LocalDate;

/**
 *
 * @author zakina
 */
public class FormPompier {
    
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
    
    public Pompier remplirPompier( HttpServletRequest request ) {
      
        Pompier p  = new Pompier();

        // Hydratation de l'id et de l'archive si c'est pour modifier
        String idStr = getDataForm(request, "id");
        if ( idStr != null && !idStr.isEmpty() ) {
            p.setId(Integer.parseInt(idStr));
        }


        // Hydratation du reste
        String nom = getDataForm( request, "nom" );
        String prenom = getDataForm( request, "prenom" );
        String numeroBip = getDataForm( request, "numeroBip" );
        LocalDate dateNaissance = LocalDate.parse((String)getDataForm( request, "dateNaissance" ));
        String indiceTraitement = getDataForm ( request, "indiceTraitement");
        LocalDate dateObtentionIndice = LocalDate.parse((String)getDataForm( request, "dateObtentionIndice" ));
        int caserne = Integer.parseInt((String)getDataForm( request, "idCaserne" ));

        Caserne c = new Caserne(caserne);

        p.setNom(nom);
        p.setPrenom(prenom);
        p.setNumeroBip(numeroBip);
        p.setDateNaissance(dateNaissance);
        p.setIndiceTraitement(indiceTraitement);
        p.setDateObtentionIndice(dateObtentionIndice);
        p.setUneCaserne(c);

        if ( erreurs.isEmpty() ) {
            resultat = "Succès de l'ajout.";
        } else {
            resultat = "Échec de l'ajout.";
        }
        
        return p ;
    }
    
    
}
