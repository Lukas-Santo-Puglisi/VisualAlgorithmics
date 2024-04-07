package propra22.q5395950.model;

import java.util.Comparator;

import propra22.q5395950.generalUtility.DataPoint;

/**
 * Ein Exemplar der Klasse ConturCalc erzeugt sich und benutzt eine Exemplar der
 * Klasse DescendingPointComparator, um die ihm &uuml;bergebenen Datenpunkte gem&auml;&szlig;
 * der in der Methode compare vorgegebenen Ordnung (absteigend) im Zusammenspiel
 * mit der statischen Methode sort der Java Collections Klasse lexikalisch zu
 * ordnen.
 * 
 * @author LukasSanto Puglisi
 */
public class DescendingPointComparator implements Comparator<DataPoint> {
	/**
	 * Mittels dieser Methode werden die beiden als Argumente &uuml;bergebenen
	 * Datenpunkte miteinander verglichen. Es wird ein negatives Integerprimitiv,
	 * null oder ein positives Integerprimitiv zur&uuml;ckgegeben, wenn das zweite
	 * Argument kleiner, gleich oder gr&ouml;&szlig;er als das erste Argument ist.
	 * 
	 * @param p1 Ein Datenpunkt f&uuml;r den Vergleich
	 * @param p2 Ein Datenpunkt f&uuml;r den Vergleich
	 * @return Das Ergebnis des Vergleichs kodiert als Integer.
	 */
	@Override
	public int compare(DataPoint p1, DataPoint p2) {

		if (p1.getX() > p2.getX()) {
			return -1;
		}

		// When x-Coordinates are equal, we sort in ascending order along Y coordinates
		if (p1.getX() == p2.getX()) {
			if (p1.getY() < p2.getY()) {
				return -1;
			}
			if (p1.getY() > p2.getY()) {
				return 1;
			} else
				return 0;

		} else
			return 1;
	}

}
