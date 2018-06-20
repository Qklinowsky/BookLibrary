drop table MEMBERS if exists;
drop table BOOKS if exists;
DROP TABLE BORROWED_BOOKS if exists;
create table MEMBERS(
id varchar(36),
Password varchar(20),
Name varchar(255),
Role varchar(120)
);
create table BOOKS(
id varchar(36),
author varchar(30), 
title varchar(30), 
releaseYear int,
description CLOB 
);
CREATE TABLE BORROWED_BOOKS (
memberID varchar(36),
bookID varchar(36),
FOREIGN KEY (bookID) REFERENCES BOOKS(id),
FOREIGN KEY (memberID) REFERENCES MEMBERS(id) );


INSERT INTO BOOKS
VALUES ('e0d2e501-e262-4c71-893d-16ae55665f66', 'Adrzej Habzda', 'Tom Adventures', '1999', 'dsdasdasda');
INSERT INTO BOOKS
VALUES ('2a5b9af3-ef34-474f-ab0c-2d49ddf5d63b', 'Adnrzej Bieruta', 'Desert', '1999', 'dsdasdasda');
INSERT INTO BOOKS
VALUES ('f6c08d2e-a96e-4380-9f98-da11387c3d17', 'Andrzej Kowalski', 'Pinguins', '1999', 'dsdasdasda');
INSERT INTO BOOKS
VALUES ('55514153-43ce-4977-84fa-fccf94cf4fb1', 'Andrzej Maly', 'Silence', '1999', 'dsdasdasda');

INSERT INTO MEMBERS (ID, PASSWORD, NAME, ROLE)
VALUES ('ba31dcad-5ce7-4965-9fc5-86ca54d9bedc', '123', 'Mikolaj Zakoscielny', 'USER');
INSERT INTO MEMBERS (ID, PASSWORD, NAME, ROLE)
VALUES ('9a8615ce-61d7-41bd-bf42-6886b5d9bc81', '123', 'Andrzej Dzialowy', 'USER');
INSERT INTO MEMBERS (ID, PASSWORD, NAME, ROLE)
VALUES ('c1a02da7-eba3-4ec8-858b-1a9e727d9fe7', '123', 'Jan Sobieski', 'USER');
INSERT INTO MEMBERS (ID, PASSWORD, NAME, ROLE)
VALUES ('2e50df9a-ccd7-418d-928f-6d1eff22f6ba', '123', 'Kazimierz Wielki', 'USER');
INSERT INTO MEMBERS (ID, PASSWORD, NAME, ROLE)
VALUES ('56622612-479a-484a-b3c9-e623e64f9d4a', 'admin123', 'admin', 'ADMIN');

INSERT INTO BORROWED_BOOKS(memberID, bookID)
VALUES ('ba31dcad-5ce7-4965-9fc5-86ca54d9bedc', '55514153-43ce-4977-84fa-fccf94cf4fb1');
INSERT INTO BORROWED_BOOKS(memberID, bookID)
VALUES ('2e50df9a-ccd7-418d-928f-6d1eff22f6ba', 'f6c08d2e-a96e-4380-9f98-da11387c3d17');