package planning;

import java.sql.*;
import java.util.ArrayList;

public class ConnexionDatabase {

    private final Connection conn;
    private final Statement stmt;
    private ResultSet rset;
    private ResultSetMetaData rsetMeta;

    public ArrayList<String> tables = new ArrayList<>();

    public ArrayList<String> requetes = new ArrayList<>();
 
    public ArrayList<String> requetesMaj = new ArrayList<>();

    public ConnexionDatabase(String nameDatabase, String loginDatabase, String passwordDatabase) throws SQLException, ClassNotFoundException{
        Class.forName("com.mysql.jdbc.Driver");

        String urlDatabase = "jdbc:mysql://localhost:3306/" + nameDatabase;

        conn = DriverManager.getConnection(urlDatabase, loginDatabase, passwordDatabase);

        stmt = conn.createStatement();
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

    public void executeUpdate(String requeteMaj) throws SQLException {
        stmt.executeUpdate(requeteMaj);
    }
}
