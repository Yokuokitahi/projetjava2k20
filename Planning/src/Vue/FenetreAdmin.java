package Vue;

import Controleur.AjouterDB;
import Controleur.InfosDB;
import Controleur.RechercherSeance;
import Controleur.UpdateDB;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;

import javax.swing.table.TableCellRenderer;
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
    
    
    public FenetreAdmin()throws SQLException, ClassNotFoundException{
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
        ajouter.setBounds(250,350,160,50);
       
        
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
        for (String matiere1 : matieres) {
            matiere.addItem(matiere1);
        }
        
        //Recupere type de cours ds un menu deroulant
        typeCours.setBounds(200,200,120,30);
        typeCours.addItem("Type de cours");
        for (String type1 : type) {
            typeCours.addItem(type1);
        }
        
        //Recupere le prof associé a une matiere via menu deroulant
        enseignants.setBounds(350,200,120,30);
        enseignants.addItem("Enseignants");
        for (String enseignant1 : enseignant) {
            enseignants.addItem(enseignant1);
        }
        
        salles.setBounds(50,250,120,30);
        salles.addItem("Salles");
        for (String sal1 : sal) {
            salles.addItem(sal1);
        }
        
        promotion.setBounds(200,250,100,30);
        promotion.addItem("Promo");
        for (String prom1 : prom) {
            promotion.addItem(prom1);
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
                    for (String gr1 : gr) {
                        groupes.addItem(gr1);
                    }
                    
                groupes.setVisible(true);
                
                }else{
                groupes.setVisible(false);
                }
            }
        });
        
        
        //Boutons ajout de cours
        ajouter.addActionListener(new ActionListener(){
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
                /*System.out.println(date);
                System.out.println(horaire);
                System.out.println(typeC);
                System.out.println(mat);
                System.out.println(prof);
                System.out.println(salle);
                System.out.println(promo);*/
                
                try {
                    AjouterDB.AjouterSeance(date, horaire, mat, typeC, prof, grp, promo, salle);
                } catch (SQLException | ClassNotFoundException ex) {
                    Logger.getLogger(FenetreAdmin.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        
       
 
        pan.add(ajouter);
    }
    
    public void modifierCours(final String id, String d, String he, String c, String t, String pr, String sa, String gr1, String gr2) throws SQLException, ClassNotFoundException{
        
        fenetre.setSize(800,600);
        
        //fonction pour avoir matieres + typecours depuis BDD
        ArrayList<String> matieres = InfosDB.getMatiere();
        ArrayList<String> type = InfosDB.getTypeDeCours();
        ArrayList<String> enseignant = InfosDB.getEnseignant();
        ArrayList<String> sal = InfosDB.getSalles();
        ArrayList<String> prom = InfosDB.getPromotion();
        
        ArrayList<String> tokens = new ArrayList<>(Arrays.asList(d.split("-")));
            String anneeP= tokens.get(0);
            String moisP= tokens.get(1);
            String joursP= tokens.get(2);

            tokens = new ArrayList<>(Arrays.asList(he.split(":")));
            String heureP= tokens.get(0);
            String minuteP= tokens.get(1);
                  
        
        //Creations de boutons
        JButton modif = new JButton("Modifier le cours");
        modif.setBackground(Color.GREEN);
        modif.setBounds(220,350,160,50);
       
        JTextPane ancienneDate = new JTextPane();
        ancienneDate.setText("Cours à modifier: "+d+" "+he+" "+c+" "+t+" "+pr+" "+sa+" "+gr1+" "+gr2);
        ancienneDate.setBounds(100,100,500,20);
        ancienneDate.setEditable(false);
        ancienneDate.setBackground(Color.RED);
        
        pan.add(ancienneDate);
        
        JTextPane newDate = new JTextPane();
        newDate.setBounds(100,400,120,40);
        
        JButton ajouterGr = new JButton("Ajouter Groupe");
        ajouterGr.setBounds(300,250,130,30);

        //on créer des menus deroulants
        jours.setBounds(50, 150, 80, 30);
        jours.addItem(joursP);
        
        mois.setBounds(150,150,80,30);
        mois.addItem(moisP);
        
        annee.setBounds(250,150,80,30);
        annee.addItem(anneeP);
        
        heure.setBounds(350,150,80,30);
        heure.addItem(heureP);
        
        min.setBounds(450,150,80,30);
        min.addItem(minuteP);
        
        //Recupere les matieres ds un menu deroulant
        matiere.setBounds(50,200,120,30);
        matiere.addItem(c);
        for (String matiere1 : matieres) {
            matiere.addItem(matiere1);
        }
        
        //Recupere type de cours ds un menu deroulant
        typeCours.setBounds(200,200,120,30);
        typeCours.addItem(t);
        for (String type1 : type) {
            typeCours.addItem(type1);
        }
        
        //Recupere le prof associé a une matiere via menu deroulant
        enseignants.setBounds(350,200,120,30);
        enseignants.addItem(pr);
        for (String enseignant1 : enseignant) {
            enseignants.addItem(enseignant1);
        }
        
        salles.setBounds(50,250,120,30);
        salles.addItem(sa);
        for (String sal1 : sal) {
            salles.addItem(sal1);
        }
        
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
        pan.add(jours);
        pan.add(mois);
        pan.add(annee);
        pan.add(heure);
        pan.add(min);
        pan.add(typeCours);
        pan.add(matiere);
        pan.add(enseignants);  
        pan.add(salles);

        //Boutons modif de cours
        modif.addActionListener(new ActionListener(){
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

                System.out.println(date);
                System.out.println(horaire);
                System.out.println(typeC);
                System.out.println(mat);
                System.out.println(prof);
                System.out.println(salle);
                
                try {
                    UpdateDB.Modifier(id, date, horaire, mat, typeC, prof, salle);
                    fenetre.dispose();
                    FenetreAdmin refresh = new FenetreAdmin();
                    refresh.supprimerCours();
                } catch (SQLException | ClassNotFoundException ex) {
                    Logger.getLogger(FenetreAdmin.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });

        pan.add(modif);
    }
    
    public void supprimerCours() throws SQLException, ClassNotFoundException{
        
        JPanel pan1 = new JPanel();
        fenetre.setContentPane(pan1);
        ArrayList<ArrayList<String>> recup = RechercherSeance.Seance();
        
        //System.out.println(recup);
        //System.out.println(recup.get(1).get(0));
        
        String[] titre = {"Id","Date","Heure de début","Cours","Type de cours","Professeur","Salle","Gr","Gr","suppr","modif"};
        
        for (ArrayList<String> recup1 : recup) {
            recup1.remove(1);         
        }
        
        int max = 0;
            for (ArrayList<String> result1 : recup) {
                if (result1.size() > max) {
                    max = result1.size()+2;
                }
            }

        Object [][] infos =  new Object[recup.size()][max+3];

        
        for (int i=0; i<recup.size(); i++){
            for (int s = 0; s< recup.get(i).size();s++){
                infos[i][s] = recup.get(i).get(s);
            }
        }
        
        JTable tableau;
        tableau = new JTable(infos,titre);
        //tableau.setDefaultRenderer(JButton.class, new TableComponent());
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
            
            tableau.getColumn("suppr").setCellRenderer(new ButtonRenderer());
            tableau.getColumn("suppr").setCellEditor(new ButtonEditor(new JCheckBox()));
            
            tableau.getColumn("modif").setCellRenderer(new ButtonRenderer());
            tableau.getColumn("modif").setCellEditor(new ButtonEditor(new JCheckBox()));
    }
    
class ButtonRenderer extends JButton implements TableCellRenderer {

  public ButtonRenderer() {
    setOpaque(true);
  }

  @Override
  public Component getTableCellRendererComponent(JTable table, Object value,
      boolean isSelected, boolean hasFocus, int row, int column) {
    if (isSelected) {
      setForeground(table.getSelectionForeground());
      setBackground(table.getSelectionBackground());
    } else {
      setForeground(table.getForeground());
      setBackground(UIManager.getColor("Button.background"));
    }
    setText((value == null) ? "" : value.toString());
    return this;
  }
}


class ButtonEditor extends DefaultCellEditor {
  protected JButton button;

  private String label;

  private boolean isPushed;
  private boolean isPousse;
  
  private int idL;
  private Object ident, date, heure, cours, type, prof, salle, gr1, gr2;

  public ButtonEditor(JCheckBox checkBox) {
    super(checkBox);
    button = new JButton();
    button.setOpaque(true);
    button.addActionListener(new ActionListener() {
    
        @Override
        public void actionPerformed(ActionEvent e) {
        fireEditingStopped();
      }
    });
  }

  @Override
  public Component getTableCellEditorComponent(JTable table, Object value,
      boolean isSelected, int row, int column) {
    if (isSelected) {
      button.setForeground(table.getSelectionForeground());
      button.setBackground(table.getSelectionBackground());  
    } else {
      button.setForeground(table.getForeground());
      button.setBackground(table.getBackground());
    }
    label = (value == null) ? "" : value.toString();
    button.setText(label);

    ident = table.getValueAt(row,0);
    date = table.getValueAt(row,1);
    heure = table.getValueAt(row,2);
    cours = table.getValueAt(row,3);
    type = table.getValueAt(row,4);
    prof = table.getValueAt(row,5);
    salle = table.getValueAt(row,6);
    gr1 = table.getValueAt(row,7);
    gr2 = table.getValueAt(row,8);
    
    if(column == 9){
        isPushed = true;
        isPousse = false;
    }
    if (column == 10){
        isPushed = false;
        isPousse = true;
          
    }
    return button;  
  }
  
  @Override
  public Object getCellEditorValue() {
    if (isPushed) {
      // 
      String S= (String) ident;
        try {
            UpdateDB.Supprimer(S);
            FenetreAdmin refresh= new FenetreAdmin();
            fenetre.dispose();
            refresh.supprimerCours();
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(FenetreAdmin.class.getName()).log(Level.SEVERE, null, ex);
        }
      JOptionPane.showMessageDialog(button, label + ":"+ idL + " !");
      System.out.println("ident =" + ident);

    }
    isPushed = false;
    
    if(isPousse){
        try {
            String identS= (String) ident;
            String dateS= (String) date;
            String heureS= (String) heure;
            String coursS= (String) cours;
            String typesS= (String) type;
            String profS= (String) prof;
            String salleS= (String) salle;
            String gr1S= (String) gr1;
            String gr2S= (String) gr2;
            
            /*ArrayList<String> tokens = new ArrayList<>(Arrays.asList(dateS.split("-")));
            String annee= tokens.get(0);
            String mois= tokens.get(1);
            String jours= tokens.get(2);
            
            tokens = new ArrayList<>(Arrays.asList(heureS.split(":")));
            String heure= tokens.get(0);
            String minute= tokens.get(1);*/
           
            //System.out.print(heure +" "+ minute);
            
           FenetreAdmin refresh2;
           refresh2 = new FenetreAdmin(); 
           refresh2.modifierCours(identS, dateS, heureS,coursS, typesS, profS, salleS, gr1S, gr2S);
           fenetre.dispose();
           
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(FenetreAdmin.class.getName()).log(Level.SEVERE, null, ex);
        }     
    }
    isPousse=false;
    return label;
  }

  public int getRow(){
      return idL;
  }

  @Override
  public boolean stopCellEditing() {
    isPushed = false;
    return super.stopCellEditing();
  }


  @Override
  protected void fireEditingStopped() {
    super.fireEditingStopped();
  }
}

}



    