package Vue;

import Controleur.AjouterDB;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author xavan
 */
public class Planning {
        
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        
       //FenetreConnexion test = new FenetreConnexion();
       //test.constructPanel();
       AjouterDB yes = new AjouterDB();
       ArrayList<String> groupes = new ArrayList<>();
       groupes.add("A");
       groupes.add("B");
       yes.AjouterSeance("2020-06-06", "10:15", "Analyse de Fourier", "Cours Magistral", "coudray", groupes, "2023", "Amphi A");
    }   
}
