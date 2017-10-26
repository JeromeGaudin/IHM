# IHM
Ce projet a été fait dans le cadre du cours d'IHM (Interface Homme Machine).

Il y a deux programmes un pour le gerant de l'hôtel et un autre pour avoir les statistiques
Le programme "Reservation" permet d'attribuer une chambre à un client qui a déjà fait sa résèrvation sur internet. Il permet aussi de choisir une autre chambre si celle-ci ne convient pas au client. La réservation peut être trouvée grâce au nom et prénom du client ou grace au numéro de réservation.
Le programme "statistique" permet de connaitre le taux de chambres occupées sur une journée ou sur plusieurs jours et le taux de non présentation (ceux qui ont réservé mais qui ne sont pas venus).

Les programmes sont inutilisable si vous les téléchagez car je n'ai pas mis le mot de passe de ma base de données par sécurité. Donc si vous voulez utiliser les progrmmes il faut vous créer une base de données MySQL, toutes les requetes SQL pour créer et remplire les tables sont dans le ficher sql. 
Dans le répertoire Reservation modifier la ligne 18 dans le fichier BaseDeDonnee.java
Dans le répertoire Statistique modifier la ligne 16 dans le fichier BaseDeDonnee.java


Si vous avez créé votre base de données et si vous avez le JDK (Java Developement Kit) les programmes s'éxecuteront correctement sinon vous pouvez toujours regarder le code.
