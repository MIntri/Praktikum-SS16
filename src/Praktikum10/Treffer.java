package Praktikum10;

public class Treffer implements Comparable<Treffer>{

	//iv
	private Integer punktzahl;
	
	public Treffer(int punktzahl){
		if(punktzahl==50||punktzahl==100||punktzahl==200||punktzahl==500||punktzahl==750||punktzahl==100)
			this.punktzahl = punktzahl;
		else
			this.punktzahl=null;
		
	}
	
	public boolean istGuterTreffer(){
		return punktzahl>100;
	}

	@Override
	public int compareTo(Treffer o) {
		return this.punktzahl-o.punktzahl;
		
	}
	
	@Override
	public String toString() {
		return punktzahl.toString();
	}

	
	public Integer getPunktzahl() {
		return punktzahl;
	}	
}
