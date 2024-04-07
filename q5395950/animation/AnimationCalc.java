package propra22.q5395950.animation;

import java.util.ArrayList;
import java.util.ListIterator;

import propra22.q5395950.generalUtility.CircleIterator;
import propra22.q5395950.generalUtility.DataModelI;
import propra22.q5395950.generalUtility.DataPoint;
import propra22.q5395950.generalUtility.GeometryCalc;
import propra22.q5395950.generalUtility.UserInterface;
import propra22.q5395950.gui.AnimationSpeedSlider;

/**
 * Ein Exemplar dieser Klasse berechnet aufgrund der Nachricht eines Exemplars
 * der Klasse AnimationCoordinator die zu zeichnenenden geometrischen Objekte.
 * Um die geometrischen Objekte fuer eine Iteration zu zeichnen, hat dieses
 * Exemplar ein Objekt der Klasse AnimationPaintCoord. Um die rotierenden
 * Tangenten zu berechnen, nutzt dieses Exemplar die Methoden der Klasse
 * SingleTangentRotation.
 * 
 * @author Lukas Santo Puglisi
 */
public class AnimationCalc {
	/**
	 * Die interne Kopie der konvexen H&uuml;lle, die f&uuml;r die Animation
	 * ben&ouml;tigt wird.
	 */
	private ArrayList<DataPoint> copyOfConvexHull = new ArrayList<DataPoint>();
	/**
	 * Der lexikographisch kleinste Punkt, bei dem die Animation startet.
	 */
	private DataPoint minP;
	/**
	 * Der Index des lexikographisch kleinsten Punktes bez&uuml;glich der konvexen
	 * H&uuml;lle.
	 */
	private int indexOfMinP;
	/**
	 * Der lexikographisch gr&ouml;&szlig;te Punkt, bei dem die Animation startet.
	 */
	private DataPoint maxP;
	/**
	 * Der Index des lexikographisch gr&ouml;&szligten Punktes bez&uuml;glich der
	 * konvexen H&uuml;lle.
	 */
	private int indexOfMaxP;
	/**
	 * Ein Exemplar der Klasse AnimationPaintCoord, mit dem das Zeichnen der Animation
	 * koordiniert wird.
	 */
	AnimationPaintCoord animationPaintCoord;
	/**
	 * Der Zeiger auf die Antipode A.
	 */
	CircleIterator aPointer;
	/**
	 * Der Zeiger auf die Antipode C.
	 */
	CircleIterator cPointer;
	/**
	 * Eine Referenz auf das UserInterface, die an den TangentPointCoord
	 * weitergegeben wird.
	 */
	UserInterface userInterface;
	/**
	 * Eine Referenz auf das Model. Die Tangentenl&auml;nge soll relativ zu der
	 * gr&ouml;&szlige der Eingabedaten sein.
	 */
	DataModelI model;
	/**
	 * Die L&auml;nge der zu zeichnenden Tangenten.
	 */
	long tangentLength;
	/**
	 * Der AnimationCoord merkt sich, ob die Animation gerade startet, oder ob
	 * lediglich das n&auml;chste Antipodenpaar animiert werden soll.
	 */
	private boolean animationStarts = true;
	/**
	 * Mit diesem Objekt werden der AnimationCoord und der AnimationPaintCoord mittels
	 * eines wait_notify Mechanismus synchronisiert.
	 */
	private SyncObject syncObject;
	/*
	 * Die Referenz auf den AnimationSpeedSlider wird an den AnimationPaintCoord
	 * weitergegeben.
	 */
	private AnimationSpeedSlider animationSpeedSlider;
	/**
	 * Der AnimationCoord setzt dieses Attribut auf wahr, sobald die Animation
	 * gestoppt werden soll. Anschlie&szlig;end wird der Thread des AnimationPaintCoord beim
	 * Zeichnen angehalten. .
	 */
	private volatile boolean animationStopped;

	/**
	 * Erstellt ein Exemplar dieser Klasse.
	 * 
	 * @param model                Das Exemplar merkt sich, wo in der konvexen
	 *                             H&uuml;lle die Tangenten darzustellen sind und
	 *                             welches Antipodenpaar anschlie&szlig;end
	 *                             darzustellen ist. Insbesondere wird model
	 *                             ben&ouml;tigt, weil die L&auml;nge der Tangenten
	 *                             abh&auml;ngig vom gr&ouml;&szlig;ten Durchmesser
	 *                             der konvexen H&uuml;lle ist. Dies ist besonders
	 *                             bei Aktivierung der ZoomIn Funktion nutzlich.
	 *                             Insbesondere sind die Tangenten damit auch bei
	 *                             besonders grossen Koordinaten sichtbar.
	 * @param userInterface        Dieser Parameter wird an den AnimationPaintCoord
	 *                             weitergegeben.
	 * @param animationSpeedSlider Dieser Parameter wird an den AnimationPaintCoord
	 *                             weitergegeben.
	 */
	public AnimationCalc(DataModelI model, UserInterface userInterface, AnimationSpeedSlider animationSpeedSlider) {
		this.model = model;
		this.userInterface = userInterface;
		copyConvexHullCalcMinPAndMaxP(model.getConvexHull());
		tangentLength = (long) model.getDiameterLength() / 10;
		this.animationSpeedSlider = animationSpeedSlider;
	}

