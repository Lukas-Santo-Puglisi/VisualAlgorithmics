package propra22.q5395950.controller;

import java.io.*;

import propra22.q5395950.generalUtility.DataModelI;
import propra22.q5395950.generalUtility.UserInterface;

/**
 * Der Controller delegiert das Einlesen von Daten beziehungsweise das Speichern
 * von Daten im Dateisystem an den InputOutputController. Dieser benutzt
 * hierf&uuml;r die statischen Methoden der Klasse FileAccess.
 * 
 * @author LukasSanto Puglisi
 */
public class InputOutputController {
	/**
	 * Eine Referenz auf das UserInterface, um den User &uuml;ber Besonderheiten
	 * beim Einlesen der Daten zu informieren.
	 */
	private UserInterface userInterface;
	/**
	 * Eine Referenz auf das Modell wird ben&ouml;tigt, um den Austausch der Daten
	 * des Modells mit dem Dateisystem zu bewerkstelligen.
	 */
	private DataModelI model;

	/**
	 * Der Controller erzeugt ein Exemplar dieser Klasse.
	 * 
	 * @param model         Das Datenmodell der Applikation
	 * @param userInterface Die GUI der Applikation.
	 */

	InputOutputController(DataModelI model, UserInterface userInterface) {
		this.model = model;
		this.userInterface = userInterface;
	}

	/**
	 * Unter Benutzung der statischen und deutlich generischeren Methode der Klasse
	 * FileAccess werden die aktuellen Daten des Datenmodells in eine Datei des
	 * Dateisystems gespeichert.
	 * 
	 * @param file Die Datei, in die das Datenmodell gespeichert werden soll.
	 * @throws IOException Wird beim Auftreten eines I/O Errors geworfen.
	 */

	public void saveToFile(File file) throws IOException {
		FileAccess.saveToFile(file, model);
	}

	/**
	 * Unter Benutzung der statischen und deutlich generischeren Methode der Klasse
	 * FileAccess werden aus der von der GUI &uuml;bergebenen Datei die Daten in das
	 * Modell geladen.
	 * 
	 * @param f Die Datei aus der die Daten gelesen werden sollen.
	 * @throws IOException Wird beim Auftreten eines I/O Errors geworfen.
	 */

	public void readFromFileIntoModel(File f) throws IOException {
		model.clearPoints();
		boolean isCorrectInput = FileAccess.readFromFileIntoModel(f, model);
		if (!isCorrectInput) {
			userInterface.raiseNoteToUser(
					"Der Input enhält Kommentare, Duplikate oder Zeilen, die nicht dem vereinbarten Eingabeformat entsprechen. "
							+ "Kommentare werden ignoriert, inkorrekte Dateneingaben werden übersprungen und bei Duplikaten wird das "
							+ "doppelte Element entfernt.");
		}
		model.safe();
		userInterface.refresh();
	}

}
