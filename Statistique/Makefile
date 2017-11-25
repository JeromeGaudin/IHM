but : build

FILES = BaseDeDonnee.class \
	ControleurChamp.class \
	ControleurFenetre.class \
	EntrerDeuxDates.class \
	EntrerUneDate.class \
	ErreurBaseDeDonnee.class \
	Fenetre.class \
	Statistique.class \
	Taux.class

build: $(FILES)

BaseDeDonnee.class: BaseDeDonnee.java
	javac BaseDeDonnee.java

ControleurChamp.class: ControleurChamp.java
	javac ControleurChamp.java

ControleurFenetre.class: ControleurFenetre.java
	javac ControleurFenetre.java

EntrerDeuxDates.class: EntrerDeuxDates.java
	javac EntrerDeuxDates.java

EntrerUneDate.class: EntrerUneDate.java
	javac EntrerUneDate.java

ErreurBaseDeDonnee.class: ErreurBaseDeDonnee.java
	javac ErreurBaseDeDonnee.java

Fenetre.class: Fenetre.java
	javac Fenetre.java

Statistique.class: Statistique.java
	javac Statistique.java

Taux.class: Taux.java
	javac Taux.java

run:
	java -cp "../driver/mariadb-client.jar:." Statistique

clean:
	rm $(FILES)