package Praktikum10;

import java.util.Calendar;
import java.util.TreeMap;

public class Init {

	public static void main(String[] args) {
		Spiel spiel = new Spiel();
		spiel.testTreffer();
		System.out.println(spiel.trefferText());
		System.out.println(spiel.score());
		
		
		TreeMap<String, Integer> telefonbuch = new TreeMap<>();
		
		telefonbuch.put("foo", 1337);
		telefonbuch.put("bar",1337);
		
		System.out.println(telefonbuch.get("foo"));
		System.out.println(telefonbuch.get("foobar"));
		
		try{
		System.out.println(telefonbuch.get(null));
		System.out.println(telefonbuch.get(1223));
		}
		catch(NullPointerException e) {
			System.out.println("nullpointer yo "+e);
		}
		catch(ClassCastException e){
			System.out.println("We dont take any keys in this hostel"+ e);
		}
		catch(Exception e){
			System.out.println("random shit was shit "+e);
		}
		finally {
			System.out.println("*telefobuch drops mic*");
		}
		
		Calendar cal = Calendar.getInstance();
		Calendar foo = cal.getInstance();
		foo.add(Calendar.DAY_OF_MONTH, 3);
		
		int delta = (int) (foo.getTimeInMillis()-cal.getTimeInMillis());
		
		System.out.println(delta/1000/60/60);
		
	}
	

}