	/**
	 * Mithilfe der Methoden der Klasse SingleTangentRotation werden die zu
	 * zeichnenden geometrischen Objekte f&uuml;r ein Antipodenpaar berechnet und
	 * mithilfe des AnimationPaintCoords gezeichnet.
	 * 
	 * @param syncObject Das syncObjet wird an den AnimationPaintCoord weitergegeben.
	 */

	public void animateAntipodePair(SyncObject syncObject) {
		if (animationStopped) {
			return;
		}
		this.syncObject = syncObject;

		if (animationStarts) {
			animateFirstAntipodePair();
		} else {
			animateNextAntipodePair();
		}
	}

	/**
	 * Der Start wird besonders behandelt, weil dann der linke Endpunkt der
	 * Tangenten direkt &uuml;ber, bzw. unter dem Antipodenpaar liegt.
	 */
	private void animateFirstAntipodePair() {
		animationStarts = false;
		aPointer = new CircleIterator(copyOfConvexHull, indexOfMinP);
		cPointer = new CircleIterator(copyOfConvexHull, indexOfMaxP);

		DataPoint antipodeA = aPointer.readOnlyNextPoint();
		DataPoint startPointA = new DataPoint(antipodeA.getX(), antipodeA.getY() - 80);
		DataPoint nextAntipodeA = aPointer.getPreviousPoint();

		DataPoint antipodeC = cPointer.readOnlyNextPoint();
		DataPoint startPointC = new DataPoint(antipodeC.getX(), antipodeC.getY() + 80);
		DataPoint nextAntipodeC = cPointer.getPreviousPoint();

		resetPointers();
		DataPoint bQuadrangle = calculateBQuadrangle(antipodeA, antipodeC);
		DataPoint dQuadrangle = calculateDQuadrangle(antipodeA, antipodeC);

		paintNextAnimation(antipodeA, nextAntipodeA, antipodeC, nextAntipodeC, startPointA, startPointC, bQuadrangle,
				dQuadrangle);
	}

	/**
	 * Nach der Animation f&uuml;r das erste Antipodenpaar wird ausschlie&szlig;lich
	 * diese Methode benutzt. Diese Methode funktioniert analog zu der Methode
	 * animateFirstAntipodePair
	 */
	private void animateNextAntipodePair() {
		DataPoint antipodeA = aPointer.readOnlyNextPoint();
		DataPoint nextAntipodeA = aPointer.getPreviousPoint();
		DataPoint antipodeC = cPointer.readOnlyNextPoint();
		DataPoint nextAntipodeC = cPointer.getPreviousPoint();

		resetPointers();

		DataPoint[] startingPoints = getStartingPointsSetAntipodePointersForNextAnimation(antipodeA, nextAntipodeA,
				antipodeC, nextAntipodeC);

		antipodeA = aPointer.readOnlyNextPoint();
		antipodeC = cPointer.readOnlyNextPoint();
		nextAntipodeA = aPointer.getPreviousPoint();
		nextAntipodeC = cPointer.getPreviousPoint();
		resetPointers();

		DataPoint bQuadrangle = calculateBQuadrangle(antipodeA, antipodeC);
		DataPoint dQuadrangle = calculateDQuadrangle(antipodeA, antipodeC);

		paintNextAnimation(antipodeA, nextAntipodeA, antipodeC, nextAntipodeC, startingPoints[0], startingPoints[1],
				bQuadrangle, dQuadrangle);
	}

	/**
	 * Diese Methode resetted die beiden AntipodenZeiger, nachdem diese benutzt
	 * wurden, um ein Antipodenpaar zu lesen.
	 */
	private void resetPointers() {
		aPointer.getNextPoint();
		cPointer.getNextPoint();
	}

