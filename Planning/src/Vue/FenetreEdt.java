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
import javax.swing.table.TableColumn;
import javax.swing.text.*;


/**
 *
 * @author Drakking
 */
public class FenetreEdt extends FenetreTemplate{
    private final JMenuBar menuBar = new JMenuBar();
    private final JMenu menuEtudiant = new JMenu("Etudiant");
    
    private final JMenuItem item1 = new JMenuItem("Emploi du temps");
    private final JMenuItem item2 = new JMenuItem("Recap");
    private final JMenuItem item3 = new JMenuItem("Fermer");
    private final JMenuItem deco = new JMenuItem("Déconnexion");
    
    private final Font font = new Font("courier",Font.ROMAN_BASELINE,10);
    private final Font font2 = new Font("courier",Font.ROMAN_BASELINE,15);
    
    private final JPanel buffer = new JPanel();
    private final JPanel buffer2 = new JPanel();
    private final Panneau grille = new Panneau();
    
    private final JTextPane semaine = new JTextPane();
    
    public FenetreEdt(final String login) throws SQLException, ClassNotFoundException{
        fenetre.setSize(new Dimension(1200,1000)); 
 
        grille.setLayout(null);
        menuEtudiant.add(item1);
        menuEtudiant.add(item2);
        menuEtudiant.addSeparator();
        menuEtudiant.add(item3);
        menuBar.add(menuEtudiant);
        menuBar.add(deco);
        
        deco.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
                fenetre.dispose();
                FenetreConnexion test;
                try {
                    test = new FenetreConnexion();
                    test.constructPanel();
                } catch (SQLException | ClassNotFoundException ex) {
                    Logger.getLogger(FenetreEdt.class.getName()).log(Level.SEVERE, null, ex);
                }
                
            }
        });
        
        item1.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent arg0) {
                try {
                    grille.removeAll();
                    grille.repaint();
                    buffer.removeAll();
                    buffer.repaint();
                    buffer2.removeAll();
                    buffer2.repaint();
                    int nbSemaineact= ConnexionDatabase.SQLNumSemaine();
                    CreerEDT(login, nbSemaineact);
                } catch (ClassNotFoundException | SQLException ex) {
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
                    buffer.removeAll();
                    buffer.repaint();
                    buffer2.removeAll();
                    buffer2.repaint();
                    Recap(login);
                } catch (SQLException | ClassNotFoundException ex) {
                    Logger.getLogger(FenetreEdt.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        item3.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent arg0) {
                System.exit(0);
            }
        });

           
    }
    
    public void CreerEDT(final String login, final int nbSemaine) throws SQLException, ClassNotFoundException{
        grille.setVisible(true);
        buffer.setVisible(false);
        buffer2.setVisible(false);
        fenetre.setContentPane(grille);
        semaine.setText("Semaine n°"+ nbSemaine);
        semaine.setFont(font2);
        semaine.setEditable(false);
        semaine.setBackground(fenetre.getBackground());
        semaine.setBounds(475, 10, 155, 20);
        grille.add(semaine);
        JButton suivant = new JButton("Semaine suivante");
        suivant.setBounds(550,30,155,20);
        
        JButton prec = new JButton("Semaine précédente");
        prec.setBounds(350,30,155,20);
        
        JButton affichage = new JButton("Afficher en liste");
        affichage.setBounds(1000,200,155,20);
        
        affichage.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                grille.removeAll();
                grille.repaint();
                buffer.removeAll();
                buffer.repaint();
                try {
                    ListeEdt(login, nbSemaine);
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
                    buffer.removeAll();
                    buffer.repaint();
                    CreerEDT(login, nbSemaine-1);
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
                    buffer.removeAll();
                    buffer.repaint();
                    CreerEDT(login, nbSemaine+1);
                } catch (SQLException | ClassNotFoundException ex) {
                    Logger.getLogger(FenetreEdt.class.getName()).log(Level.SEVERE, null, ex);
                }
            }         
        });

        fenetre.setJMenuBar(menuBar);
        grille.add(suivant);
        grille.add(prec); 
        grille.add(affichage);
        
        String infox;
        int posX=0, posY=0;
        RechercherSeanceSemaine testSeance = new RechercherSeanceSemaine();
        ArrayList<ArrayList<String>> result = testSeance.SeanceSemaine(login,nbSemaine);
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
                StyledDocument doc = cours.getStyledDocument();
                SimpleAttributeSet center = new SimpleAttributeSet();
                StyleConstants.setAlignment(center, StyleConstants.ALIGN_CENTER);
                doc.setParagraphAttributes(0, doc.getLength(), center, false);
                if("Probabilité".equals(iterator.get(4))){
                    cours.setBackground(Color.green);
                }
                if("Physique".equals(iterator.get(4))){
                    cours.setBackground(Color.cyan);
                }
                if("Analyse de Fourier".equals(iterator.get(4))){
                    cours.setBackground(Color.orange);
                }
                if("Initiation réseau".equals(iterator.get(4))){
                    cours.setBackground(Color.magenta);
                }     
                //cours.setBackground(Color.orange);
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
    
    public void AdminCreerEDT(final String login, final int nbSemaine) throws SQLException, ClassNotFoundException{
        grille.setVisible(true);
        buffer.setVisible(false);
        buffer2.setVisible(false);
        fenetre.setContentPane(grille);
        semaine.setText("Semaine n°"+ nbSemaine);
        semaine.setFont(font2);
        semaine.setEditable(false);
        semaine.setBackground(fenetre.getBackground());
        semaine.setBounds(475, 10, 155, 20);
        grille.add(semaine);
        menuBar.remove(menuEtudiant);
        JButton retour = new JButton("Revenir à la recherche");
        retour.setBounds(100,30,155,20);
        
        JButton suivant = new JButton("Semaine suivante");
        suivant.setBounds(550,30,155,20);
        
        JButton prec = new JButton("Semaine précédente");
        prec.setBounds(350,30,155,20);
        
        prec.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent ae) {
                try {
                    grille.removeAll();
                    grille.repaint();
                    buffer.removeAll();
                    buffer.repaint();
                    AdminCreerEDT(login, nbSemaine-1);
                } catch (SQLException | ClassNotFoundException ex) {
                    Logger.getLogger(FenetreEdt.class.getName()).log(Level.SEVERE, null, ex);
                }
            }            
        });
        
        retour.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    FenetreAdmin admin = new FenetreAdmin();
                    fenetre.dispose();
                    admin.BaseAdmin();
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
                    buffer.removeAll();
                    buffer.repaint();
                    AdminCreerEDT(login, nbSemaine+1);
                } catch (SQLException | ClassNotFoundException ex) {
                    Logger.getLogger(FenetreEdt.class.getName()).log(Level.SEVERE, null, ex);
                }
            }         
        });

        fenetre.setJMenuBar(menuBar);
        grille.add(suivant);
        grille.add(prec); 
        grille.add(retour);
        
        String infox;
        int posX=0, posY=0;
        RechercherSeanceSemaine testSeance = new RechercherSeanceSemaine();
        ArrayList<ArrayList<String>> result = testSeance.SeanceSemaine(login,nbSemaine);
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
                StyledDocument doc = cours.getStyledDocument();
                SimpleAttributeSet center = new SimpleAttributeSet();
                StyleConstants.setAlignment(center, StyleConstants.ALIGN_CENTER);
                doc.setParagraphAttributes(0, doc.getLength(), center, false);
                if("Probabilité".equals(iterator.get(4))){
                    cours.setBackground(Color.green);
                }
                if("Physique".equals(iterator.get(4))){
                    cours.setBackground(Color.cyan);
                }
                if("Analyse de Fourier".equals(iterator.get(4))){
                    cours.setBackground(Color.orange);
                }
                if("Initiation réseau".equals(iterator.get(4))){
                    cours.setBackground(Color.magenta);
                } 
                //cours.setBackground(Color.orange);
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
    
    public void RefCreerEDT(final String login, final int nbSemaine) throws SQLException, ClassNotFoundException{
        grille.setVisible(true);
        buffer.setVisible(false);
        buffer2.setVisible(false);
        fenetre.setContentPane(grille);
        semaine.setText("Semaine n°"+ nbSemaine);
        semaine.setFont(font2);
        semaine.setEditable(false);
        semaine.setBackground(fenetre.getBackground());
        semaine.setBounds(475, 10, 155, 20);
        grille.add(semaine);
        menuBar.remove(menuEtudiant);
        JButton retour = new JButton("Revenir à la recherche");
        retour.setBounds(100,30,155,20);
        
        JButton suivant = new JButton("Semaine suivante");
        suivant.setBounds(550,30,155,20);
        
        JButton prec = new JButton("Semaine précédente");
        prec.setBounds(350,30,155,20);
        
        prec.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent ae) {
                try {
                    grille.removeAll();
                    grille.repaint();
                    buffer.removeAll();
                    buffer.repaint();
                    RefCreerEDT(login, nbSemaine-1);
                } catch (SQLException | ClassNotFoundException ex) {
                    Logger.getLogger(FenetreEdt.class.getName()).log(Level.SEVERE, null, ex);
                }
            }            
        });
        
        retour.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
                FenetreReferent ref = new FenetreReferent();
                try {
                    fenetre.dispose();
                    ref.Recherche();
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
                    buffer.removeAll();
                    buffer.repaint();
                    RefCreerEDT(login, nbSemaine+1);
                } catch (SQLException | ClassNotFoundException ex) {
                    Logger.getLogger(FenetreEdt.class.getName()).log(Level.SEVERE, null, ex);
                }
            }         
        });

        fenetre.setJMenuBar(menuBar);
        grille.add(suivant);
        grille.add(prec); 
        grille.add(retour);
        
        String infox;
        int posX=0, posY=0;
        RechercherSeanceSemaine testSeance = new RechercherSeanceSemaine();
        ArrayList<ArrayList<String>> result = testSeance.SeanceSemaine(login,nbSemaine);
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
                StyledDocument doc = cours.getStyledDocument();
                SimpleAttributeSet center = new SimpleAttributeSet();
                StyleConstants.setAlignment(center, StyleConstants.ALIGN_CENTER);
                doc.setParagraphAttributes(0, doc.getLength(), center, false);
                if("Probabilité".equals(iterator.get(4))){
                    cours.setBackground(Color.green);
                }
                if("Physique".equals(iterator.get(4))){
                    cours.setBackground(Color.cyan);
                }
                if("Analyse de Fourier".equals(iterator.get(4))){
                    cours.setBackground(Color.orange);
                }
                if("Initiation réseau".equals(iterator.get(4))){
                    cours.setBackground(Color.magenta);
                } 
                //cours.setBackground(Color.orange);
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
    
    public void Recap(final String login) throws SQLException, ClassNotFoundException{
        
        grille.setVisible(false);
        buffer.setVisible(false);
        buffer2.setVisible(true);
        buffer2.setBackground(fenetre.getBackground());
        RechercherSeance heures = new RechercherSeance();
        ArrayList<String> matieres = InfosDB.getMatiere();
        fenetre.setJMenuBar(menuBar); 
        fenetre.setContentPane(buffer2);
        
        ArrayList<String> informations = new ArrayList<>();
        ArrayList<String> infosHeures;
        
        
        for(String iterator:matieres){
            infosHeures = heures.RecapSeance(login, iterator);
            double nbHeures = (infosHeures.size())*1.5;
            informations.add(nbHeures + " heures");
        }
         Object[][] table = new Object[1][informations.size()];
         
         for (int j=0; j < informations.size();j++)
         {
             table[0][j] = informations.get(j);
         }
         
         String colonne[] = new String[matieres.size()];
         
         for (int j=0; j < matieres.size();j++)
         {
             colonne[j] = matieres.get(j);
         }
         
         JTable tableau = new JTable(table,colonne);
         JScrollPane scroll = new JScrollPane(tableau);
         scroll.getViewport().setBackground(fenetre.getBackground());
         
         buffer2.add(scroll);
         
    }
    
    public void ListeEdt(final String login, final int nbSemaine) throws SQLException, ClassNotFoundException{
        grille.setVisible(false);
        buffer.setVisible(true);
        buffer2.setVisible(false);
        buffer.setBackground(fenetre.getBackground());
        JTextPane jours1 = new JTextPane();
        JTextPane jours2 = new JTextPane();
        JTextPane jours3 = new JTextPane();
        JTextPane jours4 = new JTextPane();
        JTextPane jours5 = new JTextPane();
        JTextPane jours6 = new JTextPane();
        fenetre.setJMenuBar(menuBar);
        fenetre.setContentPane(buffer);
        
        semaine.setText("Semaine n°"+ nbSemaine);
        semaine.setFont(font2);
        semaine.setEditable(false);
        semaine.setBackground(fenetre.getBackground());
        semaine.setBounds(475, 10, 155, 20);
        buffer.add(semaine);
        
        JButton suivant = new JButton("Semaine suivante");
        suivant.setBounds(550,30,155,20);
        suivant.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent ae) {
                try {
                    buffer.removeAll();
                    buffer.repaint();
                    ListeEdt( login, nbSemaine+1);
                } catch (SQLException | ClassNotFoundException ex) {
                    Logger.getLogger(FenetreEdt.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            
        });
        
        JButton prec = new JButton("Semaine précédente");
        prec.setBounds(350,30,155,20);
        prec.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent ae) {
                try {
                    buffer.removeAll();
                    buffer.repaint();
                    ListeEdt( login, nbSemaine-1);
                } catch (SQLException | ClassNotFoundException ex) {
                    Logger.getLogger(FenetreEdt.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            
        });
        
        JButton affichage = new JButton("Afficher en tableau");
        affichage.setBounds(1000,200,155,20);   
        affichage.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                grille.removeAll();
                grille.repaint();
                try {
                    CreerEDT(login, nbSemaine);
                } catch (SQLException | ClassNotFoundException ex) {
                    Logger.getLogger(FenetreEdt.class.getName()).log(Level.SEVERE, null, ex);
                }
            }   
        });
        
        
        //buffer.add(suivant);
        //buffer.add(prec); 
        buffer.add(affichage);
        //int posX=200, posY=150, var =0;
        String infox; 
        
        RechercherSeanceSemaine testSeance = new RechercherSeanceSemaine();
        ArrayList<ArrayList<String>> result = testSeance.SeanceSemaine(login,nbSemaine);

        int max = 0, lundi=1,mardi=1,mercredi=1,jeudi=1,vendredi=1,samedi=1;
            for (ArrayList<String> result1 : result) {
                if (result1.size() > max) {
                    max = result1.size();
                }
            }
            
        Object[][] table = {{" ", " ", " ", " ", " ", " "," "," "},
                           {" ", " ", " ", " ", " ", " "," "," "},
                           {" ", " ", " ", " ", " ", " "," "," "},
                           {" ", " ", " ", " ", " ", " "," "," "},
                           {" ", " ", " ", " ", " ", " "," "," "},
                           {" ", " ", " ", " ", " ", " "," "," "},
        };
        //ArrayList<String> infos = new ArrayList<>();
        String[] jours = {"Jour", "Cours n°1", "Cours n°2", "Cours n°3", "Cours n°4", "Cours n°5","Cours n°6","Cours n°7"};
        if(!result.get(0).get(0).equals("Erreur : pas de cours disponibles actuellement")){
            for(ArrayList<String> iterator : result){
                
                if(iterator.get(3).equals("0")){
                    iterator.set(3, "08:30 - 10:00");
                }
                if(iterator.get(3).equals("1")){
                    iterator.set(3, "10:15 - 11:45");
                 
                }
                if(iterator.get(3).equals("2")){
                    iterator.set(3, "12:00 - 13:30");
                    
                }
                if(iterator.get(3).equals("3")){
                    iterator.set(3, "13:45 - 15:15");
                }
                if(iterator.get(3).equals("4")){
                    iterator.set(3, "15:30 - 17:00");
                }
                if(iterator.get(3).equals("5")){
                    iterator.set(3, "17:15 - 18:45");
                }
                if(iterator.get(3).equals("6")){
                    iterator.set(3, "19:30 - 20:30");
                }
                infox = iterator.get(3)+ " "+ iterator.get(4)+"  "+iterator.get(6).toUpperCase()+"  "+iterator.get(7)+"  "+iterator.get(5)+"\n";    

                for(int k=9;k<iterator.size();k++){//AJOUTER LES INFOS DU COURS
                    infox+= "Gr."+iterator.get(k)+" ";
                    
                }
                if("0".equals(iterator.get(2))){
                    System.out.println(lundi);
                    table[0][0]="Lundi";
                    table[0][lundi] = infox;
                    lundi++;
                }
                if("1".equals(iterator.get(2))){
                    table[1][0]="Mardi";
                    table[1][mardi] = infox;
                    mardi++;
                }
                if("2".equals(iterator.get(2))){
                    table[2][0]="Mercredi";
                    table[2][mercredi] = infox;
                    mercredi++;
                }
                if("3".equals(iterator.get(2))){
                    table[3][0]="Jeudi";
                    table[3][jeudi] = infox;
                    jeudi++;
                }
                if("4".equals(iterator.get(2))){
                    table[4][0]="Vendredi";
                    table[4][vendredi] = infox;
                    vendredi++;
                }
                if("5".equals(iterator.get(2))){
                    table[5][0]="Samedi";
                    table[5][samedi] = infox;
                    samedi++;
                }
            }
            
            JTable tableau2 = new JTable(table,jours);
            
            tableau2.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
            TableColumn colonne0 = tableau2.getColumnModel().getColumn(0);
            colonne0.setPreferredWidth(100);
            TableColumn colonne1 = tableau2.getColumnModel().getColumn(1);
            colonne1.setPreferredWidth(200);
            TableColumn colonne2 = tableau2.getColumnModel().getColumn(2);
            colonne2.setPreferredWidth(200);
            TableColumn colonne3 = tableau2.getColumnModel().getColumn(3);
            colonne3.setPreferredWidth(200);
            TableColumn colonne4 = tableau2.getColumnModel().getColumn(4);
            colonne4.setPreferredWidth(200);
            TableColumn colonne5 = tableau2.getColumnModel().getColumn(5);
            colonne5.setPreferredWidth(200);
            TableColumn colonne6 = tableau2.getColumnModel().getColumn(6);
            colonne6.setPreferredWidth(200);
            TableColumn colonne7 = tableau2.getColumnModel().getColumn(7);
            colonne7.setPreferredWidth(200);
            
            JScrollPane scroll = new JScrollPane(tableau2);
            scroll.setPreferredSize(new Dimension(1100,900));
            scroll.getViewport().setBackground(fenetre.getBackground());
            
            buffer.add(scroll);           
        }else{
            JOptionPane.showMessageDialog(null,result.get(0).get(0));
        }
    }
}