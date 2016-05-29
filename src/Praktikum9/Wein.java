package Praktikum9;

/**
 *Diese Klasse ist ein allgemeiner Wein der seinen Alk. wert kennt und ihn über  toString() mit seeiner klassen bezeicnnung ausgibt. 
 * 
 * @author Falk Schmitz
 */
public class Wein {

	//IV
	double alk;
	
	//IM
	/**
	 * Beim erstellen muss der Alk wert angegeben werden.
	 * @param alk
	 */
	public Wein(double alk){
		this.alk=alk;
	}
	/**
	 * gibt klassen name und alk. wert zurück
	 */
	public String toString(){
		return getClass().getName() + " Alk.Wert: "+ alk; 
	}
}
