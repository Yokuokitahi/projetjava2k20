package Controleur;

import Modele.ConnexionDatabase;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;


 /* @author Xavier Antoine */

public class AjouterDB {
    
    public AjouterDB() throws SQLException, ClassNotFoundException{
    }
    
    public void AjouterSeance(String date, String heureDebut, String cours, String typeCours, String nomProf, ArrayList<String> groupes, String promo, String salle) throws SQLException, ClassNotFoundException{
        ConnexionDatabase connect = ConnexionDatabase.getInstance();
        String heureFin = "";
        int semaine = ConnexionDatabase.SQLNumSemaine(date);
        ArrayList<String> IDCours = connect.ExecuterRequete("SELECT IDC FROM cours WHERE Nom = '" +cours+"'");
        ArrayList<String> IDType = connect.ExecuterRequete("SELECT ID_TC FROM type_cours WHERE Nom = '" +typeCours+"'");
        ArrayList<String> IDProf = connect.ExecuterRequete("SELECT ID FROM user WHERE Nom = '" +nomProf+"'");
        ArrayList<String> IDSalle = connect.ExecuterRequete("SELECT IDSalle FROM salle WHERE Nom = '" +salle+"'");
        switch (heureDebut) {
                    case "8:30":
                    case "08:30":
                        heureFin = "10:00:00";
                        break;
                    case "10:15":
                        heureFin = "11:45:00";
                        break;
                    case "12:00":
                        heureFin = "13:30:00" ;
                        break;
                    case "13:45":
                        heureFin = "15:15:00";
                        break;
                    case "15:30":
                        heureFin = "17:00:00";
                        break;
                    case "17:15":
                        heureFin = "18:45:00";
                        break;
                    case "19:00":
                        heureFin = "20:30:00";
                        break;
                    default : 
                        JOptionPane.showMessageDialog(null, "Erreur : l'heure entrée n'est pas valide");
                }
        
        
        connect.ExecuterUpdate("INSERT INTO seance (Semaine, Date, Heure_debut, Heure_fin, ID_Cours, ID_Type) VALUES ('"+semaine+"', '"+date+"', '"+heureDebut+"', '" + heureFin + "', '"+IDCours.get(0)+"', '"+IDType.get(0)+"')");
        ArrayList<String> IDSeance = connect.ExecuterRequete("SELECT MAX(ID) FROM seance");
        connect.ExecuterUpdate("INSERT INTO seance_enseignant (ID_Seance, ID_Enseignant) VALUES ('"+IDSeance.get(0)+"', '"+IDProf.get(0)+"')");
        connect.ExecuterUpdate("INSERT INTO seance_salles (ID_Seance, ID_Salle) VALUES ('"+IDSeance.get(0)+"', '"+IDSalle.get(0)+"')");
        
        String requete = "SELECT ID FROM groupe WHERE ID_Promotion = "+promo + " AND (Nom = '"
                ;
        for (String iterator2 : groupes) {
            requete = requete + iterator2 + "'";
            if(!iterator2.equals(groupes.get(groupes.size()-1))){
                requete += " OR Nom = '";
            }
        }
        requete += ")";
        ArrayList<String> IDGroupes = connect.ExecuterRequete(requete);
        for(String iterator:IDGroupes){
            connect.ExecuterUpdate("INSERT INTO seance_groupes (ID_Seance, ID_Groupe) VALUES ('"+IDSeance.get(0)+"','"+iterator+"')"); 
        }
        JOptionPane.showMessageDialog(null, "Cours enregistré");
    }
}
