package Vue;

import Controleur.RechercherSeance;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author xavan
 */
public class Planning {

    public static void main(String[] args) throws SQLException {
       //FenetreConnexion test = new FenetreConnexion();
       //test.constructPanel();
       
       RechercherSeance testSeance = new RechercherSeance();
       
       ArrayList<String> result = testSeance.Seance("abdoulnasir@edu.ece.fr");
       
        for (String result1 : result) {
            System.out.println(result1);
        }
    }
    
}
