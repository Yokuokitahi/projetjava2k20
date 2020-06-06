package Vue;

import Controleur.InfosDB;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;


 /* @author Xavier Antoine */

public class FenetreReferent extends FenetreTemplate{
    
    private final JMenuBar menuBar = new JMenuBar();
    private final JMenu referent = new JMenu("Référent Pédagogique");
    private final JPanel buff = new JPanel();
    private final JComboBox promotion = new JComboBox();
    private final JComboBox groupes = new JComboBox();
    
    public FenetreReferent(){
        fenetre.setTitle("Référent Pédagogique");
        fenetre.setSize(1200,500);
        buff.setLayout(null);

        menuBar.add(referent);
        fenetre.setContentPane(buff);
        fenetre.setJMenuBar(menuBar);
    }
    
    
    public void Recherche() throws SQLException, ClassNotFoundException{
    ArrayList<String> prom = InfosDB.getPromotion();
    JRadioButton prof; 
    JRadioButton gr;
    JRadioButton etudiant; 
    final JTextField nom = new JTextField();
    final JTextField prenom = new JTextField();
    final JButton ajouter = new JButton("Rechercher un emploi du temps");
    ajouter.setBackground(Color.GREEN);
    
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
    
    ajouter.setBounds(450,350,250,50);
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
    buff.add(ajouter);
    groupes.setVisible(false);
    ajouterGr.setVisible(false);
    promotion.setVisible(false);
    nom.setVisible(false);
    prenom.setVisible(false);
    ajouter.setVisible(false);
  
    G1.add(prof); 
    G1.add(gr);
    G1.add(etudiant); 
    
    
    
    prof.addActionListener(new ActionListener(){

        @Override
        public void actionPerformed(ActionEvent e) {
            promotion.setVisible(false);
            ajouterGr.setVisible(false);
            groupes.setVisible(false);
            nom.setVisible(true);
            prenom.setVisible(true);
            ajouter.setVisible(true);
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
            ajouter.setVisible(true);
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
            ajouter.setVisible(true);
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
