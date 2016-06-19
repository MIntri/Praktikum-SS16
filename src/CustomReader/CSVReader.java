package CustomReader;

import java.io.FilterReader;
import java.io.IOException;
import java.io.Reader;
/**
 * Dieser Reader liest die Wörter einer CSV Datei aus. 
 * Mit jedem read Aufruf, wird das nächste wort zurückgegeben.
 * Klasse implementiert nur die read(char[] cbuf, int off, int len) Funktion 
 * und kann daher nur richtig mit diese verwendet werden.
 * 
 * @author Falk Schmitz
 *
 */
public class CSVReader extends FilterReader {
	//Index Zeiger
	int index =1;
	//eingelesener input von in
	char[] input = new char[1000];
	//anzahl der Zeichen beim read von in und somit auch Dateinende.
	int inputSize = -1;
	
	/**
	 * Erstellt den Reader mit vorrangehendem Reader
	 * @param in
	 */
	public CSVReader(Reader in) {
		super(in);
	}
	
	
	/**
	 * Liest das nächste Wort bis zum nächsten Trenn-Komma aus dem input Array.
	 * CSV-Datei darf maximal 1000 Zeichen lang sein.
	 * 
	 * @param cbuf Zielbuffer
	 * @param off Startindex
	 * @param len Anzahl der Zeichen die gelesen werden sollen
	 * @return Anzahl der gelesenen Zeichen oder -1 wenn das Ende erreicht ist.
	 */
	@Override
	public int read(char[] cbuf, int off, int len) throws IOException{
		//Da der BufferedReader sich leert nach dem auslesen, darf ddieser nur bei der ersten Anwendung ausgelesen werden
		if(inputSize<0)
			inputSize =in.read(input, 0, 1000);
		//stelle merken bei der der Reader fürs näcshte Wort anfängt
		int oldIndex = index;
		//Zähler fürs schreiben in den Buffer
		int i=1;
		//Solange das nächste Zeichen in den Buffer schreiben bis ein ';' oder ',' und solange der Reader INdex nicht das Ende erreicht hat
		//da eine CSV nicht mit Trennzeichen enden muss
		while((input[index]!=';'&&input[index]!=',')&&index<inputSize){
			cbuf[off+i]=input[index];
			index++;
			i++;
		}
		//Wenn das nächste Zeichen ein Trennzeichen ist, wird es übersprungen zur vorbereitung für das nächste Wort
		//Somit wird auch der Reader nicht beendet wenn zwei Trennzeichen nacheinander in der Datei stehen.
		if(input[index]==';'||input[index]==',')index++;
		
		//Berechnet wie viele Zeichen das Wort hatte. Wenn es kein nächstes Wort gibt, wird -1 berechent
		int size=index - oldIndex-1;
		
		return size;
	}
	/**
	 * setzt die Reader zum ersten Wort zurück
	 */
	@Override
	public void reset() throws IOException {
		index=1;
	}
}
