package propra22.q5395950.gui;

import java.awt.Color;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JPanel;

import propra22.q5395950.controller.AnimationListener;
import propra22.q5395950.generalUtility.DataModelI;

/**
 * Ein Exemplar der Klasse SouthPanel repr&auml;sentiert den s&uuml;dlichen Teil der
 * Sicht der Applikation. Es werden die Checkboxes zum Ein und Ausschalten der
 * Anzeige der geometrischen Objekte dargestellt. Weiterhin kann die Nutzerin
 * einstellen, ob die Sicht auf die aktuellen Daten mit der ZoomIn Funktion zur
 * besseren Anzeige transformiert werden soll. Ferner wird der Button zum Start
 * der Animation dargestellt. Bei den Checkboxen und dem Button sind die
 * actionListener angemeldet, die als eigene Klassen realisiert sind und die
 * Behandlung der Ereignisse implementieren.
 * 
 * @author LukasSanto Puglisi
 *
 */
public class SouthPanel extends JPanel {
	/**
	 * Mittels dieser CheckBox kann die Nutzerin einstellen, ob die konvexe H&uuml;lle
	 * der aktuellen Datenpunkte des Datenmodells angezeigt werden sollen oder
	 * nicht. Bei Start der Applikation wird die konvexe H&uuml;lle angezeigt, die
	 * Checkbox ist also markiert.
	 */
	private JCheckBox convexHullCheckBox;
	/**
	 * Mittels dieser CheckBox kann die Nutzerin einstellen, ob der
	 * gr&ouml;&szlig;te Durchmesser der konvexen H&uuml;lle der aktuellen Datenpunkte
	 * des Datenmodells angezeigt werden sollen oder nicht. Bei Start der
	 * Applikation wird der Durchmesser angezeigt, die Checkbox ist also markiert.
	 */
	private JCheckBox diameterCheckBox;
	/**
	 * Mittels dieser CheckBox kann die Nutzerin einstellen, ob das
	 * gr&ouml;&szlig;te Viereck der konvexen H&uuml;lle der aktuellen Datenpunkte des
	 * Datenmodells angezeigt werden sollen oder nicht. Bei Start der Applikation
	 * das Viereck angezeigt, die Checkbox ist also markiert.
	 */
	private JCheckBox showQuadrangle;
	/**
	 * Mittels dieser CheckBox kann die Nutzerin einstellen, ob die Sicht auf die
	 * aktuellen Daten mit der ZoomIn Funktion zur besseren Anzeige transformiert
	 * werden soll. Bei Start der Applikation werden die Daten nicht transformiert,
	 * die Checkbox ist also nicht markiert.
	 */
	private JCheckBox zoomInCheckBox;
	/**
	 * Mittels dieses Buttons kann die Nutzerin einstellen, ob die Animation
	 * gestartet werden soll oder nicht.
	 */
	private JButton animationButton;
	/**
	 * Beim startAnimation Button ist der animationListener angemeldet, der die
	 * Animation startet beziehungsweise beendet, sobald ein actionEvent abgefeuert
	 * wurde.
	 */
	private AnimationListener animationListener;
	/**
	 * Ein Exemplar der Klasse SouthPanel informiert das IODevice, welche Checkboxen
	 * durch den User bet&auml;tigt wurden, sodass das IODevice die entsprechenden
	 * geometrischen Objekte zeichnet oder nicht.
	 */
	private IODevice iODevice;

	/**
	 * Erzeugt ein Exemplar der Klasse SouthPanel. Hierbei werden die CheckBox sowie
	 * der Button f&uuml;r die Animation erzeugt. Bei Initialisierung des Exemplars
	 * werden Checkboxen und Buttons bei den ActionListener angemeldet, die die
	 * Behandlung der ActionEvents behandeln.
	 * 
	 * @param mainFrame            Das MainFrame der Applikation
	 * @param model                Eine Referenz auf das Datenmodell der
	 *                             Applikation.
	 * @param iODevice             Die Zeichenfl&auml;che der Applikation.
	 * @param animationSpeedSlider Der AnimationSpeedSlider, welcher an den
	 *                             AnimationListener beim animationButton
	 *                             weitergegeben wird.
	 */
	public SouthPanel(MainFrame mainFrame, DataModelI model, IODevice iODevice,
			AnimationSpeedSlider animationSpeedSlider) {
		this.iODevice = iODevice;
		convexHullCheckBox = new JCheckBox("Zeige konvexe Hülle an", true);
		convexHullCheckBox.addItemListener(new ItemListener() {

			@Override
			public void itemStateChanged(ItemEvent e) {
				iODevice.setShowConvexHull(!iODevice.isShowConvexHull());
				mainFrame.refresh();
				;
			}

		});

		zoomInCheckBox = new JCheckBox("Zoom In", false);
		zoomInCheckBox.addItemListener(new ItemListener() {

			@Override
			public void itemStateChanged(ItemEvent e) {
				iODevice.setZoomIn(!iODevice.isZoomIn());
				mainFrame.refresh();
			}

		});

		diameterCheckBox = new JCheckBox("Zeige den größten Durchmesser an", true);
		diameterCheckBox.addItemListener(new ItemListener() {

			@Override
			public void itemStateChanged(ItemEvent e) {
				iODevice.setShowDiameter(!iODevice.isShowDiameter());
				iODevice.repaint();
			}

		});

		showQuadrangle = new JCheckBox("Zeige das größte Viereck an", true);
		showQuadrangle.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				iODevice.setShowQuadrangle(!iODevice.isShowQuadrangle());
				iODevice.repaint();
			}

		});

		animationButton = new JButton("Starte Animation");
		animationListener = new AnimationListener(mainFrame, model, animationSpeedSlider);
		animationButton.addActionListener(animationListener);

		this.setBackground(new Color(255, 102, 102));
		this.add(convexHullCheckBox);
		this.add(diameterCheckBox);
		this.add(showQuadrangle);
		this.add(zoomInCheckBox);
		this.add(animationButton);
	}

	/**
	 * Das MainFrame weist ein Exemplar dieser Klasse an, Input an den Checkboxen,
	 * die w&auml;hrend der Animation ausgeschaltet wurden, wieder zu behandeln. Auf
	 * der Zeichenfl&auml;che wird genau dann der gr&ouml;&szlig;te Durchmesser und
	 * das gr&ouml;&szlig;te Viereck der konvexen H&uuml;llen der aktuellen Datenpunkte
	 * angezeigt, wenn diese auch vor Start der Animation dargestellt wurden.
	 */
	public void switchOn() {
		diameterCheckBox.setEnabled(true);
		showQuadrangle.setEnabled(true);
		iODevice.setShowDiameter(diameterCheckBox.isSelected());
		iODevice.setShowQuadrangle(showQuadrangle.isSelected());
	}

	/**
	 * Das MainFrame weist ein Exemplar dieser Klasse an, Input an den Checkboxen,
	 * die bei der Animation st&ouml;ren k&ouml;nnen, zu ignorieren. Auf der
	 * Zeichenfl&auml;che werden weder der gr&ouml;&szlig;te Durchmesser noch das
	 * gr&ouml;&szlig;te Viereck der konvexen H&uuml;llen der aktuellen Datenpunkte
	 * angezeigt.
	 */
	public void switchOff() {
		showQuadrangle.setEnabled(false);
		diameterCheckBox.setEnabled(false);
		iODevice.setShowDiameter(false);
		iODevice.setShowQuadrangle(false);

	}

}
