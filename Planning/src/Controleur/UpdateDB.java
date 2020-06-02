package Controleur;

import Modele.ConnexionDatabase;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;


 /* @author Xavier Antoine */

public class UpdateDB {
    
    public UpdateDB() throws SQLException, ClassNotFoundException{
    }
    
    public void Supprimer(int idSeance, ConnexionDatabase connect) throws SQLException{
        connect.ExecuterUpdate("DELETE FROM seance WHERE ID = "+ idSeance);
        connect.ExecuterUpdate("DELETE FROM seance_enseignant WHERE ID_Seance = "+ idSeance);
        connect.ExecuterUpdate("DELETE FROM seance_groupes WHERE ID_Seance = "+ idSeance);
        connect.ExecuterUpdate("DELETE FROM seance_salles WHERE ID_Seance = "+ idSeance);
        JOptionPane.showMessageDialog(null, "Séance supprimée");
    }
    
    public void Modifier(String type,int idSeance, String changement, ConnexionDatabase connect) throws SQLException{
        if("Date".equals(type)){
            connect.ExecuterUpdate("UPDATE seance SET Date =\""+changement+"\"WHERE ID ="+idSeance);
            JOptionPane.showMessageDialog(null, "Changement effectué");
        }
        if("Heure".equals(type)){
            try{
                switch (changement) {
                    case "8:30":
                    case "08:30":
                        connect.ExecuterUpdate("UPDATE seance SET Heure_debut =\""+changement+"\"WHERE ID ="+idSeance);
                        connect.ExecuterUpdate("UPDATE seance SET Heure_fin = \"10:00:00\" WHERE ID ="+idSeance);
                        JOptionPane.showMessageDialog(null, "Changement effectué");
                        break;
                    case "10:15":
                        connect.ExecuterUpdate("UPDATE seance SET Heure_debut =\""+changement+"\"WHERE ID ="+idSeance);
                        connect.ExecuterUpdate("UPDATE seance SET Heure_fin = \"11:45:00\" WHERE ID ="+idSeance);
                        JOptionPane.showMessageDialog(null, "Changement effectué");
                        break;
                    case "12:00":
                        connect.ExecuterUpdate("UPDATE seance SET Heure_debut =\""+changement+"\"WHERE ID ="+idSeance);
                        connect.ExecuterUpdate("UPDATE seance SET Heure_fin = \"13:30:00\" WHERE ID ="+idSeance);
                        JOptionPane.showMessageDialog(null, "Changement effectué");
                        break;
                    case "13:45":
                        connect.ExecuterUpdate("UPDATE seance SET Heure_debut =\""+changement+"\"WHERE ID ="+idSeance);
                        connect.ExecuterUpdate("UPDATE seance SET Heure_fin = \"15:15:00\" WHERE ID ="+idSeance);
                        JOptionPane.showMessageDialog(null, "Changement effectué");
                        break;
                    case "15:30":
                        connect.ExecuterUpdate("UPDATE seance SET Heure_debut =\""+changement+"\"WHERE ID ="+idSeance);
                        connect.ExecuterUpdate("UPDATE seance SET Heure_fin = \"17:00:00\" WHERE ID ="+idSeance);
                        JOptionPane.showMessageDialog(null, "Changement effectué");
                        break;
                    case "17:15":
                        connect.ExecuterUpdate("UPDATE seance SET Heure_debut =\""+changement+"\"WHERE ID ="+idSeance);
                        connect.ExecuterUpdate("UPDATE seance SET Heure_fin = \"18:45:00\" WHERE ID ="+idSeance);
                        JOptionPane.showMessageDialog(null, "Changement effectué");
                        break;
                    case "19:00":
                        connect.ExecuterUpdate("UPDATE seance SET Heure_debut =\""+changement+"\"WHERE ID ="+idSeance);
                        connect.ExecuterUpdate("UPDATE seance SET Heure_fin = \"20:30:00\" WHERE ID ="+idSeance);
                        JOptionPane.showMessageDialog(null, "Changement effectué");
                        break;
                    default : 
                        JOptionPane.showMessageDialog(null, "Erreur : l'heure entrée n'est pas valide");
                }
            }catch(SQLException e){
                JOptionPane.showMessageDialog(null, "ErreurDB : l'heure entrée n'est pas valide");
            }
            
        }
        if("Salle".equals(type)){
            ArrayList<String> newSalle = connect.ExecuterRequete("SELECT IDSalle FROM salle WHERE Nom = \""+changement+"\"");
            if(!newSalle.isEmpty()){
                connect.ExecuterUpdate("UPDATE seance_salles SET ID_Salle ="+newSalle.get(0)+"WHERE ID_Seance ="+idSeance);
                JOptionPane.showMessageDialog(null, "Changement effectué");
            }else{
                JOptionPane.showMessageDialog(null, "Erreur : cette salle n'existe pas");
            }
        }
    }
}
