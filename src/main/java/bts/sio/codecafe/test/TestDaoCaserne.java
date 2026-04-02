/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bts.sio.codecafe.test;

import bts.sio.codecafe.database.ConnexionBdd;
import bts.sio.codecafe.database.DaoCaserne;
import java.sql.Connection;

/**
 *
 * @author zakina
 */
public class TestDaoCaserne {
    
      public static void main (String args[]){
        
        Connection cnx = ConnexionBdd.ouvrirConnexion();
        System.out.println ("nombre de casernes=" + DaoCaserne.getLesCasernes(cnx).size());
           
    }
    
}
