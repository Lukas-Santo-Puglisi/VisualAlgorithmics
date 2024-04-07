package propra22.q5395950.gui;

import java.awt.*;

import javax.swing.*;

import propra22.q5395950.controller.*;
import propra22.q5395950.generalUtility.DataModelI;
import propra22.q5395950.generalUtility.DataPoint;
import propra22.q5395950.generalUtility.UserInterface;

/**
 * Ein Exemplar dieser Klasse verbindet die verschiedenen Komponenten der Sicht,
 * welche zusammen durch das UserInterface, die Men&uuml;leiste sowie die Buttons und
 * Checkboxes und den Animationspeedslider implementiert werden. Die Details und
 * die Funktionalit&auml;t dieser Komponenten werden in eigenen Unterklassen von
 * JPanel realisiert, w&auml;hrend das MainFrame lediglich eine Referenz auf
 * Komponenten erh&auml;lt.
 * 
 * @author LukasSanto Puglisi
 *
 */

public class MainFrame extends JFrame implements UserInterface {
	/**
	 * Ein Exemplar der Klasse MainFrame erh&auml;lt eine Referenz auf das IODevice, kann
	 * es ein und ausschalten, kann es auffordern sich (z.B. bei &auml;nderungen des
	 * Datenmodells) neuzuzeichnen und kann es zum Zeichnen der Animation
	 * auffordern.
	 */
	private IODevice iODevice;
	/**
	 * Ein Exemplar der Klasse MainFrame erh&auml;lt eine Referenz auf ein Exemplar der
	 * Klasse AnimationSpeedSlider. Das MainFrame, kann es w&auml;hrend der Animatio ein-
	 * und nach der Animation ausschalten.
	 */
	private AnimationSpeedSlider animationSpeedSlider;
	/**
	 * Im NorthPanel, das eine Unterklasse von JPanel ist, werden die Buttons zur
	 * Realisierung der zuf&auml;lligen Datenpunktegenerierung und der Undo und
	 * RedoFunktion untegebracht.
	 */
	private NorthPanel northPanel;
	/**
	 * Im SouthPanel, das eine Unterklasse von JPanel ist, werden die JCheckboxes
	 * untergebracht, mit denen eingestellt werden kann, welche geometrischen
	 * Objekte (konvexe H&uuml;lle, gr&ouml;&szlig;ter Durchmesser, gr&ouml;&szlig;tes Viereck, gr&ouml;&szlig;tes
	 * Dreieck) angezeigt werden sollen. Zus&auml;tzlich ist dort der Button
	 * untergebracht, mit dem die Animation gestartet und beendet werden kann.
	 */
	private SouthPanel southPanel;
	/**
	 * An das Exemplar der Klasse MainMenuBar wird die Funktionalit&auml;t &Ouml;ffnen und
	 * Speichern von Dokumenten sowie die Hilfefunktionalit&auml;t weiterdelegiert.
	 */
	private MainMenuBar mainMenuBar;

	/**
	 * Ein Exemplar der Klasse Controller, welches die Applikation startet, erzeugt
	 * das MainFrame. Bei Initialisierung erzeugt der MainFrame alle Komponenten,
	 * die f&uuml;r die Sicht auf die Daten notwendig sind und definiert zus&auml;tzlich seine
	 * eigene Darstellung (Gr&ouml;&szlig;e, Farbe, etc.).
	 * 
	 * @param model      Das MainFrame ben&ouml;tigt selbst keine Referenz auf das
	 *                   Datenmodell, sondern leitet diese lediglich an die
	 *                   Komponenten weiter, die diese ben&ouml;tigen.
	 * @param controller Das MainFrame ben&ouml;tigt selbst keine Referenz auf den
	 *                   Controller, sondern leitet diese lediglich an die
	 *                   Komponenten weiter, die diese ben&ouml;tigen.
	 */

	public MainFrame(DataModelI model, Controller controller) {
		mainMenuBar = new MainMenuBar(this, controller);
		setJMenuBar(mainMenuBar);
		iODevice = new IODevice(model);

		animationSpeedSlider = new AnimationSpeedSlider();
		animationSpeedSlider.setEnabled(false);
		add(animationSpeedSlider, BorderLayout.WEST);

		northPanel = new NorthPanel(model, this);
		add(northPanel, BorderLayout.NORTH);

		getContentPane().add(iODevice, BorderLayout.CENTER);

		southPanel = new SouthPanel(this, model, iODevice, animationSpeedSlider);
		add(southPanel, BorderLayout.SOUTH);

		setMainFrame();
	}

