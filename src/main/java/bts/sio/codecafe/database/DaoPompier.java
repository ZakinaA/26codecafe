package bts.sio.codecafe.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import bts.sio.codecafe.model.Caserne;
import bts.sio.codecafe.model.Pompier;

public class DaoPompier {

    Connection cnx;
    static PreparedStatement requeteSql = null;
    static ResultSet resultatRequete = null;

    public static ArrayList<Pompier> getLesPompiers(Connection cnx) {

        ArrayList<Pompier> lesPompiers = new ArrayList<Pompier>();
        try {
            requeteSql = cnx.prepareStatement(
                    "SELECT pompier.id as p_id, pompier.nom as p_nom, pompier.prenom as p_prenom, "
                    + "pompier.numero_bip as p_bip, pompier.date_naissance as p_date_naissance, "
                    + "pompier.indice_traitement as p_indice, pompier.date_obtention_indice as p_date_indice, "
                    + "pompier.statut as p_statut, "
                    + "c.id as c_id, c.nom as c_nom "
                    + "FROM pompier INNER JOIN caserne c ON pompier.caserne_id = c.id"
            );
            resultatRequete = requeteSql.executeQuery();

            while (resultatRequete.next()) {
                Pompier p = new Pompier();
                p.setId(resultatRequete.getInt("p_id"));
                p.setNom(resultatRequete.getString("p_nom"));
                p.setPrenom(resultatRequete.getString("p_prenom"));
                p.setNumeroBip(resultatRequete.getString("p_bip"));
                if (resultatRequete.getDate("p_date_naissance") != null) {
                    p.setDateNaissance(resultatRequete.getDate("p_date_naissance").toLocalDate());
                }
                p.setIndiceTraitement(resultatRequete.getString("p_indice"));
                if (resultatRequete.getDate("p_date_indice") != null) {
                    p.setDateObtentionIndice(resultatRequete.getDate("p_date_indice").toLocalDate());
                }
                p.setStatut(resultatRequete.getString("p_statut"));

                Caserne c = new Caserne();
                c.setId(resultatRequete.getInt("c_id"));
                c.setNom(resultatRequete.getString("c_nom"));
                p.setUneCaserne(c);

                // p.setUnGrade(resultatRequete.getString("g_id");
                // p.setUneFonction(resultatRequete.getString("f_id");
                lesPompiers.add(p);
            }

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("La requête de getLesPompiers a généré une erreur");
        }
        return lesPompiers;
    }

    public static Pompier getPompierById(Connection cnx, int idPompier) {

        Pompier p = null;
        try {
            requeteSql = cnx.prepareStatement(
                    "SELECT pompier.id as p_id, pompier.nom as p_nom, pompier.prenom as p_prenom, "
                    + "pompier.numero_bip as p_bip, pompier.date_naissance as p_date_naissance, "
                    + "pompier.indice_traitement as p_indice, pompier.date_obtention_indice as p_date_indice, "
                    + "pompier.statut as p_statut, "
                    + "c.id as c_id, c.nom as c_nom "
                    + "FROM pompier INNER JOIN caserne c ON pompier.caserne_id = c.id "
                    + "WHERE pompier.id = ?"
            );
            requeteSql.setInt(1, idPompier);
            resultatRequete = requeteSql.executeQuery();

            if (resultatRequete.next()) {
                p = new Pompier();
                p.setId(resultatRequete.getInt("p_id"));
                p.setNom(resultatRequete.getString("p_nom"));
                p.setPrenom(resultatRequete.getString("p_prenom"));
                p.setNumeroBip(resultatRequete.getString("p_bip"));
                if (resultatRequete.getDate("p_date_naissance") != null) {
                    p.setDateNaissance(resultatRequete.getDate("p_date_naissance").toLocalDate());
                }
                p.setIndiceTraitement(resultatRequete.getString("p_indice"));
                if (resultatRequete.getDate("p_date_indice") != null) {
                    p.setDateObtentionIndice(resultatRequete.getDate("p_date_indice").toLocalDate());
                }
                p.setStatut(resultatRequete.getString("p_statut"));

                Caserne c = new Caserne();
                c.setId(resultatRequete.getInt("c_id"));
                c.setNom(resultatRequete.getString("c_nom"));
                p.setUneCaserne(c);

                // p.setUnGrade(resultatRequete.getString("g_id");
                // p.setUneFonction(resultatRequete.getString("f_id");
            }

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("La requête de getPompierById a généré une erreur");
        }
        return p;
    }

    public static Pompier addPompier(Connection connection, Pompier p) {
        int idGenere = -1;
        try {
            requeteSql = connection.prepareStatement(
                    "INSERT INTO pompier (nom, prenom, caserne_id) VALUES (?,?,?)",
                    requeteSql.RETURN_GENERATED_KEYS
            );
            requeteSql.setString(1, p.getNom());
            requeteSql.setString(2, p.getPrenom());
            requeteSql.setInt(3, p.getUneCaserne().getId());

            requeteSql.executeUpdate();

            resultatRequete = requeteSql.getGeneratedKeys();
            while (resultatRequete.next()) {
                idGenere = resultatRequete.getInt(1);
                p.setId(idGenere);
                p = DaoPompier.getPompierById(connection, p.getId());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return p;
    }
}
