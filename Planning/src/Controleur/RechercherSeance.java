package Controleur;

import Modele.ConnexionDatabase;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;


/**
 *
 * @author Xavier Antoine
 */
public class RechercherSeance {

    public RechercherSeance() throws SQLException {

    }
    
    public static ArrayList<ArrayList<String>> Seance() throws SQLException, ClassNotFoundException{
        ConnexionDatabase connect = ConnexionDatabase.getInstance ();
        ArrayList<String> resultats,recherche;
        String request = "SELECT ID,Semaine,Date,Heure_debut,ID_Cours,ID_Type FROM `seance`";
        
        resultats = connect.ExecuterRequete(request);
        
        if(!resultats.isEmpty()){
            ArrayList<ArrayList<String>> tokenResultats = new ArrayList<>();
            ArrayList<String> tokens;
 
            for(int i =0;i<resultats.size();i++){
                tokens = new ArrayList<>(Arrays.asList(resultats.get(i).split(",")));
                tokenResultats.add(i,tokens); 
            }
         
            for (ArrayList<String> iterator : tokenResultats) { //affichage TYPE DE COURS
                recherche = connect.ExecuterRequete("SELECT Nom FROM type_cours WHERE ID_TC = " + iterator.get(iterator.size()-1));
                if (!recherche.isEmpty()) {
                    iterator.set(iterator.size()-1, recherche.get(0));
                }
            }
        
            for (ArrayList<String> iterator : tokenResultats) { //affichage ENSEIGNANT
                ArrayList<String> IDProf = connect.ExecuterRequete("SELECT IDProf FROM enseignant WHERE ID_Cours = " + iterator.get(iterator.size()-2));
                recherche = connect.ExecuterRequete("SELECT Nom FROM user WHERE ID = " + IDProf.get(0));
            
                if (!recherche.isEmpty()) {
                    iterator.add(recherche.get(0));
                }
            }
        
            for (ArrayList<String>  iterator : tokenResultats) { //affichage MATIERE
                recherche = connect.ExecuterRequete("SELECT Nom FROM cours WHERE IDC = " + iterator.get(iterator.size()-3));
                if (!recherche.isEmpty()) {
                    iterator.set(iterator.size()-3, recherche.get(0));
                }
            }
        
            for (ArrayList<String> iterator : tokenResultats) { //affichage SALLE et SITE
                ArrayList<String> IDSalle = connect.ExecuterRequete("SELECT ID_Salle FROM seance_salles WHERE ID_Seance = " + iterator.get(0));
                recherche = connect.ExecuterRequete("SELECT Nom FROM salle WHERE IDSalle = " + IDSalle.get(0));
            
                if (!recherche.isEmpty()) {
                    iterator.add(recherche.get(0));
                }
            }
        
            for (ArrayList<String> iterator : tokenResultats) { //affichage SALLE et SITE
                ArrayList<String> IDGroupes = connect.ExecuterRequete("SELECT ID_Groupe FROM seance_groupes WHERE ID_Seance = " + iterator.get(0));

                String requete = "SELECT Nom, ID_Promotion FROM groupe WHERE ID = ";
            
                for (String iterator2 : IDGroupes) {
                    requete = requete + " " + iterator2;
                    if(!iterator2.equals(IDGroupes.get(IDGroupes.size()-1))){
                        requete += " OR ID = ";
                    }
                }
            
                recherche = connect.ExecuterRequete(requete);  
            
                for (String recherche1 : recherche) {
                    if (!recherche.isEmpty()) {
                        iterator.add(recherche1);
                    }
                }
            
            }
            for (ArrayList<String> tokenResultat : tokenResultats) {//TOKENIZER
                for (String iterator : tokenResultat) {
                    String temp = iterator.replaceAll("\n", "");
                    tokenResultat.set(tokenResultat.indexOf(iterator), temp);
                } 
            }
                    
            return tokenResultats;
        }else{
            ArrayList<ArrayList<String>> erreur = new ArrayList<>();
            ArrayList<String> erreur2 = new ArrayList<>();
            erreur2.add("Erreur : pas de cours disponibles actuellement");
            erreur.add(erreur2);
            return erreur;
        }   
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
