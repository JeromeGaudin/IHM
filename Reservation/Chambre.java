public class Chambre {
	private int id;
	private int numChambre;
	private String sigle;
	private String texte;

	public Chambre(int id, int numChambre, String sigle, String texte) {
		this.id = id;
		this.numChambre = numChambre;
		this.sigle = sigle;
		this.texte = texte;
	}

	public Chambre(int id, int numChambre) {
		this.id = id;
		this.numChambre = numChambre;
		this.sigle = null;
		this.texte = null;
	}

	public void setSigle(String sigle) {
		this.sigle = sigle;
	}

	public void setTexte(String texte) {
		this.texte = texte;
	}

	public int getNumChambre() {
		return numChambre;
	}

	public String getSigle() {
		return sigle;
	}

	public String getTexte() {
		return texte;
	}

	public int getId() {
		return id;
	}

}
