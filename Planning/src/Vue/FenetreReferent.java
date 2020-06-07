package Vue;

import Controleur.InfosDB;
import Modele.ConnexionDatabase;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;


 /* @author Xavier Antoine */

public class FenetreReferent extends FenetreTemplate{
    
    private final JMenuBar menuBar = new JMenuBar();
    private final JMenu referent = new JMenu("Référent Pédagogique");
    private final JMenuItem deco = new JMenuItem("Déconnexion");
    private final JMenuItem close = new JMenuItem("Fermer");

    private final JPanel buff = new JPanel();
    private final JComboBox promotion = new JComboBox();
    private final JComboBox groupes = new JComboBox();
    
    public FenetreReferent(){
        ///Fenetre du réferent
        
        fenetre.setTitle("Référent Pédagogique");
        fenetre.setSize(1200,500);
        buff.setLayout(null);
        buff.setBackground(fenetre.getBackground());
        
        referent.add(deco);
        referent.addSeparator();
        referent.add(close);
        
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
        
        close.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent arg0) {
                System.exit(0);
            }
        });
        
        menuBar.add(referent);
        fenetre.setContentPane(buff);
        fenetre.setJMenuBar(menuBar);
    }
    
    
    public void Recherche() throws SQLException, ClassNotFoundException{
    //Methode pour rechercher edt selon nom prenom prof/eleves ou selon groupe/promo
        
    ArrayList<String> prom = InfosDB.getPromotion();
    final JRadioButton prof; 
    final JRadioButton gr;
    final JRadioButton etudiant; 
    final JTextField nom = new JTextField();
    final JTextField prenom = new JTextField();
    final JButton rech = new JButton("Rechercher un emploi du temps");
    rech.setBackground(Color.GREEN);
    
    ButtonGroup G1; 
    JLabel text = new JLabel("Choississez une recherche :");
    prof = new JRadioButton(); 
    gr = new JRadioButton();
    etudiant = new JRadioButton(); 
    final JButton ajouterGr = new JButton("Ajouter un groupe");
    G1 = new ButtonGroup(); 

    prof.setText("Professeur"); 
    gr.setText("Groupe");
    etudiant.setText("Etudiant"); 
    
    prof.setBackground(buff.getBackground());
    gr.setBackground(buff.getBackground());
    etudiant.setBackground(buff.getBackground());
    
    rech.setBounds(450,350,250,50);
    prof.setBounds(400, 80, 120, 50); 
    gr.setBounds(530,80,120,50);
    etudiant.setBounds(660, 80, 80, 50); 
    text.setBounds(500, 60, 300,20);
    ajouterGr.setBounds(510,250,130,30);
    groupes.setBounds(650,250,80,30);
    groupes.addItem("Groupes");
    nom.setBounds(460,250,100,30);
    prenom.setBounds(580,250,100,30);
    
    promotion.setBounds(400,250,100,30);
    promotion.addItem("Promo");
    for (String prom1 : prom) {
            promotion.addItem(prom1);
    }
    
    buff.add(text);
    buff.add(prof); 
    buff.add(gr);
    buff.add(nom);
    buff.add(prenom);
    buff.add(etudiant); 
    buff.add(promotion);
    buff.add(ajouterGr);
    buff.add(groupes);
    buff.add(rech);
    groupes.setVisible(false);
    ajouterGr.setVisible(false);
    promotion.setVisible(false);
    nom.setVisible(false);
    prenom.setVisible(false);
    rech.setVisible(false);
  
    G1.add(prof); 
    G1.add(gr);
    G1.add(etudiant); 
    
    rech.addActionListener(new ActionListener(){

        @Override
        public void actionPerformed(ActionEvent e) {
            if(prof.isSelected()){
                String nomProf = nom.getText();
                String prenomProf = prenom.getText();
                String login;
                FenetreEdt edt;
                try {
                    int nbSemaine = ConnexionDatabase.SQLNumSemaine();
                    login = InfosDB.getLogin(nomProf, prenomProf);
                    if(!"User not found".equals(login)){
                        edt = new FenetreEdt(login);
                        fenetre.dispose();
                        edt.RefCreerEDT(login, nbSemaine);
                    }else{
                        JOptionPane.showMessageDialog(null, "User not found");
                        FenetreReferent ref = new FenetreReferent();
                        fenetre.dispose();
                        ref.Recherche();
                    }
                    
                } catch (SQLException | ClassNotFoundException ex) {
                    Logger.getLogger(FenetreReferent.class.getName()).log(Level.SEVERE, null, ex);
                } 
            }
            if(gr.isSelected()){
                String promo = promotion.getSelectedItem().toString();
                String grp = groupes.getSelectedItem().toString();
                FenetreEdt edt;
                try {
                    int nbSemaine = ConnexionDatabase.SQLNumSemaine();
                    String login = InfosDB.getLoginGroupe(grp, promo);
                    if(!"User not found".equals(login)){
                        edt = new FenetreEdt(login);
                        fenetre.dispose();
                        edt.RefCreerEDT(login, nbSemaine);
                    }else{
                        JOptionPane.showMessageDialog(null, "User not found");
                        FenetreReferent ref = new FenetreReferent();
                        fenetre.dispose();
                        ref.Recherche();
                    }
                    
                } catch (SQLException | ClassNotFoundException ex) {
                    Logger.getLogger(FenetreReferent.class.getName()).log(Level.SEVERE, null, ex);
                } 
                
                
            }
            if(etudiant.isSelected()){
                String nomEtudiant = nom.getText();
                String prenomEtudiant = prenom.getText();
                String login;
                FenetreEdt edt;
                try {
                    int nbSemaine = ConnexionDatabase.SQLNumSemaine();
                    login = InfosDB.getLogin(nomEtudiant, prenomEtudiant);
                    if(!"User not found".equals(login)){
                        edt = new FenetreEdt(login);
                        fenetre.dispose();
                        edt.RefCreerEDT(login, nbSemaine);
                    }else{
                        JOptionPane.showMessageDialog(null, "User not found");
                        FenetreReferent ref = new FenetreReferent();
                        fenetre.dispose();
                        ref.Recherche();
                    }
                } catch (SQLException | ClassNotFoundException ex) {
                    Logger.getLogger(FenetreReferent.class.getName()).log(Level.SEVERE, null, ex);
                } 
            }
            
        }
        
    });
    
    prof.addActionListener(new ActionListener(){

        @Override
        public void actionPerformed(ActionEvent e) {
            promotion.setVisible(false);
            ajouterGr.setVisible(false);
            groupes.setVisible(false);
            nom.setVisible(true);
            prenom.setVisible(true);
            rech.setVisible(true);
        }
        
    });
    
    gr.addActionListener(new ActionListener(){

        @Override
        public void actionPerformed(ActionEvent e) {
            promotion.setVisible(true);
            ajouterGr.setVisible(true);
            groupes.setVisible(false);
            nom.setVisible(false);
            prenom.setVisible(false);
            rech.setVisible(true);
        }
    });
    
    etudiant.addActionListener(new ActionListener(){

        @Override
        public void actionPerformed(ActionEvent e) {
            promotion.setVisible(false);
            ajouterGr.setVisible(false);
            groupes.setVisible(false);
            nom.setVisible(true);
            prenom.setVisible(true);
            rech.setVisible(true);
        }
        
    });
    
    ajouterGr.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent arg0) {
                String promo = promotion.getSelectedItem().toString();
                
                ArrayList<String> gr = new ArrayList<>();
                
                if (!"Promo".equals(promo)){  
                try {
                    gr = InfosDB.getGroupes(promo);
                } catch (SQLException | ClassNotFoundException ex) {
                    Logger.getLogger(FenetreAdmin.class.getName()).log(Level.SEVERE, null, ex);
                }
                groupes.removeAllItems();
                groupes.addItem("Groupes");
                    for (String gr1 : gr) {
                        groupes.addItem(gr1);
                    }
                    
                groupes.setVisible(true);
                
                }else{
                groupes.setVisible(false);
                }
            }
        });
        
    }
}