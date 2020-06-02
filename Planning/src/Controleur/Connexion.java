package Controleur;


import Modele.ConnexionDatabase;
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
        int log = 0;
        ArrayList<String> resultats;
        resultats = connect.ExecuterRequete("SELECT DroitAcces FROM `user` WHERE Email = \""+ login +"\" AND Password = \""+ password+ "\"");
        int n= resultats.size();
        
        if(n!=0)
        {
            resultats.set(0, resultats.get(0).replaceAll("\n", ""));
            n= Integer.parseInt(resultats.get(0));
            switch(n){
                case 1:
                    log = 1;
                    break;
                case 2:
                    log = 2;
                    break;
                case 3:
                    log = 3;
                    break;
                case 4:
                    log = 4;
                    break;
            }
            
        }
        else
        {
            log = 0;
        }
        
        return log;
    }
    
    
}
    
