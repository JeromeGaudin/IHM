import java.awt.event.*;
import javax.swing.*;
import java.util.List;



public class ControleurChamp implements ActionListener {
  private AfficherReservation reservation;
  private JTextField champ1;
  private JTextField champ2;
  private BaseDeDonnee db;
  private boolean unSeulChamp;

  public ControleurChamp(AfficherReservation afficherReservation, JTextField reference) {
    this.reservation = afficherReservation;
    this.champ1 = reference;
    this.champ2 = null;
    this.db = BaseDeDonnee.getInstance();
    this.unSeulChamp = true;
  }

  public ControleurChamp(AfficherReservation afficherReservation, JTextField prenom, JTextField nom) {
    this.reservation = afficherReservation;
    this.champ1 = prenom;
    this.champ2 = nom;
    this.db = BaseDeDonnee.getInstance();
    this.unSeulChamp = false;
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    e = (ActionEvent) e;

    Effacer effacer = Effacer.getInstance();
    effacer.cleanAll();
    if(unSeulChamp == true) {
      String entrer = champ1.getText().trim();
      if(entrer != null && entrer.length()>9 && entrer.charAt(4) == '-' && entrer.charAt(9) == '-') {
        List<Reservation> listReservation = db.chercherReservation(entrer);
        if(listReservation.size() != 0) {
          reservation.setValeur(listReservation);
        }
      } else {
        // sinon mauvais format
        MessageErreur erreur = new MessageErreur(MessageErreur.MAUVAIS_FORMAT);
      }
    } else {//nom presentation
      String prenom = champ1.getText().trim();
      String nom = champ2.getText().trim();
      if( ! nom.equals("") && ! prenom.equals("")) {
        List<Reservation> listReservation = db.chercherReservation(prenom, nom);
        if(listReservation.size() != 0) {
          reservation.setValeur(listReservation);
        }
      }
    }
  }
}