	/**
	 * Diese Methode berechnet die Startpunkte f&uuml;r die Rotation der Tangenten
	 * und setzt die Antipodenpointer f&uuml;r das Lesen des n&auml;chsten
	 * Antipodenpaares.
	 * 
	 * @param antipodeA     Die lexikalisch (nach x, dann nach y Koordinate)
	 *                      kleinere Antipode.
	 * @param nextAntipodeA Die auf antipodeA folgende Antipode in der konvexen
	 *                      H&uuml;lle.
	 * @param antipodeC     Die lexikalisch (nach x, dann nach y Koordinate)
	 *                      gr&ouml;&szlig;ere Antipode.
	 * @param nextAntipodeC Die auf antipodeC folgende Antipode in der konvexen
	 *                      H&uuml;lle.
	 * @return Der linke Startpunkt der Tangente, der f&uuml;r die Rotation
	 *         derselben in der folgenden Rotation ben&ouml;tigt wird.
	 * 
	 */
	private DataPoint[] getStartingPointsSetAntipodePointersForNextAnimation(DataPoint antipodeA, DataPoint nextAntipodeA,
			DataPoint antipodeC, DataPoint nextAntipodeC) {
		// Das n&auml;chste Antipodenpaar wird berechnet.
		long angle =  GeometryCalc.angleComparison(antipodeA, nextAntipodeA, antipodeC, nextAntipodeC);
		DataPoint startPointA = null;
		DataPoint startPointC = null;
		// Es wird sichergestellt, dass die beiden Tangenten f&uuml;r das n&auml;chste
		// Antipoden
		// paar parallel bleiben.
		if (angle > 0) {
//			long xC = nextAntipodeC.getX();
//			long yC = nextAntipodeC.getY();
//			long xA = antipodeA.getX();
//			long yA = antipodeA.getY();
//			long newX = xA - (-xC + antipodeC.getX());
//			long newY = yA - (-yC + antipodeC.getY());
			int xC = nextAntipodeC.getX();
			int yC = nextAntipodeC.getY();
			int xA = antipodeA.getX();
			int yA = antipodeA.getY();
			int newX = xA - (-xC + antipodeC.getX());
			int newY = yA - (-yC + antipodeC.getY());
			startPointA = new DataPoint(newX, newY);
			startPointC = new DataPoint(antipodeC.getX(), antipodeC.getY());
			cPointer.getPreviousPoint();
		}
		if (angle < 0) {
//			long xA = nextAntipodeA.getX();
//			long yA = nextAntipodeA.getY();
//			long xC = antipodeC.getX();
//			long yC = antipodeC.getY();
//			long newX = xC - (-xA + antipodeA.getX());
//			long newY = yC - (-yA + antipodeA.getY());
			int xA = nextAntipodeA.getX();
			int yA = nextAntipodeA.getY();
			int xC = antipodeC.getX();
			int yC = antipodeC.getY();
			int newX = xC - (-xA + antipodeA.getX());
			int newY = yC - (-yA + antipodeA.getY());
			startPointA = new DataPoint(antipodeA.getX(), antipodeA.getY());
			startPointC = new DataPoint(newX, newY);
			aPointer.getPreviousPoint();
		}
		if (angle == 0) {
			startPointA = new DataPoint(antipodeA.getX(), antipodeA.getY());
			startPointC = new DataPoint(antipodeC.getX(), antipodeC.getY());
			aPointer.getPreviousPoint();
			cPointer.getPreviousPoint();
		}
		DataPoint[] startingPoints = new DataPoint[2];
		startingPoints[0] = startPointA;
		startingPoints[1] = startPointC;
		return startingPoints;
	}

	/**
	 * Diese Methode berechnet den Eckpunkt d des Vierecks f&uuml;r das
	 * Antipodenpaar a und c.
	 * 
	 * @param antipodeA AntipodeA entspricht zun&auml;chst dem linken Endpunkt des
	 *                  Vierecks
	 * @param antipodeC Die AntipodeC entspricht zun&auml;chst dem rechten Endpunkt
	 *                  des Vierecks
	 * @return gibt den (oberen) Endpunkt des Vierecks zur&uuml;ck
	 */
	private DataPoint calculateDQuadrangle(DataPoint antipodeA, DataPoint antipodeC) {
		CircleIterator dPointer = new CircleIterator(copyOfConvexHull, cPointer.nextIndex());
		DataPoint dQuadrangle = dPointer.getNextPoint();
		DataPoint afterD = dPointer.getNextPoint();
		while (GeometryCalc.isHigher(antipodeA, antipodeC, afterD, dQuadrangle)) {
			dQuadrangle = afterD;
			afterD = dPointer.getNextPoint();
		}
		return dQuadrangle;
	}

