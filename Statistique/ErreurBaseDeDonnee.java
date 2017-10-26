import javax.swing.*;
import java.awt.event.*;

public class ErreurBaseDeDonnee extends JOptionPane {
  public static final int ERREUR_CONNEXION = 1;
  public static final int ERREUR_REQUETE = 2;
  public static final int ERREUR_DRIVER = 3;

  public ErreurBaseDeDonnee(int typeErreur) {
    super();

    String texteErreurConnexion = "L'application ne peut pas se connecter"+System.getProperty("line.separator")
    +"a la base de donnée. Le problème peut"+System.getProperty("line.separator")
    +"venir de votre connexion internet ou "+System.getProperty("line.separator")
    +"du serveur.";

    String texteErreurDriver = "Le driver mariadb n'a pas été trouvé, il"+System.getProperty("line.separator")
    +"peurse trouver a un autre emplacement"+System.getProperty("line.separator")
    +"dans ce cas changer l'emplacement"+System.getProperty("line.separator")
    +" dans le Makefile.";

    String texteErreurRequete = "La requete n'a pas abouti au serveur,"+System.getProperty("line.separator")
    +"cela peut provenir de votre connexion"+System.getProperty("line.separator")
    +"d'internet ou du serveur.";

    String texte=null, titre=null;

    if(typeErreur == ERREUR_DRIVER) {
      texte = texteErreurDriver;
      titre="Erreur de driver";
    } else if(typeErreur == ERREUR_REQUETE) {
      texte = texteErreurRequete;
      titre="Erreur de requete";
    } else if(typeErreur == ERREUR_CONNEXION) {
      texte = texteErreurConnexion;
      titre="Erreur de connexion";
    }

    this.showConfirmDialog(this,texte,titre,JOptionPane.DEFAULT_OPTION,JOptionPane.ERROR_MESSAGE);
    if(typeErreur == ERREUR_DRIVER || typeErreur == ERREUR_CONNEXION) {
      if(typeErreur == ERREUR_CONNEXION) {
        BaseDeDonnee.getInstance().fermerConnexion();
      }
      System.exit(1);
    }
  }

}
