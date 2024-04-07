package propra22.q5395950.generalUtility;

import java.util.ArrayList;

/**
 * Das ModelI ist ein Interface, das zur Manipulation des Datenmodells der
 * Applikation dient. Es wird von der Klasse Model implementiert.
 * 
 * @author LukasSanto Puglisi
 */

public interface DataModelI {
	/**
	 * Liefert alle Datenpunkte der aktuellen Punktmenge.
	 * 
	 * @return Alle Datenpunkte der aktuellen Punktmenge.
	 */
	ArrayList<DataPoint> getAllPoints();

	/**
	 * Liefert die konvexe H&uuml;lle der aktuellen Punktmenge.
	 * 
	 * @return Die konvexe H&uuml;lle der aktuellen Punktmenge.
	 */
	ArrayList<DataPoint> getConvexHull();

	/**
	 * F&uuml;gt die in der ArrayList list gespeicherten Punkte der aktuellen Punktmenge
	 * hinzu. Zuvor werden alle alten Datenpunkte gel&ouml;scht.
	 * 
	 * @param list
	 */
	void setAllPointsFromNew(ArrayList<DataPoint> list);

	/**
	 * F&uuml;gt der aktuellen Punktmenge einen Datenpunkt hinzu.
	 * 
	 * @param p Der hinzuzuf&uuml;gende Datenpunkt.
	 */
	void addPoint(DataPoint p);

	/**
	 * &uuml;berpr&uuml;ft ob in der N&auml;he der durch x und y definierten Koordinate ein
	 * Datenpunkt gespeichert ist.
	 * 
	 * @param x x Koordinate bei der gesucht wird.
	 * @param y y Koordinate bei der gesucht wird.
	 * @return true, falls sich in der N&auml;he der Koordinate ein Datenpunkt befindet.
	 */
//	boolean isThereNearPoint(long x, long y);
	boolean isThereNearPoint(int x, int y);

	/**
	 * Liefert den n&auml;hesten Datenpunkt in der aktuellen Punktmenge des Datenmodells.
	 * Diese Methode wird vom MouseEventController benutzt, um die Nutzereingaben
	 * auf der Zeichenfl&auml;che im Datenmodell umzusetzen.
	 * 
	 * @param x x-Koordinate bei der nach dem n&auml;hesten Datenpunkt gesucht wird.
	 * @param y y-Koordinate bei der nach dem n&auml;hesten Datenpunkt gesucht wird.
	 * @return Der n&auml;heste Datenpunkt.
	 */
//	DataPoint getNearPoint(long x, long y);
	DataPoint getNearPoint(int x, int y);

	/**
	 * L&ouml;scht den Punkt in der aktuellen Punktmenge des Datenmodells bei Koordinate
	 * (x,y). Diese Methode wird vom MouseEventController benutzt, um die
	 * Nutzereingaben auf der Zeichenfl&auml;che im Datenmodell umzusetzen.
	 * 
	 * @param x x-Koordinate des zu l&ouml;schenden Punkts.
	 * @param y y-Koordinate des zu l&ouml;schenden Punkts.
	 */
//	void deletePointAt(long x, long y);
	void deletePointAt(int x, int y);

	/**
	 * Setzt die Koordinaten des Datenpunkts p auf die Koordinate (x, y).
	 * 
	 * @param p Der zu modfizierenden Datenpunkt.
	 * @param x Die zu setzende x-Koordinate.
	 * @param y Die zu setzende y-Koordinate.
	 */
//	void setGivenPoint(DataPoint p, long x, long y);
	void setGivenPoint(DataPoint p, int x, int y);

	/**
	 * L&ouml;scht die aktuelle Punktmenge des Datenmodells.
	 */
	void clearPoints();

	/**
	 * Transformiert die aktuelle Punktmenge des Datenmodells in eine
	 * String Repr&auml;sentation. Diese Methode kann f&uuml;r Debugging Zwecke benutzt
	 * werden.
	 * 
	 * @return Eine String Repr&auml;sentation des Datenmodells.
	 */
	String toString();

	/**
	 * Gibt den gr&ouml;&szlig;ten Durchmesser in der konvexen H&uuml;lle der aktuellen
	 * Datenpunktmenge zur&uuml;ck, also zwei Punkte. Liefert null zur&uuml;ck, falls die
	 * &uuml;berpr&uuml;fung des Durchmessers noch nicht durchgef&uuml;hrt werden soll.
	 * 
	 * @return Der Durchmesser, ein Array mit zwei Punkten.
	 */
	int[][] getDiameter();

	/**
	 * Gibt die L&auml;nge des Durchmessers (gr&ouml;&szlig;ter Abstand) in der Punktmenge zur&uuml;ck.
	 * Liefert Double.NaN zur&uuml;ck, falls die &uuml;berpr&uuml;fung noch nicht durchgef&uuml;hrt
	 * werden soll.
	 * 
	 * @return Durchmesser L&auml;nge.
	 */
	double getDiameterLength();

	/**
	 * Gibt das gr&ouml;&szlig;te eingeschlossene Viereck zur&uuml;ck. Liefert null zur&uuml;ck, falls
	 * die &uuml;berpr&uuml;fung des Viereck noch nicht durchgef&uuml;hrt werden soll.
	 * 
	 * @return Das gr&ouml;&szlig;te Viereck, ein Array mit vier Punkten.
	 */
	int[][] getQuadrangle();

	/**
	 * Gibt die Fl&auml;che des gr&ouml;&szlig;ten Vierecks zur&uuml;ck. Liefert Double.NaN zur&uuml;ck, falls
	 * die &uuml;berpr&uuml;fung noch nicht durchgef&uuml;hrt werden soll.
	 * 
	 * @return Viereckfl&auml;che.
	 */
	double getQuadrangleArea();

	/**
	 * Speichert die aktuelle Datenmenge des Datenmodells in der Historie der
	 * Datenmengen des Datenmodells ab. Diese Methode muss werden, wenn &Auml;nderungen
	 * an der aktuellen Datenpunktmenge des Datenmodells durchgef&uuml;hrt werden.
	 */
	void safe();

	/**
	 * Der UndoActionListener ruft diese Methode auf, um die durch die
	 * undo Funktionalit&auml;t determinierten &Auml;nderungen im Datenmodell zu veranlassen.
	 */
	void undo();

	/**
	 * Der RedoActionListener ruft diese Methode auf, um die durch die redo
	 * Funktionalit&auml;t determinierten &Auml;nderungen im Datenmodell zu veranlassen.
	 */
	void redo();

	/**
	 * L&ouml;scht die Historie der Datenmengen des Datenmodells. Diese Funktionalit&auml;t
	 * wird beim Klicken auf das JMenuItem neu der GUI ausgel&ouml;st.
	 */
	void cleanModelHistory();

	/**
	 * L&ouml;scht alle Duplikate innerhalb der aktuellen Datenmenge des Datenmodells.
	 * Duplikate k&ouml;nnen beim Generieren zuf&auml;lliger Datenpunkte sowie beim Einlesen
	 * von Daten aus externen Dateien in die Datenmenge gelangen.
	 * 
	 * @return true, falls Duplikate gefunden und gel&ouml;scht wurden.
	 */
	boolean deleteDuplicates();

	/**
	 * Pr&uuml;ft ab, ob sich in der gegenw&auml;rtigen Punktmenge des Datenmodells
	 * Datenpunkte befinden.
	 * 
	 * @return true, falls sich in der gegenw&auml;rtigen Punktmenge des Datenmodells
	 *         keine Datenpunkte befinden.
	 */
	boolean isEmpty();
}
