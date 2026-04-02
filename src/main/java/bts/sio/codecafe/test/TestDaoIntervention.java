package bts.sio.codecafe.test;

import bts.sio.codecafe.database.ConnexionBdd;
import bts.sio.codecafe.database.DaoIntervention;
import bts.sio.codecafe.model.Intervention;

import java.sql.Connection;
import java.time.LocalTime;

/**
 * Author: JoackimV
 * Created: 02/04/2026 19:14
 * Last modified: 02/04/2026 19:14
 */
public class TestDaoIntervention {
    public static void main(String[] args) {

        Connection cnx = ConnexionBdd.ouvrirConnexion();

        // Test : récupérer toutes les interventions
        System.out.println("Nombre d'interventions = "
                + DaoIntervention.getLesInterventions(cnx).size());

        // Test : récupérer une intervention par ID
        Intervention inter = DaoIntervention.getInterventionById(cnx, 1);
        if (inter != null) {
            System.out.println("Intervention 1 : ville = " + inter.getVille());
        } else {
            System.out.println("Aucune intervention trouvée avec l'id 1");
        }

        // Test : ajouter une intervention
        Intervention newInter = new Intervention();
        newInter.setRue("Rue de Paris");
        newInter.setCopos("76600");
        newInter.setVille("Le Havre");
        newInter.setHeureAppel(LocalTime.of(10, 30));
        newInter.setHeureArrivee(LocalTime.of(10, 45));
        newInter.setDuree(15);
        newInter.setArchive(0);

        newInter = DaoIntervention.addIntervention(cnx, newInter);

        System.out.println("Nouvelle intervention ajoutée avec id = "
                + newInter.getId());
    }
}
