import javax.swing.*;
import java.awt.*;
//import java.awt.event.*;

public class EntrerUneDate extends JPanel {
  public EntrerUneDate(Taux taux1, Taux taux2) {
    super();
    JTextField ftfdate = new JTextField();

    Dimension dimensionTextField = new Dimension(150,30);
    ftfdate.setPreferredSize(dimensionTextField);
    JButton boutonRechercher = new JButton("Rechercher");

    ControleurChamp cnt = new ControleurChamp(ftfdate, taux1, taux2);
    boutonRechercher.addActionListener(cnt);

    this.setLayout(new GridBagLayout());
    GridBagConstraints contraintes = new GridBagConstraints();

    contraintes.gridx = 0;
    contraintes.gridy = 0;
    contraintes.gridwidth = 1;
    contraintes.gridheight = 1;
    contraintes.fill = GridBagConstraints.HORIZONTAL;
    contraintes.anchor = GridBagConstraints.CENTER;
    contraintes.insets = new Insets(5,5,5,5); // marge sur les cotés
    contraintes.weightx=0.0f;
    contraintes.weighty=0.0f;
    this.add(ftfdate, contraintes);

    contraintes.gridx = 0;
    contraintes.gridy = 1;
    contraintes.gridwidth = 1;
    contraintes.gridheight = 1;
    contraintes.fill = GridBagConstraints.HORIZONTAL;
    contraintes.anchor = GridBagConstraints.CENTER;
    contraintes.insets = new Insets(10,20,20,20); // marge sur les cotés
    contraintes.weightx=0.0f;
    contraintes.weighty=0.0f;
    this.add(boutonRechercher, contraintes);
  }
}
