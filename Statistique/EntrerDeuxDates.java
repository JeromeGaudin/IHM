import javax.swing.*;
import java.awt.*;

public class EntrerDeuxDates extends JPanel {
  public EntrerDeuxDates(Taux taux) {
    super();
    JTextField ftfdatedebut = new JTextField();
    JTextField ftfdatefin = new JTextField();

    JLabel jlDebut = new JLabel("debut");
    JLabel jlFin = new JLabel("fin");
    Dimension dimensionTextField = new Dimension(150,30);
    ftfdatedebut.setPreferredSize(dimensionTextField);
    ftfdatefin.setPreferredSize(dimensionTextField);
    JButton boutonRechercher = new JButton("Rechercher");

    ControleurChamp cnt = new ControleurChamp(ftfdatedebut, ftfdatefin, taux);
    boutonRechercher.addActionListener(cnt);

    this.setLayout(new GridBagLayout());
    GridBagConstraints contraintes = new GridBagConstraints();

    contraintes.gridx = 0;
    contraintes.gridy = 0;
    contraintes.gridwidth = 1;
    contraintes.gridheight = 1;
    contraintes.fill = GridBagConstraints.HORIZONTAL;
    contraintes.anchor = GridBagConstraints.CENTER;
    contraintes.insets = new Insets(5,5,5,10); // marge sur les cotés
    contraintes.weightx=0.0f;
    contraintes.weighty=0.0f;
    this.add(jlDebut, contraintes);

    contraintes.gridx = 1;
    contraintes.gridy = 0;
    contraintes.gridwidth = 1;
    contraintes.gridheight = 1;
    contraintes.fill = GridBagConstraints.HORIZONTAL;
    contraintes.anchor = GridBagConstraints.CENTER;
    contraintes.insets = new Insets(5,5,5,5); // marge sur les cotés
    contraintes.weightx=0.0f;
    contraintes.weighty=0.0f;
    this.add(ftfdatedebut, contraintes);


    contraintes.gridx = 0;
    contraintes.gridy = 1;
    contraintes.gridwidth = 1;
    contraintes.gridheight = 1;
    contraintes.fill = GridBagConstraints.HORIZONTAL;
    contraintes.anchor = GridBagConstraints.CENTER;
    contraintes.insets = new Insets(5,5,5,10); // marge sur les cotés
    contraintes.weightx=0.0f;
    contraintes.weighty=0.0f;
    this.add(jlFin, contraintes);

    contraintes.gridx = 1;
    contraintes.gridy = 1;
    contraintes.gridwidth = 1;
    contraintes.gridheight = 1;
    contraintes.fill = GridBagConstraints.HORIZONTAL;
    contraintes.anchor = GridBagConstraints.NORTH;
    contraintes.insets = new Insets(5,5,5,5); // marge sur les cotés
    contraintes.weightx=0.0f;
    contraintes.weighty=0.0f;
    this.add(ftfdatefin, contraintes);

    contraintes.gridx = 1;
    contraintes.gridy = 2;
    contraintes.gridwidth = 1;
    contraintes.gridheight = 1;
    contraintes.fill = GridBagConstraints.HORIZONTAL;
    contraintes.anchor = GridBagConstraints.NORTH;
    contraintes.insets = new Insets(20,20,20,20); // marge sur les cotés
    contraintes.weightx=0.0f;
    contraintes.weighty=0.0f;
    this.add(boutonRechercher, contraintes);
  }
}
