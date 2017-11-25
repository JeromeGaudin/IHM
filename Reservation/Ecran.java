import javax.swing.*;
import java.awt.*;
import java.util.Calendar;

import java.util.List;
import java.util.ArrayList;
import java.sql.Date;

public class Ecran extends JFrame {
  public Ecran(Calendar date) {
    super("Reservation");
    this.setResizable(false);
    this.setSize(1250,750);
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.setLocation(200,200);

    AfficherReservation reservation = new AfficherReservation();
    AfficherChambre chambre = new AfficherChambre();
    AfficherNumeroChambre numChambre = new AfficherNumeroChambre();
    EntrerInformation entrerInformation = new EntrerInformation();

    Effacer effacer = Effacer.getInstance();
    effacer.setAttribut(reservation, numChambre, chambre);

    // arg pour les autres controleur
    JTextField[] textField = entrerInformation.getTextField();

    //controleur
    ControleurChamp cnt1 = new ControleurChamp(reservation, textField[0], textField[1]);
    ControleurChamp cnt2 = new ControleurChamp(reservation, textField[2]);
    ControleurSelectionner cnt3 = new ControleurSelectionner(numChambre, chambre, reservation, date);
    ControleurValider cnt4 = new ControleurValider(numChambre, reservation, cnt3);
    ControleurEchanger cnt5 = new ControleurEchanger(numChambre, chambre);

    //ajout des controleur au composant
    entrerInformation.setControleur(cnt1, cnt2);
    reservation.setControleur(cnt3);
    numChambre.setControleur(cnt4);
    chambre.setControleur(cnt5);

    this.setLayout(new GridBagLayout());
    GridBagConstraints contraintes = new GridBagConstraints();

    contraintes.gridx = 0;
    contraintes.gridy = 0;
    contraintes.gridwidth = 1;
    contraintes.gridheight = 1;
    contraintes.fill = GridBagConstraints.HORIZONTAL;
    contraintes.anchor = GridBagConstraints.CENTER;
    contraintes.insets = new Insets(5,5,5,5);
    contraintes.weightx=1.0f;
    contraintes.weighty=1.0f;
    this.add(entrerInformation, contraintes);

    contraintes.gridx = 0;
    contraintes.gridy = 1;
    contraintes.gridwidth = 2;
    contraintes.gridheight = 1;
    contraintes.fill = GridBagConstraints.HORIZONTAL;
    contraintes.anchor = GridBagConstraints.CENTER;
    contraintes.insets = new Insets(5,5,5,5);
    contraintes.weightx=1.0f;
    contraintes.weighty=1.0f;
    this.add(reservation, contraintes);

    contraintes.gridx = 1;
    contraintes.gridy = 0;
    contraintes.gridwidth = 1;
    contraintes.gridheight = 1;
    contraintes.fill = GridBagConstraints.HORIZONTAL;
    contraintes.anchor = GridBagConstraints.CENTER;
    contraintes.insets = new Insets(5,5,5,5);
    contraintes.weightx=1.0f;
    contraintes.weighty=1.0f;
    this.add(numChambre, contraintes);

    contraintes.gridx = 2;
    contraintes.gridy = 0;
    contraintes.gridwidth = 1;
    contraintes.gridheight = 2;
    contraintes.fill = GridBagConstraints.HORIZONTAL;
    contraintes.anchor = GridBagConstraints.CENTER;
    contraintes.insets = new Insets(5,5,5,5);
    contraintes.weightx=1.0f;
    contraintes.weighty=1.0f;
    this.add(chambre, contraintes);

  }
}
