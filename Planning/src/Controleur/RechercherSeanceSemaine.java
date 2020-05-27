package Controleur;

import Modele.ConnexionDatabase;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;

 /* @author Xavier Antoine*/

public class RechercherSeanceSemaine {
    private ConnexionDatabase connect = null;
    private final int numSemaine;

    public RechercherSeanceSemaine() throws SQLException, ClassNotFoundException {
        connect = new ConnexionDatabase();
        numSemaine = connect.SQLNumSemaine();
    }
    
    public ArrayList<String> SeanceSemaine(String login) throws SQLException{
        ArrayList<String> identifiant, IDSeance, resultats, IDGroupe;
        String request = "SELECT * FROM `seance` WHERE (ID =";
        
        identifiant = connect.ExecuterRequete("SELECT ID FROM `user` WHERE Email = \""+ login +"\"");
        IDGroupe = connect.ExecuterRequete("SELECT ID_Groupe FROM `etudiant` WHERE ID = \""+ identifiant.get(0) +"\"");
        IDSeance = connect.ExecuterRequete("SELECT ID_Seance FROM `seance_groupes` WHERE ID_Groupe = \""+ IDGroupe.get(0) +"\"");
        
        for (String IDSeance1 : IDSeance) {
            request = request + " " + IDSeance1;
            if(!IDSeance1.equals(IDSeance.get(IDSeance.size()-1))){
                request += " OR ID = ";
            }
        }
        request += ")  AND Semaine = " + numSemaine + " ORDER BY date, heure_debut";
        resultats = connect.ExecuterRequete(request);
        
        return resultats;
    }
    
    public ArrayList<String> SeanceSemaine(String login, int semaine) throws SQLException{
        ArrayList<String> identifiant, IDSeance, resultats, IDGroupe;
        String request = "SELECT * FROM `seance` WHERE (ID =";
        
        identifiant = connect.ExecuterRequete("SELECT ID FROM `user` WHERE Email = \""+ login +"\"");
        IDGroupe = connect.ExecuterRequete("SELECT ID_Groupe FROM `etudiant` WHERE ID = \""+ identifiant.get(0) +"\"");
        IDSeance = connect.ExecuterRequete("SELECT ID_Seance FROM `seance_groupes` WHERE ID_Groupe = \""+ IDGroupe.get(0) +"\"");
        
        for (String IDSeance1 : IDSeance) {
            request = request + " " + IDSeance1;
            if(!IDSeance1.equals(IDSeance.get(IDSeance.size()-1))){
                request += " OR ID = ";
            }
        }
        request += ")  AND Semaine = " + semaine + " ORDER BY date, heure_debut";
        //System.out.println(request);
        resultats = connect.ExecuterRequete(request);
        
        //System.out.println(resultats);
        
        String[][] tokenResultats= new String[resultats.size()][8];
        String[] tokens;
 
         for(int i =0;i<resultats.size();i++){
             tokens = resultats.get(i).split(",");
             System.arraycopy(tokens, 0, tokenResultats[i], 0, 8); 
         }
        
        for (String[] tokenResultat : tokenResultats) {
            System.out.println(Arrays.toString(tokenResultat));
        }
             
        return resultats;
    }
}