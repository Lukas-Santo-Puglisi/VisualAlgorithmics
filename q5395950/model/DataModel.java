package propra22.q5395950.model;

import java.util.ArrayList;

import propra22.q5395950.generalUtility.DataModelI;
import propra22.q5395950.generalUtility.DataPoint;

/**
 * Das Model implementiert das Interface ModelI, welches den kontrollierten
 * Zugriff von a&szlig;en auf das Datenmodell sicherstellt. Das Model hat sowohl
 * ein Exemplar der Klasse DataPoints zur Verwaltung der aktuellen Menge der
 * Datenpunkte, als auch ein Exemplar der Klasse ModelHistory zur Verwaltung der
 * Datenpunkte, die w&auml;hrend der Verwendung Applikation durch den User
 * entstehen.
 * 
 * @author LukasSanto Puglisi
 */
public class DataModel implements DataModelI {
	/**
	 * Ein Exemplar der Klasse DataPoints zur Verwaltung der aktuellen Menge der
	 * Datenpunkte.
	 */
	private CurrentDataPointContainer currentDataPointContainer = new CurrentDataPointContainer();
	/**
	 * Ein Exemplar der Klasse modelHistory zur Verwaltung der Datenpunkte, die
	 * w&auml;hrend der Verwendung Applikation durch den User entstehen. Dieses
	 * Exemplar implementiert die undo und redo Funktion der Applikation.
	 */
	private ModelHistory modelHistory = new ModelHistory();

	private boolean dataChangedSinceLastCreationOfCalcs = false;
	private SquareAndDiameterCalc dc;
	private ConvexHullCalc convexHullCalc;

	/**
	 * Bei Erzeugung des Datenmodells wird die leere Datenmenge als Ausgangspunkt
	 * f&uuml;r die Modellhistorie abgespeichert.
	 */
	public DataModel() {
		safe();
	}

	/**
	 * F&uuml;r das Speichern der aktuellen Punktmenge in einer externen Datei wie
	 * f&uuml;r das Zeichnen der aktuellen Datenpunktmenge kann &uuml;ber diese
	 * Methode die aktuelle Datenpunktmenge des Datenmodells gelesen werden.
	 * 
	 * @return Die aktuelle Datenpunktmenge des Datenmodells.
	 */
	@Override
	public ArrayList<DataPoint> getAllPoints() {
		return currentDataPointContainer.getAllPoints();
	}

	/**
	 * Berechnet Gibt die konvexe H&uuml;lle der aktuellen Punktmenge des
	 * Datenmodells zur&uuml;ck. Die Berechnung der Kontur ist f&uuml;r das Exemplar
	 * dieser Klasse nicht sichtbar.
	 * 
	 * @return Die konvexe H&uuml;lle des Datenmodells.
	 */
	@Override
	public ArrayList<DataPoint> getConvexHull() {
		if (dataChangedSinceLastCreationOfCalcs) {
			recalculateCalculators();
		}
		return convexHullCalc.getConvexHull();
	}

	private void recalculateCalculators() {
		convexHullCalc = new ConvexHullCalc(currentDataPointContainer.getAllPoints());
		dc = new SquareAndDiameterCalc(convexHullCalc.getConvexHull());
		dataChangedSinceLastCreationOfCalcs = false;
	}

	/**
	 * Entfernt alle Datenpunkte aus der aktuellen Datenpunktmenge und f&uuml;gt die
	 * Datenpunkte in der &uuml;bergebenen Liste der aktuellen Datenpunktmenge
	 * hinzu.
	 * 
	 * @param newDataPoints Die Datenpunkte, die die neue Datenpunktmenge
	 *                      darstellen.
	 */
	@Override
	public void setAllPointsFromNew(ArrayList<DataPoint> newDataPoints) {
		currentDataPointContainer.setAllPointsFromNew(newDataPoints);
		dataChangedSinceLastCreationOfCalcs = true;

	}

	/**
	 * F&uuml;gt der aktuellen Datenpunktmenge einen Datenpunkt hinzu.
	 * 
	 * @param dataPoint Der hinzuzuf&uuml;gende Datenpunkt.
	 */
	@Override
	public void addPoint(DataPoint dataPoint) {
		currentDataPointContainer.add(dataPoint);
		dataChangedSinceLastCreationOfCalcs = true;
	}

	/**
	 * Ermittelt, ob sich in der euklidischen N&auml;he der in den Parametern
	 * angegebenen Koordinate ein in der Datenpunktmenge gespeicherter Punkt
	 * befindet. Der Schwellwert wird hierbei auf 10 festgesetzt.
	 * 
	 * @param x x Koordinate bei der gesucht wird.
	 * @param y y Koordinate bei der gesucht wird.
	 * @return true, wenn sich in der N&auml;he der Koordinate ein Datenpunkt
	 *         befindet.
	 */
	@Override
//	public boolean isThereNearPoint(long x, long y) {
//		return currentDataPointContainer.isThereNearPoint(x, y);
//	}
	
