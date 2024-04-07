package propra22.q5395950.generalUtility;

import java.util.ArrayList;
import java.util.ListIterator;

/**
 * Ein Exemplar dieser Klasse hat einen ListIterator f&uuml;r die Liste, die
 * diesem bei dessen Erzeugung &uuml;bergeben wird. Ein Exemplar dieser Klasse
 * soll die Iteration durch eine ArrayList so implementieren, dass beim
 * Vorw&auml;rtsiterieren am Ende der ArrayList wieder auf das erste Element und
 * beim R&uuml;ckw&auml;rtsiterieren vom ersten auf das letzte Element
 * gesprungen wird. Zus&auml;tzlich soll ein Exemplar dieser Klasse einen Punkt
 * von einer ArrayList lesen k&ouml;nnen ohne die Position seines Iterators an
 * der Liste zu &auml;ndern.
 * 
 * @author LukasSanto Puglisi
 */
public class CircleIterator {
	/**
	 * Der intere Iterator, mit dem durch die dem Exemplar Liste iteriert wird,
	 * welche einem Exemplar dieser Klasse bei Instanziierung &uuml;bergeben wurde.
	 */
	private ListIterator<DataPoint> internalIterator;

	/**
	 * Erzeugt ein Exemplar dieser Klasse. Bei der Erzeugung erzeugt sich das
	 * Exemplar einen Iterator auf die Liste list.
	 * 
	 * @param list Die Liste, durch welche ein Exemplar dieser Klasse iteriert.
	 */
	public CircleIterator(ArrayList<DataPoint> list) {
		internalIterator = list.listIterator();
	}

	/**
	 * Erzeugt ein Exemplar dieser Klasse, wobei die startPosition des internen
	 * Iterators auf die Liste list festgelegt wird.
	 * 
	 * @param list          Die Arrayliste, durch welche ein Exemplar dieser Klasse
	 *                      iteriert.
	 * @param startPosition Die Position der ArrayListe list auf welcher der interne
	 *                      Iterator zu Beginn zeigt.
	 */
	public CircleIterator(ArrayList<DataPoint> list, int startPosition) {
		internalIterator = list.listIterator(startPosition);
	}

	/**
	 * Liefert den n&auml;chsten Punkt in der ArrayListe, wobei der interne Iterator
	 * um eine Position nach vorne geschoben wird.
	 * 
	 * @return Der n&auml;chste Punkt in der ArrayListe.
	 */
	public DataPoint getNextPoint() {

		if (internalIterator.hasNext()) {
			return internalIterator.next();
		} else {
			while (internalIterator.hasPrevious()) {
				internalIterator.previous();
			}
			return internalIterator.next();
		}
	}

	/**
	 * Liefert den n&auml;chsten Punkt in der ArrayListe, wobei der interne Iterator
	 * jedoch nicht um eine Position nach vorne geschoben wird.
	 * 
	 * @return Der n&auml;chste Punkt in der ArrayListe.
	 */
	public DataPoint readOnlyNextPoint() {
		DataPoint dataPoint = getNextPoint();
		getPreviousPoint();
		return dataPoint;
	}

	/**
	 * Liefert den vorangehenden Punkt in der ArrayListe, wobei der interne Iterator
	 * um eine Position nach hinten geschoben wird.
	 * 
	 * @return Der vorangehende Punkt in der ArrayListe.
	 */
	public DataPoint getPreviousPoint() {
		if (internalIterator.hasPrevious()) {
			return internalIterator.previous();
		} else {
			while (internalIterator.hasNext()) {
				internalIterator.next();
			}
			return internalIterator.previous();
		}
	}

	/**
	 * Gibt den Index der n&auml;chsten Iteration zur&uuml;ck.
	 * 
	 * @return Der Index des internen Iterators.
	 */
	public int nextIndex() {
		return internalIterator.nextIndex();
	}

}
