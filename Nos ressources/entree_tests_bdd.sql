USE ENCHERES
Go 

insert into Utilisateurs (pseudo, nom, prenom, email, telephone, rue, code_postal, ville, mot_de_passe, credit, actif)
values ('dbadmin', 'nomUser', 'prenomuser', 'monemail@gmail.com', '0600000000', 'ma rue', '01000', 'VILLE','$2a$10$PrI5Gk9L.tSZiW9FXhTS8O8Mz9E97k2FZbFvGFFaSsiTUIl.TCrFu', 10000, 1);
 
insert into Utilisateurs (pseudo, nom, prenom, email, telephone, rue, code_postal, ville, mot_de_passe, credit, actif)
values ('dbuser', 'nomUser2', 'prenomuser2', 'monemailadmin@gmail.com', '0600000000', 'ma rue', '01000', 'VILLE','$2a$10$PrI5Gk9L.tSZiW9FXhTS8O8Mz9E97k2FZbFvGFFaSsiTUIl.TCrFu', 10000, 1);
 
---
 
insert into app_role (ROLE_NAME)
values ('ROLE_ADMIN');

insert into app_role (ROLE_NAME)
values ('ROLE_USER');
 
---
 
insert into user_role (no_utilisateur, ROLE_ID)		-- utilisateur 1 est admin et login 
values (1, 1);
 
insert into user_role (no_utilisateur, ROLE_ID)
values (1, 2);
 
insert into user_role (no_utilisateur, ROLE_ID) -- utilisateur 2 est juste connect�
values (2, 2);

---

insert into categories (libelle) values ('Maison'); 
insert into categories (libelle) values ('Jardin'); 
insert into categories (libelle) values ('Cuisine'); 

---

insert into articles_vendus (nom_article, description, date_debut_encheres, date_fin_encheres, prix_initial, no_utilisateur, no_categorie) 
values ('Vélo', 'Vélo ancien en bon état de marche', '2020-09-05', '2020-09-12', 150, 1, 2); 

insert into articles_vendus (nom_article, description, date_debut_encheres, date_fin_encheres, prix_initial, no_utilisateur, no_categorie) 
values ('Robot multifonction', 'Robot de cuisine, fonctionne très bien', '2020-09-06', '2020-09-011', 50, 1, 3); 

insert into articles_vendus (nom_article, description, date_debut_encheres, date_fin_encheres, prix_initial, no_utilisateur, no_categorie) 
values ('Chaise de jardin', 'Chaise de jardin en bois vert', '2020-09-06', '2020-09-09', 100, 2, 2); 

insert into articles_vendus (nom_article, description, date_debut_encheres, date_fin_encheres, prix_initial, no_utilisateur, no_categorie) 
values ('Vélo enfant', 'Vélo en très état de marche', '2020-09-03', '2020-09-06', 200, 2, 2); 

insert into articles_vendus (nom_article, description, date_debut_encheres, date_fin_encheres, prix_initial, no_utilisateur, no_categorie) 
values ('Sophie la girafe', 'Jouet pour bébé', '2020-09-03', '2020-09-10', 150, 1, 1); 

insert into articles_vendus (nom_article, description, date_debut_encheres, date_fin_encheres, prix_initial, no_utilisateur, no_categorie) 
values ('Wii', 'Wii de 2010, plus de manuel mais bon état de marche', '2020-09-05', '2020-09-09', 300, 1, 1); 

---

insert into retraits (rue, code_postal, ville, no_article) values ('10 rue des fleurs', 55000, 'MANS', 1); 
insert into retraits (rue, code_postal, ville, no_article) values ('10 rue des fleurs', 55000, 'MANS', 2); 
insert into retraits (rue, code_postal, ville, no_article) values ('25 rue des citrons', 54000, 'VILLETTE', 3); 
insert into retraits (rue, code_postal, ville, no_article) values ('25 rue des citrons', 54000, 'VILLETTE', 4); 
insert into retraits (rue, code_postal, ville, no_article) values ('10 rue des fleurs', 55000, 'MANS', 5); 
insert into retraits (rue, code_postal, ville, no_article) values ('10 rue des fleurs', 55000, 'MANS', 6); 

---

select * from utilisateurs;

select * from app_role; 

select * from user_role ; 

select * from articles_vendus;

select * from retraits;

select * from categories; 

-- 

update utilisateurs set credit=10000; 