package propra22.q5395950.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

import propra22.q5395950.controller.Controller;

/**
 * Ein Exemplar der Klasse MainMenuBar realisiert die Men&uuml;leiste, welches die
 * Sicht auf das HilfeMen&uuml; sowie Men&uuml;items zum Speichern, &Ouml;ffnen und Resetten
 * der Applikation (Men&uuml;item Neu) darstellt. Bei den Items werden Listener
 * angemeldet, die die von den Men&uuml;namen implizierten Funktionalit&auml;ten weiter
 * behandeln.Aktionen, die beim Speichern, &Ouml;ffnen und Resetten ausgef&uuml;hrt werden
 * sollen, werden an den Controller weitergeleitet.
 * 
 * @author LukasSanto Puglisi
 */
public class MainMenuBar extends JMenuBar {
	/**
	 * Das Hilfemen&uuml;, mit dem &uuml;ber das Men&uuml;item Hilfe auf die Bedienungsanleitung
	 * der Applikation zugegriffen werden kann.
	 */
	private JMenu helpMenu;
	/**
	 * Das Men&uuml;item Hilfe auf, mit dem auf die Bedienungsanleitung der Applikation
	 * zugegriffen werden kann.
	 */
	private JMenuItem hilfe;
	/**
	 * Das Men&uuml;item Neu, mit dem die Applikation geressetet werden kann. Infolge des
	 * Resettens wird sowohl die Historie der Datenpunktmengen, als auch die
	 * aktuelle Datenpunktmenge gel&ouml;scht.
	 */
	private JMenuItem neu;
	/**
	 * Das Men&uuml;item Neu, mit dem die Datenpunkte externer Dateien in das Datenmodell
	 * geladen werden k&ouml;nnen.
	 */
	private JMenuItem oeffnen;
	/**
	 * Das Men&uuml;item Speichern, mit dem die aktuelle Datenpunktmenge in eine externe
	 * Datei geladen werden kann. Wurde zuvor eine Datei ge&ouml;ffnet, wird die aktuelle
	 * Datenpunktmenge in diese Datei geladen. Wurde die Datenmenge zuvor
	 * gespeichert (mit Speichern Unter oder Speichern), wird die aktuelle
	 * Datenpunktmenge ebenfalls in diese Datei geladen. Ansonsten wird die
	 * Benutzerin aufgefordert, die Datei auszuw&auml;hlen, in die die Datenmenge
	 * gespeichert werden soll. Der Dateiauswahldialog startet immer im Verzeichnis
	 * ../Propra-SS22-Basis/data.
	 */
	private JMenuItem speichern;
	/**
	 * Das Men&uuml;item Speichern Unter, mit dem die aktuelle Datenpunktmenge in eine
	 * externe Datei geladen werden kann. Im Gegensatz zum Item Speichern wird die
	 * Benutzerin immer aufgefordert, die Datei auszuw&auml;hlen, in die die Datenmenge
	 * gespeichert werden soll. Der Dateiauswahldialog startet immer im Verzeichnis
	 * ../Propra-SS22-Basis/data.
	 */
	private JMenuItem speichernUnter;
	/**
	 * Ein Exemplar der Klasse JFileChooser realisiert die Navigation im Dateisystem
	 * beim Speichern und &Ouml;ffnen von Daten und externen Dateien.
	 */
	private JFileChooser fileChooser;
	/**
	 * Datei, auf die zuletzt bei einer Lade- oder Speicheroperation zugegriffen
	 * wurde.
	 */
	private File currentFile;

	/**
	 * Erzeugt ein Exemplar der Klasse MainMenuBar und initialisiert es 
	 * mit den JMenuitems. Das Exemplar der Klasse MainMenuBar erh&auml;lt eine Referenz auf
	 * den MainFrame, sodass Dialoge beim Anzeigen des Hilfepanels und des
	 * Dateiauswahldialogs, zentriert dargestellt werden. &uuml;ber ActionListener 
	 * an den JMenuitems wird der Controller angesprochen, der die Behandlung 
	 * der Speichern, Reset und &Ouml;ffnen Operationen behandelt. 
	 * 
	 * @param mainFrame  Das MainFrame der Applikation.
	 * @param controller Der Controller behandelt das Speichern und Resetten des
	 *                   Datenmodells sowie das &Ouml;ffnen externer Dateien.
	 */

	public MainMenuBar(MainFrame mainFrame, Controller controller) {

		JMenu fileMenu = new JMenu("Datei");

		helpMenu = new JMenu("Hilfe");
		hilfe = new JMenuItem("Hilfe anzeigen");
		neu = new JMenuItem("Neu");
		oeffnen = new JMenuItem("Öffnen");
		speichern = new JMenuItem("Speichern");
		speichernUnter = new JMenuItem("Speichern unter");
		fileChooser = new JFileChooser("../ProPra-SS22-Basis/data");

		hilfe.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				HelpPanel helpPanel = new HelpPanel();

				JOptionPane.showMessageDialog(mainFrame, helpPanel, "Bedienungsanleitung",
						JOptionPane.INFORMATION_MESSAGE);
			}

		});

		oeffnen.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (fileChooser.showOpenDialog(mainFrame) == JFileChooser.APPROVE_OPTION) {
					currentFile = fileChooser.getSelectedFile();
					controller.readFromFileIntoModel(currentFile);
				}
				;
			}
		});

		// resets the model and cleans the model History
		neu.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				currentFile = null;
				controller.resetModel();
			}
		});
		speichern.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (currentFile == null) {
					if (fileChooser.showSaveDialog(mainFrame) == JFileChooser.APPROVE_OPTION) {
						currentFile = fileChooser.getSelectedFile();
						controller.saveToFile(currentFile);
					}

				} else {
					controller.saveToFile(currentFile);
				}
			}
		});
		speichernUnter.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (fileChooser.showSaveDialog(mainFrame) == JFileChooser.APPROVE_OPTION) {
					currentFile = fileChooser.getSelectedFile();
					controller.saveToFile(currentFile);

				}
			}

		});

		fileMenu.add(neu);
		fileMenu.add(oeffnen);
		fileMenu.add(speichern);
		fileMenu.add(speichernUnter);
		helpMenu.add(hilfe);
		this.add(fileMenu);
		this.add(helpMenu);
	}
/**
 * Der MainFrame kann die JMenuItems ausschalten. Dies ist w&auml;hrend der Animation erforderlich, um 
 * Konsistenz zwischen Sicht und Datenmodell zu bewahren. 
 */
	public void switchOff() {
		neu.setEnabled(false);
		oeffnen.setEnabled(false);
		speichernUnter.setEnabled(false);
		speichern.setEnabled(false);
		hilfe.setEnabled(false);
	}
	/**
	 * Schaltet die JMenuItems nach der Animation wieder ein.
	 */
	public void switchOn() {
		neu.setEnabled(true);
		oeffnen.setEnabled(true);
		speichernUnter.setEnabled(true);
		speichern.setEnabled(true);
		hilfe.setEnabled(true);
	}
}
