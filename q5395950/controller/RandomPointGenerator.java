package propra22.q5395950.controller;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.ThreadLocalRandom;

import javax.swing.JButton;

import propra22.q5395950.generalUtility.DataModelI;
import propra22.q5395950.generalUtility.DataPoint;
import propra22.q5395950.generalUtility.UserInterface;

/**
 * Ein Exemplar dieser Klasse wird bei den f&uuml;r die Generierung von
 * Zufallspunkten zust&auml;ndigen JButtons in der GUI angemeldet. Ein Exemplar
 * dieser Klasse generiert die von den JButtons dem User angezeigten Anzahl von
 * Zufallspunkten, f&uuml;gt diese dem Modell hinzu und fordert die GUI auf sich
 * neu zu zeichnen, um eine konsistente Sicht sicherzustellen. Die Koordinaten
 * der generierten Punkte richten sich nach der aktuellen Gr&ouml;&szlige der
 * Zeichenfl&auml;che.
 * 
 * @author LukasSanto Puglisi
 */
public class RandomPointGenerator implements ActionListener {
	/**
	 * Das Modell, dem die zuf&auml;llig generierten Punkte geschickt werden.
	 */
	DataModelI model;
	/**
	 * Das UserInterface, das nach der Ver&auml;nderung des Datenmodells
	 * aufgefordert wird, eine konsistente Sicht auf die Daten herzustellen.
	 */
	UserInterface userInterface;

	/**
	 * Ein Exemplar dieser Klasse wird von der GUI erzeugt und bei den JButtons als
	 * ActionListener zur Erzeugung von Zufallspunkten angemeldet.
	 * 
	 * @param model         Das Datenmodell der Applikation.
	 * @param userInterface Die GUI der Applikation.
	 */

	public RandomPointGenerator(DataModelI model, UserInterface userInterface) {
		this.model = model;
		this.userInterface = userInterface;
	}

	/**
	 * Wird an der GUI ein JButton zur Generierung von Zufallspunkten gedr&uuml;ckt,
	 * wird ermittelt wieviele Punkte zuf&auml;llig erzeugt werden sollen. Das
	 * Erzeugen der Punkte wird an die Methode addRandomPointsToModel delegiert.
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		JButton button = (JButton) e.getSource();
		String buttonText = button.getText();
		Dimension dimension = button.getSize();
		int buttonHeight = (int) dimension.getHeight();
		switch (buttonText) {
		case "Füge 10 zufällige Punkte hinzu":
			addRandomPointsToModel(10, buttonHeight);
			break;
		case "Füge 50 zufällige Punkte hinzu":
			addRandomPointsToModel(50, buttonHeight);
			break;
		case "Füge 100 zufällige Punkte hinzu":
			addRandomPointsToModel(100, buttonHeight);
			break;
		case "Füge 500 zufällige Punkte hinzu":
			addRandomPointsToModel(500, buttonHeight);
			break;
		case "Füge 1000 zufällige Punkte hinzu":
			addRandomPointsToModel(1000, buttonHeight);
			break;

		}
	}

	/**
	 * Es werden i Zufallspnkte erzeugt, die dem Datenmodell hinzugef&uuml;gt werden
	 * und danach in der View angezeigt werden. Eventuell generierte Duplikate
	 * werden gel&ouml;scht. Die Modellhistorie wird mit dem neuen Datenmodell des
	 * Modells aktualisiert.
	 * 
	 * @param i            die Anzahl der zu erzeugenden Zufallspunkte
	 * @param buttonHeight Stellt sicher, dass die Zufallspunkte unter den
	 *                     Zufalls_Buttons des GUIs liegen.
	 */

	private void addRandomPointsToModel(int i, int buttonHeight) {
		Dimension dim = userInterface.getIODeviceSize();
		for (int j = 0; j < i; j++) {
			int randomX = ThreadLocalRandom.current().nextInt(5, dim.width - dim.width / 10);
			int randomY = ThreadLocalRandom.current().nextInt(buttonHeight + 20, dim.height - dim.height / 10);
			DataPoint randomPoint = new DataPoint(randomX, randomY);
			model.addPoint(randomPoint);
		}

		model.deleteDuplicates();
		model.safe();
		userInterface.refresh();
	}

}
