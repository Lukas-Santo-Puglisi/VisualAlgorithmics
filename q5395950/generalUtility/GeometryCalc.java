package propra22.q5395950.generalUtility;

import java.math.BigInteger;

/**
 * Diese Klasse verf&uuml;gt auschlie&szlig &uuml;ber eine Vielzahl von
 * statischen Methoden, mit denen geometrische Beziehungen zwischen mehreren
 * Datenpunkten ermittelt werden k&ouml;nnen.
 * 
 * @author LukasSanto Puglisi
 */
public class GeometryCalc {
	/**
	 * Berechnet die Fl&auml;che eines Vierecks, das durch die vier in den
	 * Parametern angegebenen Datenpunkte aufgespannt wird.
	 * 
	 * @param a Eckpunkt des Vierecks.
	 * @param b Eckpunkt des Vierecks.
	 * @param c Eckpunkt des Vierecks.
	 * @param d Eckpunkt des Vierecks.
	 * @return Die Fl&auml;che des Vierecks.
	 */

	public static double quadrangleArea(DataPoint a, DataPoint b, DataPoint c, DataPoint d) {
		return (GeometryCalc.isLeftLong(a, b, c) + GeometryCalc.isLeftLong(a, c, d)) / 2;
	}

	/**
	 * Die Datenpunkte left und right spannen eine Linie im euklidischen Raum auf.
	 * Die Methode testet ob der Datenpunkt query links oder auf der Linie liegt.
	 * Falls der Wert positiv ist, liegt der Wert links, falls der Wert null ist auf
	 * und falls der Wert negativ ist, unter der Linie. Die Methode wird
	 * au&szlig;erdem benutzt, um die Fl&auml;che eines Vierecks zu berechnen.
	 * 
	 * @param left  Der linke Datenpunkt der Linie.
	 * @param right Der rechte Datenpunkt der Linie.
	 * @param query Der zu testende Datenpunkt.
	 * @return Den long Wert, aus dem die Lage von query bez&uuml;glich der Linie
	 *         geschlossen werden kann.
	 */
//	private static long isLeftLong(DataPoint left, DataPoint right, DataPoint query) {
//		long sum1 = left.getX() * (right.getY() - query.getY());
//		long sum2 = right.getX() * (query.getY() - left.getY());
//		long sum3 = query.getX() * (left.getY() - right.getY());
//		// consider inverted coordinate orientation on screen
//		return sum1 + sum2 + sum3;
//	}
	private static int isLeftLong(DataPoint left, DataPoint right, DataPoint query) {
		int sum1 = left.getX() * (right.getY() - query.getY());
		int sum2 = right.getX() * (query.getY() - left.getY());
		int sum3 = query.getX() * (left.getY() - right.getY());
		// consider inverted coordinate orientation on screen
		return sum1 + sum2 + sum3;
	}

	/*
	 * compares the two triangles defined by a, c and d/dOther, respectively. The
	 * comparison is done such that a is followed by d is followed by dOther is
	 * followed by c in anti-clockwise direction.
	 */
	/**
	 * Diese Methode implementiert den Vergleich von Dreiecksh&ouml;hen und wird zur
	 * Berechnung des gr&ouml;&szlig;ten Vierecks innerhalb einer konvexen
	 * H&uuml;lle verwendet. Verglichen wird das Dreieck, das durch a, c, d
	 * aufgespannt wird mit dem Dreieck, das durch a, c, dOther aufgespannt wird.
	 * 
	 * @param a      Eckpunkt der Dreiecke c a d und c a dOther.
	 * @param c      Eckpunkt der Dreiecke a c d und a c dOther.
	 * @param d      Eckpunkt des Dreiecks a c d.
	 * @param dOther Eckpunkt des Dreiecks a c dOther.
	 * @return true, wenn a c dOther gr&ouml;&szlig;er ist als a c d.
	 */
//	public static boolean isHigher(DataPoint a, DataPoint c, DataPoint d, DataPoint dOther) {
//		long aPlusDotherMinusCX = a.getX() + dOther.getX() - c.getX();
//		long aPlusDotherMinusCY = a.getY() + dOther.getY() - c.getY();
//		DataPoint aPlusDotherMinusC = new DataPoint(aPlusDotherMinusCX, aPlusDotherMinusCY);
//		// consider inverted coordinate orientation on screen hence invert the sign
//		return isLeftLong(aPlusDotherMinusC, dOther, d) > 0;
//	}
	public static boolean isHigher(DataPoint a, DataPoint c, DataPoint d, DataPoint dOther) {
		int aPlusDotherMinusCX = a.getX() + dOther.getX() - c.getX();
		int aPlusDotherMinusCY = a.getY() + dOther.getY() - c.getY();
		DataPoint aPlusDotherMinusC = new DataPoint(aPlusDotherMinusCX, aPlusDotherMinusCY);
		// consider inverted coordinate orientation on screen hence invert the sign
		return isLeftLong(aPlusDotherMinusC, dOther, d) > 0;
	}

