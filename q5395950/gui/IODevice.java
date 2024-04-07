package propra22.q5395950.gui;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.util.*;

import javax.swing.JPanel;

import propra22.q5395950.controller.MouseEventController;
import propra22.q5395950.generalUtility.DataModelI;
import propra22.q5395950.generalUtility.DataPoint;

/**
 * Ein Exemplar der Klasse IODevice repr&auml;sentiert die Zeichenfl&auml;che an der GUI.
 * Dieses Exemplar stellt sowohl die Sicht auf das Datenmodell dar, als auch
 * eine Eingabem&ouml;glichkeit. Das IODevice hat einen MouseEventController, der die
 * Eingaben am IODevice verarbeitet und in Anweisungen an die GUI sowie an das
 * Datenmodell umsetzt.
 * 
 * @author LukasSanto Puglisi
 *
 */
public class IODevice extends JPanel {
	/**
	 * Mit der Referenz auf das Datenmodell kann das IODevice die aktuellen Daten
	 * lesen und anzeigen.
	 */
	DataModelI dataModel;
	/**
	 * Das IODevice hat einen MouseEventController, der die Eingaben am IODevice
	 * verarbeitet und in Anweisungen an die GUI sowie an das Datenmodell umsetzt.
	 */
	MouseEventController mouseAdapter;
	/**
	 * Das IODevice merkt sich, ob die konvexe H&uuml;lle der aktuellen Datenmenge des
	 * Datenmodells angezeigt werden soll oder nicht.
	 */
	public boolean showConvexHull = true;
	/**
	 * Das IODevice merkt sich, ob der gr&ouml;&szlig;te Durchmesser der konvexen H&uuml;lle der
	 * aktuellen Datenmenge des Datenmodells angezeigt werden soll oder nicht.
	 */
	public boolean showDiameter = true;
	/**
	 * Das IODevice merkt sich, ob das gr&ouml;&szlig;te Viereck der konvexen H&uuml;lle der
	 * aktuellen Datenmenge des Datenmodells angezeigt werden soll oder nicht.
	 */
	public boolean showQuadrangle = true;
	/**
	 * Das IODevice merkt sich, ob die aktuelle Datenmenge des Datenmodells in einer
	 * transformierten Darstellung angezeigt werden soll, sodass auch Daten
	 * au&szlig;erhalb der Zeichenfl&auml;che angezeigt werden k&ouml;nnen.
	 */
	public boolean zoomIn = false;
	/**
	 * Das IODevice merkt sich, ob gerade eine Animation dargestellt werden soll
	 * oder nicht.
	 */
	private boolean isAnimate;
	/**
	 * Die Tangente am Antipodepunkt A, die w&auml;hrend der Animation gezeichnet werden
	 * soll.
	 */
	private DataPoint[] tangentA;
	/**
	 * Die Tangente am Antipodepunkt C, die w&auml;hrend der Animation gezeichnet werden
	 * soll.
	 */
	private DataPoint[] tangentC;
	/**
	 * Der Antipodepunkt A, der w&auml;hrend der Animation gezeichnet werden soll.
	 */
	private DataPoint antipodeA;
	/**
	 * Der Antipodepunkt C, der w&auml;hrend der Animation gezeichnet werden soll.
	 */
	private DataPoint antipodeC;
	/**
	 * Ein Eckpunkt des gr&ouml;&szlig;ten Vierecks, der w&auml;hrend der Animation gezeichnet
	 * werden soll.
	 */
	private DataPoint dQuadrangle;
	/**
	 * Ein Eckpunkt des gr&ouml;&szlig;ten Vierecks, der w&auml;hrend der Animation gezeichnet
	 * werden soll.
	 */
	private DataPoint bQuadrangle;

	/**
	 * Fragt den Wert der zoomIn Variable ab. Diese Methode wird ben&ouml;tigt, um den
	 * Wert dieser Variable zu invertieren, wenn die JCeckbox Zoom-In an der GUI
	 * bet&auml;tigt. wird.
	 * 
	 * @return Der boolesche Wert der zoomIn Variable.
	 */

	public boolean isZoomIn() {
		return zoomIn;
	}

	/**
	 * Setzt den Wert der zoomIn Variable. Wird die zoomInVariable gesetzt, wird der
	 * Userinput an der Zeichenfl&auml;che ignoriert, um Sicht und Datenmodell konsistent
	 * zu halten.
	 * 
	 * @param zoomIn Der Wert auf den die zoomInVariable gesetzt werden soll.
	 */
	public void setZoomIn(boolean zoomIn) {
		this.zoomIn = zoomIn;
		if (zoomIn) {
			switchOffInputDevice();
		} else {
			switchOnInputDevice();
		}
	}

