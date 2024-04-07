package propra22.q5395950.myTests;

import java.util.ArrayList;

import propra22.q5395950.generalUtility.CircleIterator;
import propra22.q5395950.generalUtility.DataPoint;

/**
 * In dieser Klasse wird die Vorw&auml;rts und R&uuml;ckw&auml;rtsiteration der
 * selbsterstellten Klasse CircleIterator durch eine ArrayList getestet.
 * @author LukasSanto Puglisi
 */
public class CircleIteratorTest {
	public static void main(String[] args) {
		ArrayList<DataPoint> list3 = new ArrayList<DataPoint>();
		list3.add(new DataPoint(100, 200));
		list3.add(new DataPoint(300, 200));
		list3.add(new DataPoint(200, 100));
		CircleIterator cForward = new CircleIterator(list3);
		for (int i = 0; i < 7; i++) {
			System.out.println(cForward.getNextPoint());
		}
		System.out.println("now backwards");
		CircleIterator cBackwoard = new CircleIterator(list3);
		for (int i = 0; i < 7; i++) {
			System.out.println(cBackwoard.getPreviousPoint());
		}
	}

}