	/**
	 * Der Winkel zwischen a b und a afterA wird mit dem Winkel zwischen a b und b
	 * afterB verglichen. Der Wert ist negativ, falls der erste Winkel
	 * gr&ouml;&szlig;er ist, positv, falls der zweite Winkel gr&ouml;&szlig;er ist
	 * und null, falls beide Winkel gleich gro&szlig; sind. Die Methode wird bei der
	 * Ermittlung des gr&ouml;&szlig;ten Durchmessers verwendet in der konvexen
	 * H&uuml;lle verwendet. Hierbei wird festgestellt, welche der beiden parallelen
	 * Schenkel des Messschiebers an den Antipodenpunkten A und C zuerst bei afterA
	 * und afterC ankommen, wenn beide gleichzeitig gedreht werden.
	 * 
	 * @param antipodeA Antipode A.
	 * @param afterA    Punkt der in der konvexen H&uuml;lle auf A folgt.
	 * @param antipodeC Antipode C.
	 * @param afterC    Punkt der in der konvexen H&uuml;lle auf A folgt.
	 * @return Der Wert aus dem ermittelt wird, welcher der beiden Winkel
	 *         gr&ouml;&szlig;er ist.
	 */

//	public static long angleComparison(DataPoint antipodeA, DataPoint afterA, DataPoint antipodeC, DataPoint afterC) {
//		long bPlusAMinusAfterBX = antipodeC.getX() + antipodeA.getX() - afterC.getX();
//		long bPlusAMinusAfterBY = antipodeC.getY() + antipodeA.getY() - afterC.getY();
//		DataPoint c = new DataPoint(bPlusAMinusAfterBX, bPlusAMinusAfterBY);
//		return isLeftLong(antipodeA, afterA, c);
//	}

	public static int angleComparison(DataPoint antipodeA, DataPoint afterA, DataPoint antipodeC, DataPoint afterC) {
		int bPlusAMinusAfterBX = antipodeC.getX() + antipodeA.getX() - afterC.getX();
		int bPlusAMinusAfterBY = antipodeC.getY() + antipodeA.getY() - afterC.getY();
		DataPoint c = new DataPoint(bPlusAMinusAfterBX, bPlusAMinusAfterBY);
		return isLeftLong(antipodeA, afterA, c);
	}

	/**
	 * Diese Methode testet ob die Linie zwischen den Datenpunkten a und afterA
	 * k&uuml;rzer ist als die Linie zwischen den Datenpunkten b undf afterB.
	 * 
	 * @param a      Linkes Ende der Linie a afterA.
	 * @param afterA Rechtes Ende der Linie a afterA.
	 * @param b      Linkes Ende der Linie b afterB.
	 * @param afterB Rechtes Ende der Linie b afterB.
	 * @return true, falls die Linie a afterA kleiner ist.
	 */
//	public static boolean isShorter(DataPoint a, DataPoint afterA, DataPoint b, DataPoint afterB) {
//		long aAfterADistance = GeometryCalc.quadraticDistanceDataPoints(a, afterA);
//		long bAfterBDistance = GeometryCalc.quadraticDistanceDataPoints(b, afterB);
//
//		return aAfterADistance < bAfterBDistance;
//	}
	public static boolean isShorter(DataPoint a, DataPoint afterA, DataPoint b, DataPoint afterB) {
		int aAfterADistance = GeometryCalc.quadraticDistanceDataPoints(a, afterA);
		int bAfterBDistance = GeometryCalc.quadraticDistanceDataPoints(b, afterB);

		return aAfterADistance < bAfterBDistance;
	}

	/**
	 * Berechnet die quadratische euklidische Distanz zwischen den Datenpunkten a
	 * und b.
	 * 
	 * @param a Datenpunkt a.
	 * @param b Datenpunkt b.
	 * @return Quadratische euklidische Distanz zwischen den Datenpunkten a und b.
	 */
//	public static long quadraticDistanceDataPoints(DataPoint a, DataPoint b) {
//		long xDistance = (long) Math.pow(a.getX() - b.getX(), 2);
//		long yDistance = (long) Math.pow(a.getY() - b.getY(), 2);
//		return xDistance + yDistance;
//	}
	public static int quadraticDistanceDataPoints(DataPoint a, DataPoint b) {
		int xDistance = (int) Math.pow(a.getX() - b.getX(), 2);
		int yDistance = (int) Math.pow(a.getY() - b.getY(), 2);
		return xDistance + yDistance;
	}

