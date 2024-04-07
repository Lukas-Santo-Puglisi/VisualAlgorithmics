package propra22.q5395950.generalUtility;

import java.awt.Dimension;
/**
 * Das UserInterface ist das Interface, das die Schnittstelle zur 
 * GUI der Applikation darstellt. Es wird von der Klasse MainFrame implementiert.
 * 
 * @author LukasSanto Puglisi
 */
public interface UserInterface {
	/**
	 * Veranlasst die GUI, insbesondere die Zeichenfl&auml;che die aktuelle Punktmenge
	 * des Datenmodells zu lesen und sich neu zu zeichnen.
	 */
	public void refresh();

	/**
	 * Zeigt dem User auf der GUI in Form eines Dialogs die Nachricht message an.
	 * 
	 * @param message Die Nachricht die angezeigt werden soll.
	 */
	public void raiseNoteToUser(String message);

	/**
	 * Benachrichtigt die GUI zus&auml;tzlich zu den aktuell angezeigten Daten, die in
	 * den Parametern angegebenen geometrischen Objekte zu zeichnen. Diese Methode * wird durch den AnimationPaintCoord w&auml;hrend einer Animation aufgerufen.
	 * 
	 * @param tangentA    Die zu zeichnende Tangente bei Antipode A.
	 * @param tangentC    Die zu zeichnende Tangente bei Antipode C.
	 * @param antipodeA   Die Antipode A, welche einen Eckpunkt des zu zeichnenden
	 *                    Vierecks darstellt.
	 * @param antipodeC   Die Antipode C, welche einen Eckpunkt des zu zeichnenden
	 *                    Vierecks darstellt.
	 * @param bQuadrangle Ein Eckpunkt des zu zeichnenden Vierecks.
	 * @param dQuadrangle Ein Eckpunkt des zu zeichnenden Vierecks.
	 */

	public void setGeometryForAnimation(DataPoint[] tangentA, DataPoint[] tangentC, DataPoint antipodeA,
			DataPoint antipodeC, DataPoint bQuadrangle, DataPoint dQuadrangle);

	/**
	 * Weist die GUI an, Userinput an der Zeichenfl&auml;che, an Buttons, Checkboxen und
	 * an JMenuItems, die bei der Animation st&ouml;ren k&ouml;nnen, zu ignorieren. Der
	 * AnimationSpeedSlider an der GUI wird dagegen eingeschaltet, damit der User
	 * die Geschwindigkeit der Animation einstellen kann.
	 */

	public void switchOffInput();

	/**
	 * Weist die GUI an, Userinput an der Zeichenfl&auml;che, an Buttons, Checkboxen und
	 * an JMenuItems, der w&auml;hrend der Animation ignoriert wurde, wieder zu
	 * behandeln. Der AnimationSpeedSlider wird dagegen ausgeschaltet, weil er
	 * lediglich zum Einstellen der Geschwindigkeit der Animation w&auml;hrend der
	 * Animation dient.
	 */
	void switchOnInput();

	/**
	 * Benachrichtigt die GUI, dass die Animation beendet ist und keine
	 * geometrischen Objekte zu zeichnen sind. Diese Methode wird durch den
	 * AnimationPaintCoord zur Beendigung einer Animation aufgerufen.
	 */
	public void noAnimationToPaint();

	/**
	 * Gibt die Gr&ouml;&szlig;e der Zeichenfl&auml;che zur&uuml;ck. Diese Methode wird vom
	 * RandomPointGenerator benutzt, damit dieser ausschlie&szlig;lich Datenpunkte
	 * innerhalb der Zeichenfl&auml;che generieren kann.
	 */
	public Dimension getIODeviceSize();
}
