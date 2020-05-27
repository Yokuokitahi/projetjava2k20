package Vue;

import Controleur.RechercherSeanceSemaine;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author xavan
 */
public class Planning {

<<<<<<< Updated upstream
    public static void main(String[] args) throws SQLException {
       FenetreConnexion test = new FenetreConnexion();
       test.constructPanel();
    }
    
=======
        
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        
        
       FenetreConnexion test = new FenetreConnexion();
       test.constructPanel();
       
       
       int i=0;
       
       RechercherSeanceSemaine testSeance = new RechercherSeanceSemaine();
       ArrayList<ArrayList<String>> result = testSeance.SeanceSemaineEtudiant("xavier.antoine@edu.ece.fr",22);
       
        if(!result.get(0).get(0).equals("Erreur : pas de cours disponibles actuellement")){
            for(ArrayList<String> iterator : result){
                i+=1;
                System.out.println("Cours n°"+i);
                System.out.println("ID de la séance : " + iterator.get(0));
                System.out.println("Semaine n°" + iterator.get(1));
                System.out.println("Jour de la séance : " + iterator.get(2));
                System.out.println("Heure de début : " + iterator.get(3));
                System.out.println("Heure de fin : " + iterator.get(4));
                System.out.println("Matière : " + iterator.get(5));
                System.out.println("Type de cours : " + iterator.get(6));
                System.out.println("Professeur : " + iterator.get(7).toUpperCase());
                System.out.println("Salle : " + iterator.get(8));
                System.out.println("Site : " + iterator.get(9));
                for(int k=10;k<iterator.size();k++){
                    System.out.println("Groupe : " + iterator.get(k));
                }
                System.out.println("\n-----------------\n");
            }
        }else{
            System.out.println(result.get(0).get(0));
        } 
    }   

>>>>>>> Stashed changes
}
