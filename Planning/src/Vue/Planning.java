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
       /* ArrayList<String> tt;
       RechercherSeance test = new RechercherSeance();
       tt = test.RecapSeance("malo", "Initiation réseau");
       double nbHeures = (tt.size())*1.5;
       System.out.println("Initiation réseau : " + nbHeures+ " heures");*/
       // ArrayList<ArrayList<String>> test = RechercherSeance.Seance();
        //System.out.println(test);
        //System.out.println(test.get(0).get(3));
    }   
}
