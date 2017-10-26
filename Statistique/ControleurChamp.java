import javax.swing.*;
import java.awt.event.*;
import java.sql.Date;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.text.DecimalFormat;

public class ControleurChamp implements ActionListener {
  private JTextField champ1;
  private JTextField champ2;
  private BaseDeDonnee db;
  private Taux tauxOccupation;
  private Taux tauxNonPresentation;

  public ControleurChamp(JTextField champ1, Taux tauxOccupation, Taux tauxNonPresentation) {
    this.champ1 = champ1;
    this.champ2 = null;
    db = BaseDeDonnee.getInstance();
    this.tauxOccupation = tauxOccupation;
    this.tauxNonPresentation = tauxNonPresentation;
  }

  public ControleurChamp(JTextField champ1, JTextField champ2, Taux tauxOccupation) {
    this.champ1 = champ1;
    this.champ2 = champ2;
    db = BaseDeDonnee.getInstance();
    this.tauxOccupation = tauxOccupation;
    this.tauxNonPresentation = null;
  }


  @Override
  public void actionPerformed(ActionEvent e) {
    e = (ActionEvent) e;
    if(e.getActionCommand().equals("Rechercher")) {
      String str = champ1.getText().trim();
      if( !str.equals("")) {
        java.sql.Date date = null;
        try {
          date = Date.valueOf(str);
          Calendar cal = new GregorianCalendar();
          cal.setTime(date);

          DecimalFormat fd = new DecimalFormat("###.##");

          // taux occupation
          if(this.champ2 != null) {
            String str2 = champ2.getText().trim();
            date = Date.valueOf(str2);
            Calendar cal2 = new GregorianCalendar();
            cal2.setTime(date);

            float nbtaux = db.tauxOccupation(cal, cal2);
            tauxOccupation.setTaux(""+fd.format(nbtaux*100)+"%");
          } else {
            float nbtaux = db.tauxOccupation(cal);
            tauxOccupation.setTaux(""+fd.format(nbtaux*100)+"%");
          }

          // taux non presentation
          if(tauxNonPresentation != null) {
            float nonPres = db.tauxNonPresentation(cal);
            tauxNonPresentation.setTaux(""+fd.format(nonPres*100)+"%");
          }
        } catch(IllegalArgumentException ex) {
          // mauvais format
          String texte = "La date n'est pas correcte, le seul format"+System.getProperty("line.separator")
          +"disponible est aaaa-mm-kk:"+System.getProperty("line.separator")
          +"a = année"+System.getProperty("line.separator")
          +"m = mois"+System.getProperty("line.separator")
          +"j = jour"+System.getProperty("line.separator")
          +"par exemple 2017-10-23";
          JOptionPane pane = new JOptionPane();
          pane.showConfirmDialog(pane,texte,"Mauvaise date",JOptionPane.DEFAULT_OPTION,JOptionPane.ERROR_MESSAGE);

        }
      }
    }
  }
}
