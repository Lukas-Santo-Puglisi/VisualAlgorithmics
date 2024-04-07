package propra22.q5395950.model;

import java.util.ArrayList;

/**
 * Ein Exemplar dieser Klasse verwaltet die Historie der Daten, die w&auml;hrend der Benutzung 
 * der Applikation entstanden sind. 
 * 
 * @author Lukas Santo Puglisi
 */
import java.util.ListIterator;

import propra22.q5395950.generalUtility.DataPoint;

public class ModelHistory {
	/**
	 * In dieser Liste werden die Daten gespeichert. Jeder ArrayList Eintrag
	 * entspricht selbst einer ArrayList, die ein Datenmodell zu einem vergangenen
	 * Zeitraum repr&auml;sentiert.
	 */
	private ArrayList<ArrayList<DataPoint>> modelHistory = new ArrayList<ArrayList<DataPoint>>();
	/**
	 * Mittels dieses Iterators merkt sich ein Exemplar dieser Klasse, welches
	 * Modell angezeigt werden soll, wenn der User mittels der Undo beziehungsweise
	 * Redo Funktion durch die vergangenen Modelle iteriert.
	 */
	private ListIterator<ArrayList<DataPoint>> modelHistoryIterator;
	/**
	 * Mittels dieser Variable merkt sich ein Exemplar dieser Klasse, ob nach der
	 * letzten Datenver&auml;nderung bereits dieUndo Funktion genutzt wurde.
	 */
	private boolean firstUndo = true;
	/**
	 * Mittels dieser Variable merkt sich ein Exemplar dieser Klasse, ob nach der
	 * letzten Datenver&auml;nderung bereits dieUndo Funktion genutzt wurde.
	 */
	private boolean firstRedo = true;

	/**
	 * Bei Konstruktion des Modells und nach jeder Ver&auml;nderung der Daten
	 * m&uuml;ssen die Daten aus dem aktuellen Datenmodell in der Historie
	 * gespeichert werden.
	 * 
	 * @param currentModel Das
	 */
	public void safe(ArrayList<DataPoint> currentModel) {
		// after every safe operation the modelHistoryIterator must be reconstructed
		ListIterator<DataPoint> itr = currentModel.listIterator();
		ArrayList<DataPoint> copyOfCurrentModel = new ArrayList<DataPoint>();
		while (itr.hasNext()) {
			DataPoint p = itr.next();
			DataPoint pCopy = new DataPoint(p.getX(), p.getY());
			copyOfCurrentModel.add(pCopy);
		}
		if (modelHistoryIterator != null) {
			resetModelHistory();
		}
		modelHistory.add(copyOfCurrentModel);
		firstUndo = true;

	}

	/**
	 * Wenn der Nutzer die Undo Funktion genutzt hat und dem Modell danach weitere
	 * Daten hinzuf&uuml;gt, m&uuml;ssen alle Daten, die Datenmodelle nach dem Undo
	 * repr&auml;sentieren, gel&ouml;scht werden. Anschlie&szlig;end wird der
	 * modelHistoryIterator auf null gesetzt sowie firstUndo und firstRedo auf true
	 * gesetzt, um zu signalisieren, dass die Redo und Undo Funktion nach der
	 * letzten Daten&auml;nderung nicht genutzt wurde.
	 */
	private void resetModelHistory() {
		if (modelHistoryIterator.hasNext()) {
			modelHistoryIterator.next();
		}
		while (modelHistoryIterator.hasNext()) {
			modelHistoryIterator.next();
			modelHistoryIterator.remove();
		}
		modelHistoryIterator = null;
		firstUndo = true;
		firstRedo = true;
	}

	/**
	 * Diese Methode liefert dem Modell die Daten, die das letzte
	 * zur&uuml;ckliegende Datenmodell repr&auml;sentieren. Diese Methode wird zur
	 * Implementation der undo Funktion der Applikation genutzt.
	 * 
	 * @return Die Daten, die das letzte zur&uuml;ckliegende Datenmodell
	 *         repr&auml;sentieren.
	 */

	public ArrayList<DataPoint> getPreviousModel() {
		if (firstUndo && (firstRedo)) {
			firstUndo = false;
			modelHistoryIterator = modelHistory.listIterator(modelHistory.size());
			// we directly want to get the previous model not the last and newest model
			modelHistoryIterator.previous();
		}
		if (firstUndo && (!firstRedo)) {
			modelHistoryIterator.previous();
			firstRedo = true;
			firstUndo = false;
		}

		if (modelHistoryIterator.hasPrevious()) {
			return modelHistoryIterator.previous();
		} else {
			return modelHistory.get(0);
		}
	}

	/**
	 * Wurde bereits die Undo Funktion benutzt, liefert diese Methode die Daten, die
	 * in der Historie der Datenmodelle das n&auml;chste Datenmodell
	 * repr&auml;sentieren. Ansonsten liefert diese Funktion die Daten, die das
	 * neueste und aktuellste Datenmodell repr&auml;sentieren.
	 * 
	 * @return Die Daten, die in der Historie der Datenmodelle das n&auml;chste
	 *         Datenmodell repr&auml;sentieren.
	 */
	public ArrayList<DataPoint> getNextModel() {
		// es wurde keine undo Operation ausgef&uuml;hrt
		if (firstRedo && firstUndo) {
			return modelHistory.get(modelHistory.size() - 1);
		}

		if (firstRedo && !firstUndo) {
			modelHistoryIterator.next();
			firstUndo = true;
			firstRedo = false;
		}

		if (modelHistoryIterator.hasNext()) {
			return modelHistoryIterator.next();
		} else {
			return modelHistory.get(modelHistory.size() - 1);
		}
	}

	/**
	 * Mittels dieser Methode kann die Modellhistorie in der Konsole angezeigt
	 * werden. Diese Methode ist f&uuml;r den Entwickler zum debuggen gedacht und
	 * f&uuml;r den User nicht erreichbar.
	 */
	@Override
	public String toString() {
		int i = 1;
		StringBuilder sb = new StringBuilder();
		for (ArrayList<DataPoint> model : modelHistory) {
			sb.append("Model " + i + " ");
			for (DataPoint p : model) {
				sb.append(p.toString());
			}
			sb.append("\n");
			i++;
		}
		return sb.toString();
	}

}
