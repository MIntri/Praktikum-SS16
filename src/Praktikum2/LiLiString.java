package Praktikum2;

/**
 * Mit dieser Klasse kann eine Linked Liste erstellt und verwaltet werden
 * 
 * @author Mintri
 * 
 */
public class LiLiString {

	// IV
	/**
	 * Erster Knoten der Liste
	 */
	private Node first;

	// IM
	/**
	 * Constructer f�r leere Liste
	 */
	public LiLiString() {
		first = null;
	}

	/**
	 * Diese Methode f�gt einen Knoten an den Anfang der Liste hinzu
	 * 
	 * @param data
	 *            Daten die gespeichert werden sollen.
	 */
	public void add(String data) {
		Node newNode = new Node(data);
		newNode.append(first);
		first = newNode;
	}

	/**
	 * Diese Methode f�gt einen Knoten ans Ende der Liste hinzu
	 * 
	 * @param data
	 */
	public void append(String data) {
		Node newNode = new Node(data);
		if (first == null) {
			first = newNode;
		} else {
			first.append(newNode);
		}
	}
	/**
	 *  Listet alle Eintr�ge auf.
	 */
	public String toString(){
		
		// output buffer
		StringBuilder output = new StringBuilder();
		//n�chster Knoten
		Node next;
		//pr�fen ob der erste Knoten leer ist
		if(first==null){
			return output.append("Liste ist leer").toString();
		} else {
			//daten an den output buffer anh�ngen.
			output.append( first.getData());
			// n�chster KNoten abgfragen und speichern
			next = first.getNextNode();
		}
		//schleife geht solange weiter wie es einen n�chsten KNoten gibt. WEnn der N�chste KNoten nicht da ist, also null, wird die schleife beendet
		while(next!=null){
			output.append(", "+next.getData());
			next=next.getNextNode();
		}
		
		return output.toString();
	}
	/**
	 * Pr�ft ob ein Word in der Liste enthalten ist
	 * @param searchWord das zu suchende Wort
	 * @return true, wenn WOrt vorhanden / false, wenn wort nicht vorhanden ist.
	 */
	public boolean searchString(String searchWord){
		Node next;
		if(first == null){
			return false;
		} else {
			next = first;
		}
		//schleife geht solange weiter wie es einen n�chsten KNoten gibt. WEnn der N�chste KNoten nicht da ist, also null, wird die schleife beendet
		while(next != null){
			if(next.getData().equals(searchWord)) return true;
			next = next.getNextNode();
		}
		return false;
	}
	/**
	 * L�scht den Eintrag auf den das Suchwort zutrifft.
	 * @param deleteWord der EIntrag der gel�scht werden soll
	 */
	public void deleteString(String deleteWord){
		Node deleteNode=first;
		Node previousNode=null;
		
		boolean found = false;
		
		while(!found){
			if(deleteNode.getData().equals(deleteWord)){
				found = true;
			} else {
				previousNode = deleteNode;
				deleteNode = deleteNode.getNextNode();
				
			}
		}
		if(previousNode==null){
			first = deleteNode.getNextNode();
		} else {
			previousNode.insertAfter(deleteNode.getNextNode());		
			}
	}

	
	/**
	 * Diese Klasse repr�sentiert einen Knoten
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
		 * n�chster Knoten der Liste
		 */
		private Node next;

		/**
		 * Constructor f�r einen Knoten mit Dataen
		 */
		private Node(String data) {
			writeData(data);
			next = null;
		}

		/**
		 * �berschreibt die Daten des Knotens
		 * 
		 * @param Data
		 *            Daten die gespeichert wereden sollen
		 */
		private void writeData(String data) {
			this.data = data;
		}

		/**
		 * H�ngt einen Knoten ans Ende der Liste an
		 * 
		 * @param nextNode
		 *            n�chster Knoten
		 */
		private void append(Node nextNode) {
			if (next == null) {
				next = nextNode;
			} else {
				next.append(nextNode);
			}

		}
		/**
		 * f�gt eine Knoten nach diesen KNoten ein
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
		 * Gibt den Knoten zur�ck, der nach diesem Knoten kommt		 
		 * @return n�chster Knoten
		 */
		private Node getNextNode(){
			return next;
		}
	}

	public static void main(String[] args) {
		LiLiString list = new LiLiString();
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
		System.out.println("Listeneintr�ge: "+list.toString());
		System.out.println();
		System.out.println("Test suchen: start");
		System.out.println("Suchen nach vorhandenem Wort \"bye4\". Erwartetes Ergebnis: true");
		System.out.println(list.searchString("bye4"));
		System.out.println("Suchen nach nicht vorhandenem Wort \"foobar\". Erwartetes Ergebnis: false");
		System.out.println(list.searchString("foobar"));
		System.out.println();
		System.out.println("Test l�schen: start");
		System.out.println("Liste vor dem L�schen:");
		System.out.println("Listeneintr�ge: "+list.toString());
		System.out.println("Eintrag \"bye2\" wird gel�scht. Erwartetes Ergebnis: ... bye3, bye1, ...");
		list.deleteString("bye2");
		System.out.println("Listeneintr�ge: "+list.toString());
		System.out.println("Test l�schen: done");
				
	}
}
