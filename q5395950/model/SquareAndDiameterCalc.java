package propra22.q5395950.model;

import java.util.ArrayList;
import java.util.ListIterator;

import propra22.q5395950.generalUtility.CircleIterator;
import propra22.q5395950.generalUtility.DataPoint;
import propra22.q5395950.generalUtility.GeometryCalc;

/**
 * Ein Exemplar dieser Klasse berechnet aus der &uuml;bergebenen Menge an
 * Datenpunkten den gr&ouml;&szlig;ten Durchmesser und das gr&ouml;&szlig;te
 * Viereck in der konvexen H&uuml;llen der &uuml;bergebenen Datenpunkte. Um die
 * konvexe H&uuml;lle zu berechnen, benutzt ein Exemplar dieser Klasse ein
 * selbst erzeugtes Exemplar der Klasse ConvexHullCalc.
 * 
 * @author LukasSanto Puglisi
 */
class SquareAndDiameterCalc {
	/**
	 * Eine Kopie der konvexen H&uuml;lle, wobei der erste Datenpunkt nicht mehr
	 * doppelt kodiert ist.
	 */
	private ArrayList<DataPoint> copyOfConvexHull = new ArrayList<DataPoint>();
	/**
	 * Der lexikographisch kleinste Datenpunkt in der konvexen H&uuml;lle.
	 */
	private DataPoint minP;
	/**
	 * Der Index des lexikographisch kleinsten Datenpunkts in der konvexen
	 * H&uuml;lle.
	 */
	private int indexOfminP;
	/**
	 * Der lexikographisch gr&ouml;&szlig;te Datenpunkt in der konvexen H&uuml;lle.
	 */
	private DataPoint maxP;
	/**
	 * Der Index des lexikographisch gr&ouml;&szlig;ten Datenpunkts in der konvexen
	 * H&uuml;lle.
	 */
	private int indexOfmaxP;
	/**
	 * Der linke Endpunkt des gr&ouml;&szlig;ten Durchmessers in der konvexen
	 * H&uuml;lle der Datenpunkte.
	 */
	private DataPoint diameterA;
	/**
	 * Der rechte Endpunkt des gr&ouml;&szlig;ten Durchmessers in der konvexen
	 * H&uuml;lle der Datenpunkte.
	 */
	private DataPoint diameterC;
	/**
	 * Ein Eckpunkt des gr&ouml;&szlig;ten Vierecks in der konvexen H&uuml;lle.
	 */
	private DataPoint quadrangleA;
	/**
	 * Ein Eckpunkt des gr&ouml;&szlig;ten Vierecks in der konvexen H&uuml;lle.
	 */
	private DataPoint quadrangleB;
	/**
	 * Ein Eckpunkt des gr&ouml;&szlig;ten Vierecks in der konvexen H&uuml;lle.
	 */
	private DataPoint quadrangleC;
	/**
	 * Ein Eckpunkt des gr&ouml;&szlig;ten Vierecks in der konvexen H&uuml;lle.
	 */
	private DataPoint quadrangleD;
	/**
	 * Die L&auml;nge des gr&ouml;&szlig;ten Durchmessers in der konvexen
	 * H&uuml;lle.
	 */
	private double diameterLength;
	/**
	 * Die Fl&auml;che des gr&ouml;&szlig;ten Vierecks in der konvexen H&uuml;lle.
	 */
	private double quadrangleArea;

	/**
	 * Erzeugt ein Exemplar der Klasse. Aus der &uuml;bergebenen Datenpunktmenge
	 * werden sofort der gr&ouml;&szlig;te Durchmesser und das gr&ouml;&szlig;te
	 * Viereck unter Zuhilfename eines Exemplars der Klasse ConvexHullCalc der
	 * berechnet.
	 * 
	 * @param dataPoints Datenpunkte, aus denen die geometrischen Objekte berechnet
	 *                   werden soll.
	 */

