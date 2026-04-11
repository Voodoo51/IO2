CREATE TABLE SPRING_SESSION (
    PRIMARY_ID CHAR(36) NOT NULL,
    SESSION_ID CHAR(36) NOT NULL,
    CREATION_TIME BIGINT NOT NULL,
    LAST_ACCESS_TIME BIGINT NOT NULL,
    MAX_INACTIVE_INTERVAL INT NOT NULL,
    EXPIRY_TIME BIGINT NOT NULL,
    PRINCIPAL_NAME VARCHAR(100),
    CONSTRAINT SPRING_SESSION_PK PRIMARY KEY (PRIMARY_ID)
);

CREATE UNIQUE INDEX SPRING_SESSION_IX1 ON SPRING_SESSION (SESSION_ID);
CREATE INDEX SPRING_SESSION_IX2 ON SPRING_SESSION (EXPIRY_TIME);
CREATE INDEX SPRING_SESSION_IX3 ON SPRING_SESSION (PRINCIPAL_NAME);

CREATE TABLE SPRING_SESSION_ATTRIBUTES (
    SESSION_PRIMARY_ID CHAR(36) NOT NULL,
    ATTRIBUTE_NAME VARCHAR(200) NOT NULL,
    ATTRIBUTE_BYTES BYTEA NOT NULL,
    CONSTRAINT SPRING_SESSION_ATTRIBUTES_PK PRIMARY KEY (SESSION_PRIMARY_ID, ATTRIBUTE_NAME),
    CONSTRAINT SPRING_SESSION_ATTRIBUTES_FK FOREIGN KEY (SESSION_PRIMARY_ID) REFERENCES SPRING_SESSION(PRIMARY_ID) ON DELETE CASCADE
);

CREATE TABLE klasa(
      id SERIAL primary key,
      nazwa varchar(5)
);

CREATE TABLE uzytkownik_typ(
      id SERIAL primary key,
      nazwa varchar(25)
);

CREATE TABLE uzytkownik(
      id SERIAL primary key,
      uzytkownik_typ_id int references uzytkownik_typ(id),
      klasa_id int references klasa(id),
      email varchar(30) UNIQUE,
      haslo varchar(30),
      imie varchar(30),
      nazwisko varchar(30)
);

CREATE TABLE przedmiot(
      id SERIAL primary key,
      nazwa varchar(25)
);

CREATE TABLE ocena_opis(
      id SERIAL primary key,
      opis text
);

CREATE TABLE ocena(
      id SERIAL primary key,
      ocena_opis_id int references ocena_opis(id),
      uczen_id int references uzytkownik(id),
      nauczyciel_id int references uzytkownik(id),
      przedmiot_id int references przedmiot(id),
      wartosc integer,
      waga integer
);

CREATE TABLE lekcja(
      id SERIAL primary key,
      klasa_id int references klasa(id),
      nauczyciel_id int references uzytkownik(id),
      przedmiot_id int references przedmiot(id),
      godzina timestamptz --with date and time
);

CREATE TABLE obecnosc(
      uczen_id int references uzytkownik(id),
      lekcja_id int references lekcja(id),
      obecnosc boolean,
      PRIMARY KEY(uczen_id, lekcja_id)
);

INSERT INTO klasa VALUES
(0, '1A'),
(1, '1B'),
(2, '2A');

INSERT INTO uzytkownik_typ VALUES
(0, 'Uczen'),
(1, 'Nauczyciel'),
(2, 'Opiekun'),
(3, 'Admin');

INSERT INTO uzytkownik VALUES 
(0, 0, 0, 'mail_test1@gmail.com', 'haslo', 'Katarzyna', 'Stepien'),
(1, 0, 0, 'mail_test2@gmail.com', 'haslo', 'Joanna', 'Bombol'),
(2, 0, 0, 'mail_test3@gmail.com', 'haslo', 'Maksymilian', 'Sulecki'),

(3, 0, 1, 'mail_test4@gmail.com', 'haslo', 'Oliwia', 'Pacocha'),
(4, 0, 1, 'mail_test5@gmail.com', 'haslo', 'Cezary', 'Bulka'),

(5, 0, 2, 'mail_test6@gmail.com', 'haslo', 'Szymon', 'Dziadek'),
(6, 0, 2, 'mail_test7@gmail.com', 'haslo', 'Szymon', 'Blaszczyk'),
(7, 0, 2, 'mail_test8@gmail.com', 'haslo', 'Cezary', 'Bula'),

(8, 1, 0, 'mail_test9@gmail.com', 'haslo', 'Jozef', 'Ciosmak'),
(9, 1, 2, 'mail_test10@gmail.com', 'haslo', 'Marian B.', 'Gorzalczany');

INSERT INTO przedmiot VALUES
(0, 'Matematyka'),
(1, 'Angielski'),
(2, 'Polski'),
(3, 'Biologia'),
(4, 'Fizyka');

INSERT INTO ocena_opis VALUES
(0, 'Ocena 1. Brak dobrze rozwiazanych zadan.'),
(1, 'Ocena 2. Blad w zadaniu 1, 2 i 3.'),
(2, 'Ocena 3. Blad w zadaniu 5 i 4.'),
(3, 'Ocena 4. Blad w zadaniu 4.'),
(4, 'Ocena 5.');

INSERT INTO ocena VALUES 
(0, 0, 0, 8, 0, 1, 3),
(1, 3, 1, 8, 0, 4, 3),
(2, 3, 2, 8, 0, 4, 3),

(3, 4, 3, 9, 3, 5, 3),
(4, 4, 4, 9, 3, 5, 3),

(5, 0, 5, 9, 4, 1, 5),
(6, 1, 6, 9, 4, 2, 5),
(7, 0, 7, 9, 4, 1, 5);

INSERT INTO lekcja VALUES
(0, 0, 8, 0, '2026-04-04 12:00:00+00'),
(1, 1, 9, 4, '2026-04-04 12:00:00+00'),
(2, 2, 9, 1, '2026-04-04 14:00:00+00');

INSERT INTO obecnosc VALUES
(0, 0, false),
(1, 0, true),
(2, 0, true),

(3, 1, true),
(4, 1, true),

(5, 2, true),
(6, 2, true),
(7, 2, false);




