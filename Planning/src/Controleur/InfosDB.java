package Controleur;

import Modele.ConnexionDatabase;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


 /* @author Xavier Antoine */

public class InfosDB {
    
    public InfosDB() throws SQLException, ClassNotFoundException{
    }
    //RETOURNE TOUTES LES SALLES
    public ArrayList<String> getSalles() throws SQLException, ClassNotFoundException{
        ConnexionDatabase connect = ConnexionDatabase.getInstance ();
        ArrayList<String> salles = connect.ExecuterRequete("SELECT Nom FROM salle");
        for (String iterator : salles) {
            String temp = iterator.replaceAll("\n", "");
            salles.set(salles.indexOf(iterator), temp);
        }
        return salles;
    }
    //RETOURNE TOUTES LES MATIERES

    public ArrayList<String> getMatiere() throws SQLException, ClassNotFoundException{
        ConnexionDatabase connect = ConnexionDatabase.getInstance ();

        ArrayList<String> cours = connect.ExecuterRequete("SELECT Nom FROM cours");
        for (String iterator : cours) {
            String temp = iterator.replaceAll("\n", "");
            cours.set(cours.indexOf(iterator), temp);
        }
        return cours;
    }
    //RETOURNE TOUS LES ENSEIGNANTS LIES A UNE MATIERE
    public ArrayList<String> getEnseignant(int IDCours) throws SQLException, ClassNotFoundException{
        ConnexionDatabase connect = ConnexionDatabase.getInstance ();
        ArrayList<String> ID = connect.ExecuterRequete("SELECT IDProf FROM enseignant WHERE ID_Cours =" + IDCours);
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
    public ArrayList<String> getTypeDeCours() throws SQLException, ClassNotFoundException{
        ConnexionDatabase connect = ConnexionDatabase.getInstance ();
        ArrayList<String> types = connect.ExecuterRequete("SELECT Nom FROM type_cours");
        return types;
    }
    //RETOURNE LES GROUPES EN FONCTION DES PROMOS
    public ArrayList<String> getGroupes(String promo) throws SQLException, ClassNotFoundException{
        ConnexionDatabase connect = ConnexionDatabase.getInstance ();
        ArrayList<String> groupes = connect.ExecuterRequete("SELECT Nom FROM groupe WHERE ID_Promotion = "+promo);
        for (String iterator : groupes) {
            String temp = iterator.replaceAll("\n", "");
            groupes.set(groupes.indexOf(iterator), temp);
        }
        return groupes;
    }
    //RETOURNE LES PROMOTIONS
    public ArrayList<String> getPromotion() throws SQLException, ClassNotFoundException{
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

}