	SquareAndDiameterCalc(ArrayList<DataPoint> convexHull) {
		if (convexHull.size() <= 1) {
			dealWithSpecialCases(convexHull);

		} else {
			generateCopyOfConvexHullCalcMinPAndMaxP(convexHull);
			calcSquareAndDiameter();
		}
	}

	/**
	 * Kopiert die konvexe H&uuml;lle, gleichzeitig werden der lexikographisch
	 * kleinste und gr&ouml;&szlig;te Punkt berechnet. In der Kopie ist der erste
	 * Datenpunkt nicht mehr doppelt kodiert.
	 * 
	 * @param convexHull Die konvexe H&uuml;lle aus der die lexikographisch
	 *                   kleinsten und gr&ouml;&szlig;ten Punkte berechnet werden.
	 */
	private void generateCopyOfConvexHullCalcMinPAndMaxP(ArrayList<DataPoint> convexHull) {
		ListIterator<DataPoint> copyIterator = convexHull.listIterator();
		// skip double encoded first element
		// Further, I find the lexicographic highest and lowest point in the convexHull
		minP = copyIterator.next();
		copyOfConvexHull.add(minP);
		indexOfminP = 0;
		maxP = minP;
		int i = 0;
		indexOfmaxP = 0;
		while (copyIterator.hasNext() && i < convexHull.size() - 2) {
			i++;
			DataPoint p = copyIterator.next();
			copyOfConvexHull.add(p);
			if (p.getX() < minP.getX()) {
				minP = p;
				indexOfminP = i;
			} else if ((p.getX() == minP.getX()) & (p.getY() < minP.getY())) {
				minP = p;
				indexOfminP = i;
			}
			if (p.getX() > maxP.getX()) {
				maxP = p;
				indexOfmaxP = i;
			} else if ((p.getX() == maxP.getX()) & (p.getY() > maxP.getY())) {
				maxP = p;
				indexOfmaxP = i;
			}
		}
	}

	/**
	 * Ist in der berechneten konvexen lediglich 0 oder ein Datenpunkt enthalten
	 * wird der Konvention zufolge null beziehungsweise das Paar beziehungsweise das
	 * Viereck mit viermal demselben Punkt zur&uuml;ckgeliefert.
	 * 
	 * @param convexHull Die konvexe H&uuml;lle, aus der die geometrischen Figuren
	 *                   berechnet werden.
	 */
	private void dealWithSpecialCases(ArrayList<DataPoint> convexHull) {
		if (convexHull.size() == 0) {
			return;
		}
		if (convexHull.size() == 1) {
			diameterLength = 0;
			quadrangleArea = 0;
			DataPoint singlePoint = convexHull.get(0);
			diameterA = new DataPoint(singlePoint.getX(), singlePoint.getY());
			diameterC = new DataPoint(singlePoint.getX(), singlePoint.getY());

			quadrangleA = new DataPoint(singlePoint.getX(), singlePoint.getY());
			quadrangleB = new DataPoint(singlePoint.getX(), singlePoint.getY());
			quadrangleC = new DataPoint(singlePoint.getX(), singlePoint.getY());
			quadrangleD = new DataPoint(singlePoint.getX(), singlePoint.getY());
		}
	}

