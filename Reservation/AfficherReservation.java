import javax.swing.*;
import java.awt.*;
import java.util.List;

public class AfficherReservation extends JPanel {
	private final int MAX_LIST = 5;
	private JLabel[][] grilleJLabel = new JLabel[MAX_LIST-1][5];
	private MonBouton[] tabBouton = new MonBouton[MAX_LIST-1];

	private List<Reservation> listReservation;
	private int reservationSelectionner;

	public AfficherReservation() {

		this.reservationSelectionner = -1;
		this.listReservation = null;

		this.setLayout(new GridBagLayout());
		JLabel reservation = new JLabel("Réservation");
		JPanel tableau = new JPanel();

    	GridBagConstraints c = new GridBagConstraints();
    	Font font = new Font("Arial",Font.BOLD,20);
    	reservation.setFont(font);

    	c.gridx = 0;
	    c.gridy = 0;
	    c.gridwidth = 1;
	    c.gridheight = 1;
	    c.fill = GridBagConstraints.CENTER;
	    c.anchor = GridBagConstraints.SOUTH;
	    c.insets = new Insets(5,5,5,5);
	    c.weightx=1.0f;
	    c.weighty=1.0f;
		this.add(reservation, c);


    	c.gridx = 0;
	    c.gridy = 1;
	    c.gridwidth = 1;
	    c.gridheight = 1;
	    c.fill = GridBagConstraints.CENTER;
	    c.anchor = GridBagConstraints.SOUTH;
	    c.insets = new Insets(0,0,0,0);
	    c.weightx=1.0f;
	    c.weighty=1.0f;
		this.add(tableau, c);

		tableau.setLayout(new GridLayout(MAX_LIST,6));

		tableau.add(new JLabel("Référence"));
		tableau.add(new JLabel("Début du séjour"));
		tableau.add(new JLabel("nombre de nuits"));
		tableau.add(new JLabel("Type de chambre"));
		tableau.add(new JLabel("<html>Description<br>du type</html>"));
		tableau.add(new JLabel(" ")); // colonne bouton


		for(int i=0; i<MAX_LIST-1; i++) {
			for(int j=0; j<5; j++) {
				grilleJLabel[i][j] = new JLabel("");
				grilleJLabel[i][j].setOpaque(true);
				tableau.add(grilleJLabel[i][j]);
			}
			tabBouton[i] = new MonBouton("Selectionner");
			tabBouton[i].setVisible(false);
			tableau.add(tabBouton[i]);
		}
	}

	public void setValeur(List<Reservation> listRes) {
		this.listReservation = listRes;
		int i=0;
		if(listRes == null) {
			this.reservationSelectionner = -1;
		} else {
			this.reservationSelectionner = -2;
			for(i=0; i<listRes.size() && i < MAX_LIST-1; i++) {
				Reservation res = listRes.get(i);
				this.grilleJLabel[i][0].setText(res.getReference());
				this.grilleJLabel[i][1].setText(res.getDebut().toString());
				this.grilleJLabel[i][2].setText(""+res.getNuit());
				this.grilleJLabel[i][3].setText(res.getSigle());
				this.grilleJLabel[i][4].setText(res.getTexte());
				this.tabBouton[i].setValeur(i);
				this.tabBouton[i].setVisible(true);
			}
		}
		//efface les autres
		while(i<MAX_LIST-1) {
			this.grilleJLabel[i][0].setText(" ");
			this.grilleJLabel[i][1].setText(" ");
			this.grilleJLabel[i][2].setText(" ");
			this.grilleJLabel[i][3].setText(" ");
			this.grilleJLabel[i][4].setText(" ");
			this.tabBouton[i].setVisible(false);
			i++;
		}
	}

	public void setReservationSelectionner(int a) {
		for(int i=0; i<grilleJLabel.length; i++) {
			for(int j=0; j<grilleJLabel[i].length; j++) {
				grilleJLabel[i][j].setBackground(Color.WHITE);
			}
		}
		for(int i=0; i<grilleJLabel[0].length; i++) {
			grilleJLabel[a][i].setBackground(Color.GRAY);
		}
		this.reservationSelectionner = a;
	}

	public int getReservationSelectionner() {
		return this.reservationSelectionner;
	}

	public List<Reservation> getListReservation() {
		return this.listReservation;
	}

	public void setControleur(ControleurSelectionner cont) {
		for(int i=0; i<tabBouton.length-1; i++) {
			tabBouton[i].addActionListener(cont);
		}
	}
}
