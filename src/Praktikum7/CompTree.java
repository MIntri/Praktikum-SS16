package Praktikum7;

import java.util.Iterator;

/**
 * Die Klasse stellt einen Binären Baum für geordnete STrings da.
 * 
 * @author Falk SChmitz
 * 
 */
public class CompTree implements Iterable{
	// IV
	// WurzelKNoten
	Node root;

	// IM
	public CompTree() {
		root = null;
	}

	/**
	 * Fügt einen neuen Knoten geordnet in den baum ein
	 * 
	 * @param n
	 *            Nutzlast
	 */
	public void add(Comparable n) {
		if (root == null)
			root = new Node(n);
		else
			root.add(n);

	}

	/**
	 * prüft ob ein WErt im Baum gespeichert ist
	 * 
	 * @param n
	 *            zu prüfender wert
	 * @return boolean true wenn ja, false wenn nicht
	 */
	public boolean contains(Comparable n) {
		if (root == null)
			return false;
		else
			return root.contains(n);
	}

	public void delete(Comparable s) {
		root.deleteNode(s);
	}

	public String toString() {
		return root.transverse();
	}

	public String preOrder() {
		return root.transversePre();
	}

	public String postOrder() {
		return root.transversePost();
	}
	
	public Iterator iterator() {
		return new CompIterator(root);
	}

	private void updateRoot(Node root) {
		this.root = root;
	}

	// Diese KLasse stellt einen Knoten im Baum da
	private class Node {
		// nächste Node im groesser pfad
		Node groesser;
		// nächste Node im kleiner Pfad
		Node kleiner;
		Comparable nutzlast;

		// IM
		/**
		 * COnstructor für node braucht nutzlast
		 * 
		 * @param n
		 */
		private Node(Comparable n) {
			groesser = null;
			kleiner = null;
			nutzlast = n;
		}

		/**
		 * fügt einen neuen KNoten geordnet in den Baum ein
		 * 
		 * @param n
		 *            NUtzlast
		 */
		private void add(Comparable n) {
			if (nutzlast.compareTo(n) <= -1) {
				if (groesser == null)
					groesser = new Node(n);
				else
					groesser.add(n);
			}
			if (nutzlast.compareTo(n) >= 1) {
				if (kleiner == null)
					kleiner = new Node(n);
				else
					kleiner.add(n);
			}
		}

		/**
		 * prüft ob ein String im Baum vorhanden ist
		 * 
		 * @param n
		 *            zu prüfender String
		 * @return true wenn String vorhanden
		 */
		private boolean contains(Comparable n) {
			if (nutzlast.compareTo(n) == 0) {
				return true;
			}

			if (nutzlast.compareTo(n) <= -1) {
				if (groesser != null)
					return groesser.contains(n);
			}
			if (nutzlast.compareTo(n) >= 1) {
				if (kleiner != null)
					return kleiner.contains(n);
			}

			return false;
		}

		/**
		 * Baut den Baum als String InOrder zusammen
		 * 
		 * @return Alle Nutzlasten InOrder
		 */

		private String transverse() {
			StringBuilder output = new StringBuilder();

			if (kleiner != null)
				output.append(kleiner.transverse());
			output.append(nutzlast + " ");
			if (groesser != null)
				output.append(groesser.transverse());

			return output.toString();
		}

		/**
		 * Baut Baum als String in Pre Order zusammen
		 * 
		 * @return
		 */
		private String transversePre() {
			StringBuilder output = new StringBuilder();

			output.append(nutzlast + " ");

			if (kleiner != null)
				output.append(kleiner.transverse());

			if (groesser != null)
				output.append(groesser.transverse());

			return output.toString();
		}

		/**
		 * Baut Buam als String post Order zusammen
		 * 
		 * @return
		 */
		private String transversePost() {
			StringBuilder output = new StringBuilder();

			if (kleiner != null)
				output.append(kleiner.transverse());

			if (groesser != null)
				output.append(groesser.transverse());

			output.append(nutzlast + " ");

			return output.toString();
		}

		/**
		 * sucht den Zlk für die deleteNode() ;ethode
		 * 
		 * @param s
		 *            Wert der gelöscht werden soll
		 * @return ZLK Node
		 */
		private Node Zlk(Comparable s) {
			if (nutzlast.compareTo(s) == 0) {
				return this;
			}

			if (nutzlast.compareTo(s) <= -1) {
				if (groesser != null)
					return groesser.Zlk(s);
			}
			if (nutzlast.compareTo(s) >= 1) {
				if (kleiner != null)
					return kleiner.Zlk(s);
			}
			return null;
		}

		/**
		 * sucht die Vorgänger Node des Zlk für die deleteNode() Methode
		 * 
		 * @param zlk
		 * @return
		 */
		private Node NodePreZlk(Node zlk) {
			if (groesser != null
					&& groesser.nutzlast.compareTo(zlk.nutzlast) == 0) {
				return this;
			}
			if (kleiner != null
					&& kleiner.nutzlast.compareTo(zlk.nutzlast) == 0) {
				return this;
			}

			if (nutzlast.compareTo(zlk.nutzlast) <= -1) {
				if (groesser != null)
					return groesser.NodePreZlk(zlk);
			}
			if (nutzlast.compareTo(zlk.nutzlast) >= 1) {
				if (kleiner != null)
					return kleiner.NodePreZlk(zlk);
			}
			return null;
		}

