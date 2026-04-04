package bts.sio.codecafe.database;

import bts.sio.codecafe.model.Situation;

import java.sql.*;
import java.util.ArrayList;

/**
 * Author: JoackimV
 * Created: 04/04/2026 00:42
 * Last modified: 04/04/2026 00:42
 */
public class DaoSituation {

    Connection cnx;
    static PreparedStatement requeteSql = null;
    static ResultSet resultatRequete = null;

    public static ArrayList<Situation> getLesSituations(Connection cnx, Integer archive) {

        ArrayList<Situation> lesSituations = new ArrayList<Situation>();
        try {

            String sql = "select s.id as s_id, s.libelle as s_libelle from situation as s";

            if ( archive != null ) {
                sql += " where archive = ?";
            }

            requeteSql = cnx.prepareStatement(sql);

            if (archive != null) {
                requeteSql.setInt(1, archive);
            }

            resultatRequete = requeteSql.executeQuery();

            while (resultatRequete.next()) {
                Situation s = new Situation();
                mapResultSetToSituation(s);

                lesSituations.add(s);
            }

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("La requête de getLesSituations a généré une erreur");
        }
        return lesSituations;
    }

    public static Situation getSituationById(Connection cnx, int idSituation) {

        Situation s = null;
        try {

            requeteSql = cnx.prepareStatement("select s.id as s_id, s.libelle as s_libelle," +
                    " s.archive as s_archive from situation as s where s.id = ?");
            requeteSql.setInt(1, idSituation);
            resultatRequete = requeteSql.executeQuery();

            if (resultatRequete.next()) {
                s = new Situation();
                mapResultSetToSituation(s);
            }

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("La requête de getSituationById a généré une erreur");
        }
        return s;
    }

    private static void mapResultSetToSituation(Situation s) throws SQLException {
        s.setId(resultatRequete.getInt("s_id"));
        s.setLibelle(resultatRequete.getString("s_libelle"));
    }

    public static Situation addSituation(Connection connection, Situation s) {
        int idGenere = -1;
        try {
            requeteSql = connection.prepareStatement("INSERT INTO situation (libelle, archive)" +
                    " VALUES (?, ?)", requeteSql.RETURN_GENERATED_KEYS);
            setParametersSituation(s);

            requeteSql.executeUpdate();

            resultatRequete = requeteSql.getGeneratedKeys();
            while (resultatRequete.next()) {
                idGenere = resultatRequete.getInt(1);
                s.setId(idGenere);
                s = DaoSituation.getSituationById(connection, s.getId());
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("La requête de addSituation a généré une erreur");
        }
        return s;
    }

    public static int updateSituationById(Connection connection, Situation s) {
        int rs = 0;

        try {
            requeteSql = connection.prepareStatement("UPDATE situation SET libelle = ?, archive = ? " +
                    "WHERE situation.id = ?");
            setParametersSituation(s);
            requeteSql.setInt(3, s.getId());

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
            System.out.println("La requête de updateSituationById a généré une erreur");
        }
        return rs;
    }

    private static void setParametersSituation(Situation s) throws SQLException {
        requeteSql.setString(1, s.getLibelle());
        requeteSql.setInt(2, s.getArchive());
    }
}
