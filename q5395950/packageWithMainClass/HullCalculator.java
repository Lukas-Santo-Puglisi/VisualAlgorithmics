package propra22.q5395950.packageWithMainClass;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import propra22.interfaces.IHullCalculator;
import propra22.q5395950.controller.FileAccess;
import propra22.q5395950.generalUtility.DataModelI;
import propra22.q5395950.generalUtility.DataPoint;
import propra22.q5395950.model.DataModel;

public class HullCalculator implements IHullCalculator {

	DataModelI dataModel = new DataModel();
	String eMail = "lsanpuglisi@gmail.com";
	String name = "Lukas-Santo Puglisi";
	String matrNr = "Q5395950";

	/**
	 * F&uuml;gt einen Punkt mit den gegebenen Koordinaten der aktuellen Punktmenge
	 * hinzu.
	 * 
	 * @param x x-Koordinate des Punkts.
	 * @param y y-Koordinate des Punkts.
	 */
	@Override
	public void addPoint(int x, int y) {
		dataModel.addPoint(new DataPoint(x, y));
	}

	/**
	 * F&uuml;gt den Inhalt des Arrays der Punktmenge hinzu. Die X-Koordinate des
	 * ersten Elements ist pointArray[0][0], Die Y-Koordinate des ersten Elements
	 * ist pointArray[0][1], usw.
	 * 
	 * @param pointArray Das Array, welches die Punktdaten enth&auml;t.
	 * @param y          y-Koordinate des Punkts.
	 */
	@Override
	public void addPointsFromArray(int[][] pointArray) {
		dataModel.clearPoints();
		for (int i = 0; i < pointArray.length; i++) {
			int x = pointArray[i][0];
			int y = pointArray[i][1];
			dataModel.addPoint(new DataPoint(x, y));
		}

	}

	/**
	 * F&uuml;gt den Inhalt der angegebenen Datei als Punkte der Punktmenge hinzu.
	 * Der volle Dateiname wird &uuml;ber fileName &uuml;bergeben.
	 * 
	 * @param fileName Der volle Dateiname der zu lesenden Datei
	 * @throws IOException Falls beim Lesen der Datei ein IO-Fehler aufgetreten ist.
	 */
	@Override
	public void addPointsFromFile(String fileName) throws IOException {
		dataModel.clearPoints();
		File file = new File(fileName);
		FileAccess.readFromFileIntoModel(file, dataModel);
	}

	/**
	 * Entfernt alle Punkte.
	 */

	@Override
	public void clearPoints() {
		dataModel.clearPoints();
	}

	/**
	 * Gibt die konvexe H&uuml;lle der Punktmenge zur&uuml;ck. Dabei ist das Array
	 * wie folgt zu verstehen: Array[0][0] ist die X-Koordinate des ersten Punkts
	 * der konvexen H&uuml;lle, array[0][1] seine die Y-Koordinate. Die weiteren
	 * Punkte sind (array[1][0],array[1][1]), (array[2][0],array[2][1]) usw.
	 * 
	 * @return Die konvexe H&uuml;lle, ein zwei dimensionaler Array.
	 */
	@Override
	public int[][] getConvexHull() {
		ArrayList<DataPoint> convexHullList = dataModel.getConvexHull();
		// der letzte Punkt aus convexHullList darf nicht an das Array &uuml;bergeben
		// werden,
		// weil dieser
		// doppelt kodiert ist.
		int obergrenze = convexHullList.size();
		if (obergrenze > 1) {
			obergrenze--;
		}
		int[][] convexHullArray = new int[obergrenze][2];
		Iterator<DataPoint> itr = convexHullList.iterator();

		for (int i = 0; i < obergrenze; i++) {
			DataPoint p = itr.next();
			convexHullArray[i][0] = (int) p.getX();
			convexHullArray[i][1] = (int) p.getY();
		}
		return convexHullArray;
	}

	/**
	 * Gibt den Durchmesser zur&uuml;ck, also zwei Punkte. Liefert null zur&uuml;ck,
	 * falls die &uuml;berpr&uuml;fung des Durchmessers noch nicht durchgef&uuml;hrt
	 * werden soll.
	 * 
	 * @return Der Durchmesser, ein Array mit zwei Punkten.
	 */
	@Override
	public int[][] getDiameter() {
		return dataModel.getDiameter();
	}

	/**
	 * Gibt die L&auml;nge des Durchmessers (gr&ouml;&szlig;ter Abstand) in der
	 * Punktmenge zur&uuml;ck. Liefert Double.NaN zur&uuml;ck, falls die
	 * &uuml;berpr&uuml;fung noch nicht durchgef&uuml;hrt werden soll.
	 * 
	 * @return Durchmesser-L&auml;nge.
	 */

	@Override
	public double getDiameterLength() {
		return dataModel.getDiameterLength();
	}

	/**
	 * Gibt die E Mail der/des bearbeitenden Studierenden zur&uuml;ck.
	 * 
	 * @return Die E Mail Adresse der/des Studierenden.
	 */

	@Override
	public String getEmail() {
		return eMail;
	}

	/**
	 * Gibt die Matrikelnummer der/des bearbeitenden Studierenden zur&uuml;ck.
	 * 
	 * @return Die Matrikelnummer der/des Studierenden.
	 */
	@Override
	public String getMatrNr() {
		return matrNr;
	}

	/**
	 * Gibt den kompletten Namen der/des bearbeitenden Studierenden zur&uuml;ck.
	 * 
	 * @return Der Name der/des Studierenden.
	 */
	@Override
	public String getName() {
		return name;
	}

	/**
	 * Gibt das gr&ouml;&szlig;te eingeschlossene Viereck zur&uuml;ck. Liefert null
	 * zur&uuml;ck, falls die &uml;berpr&uuml;fung des Viereck noch nicht
	 * durchgef&uuml;hrt werden soll.
	 * 
	 * @return Das gr&ouml;&szlig;te Viereck, ein Array mit vier Punkten.
	 */
	@Override
	public int[][] getQuadrangle() {
		return dataModel.getQuadrangle();
	}

	/**
	 * Gibt die Fl&auml;che des gr&ouml;&szlig;ten Vierecks zur&uuml;ck. Liefert
	 * Double.NaN zur&uuml;ck, falls die &Uuml;berpr&uuml;fung noch nicht
	 * durchgef&uuml;hrt werden soll.
	 * 
	 * @return Viereckfl&auml;che.
	 */
	@Override
	public double getQuadrangleArea() {
		return dataModel.getQuadrangleArea();
	}

	/**
	 * Gibt das gr&ouml;&szlig;te eingeschlossene Dreieck zur&uuml;ck. Liefert null
	 * zur&uuml;ck, falls die &Uuml;berpr&uuml;fung des Dreiecks noch nicht
	 * durchgef&uuml;hrt werden soll.
	 * 
	 * @return Das gr&ouml;&szlig;te Dreieck, ein Array mit drei Punkten.
	 */
	@Override
	public int[][] getTriangle() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Gibt die Fl&auml;che des gr&ouml;&szlig;ten Dreiecks zur&uuml;ck. Liefert
	 * Double.NaN zur&uuml;ck, falls die &Uuml;berpr&uuml;fung noch nicht
	 * durchgef&uuml;hrt werden soll.
	 * 
	 * @return Dreiecksfl&auml;che.
	 */

	@Override
	public double getTriangleArea() {
		// TODO Auto-generated method stub
		return 0;
	}

}
