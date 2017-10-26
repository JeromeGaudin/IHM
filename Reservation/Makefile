but : build

FILES = AfficherChambre.class \
	AfficherNumeroChambre.class \
	AfficherReservation.class \
	BaseDeDonnee.class \
	Categorie.class \
	Chambre.class \
	ControleurChamp.class \
	ControleurEchanger.class \
	ControleurFenetre.class \
	ControleurSelectionner.class \
	ControleurValider.class \
	Ecran.class \
	Effacer.class \
	EntrerInformation.class \
	MessageErreur.class \
	MonBouton.class \
	Reservation.class \
	Main.class

build: $(FILES)

AfficherChambre.class: AfficherChambre.java
	javac AfficherChambre.java

AfficherNumeroChambre.class: AfficherNumeroChambre.java
	javac AfficherNumeroChambre.java

AfficherReservation.class: AfficherReservation.java
	javac AfficherReservation.java

BaseDeDonnee.class: BaseDeDonnee.java
	javac BaseDeDonnee.java

Categrorie.class: Categorie.java
	javac Categorie.java

Chambre.class: Chambre.java
	javac Chambre.java

ControleurChamp.class: ControleurChamp.java
	javac ControleurChamp.java

ControleurEchanger.class: ControleurEchanger.java
	javac ControleurEchanger.java

ControleurFenetre.class: ControleurFenetre.java
	javac ControleurFenetre.java

ControleurSelectionner.class: ControleurSelectionner.java
	javac ControleurSelectionner.java

ControleurValider.class: ControleurValider.java
	javac ControleurValider.java

Ecran.class: Ecran.java
	javac Ecran.java

Effacer.class: Effacer.java
	javac Effacer.java

EntrerInformation.class: EntrerInformation.java
	javac EntrerInformation.java

MessageErreur.class: MessageErreur.java
	javac MessageErreur.java

MonBouton.class: MonBouton.java
	javac MonBouton.java

Reservation.class: Reservation.java
	javac Reservation.java

Main.class: Main.java
	javac Main.java


run:
	java -cp "/export/documents/mariadb-client.jar:." Main

clean:
	rm $(FILES)