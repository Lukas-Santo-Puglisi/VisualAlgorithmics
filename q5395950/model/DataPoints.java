package propra22.q5395950.model;

import java.util.*;
import propra22.q5395950.generalUtility.DataPoint;
import propra22.q5395950.generalUtility.GeometryCalc;

public class DataPoints {
	ArrayList<DataPoint> list = new ArrayList<DataPoint>();

	void add(DataPoint p) {
		list.add(p);
	}

	void setAllPoints(ArrayList<DataPoint> inputList) {
		list.clear();
		Iterator<DataPoint> itr = inputList.iterator();
		while (itr.hasNext()) {
			list.add(itr.next());
		}
	}

	ArrayList<DataPoint> getAllPoints() {
		return list;
	}
	/**
	 * 
	 * @param x
	 * @param y
	 * @return
	 */
	boolean isNear(long x, long y) {
		boolean exist = false;
		Iterator<DataPoint> itr = list.iterator();
		while (itr.hasNext()) {
			DataPoint p1 = itr.next();
			double distance = GeometryCalc.quadraticDistanceCoordinates(p1.getX(), p1.getY(), x, y);
			if (distance < 10) {
				exist = true;
			}
		}
		return exist;
	}

	DataPoint getNearPoint(long x, long y) {
		DataPoint closestPoint = null;
		int currentMinDistance = Integer.MAX_VALUE;
		Iterator<DataPoint> itr = list.iterator();
		while (itr.hasNext()) {
			DataPoint nextPoint = itr.next();
			long distance = GeometryCalc.quadraticDistanceCoordinates(nextPoint.getX(), nextPoint.getY(), x, y);
			if (distance < currentMinDistance) {
				closestPoint = nextPoint;
				currentMinDistance = (int) distance;
			}
		}
		return closestPoint;
	}

	void deletePointAt(long x, long y) {
		Iterator<DataPoint> itr = list.iterator();
		while (itr.hasNext()) {
			DataPoint p1 = itr.next();
			if (p1.getX() == x & p1.getY() == y) {
				itr.remove();
			}
		}
	}

	void setGivenPoint(DataPoint p, long x, long y) {
		Iterator<DataPoint> itr = list.iterator();
		while (itr.hasNext()) {
			DataPoint p1 = itr.next();
			if (p1 == p) {
				p1.setX(x);
				p1.setY(y);
			}
		}
	}

	int size() {
		return list.size();
	}

	public void setRandomPoints(ArrayList<DataPoint> randomPoints) {
		Iterator<DataPoint> itr = list.iterator();
		while (itr.hasNext()) {
			list.add(itr.next());
		}

	}

	public boolean deleteDuplicates() {
		ArrayList<DataPoint> newList = new ArrayList<DataPoint>();

		// Traverse through the first list
		for (DataPoint element : list) {

			// If this element is not present in newList
			// then add it
			if (!newList.contains(element)) {

				newList.add(element);
			}
		}
		boolean containedDuplicates = list.size() > newList.size();
		list = newList;
		return containedDuplicates;
	}
}
