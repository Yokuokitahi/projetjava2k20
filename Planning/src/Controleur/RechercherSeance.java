package Controleur;

import Modele.ConnexionDatabase;
import java.sql.SQLException;
import java.util.ArrayList;


/**
 *
 * @author Xavier Antoine
 */
public class RechercherSeance {

    public RechercherSeance() throws SQLException {

    }
    
    public ArrayList<String> Seance(String login) throws SQLException, ClassNotFoundException{
        ConnexionDatabase connect = ConnexionDatabase.getInstance ();
        ArrayList<String> identifiant;
        ArrayList<String> IDGroupe;
        ArrayList<String> resultats;
        
        identifiant = connect.ExecuterRequete("SELECT ID FROM `user` WHERE Email = \""+ login +"\"");
        IDGroupe = connect.ExecuterRequete("SELECT ID_Groupe FROM `etudiant` WHERE ID = \""+ identifiant +"\"");
        resultats = connect.ExecuterRequete("SELECT * FROM `seance` WHERE ID_Groupe = \""+ IDGroupe +"\"");
         
        return resultats;
    }
    
    public ArrayList<String> RecapSeance(String login, String matiere) throws SQLException, ClassNotFoundException{
        ConnexionDatabase connect = ConnexionDatabase.getInstance ();
        ArrayList<String> identifiant;
        ArrayList<String> IDGroupe, IDMatiere, IDSeance;
        ArrayList<String> resultats;
        
        identifiant = connect.ExecuterRequete("SELECT ID FROM `user` WHERE Email = \""+ login +"\"");
        IDGroupe = connect.ExecuterRequete("SELECT ID_Groupe FROM `etudiant` WHERE ID = \""+ identifiant.get(0) +"\"");
        IDMatiere = connect.ExecuterRequete("SELECT IDC FROM cours WHERE Nom = '"+matiere+"'");
        IDSeance = connect.ExecuterRequete("SELECT ID_Seance FROM seance_groupes WHERE ID_Groupe = "+ IDGroupe.get(0));
        String request = "SELECT ID, ID_Cours FROM seance WHERE ID_Cours = "+IDMatiere.get(0)+"AND (ID=";
        for (String IDSeance1 : IDSeance) {
            request = request + " " + IDSeance1;
            if(!IDSeance1.equals(IDSeance.get(IDSeance.size()-1))){
                request += " OR ID = ";
            }
        }
        request += ")";
        
        resultats = connect.ExecuterRequete(request);
        
        for (String iterator : resultats) {
            String temp = iterator.replaceAll("\n", "");
            resultats.set(resultats.indexOf(iterator), temp);
        }
        
        return resultats;
    }
}
