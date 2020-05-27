package Modele;


import Controleur.ConnexionDatabase;
import java.util.*;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author xavan
 */
public class Connexion{
    
    private ConnexionDatabase connect = null;

    public Connexion() throws SQLException {
        try {
            connect = new ConnexionDatabase();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Connexion.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Erreur Database");
            System.exit(-1);
        }
    }
    
    public int UserConnect(String login,String password) throws SQLException{
        int log;
        ArrayList<String> resultats;
        resultats = connect.ExecuterRequete("SELECT * FROM `user` WHERE Email = \""+ login +"\" AND Password = \""+ password+ "\"");
        int n=resultats.size();
        if(n!=0)
        {
            log = 1;
            
        }
        else
        {
            log = 0;
        }
        
        return log;
    }
    
    
}
    
