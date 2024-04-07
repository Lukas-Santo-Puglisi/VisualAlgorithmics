package propra22.q5395950.gui;

import java.awt.Dimension;
import java.awt.geom.AffineTransform;

import propra22.q5395950.generalUtility.DataModelI;

/**
 * Diese Klasse stellt mittels der statischen Methode getTransform dem IODevice
 * eine M&ouml;glichkeit zur Verf&uuml;gung die Daten f&uuml;r die Sicht auf die Daten zu
 * transformieren, sodass die eigentlichen Daten unver&auml;ndert bleiben, aber die
 * Daten so dargestellt werden k&ouml;nnen, dass sie unabh&auml;ngig von ihrer
 * eigentlichen Lage im Koordinatensystem zentriert auf der Zeichenfl&auml;che
 * erscheinen.
 * 
 * @author LukasSanto Puglisi
 *
 */
public class ZoomInCalc {
	/**
	 * Mittels dieser statischen Methode erh&auml;lt das IODevice ein Exemplar der Klasse
	 * AffineTransform. Dieses Exemplar gibt dem IODevice die M&ouml;glichkeit die Sicht
	 * auf die Daten zu transformieren, sodass die eigentlichen Daten unver&auml;ndert
	 * bleiben, aber die Daten so dargestellt werden k&ouml;nnen, dass sie unabh&auml;ngig von
	 * ihrer eigentlichen Lage im Koordinatensystem zentriert auf der Zeichenfl&auml;che
	 * erscheinen.
	 * 
	 * @param model        Das Datenmodell, mit dem die gegenw&auml;rtige Lage der
	 *                     aktuellen Datenpunkte ermittelt werden k&ouml;nnen.
	 * @param sizeIODevice Die Daten werde bez&uuml;glich der Gr&ouml;&szlig;e der Zeichenfl&auml;che
	 *                     transformiert
	 * @return Die Affine Transformation die das graphics Objet zum Zeichnen der
	 *         Zeichenfl&auml;che benutzt, um die Daten geeignet darzustellen.
	 */
	public static AffineTransform getTransform(DataModelI model, Dimension sizeIODevice) {
		int[][] quadrangle = model.getQuadrangle();
		int currentMinX = quadrangle[0][0];
		int currentMaxX = quadrangle[0][0];
		int currentMinY = quadrangle[0][1];
		int currentMaxY = quadrangle[0][1];
		for (int i = 1; i < 4; i++) {
			if (quadrangle[i][0] < currentMinX) {
				currentMinX = quadrangle[i][0];
			}
			if (quadrangle[i][1] < currentMinY) {
				currentMinY = quadrangle[i][1];
			}
			if (quadrangle[i][0] > currentMaxX) {
				currentMaxX = quadrangle[i][0];
			}
			if (quadrangle[i][1] > currentMaxY) {
				currentMaxY = quadrangle[i][1];
			}
		}
		Dimension dimension = sizeIODevice;
		AffineTransform startMatrix = new AffineTransform();
		AffineTransform translate1 = AffineTransform.getTranslateInstance((double) (-currentMinX),
				(double) (-currentMinY));
		double xScale = 1.0 / (double) (currentMaxX - currentMinX);
		double yScale = 1.0 / (double) (currentMaxY - currentMinY);
		xScale = xScale * dimension.width * 0.7;
		yScale = yScale * dimension.height * 0.7;

		AffineTransform scale1 = AffineTransform.getScaleInstance(xScale, yScale);
		AffineTransform translate2 = AffineTransform.getTranslateInstance((dimension.width * 0.1),
				(dimension.height * 0.1));
		startMatrix.concatenate(translate2);
		startMatrix.concatenate(scale1);
		startMatrix.concatenate(translate1);

		return startMatrix;
	}

}
