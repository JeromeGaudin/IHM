public class Effacer {
	private AfficherReservation reservation;
	private AfficherNumeroChambre numChambre;
	private AfficherChambre chambre;
	private static Effacer instance;

	public void setAttribut(AfficherReservation reservation, AfficherNumeroChambre numChambre, AfficherChambre chambre) {
		this.reservation = reservation;
		this.numChambre = numChambre;
		this.chambre = chambre;
	}

	public static Effacer getInstance(){
		if(instance == null)
			instance = new Effacer();
		return instance;
	}

	public void Effacer() {
	}



	public void cleanAll() {
		this.reservation.setValeur(null);
		this.chambre.setValeur(null);
		this.numChambre.setChambre(null);
	}

	public void effacerListChambre() {
		this.chambre.setValeur(null);
	}
}