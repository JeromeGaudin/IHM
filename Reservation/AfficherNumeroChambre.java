import javax.swing.*;
import java.awt.*;

public class AfficherNumeroChambre extends JPanel{
  private JLabel jlNum;
  private Chambre chambre;
  private JButton valider;

  private Font fontDeBase;

  public AfficherNumeroChambre() {
    super();

    this.chambre = null;

    JLabel jlTextNum = new JLabel("Numéro de chambre proposé");
    jlNum = new JLabel(" ");

    fontDeBase = jlNum.getFont();

    valider = new JButton("Valider");


    this.setLayout(new GridBagLayout());
    GridBagConstraints contraintes = new GridBagConstraints();
    contraintes.gridx = 0;
    contraintes.gridy = 0;
    contraintes.gridwidth = 1;
    contraintes.gridheight = 1;
    contraintes.fill = GridBagConstraints.VERTICAL;
    contraintes.anchor = GridBagConstraints.NORTH;
    contraintes.insets = new Insets(10,10,10,10); // marge sur les cotés
    contraintes.weightx=1.0f;
    contraintes.weighty=1.0f;
    this.add(jlTextNum, contraintes);

    contraintes.gridx = 0;
    contraintes.gridy = 1;
    contraintes.gridwidth = 1;
    contraintes.gridheight = 1;
    contraintes.fill = GridBagConstraints.VERTICAL;
    contraintes.anchor = GridBagConstraints.CENTER;
    contraintes.insets = new Insets(0,5,5,5); // marge sur les cotés
    contraintes.weightx=0.0f;
    contraintes.weighty=1.0f;

    Font font = new Font("Arial",Font.BOLD,20);
    jlNum.setFont(font);
    this.add(jlNum, contraintes);

    contraintes.gridx = 0;
    contraintes.gridy = 2;
    contraintes.gridwidth = 1;
    contraintes.gridheight = 1;
    contraintes.fill = GridBagConstraints.VERTICAL;
    contraintes.anchor = GridBagConstraints.CENTER;
    contraintes.insets = new Insets(0,5,5,5); // marge sur les cotés
    contraintes.weightx=0.0f;
    contraintes.weighty=1.0f;
    this.add(valider, contraintes);


  }

  public void setChambre(Chambre chambre) {
    this.chambre = chambre;
    if(chambre != null && chambre.getId() == -1) {
      jlNum.setFont(fontDeBase);
      this.jlNum.setText("<html>Une chambre est déjà attribué<br> à cette réservation</html>"); // System.getProperty(line.separator) ne marche pas dans un JLabel
    } else if(chambre != null && chambre.getId() == -2) {
      jlNum.setFont(fontDeBase);
      this.jlNum.setText("Plus de chambre disponible");
    } else if(chambre != null) {
      Font font = new Font("Arial",Font.BOLD,20);
      jlNum.setFont(font);
      this.jlNum.setText(""+chambre.getNumChambre());
    } else {
      this.jlNum.setText(" ");
    }
  }

  public Chambre getChambre() {
    return chambre;
  }

  public void setControleur(ControleurValider cont) {
    valider.addActionListener(cont);
  }

}
