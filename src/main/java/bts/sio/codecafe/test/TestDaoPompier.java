/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bts.sio.codecafe.test;

import bts.sio.codecafe.database.ConnexionBdd;
import bts.sio.codecafe.database.DaoPompier;
import java.sql.Connection;
import bts.sio.codecafe.model.Caserne;
import bts.sio.codecafe.model.Pompier;

/**
 *
 * @author zakina
 */
public class TestDaoPompier {
    
    public static void main (String args[]){
        
        Connection cnx = ConnexionBdd.ouvrirConnexion();
        System.out.println ("nombre de pomipiers=" + DaoPompier.getLesPompiers(cnx).size());
        
        System.out.println ("Le pompier 1 s'appelle =" + DaoPompier.getPompierById(cnx,1).getNom());
        
        Pompier p = new Pompier();
        p.setNom("CHAUVEL");
        p.setPrenom("Jules");
        p.setUneCaserne(new Caserne(1));
        
        p = DaoPompier.addPompier(cnx, p);
        System.out.println("le nouveau pompier a reçu l id = " + p.getId());
        
        
    }
    
}
