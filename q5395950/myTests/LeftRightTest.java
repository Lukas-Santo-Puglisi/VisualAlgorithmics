package propra22.q5395950.myTests;
import propra22.q5395950.generalUtility.DataPoint;
import propra22.q5395950.generalUtility.GeometryCalc;
/**
 * Mit einem Exemplar dieser Klasse wird die Methode isLeftBoolean der 
 * Klasse GeometryCalc getestet. 
 * @author LukasSanto Puglisi
 */
public class LeftRightTest {
    
    public static void main (String[] args) {
        DataPoint p1 = new DataPoint(-1969, 352);
        DataPoint p2 = new DataPoint(-1976 , 306);
        DataPoint p3 = new DataPoint(-1981, 278);
        boolean test = GeometryCalc.isLeftBoolean(p1, p2, p3);
        System.out.println("test: " +test);
        
        
       
    }
    
    

}
