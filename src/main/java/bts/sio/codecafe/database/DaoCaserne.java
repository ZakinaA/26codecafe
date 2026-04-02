/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bts.sio.codecafe.database;

/*
import static bts.sio.codecafe.database.DaoPompier.requeteSql;
import static bts.sio.codecafe.database.DaoPompier.resultatRequete;
*/
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import bts.sio.codecafe.model.Caserne;

/**
 *
 * @author zakina
 */
public class DaoCaserne {
    
    Connection cnx;
    static PreparedStatement requeteSql = null;
    static ResultSet resultatRequete = null;
    
    public static ArrayList<Caserne> getLesCasernes(Connection cnx){
        
        ArrayList<Caserne> lesCasernes= new ArrayList<Caserne>();
        try{
            requeteSql = cnx.prepareStatement("select * from caserne");
            resultatRequete = requeteSql.executeQuery();
            
            while (resultatRequete.next()){
                
                Caserne c = new Caserne();
                    c.setId(resultatRequete.getInt("id"));
                    c.setNom(resultatRequete.getString("nom"));
                    c.setRue(resultatRequete.getString("rue"));
                    c.setCopos(resultatRequete.getInt("copos"));
                    c.setVille(resultatRequete.getString("ville"));

                lesCasernes.add(c);
            }
           
        }
        catch (SQLException e){
            e.printStackTrace();
            System.out.println("La requête de getLesPompiers e généré une erreur");
        }
        return lesCasernes;
    }
    
    public static Caserne getCaserneById(Connection cnx, int idCaserne){
    
    Caserne uneCaserne = new Caserne();
    try
    {
        requeteSql=cnx.prepareStatement(" select * from caserne" +
                                       "where p.id= ? ");
        
        requeteSql.setInt(1, idCaserne);
        System.out.println(requeteSql);
        resultatRequete=requeteSql.executeQuery();
        
        if ( resultatRequete.next() ) {
            uneCaserne.setId(resultatRequete.getInt("id"));
            uneCaserne.setNom(resultatRequete.getString("nom"));
            uneCaserne.setRue(resultatRequete.getString("rue"));
            uneCaserne.setCopos(resultatRequete.getInt("copos"));
            uneCaserne.setVille(resultatRequete.getString("ville"));
        }
    }
    catch (SQLException e)
    {
        e.printStackTrace();
    }
    return uneCaserne;
    
}
}
