package Controleur;

import Modele.ConnexionDatabase;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Xavier Antoine
 */
public class RechercherSeance {
    private ConnexionDatabase connect = null;

    public RechercherSeance() throws SQLException {
        try {
            connect = new ConnexionDatabase("ProjetJava","root","");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Connexion.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Erreur Database");
            System.exit(-1);
        }
    }
    
    public ArrayList<String> Seance(String login) throws SQLException{
        ArrayList<String> identifiant;
        ArrayList<String> IDGroupe;
        ArrayList<String> resultats;
        
        identifiant = connect.ExecuterRequete("SELECT ID FROM `user` WHERE Email = \""+ login +"\"");
        IDGroupe = connect.ExecuterRequete("SELECT ID_Groupe FROM `etudiant` WHERE ID = \""+ identifiant +"\"");
        resultats = connect.ExecuterRequete("SELECT * FROM `seance` WHERE ID_Groupe = \""+ IDGroupe +"\"");
         
        return resultats;
    }
}
