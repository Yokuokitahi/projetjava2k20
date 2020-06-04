package Controleur;

import Modele.ConnexionDatabase;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;


 /* @author Xavier Antoine */

public class UpdateDB {
    
    public UpdateDB() throws SQLException, ClassNotFoundException{
    }
    
    public void Supprimer(int idSeance) throws SQLException, ClassNotFoundException{
        ConnexionDatabase connect = ConnexionDatabase.getInstance ();
        connect.ExecuterUpdate("DELETE FROM seance WHERE ID = "+ idSeance);
        connect.ExecuterUpdate("DELETE FROM seance_enseignant WHERE ID_Seance = "+ idSeance);
        connect.ExecuterUpdate("DELETE FROM seance_groupes WHERE ID_Seance = "+ idSeance);
        connect.ExecuterUpdate("DELETE FROM seance_salles WHERE ID_Seance = "+ idSeance);
        JOptionPane.showMessageDialog(null, "Séance supprimée");
    }
    
    public void Modifier(String IDSeance, String date, String heureDebut, String cours, String typeCours, String nomEnseignant, String nomSalle, ArrayList<String> groupes, String promo) throws SQLException, ClassNotFoundException{
        ConnexionDatabase connect = ConnexionDatabase.getInstance ();
        
        ArrayList<String> IDCours = connect.ExecuterRequete("SELECT IDC FROM cours WHERE Nom = '"+cours +"'");
        ArrayList<String> IDType = connect.ExecuterRequete("SELECT ID_TC FROM type_cours WHERE Nom = '"+typeCours +"'");
        ArrayList<String> IDEnseignant = connect.ExecuterRequete("SELECT ID FROM user WHERE Nom = '"+nomEnseignant +"'");
        ArrayList<String> IDSalle = connect.ExecuterRequete("SELECT IDSalle FROM salle WHERE Nom = '"+nomSalle +"'");
        int nbSemaine = ConnexionDatabase.SQLNumSemaine(date), etat=1;
        String heureFin = "";
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
        connect.ExecuterUpdate("UPDATE seance SET Semaine = '"+nbSemaine+"', Date = '"+date+"', Heure_debut = '"+heureDebut+"', Heure_fin = '"+heureFin+"', Etat = '"+etat+"', ID_Cours = '"+IDCours.get(0)+"', ID_Type = '"+IDType.get(0)+"' WHERE ID = "+IDSeance);
        connect.ExecuterUpdate("UPDATE seance_enseignant SET ID_Enseignant ='"+IDEnseignant.get(0)+"' WHERE ID_Seance = '"+IDSeance+"'");
        connect.ExecuterUpdate("UPDATE seance_salles SET ID_Salle ='"+IDSalle.get(0)+"' WHERE ID_Seance = '"+IDSeance+"'");
        for(String iterator:IDGroupes){
            connect.ExecuterUpdate("UPDATE seance_groupes SET ID_Groupe = '"+iterator+"' WHERE ID_Seance = '"+IDSeance+"'"); 
        }
        JOptionPane.showMessageDialog(null, "Changement effectué");        
    }
}
