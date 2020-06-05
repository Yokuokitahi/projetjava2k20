/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vue;

import Controleur.AjouterDB;
import Controleur.InfosDB;
import Controleur.RechercherSeance;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import javax.swing.table.TableColumn;

/**
 *
 * @author Drakking
 */
public class FenetreAdmin extends FenetreTemplate{
    
    private final JMenuBar menuBar = new JMenuBar();
    private final JMenu test1 = new JMenu("Admin");
    
    private final JPanel pan = new JPanel();
    private final JComboBox jours = new JComboBox();
    private final JComboBox mois = new JComboBox();
    private final JComboBox annee = new JComboBox();
    private final JComboBox heure = new JComboBox();
    private final JComboBox min = new JComboBox();
    private final JComboBox matiere = new JComboBox();
    private final JComboBox typeCours = new JComboBox();
    private final JComboBox enseignants = new JComboBox();
    private final JComboBox salles = new JComboBox();
    private final JComboBox promotion = new JComboBox();
    private final JComboBox groupes = new JComboBox();
    
    private String j;
    private String m;
    private String a;
    private String h;
    private String mn;
    private String date;
    private String horaire;
    private String typeC;
    private String mat;
    private String prof;
    private String salle;
    private String promo;
    
    
    public FenetreAdmin(String login)throws SQLException, ClassNotFoundException{
        fenetre.setTitle("Administration");
        fenetre.setSize(1200,1000);
        pan.setLayout(null);

        menuBar.add(test1);
        fenetre.setContentPane(pan);
        fenetre.setJMenuBar(menuBar);
        
    }
    