	public boolean isThereNearPoint(int x, int y) {
		return currentDataPointContainer.isThereNearPoint(x, y);
	}

	/**
	 * Gibt dem Datenmodell den Datenpunkt aus der aktuellen Datenmenge zur&uuml;ck,
	 * der am n&auml;hesten bei der Koordinate (x, y) liegt.
	 * 
	 * @param x x Koordinate bei der nach dem n&auml;hesten Datenpunkt gesucht wird.
	 * @param y y Koordinate bei der nach dem n&auml;hesten Datenpunkt gesucht wird.
	 * @return Der Datenpunkt aus der aktuellen Datenmenge, der am n&auml;hesten bei
	 *         der Koordinate (x, y) liegt.
	 */
	@Override
//	public DataPoint getNearPoint(long x, long y) {
//		return currentDataPointContainer.getNearPoint(x, y);
//	}
	public DataPoint getNearPoint(int x, int y) {
		return currentDataPointContainer.getNearPoint(x, y);
	}

	/**
	 * Ein Datenpunkt bei der in den Parametern &uuml;bergebenen Koordinate wird aus
	 * der aktuellen Datenpunktmenge gel&ouml;scht.
	 * 
	 * @param x x Koordinate, bei der der zu l&ouml;schende Datenpunkt liegt.
	 * @param y y Koordinate, bei der der zu l&ouml;schende Datenpunkt liegt.
	 */
	@Override
//	public void deletePointAt(long x, long y) {
//		currentDataPointContainer.deletePointAt(x, y);
//		dataChangedSinceLastCreationOfCalcs = true;
//	}
	public void deletePointAt(int x, int y) {
		currentDataPointContainer.deletePointAt(x, y);
		dataChangedSinceLastCreationOfCalcs = true;
	}

	/**
	 * Setzt die aktuellen Koordinaten des &uuml;bergebenen Datenpunkts der
	 * aktuellen Datenpuntkmenge auf die &uuml;bergebenen Koordinaten.
	 * 
	 * @param dataPoint Der zu modifizierende Datenpunkt.
	 * @param x         x Koordinate, die x Koordinate zu modifizierende
	 *                  Datenpunktes &uuml;berschreibt.
	 * @param y         y Koordinate, die y Koordinate zu modifizierende
	 *                  Datenpunktes &uuml;berschreibt.
	 */
	@Override
//	public void setGivenPoint(DataPoint dataPoint, long x, long y) {
//		currentDataPointContainer.setGivenPoint(dataPoint, x, y);
//		dataChangedSinceLastCreationOfCalcs = true;
//	}
	public void setGivenPoint(DataPoint dataPoint, int x, int y) {
		currentDataPointContainer.setGivenPoint(dataPoint, x, y);
		dataChangedSinceLastCreationOfCalcs = true;
	}

	/**
	 * &Uuml;berpr&uuml;ft, ob sich in der aktuellen Datenpunktmenge des
	 * Datenmodells Duplikate befinden. Diese werden gel&ouml;scht.
	 * 
	 * @return true, wenn sich in der Datenpunktmenge Duplikate befanden.
	 */
	@Override
	public boolean deleteDuplicates() {
		dataChangedSinceLastCreationOfCalcs = true;
		return currentDataPointContainer.deleteDuplicates();
	}

	/**
	 * Resettet die aktuelle Datenpunktmenge des Datenmodells.
	 */
	@Override
	public void clearPoints() {
		dataChangedSinceLastCreationOfCalcs = true;
		currentDataPointContainer = new CurrentDataPointContainer();
	}

	/**
	 * Transformiert die aktuelle Punktmenge des Datenmodells in eine String
	 * Repr&auml;sentation. Diese Methode kann f&uuml;r Debugging Zwecke benutzt
	 * werden.
	 * 
	 * @return Eine String-Repr&auml;sentation des Datenmodells.
	 */
	@Override
	public String toString() {
		ArrayList<DataPoint> list = currentDataPointContainer.getAllPoints();
		StringBuilder sb = new StringBuilder();
		for (DataPoint p : list) {
			String x = Integer.toString((int) p.getX());
			String y = Integer.toString((int) p.getY());
			sb.append("(" + x + "," + y + ")\r\n");
		}
		return sb.toString();
	}

