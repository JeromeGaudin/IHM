import javax.swing.*;
import java.awt.*;

public class EntrerInformation extends JPanel {
  private JFormattedTextField tfnom;
  private JFormattedTextField tfprenom;
  private JFormattedTextField tfreservation;
  private JButton rechercher1;
  private JButton rechercher2;

  public EntrerInformation() {
    super();

    tfnom = new JFormattedTextField();
    tfprenom = new JFormattedTextField();
    tfreservation = new JFormattedTextField();

    rechercher1 = new JButton("Rechercher");
    rechercher2 = new JButton("Rechercher");
    JLabel nom = new JLabel("nom");
    JLabel prenom = new JLabel("prenom");
    JLabel reference = new JLabel("reference");

    Dimension dimensionTextField = new Dimension(150,30);
    tfreservation.setPreferredSize(dimensionTextField);
    tfprenom.setPreferredSize(dimensionTextField);
    tfnom.setPreferredSize(dimensionTextField);

    this.setLayout(new GridBagLayout());
    GridBagConstraints contraintes = new GridBagConstraints();

    contraintes.gridx = 0;
    contraintes.gridy = 1;
    contraintes.gridwidth = 1;
    contraintes.gridheight = 1;
    contraintes.fill = GridBagConstraints.HORIZONTAL;
    contraintes.anchor = GridBagConstraints.CENTER;
    contraintes.insets = new Insets(5,40,5,5);
    contraintes.weightx=1.0f;
    contraintes.weighty=1.0f;
    this.add(nom, contraintes);

    contraintes.gridx = 0;
    contraintes.gridy = 0;
    contraintes.gridwidth = 1;
    contraintes.gridheight = 1;
    contraintes.fill = GridBagConstraints.HORIZONTAL;
    contraintes.anchor = GridBagConstraints.CENTER;
    contraintes.insets = new Insets(5,40,5,5);
    contraintes.weightx=0.0f;
    contraintes.weighty=1.0f;
    this.add(prenom, contraintes);

    contraintes.gridx = 0;
    contraintes.gridy = 3;
    contraintes.gridwidth = 1;
    contraintes.gridheight = 1;
    contraintes.fill = GridBagConstraints.HORIZONTAL;
    contraintes.anchor = GridBagConstraints.CENTER;
    contraintes.insets = new Insets(5,40,5,5);
    contraintes.weightx=0.0f;
    contraintes.weighty=1.0f;
    this.add(reference, contraintes);

    contraintes.gridx = 1;
    contraintes.gridy = 1;
    contraintes.gridwidth = 1;
    contraintes.gridheight = 1;
    contraintes.fill = GridBagConstraints.HORIZONTAL;
    contraintes.anchor = GridBagConstraints.CENTER;
    contraintes.insets = new Insets(5,5,5,5);
    contraintes.weightx=0.0f;
    contraintes.weighty=0.0f;
    this.add(tfnom, contraintes);

    contraintes.gridx = 1;
    contraintes.gridy = 0;
    contraintes.gridwidth = 1;
    contraintes.gridheight = 1;
    contraintes.fill = GridBagConstraints.HORIZONTAL;
    contraintes.anchor = GridBagConstraints.CENTER;
    contraintes.insets = new Insets(5,5,5,5);
    contraintes.weightx=1.0f;
    contraintes.weighty=0.0f;
    this.add(tfprenom, contraintes);

    contraintes.gridx = 1;
    contraintes.gridy = 2;
    contraintes.gridwidth = 1;
    contraintes.gridheight = 1;
    contraintes.fill = GridBagConstraints.HORIZONTAL;
    contraintes.anchor = GridBagConstraints.CENTER;
    contraintes.insets = new Insets(20,20,20,20);
    contraintes.weightx=0.0f;
    contraintes.weighty=0.0f;
    this.add(rechercher1, contraintes);

    contraintes.gridx = 1;
    contraintes.gridy = 3;
    contraintes.gridwidth = 1;
    contraintes.gridheight = 1;
    contraintes.fill = GridBagConstraints.HORIZONTAL;
    contraintes.anchor = GridBagConstraints.CENTER;
    contraintes.insets = new Insets(5,5,5,5);
    contraintes.weightx=0.0f;
    contraintes.weighty=0.0f;
    this.add(tfreservation, contraintes);

    contraintes.gridx = 1;
    contraintes.gridy = 4;
    contraintes.gridwidth = 1;
    contraintes.gridheight = 1;
    contraintes.fill = GridBagConstraints.HORIZONTAL;
    contraintes.anchor = GridBagConstraints.CENTER;
    contraintes.insets = new Insets(20,20,20,20);
    contraintes.weightx=0.0f;
    contraintes.weighty=0.0f;
    this.add(rechercher2, contraintes);

  }

  public JFormattedTextField[] getTextField(){
    JFormattedTextField[] t = new JFormattedTextField[3];
    t[0] = this.tfprenom;
    t[1] = this.tfnom;
    t[2] = this.tfreservation;
    return t;
  }

  public void setControleur(ControleurChamp cnt1, ControleurChamp cnt2) {
    rechercher2.addActionListener(cnt2);
    rechercher1.addActionListener(cnt1);
  }

}
