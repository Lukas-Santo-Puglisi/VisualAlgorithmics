package propra22.q5395950.gui;

import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JPanel;

import propra22.q5395950.controller.RandomPointGenerator;
import propra22.q5395950.controller.RedoActionListener;
import propra22.q5395950.controller.UndoActionListener;
import propra22.q5395950.generalUtility.DataModelI;

/**
 * Ein Exemplar der Klasse NorthPanel repr&auml;sentiert den n&ouml;rdlichen Teil der Sicht
 * der Applikation. Es werden die Buttons zur Generierung der zuf&auml;lligen
 * Datenpunktpunkte angezeigt. Ebenso wird der Benutzerin &uuml;ber die undo und redo
 * Buttons in dem n&ouml;rdlichen Teil der Sicht die M&ouml;glichkeit geboten, auf die
 * undo und redo Funktionalit&auml;t der Applikation zuzugreifen. Bei den Buttons
 * sind die actionListener angemeldet, die als eigene Klassen realisiert sind
 * und die Behandlung der Ereignisse implementieren.
 * 
 * @author LukasSanto Puglisi
 *
 */

public class NorthPanel extends JPanel {
	/**
	 * Bei Klick auf diesen Button wird ein Exemplar der Klasse RandomPointGenerator
	 * benachrichtigt, welcher mit der entsprechenden Zahl von Datenpunkten das
	 * Datenmodell anreichert und anschlie&szlig;end die GUI benachrichtigt sich zu
	 * aktualisieren.
	 */
	private JButton pointGenerator10;
	/**
	 * Bei Klick auf diesen Button wird ein Exemplar der Klasse RandomPointGenerator
	 * benachrichtigt, welcher mit der entsprechenden Zahl von Datenpunkten das
	 * Datenmodell anreichert und anschlie&szlig;end die GUI benachrichtigt sich zu
	 * aktualisieren.
	 */
	private JButton pointGenerator50;
	/**
	 * Bei Klick auf diesen Button wird ein Exemplar der Klasse RandomPointGenerator
	 * benachrichtigt, welcher mit der entsprechenden Zahl von Datenpunkten das
	 * Datenmodell anreichert und anschlie&szlig;end die GUI benachrichtigt sich zu
	 * aktualisieren.
	 */
	private JButton pointGenerator100;
	/**
	 * Bei Klick auf diesen Button wird ein Exemplar der Klasse RandomPointGenerator
	 * benachrichtigt, welcher mit der entsprechenden Zahl von Datenpunkten das
	 * Datenmodell anreichert und anschlie&szlig;end die GUI benachrichtigt sich zu
	 * aktualisieren.
	 */
	private JButton pointGenerator500;
	/**
	 * Bei Klick auf diesen Button wird ein Exemplar der Klasse RandomPointGenerator
	 * benachrichtigt, welcher mit der entsprechenden Zahl von Datenpunkten das
	 * Datenmodell anreichert und anschlie&szlig;end die GUI benachrichtigt sich zu
	 * aktualisieren.
	 */
	private JButton pointGenerator1000;
	/**
	 * Bei Klick auf diesen Button wird ein Exemplar der Klasse UndoActionListener
	 * benachrichtigt, welcher die Undo Funktion der Applikation startet und
	 * koordiniert.
	 */
	private JButton undoButton;
	/**
	 * Bei Klick auf diesen Button wird ein Exemplar der Klasse RedoActionListener
	 * benachrichtigt, welcher die Redo Funktion der Applikation startet und
	 * koordiniert.
	 */
	private JButton redoButton;
	/**
	 * An den Buttons wird ein Exemplar der Klasse RandomPointGenerator angemeldet,
	 * welcher das Datenmodell bei Entreten des ActionsEvents mit zuf&auml;lligen
	 * Datenpunkten anreichert.
	 */
	private RandomPointGenerator randomPointGenerator;
	/**
	 * An den Buttons wird ein Exemplar der Klasse UndoActionListener angemeldet,
	 * welcher die Undo Funktion der Applikation startet und koordiniert.
	 */
	private UndoActionListener undoActionListener;
	/**
	 * An den Buttons wird ein Exemplar der Klasse RedoActionListener angemeldet,
	 * welcher die Redo Funktion der Applikation startet und koordiniert.
	 */
	private RedoActionListener redoActionListener;

	/**
	 * Erzeugt ein Exemplar der Klasse. Die Referenzen auf das Datenmodell und die
	 * Sicht werden nicht gespichert sondern an den RandomPointGenerator und die
	 * undo/redoActionListener weitergegeben. Das Panel erzeugt die ZufallsButtons
	 * sowie die Buttons f&uuml;r die undo/redo Funktion und meldet die entsprechenden
	 * ActionListeners an den Buttons an.
	 * 
	 * @param model     Eine Referenz auf das Datenmodell.
	 * @param mainFrame Eine Referenz auf den MainFrame.
	 */

	public NorthPanel(DataModelI model, MainFrame mainFrame) {
		pointGenerator10 = new JButton("Füge 10 zufällige Punkte hinzu");
		pointGenerator50 = new JButton("Füge 50 zufällige Punkte hinzu");
		pointGenerator100 = new JButton("Füge 100 zufällige Punkte hinzu");
		pointGenerator500 = new JButton("Füge 500 zufällige Punkte hinzu");
		pointGenerator1000 = new JButton("Füge 1000 zufällige Punkte hinzu");
		randomPointGenerator = new RandomPointGenerator(model, mainFrame);
		pointGenerator10.addActionListener(randomPointGenerator);
		pointGenerator50.addActionListener(randomPointGenerator);
		pointGenerator100.addActionListener(randomPointGenerator);
		pointGenerator500.addActionListener(randomPointGenerator);
		pointGenerator1000.addActionListener(randomPointGenerator);
		undoButton = new JButton("Undo");
		undoActionListener = new UndoActionListener(mainFrame, model);
		undoButton.addActionListener(undoActionListener);

		redoButton = new JButton("Redo");
		redoActionListener = new RedoActionListener(mainFrame, model);
		redoButton.addActionListener(redoActionListener);

		this.setBackground(new Color(51, 150, 255));
		this.add(pointGenerator10);
		this.add(pointGenerator50);
		this.add(pointGenerator100);
		this.add(pointGenerator500);
		this.add(pointGenerator1000);
		this.add(undoButton);
		this.add(redoButton);
	}

	/**
	 * Das MainFrame weist ein Exemplar dieser Klasse an, Input an den Buttons, die
	 * bei der Animation st&ouml;ren k&ouml;nnen, zu ignorieren.
	 */
	public void switchOff() {
		pointGenerator10.setEnabled(false);
		pointGenerator50.setEnabled(false);
		pointGenerator100.setEnabled(false);
		pointGenerator500.setEnabled(false);
		pointGenerator1000.setEnabled(false);
		undoButton.setEnabled(false);
		redoButton.setEnabled(false);
	}

	/**
	 * Der MainFrame weist ein Exemplar dieser Klasse an, alle Buttons, der w&auml;hrend
	 * der Animation ignoriert wurde, wieder zu behandeln.
	 */
	public void switchOn() {
		pointGenerator10.setEnabled(true);
		pointGenerator50.setEnabled(true);
		pointGenerator100.setEnabled(true);
		pointGenerator500.setEnabled(true);
		pointGenerator1000.setEnabled(true);
		undoButton.setEnabled(true);
		redoButton.setEnabled(true);
	}
}
