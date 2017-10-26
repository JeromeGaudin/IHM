import java.sql.*;
import java.util.Calendar;
import java.text.SimpleDateFormat;

public class BaseDeDonnee {
  private static BaseDeDonnee instance = null;
  private Connection connexionihm;
  private Connection connexiongaudin;

  private BaseDeDonnee() {
    try {
      Class.forName("org.mariadb.jdbc.Driver");
      try {
        connexionihm = DriverManager.getConnection("jdbc:mariadb://dwarves.iut-fbleau.fr/projetihm", "projetihm", "mhitejorp");
        try {
  				connexiongaudin = DriverManager.getConnection("jdbc:mariadb://localhost/nomDeLaBaseDeDonnée", "root", "motDePasse"); // ligne à changer
  			}catch(SQLException e) {
          this.connexionihm.close();
  				System.err.println("Erreur connexion : base de donnée interne");
          ErreurBaseDeDonnee erreur = new ErreurBaseDeDonnee(ErreurBaseDeDonnee.ERREUR_CONNEXION);
  			}
      } catch(SQLException e) {
        System.err.println("Erreur connexion : base de donnée externe");
        ErreurBaseDeDonnee erreur = new ErreurBaseDeDonnee(ErreurBaseDeDonnee.ERREUR_CONNEXION);
      }
    } catch(ClassNotFoundException e) {
      System.err.println("Erreur du driver");
      ErreurBaseDeDonnee erreur = new ErreurBaseDeDonnee(ErreurBaseDeDonnee.ERREUR_DRIVER);
    }
  }

  public static BaseDeDonnee getInstance() {
    if(instance == null)
      instance = new BaseDeDonnee();
    return instance;
  }

  private int nombreDeChambre() {
    int nbChambre = 0;
    try {
      PreparedStatement requeteNbChambre = connexiongaudin.prepareStatement("SELECT COUNT(*) AS rowcount FROM IHM_Chambre");
      ResultSet resSQLnbChambre = requeteNbChambre.executeQuery();
      if(resSQLnbChambre.first()) {
        nbChambre = resSQLnbChambre.getInt(1);
      }
      resSQLnbChambre.close();
      requeteNbChambre.close();
    }catch(SQLException e) {
      System.err.println("Erreur de la requete nombreDeChambre");
      ErreurBaseDeDonnee erreur = new ErreurBaseDeDonnee(ErreurBaseDeDonnee.ERREUR_REQUETE);
    }
    return nbChambre;
  }

  private int nombreOccupant(Calendar date) {
    int chambreOccupe = 0;
    try {
      PreparedStatement requeteReservation = connexionihm.prepareStatement("SELECT id FROM Reservation WHERE ? >= debut AND ? <= ADDDATE(debut,nuits);");
      PreparedStatement requeteOccupe = connexiongaudin.prepareStatement("SELECT chambre FROM IHM_Occupe WHERE reservation = ?");

      // toutes les réservation pour tel jour
      SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
      requeteReservation.setString(1, sdf.format(date.getTime()));
      requeteReservation.setString(2, sdf.format(date.getTime()));
      ResultSet resSQLReservation = requeteReservation.executeQuery();
      while(resSQLReservation.next()) {
        //vérifie si une chambre est attibué à la réservation
        requeteOccupe.setInt(1, resSQLReservation.getInt(1));
        ResultSet resSQLOccupe = requeteOccupe.executeQuery();
        if(resSQLOccupe.first())
          chambreOccupe++;
        resSQLOccupe.close();
      }
      requeteOccupe.close();
      resSQLReservation.close();
      requeteReservation.close();
    } catch(SQLException e) {
      System.err.println("Erreur de la requete tauxOccupation(date)");
      ErreurBaseDeDonnee erreur = new ErreurBaseDeDonnee(ErreurBaseDeDonnee.ERREUR_REQUETE);
    }
    return chambreOccupe;
  }

  public float tauxOccupation(Calendar date) {
    float nbOccupant = (float) nombreOccupant(date);
    float nbChambre = (float) nombreDeChambre();
    return nbOccupant / nbChambre;
  }

  public float tauxNonPresentation(Calendar date) {
    int nbChambre = nombreDeChambre();
    int nombreOccupant = nombreOccupant(date);
    int nombreReservation =0;
    try {
      PreparedStatement requeteReservation = connexionihm.prepareStatement("SELECT COUNT(*) AS rowcount FROM Reservation WHERE ? >= debut AND ? <= ADDDATE(debut,nuits)");
      // toutes les réservation pour tel jour
      SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
      requeteReservation.setString(1, sdf.format(date.getTime()));
      requeteReservation.setString(2, sdf.format(date.getTime()));
      ResultSet resSQLReservation = requeteReservation.executeQuery();
      if(resSQLReservation.first())
        nombreReservation = resSQLReservation.getInt(1);
    } catch(SQLException e) {
      System.err.println("Erreur de la requete tauxNonPresentation");
      ErreurBaseDeDonnee erreur = new ErreurBaseDeDonnee(ErreurBaseDeDonnee.ERREUR_REQUETE);
    }
    if(nombreReservation ==0) {
      return 1;
    } else {
      return 1.0f -((float)nombreOccupant) / ((float)nombreReservation);
    }
  }

  public float tauxOccupation(Calendar debut, Calendar fin) {
    int nbOccupant =0, nbChambre=0;
    int nbChambreIncrement = nombreDeChambre();
    for(Calendar i= (Calendar) debut.clone(); i.getTimeInMillis()<=fin.getTimeInMillis(); i.add(Calendar.DATE, 1)) {
      nbOccupant += nombreOccupant(i);
      nbChambre += nbChambreIncrement;
    }

    return ((float)nbOccupant) / ((float)nbChambre);
  }

  public void fermerConnexion() {
    try {
      connexionihm.close();
      connexiongaudin.close();
    } catch(SQLException e) { // pas d'impact sur l'utilisation du programme
      System.err.println("Erreur lors de la fermeture de connexion");
    }
  }
}
