package propra22.q5395950.gui;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;

/**
 * Das HelpPanel wird dem User in einem Dialog angezeigt, wenn dieser das
 * JMenuItem Hilfe anzeigen, im Hilfemen&uuml; anklickt.
 * 
 * @author LukasSanto Puglisi
 */
public class HelpPanel extends JPanel {
	/**
	 * Dieser String repr&auml;sentiert die Erkl&auml;rung der Men&uuml;leiste und wird dem User
	 * initial angezeigt.
	 */
	private final String menuBarString = "Das Programm kann Punktmengen aus Dateien einlesen und abspeichern.\r\n"
			+ "Benutzen Sie bitte die üblichen Menübefehle Datei, dann Neu, Öffnen, Speichern "
			+ "und Speichern unter zum Resetten der Daten der Applikation sowie zum "
			+ "Öffnen und Speichern externer Daten. Die Daten werden beim Speichern im Textformat abgelegt, "
			+ "wobei in jeder Zeile ein Punkt mit durch Leerzeichen getrennten ganzzahligen "
			+ "X- und Y-Koordinaten steht. Zum Beispiel bedeutet\r\n" + "123 453\r\n" + "237 135\r\n" + "42 357\r\n"
			+ "die Punktmenge {(123, 453), (237, 135), (42, 357)}. Alle Zeilen, die sich nicht\r\n"
			+ "in diesem Format einlesen lassen, werden beim Öffnen von .txt Dateien ignoriert. \r\n"
			+ "Die grafische Eingabe erlaubt keine Punkte mit gleichen Koordinaten. Insbesondere ist das setzen von Punkten übereinander nicht erlaubt.\r\n"
			+ "Auch bei den zufällig erzeugten Punkten entstehen keine Doppelungen. Falls in einer Eingabe Datei Punkte doppelte Punkte vorkommen, "
			+ "so werden die Punkte nur einmal dargestellt. Der Nutzer wird hierüber informiert. "
			+ "Die anderen Punkte, soweit sie korrekt sind, werden akzeptiert.\r\n"
			+ " Wenn man neue Punkte hinzufügt, random oder durch Mausklick, werden diese in der sichtbaren Zeichenfläche eingefügt "
			+ "werden. Gemäß Anforderungen werden diese Punkte lediglich hinzugefügt. Dies impliziert jedoch nicht, dass "
			+ "die alten Punkte - ob generiert oder per Hand eingefügt - gel&ouml;scht werden.";
	/**
	 * Dieser String repr&auml;sentiert die Erkl&auml;rung der Zeichenfl&auml;che und wird dem User
	 * auf Knopfdruck angezeigt.
	 */
	private final String zeichenFlaecheString = "Sie können auf der zunächst leeren Zeichenflaeche mittels Mausklicks "
			+ "nach Belieben Punkte setzen, löschen und verschieben. Beim Löschen und Verschieben "
			+ "reichen Mausklicks in der Nähe "
			+ "eines Punktes aus. Das Programm berechnet für jede Konstellation sofort die konvexe Hülle und "
			+ "zeigt sie an.\r\n" + "Das gilt auch für das Verschieben: Waehrend mit der Maus "
			+ "ein Punkt verschoben wird, geht die Hülle immer mit.\r\n";
	/**
	 * Dieser String repr&auml;sentiert die Erkl&auml;rung der oberen Eingabeleiste und wird
	 * dem User auf Knopfdruck angezeigt.
	 */
	private final String obereEingabeLeisteString = "Das Programm kann zufällige Eingaben von 10, 50, 100, 500 und 1000 "
			+ "Eingaben Punkten erzeugen, die im Bereich der Zeichenfläche liegen, diese Punkte "
			+ "sollen zur aktuellen Menge hinzugefügt werden.\r\n"
			+ "Klicken Sie zur Erzeugung der zufälligen Eingabe auf die entsprechenden"
			+ "Buttons in der oberen Eingabeleiste. \r\n"
			+ " In der oberen EingabeLeiste gibt es eine Undo/Redo-Funktion: Alle vorhergehenden Einfügungen, "
			+ "Verschiebungen usw. können durch sukzessives Undo rückgängig und durch Redo "
			+ "entsprechend wieder gültig gemacht werden. Undo und Redo ändern aber "
			+ "nichts an abgespeicherten Dateien.\r\n " + "Eine Verschiebung ist eine Aktion mit der "
			+ "Maus, bei der ein Punkt angefasst und irgendwo wieder losgelassen wird.\r\n"
			+ "Undo/Redo betrifft also nicht die sehr vielen Zwischenstationen waehrend der "
			+ "Verschiebung, sondern nur die Ausgangs- und Endlage des Punktes.\r\n"
			+ "Das Erzeugen von mehreren Punkten wird in einem einzigen Undo komplett "
			+ "rückgängig gemacht und in einem Redo wieder hergestellt.";
	/**
	 * Dieser String repr&auml;sentiert die Erkl&auml;rung der unteren Eingabeleiste und wird
	 * dem User auf Knopfdruck angezeigt.
	 */
	private final String untereEingabeLeisteString = "Das Programm kann die rotierenden Messschieber animiert "
			+ "darstellen:"
			+ "Auf Knopfdruck (siehe untere Bedienleiste) laufen die beiden parallelen Tangenten einmal um "
			+ "das Polygon herum, und zu jeder Zwischenstelle wird das dazu passende Antipodenpaar und "
			+ "das zugehörige Viereck dargestellt.\r\n" + "Das Programm beherrscht ein Zoom In "
			+ "und Zoom Out (ebenso einstellbar in der unteren Bedienleiste). "
			+ "Dabei wird der betrachtete Ausschnitt der Daten vergrößert bzw. verkleinert. Ferner wird eine passende"
			+ "Translation angewandt, um die Daten zentriert darzustellen.\r\n"
			+ "So können auch Daten mit sehr großen Koordinaten auf dem Bildschirm sichtbar gemacht werden. "
			+ "Solange die Zoom-In-Funktion eingeschaltet ist, können keine Punkte per Mausklick "
			+ "eingefügt oder gelöscht werden.";
	/**
	 * Die Variable zeigt auf den Teil der Bedienungsanleitung, der gerade angezeigt
	 * werden soll.
	 */
	private JTextArea helpDisplay;
	/**
	 * In den JScrollPane wird das helpDisplay eingebetettet, um dieses scrollbar zu
	 * machen.
	 */
	private JScrollPane scroll;
	/**
	 * Wenn der User auf den menuBarButton dr&uuml;ckt, wird ihm oder ihr der Teil der
	 * Bedienungsanleitung angezeigt, der das Men&uuml; erkl&auml;rt.
	 */
	private JButton menuBarButton;
	/**
	 * Wenn der User auf den untereEingabeLeisteButton dr&uuml;ckt, wird ihm oder ihr der
	 * Teil der Bedienungsanleitung angezeigt, der die untere Eingabeleiste erkl&auml;rt.
	 */
	private JButton untereEingabeLeisteButton;
	/**
	 * Wenn der User auf den obereEingabeLeisteButton dr&uuml;ckt, wird ihm oder ihr der
	 * Teil der Bedienungsanleitung angezeigt, der die obere Eingabeleiste erkl&auml;rt.
	 */
	private JButton obereEingabeLeisteButton;
	/**
	 * Wenn der User auf den zeichenFlaecheButton dr&uuml;ckt, wird ihm oder ihr der Teil
	 * der Bedienungsanleitung angezeigt, der die Zeichenfl&auml;che erkl&auml;rt.
	 */
	private JButton zeichenFlaecheButton;
	/**
	 * In dieses Panel werden die Buttons f&uuml;r die obere Eingabeleiste und f&uuml;r das
	 * Men&uuml; eingebettet.
	 */
	private JPanel helpNavigationNorthPanel;
	/**
	 * In dieses Panel werden die Buttons für die untere Eingabeleiste und f&uuml;r die
	 * Zeichenfl&auml;che eingebettet.
	 */
	private JPanel helpNavigationSouthPanel;

