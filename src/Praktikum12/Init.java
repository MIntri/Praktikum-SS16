package Praktikum12;
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
		Plagiat plagiat;
		try {
			 plagiat = new Plagiat(args);
		} catch (ParamException e) {
			e.printStackTrace();
		}
		
	}
}
