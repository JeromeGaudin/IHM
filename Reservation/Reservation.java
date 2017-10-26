import java.util.Date;

public class Reservation {
	private int idRes; // id reservation
	private int id; // id Categorie
	private String reference;
	private Date debut;
	private int nuits;
	private String sigle;
	private String texte;

	public Reservation() {
		this.id = 0; // Reservation faux car id ne peut pas être égale à 0
	}

	public Reservation(int idRes,  int id, String reference, Date debut, int nuits, String sigle, String texte) {
		this.idRes = idRes;
		this.id = id;
		this.reference = reference;
		this.debut = debut;
		this.nuits = nuits;
		this.sigle = sigle;
		this.texte = texte;
	}

	public String getReference() {
		return reference;
	}

	public Date getDebut() {
		return debut;
	}

	public int getNuit() {
		return nuits;
	}

	public String getSigle() {
		return sigle;
	}

	public String getTexte() {
		return texte;
	}

	public int getIdCategorie() {
		return id;
	}

	public int getIdReservation() {
		return idRes;
	}
}
