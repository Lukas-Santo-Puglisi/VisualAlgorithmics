package propra22.q5395950.model;

import java.util.ArrayList;
import java.util.ListIterator;

import propra22.q5395950.generalUtility.ModelI;
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
public class Model implements ModelI {
	/**
	 * Ein Exemplar der Klasse DataPoints zur Verwaltung der aktuellen Menge der
	 * Datenpunkte.
	 */
	private DataPoints currentDataPoints = new DataPoints();
	/**
	 * Ein Exemplar der Klasse modelHistory zur Verwaltung der Datenpunkte, die
	 * w&auml;hrend der Verwendung Applikation durch den User entstehen. Dieses
	 * Exemplar implementiert die undo und redo Funktion der Applikation.
	 */
	private ModelHistory modelHistory = new ModelHistory();
	/**
	 * Bei Erzeugung des Datenmodells wird die leere Datenmenge als Ausgangspunkt 
	 * f&uuml;r die Modellhistorie abgespeichert. 
	 */
	public Model() {
		safe();
	}
	/**
	 * 
	 */
	@Override
	public ArrayList<DataPoint> getAllPoints() {
		return currentDataPoints.getAllPoints();
	}

	@Override
	public ArrayList<DataPoint> getConvexHull() {
		ConturCalc cc = new ConturCalc(currentDataPoints.getAllPoints());
		ConvexHullCalc chc = new ConvexHullCalc(cc.getContour());
		return chc.getConvexHull();
	}

	// reads in all points from the list
	@Override
	public void addPointsFromArrayList(ArrayList<DataPoint> list) {
		currentDataPoints.setAllPoints(list);
	}

	@Override
	public void addPoint(DataPoint p) {
		currentDataPoints.add(p);
	}

	@Override
	public boolean isThereNearPointInModel(long x, long y) {
		return currentDataPoints.isNear(x, y);
	}


	@Override
	public DataPoint getClosestModelPointForCoordinate(long x, long y) {
		return currentDataPoints.getNearPoint(x, y);
	}

	@Override
	public void deleteModelPointAtCoordinate(long x, long y) {
		currentDataPoints.deletePointAt(x, y);
	}

	@Override
	public void modifyDataPointInModel(DataPoint p, long x, long y) {
		currentDataPoints.setGivenPoint(p, x, y);
	}

	@Override
	// returns true if model contained duplicates
	public boolean deleteDuplicates() {
		return currentDataPoints.deleteDuplicates();
	}

	@Override
	public void clearPoints() {
		currentDataPoints = new DataPoints();
	}

	@Override
	public String toString() {
		ArrayList<DataPoint> list = currentDataPoints.getAllPoints();
		StringBuilder sb = new StringBuilder();
		for (DataPoint p : list) {
			String x = Integer.toString((int) p.getX());
			String y = Integer.toString((int) p.getY());
			sb.append("(" + x + "," + y + ")\r\n");
		}
		return sb.toString();
	}

	@Override
	public int[][] getDiameter() {
		ArrayList<DataPoint> convexHull = getConvexHull();
		SquareAndDiameterCalc dc = new SquareAndDiameterCalc(convexHull);
		return dc.getDiameter();
	}

	@Override
	public double getDiameterLength() {
		ArrayList<DataPoint> convexHull = getConvexHull();
		SquareAndDiameterCalc dc = new SquareAndDiameterCalc(convexHull);
		return dc.getDiameterLenght();
	}

	@Override
	public int[][] getQuadrangle() {
		ArrayList<DataPoint> convexHull = getConvexHull();
		SquareAndDiameterCalc dc = new SquareAndDiameterCalc(convexHull);
		return dc.getQuadrangle();
	}

	@Override
	public double getQuadrangleArea() {
		ArrayList<DataPoint> convexHull = getConvexHull();
		SquareAndDiameterCalc dc = new SquareAndDiameterCalc(convexHull);
		return dc.getQuadrangleArea();
	}


	// the safe, undo, cleanModelHistory, redo method implement the undo/redo
	// function of the application
	@Override
	public void safe() {
		modelHistory.safe(currentDataPoints.getAllPoints());
	}

	public void undo() {
		ArrayList<DataPoint> previousModel = modelHistory.getPreviousModel();
		currentDataPoints.setAllPoints(previousModel);
	}

	@Override
	public void cleanModelHistory() {
		modelHistory = new ModelHistory();

	}

	@Override
	public void redo() {
		ArrayList<DataPoint> nextModel = modelHistory.getNextModel();
		currentDataPoints.setAllPoints(nextModel);
	}
	@Override
	public boolean isEmpty() {
		return currentDataPoints.size() == 0;
	}

}
