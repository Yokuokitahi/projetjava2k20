
package Controleur;


import Modele.ConnexionDatabase;
import java.util.*;
import java.sql.*;


/**
 *
 * @author xavan
 */
public class Connexion{
    

    public Connexion() throws SQLException {
       
    }
    
    public int UserConnect(String login,String password) throws SQLException, ClassNotFoundException{
        ///AUTHENTIFICATION lors de la connexion (selon le login)
        
        ConnexionDatabase connect = ConnexionDatabase.getInstance();
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
    
