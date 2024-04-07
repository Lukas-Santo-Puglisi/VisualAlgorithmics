package propra22.q5395950.testForAnimation;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.AffineTransform;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.ListIterator;

import javax.swing.JPanel;

import propra22.q5395950.animation.SingleTangentRotation;
import propra22.q5395950.generalUtility.DataPoint;
import propra22.q5395950.gui.ZoomInCalc;
/**
 * Testet die Methode rotatePointAroundCenter der Klasse SingleTangentRotation.
 * Hierbei wird der Startpunkt um den DataPoint Center um 270 Grad gedreht.   
 * @author LukasSanto Puglisi
 */
public class TestPanelPoints extends JPanel {

	DataPoint center;
	DataPoint startRotate;

	public ArrayList<DataPoint> savedPoints = new ArrayList<DataPoint>();

	public TestPanelPoints() {
		center = new DataPoint(65, 376);
		startRotate = new DataPoint(125, 520);
		for (int i = -270; i <= 0; i++) {

			DataPoint toAdd = SingleTangentRotation.rotatePointAroundCenter(center, startRotate, i);
			savedPoints.add(toAdd);
		}

	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		paintPoints(g2);
	}

	private void paintPoints(Graphics2D g2) {
		Iterator<DataPoint> pointIterator = savedPoints.iterator();
		while (pointIterator.hasNext()) {
			DataPoint p = pointIterator.next();
			long x = p.getX();
			long y = p.getY();
			long x_verbessert = x - 2;
			long y_verbessert = y - 2;
			g2.fillOval((int) x_verbessert, (int) y_verbessert, 10, 10);

		}
		g2.setColor(Color.red);
		g2.fillOval((int) center.getX() - 5, (int) center.getY() - 5, 10, 10);
		g2.setColor(Color.orange);
		g2.fillOval((int) startRotate.getX() - 5, (int) startRotate.getY() - 5, 10, 10);

	}
}
