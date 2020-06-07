package Modele;

import java.sql.*;
import java.text.*;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class ConnexionDatabase {

    private static Connection conn = null;
    private final Statement stmt;
    private ResultSet rset;
    private ResultSetMetaData rsetMeta;
    private static ConnexionDatabase connexion = null;

    public ArrayList<String> tables = new ArrayList<>();

    public ArrayList<String> requetes = new ArrayList<>();
 
    public ArrayList<String> requetesMaj = new ArrayList<>();

    private ConnexionDatabase() throws SQLException, ClassNotFoundException{
        Class.forName("com.mysql.jdbc.Driver");

        String urlDatabase = "jdbc:mysql://localhost:3306/projetjava";

        conn = DriverManager.getConnection(urlDatabase, "root", "");

        stmt = conn.createStatement();
    }
    
    public static ConnexionDatabase getInstance() throws SQLException, ClassNotFoundException{
    if(conn == null){
      connexion = new ConnexionDatabase();
      
    } 
    return connexion;   
  }  

    public void ajouterTable(String table) {
        tables.add(table);
    }

    public void ajouterRequete(String requete) {
        requetes.add(requete);
    }

    public void ajouterRequeteMaj(String requete) {
        requetesMaj.add(requete);
    }

    public ArrayList remplirChampsTable(String table) throws SQLException {

        rset = stmt.executeQuery("select * from " + table);

        rsetMeta = rset.getMetaData();

        int nbColonne = rsetMeta.getColumnCount();

        ArrayList<String> liste;
        liste = new ArrayList<>();
        String champs = "";

        for (int i = 0; i < nbColonne; i++) {
            champs = champs + " " + rsetMeta.getColumnLabel(i + 1);
        }

        champs = champs + "\n";

        liste.add(champs);

        return liste;
    }

    public ArrayList ExecuterRequete(String requete) throws SQLException {
        rset = stmt.executeQuery(requete);

        rsetMeta = rset.getMetaData();

        int nbColonne = rsetMeta.getColumnCount();

        ArrayList<String> liste;
        liste = new ArrayList<>();

        while (rset.next()) {
            String champs;
            champs = rset.getString(1);

            for (int i = 1; i < nbColonne; i++) {
                champs = champs + "," + rset.getString(i + 1);
            }

            champs = champs + "\n";

            liste.add(champs);
        }

        return liste;
    }

    public void ExecuterUpdate(String requeteMaj) throws SQLException {
        stmt.executeUpdate(requeteMaj);
    }
    
    public String dateAjd(){
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        java.util.Date date = new java.util.Date();
        //System.out.println(dateFormat.format(date));
        
        return(dateFormat.format(date));
    }
    
    public static ArrayList<String> SQLDateAjd() throws SQLException, ClassNotFoundException{
        ConnexionDatabase connect = new ConnexionDatabase();
        
        ArrayList<String> resultat = connect.ExecuterRequete("SELECT CURDATE()");
        
        return resultat;   
    }
    
    public static int SQLNumSemaine() throws SQLException, ClassNotFoundException{
        ConnexionDatabase connect = ConnexionDatabase.getInstance();
        ArrayList<String> resultat = connect.ExecuterRequete("SELECT YEARWEEK(CURDATE())");
        String result = resultat.get(0);
        int longueur = result.length();
        
        String semaine = Character.toString(result.charAt(longueur-3)) + Character.toString(result.charAt(longueur-2)) ;
        int numSemaine = Integer.parseInt(semaine) + 1; 
        
        return numSemaine; 
    }
    
    public static int SQLNumSemaine(String date) throws SQLException, ClassNotFoundException{
        ConnexionDatabase connect = new ConnexionDatabase();
        
        ArrayList<String> resultat = connect.ExecuterRequete("SELECT YEARWEEK(\""+date+"\")");
        if(!resultat.isEmpty()){
            String result = resultat.get(0);
            int longueur = result.length();
        
            String semaine = Character.toString(result.charAt(longueur-3)) + Character.toString(result.charAt(longueur-2)) ;
            int numSemaine = Integer.parseInt(semaine) + 1; 
        
            return numSemaine; 
        }else{
            JOptionPane.showMessageDialog(null, "Erreur");
            return 0;
        }
        
    }
}
