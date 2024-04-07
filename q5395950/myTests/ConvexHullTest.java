package propra22.q5395950.myTests;

import java.util.*;
import propra22.q5395950.generalUtility.DataPoint;
import propra22.q5395950.model.*;

/**
 * In dieser Klasse wird das Berechnen der konvexen H&uuml;lle aus einer durch ein
 * Exemplar der Klasse ContourCalc berechneten Kontour getestet. Hierzu muuml;ssen
 * voruuml;bergehend die Sichtbarkeiten der Klassen ContourCalc und ConvexHullCalc
 * auf public gesetzt werden.
 * 
 * @author LukasSanto Puglisi
 */
public class ConvexHullTest {
	public static void main(String[] args) {
		ArrayList<DataPoint> list3 = new ArrayList<DataPoint>();
		list3.add(new DataPoint(100, 200));
		list3.add(new DataPoint(300, 200));
		list3.add(new DataPoint(200, 400));

//		ContourCalc cc = new ContourCalc(list3);
//		ArrayList<DataPoint> contourList = cc.getContour();

//		ConvexHullCalc chc = new ConvexHullCalc(contourList);
//		ArrayList<DataPoint> convexList = chc.getConvexHull();

//		Iterator<DataPoint> itr = convexList.iterator();

//		while (itr.hasNext()) {
//			System.out.println(itr.next());
//		}

	}
}
