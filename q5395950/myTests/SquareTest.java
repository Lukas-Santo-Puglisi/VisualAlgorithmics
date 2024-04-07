package propra22.q5395950.myTests;

import java.util.ArrayList;
import java.util.ListIterator;

import propra22.q5395950.generalUtility.DataPoint;
import propra22.q5395950.model.ConturCalc;
import propra22.q5395950.model.ConvexHullCalc;
import propra22.q5395950.model.SquareAndDiameterCalc;

public class SquareTest {

	public static void main(String[] args) {
		ArrayList<DataPoint> list3 = new ArrayList<DataPoint>();
		list3.add(new DataPoint(100, 200));
		list3.add(new DataPoint(200, -100));
		list3.add(new DataPoint(700, 200));
		list3.add(new DataPoint(300, 400));
	        
		ConturCalc cc = new ConturCalc(list3);
		ArrayList<DataPoint> contourList = cc.getContour();
		ConvexHullCalc chc = new ConvexHullCalc(contourList);
	        
		ArrayList<DataPoint> convexList = chc.getConvexHull();
		
		SquareAndDiameterCalc squareDiamCalc = new SquareAndDiameterCalc(convexList);
		double area = squareDiamCalc.getQuadrangleArea();
		System.out.println("das area ist: " +area);
		int[][] vertices = squareDiamCalc.getQuadrangle();
//		System.out.println("a =" + vertices[0][0] + " " + vertices[0][1]);
//		System.out.println("b =" + vertices[1][0] + " " + vertices[1][1]);
//		System.out.println("c =" + vertices[2][0] + " " + vertices[2][1]);
//		System.out.println("d =" + vertices[3][0] + " " + vertices[3][1]);
//		ArrayList<MyPoint> gegenDenUhrzeigersinn = squareDiamCalc.getModifiedCopyOfConvexHull();
//		ListIterator<MyPoint> itr = gegenDenUhrzeigersinn.listIterator();
//		while(itr.hasNext()) {
//				System.out.println(itr.next());
//	        }
		}
}