	/**
	 * Berechnet aus der &uuml;bergebenen Menge an Datenpunkten den
	 * gr&ouml;&szlig;ten Durchmesser und das gr&ouml;&szlig;te Viereck in der
	 * konvexen H&uuml;llen der &uuml;bergebenen Datenpunkte. Es werden begonnen mit
	 * dem Antipodenpaar minP und maxP der konvexen H&uuml;lle unter Zuhilfename der
	 * Methode getNextAntipodesAndVertices alle Antipodenpaare abgegangen und aus
	 * diesen wird das Maximum bestimmt. Gleichzeitig wird f&uuml;r je ein
	 * Antipodenpaar unter Zuhilfename derselben Methode das gr&ouml;&szlig;te
	 * Viereck zur&uuml;ckgegeben. Unter diesen Vierecken wird wieder das
	 * gr&ouml;&szlig;te Viereck gespeichert.
	 */
	private void calcSquareAndDiameter() {
		CircleIterator aPointer = new CircleIterator(copyOfConvexHull, indexOfminP);
		CircleIterator bPointer = new CircleIterator(copyOfConvexHull, indexOfmaxP);
		DataPoint readInMin = aPointer.readOnlyNextPoint();
		DataPoint readInMax = bPointer.readOnlyNextPoint();

		diameterA = new DataPoint(readInMin.getX(), readInMin.getY());
		diameterC = new DataPoint(readInMax.getX(), readInMax.getY());

		quadrangleA = new DataPoint(readInMin.getX(), readInMin.getY());
		quadrangleB = new DataPoint(readInMin.getX(), readInMin.getY());
		quadrangleC = new DataPoint(readInMax.getX(), readInMax.getY());
		quadrangleD = new DataPoint(readInMax.getX(), readInMax.getY());

		double currentDiameterLength = 0;
		double currentQuadrangleArea = 0;
		DataPoint[] nextAntipodes = null;
		DataPoint nextAntipodeA = null;
		DataPoint nextAntipodeC = null;
		DataPoint highestB = null;
		DataPoint highestD = null;

		do {
			nextAntipodes = getNextAntipodesAndVertices(aPointer, bPointer);
			nextAntipodeA = nextAntipodes[0];
			highestB = nextAntipodes[1];
			nextAntipodeC = nextAntipodes[2];
			highestD = nextAntipodes[3];
			double toCompareDiameter = GeometryCalc.quadraticDistanceDataPoints(nextAntipodeA, nextAntipodeC);
			if (toCompareDiameter > currentDiameterLength) {
				currentDiameterLength = refreshCurrentDiameter(nextAntipodeA, nextAntipodeC, toCompareDiameter);
			}
			double toCompareQuadrangleArea = GeometryCalc.quadrangleArea(nextAntipodeA, highestB, nextAntipodeC,
					highestD);
			if (toCompareQuadrangleArea > currentQuadrangleArea) {
				currentQuadrangleArea = refreshCurrentQuadrangle(nextAntipodeA, nextAntipodeC, highestB, highestD,
						toCompareQuadrangleArea);
			}

		} while ((nextAntipodeA != maxP) || (nextAntipodeC != minP));
		diameterLength = Math.sqrt(currentDiameterLength);
		quadrangleArea = currentQuadrangleArea;
	}

	/**
	 * Die Methode calcSquareAndDiameter benutzt diese Methode, um die Variablen,
	 * die den aktuell gr&ouml;&szlig;ten Durchmesser und die L&auml;nge des aktuell
	 * gr&ouml;&szlig;ten Durchmessers speichern, mit den &uuml;bergebenen
	 * Parametern zu aktualisieren.
	 * 
	 * @param nextAntipodeA     Der Kandidat f&uuml;r einen Eckpunkt des
	 *                          gr&ouml;&szlig;ten Durchmessers.
	 * @param nextAntipodeC     Der Kandidat f&uuml;r einen Eckpunkt des
	 *                          gr&ouml;&szlig;ten Durchmessers.
	 * @param toCompareDiameter Der Kandidat f&uuml;r die L&auml;nge des
	 *                          gr&ouml;&szlig;ten Durchmessers.
	 * @return Der Kandidat f&uuml;r die L&auml;nge des aktuell Durchmessers.
	 */
	private double refreshCurrentDiameter(DataPoint nextAntipodeA, DataPoint nextAntipodeC, double toCompareDiameter) {
		double currentDiameterLength;
		diameterA.setX(nextAntipodeA.getX());
		diameterA.setY(nextAntipodeA.getY());
		diameterC.setX(nextAntipodeC.getX());
		diameterC.setY(nextAntipodeC.getY());
		currentDiameterLength = toCompareDiameter;
		return currentDiameterLength;
	}