	/**
	 * Setzt den Titel des MainFrames, die Gr&ouml;&szlig;e, und seine Position relativ zum
	 * Bildschirm.
	 */

	private void setMainFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		setSize(dim);
		setLocationRelativeTo(null);
		setTitle("The Linear Geominator by Lukas-Santo Puglisi (Q5395950)");
		setVisible(true);
	}

	/**
	 * Gibt die Gr&ouml;&szlig;e der Zeichenfl&auml;che wieder. Die Buttons zur Generierung der
	 * zuf&auml;lligen Datenpunkte ben&ouml;tigen diese, um Datenpunkte zu Generieren, die
	 * innerhalb der Zeichenfl&auml;che liegen.
	 */
	public Dimension getIODeviceSize() {
		return iODevice.getSize();
	}

	/**
	 * Das MainFrame bietet diese Methode an, mit der die Zeichenfl&auml;che aufgefordert
	 * wird sich neu zuzeichnen. Diese Methode wird Controllklassen aufgerufen, die
	 * das Datenmodell ver&auml;ndert haben, sodass die Sicht aktualisiert werden muss,
	 * um Konsistenz sicherzustellen.
	 */
	@Override
	public void refresh() {
		iODevice.repaint();
	}

	/**
	 * Das MainFrame bietet diese Methode an, um Controllklassen zu erm&ouml;glichen, die
	 * Nutzerin direkt &uuml;ber Besonderheiten zu informieren, die den Programmablauf
	 * &auml;ndern k&ouml;nnen (z.B. Besonderheiten beim Einlesen von Daten, Animation kann
	 * aufgrund von zuwenigen Datenpunkten nicht gestartet werden).
	 * 
	 * @param noteMessage Die Nachricht, die der Benutzerin angezeigt werden soll.
	 */
	@Override
	public void raiseNoteToUser(String noteMessage) {
		JOptionPane.showMessageDialog(MainFrame.this, noteMessage, "Information", JOptionPane.INFORMATION_MESSAGE);

	}

	/**
	 * Benachrichtigt das IODevice zus&auml;tzlich zu den aktuell angezeigten Daten, die
	 * in den Parametern angegebenen geometrischen Objekte zu zeichnen. Diese
	 * Methode wird durch den AnimationPaintCoord w&auml;hrend einer Animation
	 * aufgerufen.
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
	@Override
	public void setGeometryForAnimation(DataPoint[] tangentA, DataPoint[] tangentB, DataPoint antipodeA,
			DataPoint antipodeC, DataPoint bQuadrangle, DataPoint dQuadrangle) {
		iODevice.setGeometryForAnimation(tangentA, tangentB, antipodeA, antipodeC, bQuadrangle, dQuadrangle);

	}

	/**
	 * Weist die GUI an, Input an der Zeichenfl&auml;che, an Buttons, Checkboxen und
	 * an JMenuItems, die bei der Animation st&ouml;ren k&ouml;nnen, zu ignorieren. Der
	 * AnimationSpeedSlider an der GUI wird dagegen eingeschaltet, damit die Nutzerin
	 * die Geschwindigkeit der Animation einstellen kann.
	 */

	@Override
	public void switchOffInput() {
		animationSpeedSlider.setEnabled(true);
		iODevice.switchOffInputDevice();
		northPanel.switchOff();
		southPanel.switchOff();
		mainMenuBar.switchOff();
	}

	/**
	 * Weist alle Komponenten der GUI an, Userinput an der Zeichenfl&auml;che, an
	 * Buttons, Checkboxen und an JMenuItems, der w&auml;hrend der Animation ignoriert
	 * wurde, wieder zu behandeln. Der AnimationSpeedSlider wird dagegen
	 * ausgeschaltet, weil er lediglich zum Einstellen der Geschwindigkeit der
	 * Animation w&auml;hrend der Animation dient.
	 */
	@Override
	public void switchOnInput() {
		animationSpeedSlider.setEnabled(false);
		northPanel.switchOn();
		southPanel.switchOn();
		iODevice.switchOnInputDevice();
		mainMenuBar.switchOn();
	}

	/**
	 * Benachrichtigt das IODevice, dass die Animation beendet ist und keine
	 * geometrischen Objekte zu zeichnen sind. Diese Methode wird durch den
	 * AnimationPaintCoord zur Beendigung einer Animation aufgerufen.
	 */
	@Override
	public void noAnimationToPaint() {
		iODevice.noAnimationToPaint();

	}

}
