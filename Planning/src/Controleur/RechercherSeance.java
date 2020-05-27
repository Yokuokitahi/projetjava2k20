package Controleur;

import Modele.ConnexionDatabase;
import java.sql.SQLException;
import java.util.ArrayList;

 /* @author Xavier Antoine*/

public class RechercherSeance {
    private ConnexionDatabase connect = null;

    public RechercherSeance() throws SQLException, ClassNotFoundException {
        connect = new ConnexionDatabase();
    }
    
    public ArrayList<String> Seance(String login) throws SQLException{
        ArrayList<String> identifiant, IDSeance, resultats, IDGroupe;
        String request = "SELECT * FROM `seance` WHERE ID =";
        
        identifiant = connect.ExecuterRequete("SELECT ID FROM `user` WHERE Email = \""+ login +"\"");
        IDGroupe = connect.ExecuterRequete("SELECT ID_Groupe FROM `etudiant` WHERE ID = \""+ identifiant.get(0) +"\"");
        IDSeance = connect.ExecuterRequete("SELECT ID_Seance FROM `seance_groupes` WHERE ID_Groupe = \""+ IDGroupe.get(0) +"\"");
        
        for (String IDSeance1 : IDSeance) {
            request = request + " " + IDSeance1;
            if(!IDSeance1.equals(IDSeance.get(IDSeance.size()-1))){
                request += " OR ID = ";
            }
        }
        
        request += " ORDER BY date, heure_debut";
        
        resultats = connect.ExecuterRequete(request);
        
        return resultats;
    }
}