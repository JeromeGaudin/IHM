import java.awt.event.*;
import java.util.List;
import java.sql.Date;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class ControleurSelectionner implements ActionListener{
  private BaseDeDonnee db;
  private AfficherChambre chambre;
  private AfficherNumeroChambre numChambre;
  private AfficherReservation reservation;
  private Calendar cal;

  public ControleurSelectionner(AfficherNumeroChambre numChambre, AfficherChambre chambre, AfficherReservation reservation, Calendar cal) {
    this.reservation = reservation;
    this.numChambre = numChambre;
    this.chambre = chambre;
    this.db = BaseDeDonnee.getInstance();
    this.cal = cal;
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    MonBouton bouton = (MonBouton) e.getSource();
    int ligne = bouton.getValeur();
    selectionner(ligne);
  }

  public void selectionner(int ligneDansAfficherReservation) {
    int ligne = ligneDansAfficherReservation;
    reservation.setReservationSelectionner(ligne);

    this.chambre.setValeur(null);
    numChambre.setChambre(null);

    List<Reservation> listRes = reservation.getListReservation();
    int placeListe = reservation.getReservationSelectionner();

    if(db.reservationAChambre(listRes.get(placeListe).getIdReservation())) {
      //chambre déjà attribuer a une réservation
      numChambre.setChambre(new Chambre(-1,0)); // indique que la chambre est déjà attribué

    } else {
      int idCategorie = listRes.get(placeListe).getIdCategorie();
      Chambre uneChambre = db.uneChambreLibre(idCategorie, cal);
      if(numChambre == null) {
        numChambre.setChambre(new Chambre(-2,0)); // indique qu'il y a plus de chambre disponible
      } else {
        numChambre.setChambre(uneChambre);
        List<Chambre> listChambre = db.chambreLibre(listRes.get(placeListe).getIdCategorie(), cal);
        Categorie categorie = db.categorie(idCategorie);
        for(int i=0; i<listChambre.size(); i++) {
          listChambre.get(i).setSigle(categorie.getSigle());
          listChambre.get(i).setTexte(categorie.getTexte());
        }
        this.chambre.setValeur(listChambre);
      }
    }
  }
}
