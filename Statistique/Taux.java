import javax.swing.*;
import java.awt.*;
import java.text.DecimalFormat;


public class Taux extends JPanel{
  private JLabel jlTaux;
  private boolean couleurInverser;

  public Taux(String nom) {
    super();

    this.couleurInverser = false;

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

  public void setTaux(float nouvelValeur) {
    Color clr[] = new Color[4];
    if(couleurInverser) {
        clr[3] = Color.red;
        clr[2] = Color.orange;   
        clr[1] = new Color(0,100,0);
        clr[0] = new Color(0,200,0);
    } else {
        clr[0] = Color.red;
        clr[1] = Color.orange;   
        clr[2] = new Color(0,100,0);
        clr[3] = new Color(0,200,0);
    }
    System.out.println(couleurInverser);
    if(nouvelValeur<=25) {
        jlTaux.setForeground(clr[0]);
    } else if (nouvelValeur<=50) {
        jlTaux.setForeground(clr[1]);        
    } else if (nouvelValeur<=75) {
        jlTaux.setForeground(clr[2]);
    } else {
        jlTaux.setForeground(clr[3]);
    }
    DecimalFormat fd = new DecimalFormat("###.##");
    jlTaux.setText(""+fd.format(nouvelValeur)+"%");
  }

  public void setCouleurInverser(boolean d) {
    this.couleurInverser = d;
  }

}
