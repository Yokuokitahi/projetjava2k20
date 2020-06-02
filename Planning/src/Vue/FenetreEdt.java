package Vue;

import Controleur.InfosDB;
import Controleur.RechercherSeance;
import Controleur.RechercherSeanceSemaine;
import Modele.ConnexionDatabase;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.SQLException;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * @author Drakking
 */
public class FenetreEdt extends FenetreTemplate{
    private final JMenuBar menuBar = new JMenuBar();
    private final JMenu test1 = new JMenu("Etudiant");
    
    private final JMenuItem item1 = new JMenuItem("Emploi du temps");
    private final JMenuItem item2 = new JMenuItem("Récapitulatif des cours");
    private final JMenuItem item3 = new JMenuItem("Fermer");
    
    private final Font font = new Font("courier",Font.ROMAN_BASELINE,10);
    private final Font font2 = new Font("courier",Font.ROMAN_BASELINE,15);
    
    private final Panneau grille = new Panneau();
    
    private final JTextPane semaine = new JTextPane();
    
    public FenetreEdt() throws SQLException, ClassNotFoundException{
        fenetre.setSize(new Dimension(1200,1000));
        
        
        test1.add(item1);
        test1.add(item2);
        test1.addSeparator();
        test1.add(item3);
        
        item3.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent arg0) {
                System.exit(0);
            }
        });
        
        
       
        menuBar.add(test1);
     
    }
    
    public void CreerEDT(final String login, final int nbSemaine, final ConnexionDatabase connect) throws SQLException, ClassNotFoundException{
        
        fenetre.setContentPane(grille);
        grille.setLayout(null);
        
        semaine.setText("Semaine n°"+ nbSemaine);
        semaine.setFont(font2);
        semaine.setEditable(false);
        semaine.setBackground(grille.getBackground());
        semaine.setBounds(475, 10, 155, 20);
        grille.add(semaine);
        
        JButton suivant = new JButton("Semaine suivante");
        suivant.setBounds(550,30,155,20);
        
        JButton prec = new JButton("Semaine précédente");
        prec.setBounds(350,30,155,20);
        
        
        item1.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent arg0) {
                try {
                grille.removeAll();
                grille.repaint();
                int nbSemaineact = connect.SQLNumSemaine(connect);
                CreerEDT(login,nbSemaineact, connect);
                } catch (SQLException | ClassNotFoundException ex) {
                    Logger.getLogger(FenetreEdt.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        
        item2.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent arg0) {
                
                try {
                    grille.removeAll();
                    grille.repaint();
                    Recap(login, connect);
                } catch (SQLException | ClassNotFoundException ex) {
                    Logger.getLogger(FenetreEdt.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        
        prec.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent ae) {
                try {
                    grille.removeAll();
                    grille.repaint();
                    CreerEDT(login, nbSemaine-1, connect);
                } catch (SQLException | ClassNotFoundException ex) {
                    Logger.getLogger(FenetreEdt.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            
        });
        
        suivant.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent ae) {
                try {
                    grille.removeAll();
                    grille.repaint();
                    CreerEDT(login, nbSemaine+1, connect);
                } catch (SQLException | ClassNotFoundException ex) {
                    Logger.getLogger(FenetreEdt.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            
        });

        fenetre.setJMenuBar(menuBar);
        grille.add(suivant);
        grille.add(prec);        
        
        String infox;
        int posX=0, posY=0;
        RechercherSeanceSemaine testSeance = new RechercherSeanceSemaine();
        ArrayList<ArrayList<String>> result = testSeance.SeanceSemaine(login,nbSemaine, connect);
        if(!result.get(0).get(0).equals("Erreur : pas de cours disponibles actuellement")){
            for(ArrayList<String> iterator : result){
                infox = iterator.get(4)+"\n"+iterator.get(6).toUpperCase()+"\n"+iterator.get(5)+"\n"+iterator.get(7)+"\n";
                for(int k=9;k<iterator.size();k++){//AJOUTER LES INFOS DU COURS
                    infox+= "Gr."+iterator.get(k)+" ";
                }
                
                switch (iterator.get(2)) {
                    case "0":
                        posX = 81;
                        break;
                    case "1":
                        posX=231;
                        break;
                    case "2":
                        posX=381;
                        break;
                    case "3":
                        posX=531;
                        break;
                    case "4":
                        posX=681;
                        break;
                    case "5":
                        posX=831;
                        break;
                    default: 
                        break;
                }
                switch(iterator.get(3)){
                    case "0":
                        posY=101;
                        break;
                    case "1":
                        posY=188;
                        break;
                    case "2":
                        posY=276;
                        break;
                    case "3":
                        posY=363;
                        break;
                    case "4":
                        posY=451;
                        break;
                    case "5":
                        posY=538;
                        break;
                    case "6":
                        posY=626;
                        break;
                }
                JTextPane cours = new JTextPane();
                cours.setBackground(Color.orange);
                cours.setFont(font);
                cours.setEditable(false);
                cours.setBounds(posX,posY,149,74);
                cours.setText(infox);
                grille.add(cours);
            }
        }else{
            JOptionPane.showMessageDialog(null,result.get(0).get(0));
        }
    }
    
    public void Recap(final String login, final ConnexionDatabase connect) throws SQLException, ClassNotFoundException{
        
        InfosDB infos = new InfosDB();
        RechercherSeance heures = new RechercherSeance();
        ArrayList<String> matieres = infos.getMatiere(connect);
        fenetre.setJMenuBar(menuBar); 
        int posX = 81,posY=101, pas = 25, i=0;
        final JPanel buffer = new JPanel();
        buffer.setLayout(null);
        fenetre.setContentPane(buffer);
        
        item1.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent arg0) {
                try {
                buffer.removeAll();
                buffer.repaint();
                int nbSemaineact = connect.SQLNumSemaine(connect);
                CreerEDT(login,nbSemaineact, connect);
                } catch (SQLException | ClassNotFoundException ex) {
                    Logger.getLogger(FenetreEdt.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        
        item2.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent arg0) {
                try {
                    grille.removeAll();
                    grille.repaint();
                    Recap(login, connect);
                } catch (SQLException | ClassNotFoundException ex) {
                    Logger.getLogger(FenetreEdt.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        ArrayList<String> infosHeures;
        ArrayList<String> informations = new ArrayList<>();
        for(String iterator:matieres){
            infosHeures = heures.RecapSeance(login, iterator, connect);
            double nbHeures = (infosHeures.size())*1.5;
            informations.add(iterator + "  " + nbHeures + " heures");
        }
       
        for(String iterator:informations){
            JTextPane cours = new JTextPane();
                cours.setBackground(Color.LIGHT_GRAY);
                cours.setFont(font);
                cours.setEditable(false);
                cours.setBounds(posX,posY+(i*pas),149,20);
                cours.setText(iterator);
                buffer.add(cours);
                i++;
        }
        
    }
}