	/**
	 * Erzeugt und initialisiert das Helppanel. Initial wird die Erkl&auml;rung der
	 * Men&uuml;leiste angezeigt.
	 */

	public HelpPanel() {

		setUpHelpDisplay();
		this.setLayout(new BorderLayout());

		helpNavigationNorthPanel = new JPanel();
		setUpHelpNavigationNorthPanel();

		helpNavigationSouthPanel = new JPanel();
		setUpHelpNavigationSouthPanel();

		add(helpNavigationNorthPanel, BorderLayout.NORTH);
		add(scroll, BorderLayout.CENTER);
		add(helpNavigationSouthPanel, BorderLayout.SOUTH);
	}

	/**
	 * Initialisiert das SouthPanel mit den Buttons mittels denen die
	 * Bedienungsanleitung f&uuml;r die untere Eingabeleiste und die Zeichenfl&auml;che
	 * angezeigt werden kann.
	 */
	private void setUpHelpNavigationSouthPanel() {
		untereEingabeLeisteButton = new JButton("Bedienungsanleitung der unteren Eingabeleiste");
		untereEingabeLeisteButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				helpDisplay.setText(untereEingabeLeisteString);
				helpDisplay.setCaretPosition(0);

			}

		});
		zeichenFlaecheButton = new JButton("Bedienungsanleitung der Zeichenfläche");
		zeichenFlaecheButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				helpDisplay.setText(zeichenFlaecheString);
				helpDisplay.setCaretPosition(0);

			}

		});
		helpNavigationSouthPanel.setLayout(new BorderLayout());
		helpNavigationSouthPanel.add(untereEingabeLeisteButton, BorderLayout.EAST);
		helpNavigationSouthPanel.add(zeichenFlaecheButton, BorderLayout.WEST);
	}

	/**
	 * Initialisiert das NorthPanel mit den Buttons mittels denen die
	 * Bedienungsanleitung für die obere Eingabeleiste und die Men&uuml;leiste angezeigt
	 * werden kann.
	 */
	private void setUpHelpNavigationNorthPanel() {
		menuBarButton = new JButton("Bedienungsanleitung der Menüleiste");
		menuBarButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				helpDisplay.setText(menuBarString);
				helpDisplay.setCaretPosition(0);
			}

		});

		obereEingabeLeisteButton = new JButton("Bedienungsanleitung der oberen Eingabeleiste");
		obereEingabeLeisteButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				helpDisplay.setText(obereEingabeLeisteString);
				helpDisplay.setCaretPosition(0);
			}

		});

		helpNavigationNorthPanel.add(menuBarButton, BorderLayout.EAST);
		helpNavigationNorthPanel.add(obereEingabeLeisteButton, BorderLayout.WEST);

	}

	/**
	 * Initialisiert das JTextArea, in dem der vom User gew&auml;hlte Teil der
	 * Bedienungsanleitung angezeigt wird. Das JTextArea wird zur besseren
	 * Darstellung in ein JScrollPane eingebettet.
	 */
	private void setUpHelpDisplay() {
		helpDisplay = new JTextArea(20, 20);
		helpDisplay.setText(menuBarString);
		helpDisplay.setBorder(BorderFactory.createCompoundBorder(helpDisplay.getBorder(),
				BorderFactory.createEmptyBorder(20, 20, 20, 20)));
		helpDisplay.setOpaque(false);

		helpDisplay.setCaretPosition(0);
		helpDisplay.setLineWrap(true);
		Font font = new Font("Serif", Font.BOLD, 18);
		helpDisplay.setFont(font);
		helpDisplay.setWrapStyleWord(true);
		helpDisplay.setEditable(false);
		scroll = new JScrollPane(helpDisplay);
		scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
	}

}