    public void ajouterCours() throws SQLException, ClassNotFoundException{
        
        //fonction pour avoir matieres + typecours depuis BDD
        ArrayList<String> matieres = InfosDB.getMatiere();
        ArrayList<String> type = InfosDB.getTypeDeCours();
        ArrayList<String> enseignant = InfosDB.getEnseignant();
        ArrayList<String> sal = InfosDB.getSalles();
        ArrayList<String> prom = InfosDB.getPromotion();
        
        //Creations de boutons
        JButton ajouter = new JButton("Ajouter un cours");
        ajouter.setBackground(Color.GREEN);
        ajouter.setBounds(50,50,160,50);
       
        JButton valide = new JButton("Valider");
        valide.setBounds(250,350,80,30);
        
        JButton ajouterGr = new JButton("Ajouter Groupe");
        ajouterGr.setBounds(300,250,130,30);

        //on créer des menus deroulants
        jours.setBounds(50, 150, 80, 30);
        jours.addItem("Jour");
        
        mois.setBounds(150,150,80,30);
        mois.addItem("Mois");
        
        annee.setBounds(250,150,80,30);
        annee.addItem("Année");
        
        heure.setBounds(350,150,80,30);
        heure.addItem("Heure");
        
        min.setBounds(450,150,80,30);
        min.addItem("Minutes");
        
        //Recupere les matieres ds un menu deroulant
        matiere.setBounds(50,200,120,30);
        matiere.addItem("Matière");
        for (int i = 0; i < matieres.size();i++){
            matiere.addItem(matieres.get(i));
        }
        
        //Recupere type de cours ds un menu deroulant
        typeCours.setBounds(200,200,120,30);
        typeCours.addItem("Type de cours");
        for (int i = 0; i < type.size();i++){
            typeCours.addItem(type.get(i));
        }
        
        //Recupere le prof associé a une matiere via menu deroulant
        enseignants.setBounds(350,200,120,30);
        enseignants.addItem("Enseignants");
        for (int i = 0; i < enseignant.size();i++){
            enseignants.addItem(enseignant.get(i));
        }
        
        salles.setBounds(50,250,120,30);
        salles.addItem("Salles");
        for (int i = 0; i < sal.size();i++){
            salles.addItem(sal.get(i));
        }
        
        promotion.setBounds(200,250,100,30);
        promotion.addItem("Promo");
        for (int i = 0; i < prom.size();i++){
            promotion.addItem(prom.get(i));
        }
        
        groupes.setBounds(450,250,80,30);
        groupes.addItem("Groupes");
        
        //setup des menus deroulants
        for (int i=1;i<=31;i++)
        {
            jours.addItem(i);
        }
        
        for (int i=1;i<=12;i++)
        {
            mois.addItem(i);
        }
        
        for (int i=2020;i<=2023;i++)
        {
            annee.addItem(i);
        }
        
        for (int i=8;i<=20;i++)
        {
            heure.addItem(i);
        }
        
        for (int i=0;i<=45;i++)
        {
            min.addItem(i);
            i=i-1+15;
        }
        
        
        //on ajoute les menus a notre pannel
        pan.add(valide);
        pan.add(ajouterGr);
        pan.add(jours);
        pan.add(mois);
        pan.add(annee);
        pan.add(heure);
        pan.add(min);
        pan.add(typeCours);
        pan.add(matiere);
        pan.add(enseignants);  
        pan.add(promotion);
        pan.add(salles);
        pan.add(groupes);
        groupes.setVisible(false);
        
        ajouterGr.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent arg0) {
                promo = promotion.getSelectedItem().toString();
                
                ArrayList<String> gr = new ArrayList<>();
                
                if (!"Promo".equals(promo)){  
                try {
                    gr = InfosDB.getGroupes(promo);
                } catch (SQLException | ClassNotFoundException ex) {
                    Logger.getLogger(FenetreAdmin.class.getName()).log(Level.SEVERE, null, ex);
                }
                groupes.removeAllItems();
                groupes.addItem("Groupes");
                for (int i = 0; i < gr.size();i++){
                        groupes.addItem(gr.get(i));
                }
                    
                groupes.setVisible(true);
                
                }else{
                groupes.setVisible(false);
                }
            }
        });
        
        
        //Boutons de validation avec action (on recupere les infos saisies)
        valide.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent arg0) {
                j = jours.getSelectedItem().toString();
                m = mois.getSelectedItem().toString();
                a = annee.getSelectedItem().toString();
                h = heure.getSelectedItem().toString();
                mn= min.getSelectedItem().toString();
                date = a +"-"+m+"-"+j;
                horaire = h + ":" + mn;
                prof = enseignants.getSelectedItem().toString();
                typeC = typeCours.getSelectedItem().toString();
                mat = matiere.getSelectedItem().toString();
                salle = salles.getSelectedItem().toString();
                promo = promotion.getSelectedItem().toString();
                ArrayList<String> grp = new ArrayList<>();
                grp.add(groupes.getSelectedItem().toString());
                System.out.println(date);
                System.out.println(horaire);
                System.out.println(typeC);
                System.out.println(mat);
                System.out.println(prof);
                System.out.println(salle);
                System.out.println(promo);
                
                try {
                    AjouterDB.AjouterSeance(date, horaire, mat, typeC, prof, grp, promo, salle);
                } catch (SQLException | ClassNotFoundException ex) {
                    Logger.getLogger(FenetreAdmin.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        
        //Bouton ajouter cours
        ajouter.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent arg0) {
                
            }
        });
 
        pan.add(ajouter);
    }
    
    public void supprimerCours() throws SQLException, ClassNotFoundException{
        
        JPanel pan1 = new JPanel();
        fenetre.setContentPane(pan1);
        ArrayList<ArrayList<String>> recup = RechercherSeance.Seance();
        
        
        String[] titre = {"Date","Heure de début","Cours","Type de cours","Professeur","Salle","Gr","Gr","suppr","modif"};
        
        for (int i=0; i<recup.size(); i++){
                recup.get(i).remove(0);
                recup.get(i).remove(0);          
        }
        
        int max = 0;
            for (ArrayList<String> result1 : recup) {
                if (result1.size() > max) {
                    max = result1.size();
                }
            }
        
        Object [][] infos =  new Object[recup.size()][max+2];
        
        for (int i=0; i<recup.size(); i++){
            for (int s = 0; s< recup.get(i).size();s++){
                infos[i][s] = recup.get(i).get(s);
            }
        }
        
        JTable tableau;
        tableau = new JTable(infos,titre);
        tableau.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
            TableColumn colonne0 = tableau.getColumnModel().getColumn(0);
            colonne0.setPreferredWidth(80);
            TableColumn colonne1 = tableau.getColumnModel().getColumn(1);
            colonne1.setPreferredWidth(100);
            TableColumn colonne2 = tableau.getColumnModel().getColumn(2);
            colonne2.setPreferredWidth(120);
            TableColumn colonne3 = tableau.getColumnModel().getColumn(3);
            colonne3.setPreferredWidth(120);
            TableColumn colonne4 = tableau.getColumnModel().getColumn(4);
            colonne4.setPreferredWidth(100);
            TableColumn colonne5 = tableau.getColumnModel().getColumn(5);
            colonne5.setPreferredWidth(80);
            TableColumn colonne6 = tableau.getColumnModel().getColumn(6);
            colonne6.setPreferredWidth(60);
            TableColumn colonne7 = tableau.getColumnModel().getColumn(7);
            colonne7.setPreferredWidth(60);
            
            JScrollPane scroll = new JScrollPane(tableau);
            scroll.setPreferredSize(new Dimension(1100,900));
            
            pan1.add(scroll);
            
    }
    
}

    