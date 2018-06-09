drop table MEMBERS if exists;
drop table BOOKS if exists;
create table MEMBERS (
ID varchar(255), 
Name varchar(255)
);
create table BOOKS(
bookID varchar(255),
author varchar(30), 
title varchar(30), 
releaseYear int,
description CLOB 
);
INSERT INTO BOOKS (bookID, author, title, releaseYear, description)
VALUES ('1', 'Adrzej Habzda', 'Dupa', '1999', 'dsdasdasda');
INSERT INTO BOOKS (bookID, author, title, releaseYear, description)
VALUES ('2', 'Adrzej Bieruta', 'Wielka Dupa', '1999', 'dsdasdasda');
INSERT INTO BOOKS (bookID, author, title, releaseYear, description)
VALUES ('3', 'Adrzej Cham', 'Pospolita Dupa', '1999', 'dsdasdasda');
INSERT INTO BOOKS (bookID, author, title, releaseYear, description)
VALUES ('4', 'Adrzej Maly', 'Niezreczna Dupa', '1999', 'dsdasdasda');

INSERT INTO MEMBERS (ID, NAME)
VALUES ('1', 'Mikolaj Zakoscielny');
INSERT INTO MEMBERS (ID, NAME)
VALUES ('2', 'Andrzej Dzialowy');
INSERT INTO MEMBERS (ID, NAME)
VALUES ('3', 'Jan Sobieski');
INSERT INTO MEMBERS (ID, NAME)
VALUES ('4', 'Kazimierz Wielki');


