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
      uczen_id int references uzytkownik(id),
      nauczyciel_id int references uzytkownik(id),
      przedmiot_id int references przedmiot(id),
      opis text,
      data date,
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
(2, '2A'),
(3, '2B'),
(4, '3A');

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
(9, 1, 2, 'mail_test10@gmail.com', 'haslo', 'Marian B.', 'Gorzalczany'),
(10, 1, 1, 'teacher11@gmail.com', 'haslo', 'Anna', 'Nowak'),
(11, 1, 3, 'teacher12@gmail.com', 'haslo', 'Piotr', 'Kowalski'),
(12, 0, 3, 'student12@gmail.com', 'haslo', 'Adam', 'Lis'),
(13, 0, 3, 'student13@gmail.com', 'haslo', 'Julia', 'Kurek'),
(14, 0, 3, 'student14@gmail.com', 'haslo', 'Natalia', 'Mazur'),
(15, 0, 3, 'student15@gmail.com', 'haslo', 'Karol', 'Wrona'),

(16, 0, 4, 'student16@gmail.com', 'haslo', 'Patryk', 'Kaczmarek'),
(17, 0, 4, 'student17@gmail.com', 'haslo', 'Oskar', 'Krupa'),
(18, 0, 4, 'student18@gmail.com', 'haslo', 'Amelia', 'Baran'),
(19, 0, 4, 'student19@gmail.com', 'haslo', 'Michal', 'Wilk'),

(20, 0, 4, 'student20@gmail.com', 'haslo', 'Maja', 'Kowalik'),
(21, 0, 3, 'student21@gmail.com', 'haslo', 'Filip', 'Dudek');

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
(0, 0, 8, 0, 'Ocena 1. Brak dobrze rozwiazanych zadan.', '2026-04-04', 1, 3),
(1, 1, 8, 0, 'Ocena 4. Blad w zadaniu 4.', '2026-04-04', 4, 3),
(2, 2, 8, 0, 'Ocena 4. Blad w zadaniu 4.', '2026-04-04', 4, 3),

(3, 3, 9, 3, 'Ocena 5.', '2026-04-05', 5, 3),
(4, 4, 9, 3, 'Ocena 5.', '2026-04-05', 5, 3),

(5, 5, 9, 4, 'Ocena 1. Brak dobrze rozwiazanych zadan.', '2026-04-06', 1, 5),
(6, 6, 9, 4, 'Ocena 2. Blad w zadaniu 1, 2 i 3.', '2026-04-06', 2, 5),
(7, 7, 9, 4, 'Ocena 1. Brak dobrze rozwiazanych zadan.', '2026-04-06', 1, 5),

(8, 12, 10, 0, 'Kartkowka', '2026-04-07', 5, 2),
(9, 13, 10, 0, 'Kartkowka', '2026-04-07', 4, 2),
(10, 14, 10, 0, 'Kartkowka', '2026-04-07', 3, 2),
(11, 15, 10, 0, 'Kartkowka', '2026-04-07', 2, 2),

(12, 12, 10, 0, 'Sprawdzian', '2026-04-09', 6, 5),
(13, 13, 10, 0, 'Sprawdzian', '2026-04-09', 5, 5),
(14, 14, 10, 0, 'Sprawdzian', '2026-04-09', 4, 5),
(15, 15, 10, 0, 'Sprawdzian', '2026-04-09', 2, 5),

(16, 21, 10, 0, 'Sprawdzian', '2026-04-09', 1, 5),

(17, 16, 11, 1, 'Vocabulary Test', '2026-04-07', 6, 3),
(18, 17, 11, 1, 'Vocabulary Test', '2026-04-07', 5, 3),
(19, 18, 11, 1, 'Vocabulary Test', '2026-04-07', 5, 3),
(20, 19, 11, 1, 'Vocabulary Test', '2026-04-07', 4, 3),
(21, 20, 11, 1, 'Vocabulary Test', '2026-04-07', 2, 3),

(22, 16, 11, 1, 'Essay', '2026-04-10', 6, 5),
(23, 17, 11, 1, 'Essay', '2026-04-10', 5, 5),
(24, 18, 11, 1, 'Essay', '2026-04-10', 4, 5),
(25, 19, 11, 1, 'Essay', '2026-04-10', 3, 5),
(26, 20, 11, 1, 'Essay', '2026-04-10', 1, 5),

(27, 0, 8, 0, 'Kartkowka', '2026-04-11', 2, 1),
(28, 1, 8, 0, 'Kartkowka', '2026-04-11', 5, 1),
(29, 2, 8, 0, 'Kartkowka', '2026-04-11', 3, 1),

(30, 5, 9, 4, 'Sprawdzian', '2026-04-11', 1, 5),
(31, 6, 9, 4, 'Sprawdzian', '2026-04-11', 3, 5),
(32, 7, 9, 4, 'Sprawdzian', '2026-04-11', 2, 5);

INSERT INTO lekcja VALUES
(0, 0, 8, 0, '2026-04-04 12:00:00+00'),
(1, 1, 9, 4, '2026-04-04 12:00:00+00'),
(2, 2, 9, 1, '2026-04-04 14:00:00+00'),

(3, 3, 10, 0, '2026-04-07 08:00:00+00'),
(4, 3, 10, 0, '2026-04-08 08:00:00+00'),
(5, 3, 10, 0, '2026-04-09 08:00:00+00'),

(6, 4, 11, 1, '2026-04-07 10:00:00+00'),
(7, 4, 11, 1, '2026-04-08 10:00:00+00'),
(8, 4, 11, 1, '2026-04-09 10:00:00+00'),

(9, 0, 8, 0, '2026-04-10 12:00:00+00'),
(10, 1, 9, 3, '2026-04-10 12:00:00+00'),
(11, 2, 9, 4, '2026-04-10 14:00:00+00');

INSERT INTO obecnosc VALUES
(0, 0, false),
(1, 0, true),
(2, 0, true),

(3, 1, true),
(4, 1, true),

(5, 2, true),
(6, 2, true),
(7, 2, false),

(12, 3, true),
(13, 3, true),
(14, 3, true),
(15, 3, false),
(21, 3, true),

(12, 4, true),
(13, 4, true),
(14, 4, false),
(15, 4, true),
(21, 4, false),

(12, 5, true),
(13, 5, true),
(14, 5, true),
(15, 5, true),
(21, 5, true),

(16, 6, true),
(17, 6, true),
(18, 6, false),
(19, 6, true),
(20, 6, false),

(16, 7, true),
(17, 7, true),
(18, 7, true),
(19, 7, true),
(20, 7, true),

(16, 8, true),
(17, 8, false),
(18, 8, true),
(19, 8, true),
(20, 8, true);



