package propra22.q5395950.controller;

import java.io.File;
import java.io.IOException;
import propra22.q5395950.generalUtility.DataModelI;
import propra22.q5395950.generalUtility.UserInterface;
import propra22.q5395950.gui.MainFrame;
import propra22.q5395950.model.DataModel;

/**
 * Der Controller erzeugt das Datenmodell, die GUI und den
 * InputOutputController, startet also das Programm und koordiniert die drei
 * Komponenten der Applikation.
 * 
 * @author LukasSanto Puglisi
 */

public class Controller {
	/**
	 * Das Datenmodell der Applikation.
	 */
	private DataModelI model = new DataModel();
	/**
	 * Die GUI der Applikation.
	 */
	private UserInterface userInterface = new MainFrame(model, this);
	/**
	 * Der InputOutputController der f&uuml;r das Laden und Speichern externer
	 * beziehungsweise interner Daten zust&auml;ndig ist.
	 */
	private InputOutputController inputOutputController = new InputOutputController(model, userInterface);;

	/**
	 * Die GUI teilt dem Controller mit, dass Dateien in das Modell eingelesen
	 * werden sollen. Der Controller beauftragt dann den inputOutputController mit
	 * dem eigentlichen einlesen der Daten.
	 * 
	 * @param f Die Datei aus der eingelesen werden soll.
	 * @throws IOException
	 */
	public void readFromFileIntoModel(File f) {
		try {
			inputOutputController.readFromFileIntoModel(f);
			userInterface.refresh();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			userInterface.raiseNoteToUser("Beim Lesen der Datei ist eine unerwartete I/O Exception aufgetreten.");
			e.printStackTrace();
		}
	}

	/**
	 * Wenn der User in der Men&uuml;leiste das JMenuItem &quot;neu&quot; anklickt,
	 * wird das Modell und die ModellHistorie geresettet. Das UserInterface wird
	 * aktualisiert um einen mit dem aktuellen leeren Datenmodell kongruenten
	 * Zustand darzustellen.
	 */
	public void resetModel() {
		model.clearPoints();
		model.cleanModelHistory();
		model.safe();
		userInterface.refresh();
	}

	/**
	 * Wenn der User in der Men&uuml;leiste das JMenuItem &quot;speichern; oder
	 * &quot;speichern untern&quot; anklickt, werden die Daten in die
	 * &uuml;bergebene Datei geschrieben.
	 * 
	 * @param file Datei in die die vom Modell verwarleteten Daten geschrieben
	 *             werden sollen.
	 */

	public void saveToFile(File file) {
		try {
			inputOutputController.saveToFile(file);
		} catch (IOException e) {
			userInterface.raiseNoteToUser("Aufgrund eines I/O Fehlers konnten die Daten nicht gespeichert werden.");
			e.printStackTrace();
		}
	}

}
