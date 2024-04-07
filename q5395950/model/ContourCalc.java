package propra22.q5395950.model;

import java.util.*;

import propra22.q5395950.generalUtility.DataPoint;

/**
 * Der ConvexHullCalc erzeugt sich ein Exemplar dieser Klasse, um die Kontur einer
 * Datenpunktmenge im zweidimensionalen Raum zu berechnen.
 * 
 * @author LukasSanto Puglisi
 *
 */
class ContourCalc {
	/**
	 * Ein Exemplar dieser Klasse erh&auml;lt bei seiner Erzeugung die Datenpunkte, aus
	 * denen es die Kontur berechnet und speichert eine Referenz auf diese
	 * Datenpunkte.
	 */
	private ArrayList<DataPoint> dataPointsToPrune;

	/**
	 * Die Kontur, die das Exemplar dieser Klasse bei seiner Initialisierung
	 * berechnet und auf Anfrage des KonvexHullCalc
	 * zur&uuml;ckgibt.
	 */
	ArrayList<DataPoint> contour = new ArrayList<DataPoint>();

	/**
	 * Die linke obere Kontur die der ConturCalc aus den Datenpunkten berechnet.
	 */
	private ArrayList<DataPoint> leftUpperContour = new ArrayList<DataPoint>();
	/**
	 * Die linke untere Kontur die der ConturCalc aus den Datenpunkten berechnet.
	 */
	private ArrayList<DataPoint> leftBottomContour = new ArrayList<DataPoint>();
	/**
	 * Die rechte obere Kontur die der ConturCalc aus den Datenpunkten berechnet.
	 */
	private ArrayList<DataPoint> rightUpperContour = new ArrayList<DataPoint>();
	/**
	 * Die rechte untere Kontur die der ConturCalc aus den Datenpunkten berechnet.
	 */
	private ArrayList<DataPoint> rightBottomContour = new ArrayList<DataPoint>();

	/**
	 * Erzeugt ein Exemplar der Klasse. Ist die &uuml;bergebene Datenpunktliste, aus der
	 * die Kontur zu berechnen ist leer, so ist nichts zu tun, denn dann ist auch
	 * die Kontur leer. Ansonsten wird bei Initialisierung des Exemplars sofort die
	 * Kontur berechnet und in der Variable contour gespeichert.
	 * 
	 * @param dataPaintsToPrune Datenpunkte aus der die Kontur berechnet werden
	 *                          soll.
	 */
	ContourCalc(ArrayList<DataPoint> dataPaintsToPrune) {
		this.dataPointsToPrune = dataPaintsToPrune;
		if (dataPaintsToPrune.size() == 0)
			return;
		calculateContour();
	}

	/**
	 * Das Exemplar dieser Klasse setzt die Kontur aus vier Teilst&uuml;cken zusammen.
	 * Beim leftSweep wird die linke Kontur, die aus oberer linker und unterer
	 * linker Kontur besteht berechnet. Beim rightSweep wird die rechte Kontur, die
	 * aus oberer rechter und unterer rechter Kontur besteht berechnet. Anschlie&szlig;end
	 * werden die vier Teilst&uuml;cke zu einer Kontur zusammengesetzt.
	 */
	private void calculateContour() {
		leftSweep();
		rightSweep();
		concatenateToContour();
	}

	/**
	 * Mit dieser Methode wird die linke Kontur der Datenpunkte berechnet. Hierbei
	 * werden die Punkte zun&auml;chst mithilfe eines Exemplars der Klasse
	 * AscendingPointComparator lexikalisch aufsteigend sortiert. In diser
	 * Reihenfolge werden die Punkte durchlaufen und die Kontur entsteht indem nur
	 * dann die Punkte in das Teilst&uuml;ck der Kontur aufgenommen werden, wenn deren y
	 * Koordinate kleiner beziehungsweise gr&ouml;&szlig;er ist als das bisherige Minimum
	 * beziehungsweise Maximum der bisher untersuchten Datenpunkte.
	 */
	private void leftSweep() {
		// sortList in ascending order
		ArrayList<DataPoint> sortedList = dataPointsToPrune;
		Collections.sort(sortedList, new AscendingPointComparator());
		Iterator<DataPoint> itr = sortedList.iterator();

		// at start min and max points are the same and equal leftmost point
		DataPoint MinYSoFarPL = itr.next();
		DataPoint MaxYSoFarPL = MinYSoFarPL;
		// lists containing points where y component is monotonously
		// ascending/descending and contains leftmost point
		leftUpperContour.add(MinYSoFarPL);
		leftBottomContour.add(MaxYSoFarPL);

		while (itr.hasNext()) {
			// first point -> no action required
			DataPoint p = itr.next();
			if (p.getY() >= MaxYSoFarPL.getY()) {
				leftUpperContour.add(p);
				MaxYSoFarPL = p;
			}

			if (p.getY() <= MinYSoFarPL.getY()) {
				leftBottomContour.add(p);
				MinYSoFarPL = p;
			}

		}

	}

