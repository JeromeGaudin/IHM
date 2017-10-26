import java.sql.*;
import java.util.List;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

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
  			connexiongaudin = DriverManager.getConnection("jdbc:mariadb://localhost/nomDeLaBaseDeDonées", "root", "motDePasse"); // ligne a changer
  			}catch(SQLException e) {
          this.connexionihm.close();
  				System.err.println("Erreur connexion : base de donnée interne");
          MessageErreur erreur = new MessageErreur(MessageErreur.ERREUR_CONNEXION);
  			}
      } catch(SQLException e) {
        System.err.println("Erreur connexion : base de donnée externe");
        MessageErreur erreur = new MessageErreur(MessageErreur.ERREUR_CONNEXION);
      }
    } catch(ClassNotFoundException e) {
      System.err.println("Erreur du driver");
      MessageErreur erreur = new MessageErreur(MessageErreur.ERREUR_DRIVER);
    }
  }

	public static BaseDeDonnee getInstance() {
		if(instance == null)
			instance = new BaseDeDonnee();
		return instance;
	}

	private int chercherClient(String prenom, String nom) {
		int res=0;
		try {
			PreparedStatement requete = connexionihm.prepareStatement("SELECT id FROM Client WHERE prenom = ? AND nom = ?;");
			requete.setString(1, prenom);
			requete.setString(2, nom);
			ResultSet resSQL = requete.executeQuery();
			if(resSQL.first()) {
				res = resSQL.getInt(1);
			} else {
				// le client n'est pas trouvé
				MessageErreur erreur = new MessageErreur(MessageErreur.CLIENT_NON_TROUVE);
			}
			resSQL.close();
			requete.close();
		} catch(SQLException e) {
			System.err.println("Erreur de la requete chercherClient");
			MessageErreur erreur = new MessageErreur(MessageErreur.ERREUR_REQUETE);
		}
		return res;
	}

	public List<Reservation> chercherReservation(String reference) {
		List<Reservation> list = new ArrayList<Reservation>();
		try {
			PreparedStatement requete = connexionihm.prepareStatement("SELECT C.id, reference, debut, nuits, sigle, texte, R.id FROM Reservation R INNER JOIN Categorie C ON R.categorie = C.id WHERE reference = ?;");
			requete.setString(1, reference);
			ResultSet resSQL = requete.executeQuery();
			if(resSQL.first()) {
				int resId = resSQL.getInt(1);
				String resReference = resSQL.getString(2);

				//obtenir la date
				String dateSQL = resSQL.getString(3);
				java.sql.Date resDate = java.sql.Date.valueOf(dateSQL);

				int resNuits = resSQL.getInt(4);
				String resSigne = resSQL.getString(5);
				String resTexte = resSQL.getString(6);
				int resIdRes = resSQL.getInt(7);
				Reservation reservation = new Reservation(resIdRes, resId, resReference, resDate, resNuits, resSigne, resTexte);
				list.add(reservation);
			} else {// sinon la reservation n'est pas trouvée
				MessageErreur erreur = new MessageErreur(MessageErreur.RESERVATION_NON_TROUVE);
			}
			resSQL.close();
			requete.close();
		} catch(SQLException e) {
			System.err.println("Erreur de la requete chercherReservation(id)");
			MessageErreur erreur = new MessageErreur(MessageErreur.ERREUR_REQUETE);
		}
		return list;
	}

	public List<Reservation> chercherReservation(String prenom, String nom) {
		List<Reservation> list = new ArrayList<Reservation>();
		int idClient = chercherClient(prenom,nom);
		if(idClient == 0) { // client non trouvé
			return list;
		}
		try {
			PreparedStatement requete = connexionihm.prepareStatement("SELECT C.id, reference, debut, nuits, sigle, texte, R.id FROM Reservation R INNER JOIN Categorie C ON R.categorie = C.id WHERE client = ?;");
			requete.setInt(1, idClient);
			ResultSet resSQL = requete.executeQuery();
			while(resSQL.next()) {
				int resId = resSQL.getInt(1);
				String resReference = resSQL.getString(2);

				//obtenir la date
				String dateSQL = resSQL.getString(3);
				java.sql.Date resDate = java.sql.Date.valueOf(dateSQL);

				int resNuits = resSQL.getInt(4);
				String resSigne = resSQL.getString(5);
				String resTexte = resSQL.getString(6);
				int resIdRes = resSQL.getInt(7);
				Reservation reservation = new Reservation(resIdRes, resId, resReference, resDate, resNuits, resSigne, resTexte);
				list.add(reservation);
			}
			if( !resSQL.first())  {// sinon la reservation n'est pas trouvée
				MessageErreur erreur = new MessageErreur(MessageErreur.RESERVATION_NON_TROUVE);
			}
			resSQL.close();
			requete.close();
		} catch(SQLException e) {
			System.err.println("Erreur de la requete chercherReservation(prenom, nom)");
			MessageErreur erreur = new MessageErreur(MessageErreur.ERREUR_REQUETE);
		}
		return list;
	}

	public List<Chambre> chambreLibre(int idCategorie, Calendar date) {
		List<Chambre> list = new ArrayList<Chambre>();
		try {
			PreparedStatement requete0 = connexiongaudin.prepareStatement("SELECT id, numero  FROM IHM_Chambre WHERE categorie = ? AND id NOT IN (SELECT chambre FROM IHM_Occupe);");
			PreparedStatement requete1 = connexiongaudin.prepareStatement("SELECT reservation, C.id, numero FROM IHM_Occupe O JOIN IHM_Chambre C ON O.chambre = C.numero WHERE categorie = ?");
			PreparedStatement requete2 = connexionihm.prepareStatement("SELECT debut, ADDDATE(debut,nuits) FROM Reservation WHERE id = ?");

			//chambre qui n'ont jamais été occupé
			requete0.setInt(1, idCategorie);
			ResultSet resSQLChambreJamaisOccupe = requete0.executeQuery();
			while(resSQLChambreJamaisOccupe.next()) {
					list.add(new Chambre(resSQLChambreJamaisOccupe.getInt(1), resSQLChambreJamaisOccupe.getInt(2)));
			}
			
			resSQLChambreJamaisOccupe.close();
			requete0.close();
			requete1.setInt(1, idCategorie);
			ResultSet resSQLOccupe = requete1.executeQuery();
			while(resSQLOccupe.next()) {
				int idReservation = resSQLOccupe.getInt(1);

				//vérifie la date
				Calendar[] cal = new Calendar[2];
				requete2.setInt(1, idReservation);
				ResultSet resSQLReservation = requete2.executeQuery();
				if(resSQLReservation.first()) {
					Date date1 = Date.valueOf(resSQLReservation.getString(1));
					Date date2 = Date.valueOf(resSQLReservation.getString(2));
					cal[0] = new GregorianCalendar();
					cal[1] = new GregorianCalendar();
					cal[0].setTime(date1);
					cal[1].setTime(date2);
				} // sinon la reservation n'est pas trouvée
				resSQLReservation.close();

				if(date.compareTo(cal[0]) < 0  || date.compareTo(cal[1]) > 0) {
					//créer l'objet chambre
					list.add(new Chambre(resSQLOccupe.getInt(2), resSQLOccupe.getInt(3)));
				}
			}
			requete2.close();
			resSQLOccupe.close();
			requete1.close();
		} catch(SQLException e)	{
			System.err.println("Erreur de la requete chambreLibre");
			MessageErreur erreur = new MessageErreur(MessageErreur.ERREUR_REQUETE);
		}
		return list;
	}

	public Chambre uneChambreLibre(int idCategorie, Calendar date) {
		Chambre chambre = null;
		try {
			PreparedStatement requete0 = connexiongaudin.prepareStatement("SELECT id, numero FROM IHM_Chambre WHERE categorie = ? AND id NOT IN (SELECT chambre FROM IHM_Occupe);");
			PreparedStatement requete1 = connexiongaudin.prepareStatement("SELECT reservation, C.id, numero, categorie FROM IHM_Occupe O JOIN IHM_Chambre C ON O.chambre = C.numero WHERE categorie = ?");
			PreparedStatement requete2 = connexionihm.prepareStatement("SELECT debut, ADDDATE(debut,nuits) FROM Reservation WHERE id = ?");

			//chambre qui n'ont jamais été occupé
			requete0.setInt(1, idCategorie);
			ResultSet resSQLChambreJamaisOccupe = requete0.executeQuery();
			if(resSQLChambreJamaisOccupe.first()) {
					chambre = new Chambre(resSQLChambreJamaisOccupe.getInt(1), resSQLChambreJamaisOccupe.getInt(2));
					resSQLChambreJamaisOccupe.close();
					requete0.close();
					return chambre;
			}

			requete1.setInt(1, idCategorie);
			ResultSet resSQLOccupe = requete1.executeQuery();
			while(resSQLOccupe.next()) {
				int idReservation = resSQLOccupe.getInt(1);

				//vérifie la date
				Calendar[] cal = new Calendar[2];
				requete2.setInt(1, idReservation);
				ResultSet resSQLReservation = requete2.executeQuery();
				if(resSQLReservation.first()) {
					Date date1 = Date.valueOf(resSQLReservation.getString(1));
					Date date2 = Date.valueOf(resSQLReservation.getString(2));
					cal[0] = new GregorianCalendar();
					cal[1] = new GregorianCalendar();
					cal[0].setTime(date1);
					cal[1].setTime(date2);
				} else  {// sinon la reservation n'est pas trouvée
					System.out.println("res non trouvé");
				}
				resSQLReservation.close();

				if(date.compareTo(cal[0]) < 0  || date.compareTo(cal[1]) > 0) {
					//créer l'objet chambre
					chambre = new Chambre(resSQLOccupe.getInt(2), resSQLOccupe.getInt(3));
					requete2.close();
					resSQLOccupe.close();
					requete1.close();
					return chambre;
				}
			}
			requete2.close();
			resSQLOccupe.close();
			requete1.close();
		} catch(SQLException e)	{
			System.err.println("Erreur de la requete uneChambreLibre");
			MessageErreur erreur = new MessageErreur(MessageErreur.ERREUR_REQUETE);
		}
		return chambre;
	}

	public Categorie categorie(int idCat) {
		Categorie categorie = null;
		try {
			PreparedStatement requete = connexionihm.prepareStatement("SELECT * FROM Categorie WHERE id = ?");
			requete.setInt(1, idCat);
			ResultSet resSQL = requete.executeQuery();
			if(resSQL.first()) {
				String sigle = resSQL.getString(2);
				String texte = resSQL.getString(3);
				categorie = new Categorie(idCat, sigle, texte);
			}
		} catch(SQLException e) {
			System.err.println("Erreur de la requete categorie");
			MessageErreur erreur = new MessageErreur(MessageErreur.ERREUR_REQUETE);
		}
		return categorie;
	}

	public void attibuerUneChambre(int idRes, int idChambre) {
		try {
			PreparedStatement requete = connexiongaudin.prepareStatement("INSERT INTO IHM_Occupe (chambre, reservation) VALUES (?,?);");
			requete.setInt(2,idRes);
			requete.setInt(1,idChambre);
			requete.executeUpdate();
		} catch(SQLException e) {
			System.err.println("Erreur de la requete attibuerUneChambre");
			MessageErreur erreur = new MessageErreur(MessageErreur.ERREUR_REQUETE);
		}
	}

	public boolean reservationAChambre(int idRes) { // si une reservation a déjà été attribuer a une chambre
		try {
			PreparedStatement requete = connexiongaudin.prepareStatement("SELECT * FROM IHM_Occupe WHERE reservation = ?");
			requete.setInt(1,idRes);
			ResultSet resSQL = requete.executeQuery();
			if(resSQL.first()) {
				return true;
			} else {
				return false;
			}
		} catch(SQLException e) {
			System.err.println("Erreur de la requete reservationAChambre");
			MessageErreur erreur = new MessageErreur(MessageErreur.ERREUR_REQUETE);
		}
		return true;
	}

	public void fermerConnexion() {
		try{
			connexionihm.close();
			connexiongaudin.close();
		} catch(SQLException e) { // pas d'impact sur l'utilisation du programme
			System.err.println("Erreur destruction requete préparé");
		}
	}
}