		private boolean isZlkKleiner(Node zlk, Node preZlk) {
			if (preZlk.kleiner != null)
				return preZlk.kleiner.equals(zlk);
			return false;
		}

		/**
		 * löscht einen Knoten
		 * 
		 * @param s
		 *            Nutzlast des Knotens der gelsöcht werden soll
		 */
		private void deleteNode(Comparable s) {
			Node zlk = Zlk(s);

			if (zlk != null) {
				Node preZlk = NodePreZlk(zlk);
				// kein nachfolger von zlk
				if (zlk.groesser == null && zlk.kleiner == null) {
					// prüfen ob root gelöscht wird
					if (preZlk == null) {
						updateRoot(null);
					} else {
						// verhältnis von zlk und preZlk prüfen
						if (isZlkKleiner(zlk, preZlk))
							preZlk.kleiner = null;
						else
							preZlk.groesser = null;
					}
				}// 1 nachfolger von zlk
				if (zlk.groesser == null ^ zlk.kleiner == null) {
					// prüfen ob root gelöscht wird
					if (preZlk == null) {
						if (zlk.groesser != null) {
							updateRoot(zlk.groesser);
						} else {
							updateRoot(zlk.kleiner);
						}
					} else {
						// verhältnis von zlk und preZlk prüfen
						if (isZlkKleiner(zlk, preZlk)) {
							// richtigen nach folger von zlk auswählen
							if (zlk.groesser != null) {
								preZlk.kleiner = zlk.groesser;
							} else {
								preZlk.kleiner = zlk.kleiner;
							}
						} else {
							// richtigen nach folger von zlk auswählen
							if (zlk.groesser != null) {
								preZlk.groesser = zlk.groesser;
							} else {
								preZlk.groesser = zlk.kleiner;
							}
						}
					}
				}
				// 2 nachfolger von zlk
				if (zlk.groesser != null && zlk.kleiner != null) {
					// node mit dem nächsten wert raussuchen und löschen
					Node nextNutzlast = nextNutzlast(zlk);
					deleteNode(nextNutzlast.nutzlast);
					// prüfen ob root gelöscht wird
					if (preZlk == null) {
						nextNutzlast.kleiner = zlk.kleiner;
						nextNutzlast.groesser = zlk.groesser;
						updateRoot(nextNutzlast);
					} else {

						nextNutzlast.kleiner = zlk.kleiner;
						nextNutzlast.groesser = zlk.groesser;

						// verhältnis von zlk und preZlk prüfen
						if (isZlkKleiner(zlk, preZlk)) {
							preZlk.kleiner = nextNutzlast;
						} else {
							preZlk.groesser = nextNutzlast;
						}
					}
				}
			}
		}

		/**
		 * gibt, von dem angegebenem knoten aus, den knoten zurück dessen
		 * nutzlast als nächstes kommt
		 * 
		 * @param zlk
		 * @return
		 */
		private Node nextNutzlast(Node zlk) {
			return lowestNutzlast(zlk.groesser);
		}

		/**
		 * gibt den knoten mit der kleinsten nutzlast zurück. vom übergebenem
		 * knoten ausgehend
		 * 
		 * @param node
		 * @return
		 */
		private Node lowestNutzlast(Node node) {
			if (node.kleiner != null)
				return lowestNutzlast(node.kleiner);
			return node;
		}

	}

	private class CompIterator implements Iterator {

		Node current;

		public CompIterator(Node f) {
			current = f;
		}

		public boolean hasNext() {
			return current!= null;
		}

		public Object next() {
			if (hasNext()){
				Node temp = current;
				current = current.groesser;
				return temp.nutzlast;
			}
			return null;
		}
	}

	public static void main(String[] args) {
		CompTree sTree = new CompTree();
		sTree.add("hallo5");
		for (int i = 0; i < 10; i++) {
			sTree.add("hallo" + i);
		}
		CompTree iTree = new CompTree();
		iTree.add(5);
		for (int i = 0; i < 10; i++) {
			iTree.add(i);
		}
		CompTree fTree = new CompTree();
		fTree.add(5.5);
		for (int i = 0; i < 10; i++) {
			fTree.add(i + 0.1);
		}
		System.out.println(sTree);
		System.out.println(iTree);
		System.out.println(fTree);
		//
		// CompTree mixTree = new CompTree();
		// mixTree.add(1);
		// mixTree.add("foobar");
		// mixTree.add(4.2);

		CompTree studiTree = new CompTree();

		studiTree.add(new Studi("drolf", 1));
		studiTree.add(new Studi("alf", 2));
		studiTree.add(new Studi("foobar", 3));

		System.out.println(studiTree);
		
		iterateStudiTree(studiTree);
	}
	
	public static void iterateStudiTree(CompTree tree){
		Iterator it = tree.iterator();
		while(it.hasNext()){
			System.out.println(it.next());
		}
		
	}
	public static void pruefStudiTree(CompTree tree){
		Iterator it = tree.iterator();
		while(it.hasNext()){
			//it.
		}
		for (Object object : tree) {
			//object.
		}
		
	}


}
