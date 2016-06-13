package Praktikum11;

import java.io.FilterWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.Map;


/**
 * DIese KLasse ist ein FilterWriter der eine Map zu Strings filtert.
 * 
 * @author falks
 * 
 */
public class MapWriter extends FilterWriter {
	/**
	 * construktor = super konstruktor
	 * 
	 * @param arg0
	 *            nächster Writer
	 */
	public MapWriter(Writer arg0) {
		super(arg0);
	}
	/**
	 * Wandelt map einträge in strings um und schreibt sie in den nächsten writer
	 * @param m
	 */
	public void write(Map<String,Integer> m){
		try{
			for(Map.Entry<String, Integer> entry : m.entrySet()){
			out.write((entry.getKey().toString()+";"+entry.getValue().toString()+"\r\n"));
			System.out.println((entry.getKey().toString()+";"+entry.getValue().toString()+"\r\n"));
			}
		}
		catch(IOException e){
			System.out.println("Fehler beim Datei Schreiben"+e);
		}
		catch(NullPointerException e){
			System.out.println("iwas sit Null"+e);
		}
		
	}
}
