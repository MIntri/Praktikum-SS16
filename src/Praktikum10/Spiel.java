package Praktikum10;

import java.util.Map;
import java.util.TreeMap;

public class Spiel {

	
	//IV
	TreeMap<Treffer,Integer> schaden;
	
	public Spiel(){
		schaden = new TreeMap<>();
	}
	
	public void registrieren(Treffer t){
		if(!schaden.containsKey(t)){
			schaden.put(t, 1);
		}
		else{
			schaden.put(t, schaden.get(t)+1);	
		}
	}
	
	public void testTreffer(){
		for(int i=1;i<15;i++){
			if(i%3==0) registrieren(new Treffer(50));
			if(i%5==0) registrieren(new Treffer(100));
			if(i%7==0) registrieren(new Treffer(200));
		}
	}
	
	public String trefferText() {
		StringBuilder output = new StringBuilder();
		for(Map.Entry<Treffer, Integer> entry : schaden.entrySet()){
			output.append(entry.getKey().toString()+":"+entry.getValue().toString()+"\t");
		}
		return output.toString();
		//return schaden.toString();
	}
	
	public int score(){
		int gutSum = 0;
		int badSum = 0;
		for(Map.Entry<Treffer, Integer> entry : schaden.entrySet()){
			if(entry.getKey().istGuterTreffer()) gutSum+=(entry.getKey().getPunktzahl()*entry.getValue());
			else badSum+=(entry.getKey().getPunktzahl()*entry.getValue());
		}
		return (gutSum*11)+(badSum*3);
	}
}
