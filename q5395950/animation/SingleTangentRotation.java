package propra22.q5395950.animation;

import java.util.ArrayList;
import propra22.q5395950.generalUtility.DataPoint;
import propra22.q5395950.generalUtility.GeometryCalc;

/**
 * Ein Exemplar dieser Klasse liefert dem AnimationCalc die Menge der Tangenten,
 * die f&uuml;r die Animation bei einem Antipodenpaar ben&ouml;tigt werden.
 * 
 * @author LukasSanto Puglisi
 */
public class SingleTangentRotation {
	/**
	 * F&uuml;r die Animation habe ich festgelegt, dass die Tangentenrotation in
	 * Ein_Grad_Schritten erfolgen soll.
	 */
	private static double degreeSteps = 1.0;

	/**
	 * Die Methode liefert die Menge der Tangenten die bei der Rotation einer
	 * Tangente mit dem Zentrum center, und linkem Endpunkt startPoint entstehen,
	 * bis der rechte Endpunkt der Tangente auf die durch center und endPoint
	 * definierte Linie st&ouml;&szlig;t.
	 * 
	 * @param center        Zentrum der Tangente
	 * @param startPoint    Definiert Startlinie der Tangente
	 * @param endPoint      Definiert Endlinie der Tangente
	 * @param tangentLength L&auml;nge der Tangente
	 * @return Menge der Rotationstangenten f&uuml;r das ParamTriple
	 */

	public static ArrayList<DataPoint[]> getTangents(DataPoint center, DataPoint startPoint, DataPoint endPoint,
			long tangentLength) {
		ArrayList<DataPoint[]> tangentList = new ArrayList<DataPoint[]>();
		double currentDegree = 0.0;

		DataPoint rotatedPoint = SingleTangentRotation.rotatePointAroundCenter(center, startPoint, currentDegree);
		DataPoint[] tangent = SingleTangentRotation.getTangentForRotatedPoint(center, rotatedPoint, tangentLength);
		DataPoint rightTangent = tangent[0];
		tangentList.add(tangent);
		while (GeometryCalc.getAngleCAandB(center, endPoint, rightTangent) > 5) {
			currentDegree = currentDegree + degreeSteps;
			rotatedPoint = SingleTangentRotation.rotatePointAroundCenter(center, startPoint, currentDegree);
			DataPoint nextPoint = new DataPoint(rotatedPoint.getX(), rotatedPoint.getY());
			tangent = SingleTangentRotation.getTangentForRotatedPoint(center, nextPoint, tangentLength);
			rightTangent = tangent[0];
			tangentList.add(tangent);
		}
		return tangentList;
	}

	/**
	 * Berechnet die Tangente mit Zentrum center, die auf der Linie liegt, die durch
	 * center und rotatedPoint definiert wird.
	 * 
	 * @param center        Zentrum
	 * @param rotatedPoint  rotierte Punkt
	 * @param tangentLength gezeichnete Tangentenl&auml;nge
	 * @return Linker und rechter Endpunkt der Tangente
	 */

	private static DataPoint[] getTangentForRotatedPoint(DataPoint center, DataPoint rotatedPoint, long tangentLength) {
		double tangentLenght = tangentLength;
		double normalizingFactor = 1.0 / Math.sqrt(GeometryCalc.quadraticDistanceDataPoints(center, rotatedPoint));
		double scale = tangentLenght * normalizingFactor;
//		long rightTangentX = (long) (center.getX() + scale * (rotatedPoint.getX() - center.getX()));
//		long rightTangentY = (long) (center.getY() + scale * (rotatedPoint.getY() - center.getY()));
		int rightTangentX = (int) (center.getX() + scale * (rotatedPoint.getX() - center.getX()));
		int rightTangentY = (int) (center.getY() + scale * (rotatedPoint.getY() - center.getY()));
		
		DataPoint rightTangent = new DataPoint(rightTangentX, rightTangentY);

//		long leftTangentX = (long) (center.getX() - scale * (rotatedPoint.getX() - center.getX()));
//		long leftTangentY = (long) (center.getY() - scale * (rotatedPoint.getY() - center.getY()));
		int leftTangentX = (int) (center.getX() - scale * (rotatedPoint.getX() - center.getX()));
		int leftTangentY = (int) (center.getY() - scale * (rotatedPoint.getY() - center.getY()));
		
		DataPoint leftTangent = new DataPoint(leftTangentX, leftTangentY);
		DataPoint[] tangents = new DataPoint[2];
		tangents[0] = leftTangent;
		tangents[1] = rightTangent;
		return tangents;
	}

	/**
	 * Diese Methode rotiert einen Punkt um ein Zentrum gegen den Uhrzeigersinn.
	 * 
	 * @param center   Das Zentrum
	 * @param toRotate Der zu rotierende Punkt
	 * @param degree   Die Gradzahl, um die rotiert werden soll
	 * @return Der rotierte Punkt
	 */

	public static DataPoint rotatePointAroundCenter(DataPoint center, DataPoint toRotate, double degree) {
		degree = 360.0 - degree;
		double radian = Math.toRadians(degree);
//		long xTilde = toRotate.getX();
//		long yTilde = toRotate.getY();
//
//		long x = center.getX();
//		long y = center.getY();
		
		int xTilde = toRotate.getX();
		int yTilde = toRotate.getY();

		int x = center.getX();
		int y = center.getY();

		double rotatedX = ((xTilde - x) * Math.cos(radian) - (yTilde - y) * Math.sin(radian) + x);
		double rotatedY = ((xTilde - x) * Math.sin(radian) + (yTilde - y) * Math.cos(radian) + y);
//		return new DataPoint((long) rotatedX, (long) rotatedY);
		return new DataPoint( (int) rotatedX, (int) rotatedY);
	}

}
