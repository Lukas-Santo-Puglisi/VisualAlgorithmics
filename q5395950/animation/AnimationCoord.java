package propra22.q5395950.animation;

import propra22.q5395950.generalUtility.DataModelI;
import propra22.q5395950.generalUtility.UserInterface;
import propra22.q5395950.gui.AnimationSpeedSlider;

/**
 * Ein Exemplar dieser Klasse startet einen neuen Ausf&uuml;hrungsstrang und
 * koordiniiert die sukzessive Darstellung der Animation der Tangenten &uuml;ber
 * alle Punkte der konvexen H&uuml;lle hinweg, bis die Ausf&uuml;hrung gestoppt
 * wird.
 * 
 * @author Lukas Santo Puglisi
 */
public class AnimationCoord extends Thread {
	/**
	 * Eine Referenz auf das Model, das an ein Exemplar der Klasse AnimationCalc
	 * weitergereicht wird.
	 */
	DataModelI model;
	/**
	 * Ein Exemplar der Klasse AnimationCalc, das zur Laufzeit erzeugt wird, wenn
	 * die Animation starten soll.
	 */
	AnimationCalc animationTangentCalc;
	/**
	 * Das Objekt mit dem der AnimationCoord und der AnimationPaintCoord mittels eines
	 * wait_notify Mechanismus synchronisiert werden. Der AnimationCoord wartet mit
	 * dem Befehl zur Animation des n&auml;chsten Antipodenpaares solange, bis die
	 * Animation f&uuml;r das aktuelle Antipodenpaar vollst&auml;ndig
	 * pr&auml;sentiert wurde.
	 */
	SyncObject syncObject;
	/**
	 * Eine Referenz auf das userInterface, das an ein Exemplar der Klasse
	 * animationTangentCalc weitergereicht wird.
	 */
	UserInterface userInterface;
	/**
	 * Sobald der User den "Stoppe Animation" Button gedr&uuml;ckt hat,
	 * benachrichtigt der angemeldete Listener den AnimationCoord, sodass isAnimate
	 * auf false gesetzt wird. Sobald der Thread des AnimationCoords die als
	 * volatile deklarierte Variable erneut liest, merkt er, dass die Animation
	 * beendet werden soll.
	 */
	private volatile boolean isAnimate;
	/**
	 * Ein Exemplar der Klasse AnimationSpeedSlider wird an den AnimationCalc
	 * weitergeleitet.
	 */
	private AnimationSpeedSlider animationSpeedSlider;

	/**
	 * Erstellt ein Exemplar dieser Klasse.
	 * 
	 * @param model                Der Parameter wird an ein Exemplar der Klasse
	 *                             AnimationCalc weitergereicht.
	 * @param userInterface        Der Parameter wird an ein Exemplar der Klasse
	 *                             AnimationTangentCalc weitergereicht.
	 * @param animationSpeedSlider Der Parameter wird an ein Exemplar der Klasse
	 *                             AnimationTangentCalc weitergereicht.
	 */
	public AnimationCoord(DataModelI model, UserInterface userInterface, AnimationSpeedSlider animationSpeedSlider) {
		this.userInterface = userInterface;
		this.model = model;
		this.animationSpeedSlider = animationSpeedSlider;
	}

	/**
	 * Startet den Thread und erstellt das syncObject, welches zur Synchronisation
	 * aller an der Animation beteiligten Klassen dient.
	 */

	public void run() {
		syncObject = new SyncObject();
		synchronized (syncObject) {
			animate();
		}
	}

	/**
	 * Stoppt die Animation, benachrichtigt das userInterface, den
	 * animationTangentCalc und den den animationSpeedSlider, dass die Animation
	 * beendet ist.
	 */

	public void stopAnimation() {
		isAnimate = false;
		animationTangentCalc.stopAnimation();
		userInterface.noAnimationToPaint();
		userInterface.switchOnInput();
		animationSpeedSlider.reset();
		userInterface.refresh();
	}

	/**
	 * Der zu diesem Exemplar geh&ouml;rige Thread wechselt in den Zustand warten,
	 * sobald es dem animationCalc benachrichtigt hat, dass es die Animation
	 * f&uuml;r das n&auml;chste Antipodenpaar zeichnen soll. Nachdem diese
	 * Animation dargestellt wurde, wird der zu diesem Exemplar zugeh&ouml;rige
	 * Thread hiervon in Kenntniss gesetzt und wieder in den Zustand runnable
	 * versetzt.
	 */
	private void animate() {
		isAnimate = true;
		animationTangentCalc = new AnimationCalc(model, userInterface, animationSpeedSlider);
		userInterface.switchOffInput();

		while (isAnimate) {
			try {
				animationTangentCalc.animateAntipodePair(syncObject);
				syncObject.wait();
			} catch (InterruptedException e) {
				userInterface.raiseNoteToUser("Die Animation wurde unerwartet unterbrochen.");
				e.printStackTrace();
			}
		}
		userInterface.noAnimationToPaint();
		userInterface.switchOnInput();
		animationSpeedSlider.reset();
		userInterface.refresh();

	}

}
