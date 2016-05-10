package Praktikum6;

import java.util.Random;

/**
 * Die Klasse stellt einen Bin�ren Baum f�r geordnete STrings da.
 * 
 * @author Falk SChmitz
 * 
 */
public class StringTree {
	// IV
	// WurzelKNoten
	Node root;

	// IM
	public StringTree() {
		root = null;
	}

	/**
	 * F�gt einen neuen Knoten geordnet in den baum ein
	 * 
	 * @param n
	 *            Nutzlast
	 */
	public void add(String n) {
		if (root == null)
			root = new Node(n);
		else
			root.add(n);

	}

	/**
	 * pr�ft ob ein WErt im Baum gespeichert ist
	 * 
	 * @param n
	 *            zu pr�fender wert
	 * @return boolean true wenn ja, false wenn nicht
	 */
	public boolean contains(String n) {
		if (root == null)
			return false;
		else
			return root.contains(n);
	}
	
	public void delete(String s){
		root.deleteNode(s);
	}

	public String toString() {
		return root.transverse();
	}

	private void updateRoot(Node root) {
		this.root = root;
	}
	


	// Diese KLasse stellt einen Knoten im Baum da
	private class Node {
		// n�chste Node im groesser pfad
		Node groesser;
		// n�chste Node im kleiner Pfad
		Node kleiner;
		String nutzlast;

		// IM
		/**
		 * COnstructor f�r node braucht nutzlast
		 * 
		 * @param n
		 */
		private Node(String n) {
			groesser = null;
			kleiner = null;
			nutzlast = n;
		}

		/**
		 * f�gt einen neuen KNoten geordnet in den Baum ein
		 * 
		 * @param n
		 *            NUtzlast
		 */
		private void add(String n) {
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
		 * pr�ft ob ein String im Baum vorhanden ist
		 * 
		 * @param n
		 *            zu pr�fender String
		 * @return true wenn String vorhanden
		 */
		private boolean contains(String n) {
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
		 * sucht den Zlk f�r die deleteNode() ;ethode
		 * 
		 * @param s
		 *            Wert der gel�scht werden soll
		 * @return ZLK Node
		 */
		private Node Zlk(String s) {
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
		 * sucht die Vorg�nger Node des Zlk f�r die deleteNode() Methode
		 * 
		 * @param zlk
		 * @return
		 */
		private Node NodePreZlk(Node zlk) {
			if (groesser != null && groesser.nutzlast.compareTo(zlk.nutzlast) == 0) {
				return this;
			}
			if (kleiner != null &&kleiner.nutzlast.compareTo(zlk.nutzlast) == 0) {
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
			if(preZlk.kleiner != null)
				return preZlk.kleiner.equals(zlk);
			return false;
		}

		/**
		 * l�scht einen Knoten
		 * 
		 * @param s
		 *            Nutzlast des Knotens der gels�cht werden soll
		 */
		private void deleteNode(String s) {
			Node zlk = Zlk(s);
			
			if(zlk != null){
				Node preZlk = NodePreZlk(zlk);
				// kein nachfolger von zlk
				if (zlk.groesser == null && zlk.kleiner == null) {
					//pr�fen ob root gel�scht wird
					if (preZlk == null) {
						updateRoot(null);
					} else {
						// verh�ltnis von zlk und preZlk pr�fen
						if (isZlkKleiner(zlk, preZlk))
							preZlk.kleiner = null;
						else
							preZlk.groesser = null;
					}
				}// 1 nachfolger von zlk
				if (zlk.groesser == null ^ zlk.kleiner == null){
					// pr�fen ob root gel�scht wird
					if (preZlk == null) {
						if (zlk.groesser != null) {
							updateRoot(zlk.groesser); 
						} else {
							updateRoot(zlk.kleiner);
						}
					} else {
						// verh�ltnis von zlk und preZlk pr�fen
						if (isZlkKleiner(zlk, preZlk)) {
							// richtigen nach folger von zlk ausw�hlen
							if (zlk.groesser != null) {
								preZlk.kleiner = zlk.groesser;
							} else {
								preZlk.kleiner = zlk.kleiner;
							}
						} else {
							// richtigen nach folger von zlk ausw�hlen
							if (zlk.groesser != null) {
								preZlk.groesser = zlk.groesser;
							} else {
								preZlk.groesser = zlk.kleiner;
							}
						}
					}
				}
				// 2 nachfolger von zlk
				if (zlk.groesser != null && zlk.kleiner != null){
					//node mit dem n�chsten wert raussuchen und l�schen
					Node nextNutzlast = nextNutzlast(zlk);
					deleteNode(nextNutzlast.nutzlast);
					// pr�fen ob root gel�scht wird
					if (preZlk == null) {
						nextNutzlast.kleiner = zlk.kleiner;
						nextNutzlast.groesser = zlk.groesser;
						updateRoot(nextNutzlast);
					}
					else{
						
						nextNutzlast.kleiner = zlk.kleiner;
						nextNutzlast.groesser = zlk.groesser;
						
						// verh�ltnis von zlk und preZlk pr�fen
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
		 * gibt, von dem angegebenem knoten aus, den knoten zur�ck dessen nutzlast als n�chstes kommt
		 * @param zlk
		 * @return
		 */
		private Node nextNutzlast(Node zlk){
			return lowestNutzlast(zlk.groesser);
		}

		/**
		 * gibt den knoten mit der kleinsten nutzlast zur�ck. vom �bergebenem knoten ausgehend
		 * @param node
		 * @return
		 */
		private Node lowestNutzlast(Node node){
			if(node.kleiner != null)
				return lowestNutzlast(node.kleiner);
			return node;
		}
	}

	public static void main(String[] args) {
		StringTree tree = new StringTree();
		tree.add("hallo5");
		for (int i = 0; i < 10; i++) {
			tree.add("hallo" + i);
		}
		
		System.out.println(tree);
		
		tree.delete("hallo6");
		System.out.println(tree);
	}

}
