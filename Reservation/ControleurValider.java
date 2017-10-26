import java.awt.event.*;
import java.util.List;

public class ControleurValider implements ActionListener {
  private BaseDeDonnee db;
  private AfficherNumeroChambre numChambre;
  private AfficherReservation reservation;
  private ControleurSelectionner selectionner;

  public ControleurValider(AfficherNumeroChambre numChambre, AfficherReservation reservation, ControleurSelectionner selectionner) {
    this.db = BaseDeDonnee.getInstance();
    this.numChambre = numChambre;
    this.reservation = reservation;
    this.selectionner = selectionner;
  }



  @Override
  public void actionPerformed(ActionEvent e) {
    int placeListe = reservation.getReservationSelectionner();
    if(placeListe >= 0) {
      Reservation res = reservation.getListReservation().get(placeListe);
      int idRes = res.getIdReservation();
      int idChambre = numChambre.getChambre().getId();
      if(idChambre >= 0) {
        db.attibuerUneChambre(idRes, idChambre);
        selectionner.selectionner(reservation.getReservationSelectionner());
      } // sinon plus de place disponible

    } else if(placeListe == -2) { // sinon pas de réservation séléctionner
      MessageErreur erreur = new MessageErreur(MessageErreur.SELECTIONNER_RESERVATION);
    } else if(placeListe == -1) { // la réservation n'est pas rechercher
      MessageErreur erreur = new MessageErreur(MessageErreur.RECHERCHER_RESERVATION);
    }
  }
}