	/**
	 * Diese Methode berechnet den Eckpunkt b des Vierecks f&uuml;r das
	 * Antipodenpaar a und c.
	 * 
	 * @param antipodeA AntipodeA entspricht zun&auml;chst dem linken Endpunkt des
	 *                  Vierecks
	 * @param antipodeC Die AntipodeC entspricht zun&auml;chst dem rechten Endpunkt
	 *                  des Vierecks
	 * @return gibt den (unteren) Endpunkt des Vierecks zur&uuml;ck
	 */
	private DataPoint calculateBQuadrangle(DataPoint antipodeA, DataPoint antipodeC) {
		CircleIterator bPointer = new CircleIterator(copyOfConvexHull, aPointer.nextIndex());
		DataPoint bQuadrangle = bPointer.getNextPoint();
		DataPoint afterB = bPointer.getNextPoint();
		while (GeometryCalc.isHigher(antipodeC, antipodeA, afterB, bQuadrangle)) {
			bQuadrangle = afterB;
			afterB = bPointer.getNextPoint();
		}
		return bQuadrangle;
	}

	/**
	 * Diese Methode benachrichtigt den AnimationPaintCoord die zuvor berechneten
	 * geometrischen Objekte zu zeichnen.
	 * 
	 * @param antipodeA     Antipode A
	 * @param nextAntipodeA Auf A folgende Antipode
	 * @param antipodeC     Antipode C
	 * @param nextAntipodeC Auf C folgende Antipode
	 * @param startPointA   Linker Endpunkt der Tangente bei Antipode A
	 * @param startPointC   Linker Endpunkt der Tangente bei Antipode C
	 * @param bQuadrangle   Unterer Endpunkt des Vierecks
	 * @param dQuadrangle   Oberer Endpunkt des Vierecks
	 */
	private void paintNextAnimation(DataPoint antipodeA, DataPoint nextAntipodeA, DataPoint antipodeC, DataPoint nextAntipodeC,
			DataPoint startPointA, DataPoint startPointC, DataPoint bQuadrangle, DataPoint dQuadrangle) {
		ArrayList<DataPoint[]> antipodeATangentList = SingleTangentRotation.getTangents(antipodeA, startPointA,
				nextAntipodeA, tangentLength);
		ArrayList<DataPoint[]> antipodeCTangentList = SingleTangentRotation.getTangents(antipodeC, startPointC,
				nextAntipodeC, tangentLength);
		//
		animationPaintCoord = new AnimationPaintCoord(userInterface, antipodeATangentList, antipodeCTangentList, syncObject,
				animationSpeedSlider, antipodeA, antipodeC, bQuadrangle, dQuadrangle);
	}

	/**
	 * Diese Methode speichert eine Kopie der Punktewolke in der Form, wie sie
	 * f&uuml;r die Antimation ben&ouml;tigt wird. Insbesondere wird das doppelte
	 * gespeicherte erste Element nicht in die in der Klasse gespeicherten konvexen
	 * H&uuml;lle &uuml;bernommen. Ferner werden der lexikalisch kleinste und
	 * gr&ouml;&szlig;te Punkt berechnet.
	 * 
	 * @param convexHull Die einzulesende konvexe H&uuml;lle der im Modell
	 *                   vorgehaltenen Punktewolke
	 */
	private void copyConvexHullCalcMinPAndMaxP(ArrayList<DataPoint> convexHull) {
		ListIterator<DataPoint> copyIterator = convexHull.listIterator();
		// skip double encoded first element
		// Further, I find the lexicographic highest and lowest point in the convexHull
		minP = copyIterator.next();
		copyOfConvexHull.add(minP);
		indexOfMinP = 0;
		maxP = minP;
		int i = 0;
		indexOfMaxP = 0;
		while (copyIterator.hasNext() && i < convexHull.size() - 2) {
			i++;
			DataPoint p = copyIterator.next();
			copyOfConvexHull.add(p);
			if (p.getX() < minP.getX()) {
				minP = p;
				indexOfMinP = i;
			} else if ((p.getX() == minP.getX()) & (p.getY() < minP.getY())) {
				minP = p;
				indexOfMinP = i;
			}
			if (p.getX() > maxP.getX()) {
				maxP = p;
				indexOfMaxP = i;
			} else if ((p.getX() == maxP.getX()) & (p.getY() > maxP.getY())) {
				maxP = p;
				indexOfMaxP = i;
			}
		}
	}

	/**
	 * Stoppt den TangentenPaintCoord beim Zeichnen der Animation auf
	 * Benachrichtigung des AnimationCoord.
	 */
	@SuppressWarnings("deprecation")
	public void stopAnimation() {
		animationStopped = true;

		if (animationPaintCoord != null) {
			animationPaintCoord.stop();
		}

	}

}