	/**
	 * Fragt den Wert der showConvexHull Variable ab. Diese Methode wird ben&ouml;tigt,
	 * um den Wert dieser Variable zu invertieren, wenn die zugeh&ouml;rige JCeckbox an
	 * der GUI bet&auml;tigt wird.
	 * 
	 * @return Der boolesche Wert der showConvexHull Variable.
	 */
	public boolean isShowConvexHull() {
		return showConvexHull;
	}

	/**
	 * Setzt den Wert der Variable showConvexHull. Wird die JCheckbox f&uuml;r
	 * showConvexHull nicht gesetzt, so wird die konvexe H&uuml;lle der aktuellen Punkte
	 * des Datenmodells in der Sicht nicht dargestellt.
	 * 
	 * @param showConvexHull Der Wert auf den die showConvexHull gesetzt werden
	 *                       soll.
	 */

	public void setShowConvexHull(boolean showConvexHull) {
		this.showConvexHull = showConvexHull;
	}

	/**
	 * Fragt den Wert der showDiameter Variable ab. Diese Methode wird ben&ouml;tigt, um
	 * den Wert dieser Variable zu invertieren, wenn die zugeh&ouml;rige JCeckbox an der
	 * GUI bet&auml;tigt wird.
	 * 
	 * @return Der boolesche Wert der showDiameter Variable.
	 */
	public boolean isShowDiameter() {
		return showDiameter;
	}

	/**
	 * Setzt den Wert der Variable showDiameter. Wird die JCheckbox f&uuml;r showDiameter
	 * nicht gesetzt, so wird der gr&ouml;&szlig;te Durchmesser in der konvexen H&uuml;lle der
	 * aktuellen Punktemenge des Datenmodells nicht in der Sicht dargestellt.
	 * 
	 * @param showDiameter Der Wert auf den die showConvexHull gesetzt werden soll.
	 */
	public void setShowDiameter(boolean showDiameter) {
		this.showDiameter = showDiameter;
	}

	/**
	 * Fragt den Wert der Variable showQuadrangle ab. Diese Methode wird ben&ouml;tigt,
	 * um den Wert dieser Variable zu invertieren, wenn die zugeh&ouml;rige JCeckbox an
	 * der GUI bet&auml;tigt wird.
	 * 
	 * @return Der boolesche Wert der showQuadrangle Variable.
	 */
	public boolean isShowQuadrangle() {
		return showQuadrangle;
	}

	/**
	 * Setzt den Wert der Variable showQuadrangle. Wird die JCheckbox f&uuml;r
	 * showQuadrangle nicht gesetzt, so wird das gr&ouml;&szlig;te Viereck in der konvexen
	 * H&uuml;lle der aktuellen Punktemenge des Datenmodells nicht in der Sicht
	 * dargestellt.
	 * 
	 * @param showQuadrangle Der Wert auf den die showConvexHull gesetzt werden
	 *                       soll.
	 */

	public void setShowQuadrangle(boolean showQuadrangle) {
		this.showQuadrangle = showQuadrangle;
	}

	/**
	 * Erzeugt ein Exemplar des IODevices, das die Zeichenfl&auml;che der Applikation
	 * repr&auml;sentiert und die Sicht auf die Daten darstellt. Am IODevice wird ein
	 * Exemplar der Klasse MouseEventController angemeldet, das den Userinput
	 * behandelt. Das IODevice erh&auml;lt eine Referenz auf model, das das Datenmodell
	 * repr&auml;sentiert und auf das f&uuml;r das Lesen der aktuellen Punktemenge zugegriffen
	 * wird, um die Sicht zu aktualisieren.
	 * 
	 * @param model
	 */

	public IODevice(DataModelI model) {
		this.dataModel = model;
		setBackground(Color.WHITE);
		mouseAdapter = new MouseEventController(model);
		addMouseListener(mouseAdapter);
		addMouseMotionListener(mouseAdapter);
	}