	/**
	 * Die Methode calcSquareAndDiameter benutzt diese Methode, um die Variablen,
	 * die das aktuell gr&ouml;&szlig;te Viereck und dessen Fl&auml;cheninhalt
	 * speichern, mit den &uuml;bergebenen Parametern zu aktualisieren.
	 * 
	 * @param nextAntipodeA           Der Kandidat f&uuml;r einen Eckpunkt
	 *                                gr&ouml;&szlig;ten Vierecks.
	 * @param nextAntipodeC           Der Kandidat f&uuml;r einen Eckpunkt
	 *                                gr&ouml;&szlig;ten Vierecks.
	 * 
	 * @param highestB                Der Kandidat f&uuml;r einen Eckpunkt
	 *                                gr&ouml;&szlig;ten Vierecks.
	 * @param highestD                Der Kandidat f&uuml;r einen Eckpunkt
	 *                                gr&ouml;&szlig;ten Vierecks.
	 * @param toCompareQuadrangleArea Der Kandidat f&uuml;r die Fl&auml;cke
	 *                                gr&ouml;&szlig;ten Vierecks.
	 * @return Die Fl&auml;che des aktuell gr&ouml;&szlig;ten Vierecks.
	 */
	private double refreshCurrentQuadrangle(DataPoint nextAntipodeA, DataPoint nextAntipodeC, DataPoint highestB,
			DataPoint highestD, double toCompareQuadrangleArea) {
		double currentQuadrangleArea;
		quadrangleA.setX(nextAntipodeA.getX());
		quadrangleA.setY(nextAntipodeA.getY());
		quadrangleB.setX(highestB.getX());
		quadrangleB.setY(highestB.getY());
		quadrangleC.setX(nextAntipodeC.getX());
		quadrangleC.setY(nextAntipodeC.getY());
		quadrangleD.setX(highestD.getX());
		quadrangleD.setY(highestD.getY());
		currentQuadrangleArea = toCompareQuadrangleArea;
		return currentQuadrangleArea;
	}

	// sets the ListIterator so that the next call of next() gives the next
	// antipodes
	/**
	 * Die Methode erh&auml;lt Iteratoren, die auf die Antipode A und die Antipode C
	 * zeigen. Mithilfe der Methode angleComparison aus der Klasse GeometryCalc wird
	 * das folgende Antipodenpaar berechnet, indem nach Abschluss der Methode der
	 * Zeiger auf die Antipode A oder der Zeiger auf die Antipode C um einen
	 * Datenpunkt der konvexen H&uuml;lle gegen die Umlaufrichtung verschoben wird. F&uuml;r
	 * dieses Antipodenpaar werden mithilfe der Methode isHigher der Klasse
	 * GeometryCalc die beiden Eckpunkte b und d berechnet, die f&uuml;r das neue
	 * Antipodenpaar das gr&ouml;&szlig;te Viereck in der konvexen H&uuml;lle bilden.
	 * 
	 * @param aPointer Der Iterator, der auf die aktuelle Antipode A zeigt.
	 * @param cPointer Der Iterator, der auf die aktuelle Antipode C zeigt.
	 * @return Das n&auml;chste Antipodenpaar und die zugeh&ouml;rigen Eckpunkte des gr&ouml;&szlig;ten
	 *         Vierecks in der konvexen H&uuml;lle f&uuml;r dieses Antipodenpaar.
	 */
	private DataPoint[] getNextAntipodesAndVertices(CircleIterator aPointer, CircleIterator cPointer) {
		DataPoint oldAntipodeA = aPointer.getNextPoint();
		DataPoint oldAntipodeC = cPointer.getNextPoint();
		DataPoint afterA = aPointer.readOnlyNextPoint();
		DataPoint afterC = cPointer.readOnlyNextPoint();
		// invert the angle because of inverted screen coordinates
		long angle = GeometryCalc.angleComparison(oldAntipodeA, afterA, oldAntipodeC, afterC);

		if (angle > 0) {
			cPointer.getPreviousPoint();
		}
		if (angle < 0) {
			aPointer.getPreviousPoint();
		}

		DataPoint newA = aPointer.readOnlyNextPoint();
		DataPoint newC = cPointer.readOnlyNextPoint();
		CircleIterator bPointer = new CircleIterator(copyOfConvexHull, aPointer.nextIndex());
		DataPoint b = bPointer.getNextPoint();
		DataPoint afterB = bPointer.getNextPoint();
		while (GeometryCalc.isHigher(newC, newA, afterB, b)) {
			b = afterB;
			afterB = bPointer.getNextPoint();
		}
		CircleIterator dPointer = new CircleIterator(copyOfConvexHull, cPointer.nextIndex());
		DataPoint d = dPointer.getNextPoint();
		DataPoint afterD = dPointer.getNextPoint();

		while (GeometryCalc.isHigher(newA, newC, afterD, d)) {
			d = afterD;
			afterD = dPointer.getNextPoint();
		}
		DataPoint[] nextAntipodesAndBAndD = new DataPoint[4];
		nextAntipodesAndBAndD[0] = newA;
		nextAntipodesAndBAndD[1] = b;
		nextAntipodesAndBAndD[2] = newC;
		nextAntipodesAndBAndD[3] = d;

		return nextAntipodesAndBAndD;
	}

