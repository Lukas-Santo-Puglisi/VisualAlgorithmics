package propra22.q5395950.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import propra22.q5395950.animation.AnimationCoord;
import propra22.q5395950.generalUtility.DataModelI;
import propra22.q5395950.generalUtility.UserInterface;
import propra22.q5395950.gui.AnimationSpeedSlider;

/**
 * Die Hauptaufgabe dieser Klasse ist es, sobald beim JButton f&uuml;r die
 * Animation ein ActionEvent erzeugt wurde, ein Exemplar der Klasse Animation
 * Coord zu erzeugen, um die Animation zu starten beziehungsweise diesem
 * mitzuteilen, dass die Animation beendet werden sollte, wenn der Knopf bei
 * einer laufenden Animation gedr&uuml;ckt wurde. Wenn das Modell weniger als
 * zwei Datenpunkte enth&auml;lt wird dem User mitgeteilt, dass er mehr Punkte
 * hinzuf&uuml;gen soll, um die Animation zu starten.
 * 
 * @author LukasSanto Puglisi
 */

public class AnimationListener implements ActionListener {
	/**
	 * Ein Exemplar der Klasse AnimationListener informiert ein Exemplar der Klasse
	 * animationCoordinator, sobald die Animation (auf Knopfdruck) gestartet
	 * beziehungsweise gestoppt werden soll.
	 */
	AnimationCoord animationCoordinator;
	/**
	 * Eine Referenz auf das Modell wird dem animationCoordinator weitergegeben.
	 */
	private DataModelI model;
	/**
	 * Eine Referenz auf das userInterface wird dem animationCoordinator
	 * weitergegeben.
	 */
	private UserInterface userInterface;
	/**
	 * Eine Referenz auf den animationSpeedSlider wird dem animationCoordinator
	 * weitergegeben.
	 */
	private AnimationSpeedSlider animationSpeedSlider;
	/**
	 * Mittels dieser booleschen Variablen merkt sich der Listener den aktuellen
	 * Zustand des JButtons, sodass die Animation korrekt gestartet beziehungsweise
	 * gestoppt werden kann.
	 */
	private boolean starteAnimation = false;

	/**
	 * Erstellt ein Exemplar dieser Klasse.
	 * 
	 * @param userInterface        Eine Referenz auf das UserInterface wird
	 *                             gespeichert.
	 * @param model                Referenz auf das Modell wird gespeichert.
	 * @param animationSpeedSlider Referenz auf den AnimationSpeedSlider wird
	 *                             gespeichert.
	 */
	public AnimationListener(UserInterface userInterface, DataModelI model, AnimationSpeedSlider animationSpeedSlider) {
		this.model = model;
		this.userInterface = userInterface;
		this.animationSpeedSlider = animationSpeedSlider;

	}

	/**
	 * Sobald beim JButton, der am UserInterface f&uuml;r die Animation
	 * zust&auml;ndig ist, ein ActionEvent erzeugt wurde, wird ein Exemplar der
	 * Klasse Animation Coord erzeugt, um die Animation zu starten beziehungsweise
	 * es wird diesem Exemplar mitgeteilt, dass die Animation beendet werden sollte,
	 * wenn der Knopf bei einer laufenden Animation gedr&uuml;ckt wurde. Wenn das
	 * Modell weniger als zwei Datenpunkte enth&auml;lt wird dem User mitgeteilt,
	 * dass er mehr Punkte hinzuf&uuml;gen soll, um die Animation zu starten.
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		if (model.getConvexHull().size() <= 1) {
			userInterface.raiseNoteToUser("Fügen Sie dem Modell bitte mehr Datenpunkte hinzu.");
			return;
		}
		JButton startAnimation = (JButton) e.getSource();
		starteAnimation = (!starteAnimation);
		if (starteAnimation) {
			animationCoordinator = new AnimationCoord(model, userInterface, animationSpeedSlider);
			startAnimation.setText("Stoppe Animation");
			animationCoordinator.start();
		} else {
			startAnimation.setText("Starte Animation");
			animationCoordinator.stopAnimation();
			animationCoordinator = null;
		}

	}

}
