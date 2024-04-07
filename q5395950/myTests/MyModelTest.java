package propra22.q5395950.myTests;

import java.util.ArrayList;
import java.util.Iterator;

import propra22.q5395950.generalUtility.DataModelI;
import propra22.q5395950.generalUtility.DataPoint;
import propra22.q5395950.model.*;
/**
 * Mit einem Exemplar dieser Klasse wird die Methode setAllPointsFromNew der Klasse 
 * DataModel getestet, mit der Punkte mittels einer ArrayList dem DataModel &uuml;bergeben 
 * werden, sodass die konvexe H&uuml;lle berechnet werden kann. 
 * @author LukasSanto Puglisi
 */
public class MyModelTest {
    public static void main(String[] args) {
        
        ArrayList<DataPoint> list2 = new ArrayList<DataPoint>();
        list2.add(new DataPoint(0, 0));
        list2.add(new DataPoint(1, 1));
        list2.add(new DataPoint(1,22));
        
        DataModelI m = new DataModel();
        m.setAllPointsFromNew(list2);
        ArrayList<DataPoint> convexHull = m.getConvexHull();
        Iterator<DataPoint> itr = convexHull.iterator();
        while (itr.hasNext()) {
            System.out.println(itr.next());
        }
    }
}
