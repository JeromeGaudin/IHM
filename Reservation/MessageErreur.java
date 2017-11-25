import javax.swing.*;
import java.awt.event.*;

public class MessageErreur extends JOptionPane {
  public static final int ERREUR_CONNEXION = 1;
  public static final int ERREUR_REQUETE = 2;
  public static final int ERREUR_DRIVER = 3;
  public static final int CLIENT_NON_TROUVE = 4;
  public static final int RESERVATION_NON_TROUVE = 5;
  public static final int MAUVAIS_FORMAT = 6;
  public static final int SELECTIONNER_RESERVATION = 7;
  public static final int RECHERCHER_RESERVATION = 8;


  public MessageErreur(int typeErreur) {
    super();

    String texteErreurConnexion = "L'application ne peut pas se connecter"+System.getProperty("line.separator")
    +"a la base de donnée. Le problème peut"+System.getProperty("line.separator")
    +"venir de votre connexion internet ou "+System.getProperty("line.separator")
    +"du serveur.";

    String texteErreurDriver = "Le driver mariadb-client.jar n'a pas "+System.getProperty("line.separator")
    +"été trouvé, ila du être déplacer."+System.getProperty("line.separator")
    +"il doit se situer à l'emplacement"+System.getProperty("line.separator")
    +" ../driver/mariadb-client.jar";

    String texteErreurRequete = "La requete n'a pas abouti au serveur,"+System.getProperty("line.separator")
    +"cela peut provenir de votre connexion"+System.getProperty("line.separator")
    +"d'internet ou du serveur.";

    String texteMauvaisFormat = "Le format pour la référence d'une"+System.getProperty("line.separator")
    +"réservation est : cccc-cccc-cccc"+System.getProperty("line.separator")
    +"c = chiffre";

    String texteRechercherReservation = "Il faut d'abord rechercher une réservation"+System.getProperty("line.separator")
    +"soit avec le prénom et le nom du client,"+System.getProperty("line.separator")
    +" soit avec la référence de la réservation";

    String texte=null, titre=null;
    int type = JOptionPane.INFORMATION_MESSAGE;

    switch(typeErreur) {
      case ERREUR_DRIVER:
        texte = texteErreurDriver;
        titre="Erreur de driver";
        break;
      case ERREUR_REQUETE:
        texte = texteErreurRequete;
        titre="Erreur de requete";
        break;
      case ERREUR_CONNEXION:
        texte = texteErreurConnexion;
        titre="Erreur de connexion";
        break;
      case CLIENT_NON_TROUVE:
        texte = "Le client n'a pas été trouvé.";
        titre = "Client non trouvé";
        break;
      case RESERVATION_NON_TROUVE:
        texte = "Aucune réservation trouvé.";
        titre = "Reservation non trouvé";
        break;
      case MAUVAIS_FORMAT:
        texte = texteMauvaisFormat;
        titre = "Mauvais format";
        break;
      case SELECTIONNER_RESERVATION:
        texte = "Il faut d'abord séléctionner une réservation";
        titre = "Réservation non séléctionner";
        break;
      case RECHERCHER_RESERVATION:
        texte = texteRechercherReservation;
        titre = "Réservation non rechercher";
        break;

    }
    if(typeErreur==ERREUR_CONNEXION||typeErreur==ERREUR_DRIVER||typeErreur==ERREUR_REQUETE) {
      type = ERROR_MESSAGE;
    }

    this.showConfirmDialog(this,texte,titre,JOptionPane.DEFAULT_OPTION,JOptionPane.ERROR_MESSAGE);
    if(typeErreur == ERREUR_DRIVER || typeErreur == ERREUR_CONNEXION) {
      System.exit(1);
    }
  }

}
