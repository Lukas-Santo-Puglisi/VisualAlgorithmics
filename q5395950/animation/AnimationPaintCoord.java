package propra22.q5395950.animation;

import java.util.ArrayList;
import java.util.ListIterator;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import propra22.q5395950.generalUtility.DataPoint;
import propra22.q5395950.generalUtility.UserInterface;
import propra22.q5395950.gui.AnimationSpeedSlider;

/**
 * Ein Exemplar dieser Klasse startet einen Ausf&uuml;hrungsstrang und
 * koordiniert damit das Zeichnen der von einem animationTangentCalc erhaltenen
 * geometrischen Objekte f&uuml;r das gerade aktuelle Antipodenpaar. Das
 * Exemplar passt hierbei die Geschwindigkeit an den vom AnimationSpeedSlider
 * vorgegebenen Wert dynamisch an.
 * 
 * @author Lukas Santo Puglisi
 */

public class AnimationPaintCoord extends Thread implements ChangeListener {
	/**
	 * Ein Exemplar der Klasse AnimationPaintCoord teilt dem UserInterface mit, welche
	 * geometrischen Objekte zu zeichnen sind.
	 */
	UserInterface userInterface;
	/*
	 * Die antipodeATangents enth&auml;lt die Menge der Tangenten, die f&uuml;r die Rotation
	 * um die Antipode A zu zeichnen sind.
	 */
	ArrayList<DataPoint[]> antipodeATangents;
	/*
	 * Die antipodeCTangents enth&auml;lt die Menge der Tangenten, die f&uuml;r die Rotation
	 * um die Antipode C zu zeichnen sind.
	 */
	ArrayList<DataPoint[]> antipodeCTangents;
	/*
	 * Mittels eines aTangentIterators wird das UserInterface sukzessive
	 * benachrichtigt, die n&auml;chste Tangente f&uuml;r die Antipode A aus der Gesamtmenge
	 * der Tangenten, die in der antipodeATangent-Liste enthalten ist, zu zeichnen.
	 */
	ListIterator<DataPoint[]> aTangentIterator;
	/*
	 * Mittels eines cTangentIterators wird das UserInterface sukzessive
	 * benachrichtigt, die n&auml;chste Tangente f&uuml;r die Antipode C aus der Gesamtmenge
	 * der Tangenten, die in der antipodeCTangent-Liste enthalten ist, zu zeichnen.
	 */
	ListIterator<DataPoint[]> cTangentIterator;
	/**
	 * Das Objekt mit dem der AnimationCoord und der AnimationPaintCoord mittels eines
	 * wait_notify Mechanismus synchronisiert werden. Der AnimationCoord wartet mit
	 * dem Befehl zur Animation des n&auml;chsten Antipodenpaares solange, bis die
	 * Animation f&uuml;r das aktuelle Antipodenpaar vollst&auml;ndig
	 * pr&auml;sentiert wurde.
	 */
	private SyncObject syncObject;
	/**
	 * Die Geschwindigkeit mit der die Animation ablaufen soll.
	 */
	private volatile int speed;
	/**
	 * Der animationSpeedSlider informiert den AnimationPaintCoord, sobald der Nutzer
	 * die Geschwindigkeit der Animation &auml;ndern m&ouml;chte.
	 */
	private AnimationSpeedSlider animationSpeedSlider;
	/*
	 * Die Antipode A, welche einen St&uuml;tzpunkt der Animation darstellt.
	 */
	private DataPoint antipodeA;
	/*
	 * Die Antipode C, welche den zu A gegen&uuml;berliegenden St&uuml;tzpunkt der
	 * Animation darstellt.
	 */
	private DataPoint antipodeC;
	/*
	 * Der Punkt dQuadrangle spannt mit dem Punkt bQuadrangle - f&uuml;r die
	 * festgew&auml;lten Antipodenpunkte A und C - das gr&ouml;&szlig;te Viereck in
	 * der konvexen H&uuml;lle auf.
	 */
	private DataPoint dQuadrangle;
	/*
	 * Der Punkt bQuadrangle spannt mit dem Punkt dQuadrangle - f&uuml;r die
	 * festgew&auml;lten Antipodenpunkte A und C - das gr&ouml;&szlig;te Viereck in
	 * der konvexen H&uuml;lle auf.
	 */
	private DataPoint bQuadrangle;

