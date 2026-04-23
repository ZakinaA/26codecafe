package bts.sio.codecafe.test;

import bts.sio.codecafe.database.ConnexionBdd;
import bts.sio.codecafe.database.DaoTypeEngin;
import bts.sio.codecafe.model.TypeEngin;

import java.sql.Connection;
import java.util.ArrayList;

/**
 * Author: JoackimV
 * Created: 04/04/2026 01:13
 * Last modified: 04/04/2026 01:13
 */
public class TestDaoTypeEngin {

    public static void main(String[] args) {

        Connection cnx = ConnexionBdd.ouvrirConnexion();

        // =========================
        // TEST 1 : LISTER TOUTES LES TYPES_ENGIN
        // =========================
        System.out.println("=== LISTE DES TYPES_ENGIN (toutes) ===");
        ArrayList<TypeEngin> situations = DaoTypeEngin.getLesTypesEngin(cnx, null);
        System.out.println("Nombre de situations = " + situations.size());

        for (TypeEngin s : situations) {
            System.out.println(s.getId() + " - " + s.getLibelle());
        }

        // =========================
        // TEST 2 : FILTRER NON ARCHIVÉES
        // =========================
        System.out.println("\n=== TYPES_ENGIN NON ARCHIVÉES ===");
        ArrayList<TypeEngin> nonArchivees = DaoTypeEngin.getLesTypesEngin(cnx, 0);

        for (TypeEngin s : nonArchivees) {
            System.out.println(s.getId() + " - " + s.getLibelle());
        }

        // =========================
        // TEST 3 : AJOUT
        // =========================
        System.out.println("\n=== AJOUT TYPE_ENGIN ===");
        TypeEngin newTypeEngin = new TypeEngin();
        newTypeEngin.setLibelle("Test situation");
        newTypeEngin.setArchive(0);

        TypeEngin inserted = DaoTypeEngin.addTypeEngin(cnx, newTypeEngin);

        if (inserted != null) {
            System.out.println("Ajout OK id = " + inserted.getId());
        } else {
            System.out.println("Erreur lors de l'ajout");
        }

        // =========================
        // TEST 4 : CONSULTATION PAR ID
        // =========================
        System.out.println("\n=== CONSULTATION PAR ID ===");
        TypeEngin s = DaoTypeEngin.getTypeEnginById(cnx, inserted.getId());

        if (s != null) {
            System.out.println("TypeEngin trouvée : " + s.getLibelle());
        } else {
            System.out.println("TypeEngin non trouvée");
        }

        // =========================
        // TEST 5 : UPDATE
        // =========================
        System.out.println("\n=== UPDATE ===");
        s.setLibelle("TypeEngin modifiée");
        s.setArchive(1);

        int rs = DaoTypeEngin.updateTypeEnginById(cnx, s);

        if (rs == 1) {
            System.out.println("Update OK");
        } else {
            System.out.println("Update échoué");
        }

        // Vérification après update
        TypeEngin updated = DaoTypeEngin.getTypeEnginById(cnx, s.getId());
        System.out.println("Après update : " + updated.getLibelle() + " archive=" + updated.getArchive());
    }
}