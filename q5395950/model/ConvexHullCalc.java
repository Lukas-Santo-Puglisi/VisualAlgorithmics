package propra22.q5395950.model;

import java.util.*;

import propra22.q5395950.generalUtility.DataPoint;
import propra22.q5395950.generalUtility.GeometryCalc;

/**
 * Das Datenmodell nutzt ein Exemplar der Klasse ConvexHullCalc, um die konvexe
 * H&uuml;lle der aktuellen Datenpunkte zu berechnen. Hierbei &uuml;bergibt das Modell
 * diesem Exemplar die vom ConturCalc berechnete Kontur der aktuellen
 * Datenpunkte.
 * 
 * @author LukasSanto Puglisi
 *
 */
class ConvexHullCalc {
	/**
	 * Die vom Modell &uuml;bergebene Kontur aus der die konvexe H&uuml;lle berechnet werden
	 * soll.
	 */
	private ArrayList<DataPoint> contour;
	/**
	 * Die Variable, in welcher die konvexe H&uuml;lle gespeichert wird und die dem
	 * Modell auf Anfrage zur&uuml;ckgegeben wird. In der konvexen H&uuml;lle wird der erste
	 * Datenpunkt der Kontur einmal als Startpunkt und einmal als Endpunkt der
	 * konvexen H&uuml;lle doppelt kodiert.
	 */
	private ArrayList<DataPoint> convexHull = new ArrayList<DataPoint>();

	/**
	 * Bei Erzeugung eines Exemplars dieser Klasse wird diesem direkt die Kontur der
	 * Datenpunkte &uuml;bergeben. Ist die Kontur leer, so ist nichts zu tun, denn dann
	 * ist die konvexe H&uuml;lle auch leer. Ansonsten wird direkt bei Initialisierung
	 * die konvexe H&uuml;lle berechnet.
	 * 
	 * @param dataPoints Die aktuelle Datenpunktmenge aus der die konvexe H&uuml;lle zu
	 *                   berechnen ist.
	 */
	ConvexHullCalc(ArrayList<DataPoint> dataPoints) {
		ContourCalc cc = new ContourCalc(dataPoints);
		this.contour = cc.getContour();
		if (dataPoints.size() == 0)
			return;
		calcConvexHull();
	}

	/**
	 * &Uuml;bergibt auf Anfrage die konvexe H&uuml;lle.
	 * 
	 * @return Die durch das Exemplar berechnete konvexe H&uuml;lle.
	 */
	ArrayList<DataPoint> getConvexHull() {
		return convexHull;
	}

	/**
	 * Berechnet aus der Kontur die konvexe H&uuml;lle. Zun&auml;chst werden alle Datenpunkte
	 * aus der Kontur in die konvexe H&uuml;lle kopiert. Dann wird die Kontur von links
	 * nach oben nach rechts nach unten und wieder nach links abgegangen, wobei
	 * immer drei (aktuell) aufeinanderfolgende Datenpunkte left, right und query
	 * betrachtet werden. Liegt right rechts (weil die y Achse auf dem Bildschirm
	 * invertiert ist, wird getestet ob der Punkt links hiervon liegt) der Linie von
	 * left und query so muss right gel&ouml;scht werden und die Datenpunkte left und
	 * right verschieben sich wieder um eine Position zur&uuml;ck. Insgesamt entsteht
	 * also die konvexe H&uuml;lle aus der Kontur durch die Bereinigung hineinragender
	 * Ecken.
	 */
	private void calcConvexHull() {
		DataPoint left;
		DataPoint right;
		DataPoint query;
		ListIterator<DataPoint> contourIterator = contour.listIterator();
		while (contourIterator.hasNext()) {
			convexHull.add(contourIterator.next());
		}

//       if contour consists only of one or two points nothing has to be done
		if (convexHull.size() == 1 || convexHull.size() == 3) {
			return;
		}

		ListIterator<DataPoint> convexHullIterator = convexHull.listIterator();
		left = convexHullIterator.next();
		if (convexHullIterator.hasNext()) {
			right = convexHullIterator.next();
			while (convexHullIterator.hasNext()) {
				query = convexHullIterator.next();
				boolean isLeft = GeometryCalc.isLeftBoolean(left, right, query);
				// the lexicographic highest point is never deleted
				boolean caseXhigher = (left.getX() < right.getX()) && (right.getX() > query.getX());
				boolean caseXequal = (left.getX() == right.getX())
						&& (right.getY() > left.getY() && query.getX() == right.getX() && query.getY() < right.getY());
				boolean skipLexicographicHighest = (caseXhigher || caseXequal);

				if (isLeft & !skipLexicographicHighest) {
					convexHullIterator.previous();
					convexHullIterator.previous();
					convexHullIterator.remove();
					convexHullIterator.previous();
					// convexHullIterator points now to left
					if (convexHullIterator.hasPrevious()) {
						left = convexHullIterator.previous();
						convexHullIterator.next();
						right = convexHullIterator.next();
					} else {
						right = query;
						convexHullIterator.next();
						convexHullIterator.next();
					}
				} else {
					left = right;
					right = query;
				}
			}
		}

	}
}