	/**
	 * Erstellt ein Exemplar dieser Klasse.
	 * 
	 * @param userInterface        Das userInterface wird erh&auml;lt die Nachricht
	 *                             f&uuml;r die zu zeichnenden Objekte
	 * @param antipodeATangents    Die zu zeichnenden Tangenten bei AntipodeA
	 * @param antipodeCTangents    Die zu zeichnenden Tangenten beim AntipodeC
	 * @param syncObject           Das Objekt, mit dem das Zeichnen in Abstimmung
	 *                             mit dem AnimationCoord koordiniert wird
	 * @param animationSpeedSlider Legt die Geschwindigkeit der Tangentenrotation
	 *                             fest
	 * @param antipodeA            AntipodeA
	 * @param antipodeC            AntipodeC
	 * @param bQuadrangle          auf AntipodeC folgender Eckpunkt des aktuellen
	 *                             Vierecks
	 * @param dQuadrangle          auf AntipodeA folgender Eckpunkt des aktuellen
	 *                             Vierecks
	 */
	public AnimationPaintCoord(UserInterface userInterface, ArrayList<DataPoint[]> antipodeATangents,
			ArrayList<DataPoint[]> antipodeCTangents, SyncObject syncObject, AnimationSpeedSlider animationSpeedSlider,
			DataPoint antipodeA, DataPoint antipodeC, DataPoint bQuadrangle, DataPoint dQuadrangle) {
		this.userInterface = userInterface;
		this.antipodeATangents = antipodeATangents;
		this.antipodeCTangents = antipodeCTangents;
		this.antipodeA = antipodeA;
		this.antipodeC = antipodeC;
		this.bQuadrangle = bQuadrangle;
		this.dQuadrangle = dQuadrangle;
		aTangentIterator = antipodeATangents.listIterator();
		cTangentIterator = antipodeCTangents.listIterator();
		this.syncObject = syncObject;
		this.animationSpeedSlider = animationSpeedSlider;
		speed = (100 - animationSpeedSlider.getValue()) * 4;
		start();

	}

	/**
	 * Sobald die Animation der Tangentenrotation f&uuml;r ein Antipodenpaar
	 * abgeschlossen ist, wird der animationCoord aus seinem wartenden Zustand in
	 * den Zustand runnable versetzt. Anschlie&szlig;end wechselt der Thread, der von
	 * einem Exemplar dieser Klasse gestartet wurde in den Zustand dead.
	 */
	public void run() {
		synchronized (syncObject) {
			paintTangents();
			syncObject.notify();

		}
	}

	/**
	 * St&ouml;&szlig;t das Zeichnen der geometrischen Objekte gem&auml;&szlig; der
	 * vom animationSpeedSlider vorgegebenen Geschwindigkeit an.
	 */

	public synchronized void paintTangents() {
		while (aTangentIterator.hasNext() && cTangentIterator.hasNext()) {
			userInterface.setGeometryForAnimation(aTangentIterator.next(), cTangentIterator.next(), antipodeA, antipodeC,
					bQuadrangle, dQuadrangle);
			try {
				speed = (100 - animationSpeedSlider.getValue()) * 4;
				sleep(speed);
			} catch (InterruptedException e) {
			}
		}

	}

	/**
	 * Sobald der Nutzer den Schieberegler des UserInterfaces bedient, passt sich
	 * die Geschwindigkeit der Animation an.
	 */
	@Override
	public void stateChanged(ChangeEvent e) {
		speed = (100 - animationSpeedSlider.getValue()) * 4;

	}

}
