/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vue;

import Controleur.RechercherSeanceSemaine;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Drakking
 */
public class FenetreEdt extends FenetreTemplate{
    private final JMenuBar menuBar = new JMenuBar();
    private final JMenu test1 = new JMenu("Emploi du temps");
    private final JMenu test2 = new JMenu("Récapitulatif des cours");
    
    private final JMenuItem item1 = new JMenuItem("Ouvrir");
    private final JMenuItem item2 = new JMenuItem("Fermer");
    
    private final Panneau grille = new Panneau();
    
    /*private final Object[][] data = {
        {"","","","","","","",""},
        {"","","","","","","",""},
        {"","","","","","","",""},
        {"","","","","","","",""},
        {"","","","","","","",""},
        {"","","","","","","",""},
        {"","","","","","","",""}    
        };
    private final String titre[] = {"","lundi","mardi","mercredi","jeudi","vendredi","samedi","dimanche"};
    private final JTable tableau = new JTable(data,titre);*/
    

    @SuppressWarnings("empty-statement")
    public FenetreEdt(String login) throws SQLException, ClassNotFoundException{
        
        fenetre.setSize(new Dimension(1200,1000));
        
        this.test1.add(item1);
        this.test1.addSeparator();
        item2.addActionListener(new ActionListener(){
        @Override
        public void actionPerformed(ActionEvent arg0) {
        System.exit(0);
        }
        });
        this.test1.add(item2);
        this.menuBar.add(test1);
        this.menuBar.add(test2);
        
       RechercherSeanceSemaine testSeance = new RechercherSeanceSemaine();
       ArrayList<ArrayList<String>> result = testSeance.SeanceSemaine(login,22);
       int i=0;
        if(!result.get(0).get(0).equals("Erreur : pas de cours disponibles actuellement")){
            for(ArrayList<String> iterator : result){
                i+=1;
                System.out.println("Cours n°"+i);
                System.out.println("ID de la séance : " + iterator.get(0));
                System.out.println("Semaine n°" + iterator.get(1));
                System.out.println("Jour de la séance : " + iterator.get(2));
                System.out.println("Heure de début : " + iterator.get(3));
                System.out.println("Matière : " + iterator.get(4));
                System.out.println("Type de cours : " + iterator.get(5));
                System.out.println("Professeur : " + iterator.get(6).toUpperCase());
                System.out.println("Salle : " + iterator.get(7));
                System.out.println("Site : " + iterator.get(8));
                for(int k=9;k<iterator.size();k++){
                    System.out.println("Groupe : " + iterator.get(k));
                }
                System.out.println("\n-----------------\n");
            }
        }else{
            JOptionPane.showMessageDialog(null,result.get(0).get(0),"Etat connexion",JOptionPane.ERROR_MESSAGE);
        }
        
        JTextPane cours = new JTextPane();
        cours.setBackground(Color.orange);
        cours.setBounds(81,276,149,74);
        fenetre.setJMenuBar(menuBar);
        fenetre.add(cours);
        fenetre.add(grille);
       //fenetre.add(new JScrollPane(tableau));
    
    }
}
