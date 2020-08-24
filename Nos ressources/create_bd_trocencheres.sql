-- Script de création de la base de données ENCHERES
--   type :      SQL Server 2012
--

CREATE TABLE CATEGORIES (
    no_categorie   INTEGER IDENTITY(1,1) NOT NULL,
    libelle        VARCHAR(30) NOT NULL
)

ALTER TABLE CATEGORIES ADD constraint categorie_pk PRIMARY KEY (no_categorie)

CREATE TABLE ENCHERES (
    no_utilisateur   INTEGER NOT NULL,
    no_article       INTEGER NOT NULL,
    date_enchere     datetime NOT NULL,
	montant_enchere  INTEGER NOT NULL

)

ALTER TABLE ENCHERES ADD constraint enchere_pk PRIMARY KEY (no_utilisateur, no_article)

CREATE TABLE RETRAITS (
	no_article         INTEGER NOT NULL,
    rue              VARCHAR(50) NOT NULL,					-- champ trop petit
    code_postal      VARCHAR(15) NOT NULL,
    ville            VARCHAR(30) NOT NULL
)

ALTER TABLE RETRAITS ADD constraint retrait_pk PRIMARY KEY  (no_article)

CREATE TABLE UTILISATEURS (
    no_utilisateur   INTEGER IDENTITY(1,1) NOT NULL,
    pseudo           VARCHAR(30) NOT NULL,
    nom              VARCHAR(30) NOT NULL,
    prenom           VARCHAR(30) NOT NULL,
    email            VARCHAR(30) NOT NULL,				-- champ trop petit
    telephone        VARCHAR(15),
    rue              VARCHAR(50) NOT NULL,				-- champ trop petit
    code_postal      VARCHAR(10) NOT NULL,
    ville            VARCHAR(30) NOT NULL,
    mot_de_passe     VARCHAR(30) NOT NULL,
    credit           INTEGER NOT NULL,
    actif   bit NOT NULL								-- ajout de tables app_role et user_role pour la gestion des droits avec spring security, gestion de l'admin de cette manière 
														
) 

ALTER TABLE UTILISATEURS ADD constraint utilisateur_pk PRIMARY KEY (no_utilisateur)
ALTER TABLE UTILISATEURS ADD constraint utilisateur_uk UNIQUE (pseudo);		-- un pseudo unique ! 

-- Create table pour les rôles et leur nom (pour spring sécurity) 
create table APP_ROLE (
  ROLE_ID   INTEGER IDENTITY(1,1) NOT NULL,
  ROLE_NAME VARCHAR(30) not null
) ;
alter table APP_ROLE add constraint APP_ROLE_PK primary key (ROLE_ID);
alter table APP_ROLE add constraint APP_ROLE_UK unique (ROLE_NAME);
 
 -- Create table pour le lien utilisateur / rôle (pour spring security) 
create table USER_ROLE (
  ID			 INTEGER IDENTITY(1,1) NOT NULL,			-- identity généré automatiquement dans entity (jpa)
  no_utilisateur BIGINT not null,
  ROLE_ID		 BIGINT not null
);
--  
alter table USER_ROLE  add constraint USER_ROLE_PK primary key (ID);
alter table USER_ROLE  add constraint USER_ROLE_UK unique (no_utilisateur, ROLE_ID);
alter table USER_ROLE  add constraint USER_ROLE_FK1 foreign key (no_utilisateur)  references APP_USER (no_utilisateur);
alter table USER_ROLE  add constraint USER_ROLE_FK2 foreign key (ROLE_ID)  references APP_ROLE (ROLE_ID);
  
-- Create table, Used by Spring Remember Me API.  
CREATE TABLE Persistent_Logins (
 
    username varchar(64) not null,
    series varchar(64) not null,
    token varchar(64) not null,
    last_used Datetime not null,
    PRIMARY KEY (series)
    );
	
CREATE TABLE ARTICLES_VENDUS (
    no_article                    INTEGER IDENTITY(1,1) NOT NULL,
    nom_article                   VARCHAR(30) NOT NULL,
    description                   VARCHAR(300) NOT NULL,
	date_debut_encheres           DATE NOT NULL,
    date_fin_encheres             DATE NOT NULL,
    prix_initial                  INTEGER,
    prix_vente                    INTEGER,
    no_utilisateur                INTEGER NOT NULL,
    no_categorie                  INTEGER NOT NULL
)

ALTER TABLE ARTICLES_VENDUS ADD constraint articles_vendus_pk PRIMARY KEY (no_article)

ALTER TABLE ARTICLES_VENDUS
    ADD CONSTRAINT encheres_utilisateur_fk FOREIGN KEY ( no_utilisateur ) REFERENCES UTILISATEURS ( no_utilisateur )
ON DELETE NO ACTION 
    ON UPDATE no action 

ALTER TABLE ENCHERES
    ADD CONSTRAINT encheres_articles_vendus_fk FOREIGN KEY ( no_article )
        REFERENCES ARTICLES_VENDUS ( no_article )
ON DELETE NO ACTION 
    ON UPDATE no action 

ALTER TABLE RETRAITS
    ADD CONSTRAINT retraits_articles_vendus_fk FOREIGN KEY ( no_article )
        REFERENCES ARTICLES_VENDUS ( no_article )
ON DELETE NO ACTION 
    ON UPDATE no action 

ALTER TABLE ARTICLES_VENDUS
    ADD CONSTRAINT articles_vendus_categories_fk FOREIGN KEY ( no_categorie )
        REFERENCES categories ( no_categorie )
ON DELETE NO ACTION 
    ON UPDATE no action 

ALTER TABLE ARTICLES_VENDUS
    ADD CONSTRAINT ventes_utilisateur_fk FOREIGN KEY ( no_utilisateur )
        REFERENCES utilisateurs ( no_utilisateur )
ON DELETE NO ACTION 
    ON UPDATE no action 

-- Used by Spring Remember Me API.  pas à créer donc ? 
CREATE TABLE Persistent_Logins (
 
    username varchar(64) not null,
    series varchar(64) not null,
    token varchar(64) not null,
    last_used Datetime not null,
    PRIMARY KEY (series)
     
);

