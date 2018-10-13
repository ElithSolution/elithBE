
CREATE TABLE typetarif (
    idtypetarif integer DEFAULT 0 NOT NULL,
    tarif character varying(56) NOT NULL,
    ordre integer DEFAULT 0 NOT NULL
);


ALTER TABLE public.typetarif OWNER TO postgres;

INSERT INTO typetarif(idtypetarif, tarif, ordre) VALUES (1, 'Plein tarif', 1);
INSERT INTO typetarif(idtypetarif, tarif, ordre) VALUES (2, 'Tarif réduit', 2);
INSERT INTO typetarif(idtypetarif, tarif, ordre) VALUES (3, 'Gratuit', 3);
INSERT INTO typetarif(idtypetarif, tarif, ordre) VALUES (4, 'Certificat cadeau réduit', 4);
INSERT INTO typetarif(idtypetarif, tarif, ordre) VALUES (5, 'Certificat cadeau gratuit', 5);

CREATE TABLE typepaiement (
    idtypepaiement integer DEFAULT 0 NOT NULL,
    paiement character varying(56) NOT NULL,
    ordre integer DEFAULT 0 NOT NULL
);

INSERT INTO typepaiement VALUES ('1', 'espèces', '1');
INSERT INTO typepaiement VALUES ('2', 'chèque', '2');
INSERT INTO typepaiement VALUES ('3', 'bon cadeau', '3');
INSERT INTO typepaiement VALUES ('4', 'forfait', '4');
INSERT INTO typepaiement VALUES ('5', 'carte débit', '5');
INSERT INTO typepaiement VALUES ('6', 'carte visa', '6');

ALTER TABLE public.typepaiement OWNER TO postgres;

ALTER TABLE ONLY typepaiement
    ADD CONSTRAINT idtypepaiement PRIMARY KEY (idtypepaiement);
ALTER TABLE ONLY typetarif
    ADD CONSTRAINT idtypetarif PRIMARY KEY (idtypetarif);


update traitement set modepaiement = 1 where modepaiement = 'espèces' ;
update traitement set modepaiement = 2 where modepaiement = 'chèque' ;
update traitement set modepaiement = 3 where modepaiement = 'bon cadeau' ;
update traitement set modepaiement = 4 where modepaiement = 'forfait' ;

update traitement set tarif = 1 where tarif = 'Plein tarif' ;
update traitement set tarif = 2 where tarif = 'Tarif réduit' ;
update traitement set tarif = 3 where tarif = 'Gratuit' ;
update traitement set tarif = 4 where tarif = 'Certificat cadeau réduit' ;
update traitement set tarif = 5 where tarif = 'Certificat cadeau gratuit' ;

ALTER TABLE traitement
    ALTER COLUMN modepaiement TYPE integer USING modepaiement::integer ;
	
update traitement set tarif = 1 where tarif IS NULL ;
update traitement set tarif = 1 where tarif = '' ;

ALTER TABLE traitement
    ALTER COLUMN tarif TYPE integer USING tarif::integer ;

ALTER TABLE typepaiement
    ADD COLUMN idclinique integer ;
	
ALTER TABLE typetarif
    ADD COLUMN idclinique integer ;

update typepaiement set idclinique = 1;	
update typetarif set idclinique = 1;