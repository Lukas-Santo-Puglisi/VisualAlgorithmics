package propra22.q5395950.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import propra22.q5395950.generalUtility.DataModelI;
import propra22.q5395950.generalUtility.UserInterface;

/**
 * Ein Exemplar dieser Klasse wird am Redo Button der GUI angemeldet. Mit der
 * Redo Funktion k&ouml;nnen Undos r&uuml;ckg&auml;ngig gemacht werden. An den
 * gespeicherten aktuell dargestellten Daten der Datenhistorie des Datenmodells werden keine
 * &Auml;nderungen vorgenommen. Dieses Exemplar delegiert die Ausf&uuml;hrung
 * der Redo_Funktion der Applikation weiter an das Modell, welche die
 * eigentliche Implementation an die darauf spezialisierte Klasse ModellHistory
 * weiterdelegiert.
 * 
 * @author LukasSanto Puglisi
 */
public class RedoActionListener implements ActionListener {
	/**
	 * Das Datenmodell, an das die Implementation der Redo_Funktion weitergeleitet
	 * wird.
	 */
	DataModelI model;
	/**
	 * Das UserInterface soll die aktuellen (unver&auml;nderten) darzustellenden
	 * Daten aus dem Datenmodell lesen und dem User anzeigen.;
	 */
	UserInterface userInterface;

	/**
	 * Ein Exemplar dieser Klasse wird von der GUI erzeugt und am Redo Button
	 * angemeldet.
	 * 
	 * @param userInterface Das userInterface, das nach einer Redo Aktion seine
	 *                      Sicht &auml;ndern soll.
	 * @param model         Das Datenmodell welches den Aufruf der Redo Funktion an
	 *                      eine Instanz der ModelHistory weitergibt.
	 */

	public RedoActionListener(UserInterface userInterface, DataModelI model) {
		this.userInterface = userInterface;
		this.model = model;
	}

	/**
	 * Sobald der JButton redo an der GUI durch den User bet&auml;tigt wurde, wird
	 * das model dazu aufgefordert die n&auml;chste Datenmenge der
	 * Datenmodellhistorie als aktuelle Datenmenge auszuw&auml;hlen. Dann soll das
	 * UserInterface die Sicht aktualisieren.
	 */

	@Override
	public void actionPerformed(ActionEvent e) {
		model.redo();
		userInterface.refresh();
	}

}
