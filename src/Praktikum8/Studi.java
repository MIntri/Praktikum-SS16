package Praktikum8;



public class Studi implements Comparable<Studi> {

	// IV
	String name;
	int matNr;

	public Studi(String name, int matNr) {
		this.name = name;
		this.matNr = matNr;
	}

	public int compareTo(Studi b) {
		return this.matNr - b.matNr;
	}
	
	public void pruefen(String fachname){
		System.out.println(this+" "+fachname);
	}

	@Override
	public String toString() {
		return "MatNr: " + matNr +" Name: "+ name;
	}

}
