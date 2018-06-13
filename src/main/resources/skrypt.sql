drop table MEMBERS if exists;
drop table BOOKS if exists;
DROP TABLE BORROWED_BOOKS if exists;
create table MEMBERS(
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
CREATE TABLE BORROWED_BOOKS (
memberID varchar(255),
bookID varchar(255),
FOREIGN KEY (bookID) REFERENCES BOOKS(bookID),
FOREIGN KEY (memberID) REFERENCES MEMBERS(ID) );


INSERT INTO BOOKS
VALUES ('e0d2e501-e262-4c71-893d-16ae55665f66', 'Adrzej Habzda', 'Dupa', '1999', 'dsdasdasda');
INSERT INTO BOOKS
VALUES ('2a5b9af3-ef34-474f-ab0c-2d49ddf5d63b', 'Adrzej Bieruta', 'Wielka Dupa', '1999', 'dsdasdasda');
INSERT INTO BOOKS
VALUES ('f6c08d2e-a96e-4380-9f98-da11387c3d17', 'Adrzej Cham', 'Pospolita Dupa', '1999', 'dsdasdasda');
INSERT INTO BOOKS
VALUES ('55514153-43ce-4977-84fa-fccf94cf4fb1', 'Adrzej Maly', 'Niezreczna Dupa', '1999', 'dsdasdasda');

INSERT INTO MEMBERS (ID, NAME)
VALUES ('ba31dcad-5ce7-4965-9fc5-86ca54d9bedc', 'Mikolaj Zakoscielny');
INSERT INTO MEMBERS (ID, NAME)
VALUES ('9a8615ce-61d7-41bd-bf42-6886b5d9bc81', 'Andrzej Dzialowy');
INSERT INTO MEMBERS (ID, NAME)
VALUES ('c1a02da7-eba3-4ec8-858b-1a9e727d9fe7', 'Jan Sobieski');
INSERT INTO MEMBERS (ID, NAME)
VALUES ('2e50df9a-ccd7-418d-928f-6d1eff22f6ba', 'Kazimierz Wielki');

INSERT INTO BORROWED_BOOKS(memberID, bookID)
VALUES ('ba31dcad-5ce7-4965-9fc5-86ca54d9bedc', '55514153-43ce-4977-84fa-fccf94cf4fb1');
INSERT INTO BORROWED_BOOKS(memberID, bookID)
VALUES ('2e50df9a-ccd7-418d-928f-6d1eff22f6ba', 'f6c08d2e-a96e-4380-9f98-da11387c3d17');