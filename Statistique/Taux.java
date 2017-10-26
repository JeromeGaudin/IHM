import javax.swing.*;
import java.awt.*;

public class Taux extends JPanel{
  private JLabel jlTaux;

  public Taux(String nom) {
    super();

    JLabel jlTextTaux = new JLabel(nom);
    jlTaux = new JLabel(" ");

    this.setLayout(new GridBagLayout());
    GridBagConstraints contraintes = new GridBagConstraints();
    contraintes.gridx = 0;
    contraintes.gridy = 0;
    contraintes.gridwidth = 1;
    contraintes.gridheight = 1;
    contraintes.fill = GridBagConstraints.HORIZONTAL;
    contraintes.anchor = GridBagConstraints.NORTH;
    contraintes.insets = new Insets(10,10,10,10); // marge sur les cotés
    contraintes.weightx=0.0f;
    contraintes.weighty=0.0f;
    this.add(jlTextTaux, contraintes);

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
    jlTaux.setFont(font);
    this.add(jlTaux, contraintes);

  }

  public void setTaux(String nouvelValeur) {
    jlTaux.setText(nouvelValeur);
  }

}