	/**
	 * Die paintComponent koordiniert das Zeichnen der Datenpunkte des Datenmodells,
	 * der geometrischen Figuren (Durchmesser, Viereck, konvexe H&uuml;lle, Dreieck)
	 * sowie die Darstellung der Animation. Mit einem Exemplar der Klasse
	 * AffineTransform kann das IODevice die Daten auf Befehl so implementieren,
	 * dass auch Datenmengen mit gro&szlig;en Koordinaten dargestellt werden k&ouml;nnen.
	 * 
	 * @param g Das Graphicsobjekt, mit dem auf der Zeichenfl&auml;che gezeichnet werden
	 *          kann.
	 */
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		if (zoomIn && (dataModel.getConvexHull().size() != 0)) {
			g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_OFF);
			AffineTransform transform = ZoomInCalc.getTransform(dataModel, this.getSize());
			g2.transform(transform);
		}
		g2.setColor(Color.blue);
		paintDataPoints(g2);
		if (!dataModel.isEmpty()) {
			if (showConvexHull) {
				g2.setColor(Color.red);
				paintConvexHull(g2);
			}

			if (showDiameter) {
				g2.setColor(Color.black);
				paintDiameter(g2);
			}
			if (showQuadrangle) {
				g2.setColor(Color.blue);
				paintQuadrangle(g2);
			}
			if (isAnimate) {
				paintAnimation(g2);
			}
		}

	}

	/**
	 * Die Methode, die paintComponent zum Zeichnen des gr&ouml;&szlig;ten Vierecks in der
	 * konvexen H&uuml;lle der aktuellen Datenpunkte benutzt.
	 * 
	 * @param g2 Das Graphicsobjekt, mit dem auf der Zeichenfl&auml;che gezeichnet wird.
	 */

	private void paintQuadrangle(Graphics2D g2) {
		int[][] quadrangle = dataModel.getQuadrangle();
		g2.drawLine(quadrangle[0][0], quadrangle[0][1], quadrangle[1][0], quadrangle[1][1]);
		g2.drawLine(quadrangle[1][0], quadrangle[1][1], quadrangle[2][0], quadrangle[2][1]);
		g2.drawLine(quadrangle[2][0], quadrangle[2][1], quadrangle[3][0], quadrangle[3][1]);
		g2.drawLine(quadrangle[3][0], quadrangle[3][1], quadrangle[0][0], quadrangle[0][1]);

	}

	/**
	 * Die Methode, die paintComponent zum Zeichnen des gr&ouml;&szlig;ten Durchmessers in der
	 * konvexen H&uuml;lle der aktuellen Datenpunkte benutzt.
	 * 
	 * @param g2 Das Graphicsobjekt, mit dem auf der Zeichenfl&auml;che gezeichnet wird.
	 */

	private void paintDiameter(Graphics2D g2) {
		int[][] diameter = dataModel.getDiameter();
		g2.drawLine(diameter[0][0], diameter[0][1], diameter[1][0], diameter[1][1]);
	}

	/**
	 * Die Methode, die paintComponent zum Zeichnen der einzelnen Datenpunkte
	 * benutzt.
	 * 
	 * @param g2 Das Graphicsobjekt, mit dem auf der Zeichenfl&auml;che gezeichnet wird.
	 */

	private void paintDataPoints(Graphics2D g2) {
		ArrayList<DataPoint> pointList = dataModel.getAllPoints();
		Iterator<DataPoint> pointIterator = pointList.iterator();
		while (pointIterator.hasNext()) {
			DataPoint p = pointIterator.next();
			long x = p.getX();
			long y = p.getY();
			long x_verbessert = x - 2;
			long y_verbessert = y - 2;
			g2.fillOval((int) x_verbessert, (int) y_verbessert, 4, 4);
		}
	}

	/**
	 * Die Methode, die paintComponent zum Zeichnen der konvexen H&uuml;lle der aktuellen
	 * Datenpunkte benutzt.
	 * 
	 * @param g2 Das Graphicsobjekt, mit dem auf der Zeichenfl&auml;che gezeichnet wird.
	 */

	private void paintConvexHull(Graphics2D g2) {
		ArrayList<DataPoint> convexHull = dataModel.getConvexHull();
		ListIterator<DataPoint> convexHullIterator = convexHull.listIterator();
		while (convexHullIterator.hasNext()) {
			DataPoint leftEdge = convexHullIterator.next();
			if (convexHullIterator.hasNext()) {
				DataPoint rightEdge = convexHullIterator.next();
				long x1 = leftEdge.getX();
				long y1 = leftEdge.getY();
				int x1_verbessert = (int) x1;
				int y1_verbessert = (int) y1;

				long x2 = rightEdge.getX();
				long y2 = rightEdge.getY();
				int x2_verbessert = (int) x2;
				int y2_verbessert = (int) y2;
				convexHullIterator.previous();
				g2.drawLine(x1_verbessert, y1_verbessert, x2_verbessert, y2_verbessert);
			}
		}
	}

	/**
	 * Die Methode, die paintComponent zum Zeichnen der Animation benutzt.
	 * 
	 * @param g2 Das Graphicsobjekt, mit dem auf der Zeichenfl&auml;che gezeichnet wird.
	 */
	private void paintAnimation(Graphics2D g2) {
		DataPoint leftTangentA = tangentA[0];
		DataPoint rightTangentA = tangentA[1];
		int ovalSize = 10;

		// paint diameter
		g2.setColor(Color.black);
		g2.drawLine((int) antipodeA.getX(), (int) antipodeA.getY(), (int) antipodeC.getX(), (int) antipodeC.getY());

		// paint Quadrangle
		g2.setColor(Color.blue);
		g2.drawLine((int) antipodeA.getX(), (int) antipodeA.getY(), (int) bQuadrangle.getX(), (int) bQuadrangle.getY());
		g2.drawLine((int) antipodeA.getX(), (int) antipodeA.getY(), (int) dQuadrangle.getX(), (int) dQuadrangle.getY());
		g2.drawLine((int) antipodeC.getX(), (int) antipodeC.getY(), (int) bQuadrangle.getX(), (int) bQuadrangle.getY());
		g2.drawLine((int) antipodeC.getX(), (int) antipodeC.getY(), (int) dQuadrangle.getX(), (int) dQuadrangle.getY());

		g2.setColor(Color.green);
		g2.fillOval((int) leftTangentA.getX() - 5, (int) leftTangentA.getY() - 5, ovalSize, ovalSize);
		g2.fillOval((int) rightTangentA.getX() - 5, (int) rightTangentA.getY() - 5, ovalSize, ovalSize);
		g2.drawLine((int) leftTangentA.getX(), (int) leftTangentA.getY(), (int) rightTangentA.getX(),
				(int) rightTangentA.getY());

		DataPoint leftTangentC = tangentC[0];
		DataPoint rightTangentC = tangentC[1];

		g2.fillOval((int) leftTangentC.getX() - 5, (int) leftTangentC.getY() - 5, ovalSize, ovalSize);
		g2.fillOval((int) rightTangentC.getX() - 5, (int) rightTangentC.getY() - 5, ovalSize, ovalSize);
		g2.drawLine((int) leftTangentC.getX(), (int) leftTangentC.getY(), (int) rightTangentC.getX(),
				(int) rightTangentC.getY());
		g2.setColor(Color.black);
	}

	/**
	 * Mittels dieser Methode sendet der AnimationPaintCoord (&uuml;ber den Umweg
	 * UserInterface, MainFrame) dem IODevice die geometrischen Objekte, die f&uuml;r die
	 * Animation darzustellen sind. Diese speichert das IODevice tempor&auml;r ab und
	 * stellt diese dann beim n&auml;chsten Neuzeichnen dar.
	 * 
	 * @param tangentA    Die rotierende Tangente bei Antipode A.
	 * @param tangentC    Die rotierende Tangente bei Antipode C.
	 * @param antipodeA   Die Antipode A.
	 * @param antipodeC   Die Antipode C.
	 * @param bQuadrangle Ein Eckpunkt des gr&ouml;&szlig;ten Vierecks in der konvexen H&uuml;lle.
	 * @param dQuadrangle Ein Eckpunkt des gr&ouml;&szlig;ten Vierecks in der konvexen H&uuml;lle.
	 */

	public void setGeometryForAnimation(DataPoint[] tangentA, DataPoint[] tangentC, DataPoint antipodeA,
			DataPoint antipodeC, DataPoint bQuadrangle, DataPoint dQuadrangle) {
		this.antipodeA = antipodeA;
		this.antipodeC = antipodeC;
		this.bQuadrangle = bQuadrangle;
		this.dQuadrangle = dQuadrangle;
		this.tangentA = tangentA;
		this.tangentC = tangentC;
		this.isAnimate = true;
		this.revalidate();
		this.repaint();

	}

	/**
	 * Auf Nachricht des MainFrames schaltet das IODevice seinen
	 * MouseEventController aus, sodass auf Userinput auf der Zeichenfl&auml;che nicht
	 * reagiert wird. Diese Methode wird ben&ouml;tigt, um eine konsistente Darstellung
	 * der Daten bei der Animation und bei der ZoomInfunktion zu gew&auml;hrleisten.
	 */

	public void switchOffInputDevice() {
		mouseAdapter.switchOffInputDevice();
	}

	/**
	 * Auf Nachricht des MainFrames schaltet das IODevice seinen
	 * MouseEventController ein, sodass auf Userinput auf der Zeichenfl&auml;che reagiert
	 * wird.
	 */
	public void switchOnInputDevice() {
		mouseAdapter.switchOnInputDevice();
	}

	/**
	 * Wird aufgerufen, wenn die Animation beendet wird. Die geometrischen Objekte
	 * f&uuml;r die Animation werden als Ergebnis des Aufrufs dieser Methode beim
	 * Neuzeichnen der Zeichenfl&auml;che nicht mehr dargestellt.
	 */
	public void noAnimationToPaint() {
		isAnimate = false;
		tangentA = null;
		tangentC = null;
		antipodeA = null;
		antipodeC = null;
		dQuadrangle = null;
		bQuadrangle = null;

	}
}
