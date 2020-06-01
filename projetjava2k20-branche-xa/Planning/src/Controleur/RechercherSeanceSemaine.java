package Controleur;

import Modele.ConnexionDatabase;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;

 /* @author Xavier Antoine*/

public class RechercherSeanceSemaine {
    private ConnexionDatabase connect = null;

    public RechercherSeanceSemaine() throws SQLException, ClassNotFoundException {
        connect = new ConnexionDatabase();
    }
    //RECHERCHE AVEC GROUPE
    public ArrayList<ArrayList<String>> SeanceSemaineGroupe(String groupe, String promotion, int semaine) throws SQLException{
        ArrayList<String> IDSeance, resultats,recherche, IDGroupe;
        String request = "SELECT ID,Semaine,Date,Heure_debut,ID_Cours,ID_Type FROM `seance` WHERE (ID =";
        
        IDGroupe = connect.ExecuterRequete("SELECT ID FROM `groupe` WHERE (Nom = \""+ groupe + "\" AND ID_Promotion = \""+ promotion + "\") OR (Nom = \""+ promotion +"\" AND ID_Promotion = \"" + groupe + "\")");
        IDSeance = connect.ExecuterRequete("SELECT ID_Seance FROM `seance_groupes` WHERE ID_Groupe = \""+ IDGroupe.get(0) +"\"");

        if(!IDSeance.isEmpty()){
        
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
            
            for (ArrayList<String> iterator : tokenResultats) { //affichage Jour de la séance
                
                String requete = "SELECT DAYOFWEEK('" + iterator.get(2)+"')";
                ArrayList<String> jour = connect.ExecuterRequete(requete);
                jour.set(0, jour.get(0).replaceAll("\n", ""));
                int day = Integer.parseInt(jour.get(0));
                day -= 2;
                String s=String.valueOf(day);
                
                if (!jour.isEmpty()) {
                    iterator.set(2, s);
                }
            }
            
            
            for (ArrayList<String> iterator : tokenResultats) { //Determination du numero de l'heure
                
                if(iterator.get(3).equals("08:30:00")){
                    iterator.set(3, "0");
                }
                if(iterator.get(3).equals("10:15:00")){
                    iterator.set(3, "1");
                }
                if(iterator.get(3).equals("12:00:00")){
                    iterator.set(3, "2");
                }
                if(iterator.get(3).equals("13:45:00")){
                    iterator.set(3, "3");
                }
                if(iterator.get(3).equals("15:30:00")){
                    iterator.set(3, "4");
                }
                if(iterator.get(3).equals("17:15:00")){
                    iterator.set(3, "5");
                }
                if(iterator.get(3).equals("19:30:00")){
                    iterator.set(3, "6");
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
    //RECHERCHE AVEC NOM ET PRENOM
    public ArrayList<ArrayList<String>> SeanceSemaine(String nom, String prenom, int semaine) throws SQLException{
        ArrayList<String> identifiant, IDSeance, resultats, IDGroupe,recherche;
        String request = "SELECT ID,Semaine,Date,Heure_debut,ID_Cours,ID_Type FROM `seance` WHERE (ID =";
        
        identifiant = connect.ExecuterRequete("SELECT ID FROM `user` WHERE (Nom = \""+ nom + "\" AND Prenom = \""+ prenom + "\") OR (Nom = \""+ prenom +"\" AND Prenom = \"" + nom + "\")");
        IDGroupe = connect.ExecuterRequete("SELECT ID_Groupe FROM `etudiant` WHERE ID = \""+ identifiant.get(0) +"\"");
        if(IDGroupe.isEmpty()){
            IDSeance = connect.ExecuterRequete("SELECT ID_Seance FROM `seance_enseignant` WHERE ID_Enseignant = \""+ identifiant.get(0) +"\"");
        }else{
            IDSeance = connect.ExecuterRequete("SELECT ID_Seance FROM `seance_groupes` WHERE ID_Groupe = \""+ IDGroupe.get(0) +"\"");
        }
        
        if(!IDSeance.isEmpty()){
        
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
            
            for (ArrayList<String> iterator : tokenResultats) { //affichage Jour de la séance
                
                String requete = "SELECT DAYOFWEEK('" + iterator.get(2)+"')";
                ArrayList<String> jour = connect.ExecuterRequete(requete);
                jour.set(0, jour.get(0).replaceAll("\n", ""));
                int day = Integer.parseInt(jour.get(0));
                day -= 2;
                String s=String.valueOf(day);
                
                if (!jour.isEmpty()) {
                    iterator.set(2, s);
                }
            }
            
            
            for (ArrayList<String> iterator : tokenResultats) { //Determination du numero de l'heure
                
                if(iterator.get(3).equals("08:30:00")){
                    iterator.set(3, "0");
                }
                if(iterator.get(3).equals("10:15:00")){
                    iterator.set(3, "1");
                }
                if(iterator.get(3).equals("12:00:00")){
                    iterator.set(3, "2");
                }
                if(iterator.get(3).equals("13:45:00")){
                    iterator.set(3, "3");
                }
                if(iterator.get(3).equals("15:30:00")){
                    iterator.set(3, "4");
                }
                if(iterator.get(3).equals("17:15:00")){
                    iterator.set(3, "5");
                }
                if(iterator.get(3).equals("19:30:00")){
                    iterator.set(3, "6");
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
    //RECHERCHE AVEC MAIL(LOGIN)
    public ArrayList<ArrayList<String>> SeanceSemaine(String login, int semaine) throws SQLException{
        ArrayList<String> identifiant, IDSeance, resultats, IDGroupe,recherche;
        String request = "SELECT ID,Semaine,Date,Heure_debut,ID_Cours,ID_Type FROM `seance` WHERE (ID =";
        
        identifiant = connect.ExecuterRequete("SELECT ID FROM `user` WHERE Email = \""+ login +"\"");
        IDGroupe = connect.ExecuterRequete("SELECT ID_Groupe FROM `etudiant` WHERE ID = \""+ identifiant.get(0) +"\"");
        if(IDGroupe.isEmpty()){
            IDSeance = connect.ExecuterRequete("SELECT ID_Seance FROM `seance_enseignant` WHERE ID_Enseignant = \""+ identifiant.get(0) +"\"");
        }else{
            IDSeance = connect.ExecuterRequete("SELECT ID_Seance FROM `seance_groupes` WHERE ID_Groupe = \""+ IDGroupe.get(0) +"\"");
        }
        
        if(!IDSeance.isEmpty()){
        
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
            
            for (ArrayList<String> iterator : tokenResultats) { //affichage Jour de la séance
                
                String requete = "SELECT DAYOFWEEK('" + iterator.get(2)+"')";
                ArrayList<String> jour = connect.ExecuterRequete(requete);
                jour.set(0, jour.get(0).replaceAll("\n", ""));
                int day = Integer.parseInt(jour.get(0));
                day -= 2;
                String s=String.valueOf(day);
                
                if (!jour.isEmpty()) {
                    iterator.set(2, s);
                }
            }
            
            
            for (ArrayList<String> iterator : tokenResultats) { //Determination du numero de l'heure
                
                if(iterator.get(3).equals("08:30:00")){
                    iterator.set(3, "0");
                }
                if(iterator.get(3).equals("10:15:00")){
                    iterator.set(3, "1");
                }
                if(iterator.get(3).equals("12:00:00")){
                    iterator.set(3, "2");
                }
                if(iterator.get(3).equals("13:45:00")){
                    iterator.set(3, "3");
                }
                if(iterator.get(3).equals("15:30:00")){
                    iterator.set(3, "4");
                }
                if(iterator.get(3).equals("17:15:00")){
                    iterator.set(3, "5");
                }
                if(iterator.get(3).equals("19:30:00")){
                    iterator.set(3, "6");
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
    //RECHERCHE AVEC PROMOTION
    public ArrayList<ArrayList<String>> SeanceSemainePromotion(String promotion, int semaine) throws SQLException{
        ArrayList<String> IDSeance, resultats, IDGroupe,recherche;
        String request = "SELECT ID,Semaine,Date,Heure_debut,ID_Cours,ID_Type FROM `seance` WHERE (ID =";
        String request2 = "SELECT ID_Seance FROM `seance_groupes` WHERE ID_Groupe =";
        IDGroupe = connect.ExecuterRequete("SELECT ID FROM groupe WHERE ID_Promotion ="+promotion);
        
        for (String iterator : IDGroupe) {
            request2 = request2 + " " + iterator;
            if(!iterator.equals(IDGroupe.get(IDGroupe.size()-1))){
                request2 += " OR ID_Groupe = ";
            }
        }
        
        IDSeance = connect.ExecuterRequete(request2);
        
        if(!IDSeance.isEmpty()){
        
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
            
            for (ArrayList<String> iterator : tokenResultats) { //affichage Jour de la séance
                
                String requete = "SELECT DAYOFWEEK('" + iterator.get(2)+"')";
                ArrayList<String> jour = connect.ExecuterRequete(requete);
                jour.set(0, jour.get(0).replaceAll("\n", ""));
                int day = Integer.parseInt(jour.get(0));
                day -= 2;
                String s=String.valueOf(day);
                
                if (!jour.isEmpty()) {
                    iterator.set(2, s);
                }
            }
            
            
            for (ArrayList<String> iterator : tokenResultats) { //Determination du numero de l'heure
                
                if(iterator.get(3).equals("08:30:00")){
                    iterator.set(3, "0");
                }
                if(iterator.get(3).equals("10:15:00")){
                    iterator.set(3, "1");
                }
                if(iterator.get(3).equals("12:00:00")){
                    iterator.set(3, "2");
                }
                if(iterator.get(3).equals("13:45:00")){
                    iterator.set(3, "3");
                }
                if(iterator.get(3).equals("15:30:00")){
                    iterator.set(3, "4");
                }
                if(iterator.get(3).equals("17:15:00")){
                    iterator.set(3, "5");
                }
                if(iterator.get(3).equals("19:30:00")){
                    iterator.set(3, "6");
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
    //RECHERCHE AVEC COURS
    public ArrayList<ArrayList<String>> SeanceSemaineCours(String cours, int semaine) throws SQLException{
        ArrayList<String> identifiant, resultats,recherche;
        
        identifiant = connect.ExecuterRequete("SELECT IDC FROM `cours` WHERE Nom = \""+ cours +"\"");
        resultats = connect.ExecuterRequete("SELECT ID,Semaine,Date,Heure_debut,ID_Cours,ID_Type FROM `seance` WHERE ID_Cours =\" "+ identifiant.get(0) + " \"");
        
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
            
            for (ArrayList<String> iterator : tokenResultats) { //affichage Jour de la séance
                
                String requete = "SELECT DAYOFWEEK('" + iterator.get(2)+"')";
                ArrayList<String> jour = connect.ExecuterRequete(requete);
                jour.set(0, jour.get(0).replaceAll("\n", ""));
                int day = Integer.parseInt(jour.get(0));
                day -= 2;
                String s=String.valueOf(day);
                
                if (!jour.isEmpty()) {
                    iterator.set(2, s);
                }
            }
            
            
            for (ArrayList<String> iterator : tokenResultats) { //Determination du numero de l'heure
                
                if(iterator.get(3).equals("08:30:00")){
                    iterator.set(3, "0");
                }
                if(iterator.get(3).equals("10:15:00")){
                    iterator.set(3, "1");
                }
                if(iterator.get(3).equals("12:00:00")){
                    iterator.set(3, "2");
                }
                if(iterator.get(3).equals("13:45:00")){
                    iterator.set(3, "3");
                }
                if(iterator.get(3).equals("15:30:00")){
                    iterator.set(3, "4");
                }
                if(iterator.get(3).equals("17:15:00")){
                    iterator.set(3, "5");
                }
                if(iterator.get(3).equals("19:30:00")){
                    iterator.set(3, "6");
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
    //RECHERCHE AVEC SALLE
    public ArrayList<ArrayList<String>> SeanceSemaineSalle(String salle, int semaine) throws SQLException{
        ArrayList<String> identifiant, IDSeance, resultats, IDGroupe,recherche;
        String request = "SELECT ID,Semaine,Date,Heure_debut,ID_Cours,ID_Type FROM `seance` WHERE (ID =";
        
        identifiant = connect.ExecuterRequete("SELECT IDSalle FROM `salle` WHERE Nom = \""+ salle +"\"");
               
        IDSeance = connect.ExecuterRequete("SELECT ID_Seance FROM `seance_salles` WHERE ID_Salle = \""+ identifiant.get(0) +"\"");
      
        if(!IDSeance.isEmpty()){
        
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
            
            for (ArrayList<String> iterator : tokenResultats) { //affichage Jour de la séance
                
                String requete = "SELECT DAYOFWEEK('" + iterator.get(2)+"')";
                ArrayList<String> jour = connect.ExecuterRequete(requete);
                jour.set(0, jour.get(0).replaceAll("\n", ""));
                int day = Integer.parseInt(jour.get(0));
                day -= 2;
                String s=String.valueOf(day);
                
                if (!jour.isEmpty()) {
                    iterator.set(2, s);
                }
            }
            
            
            for (ArrayList<String> iterator : tokenResultats) { //Determination du numero de l'heure
                
                if(iterator.get(3).equals("08:30:00")){
                    iterator.set(3, "0");
                }
                if(iterator.get(3).equals("10:15:00")){
                    iterator.set(3, "1");
                }
                if(iterator.get(3).equals("12:00:00")){
                    iterator.set(3, "2");
                }
                if(iterator.get(3).equals("13:45:00")){
                    iterator.set(3, "3");
                }
                if(iterator.get(3).equals("15:30:00")){
                    iterator.set(3, "4");
                }
                if(iterator.get(3).equals("17:15:00")){
                    iterator.set(3, "5");
                }
                if(iterator.get(3).equals("19:30:00")){
                    iterator.set(3, "6");
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
}