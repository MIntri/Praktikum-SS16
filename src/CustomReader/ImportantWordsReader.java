package CustomReader;

import java.io.FilterReader;
import java.io.IOException;
import java.io.Reader;
import java.util.HashSet;
import java.util.TreeSet;

public class ImportantWordsReader extends FilterReader {
	//Menge der unwichtigen Wörter
	HashSet<String> unimportantWords;
	public ImportantWordsReader(Reader in, HashSet<String> unimportantWords) {
		super(in);
		this.unimportantWords=unimportantWords;
	}

	/**
	 * Liest das nächste Wort aus in und schreibt es in den Zielbuffer wenn es nicht in der Menge der unwichtigen Wörter steht.
	 * 
	 * @param cbuf Zielbuffer
	 * @param off Startindex
	 * @param len Anzahl der Zeichen die gelesen werden sollen
	 * @return Anzahl der gelesenen Zeichen oder -1 wenn das Ende erreicht ist.
	 */
	@Override
	public int read(char[] cbuf, int off, int len) throws IOException{
	
		//länge des empfangen Worts
		int i;
		//nächstes Wort
		char[] nextWord; 
		
		//wenn das Wort in der Liste unwichtigen Worte ist, werden die 
		//2 vorherigen schritte solange wiederholt bis der fall nicht mehr eintritt.
		do{
			//nächstes Wort lesen
			nextWord = new char[len];
			i=in.read(nextWord, 0, len);
			if(i<0)return i;		
		}
		while(unimportantWords.contains(new String(nextWord).trim()));
		//Wort in den Zielbuffer schreiben
		int j=1;
		while(j<=i&&j<=len){
			cbuf[off+j]=nextWord[j];
			j++;
		}
		// zurückgeben wie lange das wort ist, das in den Zielbuffer geschrieben wurde		
		return j-1;
	}
}
