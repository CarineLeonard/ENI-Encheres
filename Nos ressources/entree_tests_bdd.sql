USE ENCHERES
Go 

insert into Utilisateurs (pseudo, nom, prenom, email, telephone, rue, code_postal, ville, mot_de_passe, credit, actif)
values ('dbuser', 'nomUser', 'prenomuser', 'monemail@gmail.com', '0600000000', 'ma rue', '01000', 'VILLE','$2a$10$PrI5Gk9L.tSZiW9FXhTS8O8Mz9E97k2FZbFvGFFaSsiTUIl.TCrFu', 0, 1);
 
insert into Utilisateurs (pseudo, nom, prenom, email, telephone, rue, code_postal, ville, mot_de_passe, credit, actif)
values ('dbadmin', 'nomUser2', 'prenomuser2', 'monemailadmin@gmail.com', '0600000000', 'ma rue', '01000', 'VILLE','$2a$10$PrI5Gk9L.tSZiW9FXhTS8O8Mz9E97k2FZbFvGFFaSsiTUIl.TCrFu', 0, 1);
 
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

select * from utilisateurs;

select * from app_role; 

select * from user_role ; 

--- 

insert into categories (libelle) values ('maison'); 
insert into categories (libelle) values ('jardin'); 

select * from articles_vendus;

select * from categories; 