package bts.sio.codecafe.test;

import bts.sio.codecafe.database.ConnexionBdd;
import bts.sio.codecafe.database.DaoSituation;
import bts.sio.codecafe.model.Situation;

import java.sql.Connection;
import java.util.ArrayList;

/**
 * Author: JoackimV
 * Created: 04/04/2026 01:13
 * Last modified: 04/04/2026 01:13
 */
public class TestDaoSituation {

    public static void main(String[] args) {

        Connection cnx = ConnexionBdd.ouvrirConnexion();

        // =========================
        // TEST 1 : LISTER TOUTES LES SITUATIONS
        // =========================
        System.out.println("=== LISTE DES SITUATIONS (toutes) ===");
        ArrayList<Situation> situations = DaoSituation.getLesSituations(cnx, null);
        System.out.println("Nombre de situations = " + situations.size());

        for (Situation s : situations) {
            System.out.println(s.getId() + " - " + s.getLibelle());
        }

        // =========================
        // TEST 2 : FILTRER NON ARCHIVÉES
        // =========================
        System.out.println("\n=== SITUATIONS NON ARCHIVÉES ===");
        ArrayList<Situation> nonArchivees = DaoSituation.getLesSituations(cnx, 0);

        for (Situation s : nonArchivees) {
            System.out.println(s.getId() + " - " + s.getLibelle());
        }

        // =========================
        // TEST 3 : AJOUT
        // =========================
        System.out.println("\n=== AJOUT SITUATION ===");
        Situation newSituation = new Situation();
        newSituation.setLibelle("Test situation");
        newSituation.setArchive(0);

        Situation inserted = DaoSituation.addSituation(cnx, newSituation);

        if (inserted != null) {
            System.out.println("Ajout OK id = " + inserted.getId());
        } else {
            System.out.println("Erreur lors de l'ajout");
        }

        // =========================
        // TEST 4 : CONSULTATION PAR ID
        // =========================
        System.out.println("\n=== CONSULTATION PAR ID ===");
        Situation s = DaoSituation.getSituationById(cnx, inserted.getId());

        if (s != null) {
            System.out.println("Situation trouvée : " + s.getLibelle());
        } else {
            System.out.println("Situation non trouvée");
        }

        // =========================
        // TEST 5 : UPDATE
        // =========================
        System.out.println("\n=== UPDATE ===");
        s.setLibelle("Situation modifiée");
        s.setArchive(1);

        int rs = DaoSituation.updateSituationById(cnx, s);

        if (rs == 1) {
            System.out.println("Update OK");
        } else {
            System.out.println("Update échoué");
        }

        // Vérification après update
        Situation updated = DaoSituation.getSituationById(cnx, s.getId());
        System.out.println("Après update : " + updated.getLibelle() + " archive=" + updated.getArchive());
    }
}