package propra22.q5395950.model;

import java.util.*;
import propra22.q5395950.generalUtility.DataPoint;
import propra22.q5395950.generalUtility.GeometryCalc;

/**
 * Ein Exemplar dieser Klasse repr&auml;sentiert die aktuelle Datenpunktmenge des
 * Datenmodells und arbeitet auschlie&szlig;lich f&uuml;r das Datenmodell (Exemplar der
 * Klasse Model). Es verf&uuml;gt &uuml;ber Methoden, um Datenpunkte hinzuzuf&uuml;gen, zu
 * l&ouml;schen, zu modifizieren, Datenpunkte zu l&ouml;schen und den zu einem Datenpunkt
 * n&auml;hesten Datenpunkt zu finden. Bei hierbei auftretenden Berechnungen st&uuml;tzt
 * sich das Exemplar dieser Klasse auf die statischen Methoden der Klasse
 * GeoemetryCalc ab.
 * 
 * @author LukasSanto Puglisi
 */
class CurrentDataPointContainer {
	/**
	 * In der ArrayList wird die aktuelle Datenpunktmenge gespeichert.
	 */
	private ArrayList<DataPoint> dataPoints = new ArrayList<DataPoint>();

	/**
	 * F&uuml;gt der aktuellen Datenpunktmenge einen Datenpunkt hinzu.
	 * 
	 * @param p Der hinzuzuf&uuml;gende Datenpunkt.
	 */
	void add(DataPoint p) {
		dataPoints.add(p);
	}

	/**
	 * Entfernt alle Datenpunkte aus der aktuellen Datenpunktmenge und f&uuml;gt die
	 * Datenpunkte in der &uuml;bergebenen Liste der aktuellen Datenpunktmenge hinzu.
	 * 
	 * @param inputList Die Datenpunkte, die der Datenpunktmenge hinzugef&uuml;gt werden
	 *                  sollen.
	 */
	void setAllPointsFromNew(ArrayList<DataPoint> inputList) {
		dataPoints.clear();
		Iterator<DataPoint> itr = inputList.iterator();
		while (itr.hasNext()) {
			dataPoints.add(itr.next());
		}
	}

	/**
	 * Gibt alle Datenpunkte der aktuellen Datenpunktmenge des Datenmodells zur&uuml;ck.
	 * 
	 * @return Alle Datenpunkte der aktuellen Datenpunktmenge des Datenmodells
	 */
	ArrayList<DataPoint> getAllPoints() {
		return dataPoints;
	}

