CREATE TABLE IHM_Chambre (
	id MEDIUMINT PRIMARY KEY NOT NULL,
    numero MEDIUMINT,
    categorie MEDIUMINT
);

CREATE TABLE IHM_Occupe(
	chambre MEDIUMINT,
    reservation MEDIUMINT,
    FOREIGN KEY (chambre) REFERENCES IHM_Chambre(id),
    PRIMARY KEY (chambre, reservation)
);