	/**
	 * Berechnet die quadratische euklidische Distanz zwischen den Koordinaten (x1,
	 * y1) und (x2, y2).
	 * 
	 * @param x1 x-Koordinate des ersten Datenpunkts.
	 * @param y1 y-Koordinate des ersten Datenpunkts.
	 * @param x2 x-Koordinate des zweiten Datenpunkts.
	 * @param y2 y-Koordinate des zweiten Datenpunkts.
	 * @return Die euklidische Distanz zwischen den Koordinaten (x1, y1) und (x2,
	 *         y2).
	 */
//	public static long quadraticDistanceCoordinates(long x1, long y1, long x2, long y2) {
//		long xDistance = (long) Math.pow(x1 - x2, 2);
//		long yDistance = (long) Math.pow(y1 - y2, 2);
//		return xDistance + yDistance;
//	}
	public static int quadraticDistanceCoordinates(int x1, int y1, int x2, int y2) {
		int xDistance = (int) Math.pow(x1 - x2, 2);
		int yDistance = (int) Math.pow(y1 - y2, 2);
		return xDistance + yDistance;
	}

	/**
	 * Berechnet den Winkel zwischen den Datenpunkten center endpoint und
	 * tangentEnd. Diese Methode wird als Stoppkriterium bei der Rotation der
	 * Tangenten w&auml;hrend der Animation verwendet.
	 * 
	 * @param center     Datenpunkt c.
	 * @param endPoint   Datenpunkt a.
	 * @param tangentEnd Datenpunkt b.
	 * @return Der Winkel zwischen den Datenpunkten center endpoint und tangentEnd.
	 */

	public static double getAngleCAandB(DataPoint center, DataPoint endPoint, DataPoint tangentEnd) {
		double caX = endPoint.getX() - center.getX();
		double caY = endPoint.getY() - center.getY();
		double cbX = tangentEnd.getX() - center.getX();
		double cbY = tangentEnd.getY() - center.getY();

		double v = (caX * cbX + caY * cbY);
		double w = Math.sqrt(caX * caX + caY * caY);
		double z = Math.sqrt(cbX * cbX + cbY * cbY);
		double angle = Math.acos(v / (w * z));
		angle = Math.toDegrees(angle);
		return angle;
	}

	/**
	 * Die Datenpunkte left und right spannen eine Linie im euklidischen Raum auf.
	 * Die Methode testet ob der Datenpunkt query links oder auf der Linie liegt und
	 * gibt true zur&uuml;ck falls dies der Fall ist. Bei der Methode wird
	 * ber&uuml;cksichtigt, dass die y Bildschirmkoordinaten im Vergleich zum
	 * kartesischen Koordinatensystem invertiert sind.
	 * 
	 * @param left  Der linke Datenpunkt der Linie.
	 * @param right Der rechte Datenpunkt der Linie.
	 * @param query Der zu testende Datenpunkt
	 * @return true falls der query Datenpunkt links oder auf der Linie left right
	 *         liegt.
	 * 
	 */

//	public static boolean isLeftBoolean(DataPoint left, DataPoint right, DataPoint query) {
//		long sum1 = left.getX() * (right.getY() - query.getY());
//		long sum2 = right.getX() * (query.getY() - left.getY());
//		long sum3 = query.getX() * (left.getY() - right.getY());
//		// consider inverted coordinate orientation on screen
//	System.out.println(sum3);
//		return (sum1 + sum2 + sum3) <= 0;
//	}
//	public static boolean isLeftBoolean(DataPoint left, DataPoint right, DataPoint query) {
//		BigInteger leftX = BigInteger.valueOf((long) left.getX());
//		BigInteger leftY = BigInteger.valueOf( (long) left.getY()); 
//		BigInteger rightX = BigInteger.valueOf((long) right.getX());
//		BigInteger rightY = BigInteger.valueOf((long) right.getY());
//		BigInteger queryX = BigInteger.valueOf((long) query.getX());
//		BigInteger queryY = BigInteger.valueOf((long) query.getY());
//	1654826228
//	-265026676
//	-1417938242
//	426263760
//		BigInteger sum1 = leftX.multiply((rightY.subtract(queryY)));
//		BigInteger sum2 = rightX.multiply((queryY.subtract(leftY)));
//		BigInteger sum3 = queryX.multiply((leftY.subtract(rightY)));
//		// consider inverted coordinate orientation on screen
//		System.out.println(sum3);
//		BigInteger result = sum1.add(sum2.add(sum3));
//		if (result.signum() == -1) {
//			return true;
//		}
//		if (result.signum() == 0) {
//			return true;
//		} else {
//			return false;
//		}
//	}
	public static boolean isLeftBoolean(DataPoint left, DataPoint right, DataPoint query) {
	long sum1 = ((long) left.getX()) * ( (long) right.getY() - (long) query.getY());
	long sum2 = ((long)  right.getX()) * ( (long) query.getY() - (long) left.getY());
	long sum3 = ((long)  query.getX()) * ( (long) left.getY() - (long) right.getY());
	System.out.println(sum3);
	// consider inverted coordinate orientation on screen
	return (sum1 + sum2 + sum3) <= 0;
}		
	

}
