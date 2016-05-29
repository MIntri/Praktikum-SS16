package Praktikum9;
/**
 * Initiator klasse
 * @author Falk Schmitz
 *
 */
public class Init {

	public static void main(String args[]){
		Wein wein = new Wein(5.7);
		Weisswein weisswein = new Weisswein(4.9);
		Rotwein rotwein = new Rotwein(6.4);
		
		Flasche<Weisswein> weissweinFlasche = new Flasche<Weisswein>(weisswein);
		Flasche<Wein> weinFlasche = new Flasche<Wein>(wein);
		Flasche<Rotwein> rotweinFlasche = new Flasche<Rotwein>(rotwein);
		
		//1e
		//Flasche<Weisswein> weissweinFlasche1 = new Flasche<Weisswein>(wein); -> wein is not a Weisswein
		//Flasche<Weisswein> weissweinFlasche1 = new Flasche<Weisswein>(rotwein); rotwein is not a Weisswein	
		
//		Flasche<Rotwein> rotweinFlasche1 = new Flasche<Rotwein>(wein);	wein is not a Rotwein
//		Flasche<Rotwein> rotweinFlasche1 = new Flasche<Rotwein>(weisswein); weisswein is not a Rotwein
		
		Flasche<Wein> weinFlasche1 = new Flasche<Wein>(weisswein); // weisswein is a wein
		Flasche<Wein> weinFlasche2 = new Flasche<Wein>(rotwein); // rotwein is a wein
		
		//2a
		Flasche<Wein> wFlasche;
		Flasche<Weisswein> wwFlasche;
		Flasche<Rotwein> rwFlasche;
		
		wFlasche = weinFlasche; //  ok weil aktueller typ paramter  gleich
	//	wFlasche = weissweinFlasche; // nicht ok weil aktueller typ paramter ungleich
	//	wFlasche = rotweinFlasche; // nicht ok weil aktueller typ paramter ungleich
		
	//	wwFlasche = weinFlasche;  nicht ok weil aktueller typ paramter ungleich
		wwFlasche = weissweinFlasche; //  ok weil aktueller typ paramter gleich
	//	wwFlasche = rotweinFlasche; //nicht ok weil aktueller typ paramter ungleich
		
	//	rwFlasche = weinFlasche; //nicht ok weil aktueller typ paramter ungleich
	//	rwFlasche = weissweinFlasche; //nicht ok weil aktueller typ paramter ungleich
		rwFlasche = rotweinFlasche; //   ok weil aktueller typ paramter gleich
		
		System.out.println(wFlasche.toString());
		wFlasche.ausgiessen();
		wFlasche.fuellenMit(rotwein);
		System.out.println(wFlasche.toString());
		wFlasche.ausgiessen();
		wFlasche.fuellenMit(weisswein);
		System.out.println(wFlasche.toString());
		wFlasche.ausgiessen();
		wFlasche.fuellenMit(wein);
		// alles kein Problem da Rotwein/Weisswein is a Wein
		
		System.out.println();
		System.out.println(wwFlasche.toString());
		//wwFlasche.ausgiessen();
		//wwFlasche.fuellenMit(wein); nope da Wein kein Weisswein ist
//		wwFlasche.fuellenMit(rotwein);	nope da Rotwein kein Weissweinb ist
		
		System.out.println();
		System.out.println(rwFlasche.toString());
	//	rwFlasche.ausgiessen();
//		rwFlasche.fuellenMit(wein); nope da wein kein rotwein
//		rwFlasche.fuellenMit(weisswein); nope da weiswein kein rotwein
		
		Flasche[] RegalRaw = new Flasche[10];
		Flasche<?>[] RegalWild = new Flasche<?>[10];
		
		RegalRaw[0] = weissweinFlasche;
		RegalRaw[1] = rotweinFlasche;
		RegalRaw[2] = weinFlasche;
		
		RegalWild[0] = weissweinFlasche;
		RegalWild[1] = rotweinFlasche;
		RegalWild[2] = weinFlasche;
		
		System.out.println(RegalRaw[0].ausgiessen());
		
		}
	
}
