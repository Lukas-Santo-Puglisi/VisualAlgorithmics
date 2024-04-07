package propra22.q5395950.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import propra22.q5395950.generalUtility.DataModelI;
import propra22.q5395950.generalUtility.UserInterface;

/**
 * Ein Exemplar dieser Klasse wird am Undo Button der GUI angemeldet. Mit der
 * Undo Funktion k&ouml;nnen Undos in der Historie der Datenmenge im Datenmodell
 * r&uuml;ckg&auml;ngig gemacht werden. An den gespeicherten dargestellten Daten
 * der Datenhistorie des Datenmodells werden keine &Auml;nderungen vorgenommen.
 * Dieses Exemplar delegiert die Ausf&uuml;hrung der Undo Funktion der
 * Applikation weiter an das Modell, welche die eigentliche Implementation an
 * die darauf spezialisierte Klasse ModellHistory weiterdelegiert.
 * 
 * @author LukasSanto Puglisi
 */
public class UndoActionListener implements ActionListener {
	/**
	 * Das model delegiert die die Ausf&uuml;hrung der Undo Funktion der Applikation
	 * weiter an die darauf spezialisierte Klasse ModellHistory.
	 */

	DataModelI model;
	/**
	 * Das userInterface soll nachdem die aktuelle Datenmenge im model
	 * ver&auml;ndert wurde wieder eine konsistente Sicht darstellen.
	 */
	UserInterface userInterface;

	/**
	 * Ein Exemplar dieser Klasse wird von der GUI erzeugt und am Undo Button
	 * angemeldet.
	 * 
	 * @param userInterface Die Referenz auf die GUI.
	 * @param model         Die Referenz auf das Datenmodell.
	 */
	public UndoActionListener(UserInterface userInterface, DataModelI model) {
		this.userInterface = userInterface;
		this.model = model;
	}

	/**
	 * Sobald der JButton undo an der GUI durch den User bet&auml;tigt wurde, wird
	 * das model dazu aufgefordert die vorhergehende Datenmenge der
	 * Datenmodellhistorie als aktuelle Datenmenge auszuw&auml;hlen. Dann soll das
	 * UserInterface die Sicht aktualisieren.
	 */

	@Override
	public void actionPerformed(ActionEvent e) {
		model.undo();
		userInterface.refresh();
	}

}
