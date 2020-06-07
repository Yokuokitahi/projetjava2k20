package Vue;

import Controleur.InfosDB;
import Controleur.RechercherSeance;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author xavan
 */
public class Planning {
        
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        
       FenetreConnexion test = new FenetreConnexion();
       test.constructPanel();
       
    }   
}
