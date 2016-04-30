package Praktikum5;

import java.util.Random;

/**
 * Die Klasse stellt einen Bin�ren Baum f�r geordnete STrings da.
 * @author Falk SChmitz
 *
 */
public class StringTree {
	//IV
	//WurzelKNoten
	Node root;
	
	//IM
	public StringTree(){
		root = null;
	}
	/**
	 * F�gt einen neuen Knoten geordnet in den baum ein 
	 * @param n Nutzlast
	 */
	public void add(String n){
		if (root == null)
			root = new Node(n);
		else
			root.add(n);
		
	}
	/**
	 * pr�ft ob ein WErt im Baum gespeichert ist
	 * @param n zu pr�fender wert
	 * @return boolean true wenn ja, false wenn nicht
	 */
	public boolean contains(String n){
		if( root == null)
			return false;
		else
		 return root.contains(n);
	}
	
	//Diese KLasse stellt einen Knoten im Baum da
	private class Node{
		//n�chste Node im groesser pfad
		Node groesser;
		//n�chste Node im kleiner Pfad
		Node kleiner;
		String nutzlast;
	
		//IM
		/**
		 * COnstructor f�r node braucht nutzlast
		 * @param n
		 */
		private Node(String n){
		groesser =null;
		kleiner = null;
		nutzlast =n;
		}
		
		/**
		 * f�gt einen neuen KNoten geordnet in den Baum ein
		 * @param n NUtzlast
		 */
		private void add(String n) {
			switch (nutzlast.compareTo(n)) {
			case 0:
				break;
			case 1:
				if(kleiner == null) 
					kleiner = new Node(n);
				else
					kleiner.add(n);
				break;
			case -1:
				if(groesser == null)
					groesser = new Node(n);
				else
					groesser.add(n);
			default:
				break;
			}
		}
		
		private boolean contains(String n) {
			switch (nutzlast.compareTo(n)) {
			case 0:
				return true;
			case 1:
				if(kleiner == null) 
					return false;
				else
					kleiner.contains(n);
				break;
			case -1:
				if(groesser == null)
					return false;
				else
					groesser.contains(n);
			default:
				break;
			}
			return false;
		}
	}

	public static void main(String[] args) {
		
	}

}
