package propra22.q5395950.myTests;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.ListIterator;

import propra22.q5395950.generalUtility.DataPoint;
import propra22.q5395950.model.ConturCalc;
import propra22.q5395950.model.ConvexHullCalc;
import propra22.q5395950.model.SquareAndDiameterCalc;

public class DiameterCalcDurchlaufTest {
	public static void main(String[] args) {
		
	
		ArrayList<DataPoint> list3 = new ArrayList<DataPoint>();
        list3.add(new DataPoint(100, 200));
        list3.add(new DataPoint(200, 400));
        list3.add(new DataPoint(300, 200));
        
        ConturCalc cc = new ConturCalc(list3);
        ArrayList<DataPoint> contourList = cc.getContour();
        ConvexHullCalc chc = new ConvexHullCalc(contourList);
        
        ArrayList<DataPoint> convexList = chc.getConvexHull();
        SquareAndDiameterCalc dc = new SquareAndDiameterCalc(convexList);
        dc.getQuadrangle();
        
//        ArrayList<MyPoint> gegenDenUhrzeigersinn = dc.getModifiedCopyOfConvexHull();
//        ListIterator<MyPoint> itr = gegenDenUhrzeigersinn.listIterator();
//        while(itr.hasNext()) {
//        	System.out.println(itr.next());
//        }
        
	}
        

}
