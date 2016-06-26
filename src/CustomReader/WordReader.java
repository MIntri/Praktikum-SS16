package CustomReader;

import java.io.FilterReader;
import java.io.IOException;
import java.io.Reader;

public class WordReader extends FilterReader {
	// Index Zeiger
	int index = 1;
	// eingelesener input von in
	char[] input = new char[1000000];
	// anzahl der Zeichen beim read von in.
	int inputSize = 0;

	public WordReader(Reader in) throws IOException {
		super(in);
		int i;
		// alle Buchstaben auslesen
		do {
			i = in.read();
			inputSize++;
			if (i != -1)
				input[inputSize] = (char) i;
		} while (i != -1);
	}

	/**
	 * Liest die nächsten Buchstaben bis zu einem Leerzeichen aus dem input
	 * Array.
	 * 
	 * @param cbuf
	 *            Zielbuffer
	 * @param off
	 *            Startindex
	 * @param len
	 *            Anzahl der Zeichen die gelesen werden sollen
	 * @return Anzahl der gelesenen Zeichen oder -1 wenn das Ende erreicht ist.
	 */
	@Override
	public int read(char[] cbuf, int off, int len) throws IOException {
				
		// Zähler fürs schreiben in den Buffer
		int i = 1;
		// Solange das nächste Zeichen in den Buffer schreiben bis ein Leerzeichen erreicht wurde und solange der Reader Index nicht das Ende erreicht hat
		//und die schreibindex nicht über len hinaus ist
		while (input[index] != ' '	&& index < inputSize && i <= len) {
			cbuf[off + i] = input[index];
			index++;
			i++;
		}
						
		// Wenn das nächste Zeichen ein Trennzeichen ist, wird es übersprungen
		// zur vorbereitung für das nächste Wort
		// Somit wird auch der Reader nicht beendet wenn zwei Trennzeichen
		// nacheinander in der Datei stehen.
		while(input[index] == ' '){index++;}
		
		//bei input ende
		if(index==inputSize)return -1;
		
		//gibt zurück wie lang das gelesene Wort ist
		return i-1;

	}
}
