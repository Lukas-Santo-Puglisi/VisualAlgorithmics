package propra22.q5395950.testForAnimation;

import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;

/**
 * In der Main Methode wird festgelegt ob die Rotation eines Punktes
 * (TestPanelSingleTangent) oder die Rotation einer Tangente getestet werden
 * soll (auskommentierter Teil). Desweiteren wird das Frame, dem das TestPanel
 * hinzugef&uuml;gt wird, definiert, sodass es f&uuml;r Debugging Zwecke angezeigt werden
 * kann.
 * 
 * @author LukasSanto Puglisi
 */
public class MainAnimationTest {
	public static void main(String[] args) {
		JFrame testFrame = new JFrame();
//		Entweder Testung Tangentenrotation
		TestPanelSingleTangent testPanel = new TestPanelSingleTangent();
//		oder Testung der Rotation eines einzelnen Punktes. 
//		TestPanelPoints  testPanel = new TestPanelPoints();

		defineFrame(testFrame, testPanel);

	}

	private static void defineFrame(JFrame testFrame, TestPanelSingleTangent testPanel) {
		testFrame.add(testPanel);
		testFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		testFrame.setSize(dim);
		testFrame.setLocationRelativeTo(null);
		testFrame.setVisible(true);
	}
}
