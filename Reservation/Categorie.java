public class Categorie {
  private int id;
  private String sigle;
  private String texte;

  public Categorie(int id, String sigle, String texte) {
    this.id = id;
    this.sigle = sigle;
    this.texte = texte;
  }

  public String getSigle() {
    return sigle;
  }

  public String getTexte() {
    return texte;
  }
}
