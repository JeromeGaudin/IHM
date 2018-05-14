# Chambre d'hôtel

## Description
Il y a deux programmes un pour le gérant de l'hôtel et un autre pour avoir les statistiques
Le programme "Reservation" permet d'attribuer une chambre à un client qui a déjà fait sa réservation sur internet. Il permet aussi de choisir une autre chambre si celle-ci ne convient pas au client. La réservation peut être trouvée grâce au nom et prénom du client ou grace au numéro de réservation.

Le programme "statistique" permet de connaitre le taux de chambres occupées sur une journée ou sur plusieurs jours et le taux de non présentation (ceux qui ont réservé mais qui ne sont pas venus).

## Utiliser l'application

### Prérequis
 - Avoir Java Developpement Kit (JDK).
 - Avoir accès à un serveur MySQL

### Installation
1. Télécharger l'archive git, la décompresser

2. Les programmes sont inutilisables dans l'état car je n'ai pas mis le mot de passe de ma base de données par sécurité. Donc si vous voulez utiliser les programmes il faut vous créer une base de données MySQL. Toutes les requêtes SQL pour créer et remplir les tables sont dans le ficher sql.

3. Il faut modifier le nom de domaine et le mot de passe auquel le programme se connecte, pour cela modifier les lignes des fichiers suivants : 
Dans le répertoire Reservation, modifier la ligne 18 du fichier BaseDeDonnee.java
Dans le répertoire Statistique, modifier la ligne 16 du fichier BaseDeDonnee.java

4. Compiler les sources avec la commande ```make```

### Lancement
Pour lancer le programme de statistique tapez la commande ```java Statistique``` dans le répertoire Statistique
Pour lancer le programme de réservation tapez la commande ```java Reservation``` dans le répertoire Reservation

## Auteurs
- Victor SRRENA
- [Jérôme GAUDIN](https://github.com/JeromeGaudin)
