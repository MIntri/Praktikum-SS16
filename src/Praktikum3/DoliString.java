package Praktikum3;

import java.util.Iterator;



/**
 * Mit dieser Klasse kann eine Linked Liste erstellt und verwaltet werden
 * 
 * @author Mintri
 * 
 */
public class DoliString implements Iterable{

	// IV
	/**
	 * Erster Knoten der Liste
	 */
	private Node first;
	/*
	 * letzer Knoten
	 */
	private Node last;

	// IM
	/**
	 * Constructer für leere Liste
	 */
	public DoliString() {
		first = null;
		last = null;
	}

	/**
	 * Diese Methode fügt einen Knoten an den Anfang der Liste hinzu
	 * 
	 * @param data
	 *            Daten die gespeichert werden sollen.
	 */
	public void add(String data) {
		if(first == null)
			last = first = new Node(data);
		else
			first = first.add(data);
		//Node newNode = new Node(data);
		//newNode.append(first);
		
	}

	/**
	 * Diese Methode fügt einen Knoten ans Ende der Liste hinzu
	 * 
	 * @param data
	 */
	public void append(String data) {
		if (first == null) {
			last = first = new Node(data);
		} else {
			last = first.append(data);
		}
	}
	/**
	 *  Listet alle Einträge auf.
	 */
	public String toString(){
		// output buffer
		StringBuilder output = new StringBuilder();
		Iterator it = iterator();
		while(it.hasNext()){
			output.append(it.next()+", ");
		}
		return output.toString();
	}
	/**
	 * Prüft ob ein Word in der Liste enthalten ist
	 * @param searchWord das zu suchende Wort
	 * @return true, wenn WOrt vorhanden / false, wenn wort nicht vorhanden ist.
	 */
	public boolean searchString(String searchWord){
		if(first == null){
			return false;
		} else {
			return first.searchString(searchWord);
		}
	}
	/**
	 * Löscht den Eintrag auf den das Suchwort zutrifft.
	 * @param deleteWord der EIntrag der gelöscht werden soll
	 */
	public void deleteNode(String deleteWord){
		if(first != null){
			first.deleteNode(deleteWord);
		}
		
	}
	
	public void insertInOrder(String data){
		if (first != null){
			first.insertInOrder(data);
		}
		else{
			last = first = new Node(data);
		}
	}
	
	public Iterator iterator(){
		return new DoliIterator(first);
	}
	
	

	
	/**
	 * Diese Klasse repräsentiert einen Knoten
	 * 
	 * @author Mintri
	 * 
	 */
	private class Node {

		// IV
		/**
		 * Daten des Knotens
		 */
		private String data;
		/**
		 * nächster Knoten der Liste
		 */
		private Node next;
		
		/**
		 * vorheriger Knoten
		 */
		private Node prev;

		/**
		 * Constructor für einen Knoten mit Dataen
		 */
		private Node(String data) {
			writeData(data);
			next = null;
			prev=null;
		}

		/**
		 * Fügt vorne an der Liste einen Knoten an
		 * @param data INhalt
		 * @return new erset Node
		 */
		private Node add(String data){
			Node newFirst = new Node(data);
			newFirst.next = this;
			this.prev = newFirst;
			return newFirst;
		}
		/**
		 * Überschreibt die Daten des Knotens
		 * 
		 * @param Data
		 *            Daten die gespeichert wereden sollen
		 */
		private void writeData(String data) {
			this.data = data;
		}

		/**
		 * Hängt einen Knoten ans Ende der Liste an
		 * 
		 * @param nextNode
		 *            nächster Knoten
		 */
		private Node append(String data) {
			if (next == null) {
				next = new Node(data);
				next.prev = this;
				return next;
			} else {
				this.next.append(data);
				return new Node(data);
			}

		}
		/**
		 * fügt eine Knoten nach diesen KNoten ein
		 * @param insertNode KNoten der danach kommen soll
		 */
		private void insertAfter(Node insertNode) {
			this.next = insertNode;
			
		}

		/**
		 * Gibt den gespeicherten Wert des Knotens wieder
		 * @return gepseicherte Daten
		 */
		private String getData(){
			return data;
		}
		/**
		 * Gibt den Knoten zurück, der nach diesem Knoten kommt		 
		 * @return nächster Knoten
		 */
		private Node getNextNode(){
			return next;
		}
		
