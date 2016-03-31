package Praktikum1;

/**
 * <b>Praktikum Objektorientierte Programmierung 2<br/>
 * Teil 1 - Debugging</b><br/>
 * <br/>
 * Einfache Prozeduren mit 10 Fehlern aus Praktikum OOP1 Teil 7
 * 
 * @author Jens Lambert
 * @version 1.1
 * debugged by Falk Schmitz tztz
 */
public class OOP2_Debugging {
	private boolean hatVersatz = false;

	/**
	 * Durchnummerierung von Spalten als Überschrift mit Trennstrich.
	 * 
	 * @param maxSpalte
	 *            höchste Spaltenzahl, bis zu der Nummeriert wird
	 * @param hatVersatz
	 *            wenn true wird eine Spalte (\t) Versatz eingefügt
	 */
	public static void tabellenUeberschrift(int maxSpalte, boolean hatVersatz) {
		// verschiebt die Nummerierung um einen Tab nach rechts
		if (hatVersatz)
			System.out.print("\t");
		// Spaltennummerierung
		for (int i = 1; i <= maxSpalte; i++) {
			System.out.print(i + "\t");
		}
		// Zeilenumbruch
		System.out.println();
		// verlängert den Trennstrich bei Versatz
		if (hatVersatz) {
			System.out.print("-------");
			for (int i = 1; i <= maxSpalte; i++) {
				System.out.print("--------");
			}
		} else {
			// Spaltentrennstrich
			for (int i = 1; i <= maxSpalte; i++) {
				System.out.print("----");
			}
		}
		// Zeilenumbruch
		System.out.println();
	}

	/**
	 * Gibt das Einmaleins als Tabelle mit Überschrift aus
	 * 
	 * @param maxZahl
	 *            höchste Zahl, incl. der das Einmaleins ausgegeben wird
	 */
	public static void einMalEins(int maxZahl) {
		// Ausgabe der Überschrift
		tabellenUeberschrift(maxZahl, true);
		// Die Ausgabe erfolge zeilenweise.
		// Daher beginnen wir außen mit der
		// Schleife über die Ausgabe aller Zeilen
		for (int zeile = 1; zeile <= maxZahl; zeile++) {
			// Zeilentitel ausgeben:
			System.out.print(zeile + "\t");
			// In jeder Zeile werden nun alle Spalten ausgegeben
			for (int spalte = 1; spalte <= maxZahl; spalte++) {
				// An jedem Kreuzungspunkt zeile/spalte wird das Produkt
				// ausgegeben; \t ist ein Tab-Schritt
				// Hinweis: .print() gibt keinen Zeilenumbruch aus
				System.out.print((spalte * zeile) + " \t");
			} // Ende Schleife über alle Spalten
				// Zeilenumbruch am Ende aller Spalten jeder Zeile
			System.out.println(" ");
		} // Ende Schleife über alle Zeilen
	}

	/**
	 * @param args
	 *            es sind keine Kommandozeilen-Parameter definiert
	 */
	public static void main(String[] args) {
		System.out.println("Aufgabe 1a)");
		tabellenUeberschrift(1, false);
		tabellenUeberschrift(5, true);
		// einMalEins(10);
		// tabellenUeberschrift("10",false);
		// -> Fehler: The method tabellenUeberschrift(int, boolean) in the type
		// OOP1_Teil07_A01 is not applicable for the arguments (String, boolean)
		System.out.println("\nAufgabe 1b)");
		einMalEins(10);
	}
}
