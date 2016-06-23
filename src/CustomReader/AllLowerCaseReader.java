package CustomReader;

import java.io.FilterReader;
import java.io.IOException;
import java.io.Reader;
/**
 * 
 * @author Falk Schmitz
 * Erweiterter FilterReader. Transformiert Großbuchstaben zu Kleinbuchstaben und Sonderzeichen zu
 * Leerzeichen. Andere Zeichen werden unverändert zurückgegeben. Implmentiert nur die read() Funktion
 */
public class AllLowerCaseReader extends FilterReader {

	/**
	 * Erstellt den Reader mit vorrangehendem Reader
	 * @param in vorrangehender Reader
	 */
	public AllLowerCaseReader(Reader arg0) {
		super(arg0);
	}
	/**
	 * Liest den nächsten Buchstaben aus dem vorrangehendem, Reader aus, transformiert ihn, wenn nötigt
	 * und gibt den buchstaben zurück
	 * 
	 *  @return int buchstabenwert
	 */
	@Override
	public int read() throws IOException{
		int c = in.read();
		//Großbruchstaben in kleinbuchstaben wandeln und zurückgeben;
		if(c>=65&&c<=90){ c+=32; return c;}
		//Zahlen einfach zurückgeben
		if(c>=48&&c<=57) return c;
		//kleinbuchstaben zurückgeben
		if(c>=97&&c<=122) return c;
		//-1 des filesendes zurückgeben
		if(c==-1) return c;
		//in allen anderen Fällen ein Leerzeichen zurückgeben
		return 32;
	}

}
