import javax.swing.*;
import java.awt.*;
import java.util.List;

public class AfficherChambre extends JPanel {
	private final int MAX_LIST = 50;
	private JLabel[][] grilleJLabel = new JLabel[MAX_LIST-1][3];
	private MonBouton[] tabBouton = new MonBouton[MAX_LIST-1];

	private List<Chambre> listChambre;

	public AfficherChambre() {

		this.listChambre = null;

		this.setLayout(new GridLayout(MAX_LIST,4));

		this.add(new JLabel("numéro chambre"));
		this.add(new JLabel("Type de chambre"));
		this.add(new JLabel("Description type"));
		this.add(new JLabel(" ")); // colonne bouton


		for(int i=0; i<MAX_LIST-1; i++) {
			for(int j=0; j<3; j++) {
				grilleJLabel[i][j] = new JLabel(" ");
				this.add(grilleJLabel[i][j]);
			}
			tabBouton[i] = new MonBouton("Echanger");
			tabBouton[i].setVisible(false);
			this.add(tabBouton[i]);
		}
	}

	public void setValeur(List<Chambre> listRes) {
		this.listChambre = listRes;
	    int i=0;
		if(listRes != null) {
			for(i=0; i<listRes.size() && i < MAX_LIST-1; i++) {
				this.grilleJLabel[i][0].setText(""+listRes.get(i).getNumChambre());
				this.grilleJLabel[i][1].setText(listRes.get(i).getSigle());
				this.grilleJLabel[i][2].setText(listRes.get(i).getTexte());
				this.tabBouton[i].setValeur(i);
				this.tabBouton[i].setVisible(true);
			}
		}
	    //efface les autres
	    while(i<MAX_LIST-1) {
	    	this.grilleJLabel[i][0].setText(" ");
			this.grilleJLabel[i][1].setText(" ");
			this.grilleJLabel[i][2].setText(" ");
			this.tabBouton[i].setVisible(false);
			i++;
		}
	}

	public Chambre getChambreDansList(int ligne) {
		return listChambre.get(ligne);
	}

	public void setControleur(ControleurEchanger cont) {
		for(int i=0; i<tabBouton.length; i++) {
			this.tabBouton[i].addActionListener(cont);
		}
	}
}
