package propra22.q5395950.generalUtility;

import java.util.ArrayList;

/**
 * Das ModelI ist ein Interface, das zur Manipulation des Datenmodells der
 * Applikation dient. Es wird von der Klasse Model implementiert.
 * 
 * @author LukasSanto Puglisi
 */

public interface ModelI {
	/**
	 * Liefert alle Datenpunkte der aktuellen Punktmenge.
	 * 
	 * @return Alle Datenpunkte der aktuellen Punktmenge.
	 */
	ArrayList<DataPoint> getAllPoints();

	/**
	 * Liefert die konvexe Hülle der aktuellen Punktmenge.
	 * 
	 * @return Die konvexe Hülle der aktuellen Punktmenge.
	 */
	ArrayList<DataPoint> getConvexHull();

	/**
	 * Fügt die in der ArrayList list gespeicherten Punkte der aktuellen Punktmenge
	 * hinzu. Zuvor werden alle alten Datenpunkte gelöscht.
	 * 
	 * @param list
	 */
	void addPointsFromArrayList(ArrayList<DataPoint> list);

	/**
	 * Fügt der aktuellen Punktmenge einen Datenpunkt hinzu.
	 * 
	 * @param p Der hinzuzufügende Datenpunkt.
	 */
	void addPoint(DataPoint p);

	/**
	 * Überprüft ob in der Nähe der durch x und y definierten Koordinate ein
	 * Datenpunkt gespeichert ist.
	 * 
	 * @param x x Koordinate bei der gesucht wird.
	 * @param y y Koordinate bei der gesucht wird.
	 * @return true, falls sich in der Nähe der Koordinate ein Datenpunkt befindet.
	 */
	boolean isThereNearPointInModel(long x, long y);

	/**
	 * Liefert den nähesten Datenpunkt in der aktuellen Punktmenge des Datenmodells.
	 * Diese Methode wird vom MouseEventController benutzt, um die Nutzereingaben
	 * auf der Zeichenfläche im Datenmodell umzusetzen.
	 * 
	 * @param x x-Koordinate bei der nach dem nähesten Datenpunkt gesucht wird.
	 * @param y y-Koordinate bei der nach dem nähesten Datenpunkt gesucht wird.
	 * @return Der näheste Datenpunkt.
	 */
	DataPoint getClosestModelPointForCoordinate(long x, long y);

	/**
	 * Löscht den Punkt in der aktuellen Punktmenge des Datenmodells bei Koordinate
	 * (x,y). Diese Methode wird vom MouseEventController benutzt, um die
	 * Nutzereingaben auf der Zeichenfläche im Datenmodell umzusetzen.
	 * 
	 * @param x x-Koordinate des zu löschenden Punkts.
	 * @param y y-Koordinate des zu löschenden Punkts.
	 */
	void deleteModelPointAtCoordinate(long x, long y);

	/**
	 * Setzt die Koordinaten des Datenpunkts p auf die Koordinate (x, y).
	 * 
	 * @param p Der zu modfizierenden Datenpunkt.
	 * @param x Die zu setzende x-Koordinate.
	 * @param y Die zu setzende y-Koordinate.
	 */
	void modifyDataPointInModel(DataPoint p, long x, long y);

	/**
	 * Löscht die aktuelle Punktmenge des Datenmodells.
	 */
	void clearPoints();

	/**
	 * Transformiert die aktuelle Punktmenge des Datenmodells in eine
	 * String-Repräsentation. Diese Methode kann für Debugging Zwecke benutzt
	 * werden.
	 * 
	 * @return Eine String-Repräsentation des Datenmodells.
	 */
	String toString();

	/**
	 * Gibt den Durchmesser zurück, also zwei Punkte. Liefert null zurück, falls die
	 * Überprüfung des Durchmessers noch nicht durchgeführt werden soll.
	 * 
	 * @return Der Durchmesser, ein Array mit zwei Punkten.
	 */
	int[][] getDiameter();

	/**
	 * Gibt die Länge des Durchmessers (größter Abstand) in der Punktmenge zurück.
	 * Liefert Double.NaN zurück, falls die Überprüfung noch nicht durchgeführt
	 * werden soll.
	 * 
	 * @return Durchmesser-Länge.
	 */
	double getDiameterLength();

	/**
	 * Gibt das größte eingeschlossene Viereck zurück. Liefert null zurück, falls
	 * die Überprüfung des Viereck noch nicht durchgeführt werden soll.
	 * 
	 * @return Das größte Viereck, ein Array mit vier Punkten.
	 */
	int[][] getQuadrangle();

	/**
	 * Gibt die Fläche des größten Vierecks zurück. Liefert Double.NaN zurück, falls
	 * die Überprüfung noch nicht durchgeführt werden soll.
	 * 
	 * @return Viereckfläche.
	 */
	double getQuadrangleArea();

	/**
	 * Der UndoActionListener ruft diese Methode auf, um die durch die
	 * undo-Funktionalität determinierten Änderungen im Datenmodell zu veranlassen.
	 * 
	 */
	void undo();

	/**
	 * Der RedoActionListener ruft diese Methode auf, um die durch die
	 * redo-Funktionalität determinierten Änderungen im Datenmodell zu veranlassen.
	 * 
	 */
	void redo();

	/**
	 * Speichert die aktuelle Datenmenge des Datenmodells in der Historie der
	 * Datenmengen des Datenmodells ab. Diese Methode wird durch die Methoden
	 * aufgerufen, die Änderungen an der Datenmenge durchführen.
	 */
	void safe();

	/**
	 * Löscht die Historie der Datenmengen des Datenmodells. Diese Funktionalität
	 * wird beim Klicken auf das JMenuItem neu der GUI ausgelöst.
	 */
	void cleanModelHistory();

	/**
	 * Löscht alle Duplikate innerhalb der aktuellen Datenmenge des Datenmodells.
	 * Duplikate können beim Generieren zufälliger Datenpunkte sowie beim Einlesen
	 * von Daten aus externen Dateien in die Datenmenge gelangen.
	 * 
	 * @return true, falls Duplikate gefunden und gelöscht wurden.
	 */
	boolean deleteDuplicates();

	/**
	 * Prüft ab, ob sich in der gegenwärtigen Punktmenge des Datenmodells
	 * Datenpunkte befinden.
	 * 
	 * @return true, falls sich in der gegenwärtigen Punktmenge des Datenmodells
	 *         keine Datenpunkte befinden.
	 */
	boolean isEmpty();
}
