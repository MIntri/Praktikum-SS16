SET ECHO ON

/* DBS2: Beispiel Blatt02 mit Fehlern */
/* Autor: Thomas Rakow, FH Düsseldorf */

/* Verwendete Namenskonvention:
   
*/

/* Korrekturen der Fehler wie im folgenden Beispiel direkt unterhalb der 
   geänderten Stelle vermerken: */
-- FEHLER: Integritätsbedingung für den Primärschlüssel wurde verletzt.
-- ABHILFE: Korrekter Wert für Primärschlüssel verwendet.

/* ======================================================== */
/* Schema aufbauen */
/* Voraussetzung: Rechte zum DROP & CREATE sind vorhanden */

  
/* Altes Schema ggf. löschen */
/* Erst Kurs dann schema droppen */

DROP TABLE Kurs;
DROP TABLE Dozent;  
DROP TABLE Student;
//    Name auf 15 Zeichen, COnstrain in line, 
CREATE TABLE Dozent (
		PersId		NUMBER (4)
			        CONSTRAINT DozentPK 
			        PRIMARY KEY, 
		Name 		VARCHAR2 (15) 
			        NOT NULL, 
		Fach 		VARCHAR2 (20), 
		Buero 		VARCHAR2 (6)

);
// , entfernt vor name not null, Reference auf Dozent(PersId) inline 
CREATE TABLE Kurs (
	KursNr NUMBER(3)  CONSTRAINT KursPK	PRIMARY KEY,
	Name VARCHAR2(40) NOT NULL,
 	Deputat NUMBER(2),
    PersId	NUMBER(4));
			REFERENCES Dozent(PersId)
);

/*
ALTER TABLE Kurs
ADD CONSTRAINT KursPersIDFK
       FOREIGN KEY PersId REFERENCES Dozent(PersId);
*/

CREATE TABLE Student (
       MatrNr		NUMBER (5)
       CONSTRAINT StudentPK PRIMARY KEY,
       Name		VARCHAR2 (20) NOT NULL,
       Semester	NUMBER (2),
       
       /*
       	CONSTRAINT StudentPK PRIMARY KEY (MatrNr)
       */
);

COMMIT;

/* ======================================================== */
/* Daten einfüen */
/* Vorhandene Daten werden nicht gelöcht oder üerschrieben!*/
INSERT INTO Dozent 
VALUES (3450, 'Doerries', 'Mathematik', 'H1.20');

INSERT INTO Dozent (PersId, Name, Fach)
VALUES (4001, 'Schwab-Trapp', 'Mediengestaltung');

INSERT INTO Dozent 
VALUES (4711, 'Dahm', 'Informatik', 'H1.18b');

INSERT INTO Dozent 
VALUES (4712, 'Rakow', 'Informatik', 'H1.18b');

INSERT INTO Dozent 
VALUES (4713, 'Geiger', 'Informatik', 'H1.20');

INSERT INTO Kurs 
VALUES (100, 'Mathematik 2', 7, 3450);

INSERT INTO Kurs 
VALUES (115, 'Mediengestaltung 2', 6, 4001);

INSERT INTO Kurs 
VALUES (106, 'Objektorientiertes Programmieren 2', 8, 4711);
/* 1 aus PersId entfernt*/

INSERT INTO Kurs 
VALUES (111, 'Datenbanksysteme 2', 7, 4712);

/* doppel id
INSERT INTO Kurs 
VALUES (115, 'Mediengestaltung 2', 6, 4001);
*(
INSERT INTO Kurs 
VALUES (104, 'FMA', 5, 4713);

INSERT INTO Student (MatrNr, Name, Semester) 
VALUES (24002, 'Xenokrates', 18); 
 
INSERT INTO Student (MatrNr, Name, Semester) 
VALUES (25403, 'Jonas', 12);                         

INSERT INTO Student (MatrNr, Name, Semester) 
VALUES (26830, 'Aristoxenos', 8); 
 
INSERT INTO Student (MatrNr, Name, Semester) 
VALUES (27550, 'Schopenhauer', 6); 

INSERT INTO Student (MatrNr, Name, Semester) 
VALUES (28106, 'Carnap', 3); 
 /* value missing */
INSERT INTO Student (MatrNr, Name) 
VALUES (29120, 'Theophrastos'); 
 
INSERT INTO Student (MatrNr, Name, Semester) 
VALUES (29555, 'Feuerbach', 2);

-- Daten persistent in die Datenbank einfuegen
/*ROLLBACK;*/

Commit
/* ======================================================== */
/* Daten aller Relationen ausgeben*/

SELECT * FROM Dozent ORDER BY PersId;
SELECT * FROM Kurs ORDER BY KursNr;
SELECT * FROM Student ORDER BY MatrNr;


/* ======================================================== */
/* ======================================================== */
/* Aufgabe 3 */

SELECT MatrNr, Name, Semester 
FROM Student 
WHERE Semester = (Select MIN(Semester) FROM Student);

/* ================================================== */
/* Aufgabe 4 */

SELECT D.PersId, D.Name, SUM(K.Deputat)
FROM Dozent D, Kurs K
WHERE K.PersId = D.PersId
AND D.Buero LIKE 'H%'
GROUP BY D.PersId, D.Name
HAVING SUM(K.Deputat) > 7
;

/* Auswertung in der Reihenfolge:

*/


/* ================================================== */
/* Aufgabe 5 */

/*IN*/
SELECT K.KursNr, K.Name, K.Deputat
FROM Kurs K
WHERE K.Deputat IN
(
SELECT K.Deputat
FROM Dozent D, Kurs K
WHERE K.PersId = D.PersId
AND D.Name = 'Rakow'
)
ORDER BY K.Name;

/*Join*/
SELECT K1.KursNr, K1.Name, K1.Deputat
FROM Kurs K1, Kurs K2, Dozent D
WHERE D.Name = 'Rakow'
AND K2.PersId = D.PersId
AND K1.Deputat = K2.Deputat
ORDER BY K1.Name;

/* ================================================== */
/* Aufgabe 6 */

/* Subselect */
SELECT KURSNR, NAME, DEPUTAT
FROM Kurs
WHERE DEPUTAT >= (SELECT AVG(DEPUTAT ) FROM KURS)
ORDER BY DEPUTAT DESC;

/* Join */

SELECT K1.KURSNR, K1.NAME, K1.DEPUTAT
FROM Kurs K1, Kurs K2
HAVING K1.DEPUTAT >= AVG(K2.DEPUTAT)
GROUP BY K1.KURSNR, K1.NAME, K1.DEPUTAT
ORDER BY K1.DEPUTAT DESC;

/* Ende des Skripts */
