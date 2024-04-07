package propra22.q5395950.myTests;

import propra22.q5395950.generalUtility.DataModelI;
import propra22.q5395950.model.DataModel;
import propra22.q5395950.packageWithMainClass.HullCalculator;
/**
 * In dieser Klasse wird das Laden einer Datei und die Berechnung der konvexen H&uuml;lle
 * durch ein Exemplar der Klasse HullCalculator getestet.
 * @author LukasSanto Puglisi
 */
public class HullCalculatorTest {
	
	public static void main(String[] args) throws Exception {
		HullCalculator hc = new HullCalculator();
		String s = "..\\ProPra-SS22-Basis\\data\\random99.points";
		hc.addPointsFromFile(s);
		int[][] x = hc.getConvexHull();
		for (int i = 0; i < x.length; i++) {
			System.out.print(x[i][0]);
			System.out.print(" ");
			System.out.println(x[i][1]);
		}
		
		
		
	}
	

}
