package propra22.q5395950.controller;

import java.awt.event.MouseEvent;

import javax.swing.JPanel;
import javax.swing.event.*;
import propra22.q5395950.generalUtility.DataModelI;
import propra22.q5395950.generalUtility.DataPoint;

/**
 * Ein Exemplar dieser Klasse wird vom Input_Output_Device des GUIs (das
 * IODevice entspricht der Zeichenfl&auml;che der GUI) erzeugt. Ein Exemplar
 * dieser Klasse nimmt die Eingaben des Benutzers auf dem IODevice entgegen und
 * &uuml;bertr&auml;gt diese in das Datenmodell.
 * 
 * @author LukasSanto Puglisi
 */
public class MouseEventController extends MouseInputAdapter {
	/**
	 * Die Daten des Modells werden entsprechend der Eingaben des Users auf der
	 * Zeichenfl&auml;che manipuliert.
	 */
	DataModelI model;
	/**
	 * Der MouseEventController merkt sich beim Dragging eines Punktes mittels der
	 * Maus &uuml;ber die Zeichenfl&auml;che diesen Punkt.
	 */
	DataPoint currentPoint;
	/**
	 * Der MouseEventController merkt sich ob gerade mit der Maus eine Dragging
	 * Operation auf der Zeichenfl&auml;che stattfindet.
	 */
	boolean ongoingDraggingOperation;
	/**
	 * Mittels dieser Variable merkt sich der MouseEventController, ob die
	 * Manipulationen des Users auf der Zeichenfl&auml;che in Bezug auf das Modell
	 * verarbeitet werden oder nicht.
	 */
	private boolean inputDeviceOn = true;

	/**
	 * Das IODevice erzeugt sich ein Exemplar dieser Klasse und delegiert als Teil
	 * der GUI die &Uuml;bertragung der Usereingaben auf der Zeichenfl&auml;che an
	 * den MouseEventController.
	 * 
	 * @param myModel Das Datenmodell der Applikation, an das die Usereingaben
	 *                geschickt werden.
	 */
	public MouseEventController(DataModelI myModel) {
		this.model = myModel;
	}

	/**
	 * Nachdem der MouseEventController bei der Zeichenfl&auml;che angemeldet wurde,
	 * wird beim Pressen der Maus der euklidisch n&auml;chste Punkt des Datenmodells
	 * gespeichert beziehungsweise aktualisiert, wenn die N&auml;he einen bestimmten
	 * Schwellwert (im Modell festgelegt) unterschreitet.
	 */
	@Override
	public void mousePressed(MouseEvent e) {
		if (inputDeviceOn) {
//			long x = e.getX();
//			long y = e.getY();
			int x = e.getX();
			int y = e.getY();

			if (model.isThereNearPoint(x, y)) {
				currentPoint = model.getNearPoint(x, y);
			}
			JPanel drawingPanel = (JPanel) e.getSource();
			drawingPanel.repaint();
		}
	}

	/**
	 * Nachdem der MouseEventController bei der Zeichenfl&auml;che angemeldet wurde,
	 * wird beim Loslassen der Maus entweder ein Punkt gel&ouml;scht (wenn Pressen
	 * und Loslassen an derselben Stelle stattfinden und sich dort im Datenmodell
	 * ein Punkt befindet) oder ein Punkt hinzugef&uuml;gt, wenn der Benutzer gerade
	 * keinen Punkt &uuml;ber die Zeichenfl&auml;che gezogen hat und sich dort im
	 * Datenmodell kein Punkt befindet. Nur bei tats&auml;chen &Auml;nderungen am
	 * Datenmodellwird die aktuell g&uuml;ltige Datenmenge in der Datenhistorie des
	 * Datenmodells abgelegt. Wenn beispielsweise der User die Maus auf der
	 * Zeichenfl&auml;che dr&uuml;ckt, zieht und losl&auml;sst ohne dass das
	 * Datenmodell ver&auml;ndert wird, weil kein Punkt in der N&auml;he ist, wird
	 * die Modellhistorie nicht ver&auml;ndert.
	 */
	@Override
	public void mouseReleased(MouseEvent e) {
		if (inputDeviceOn) {
			if (!ongoingDraggingOperation) {
//				long x = e.getX();
//				long y = e.getY();
				int x = e.getX();
				int y = e.getY();
				if (model.isThereNearPoint(x, y)) {
					DataPoint z = model.getNearPoint(x, y);
					model.deletePointAt(z.getX(), z.getY());
					model.safe();
				} else {
					model.addPoint(new DataPoint(x, y));
					model.safe();
				}

				JPanel myPanel = (JPanel) e.getSource();
				myPanel.repaint();
			}
			if (ongoingDraggingOperation && currentPoint != null) {
				currentPoint = null;
				model.safe();
			}
			ongoingDraggingOperation = false;

		}
	}

	/**
	 * Nachdem der MouseEventController bei der Zeichenfl&auml;che angemeldet wurde,
	 * wird beim Ziehen eines Punktes im Datenmodell &uuml;ber die
	 * Zeichenfl&auml;che ein Punkt im Datenmodell aktualisiert, wenn dieser gerade 
	 * &uuml;ber die Zeichenfl&auml;che gezogen wird.
	 */
	@Override
	public void mouseDragged(MouseEvent e) {
		if (inputDeviceOn) {
			ongoingDraggingOperation = true;
//			long x = e.getX();
//			long y = e.getY();
			int x = e.getX();
			int y = e.getY();
			if (currentPoint != null) {
				model.setGivenPoint(currentPoint, x, y);
			}
			JPanel drawingPanel = (JPanel) e.getSource();
			drawingPanel.repaint();
		}

	}

	/**
	 * Mit dieser Methode kann das InputDevice die Entgegennahme von Userinput auf
	 * der Zeichenfl&auml;che blockieren, zum Beispiel dann, wenn gerade die
	 * Zoom_Infunktion aktiviert ist.
	 */

	public void switchOffInputDevice() {
		inputDeviceOn = false;
	}

	/**
	 * Mit dieser Methode kann das InputDevice die Entgegennahme von Userinput auf
	 * der Zeichenfl&auml;che freigegeben, zum Beispiel dann, wenn die
	 * Zoom_Infunktion deaktiviert wird.
	 */
	public void switchOnInputDevice() {
		inputDeviceOn = true;
	}

}
