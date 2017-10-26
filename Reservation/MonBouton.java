import javax.swing.JButton;

public class MonBouton extends JButton {
  private int valeur;

  public MonBouton(String nom) {
    super(nom);
    this.valeur =-1;
  }

  public int getValeur() {
    return valeur;
  }

  public void setValeur(int valeur) {
    this.valeur = valeur;
  }
}
