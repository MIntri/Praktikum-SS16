package Praktikum11;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.TreeMap;

public class Init {

	public static void main(String[] args) {
				
		TreeMap<String, Integer> telefonbuch = new TreeMap<>();
		
		telefonbuch.put("foo", 1337);
		telefonbuch.put("bar",1337);
		
		File output = new File("output.txt");
		MapWriter mw = null;
		BufferedWriter bw =null;
		FileWriter fw = null;
		
		try {
			fw = new FileWriter(output);
			bw =  new BufferedWriter(fw);
			mw = new MapWriter(bw);
		} catch (IOException e) {
			e.printStackTrace();
		}
				
		try {
			mw.write(telefonbuch);
			mw.flush();
			mw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
	}
	

}