	/**
	 * Wird von einem Exemplar der Klasse DataModel aufgerufen, um die L&auml;nge des
	 * gr&ouml;&szlig;ten Durchmessers der konvexen H&uuml;lle zu lesen, welches bei Initialisierung
	 * eines Exemplars dieser Klasse bereits berechnet wird.
	 * 
	 * @return Die L&auml;nge des gr&ouml;&szlig;ten Durchmessers der konvexen H&uuml;lle
	 */
	double getDiameterLenght() {
		return diameterLength;
	}

	/**
	 * Wird von einem Exemplar der Klasse DataModel aufgerufen, um den linken und
	 * rechten Eckpunkt des gr&ouml;&szlig;ten Durchmessers der konvexen H&uuml;lle zu lesen, welche
	 * bei Initialisierung eines Exemplars dieser Klasse bereits berechnet wurden.
	 * 
	 * @return Der linke und rechte Eckpunkt des gr&ouml;&szlig;ten Durchmessers der konvexen
	 *         H&uuml;lle
	 */
	int[][] getDiameter() {
		int[][] resultTuple = new int[2][2];
		resultTuple[0][0] = (int) diameterA.getX();
		resultTuple[0][1] = (int) diameterA.getY();
		resultTuple[1][0] = (int) diameterC.getX();
		resultTuple[1][1] = (int) diameterC.getY();
		return resultTuple;
	}

	/**
	 * Wird von einem Exemplar der Klasse DataModel aufgerufen, um die Eckpunkte des
	 * gr&ouml;&szlig;ten Vierecks in der konvexen H&uuml;lle zu lesen, welche bei Initialisierung
	 * eines Exemplars dieser Klasse bereits berechnet wurden.
	 * 
	 * @return Die Eckpunkte des gr&ouml;&szlig;ten Vierecks in der konvexen H&uuml;lle
	 */
	int[][] getQuadrangle() {
		int[][] resultTuple = new int[4][2];
		resultTuple[0][0] = (int) quadrangleA.getX();
		resultTuple[0][1] = (int) quadrangleA.getY();
		resultTuple[1][0] = (int) quadrangleB.getX();
		resultTuple[1][1] = (int) quadrangleB.getY();
		resultTuple[2][0] = (int) quadrangleC.getX();
		resultTuple[2][1] = (int) quadrangleC.getY();
		resultTuple[3][0] = (int) quadrangleD.getX();
		resultTuple[3][1] = (int) quadrangleD.getY();
		return resultTuple;
	}

	/**
	 * Wird von einem Exemplar der Klasse DataModel aufgerufen, um die Fl&auml;che des
	 * gr&ouml;&szlig;ten Vierecks der konvexen H&uuml;lle zu lesen, welche bei Initialisierung
	 * eines Exemplars dieser Klasse bereits berechnet wurden.
	 * 
	 * @return Die Fl&auml;che des gr&ouml;&szlig;ten Vierecks der konvexen H&uuml;lle
	 */
	double getQuadrangleArea() {
		return quadrangleArea;
	}
}