	/**
	 * Mit dieser Methode wird die rechte Kontur der Datenpunkte berechnet. Hierbei
	 * werden die Punkte zun&auml;chst mithilfe eines Exemplars der Klasse Descending
	 * PointComparator lexikalisch absteigend sortiert. In dieser Reihenfolge werden
	 * die Punkte durchlaufen und die Kontur entsteht indem nur dann die Punkte in
	 * das Teilst&uuml;ck der Kontur aufgenommen werden, wenn deren y Koordinate kleiner
	 * beziehungsweise gr&ouml;&szlig;er ist als das bisherige Minimum beziehungsweise Maximum
	 * der bisher untersuchten Datenpunkte. Im Gegensatz zum leftSweep, werden die
	 * Punkte nur aufgenommen, wenn diese strikt kleiner beziehungsweise strikt
	 * gr&ouml;&szlig;er sind als das bisherige Minimum, Maximum sind. Damit wird eine
	 * &uuml;berschneidung mit der rechten Kontur verhindert.
	 */
	private void rightSweep() {
		ArrayList<DataPoint> sortedList = dataPointsToPrune;
		Collections.sort(sortedList, new DescendingPointComparator());
		Iterator<DataPoint> itr = sortedList.iterator();

		// at start min and max points are the same and equal leftmost point
		DataPoint MinYSoFarPR = itr.next();
		DataPoint MaxYSoFarPR = MinYSoFarPR;

		rightUpperContour.add(MaxYSoFarPR);
		rightBottomContour.add(MinYSoFarPR);

		while (itr.hasNext()) {
			// first point -> no action required
			DataPoint p = itr.next();
			// In contrast to left sweep the right contour ends when the next points y
			// coordinate
			// is not strictly higher than the current y-max to prevent an overlap between
			// contours
			if (p.getY() > MaxYSoFarPR.getY()) {
				rightUpperContour.add(p);
				MaxYSoFarPR = p;
			}

			if (p.getY() < MinYSoFarPR.getY()) {
				rightBottomContour.add(p);
				MinYSoFarPR = p;
			}
		}

	}

	/**
	 * Nachdem die Teilst&uuml;cke der Kontur berechnet wurden, werden diese
	 * zusammengesetzt. Hierbei m&uuml;ssen gegebenenfalls Randpunkte der Teilst&uuml;cke
	 * gel&ouml;scht werden um Doppelungen zu vermeiden. Die Punkte werden entgegen der
	 * Umlaufreihenfolge des Konturpolygons sortiert.
	 */
	private void concatenateToContour() {
		Collections.reverse(rightBottomContour);
		rightUpperContour.remove(0);
		rightBottomContour.remove(0);
		Collections.reverse(leftUpperContour);
		leftUpperContour.remove(0);
		Collections.addAll(contour, leftBottomContour.toArray(new DataPoint[0]));
		Collections.addAll(contour, rightBottomContour.toArray(new DataPoint[0]));
		Collections.addAll(contour, rightUpperContour.toArray(new DataPoint[0]));
		Collections.addAll(contour, leftUpperContour.toArray(new DataPoint[0]));

	}

	/**
	 * Das Datenmodell (Exemplar der Klasse Model) benutzt diese Methode, um die
	 * Kontur zu erhalten.
	 * 
	 * @return Die Kontur der Datenpunkte, die dem ConturCalc bei seiner Erzeugung
	 *         mitgegeben wurden.
	 */
	ArrayList<DataPoint> getContour() {
		return contour;
	}

}