		private void insertInOrder(String data){
			//true wenn neuer String kleiner ist als der wert der this node
			if(this.data.compareTo(data)>= 1){
				//neuer knoten der vor diesem eingefügt wird
				Node newNode = new Node (data);
				newNode.next = this;
				newNode.prev = this.prev;
				//prüfung wenn die neue node vor die erste node muss
				if(this.prev != null){
					this.prev.next = newNode;
				}
				else{
					first = newNode;
				}
				this.prev = newNode;
				//wenn der neue string größer ist gehts weiter
			}else{
				//prüfung wenn die neue Node als neue letze rein muss
				//daher gehts nur weiter wenn die nächste node nicht leer ist.
				if(next != null){
					next.insertInOrder(data);
				}
				//wenn die neue Node als neue letzte rein muss
				else{
					Node newNode = new Node (data);
					last = this.next = newNode;
					newNode.prev = this;
					
				}
			}
		}
		
		private StringBuilder buildString(){
			StringBuilder output = new StringBuilder();
			output.append(data);
			Node next = this.next;
			while(next!=null){
				output.append(", "+next.data);
				next=next.getNextNode();
			}
			return output;
		}
		
		private boolean searchString(String searchWord){
			
			Node next =this;
			//schleife geht solange weiter wie es einen nächsten KNoten gibt. WEnn der Nächste KNoten nicht da ist, also null, wird die schleife beendet
			while(next != null){
				if(next.getData().equals(searchWord)) return true;
				next = next.getNextNode();
			}
			return false;
			
		}
		
		private void deleteNode(String deleteWord){
			
			Node deleteNode=this;
			Node previousNode=null;
			
			boolean found = false;
			
			while(!found){
				if(deleteNode.data.equals(deleteWord)){
					found = true;
				} else {
					previousNode = deleteNode;
					deleteNode = deleteNode.next;
					
				}
			}
			if(previousNode==null){
				first = deleteNode.getNextNode();
			} else {
				previousNode.insertAfter(deleteNode.getNextNode());		
				}
		}
	}
	
	class DoliIterator implements Iterator{

		Node current;
		
		public DoliIterator(Node f){
			current = f;
		}
		/**
		 * prüft ob es einen aktuellen knoten gibt
		 */
		public boolean hasNext() {
			return current!=null;
		}
		/**
		 * gibt den wert des aktuellen knotens zurück und dnan zum nächsten KNoten
		 */
		public Object next() {
			if(hasNext()){
				Node temp=current;
				current=current.getNextNode();
				return temp.getData();
			}
			return null;
		}
		
	}
	
	public static void main(String[] args) {
		
		DoliString list = new DoliString();
		
		System.out.println("Test append: start");
		for (int i = 0; i < 10; i++) {
			list.append("hallo" + i);
		}

		System.out.println("Test append: done");
		System.out.println();
		System.out.println("Test add: start");
		for (int i = 0; i < 10; i++) {
			list.add("bye" + i);
		}
		System.out.println("Test add: done");
		
		System.out.println();
		System.out.println("Listeneinträge: "+list);
		System.out.println();
		System.out.println("Test suchen: start");
		System.out.println("Suchen nach vorhandenem Wort \"bye4\". Erwartetes Ergebnis: true");
		System.out.println(list.searchString("bye4"));
		System.out.println("Suchen nach nicht vorhandenem Wort \"foobar\". Erwartetes Ergebnis: false");
		System.out.println(list.searchString("foobar"));
		System.out.println();
		
		System.out.println("Test insert In Order: start");
		System.out.println("Iinital Liste");
		System.out.println(list);
		System.out.println("Einfügen von \"hallo2\"");
		list.insertInOrder("hallo91");
		System.out.println("Liste nach einfügen");
		System.out.println(list);
		/*
		System.out.println("Test löschen: start");
		System.out.println("Liste vor dem Löschen:");
		System.out.println("Listeneinträge: "+list);
		System.out.println("Eintrag \"bye2\" wird gelöscht. Erwartetes Ergebnis: ... bye3, bye1, ...");
		list.deleteNode("bye2");
		System.out.println("Listeneinträge: "+list);
		System.out.println();
		System.out.println("Test löschen: done");
		*/
		
		//Prakt 4
		Iterator it = list.iterator();
		while(it.hasNext()){
			System.out.println(it.next());
		}
				
	}

	
}
