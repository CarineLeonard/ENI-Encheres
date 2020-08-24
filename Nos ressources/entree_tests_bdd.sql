insert into Utilisateurs (no_utilisateur, pseudo, nom, prenom, email, telephone, rue, code_postal, ville, mot_de_passe, credit, actif)
values (1, 'dbuser', 'nomUser', 'prenomuser', 'monemail@gmail.com', '06.00.00.00.00', 'ma rue', '01000', 'VILLE','$2a$10$PrI5Gk9L.tSZiW9FXhTS8O8Mz9E97k2FZbFvGFFaSsiTUIl.TCrFu', 0, 1);
 
insert into Utilisateurs (no_utilisateur, pseudo, nom, prenom, email, telephone, rue, code_postal, ville, mot_de_passe, credit, actif)
values (2, 'dbadmin', 'nomUser2', 'prenomuser2', 'monemail@gmail.com', '06.00.00.00.00', 'ma rue', '01000', 'VILLE','$2a$10$PrI5Gk9L.tSZiW9FXhTS8O8Mz9E97k2FZbFvGFFaSsiTUIl.TCrFu', 0, 1);
 
---
 
insert into app_role (ROLE_ID, ROLE_NAME)
values (1, 'ROLE_ADMIN');

insert into app_role (ROLE_ID, ROLE_NAME)
values (2, 'ROLE_USER');
 
---
 
insert into user_role (id, no_utilisateur, ROLE_ID)		-- utilisateur 1 est admin et login 
values (1, 1, 1);
 
insert into user_role (id, no_utilisateur, ROLE_ID)
values (2, 1, 2);
 
insert into user_role (id, no_utilisateur, ROLE_ID) -- utilisateur 2 est juste connecté
values (3, 2, 2);

select * from utilisateurs;

select * from app_role; 

select * from user_role ; 