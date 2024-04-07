package propra22.q5395950.gui;

import java.io.File;

import javax.swing.filechooser.*;

/**
 * Mit einem Exemplar dieser Klasse filtert ein Exemplar der Klasse JFileChooser
 * alle Dateien aus der Sicht auf das Dateisystem heraus die nicht vom Typ txt
 * sind. Der TextFileFilter wird dem JFileChooser der MainMenuBar in der
 * Men&uuml;leiste der GUI hinzugef&uuml;gt.
 * 
 * @author LukasSanto Puglisi
 */
public class TextFileFilter extends FileFilter {
	/**
	 * Mittels dieser Methode bestimmt ein Exemplar der Klasse TextFileFilter, 
	 * ob er die Datei f herausilfert oder nicht.
	 * @param f Die Datei, die gefiltert wird. 
	 */
	@Override
	public boolean accept(File f) {
		if (f.isDirectory()) {
			return true;
		}
		String name = f.getName();
		int pointIndex = name.lastIndexOf(".");
		String fileExtension;
		if ((pointIndex == -1) || pointIndex == name.length() - 1) {
			fileExtension = "";
		} else {
			fileExtension = name.substring(pointIndex + 1, name.length());
		}

		if (fileExtension.equals("txt")) {
			return true;
		} else {
			return false;
		}

	}
	/**
	 * Beschreibt welche Dateien akzeptiert beziehungsweise gefiltert werden. 
	 */	
	@Override
	public String getDescription() {
		return "Dateien im txt Format (*.txt";
	}

}
