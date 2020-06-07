package Controleur;

import Modele.ConnexionDatabase;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


 /* @author Xavier Antoine */

public class InfosDB {
    
    public InfosDB() throws SQLException, ClassNotFoundException{
    }
    //RETOURNE TOUTES LES SALLES
    public static ArrayList<String> getSalles() throws SQLException, ClassNotFoundException{
        ConnexionDatabase connect = ConnexionDatabase.getInstance ();
        ArrayList<String> salles = connect.ExecuterRequete("SELECT Nom FROM salle");
        for (String iterator : salles) {
            String temp = iterator.replaceAll("\n", "");
            salles.set(salles.indexOf(iterator), temp);
        }
        return salles;
    }
    //RETOURNE TOUTES LES MATIERES
    public static ArrayList<String> getMatiere() throws SQLException, ClassNotFoundException{
        ConnexionDatabase connect = ConnexionDatabase.getInstance ();

        ArrayList<String> cours = connect.ExecuterRequete("SELECT Nom FROM cours");
        for (String iterator : cours) {
            String temp = iterator.replaceAll("\n", "");
            cours.set(cours.indexOf(iterator), temp);
        }
        return cours;
    }

    //RETOURNE TOUS LES ENSEIGNANTS
    public static ArrayList<String> getEnseignant() throws SQLException, ClassNotFoundException{

        ConnexionDatabase connect = ConnexionDatabase.getInstance ();
        ArrayList<String> ID = connect.ExecuterRequete("SELECT IDProf FROM enseignant ");
        String request = "SELECT Nom FROM user WHERE ID =";
        
        Set<String> mySet = new HashSet<>(ID);
        List<String> newID = new ArrayList<>(mySet);

        for (String iterator : newID) {
            request = request + " " + iterator;
            if(!iterator.equals(newID.get(newID.size()-1))){
                request += " OR ID = ";
            }
        }

        ArrayList<String> enseignants = connect.ExecuterRequete(request);
        for (String iterator : enseignants) {
            String temp = iterator.replaceAll("\n", "");
            enseignants.set(enseignants.indexOf(iterator), temp);
        }
        return enseignants;
    }
    //RETOURNE LES TYPES DE COURS
    public static ArrayList<String> getTypeDeCours() throws SQLException, ClassNotFoundException{
        ConnexionDatabase connect = ConnexionDatabase.getInstance ();
        ArrayList<String> types = connect.ExecuterRequete("SELECT Nom FROM type_cours");
        for (String iterator : types) {
            String temp = iterator.replaceAll("\n", "");
            types.set(types.indexOf(iterator), temp);
        }
        return types;
    }
    //RETOURNE LES GROUPES EN FONCTION DES PROMOS
    public static ArrayList<String> getGroupes(String promo) throws SQLException, ClassNotFoundException{
        ConnexionDatabase connect = ConnexionDatabase.getInstance ();
        ArrayList<String> groupes = connect.ExecuterRequete("SELECT Nom FROM groupe WHERE ID_Promotion = "+promo);
        for (String iterator : groupes) {
            String temp = iterator.replaceAll("\n", "");
            groupes.set(groupes.indexOf(iterator), temp);
        }
        return groupes;
    }
    //RETOURNE LES PROMOTIONS
    public static ArrayList<String> getPromotion() throws SQLException, ClassNotFoundException{
        ConnexionDatabase connect = ConnexionDatabase.getInstance ();
        ArrayList<String> promo = connect.ExecuterRequete("SELECT ID_Promotion FROM groupe ORDER BY ID_Promotion");
        Set<String> mySet = new HashSet<>(promo);
        promo = new ArrayList<>(mySet);
        for (String iterator : promo) {
            String temp = iterator.replaceAll("\n", "");
            promo.set(promo.indexOf(iterator), temp);
        }
        return promo;
    }
    //RETOURNE LE LOGIN DE LA PERSONNE
    public static String getLogin(String nom, String prenom) throws SQLException, ClassNotFoundException{
        ConnexionDatabase connect = ConnexionDatabase.getInstance();
        ArrayList<String> log = connect.ExecuterRequete("SELECT  Email FROM user WHERE (Nom = '"+nom +"' AND Prenom = '"+prenom +"') OR (Nom = '"+prenom +"' AND Prenom = '"+nom +"')");
        if(!log.isEmpty()){
            String login = log.get(0);
            login = login.replaceAll("\n","");
            return login;
        }else{
            return "User not found";
        }
    }
    //RETOURNE UN LOGIN D'UNE PERSONNE D'UN GROUPE
    public static String getLoginGroupe(String grp, String promo) throws SQLException, ClassNotFoundException{
        ConnexionDatabase connect = ConnexionDatabase.getInstance();
        ArrayList<String> ID = connect.ExecuterRequete("SELECT ID FROM groupe WHERE (Nom = '"+grp +"' AND ID_Promotion = '"+promo +"') OR (Nom = '"+promo +"' AND ID_Promotion = '"+grp +"')");
        String IDGroupe = ID.get(0);
        ArrayList<String> nomPrenom = connect.ExecuterRequete("SELECT Nom,Prenom FROM `etudiant` WHERE ID_Groupe = "+ IDGroupe+" LIMIT 1");
        ArrayList<String> tokens = new ArrayList<>(Arrays.asList(nomPrenom.get(0).split(",")));
        String nom= tokens.get(0);
        nom = nom.replaceAll("\n", "");
        String prenom= tokens.get(1);
        prenom = prenom.replaceAll("\n","");
        String login = InfosDB.getLogin(nom, prenom);
        login = login.replaceAll("\n","");
        return login;
    }

}
