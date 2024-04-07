package propra22.q5395950.controller;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.regex.Pattern;

import propra22.q5395950.generalUtility.DataModelI;
import propra22.q5395950.generalUtility.DataPoint;

/**
 * Diese Klasse enth&auml;lt statische Methoden, um Daten aus Dateien einzulesen,
 * beziehungsweise in diese zu schreiben.
 * 
 * @author LukasSanto Puglisi
 */
public class FileAccess {

	/**
	 * Mittels dieser Methode k&ouml;nnen die Datenpunkte aus dem Modell in eine
	 * Datei geschrieben werden.
	 * 
	 * @param file  Datei in die die Punkte geschrieben werden sollen.
	 * @param model Das Modell, das die zu speichernden Punkte enth&auml;lt.
	 * @throws IOException Wird beim Auftreten eines I/O Errors geworfen.
	 */

	public static void saveToFile(File file, DataModelI model) throws IOException {
		ArrayList<DataPoint> list = model.getAllPoints();
		FileWriter fw = new FileWriter(file);
		BufferedWriter bw = new BufferedWriter(fw);
		for (DataPoint p : list) {
			int x = (int) p.getX();
			int y = (int) p.getY();
			String s = " ";
			bw.write(Integer.toString(x) + s + Integer.toString(y));
			bw.newLine();

		}
		bw.flush();
		bw.close();
	}

	/**
	 * Mittels dieser statischen Methode k&ouml;nnen Datenpunkte aus einer Datei in
	 * das Modell eingelesen werden. 
	 * 
	 * @param file Datei aus der die Datenpunkte in das Modell gelesen werden sollen. 
	 * @param model Modell, in die die Datenpunkte aus der Datei gelesen werden sollen. 
	 * @return Die Methode gibt true zur&uuml;ck, falls beim
	 * Lesen der Datei festgestellt wurde, dass diese Duplikate, Kommentare oder
	 * nicht dem Eingabeformat entsprechende Zeilen enth&auml;lt. 
	 * @throws IOException IOException Wird beim Auftreten eines I/O Errors geworfen.
	 */
	public static boolean readFromFileIntoModel(File file, DataModelI model) throws IOException {
		FileInputStream fis = new FileInputStream(file);
		InputStreamReader isr = new InputStreamReader(fis);
		BufferedReader br = new BufferedReader(isr);
		String line;
		/*
		 * pattern checks for one or more digit
		 */
		Pattern p = Pattern.compile("(-|\\+)?\\d+");
		boolean inputInCorrectFormat = true;
		while ((line = br.readLine()) != null) {
			String trimmedLine = line.trim();
			String[] tuple = trimmedLine.split(" ");
			if (tuple.length < 2) {
				inputInCorrectFormat = false;
				continue;
			}
			if (!p.matcher(tuple[0]).matches()) {
				inputInCorrectFormat = false;
				continue;
			}
			/*
			 * we ignore only white space after the first integer
			 */
			int i = 1;
			while (i < tuple.length && tuple[i] == "") {
				i++;
			}
			if (!p.matcher(tuple[i]).matches()) {
				inputInCorrectFormat = false;
				continue;
			}
			int x = Integer.parseInt(tuple[0]);
			int y = Integer.parseInt(tuple[i]);
			model.addPoint(new DataPoint(x, y));
		}

		br.close();
		if (model.deleteDuplicates()) {
			inputInCorrectFormat = false;
		}
		return inputInCorrectFormat;

	}
}
