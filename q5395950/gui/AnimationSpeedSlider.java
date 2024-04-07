package propra22.q5395950.gui;

import java.awt.Font;
import java.util.Hashtable;

import javax.swing.JLabel;
import javax.swing.JSlider;
/**
 * Ein Exemplar der Klasse AnimationSpeedSlider wird zun&auml;chst ausgeschaltet an der GUI 
 * angebracht. Sobald die Animation startet, kann der User mittels Manipulation des 
 * Sliders die Geschwindigkeit der Animation einstellen. 
 * @author LukasSanto Puglisi
 */

public class AnimationSpeedSlider extends JSlider {
	/**
	 * Repr&auml;sentiert minimale Geschwindigkeit der Animation. 
	 */
	
	static final int Speed_MIN = 0;
	/**
	 * Repr&auml;sentiert die maximale Geschwindigkeit der Animation.  
	 */
	static final int Speed_MAX = 100;
	
	/**
	 * Repr&auml;sentiert die Geschwindgigkeit der Animation bei Start. 
	 */
	static final int Speed_INIT = 50;
	/**
	 * Erzeugt und initialisiert mit Skala sowie Beschriftung ein Exemplar der Klasse. 
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public AnimationSpeedSlider() {
		super(JSlider.VERTICAL, Speed_MIN, Speed_MAX, Speed_INIT);
		setMajorTickSpacing(25);
		setMinorTickSpacing(5);
		setPaintTicks(true);
		setPaintLabels(true);
		setFont(new Font("Serif", Font.ITALIC, 15));
		Hashtable labelTable = new Hashtable();
		labelTable.put( 0 , new JLabel("Langsam") );
		labelTable.put( 50 , new JLabel("Normal") );
		labelTable.put( 100, new JLabel("Schnell") );
		setLabelTable( labelTable );
		setPaintLabels(true);
	}
	/**
	 * Diese Methode wird vom AnimationCoord 
	 * bei Beendigung der Animation aufgerufen, um den Slider wieder auf den mittleren 
	 * Wert zu setzen. 
	 */
	public void reset() {
		setValue(50);
	}
}
