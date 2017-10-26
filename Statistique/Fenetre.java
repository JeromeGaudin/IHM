import javax.swing.*;
import java.awt.*;

public class Fenetre extends JFrame {
  public Fenetre() {
    super("Statistique Hôtel");
    this.setSize(650,350);
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.setLocation(200,200);

    ControleurFenetre contFen = new ControleurFenetre();
    this.addWindowListener(contFen);

    Taux tauxOccupation1 = new Taux("Taux d'occupation :");
    Taux tauxOccupation2 = new Taux("Taux d'occupation :");
    Taux tauxNonPresentation = new Taux("Taux de non présentation :");
    EntrerUneDate entrer1 = new EntrerUneDate(tauxOccupation1, tauxNonPresentation);
    EntrerDeuxDates entrer2 = new EntrerDeuxDates(tauxOccupation2);

    this.setLayout(new GridBagLayout());
    GridBagConstraints contraintes = new GridBagConstraints();

    contraintes.gridx = 0;
    contraintes.gridy = 0;
    contraintes.gridwidth = 1;
    contraintes.gridheight = 1;
    contraintes.fill = GridBagConstraints.HORIZONTAL;
    contraintes.anchor = GridBagConstraints.CENTER;
    contraintes.insets = new Insets(5,5,5,5); // marge sur les cotés
    contraintes.weightx=1.0f;
    contraintes.weighty=1.0f;
    this.add(entrer1, contraintes);

    contraintes.gridx = 0;
    contraintes.gridy = 1;
    contraintes.gridwidth = 2;
    contraintes.gridheight = 1;
    contraintes.fill = GridBagConstraints.HORIZONTAL;
    contraintes.anchor = GridBagConstraints.CENTER;
    contraintes.insets = new Insets(5,5,5,5); // marge sur les cotés
    contraintes.weightx=1.0f;
    contraintes.weighty=0.0f;
    this.add(entrer2, contraintes);

    contraintes.gridx = 1;
    contraintes.gridy = 0;
    contraintes.gridwidth = 1;
    contraintes.gridheight = 1;
    contraintes.fill = GridBagConstraints.HORIZONTAL;
    contraintes.anchor = GridBagConstraints.CENTER;
    contraintes.insets = new Insets(5,5,5,5); // marge sur les cotés
    contraintes.weightx=1.0f;
    contraintes.weighty=0.0f;
    this.add(tauxOccupation1, contraintes);

    contraintes.gridx = 2;
    contraintes.gridy = 0;
    contraintes.gridwidth = 1;
    contraintes.gridheight = 1;
    contraintes.fill = GridBagConstraints.HORIZONTAL;
    contraintes.anchor = GridBagConstraints.EAST;
    contraintes.insets = new Insets(5,5,5,5); // marge sur les cotés
    contraintes.weightx=1.0f;
    contraintes.weighty=0.0f;
    this.add(tauxNonPresentation, contraintes);

    contraintes.gridx = 1;
    contraintes.gridy = 1;
    contraintes.gridwidth = 2;
    contraintes.gridheight = 1;
    contraintes.fill = GridBagConstraints.HORIZONTAL;
    contraintes.anchor = GridBagConstraints.CENTER;
    contraintes.insets = new Insets(5,100,5,5); // marge sur les cotés
    contraintes.weightx=0.0f;
    contraintes.weighty=1.0f;
    this.add(tauxOccupation2, contraintes);
  }
}
