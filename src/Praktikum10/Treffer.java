package Praktikum10;

/**
 * Diese klasse stellt einen Treffer mit punktzahl dar.
 * @author falks
 *
 */
public class Treffer implements Comparable<Treffer>{

	//iv
	private Integer punktzahl;
	/**
	 * Treffer nur mit param = 50,100,200,500,750,1000
	 * @param punktzahl
	 */
	public Treffer(int punktzahl){
		if(punktzahl==50||punktzahl==100||punktzahl==200||punktzahl==500||punktzahl==750||punktzahl==1000)
			this.punktzahl = punktzahl;
		else
			this.punktzahl=null;
		
	}
	/**
	 * bewertet die punktzashl 
	 * @return boolean true wenn punktzahl>100
	 */
	public boolean istGuterTreffer(){
		return punktzahl>100;
	}
	/**
	 * normale compare implementations
	 */
	@Override
	public int compareTo(Treffer o) {
		return this.punktzahl-o.punktzahl;
		
	}
	/**
	 * gibt punktzahl zur�ck
	 */
	@Override
	public String toString() {
		return punktzahl.toString();
	}

	/**
	 * gibt punktzahl zur�ck
	 * @return
	 */
	public Integer getPunktzahl() {
		return punktzahl;
	}	
}