	/**
	 * Ermittelt, ob sich in der euklidischen N&auml;he der in den Parametern angegebenen
	 * Koordinate ein in der Datenpunktmenge gespeicherter Punkt befindet. Der
	 * Schwellwert wird hierbei auf 10 festgesetzt.
	 * 
	 * @param x x Koordinate bei der gesucht wird.
	 * @param y y Koordinate bei der gesucht wird.
	 * @return true, wenn sich in der N&auml;he der Koordinate ein Datenpunkt befindet.
	 */
//	boolean isThereNearPoint(long x, long y) {
//		Iterator<DataPoint> itr = dataPoints.iterator();
//		while (itr.hasNext()) {
//			DataPoint p1 = itr.next();
//			long distance = GeometryCalc.quadraticDistanceCoordinates(p1.getX(), p1.getY(), x, y);
//			if (distance < 10) {
//				return true;
//			}
//		}
//		return false;
//	}
	boolean isThereNearPoint(int x, int y) {
		Iterator<DataPoint> itr = dataPoints.iterator();
		while (itr.hasNext()) {
			DataPoint p1 = itr.next();
			long distance = GeometryCalc.quadraticDistanceCoordinates(p1.getX(), p1.getY(), x, y);
			if (distance < 10) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Gibt dem Datenmodell den Datenpunkt aus der aktuellen Datenmenge zur&uuml;ck, der
	 * am n&auml;hesten bei der Koordinate (x, y) liegt.
	 * 
	 * @param x x Koordinate bei der nach dem n&auml;hesten Datenpunkt gesucht wird.
	 * @param y y Koordinate bei der nach dem n&auml;hesten Datenpunkt gesucht wird.
	 * @return Der Datenpunkt aus der aktuellen Datenmenge, der am n&auml;hesten bei der
	 *         Koordinate (x, y) liegt.
	 */
//	DataPoint getNearPoint(long x, long y) {
//		DataPoint closestPoint = null;
//		int currentMinDistance = Integer.MAX_VALUE;
//		Iterator<DataPoint> itr = dataPoints.iterator();
//		while (itr.hasNext()) {
//			DataPoint nextPoint = itr.next();
//			long distance = GeometryCalc.quadraticDistanceCoordinates(nextPoint.getX(), nextPoint.getY(), x, y);
//			if (distance < currentMinDistance) {
//				closestPoint = nextPoint;
//				currentMinDistance = (int) distance;
//			}
//		}
//		return closestPoint;
//	}
	DataPoint getNearPoint(int x, int y) {
		DataPoint closestPoint = null;
		int currentMinDistance = Integer.MAX_VALUE;
		Iterator<DataPoint> itr = dataPoints.iterator();
		while (itr.hasNext()) {
			DataPoint nextPoint = itr.next();
			int distance = GeometryCalc.quadraticDistanceCoordinates(nextPoint.getX(), nextPoint.getY(), x, y);
			if (distance < currentMinDistance) {
				closestPoint = nextPoint;
				currentMinDistance = (int) distance;
			}
		}
		return closestPoint;
	}

	/**
	 * Das Datenmodell kann einen Punkt aus der aktuellen Datenpunktmenge bei der
	 * angegebenen Koordinate l&ouml;schen.
	 * 
	 * @param x x Koordinate, bei der der zu l&ouml;schende Datenpunkt liegt.
	 * @param y y Koordinate, bei der der zu l&ouml;schende Datenpunkt liegt.
	 */
	void deletePointAt(long x, long y) {
		Iterator<DataPoint> itr = dataPoints.iterator();
		while (itr.hasNext()) {
			DataPoint p1 = itr.next();
			if (p1.getX() == x & p1.getY() == y) {
				itr.remove();
			}
		}
	}

	/**
	 * Setzt die aktuellen Koordinaten des &uuml;bergebenen Datenpunkts auf die
	 * &uuml;bergebenen Koordinaten.
	 * 
	 * @param dataPoint Der zu modifizierende Datenpunkt.
	 * @param x         x Koordinate, die x Koordinate zu modifizierende
	 *                  Datenpunktes &uuml;berschreibt.
	 * @param y         y Koordinate, die y Koordinate zu modifizierende
	 *                  Datenpunktes &uuml;berschreibt.
	 */
//	void setGivenPoint(DataPoint dataPoint, long x, long y) {
//		dataPoint.setX(x);
//		dataPoint.setY(y);
//
//	}
	void setGivenPoint(DataPoint dataPoint, int x, int y) {
		dataPoint.setX(x);
		dataPoint.setY(y);

	}

	/**
	 * Gibt die M&auml;chtigkeit der aktuellen Datenpunktmenge an.
	 * 
	 * @return Gr&ouml;&szlig;e der Datenpunktmenge.
	 */

	int size() {
		return dataPoints.size();
	}
/**
 * &Uuml;berpr&uuml;ft, ob sich in der aktuellen Datenpunktmenge des Datenmodells Duplikate 
 * befinden. Diese werden gel&ouml;scht.
 * @return true, wenn sich in der Datenpunktmenge Duplikate befanden. 
 */
	boolean deleteDuplicates() {
		ArrayList<DataPoint> newList = new ArrayList<DataPoint>();

		// Traverse through the first list
		for (DataPoint dataPoint : dataPoints) {

			// If this element is not present in newList
			// then add it
			if (!newList.contains(dataPoint)) {

				newList.add(dataPoint);
			}
		}
		boolean containedDuplicates = dataPoints.size() > newList.size();
		dataPoints = newList;
		return containedDuplicates;
	}
}
