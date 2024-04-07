package propra22.q5395950.myTests;

import propra22.q5395950.generalUtility.DataPoint;
import propra22.q5395950.generalUtility.GeometryCalc;
/**
 * Mit dieser TestKlasse werden die Methoden isHigher 
 * sowie quadrangleArea der Klasse GeometryCalc fuuml;r ein Viereck getestet. 
 * @author LukasSanto Puglisi
 *
 * @param message
 * @return returnValue
 * @throws forExceptions
 *
 */
public class isHigherTest {

	public static void main(String[] args) {
		DataPoint a = new DataPoint(100, 200);
		DataPoint b = new DataPoint(200, -100);
		DataPoint c = new DataPoint(700, 200);
		DataPoint d = new DataPoint(300, 400);
		DataPoint dOther = new DataPoint(200, 450);
		System.out.println(GeometryCalc.isHigher(c, a, b, a));
		System.out.println(GeometryCalc.isHigher(c, a, d, dOther));

		

		DataPoint x = new DataPoint(0, 0);
		DataPoint y = new DataPoint(500, 0);
		DataPoint z = new DataPoint(500, 500);
		DataPoint w = new DataPoint(0, 500);
		System.out.println("Die Flaeche des Vierecks mit Koordinaten");
		System.out.println("x = (0, 0) ; y = (500, 0); z = (0, 500); w = (500, 500");
		System.out.println("es wird erwartet: 250000.0");
		double resultat = GeometryCalc.quadrangleArea(x, y, z, w);
		System.out.println("das resultat ist: " + resultat);
	}

}
