package Praktikum9;
/**
 * DIese klasse kann verschiedene Wein sorte aufnehmen
 * @author Falk Schmitz
 *
 * @param <T>
 */
public class Flasche<T extends Wein> {
	//IV 
	T inhalt= null;;
	/**
	 * beim erstellen muss der wein der hinein soll angegeben werden
	 * @param wein
	 */
	public Flasche(T wein){
		inhalt=wein;
	}
	/**
	 * gibt toString() von inhalt wieder
	 */
	public String toString(){
		return inhalt.toString();
	}
	/**
	 * Füllt die Flasche mit dem übergebenem Wein, wenn die Flsche leer ist
	 * @param wein
	 */
	public void fuellenMit(T wein){
		if(inhalt == null)
			inhalt=wein;
		else
			System.out.println("Flasche ist voll");
	}
	/**
	 * Giesst den wein aus und leert somit die Flasche. ergo: inhalt = null
	 * @return
	 */
	public T ausgiessen(){
		T temp = inhalt;
		inhalt = null;
		return temp;
	}
	/**
	 * gibt zurück, was diese Flsche beinhaltet
	 * @return
	 */
	public String werBinIch(){
		return this.toString();
	}
	
	
}
