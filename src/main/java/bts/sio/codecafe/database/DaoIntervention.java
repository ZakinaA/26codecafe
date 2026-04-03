package bts.sio.codecafe.database;

import bts.sio.codecafe.model.Intervention;

import java.sql.*;
import java.util.ArrayList;

/**
 * Author: JoackimV
 * Created: 02/04/2026 18:44
 * Last modified: 02/04/2026 18:44
 */
public class DaoIntervention {

    Connection cnx;
    static PreparedStatement requeteSql = null;
    static ResultSet resultatRequete = null;

    public static ArrayList<Intervention> getLesInterventions(Connection cnx, Integer archive) {

        ArrayList<Intervention> lesInterventions = new ArrayList<Intervention>();
        try {

            String sql = "select i.id as i_id, i.rue as i_rue, i.copos as i_copos," +
                    " i.ville as i_ville, i.heure_appel as i_heure_appel, i.heure_arrivee as i_heure_arrivee," +
                    " i.duree as i_duree, i.archive as i_archive from intervention as i";

            if ( archive != null ) {
                sql += " where archive = ?";
            }

            requeteSql = cnx.prepareStatement(sql);

            if (archive != null) {
                requeteSql.setInt(1, archive);
            }

            resultatRequete = requeteSql.executeQuery();

            while (resultatRequete.next()) {
                Intervention i = new Intervention();
                mapResultSetToIntervention(i);

                lesInterventions.add(i);
            }

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("La requête de getLesInterventions a généré une erreur");
        }
        return lesInterventions;
    }

    public static Intervention getInterventionById(Connection cnx, int idIntervention) {

        Intervention i = null;
        try {

            requeteSql = cnx.prepareStatement("select i.id as i_id, i.rue as i_rue, i.copos as i_copos," +
                    " i.ville as i_ville, i.heure_appel as i_heure_appel, i.heure_arrivee as i_heure_arrivee," +
                    " i.duree as i_duree, i.archive as i_archive from intervention as i where i.id = ?");
            requeteSql.setInt(1, idIntervention);
            resultatRequete = requeteSql.executeQuery();

            if (resultatRequete.next()) {
                i = new Intervention();
                mapResultSetToIntervention(i);
            }

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("La requête de getInterventionById a généré une erreur");
        }
        return i;
    }

    private static void mapResultSetToIntervention(Intervention i) throws SQLException {
        i.setId(resultatRequete.getInt("i_id"));
        i.setRue(resultatRequete.getString("i_rue"));
        i.setCopos(resultatRequete.getString("i_copos"));
        i.setVille(resultatRequete.getString("i_ville"));
        i.setHeureAppel(resultatRequete.getTime("i_heure_appel").toLocalTime());
        i.setHeureArrivee(resultatRequete.getTime("i_heure_arrivee").toLocalTime());
        i.setDuree(resultatRequete.getInt("i_duree"));
        i.setArchive(resultatRequete.getInt("i_archive"));
    }

    public static Intervention addIntervention(Connection connection, Intervention i) {
        int idGenere = -1;
        try {
            requeteSql = connection.prepareStatement("INSERT INTO intervention (rue, copos, ville, heure_appel, heure_arrivee, duree, archive)" +
                    " VALUES (?, ?, ?, ?, ?, ?, ?)", requeteSql.RETURN_GENERATED_KEYS);
            setParametersIntervention(i);

            requeteSql.executeUpdate();

            resultatRequete = requeteSql.getGeneratedKeys();
            while (resultatRequete.next()) {
                idGenere = resultatRequete.getInt(1);
                i.setId(idGenere);
                i = DaoIntervention.getInterventionById(connection, i.getId());
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("La requête de addIntervention a généré une erreur");
        }
        return i;
    }

    public static int updateInterventionById(Connection connection, Intervention i) {
        int rs = 0;

        try {
            requeteSql = connection.prepareStatement("UPDATE intervention SET rue = ?, copos = ?," +
                    " ville = ?, heure_appel = ?, heure_arrivee = ?, duree = ?, archive = ? " +
                    "WHERE intervention.id = ?");
            setParametersIntervention(i);
            requeteSql.setInt(8, i.getId());

            rs = requeteSql.executeUpdate();

            if (rs == 1) {
                System.out.println("Mise à jour OK");
            } else if (rs == 0) {
                System.out.println("Aucune ligne mise à jour");
            } else {
                System.out.println("Attention : plusieurs lignes modifiées !");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("La requête de updateInterventionById a généré une erreur");
        }
        return rs;
    }

    private static void setParametersIntervention(Intervention i) throws SQLException {
        requeteSql.setString(1, i.getRue());
        requeteSql.setString(2, i.getCopos());
        requeteSql.setString(3, i.getVille());
        requeteSql.setTime(4, Time.valueOf(i.getHeureAppel()));
        requeteSql.setTime(5, Time.valueOf(i.getHeureArrivee()));
        requeteSql.setInt(6, i.getDuree());
        requeteSql.setInt(7, i.getArchive());
    }
}
