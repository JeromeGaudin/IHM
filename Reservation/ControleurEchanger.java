import java.awt.event.*;
import java.util.List;

public class ControleurEchanger implements ActionListener{
  private BaseDeDonnee db;
  private AfficherNumeroChambre numChambre;
  private AfficherChambre chambre;

  public ControleurEchanger(AfficherNumeroChambre numChambre, AfficherChambre chambre) {
    this.numChambre = numChambre;
    this.chambre = chambre;
    this.db = BaseDeDonnee.getInstance();
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    MonBouton bouton = (MonBouton) e.getSource();
    int ligne = bouton.getValeur();
    Chambre ch = this.chambre.getChambreDansList(ligne);
    this.numChambre.setChambre(ch);
  }
}
