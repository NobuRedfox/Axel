# VierGewinntOOP

Ein Vier-Gewinnt-Spiel in Java für die Konsole.

Das Projekt ist objektorientiert aufgebaut und trennt Spiellogik, Spielfeld und Spieler in eigene Klassen.

---

## Funktionen

- Zwei-Spieler-Modus
- 6x7-Spielfeld
- Konsolenausgabe mit Spielfeldrahmen
- Eingabe einer Spalte von 1 bis 7
- Prüfung auf ungültige Eingaben
- Prüfung, ob eine Spalte voll ist
- Spielstein fällt automatisch in die unterste freie Zeile
- Spielerwechsel zwischen `X` und `O`
- Gewinnprüfung horizontal
- Gewinnprüfung vertikal
- Gewinnprüfung diagonal nach rechts unten
- Gewinnprüfung diagonal nach rechts oben
- Erkennung von Unentschieden
- Möglichkeit, nach Spielende erneut zu spielen

---

## Projektaufbau

```text
VierGewinntOOP/
├── README.md
└── src/
    ├── Main.java
    ├── Game.java
    ├── Board.java
    └── Player.java
```

---

## Klassen

### Main

Startet das Spiel und fragt nach Spielende, ob erneut gespielt werden soll.

### Game

Steuert den Spielablauf.

Dazu gehören:

- Spieler anlegen
- aktueller Spieler
- Eingabe der Spalte
- Spielerwechsel
- Gewinnprüfung
- Unentschieden prüfen

### Board

Verwaltet das Spielfeld.

Dazu gehören:

- Spielfeld erstellen
- Spielfeld ausgeben
- Spielstein in eine Spalte werfen
- prüfen, ob das Spielfeld voll ist
- Gewinnprüfung in alle Richtungen

### Player

Speichert die Daten eines Spielers.

Dazu gehören:

- Name
- Symbol (`X` oder `O`)

---

## Gelernte Inhalte

In diesem Projekt wurden vor allem diese Java- und OOP-Grundlagen geübt:

- Klassen und Objekte
- Attribute
- Methoden
- Konstruktoren
- Sichtbarkeit mit `private` und `public`
- Getter-Methoden
- zweidimensionale Arrays
- Schleifen
- Bedingungen
- Eingabeprüfung mit `Scanner`
- Aufteilung eines Programms in mehrere Klassen

---

## Starten

Das Projekt wurde mit IntelliJ IDEA erstellt.

Zum Starten die Datei `src/Main.java` öffnen und die `main`-Methode ausführen.

---

## Hinweis

Dieses Projekt ist eine objektorientierte Version eines Konsolenspiels.

Im Vergleich zu einfachen Ein-Datei-Projekten ist der Code hier bereits besser aufgeteilt: `Board`, `Game` und `Player` haben jeweils eigene Aufgaben.
