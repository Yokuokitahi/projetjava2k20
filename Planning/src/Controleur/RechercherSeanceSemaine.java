package Controleur;

import Modele.ConnexionDatabase;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
    
    public ArrayList<ArrayList<String>> SeanceSemaine(String login, int semaine) throws SQLException{
        ArrayList<String> identifiant, IDSeance, resultats, IDGroupe,recherche;
        String request = "SELECT ID,Semaine,Date,Heure_debut,Heure_fin,ID_Cours,ID_Type FROM `seance` WHERE (ID =";
        
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
        resultats = connect.ExecuterRequete(request);

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
            ArrayList<String> IDSite = connect.ExecuterRequete("SELECT ID_Site FROM salle WHERE IDSalle = " + IDSalle.get(0));
            ArrayList<String> Site = connect.ExecuterRequete("SELECT Nom from site WHERE IDSite = " + IDSite.get(0));
            
            if (!recherche.isEmpty()) {
                iterator.add(recherche.get(0));
                iterator.add(Site.get(0));
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
                //System.out.println(temp);
                tokenResultat.set(tokenResultat.indexOf(iterator), temp);
            } 
        }
       
        /*int i =0;
        for(ArrayList<String> iterator : tokenResultats){
            i+=1;
            System.out.println("Cours n°"+i);
            System.out.println("ID de la séance :" + iterator.get(0));
            System.out.println("Semaine n°" + iterator.get(1));
            System.out.println("Jour de la séance :" + iterator.get(2));
            System.out.println("Heure de début :" + iterator.get(3));
            System.out.println("Heure de fin :" + iterator.get(4));
            System.out.println("Matière :" + iterator.get(5));
            System.out.println("Type de cours :" + iterator.get(6));
            System.out.println("Professeur :" + iterator.get(7).toUpperCase());
            System.out.println("Salle :" + iterator.get(8));
            System.out.println("Site :" + iterator.get(9));
            for(int k=10;k<iterator.size();k++){
                System.out.println("Groupe :" + iterator.get(k));
            }
            System.out.println("\n-----------------\n");
        }*/
        
        
        //System.out.println(tokenResultats);
             
        return tokenResultats;
    }
}