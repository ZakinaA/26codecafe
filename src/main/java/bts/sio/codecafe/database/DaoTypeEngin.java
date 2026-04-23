package bts.sio.codecafe.database;

import bts.sio.codecafe.model.TypeEngin;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Author: JoackimV
 * Created: 23/04/2026 14:33
 * Last modified: 23/04/2026 14:33
 */
public class DaoTypeEngin {

    Connection cnx;
    static PreparedStatement requeteSql = null;
    static ResultSet resultatRequete = null;

    public static ArrayList<TypeEngin> getLesTypesEngin(Connection cnx, Integer archive) {

        ArrayList<TypeEngin> lesTypesEngin = new ArrayList<TypeEngin>();
        try {

            String sql = "select s.id as s_id, s.libelle as s_libelle, s.archive as s_archive from type_engin as s";

            if ( archive != null ) {
                sql += " where archive = ?";
            }

            requeteSql = cnx.prepareStatement(sql);

            if (archive != null) {
                requeteSql.setInt(1, archive);
            }

            resultatRequete = requeteSql.executeQuery();

            while (resultatRequete.next()) {
                TypeEngin s = new TypeEngin();
                mapResultSetToTypeEngin(s);

                lesTypesEngin.add(s);
            }

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("La requête de getLesTypesEngin a généré une erreur");
        }
        return lesTypesEngin;
    }

    public static TypeEngin getTypeEnginById(Connection cnx, int idTypeEngin) {

        TypeEngin s = null;
        try {

            requeteSql = cnx.prepareStatement("select s.id as s_id, s.libelle as s_libelle," +
                    " s.archive as s_archive from type_engin as s where s.id = ?");
            requeteSql.setInt(1, idTypeEngin);
            resultatRequete = requeteSql.executeQuery();

            if (resultatRequete.next()) {
                s = new TypeEngin();
                mapResultSetToTypeEngin(s);
            }

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("La requête de getTypeEnginById a généré une erreur");
        }
        return s;
    }

    private static void mapResultSetToTypeEngin(TypeEngin s) throws SQLException {
        s.setId(resultatRequete.getInt("s_id"));
        s.setLibelle(resultatRequete.getString("s_libelle"));
        s.setArchive(resultatRequete.getInt("s_archive"));
    }

    public static TypeEngin addTypeEngin(Connection connection, TypeEngin s) {
        int idGenere = -1;
        try {
            requeteSql = connection.prepareStatement("INSERT INTO type_engin (libelle, archive)" +
                    " VALUES (?, ?)", requeteSql.RETURN_GENERATED_KEYS);
            setParametersTypeEngin(s);

            requeteSql.executeUpdate();

            resultatRequete = requeteSql.getGeneratedKeys();
            while (resultatRequete.next()) {
                idGenere = resultatRequete.getInt(1);
                s.setId(idGenere);
                s = DaoTypeEngin.getTypeEnginById(connection, s.getId());
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("La requête de addTypeEngin a généré une erreur");
        }
        return s;
    }

    public static int updateTypeEnginById(Connection connection, TypeEngin s) {
        int rs = 0;

        try {
            requeteSql = connection.prepareStatement("UPDATE type_engin SET libelle = ?, archive = ? " +
                    "WHERE type_engin.id = ?");
            setParametersTypeEngin(s);
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
            System.out.println("La requête de updateTypeEnginById a généré une erreur");
        }
        return rs;
    }

    private static void setParametersTypeEngin(TypeEngin s) throws SQLException {
        requeteSql.setString(1, s.getLibelle());
        requeteSql.setInt(2, s.getArchive());
    }

    public static int toggleArchiveTypeEngin(Connection connection, int idTypeEngin, int archive) {
        int rs = 0;
        try {
            requeteSql = connection.prepareStatement(
                    "UPDATE type_engin SET archive = ? WHERE id = ?"
            );

            requeteSql.setInt(1, archive);
            requeteSql.setInt(2, idTypeEngin);

            rs = requeteSql.executeUpdate();

            if (rs == 1) {
                System.out.println("Archivage OK");
            } else if (rs == 0) {
                System.out.println("Aucune ligne archivée");
            } else {
                System.out.println("Attention : plusieurs lignes archivées !");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("La requête de toggleArchiveTypeEngin a généré une erreur");
        }
        return rs;
    }
}
