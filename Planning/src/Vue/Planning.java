package Vue;

import Controleur.RechercherSeanceSemaine;
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
       
       RechercherSeanceSemaine testSeance = new RechercherSeanceSemaine();
       ArrayList<String> result = testSeance.SeanceSemaine("abdoulnasir@edu.ece.fr",22);
       
        //for (String result1 : result) {
          //  System.out.println(result1);
        //}
    }   
}