	/**
	 * Gibt den gr&ouml;&szlig;ten Durchmesser in der konvexen H&uuml;lle der
	 * aktuellen Datenpunktmenge zur&uuml;ck, also zwei Punkte. Liefert null
	 * zur&uuml;ck, falls die &Uuml;berpr&uuml;fung des Durchmessers noch nicht
	 * durchgef&uuml;hrt werden soll.
	 * 
	 * @return Der Durchmesser, ein Array mit zwei Punkten.
	 */
	@Override
	public int[][] getDiameter() {
		if (dataChangedSinceLastCreationOfCalcs) {

			dataChangedSinceLastCreationOfCalcs = false;
			return dc.getDiameter();
		} else {

			return dc.getDiameter();
		}

//		dc = new SquareAndDiameterCalc(currentDataPointContainer.getAllPoints());
//		return dc.getDiameter();
	}

	/**
	 * Gibt die L&auml;nge des Durchmessers (gr&ouml;&szlig;ter Abstand) in der
	 * Punktmenge zur&uuml;ck. Liefert Double.NaN zur&uuml;ck, falls die
	 * &uuml;berpr&uuml;fung noch nicht durchgef&uuml;hrt werden soll.
	 * 
	 * @return Durchmesser L&auml;nge.
	 */
	@Override
	public double getDiameterLength() {
		if (dataChangedSinceLastCreationOfCalcs) {
			recalculateCalculators();
		}

		return dc.getDiameterLenght();
//		dc = new SquareAndDiameterCalc(currentDataPointContainer.getAllPoints());
//		return dc.getDiameterLenght();
	}

	/**
	 * Gibt das gr&ouml;&szlig;te eingeschlossene Viereck zur&uuml;ck. Liefert null
	 * zur&uuml;ck, falls die &uuml;berpr&uuml;fung des Viereck noch nicht
	 * durchgef&uuml;hrt werden soll.
	 * 
	 * @return Das gr&ouml;&szlig;te Viereck, ein Array mit vier Punkten.
	 */
	@Override
	public int[][] getQuadrangle() {
		if (dataChangedSinceLastCreationOfCalcs) {
			recalculateCalculators();
		}

		return dc.getQuadrangle();

	}

	/**
	 * Gibt die Fl&auml;che des gr&ouml;&szlig;ten Vierecks zur&uuml;ck. Liefert
	 * Double.NaN zur&uuml;ck, falls die &uuml;berpr&uuml;fung noch nicht
	 * durchgef&uuml;hrt werden soll.
	 * 
	 * @return Viereckfl&auml;che.
	 */
	@Override
	public double getQuadrangleArea() {
		if (dataChangedSinceLastCreationOfCalcs) {
			recalculateCalculators();
		}
		return dc.getQuadrangleArea();

//		dc = new SquareAndDiameterCalc(currentDataPointContainer.getAllPoints());
//		return dc.getQuadrangleArea();
	}

	/**
	 * Speichert die aktuelle Datenmenge des Datenmodells in der Historie der
	 * Datenmengen des Datenmodells ab. Diese Methode muss werden, wenn
	 * &auml;nderungen an der aktuellen Datenpunktmenge des Datenmodells
	 * durchgef&uuml;hrt werden.
	 */
	@Override
	public void safe() {
		modelHistory.safe(currentDataPointContainer.getAllPoints());
	}

	/**
	 * Der UndoActionListener ruft diese Methode auf, um die durch die undo
	 * Funktionalit&auml;t determinierten &auml;nderungen im Datenmodell zu
	 * veranlassen.
	 */
	public void undo() {
		dataChangedSinceLastCreationOfCalcs = true;
		ArrayList<DataPoint> previousModel = modelHistory.getPreviousModel();
		currentDataPointContainer.setAllPointsFromNew(previousModel);
	}

	/**
	 * Der RedoActionListener ruft diese Methode auf, um die durch die redo
	 * Funktionalit&auml;t determinierten &auml;nderungen im Datenmodell zu
	 * veranlassen.
	 */
	@Override
	public void redo() {
		dataChangedSinceLastCreationOfCalcs = true;
		ArrayList<DataPoint> nextModel = modelHistory.getNextModel();
		currentDataPointContainer.setAllPointsFromNew(nextModel);
	}

	/**
	 * L&ouml;scht die Historie der Datenmengen des Datenmodells. Diese
	 * Funktionalit&auml;t wird beim Klicken auf das JMenuItem neu der GUI
	 * ausgel&ouml;st.
	 */
	@Override
	public void cleanModelHistory() {
		modelHistory = new ModelHistory();
	}

	/**
	 * Pr&uuml;ft ab, ob sich in der gegenw&auml;rtigen Punktmenge des Datenmodells
	 * Datenpunkte befinden.
	 * 
	 * @return true, falls sich in der gegenw&auml;rtigen Punktmenge des
	 *         Datenmodells keine Datenpunkte befinden.
	 */
	@Override
	public boolean isEmpty() {
		return currentDataPointContainer.size() == 0;
	}

}
