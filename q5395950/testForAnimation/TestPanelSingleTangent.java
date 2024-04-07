package propra22.q5395950.testForAnimation;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.JPanel;

import propra22.q5395950.animation.SingleTangentRotation;
import propra22.q5395950.generalUtility.DataPoint;
/**
 * Mit einem Exemplar dieser Klasse wird die Rotation einer Tangent bei einem Datenpunkt von 
 * einem Startpunkt hin zu einem Endpunkt getestet. 
 * @author LukasSanto Puglisi
 *
 * @param message
 * @return returnValue
 * @throws forExceptions
 *
 */
public class TestPanelSingleTangent extends JPanel{
	DataPoint center;
	DataPoint startPoint;
	DataPoint endPoint;
	
	public ArrayList<DataPoint[]> savedPoints = new ArrayList<DataPoint[]>();
	
	public TestPanelSingleTangent() {
		center = new DataPoint(65, 376);
		startPoint = new DataPoint(372, 106);
		endPoint = new DataPoint(125, 520);
		
		ArrayList<DataPoint[]> tangentForRotatedPoint = SingleTangentRotation.getTangents(center, startPoint, endPoint, 150);
		savedPoints = tangentForRotatedPoint;
		
	}
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		paintCenterStartEndPoint(g2);
		paintTangents(g2);
	}
	
		
	private void paintTangents(Graphics2D g2) {
		Iterator<DataPoint[]> itr = savedPoints.iterator();
		while (itr.hasNext()) {
			DataPoint[] tangent = itr.next();
			DataPoint leftTangent = tangent[0];
			DataPoint rightTangent = tangent[1];
			
			g2.setColor(Color.red);
	        g2.fillOval( (int) leftTangent.getX() - 5, (int)  leftTangent.getY() - 5, 10, 10);
	        g2.setColor(Color.blue);
	        g2.fillOval( (int) rightTangent.getX() - 5, (int) rightTangent.getY() - 5, 10, 10);
			g2.setColor(Color.black);
			g2.drawLine( (int) leftTangent.getX(), (int) leftTangent.getY(), (int) rightTangent.getX(), (int) rightTangent.getY());  
			
		}
		
		
	}
	private void paintCenterStartEndPoint(Graphics2D g2) {
        g2.setColor(Color.red);
        g2.fillOval( (int) center.getX() - 5, (int)  center.getY() - 5, 10, 10);
        g2.setColor(Color.blue);
        g2.fillOval( (int) startPoint.getX() - 5, (int) startPoint.getY() - 5, 10, 10);
        g2.setColor(Color.orange);
        g2.fillOval( (int) endPoint.getX() - 5, (int)  endPoint.getY() -5, 10, 10);
    }
}
