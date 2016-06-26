package Praktikum12;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import CustomReader.AllLowerCaseReader;
import CustomReader.WordReader;

/**
 * Start Klasse
 * @author Falk Schmitz
 *
 */
public class Init {

	/**
	 * Startet das Programm
	 * 
	 * @param args 
	 * 		-i=textfile.txt
	 * 		[-f=unwichtige_wörter.csv]
	 */
	public static void main(String[] args) {
		Plagiat plagiat=null;
	
		
		
		try {
			 plagiat = new Plagiat(args);
		} catch (ParamException e) {
			e.printStackTrace();
		}
		plagiat.printCheckSum();
		System.out.println(plagiat);
		System.out.println();
	}
	
	
	
//	FileReader fr =null;
//	BufferedReader br = null;
//	AllLowerCaseReader alr = null;
//	WordReader wr = null;
//	
//	try {
//		fr = new FileReader(".\\res\\test_imput.txt");
//		br = new BufferedReader(fr);
//		alr = new AllLowerCaseReader(br);
//		wr = new WordReader(alr);
//	} catch (FileNotFoundException e) {
//		// TODO Auto-generated catch block
//		e.printStackTrace();
//	} catch (IOException e) {
//		// TODO Auto-generated catch block
//		e.printStackTrace();
//	}
//	int i=0;
//	do{
//		char[] word = new char[30];
//		try {
//			i = wr.read(word, 0, 30);
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		StringBuilder sb = new StringBuilder();
//		for(int j=1;j<=i;j++){
//			sb.append(word[j]);
//		}
//		//	System.out.println(sb.toString());
//		System.out.println(new String(word).trim());
//	}while(i>0);
}
