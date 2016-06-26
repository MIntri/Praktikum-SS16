package CustomReader;

import java.io.FilterReader;
import java.io.IOException;
import java.io.Reader;
/**
 * Erweiterter FilterReader. Liest die  ganze Wörter einer CSV Datei aus. 
 * Mit jedem read Aufruf, wird das nächste wort zurückgegeben.
 * Klasse implementiert nur die read(char[] cbuf, int off, int len) Funktion 
 * CSV-Datei darf maximal 1000 Zeichen lang sein.
 * 
 * @author Falk Schmitz
 *
 */
public class CSVReader extends FilterReader {
	//Index Zeiger
	int index =1;
	//eingelesener input von in
	char[] input = new char[1000];
	//anzahl der Zeichen beim read von in.
	int inputSize = -1;
	
	/**
	 * Erstellt den Reader mit vorrangehendem Reader
	 * @param in vorrangehender Reader
	 * @throws IOException 
	 */
	public CSVReader(Reader in) throws IOException {
		super(in);
		inputSize =in.read(input, 0, 1000);
	}
	
	
	/**
	 * Liest das nächste Wort bis zum nächsten Trenn-Komma aus dem input Array.
	 * 
	 * @param cbuf Zielbuffer
	 * @param off Startindex
	 * @param len Anzahl der Zeichen die gelesen werden sollen
	 * @return Anzahl der gelesenen Zeichen oder -1 wenn das Ende erreicht ist.
	 */
	@Override
	public int read(char[] cbuf, int off, int len) throws IOException{
		
		//stelle merken bei der der Reader fürs näcshte Wort anfängt
		int oldIndex = index;
		//Zähler fürs schreiben in den Buffer
		int i=1;
		//Solange das nächste Zeichen in den Buffer schreiben bis ein ';' oder ',' und solange der Reader INdex nicht das Ende erreicht hat
		//da eine CSV nicht mit Trennzeichen enden muss
		while((input[index]!=';'&&input[index]!=',')&&index<inputSize&&(index - oldIndex-1)<=len){
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
