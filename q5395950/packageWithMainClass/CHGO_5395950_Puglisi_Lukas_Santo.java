package propra22.q5395950.packageWithMainClass;

import javax.swing.UIManager;
import propra22.q5395950.controller.Controller;

import propra22.tester.Tester;
import propra22.interfaces.IHullCalculator;

/**
 * Mit dieser Klasse wird die Applikation gestartet. Beim normalen Porgrammstart 
 * erscheint das Hauptfenster. &Uuml;ber das Program Argument t kann die Applikation auch ohne 
 * Benutzerorberfl&auml;che gestartet werden, w&auml;hrend das Exemplar der Klasse Tester 
 * die externen Tests testet. 
 * @author LukasSanto Puglisi
 *
 * @param message
 * @return returnValue
 * @throws forExceptions
 *
 */
public class CHGO_5395950_Puglisi_Lukas_Santo {

	public static void main(String[] args)  {
			if (args.length > 0 && "-t".equals(args[0])) {
				IHullCalculator calculator = new HullCalculator();
				Tester tester = new Tester(args, calculator);
				System.out.println(tester.test());
				System.exit(0);
			} else {
				UIManager.put("swing.boldMetal", Boolean.TRUE);
				Controller controller = new Controller();
			}
	}

}
