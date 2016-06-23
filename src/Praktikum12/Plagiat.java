package Praktikum12;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.Buffer;
import java.util.HashMap;
import java.util.TreeSet;

import com.sun.xml.internal.ws.client.ClientSchemaValidationTube;

import CustomReader.*;


/**
 * 
 * Dieses Programm analysiert und codiert eine übergebene Textfile. Optional kann eine Liste von unwichtigen Wörter mitgegeben werden.
 * Wenn keine Liste übergeben wird, benutzt das Programm eine default Liste.
 * Alle Paramter müssen vollständige Systempfade sein.
 * @param
 * 		-i=textfile.txt
 * 		[-f=unwichtige_wörter.csv]
 * @author Falk Schmitz
 *
 */
public class Plagiat {
	
	//gefilterter und gezählter Text
	HashMap<String, Integer> filteredText;
	//Unwichtige Worte
	TreeSet<String> unimportantWords = new TreeSet<>();
	
	/**
	 * Das Programm kann nur mit den Startparamtern gestartet werden.
	 * @param args Startparamtern
	 * @throws ParamException
	 */
	public Plagiat(String[] args) throws ParamException{
		String files[] = inFilename(args);
		readUnimportantWords(files[1]);
		
		
	};
	/**
	 * Überprüft die Paramter die beim Programmstart übergeben wurden nach gewünschter Paramtersyntax und Dateiendungen
	 * @param args Programmstart Parameter
	 * @return String[] URL der Datein [0] URL zu checkende Textdatei, [1] RL unwichtige Wörter CSV-Datei
	 * @throws ParamException
	 */
	private String[] inFilename(String[] args)throws ParamException{
		//Anzahl der Paramter checken
		if(args.length<0 || args.length>2) throw new ParamException("Please check your number of parameters");
		
		//URL der Datein 
		// files[0] = URL zu checkende Textdatei
		// files[1] = URL unwichtige Wörter CSV-Datei
		String[] files = new String[2];
		
		//testen ob erste Paramter -i ist 
		if((args[0].substring(0,2).equals("-i")))
			//test ob es eine txt Datei ist
			if((args[0].substring(args[0].length()-4, args[0].length())).equals(".txt"))
				files[0]=args[0].substring(3,args[0].length());
			else
				throw new ParamException("First file must be a .txt file");
		else
			throw new ParamException("First Paramter ist Wrong");
		
		//test ob es der zweite Parameter benutzt wurde, da er optional ist
		if(args.length==1)
			//wenn der zweite Paramter nicht genutzt wurde, wird die standart lsite an unnützen Wörtern benutzt
			files[1]="./res/unimportant_words.csv";
		else{
		//testen ob zweiter Paramter -f ist 
				if((args[1].substring(0,2).equals("-f")))
					//test ob es eine csv Datei ist
					if((args[1].substring(args[1].length()-4, args[1].length())).equals(".csv"))
						files[1]=args[1].substring(3,args[1].length());
					else
						throw new ParamException("Second file must be a .csv file");
				else
					throw new ParamException("Second Paramter ist Wrong");
		}
		
		return files;
	}
	
	/**
	 * Liest die unwichtigen Wörter aus der CSV Datei ein und speichert sie in unimportant Words ab
	 * @param location der Ort der CSV Datei
	 */
	private void readUnimportantWords(String location){
		//Deklaration der Reader
		FileReader fr=null;
		BufferedReader br= null;
		CSVReader csvr = null;
		//Buffer in dem der letzte Reader schreibt
		char[] nextWord = new char[20];
		
		try {
			fr = new FileReader(location);
			br = new BufferedReader(fr);
			csvr = new CSVReader(br);
			//speichert die größe des nächsten Worts/Zählvariable
			int i;
			//Alle Wörter aus der Datei lesen
			do{
				//nächstes Wort vom CSV Reader
				i = csvr.read(nextWord, 0, 20);
				//baut das Chararray zu nem String zusammen
				StringBuilder sb = new StringBuilder();
				for(int j=1;j<=i;j++){
					sb.append(nextWord[j]);
				}
				//Wort dem Set hinzufügen
/**Dev-Note: Hier ansetzen für Sorting Übung*/
				unimportantWords.add(sb.toString());
			}while(i>0);
		
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		finally{
			try {
				csvr.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	private void readFilteredText(String location){
	//	Deklaration der benötigten Reader
		FileReader fr =null;
		BufferedReader br = null;
		AllLowerCaseReader alr = null;
		WordReader wr = null;
		ImportantWordsReader iwr = null;
		
		//Buffer in dem der letzte Reader schreibt
		char[] nextWord = new char[20];
		
		try {
			fr = new FileReader(location);
			br = new BufferedReader(fr);
			alr = new AllLowerCaseReader(br);
			wr = new WordReader(alr);
			iwr = new ImportantWordsReader(wr);
			//speichert die größe des nächsten Worts/Zählvariable
			int i;
			//Alle Wörter aus der Datei lesen
			do{
				//nächstes Wort vom CSV Reader
				i = iwr.read(nextWord, 0, 20);
				//baut das Chararray zu nem String zusammen
				StringBuilder sb = new StringBuilder();
				for(int j=1;j<=i;j++){
					sb.append(nextWord[j]);
				}
				//Wort der Map hinzufügen
				unimportantWords.add(sb.toString());
			}while(i>0);
		
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		finally{
			try {
				iwr.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
