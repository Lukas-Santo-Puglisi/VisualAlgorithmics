package propra22.q5395950.generalUtility;

/**
 * Ein Exemplar dieser Klasse repr&auml;sentiert einen zweidimensionalen Punkt 
 * in der euklidischen Ebene. Zwei Datenpunkte werden als gleich betrachtet, 
 * sofern sie die gleichen Ortskoordinaten haben.  
 * 
 * @author LukasSanto Puglisi
 */
public class DataPoint {
	/**
	 * Die x Koordinate des zweidimensionalen Punktes.
	 */
//	private long x;
	private int x;
	/**
	 * Die y Koordinate des zweidimensionalen Punktes.
	 */
//	private long y;
	private int y;

	/**
	 * Erzeugt ein Exemplar dieser Klasse. Die Klasse merkt sich seine x und seine y
	 * Koordinate.
	 * 
	 * @param x Die x Koordinate.
	 * @param y Die y Koordinate.
	 */
//	public DataPoint(long x, long y) {
//		this.x = x;
//		this.y = y;
//	}
	public DataPoint(int x, int y) {
		this.x = x;
		this.y = y;
	}

	/**
	 * Gibt die x Koordinate des Datenpunkts zur&uuml;ck.
	 * 
	 * @return Die x Koordinate des Datenpunkts.
	 */
//	public long getX() {
//		return x;
//	}

	public int getX() {
		return x;
	}

	/**
	 * Gibt die y Koordinate des Datenpunkts zur&uuml;ck.
	 * 
	 * @return Die y Koordinate des Datenpunkts.
	 */
//	public long getY() {
//		return y;
//	}
	public int getY() {
		return y;
	}

	/**
	 * Setzt die x Koordinate des Datenpunkts.
	 * 
	 * @param x Der Wert auf den die x Koordinate gesetzt werden soll.
	 */
//	public void setX(long x) {
//		this.x = x;
//	}
	public void setX(int x) {
		this.x = x;
	}

	/**
	 * Setzt die y Koordinate des Datenpunkts.
	 * 
	 * @param y Der Wert auf den die y Koordinate gesetzt werden soll.
	 */
//	public void setY(long y) {
//		this.y = y;
//	}
	public void setY(int y) {
		this.y = y;
	}

	@Override
	public String toString() {
		return "(" + x + "," + y + ")";

	}
	
	@Override
	public boolean equals(Object o) {
		if (o instanceof DataPoint) {
			DataPoint comparison = (DataPoint) o;
			if (comparison.getX() == this.getX() && comparison.getY() == this.getY()) {
				return true;
			} else
				return false;

		} else {
			return false;
		}

	}

	

}
