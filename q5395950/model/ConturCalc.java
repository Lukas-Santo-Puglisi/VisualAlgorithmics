package propra22.q5395950.model;
import java.util.*;

import propra22.q5395950.generalUtility.DataPoint;


public class ConturCalc {
    private ArrayList<DataPoint> pointList;  


    //variables for left sweep
    private DataPoint MinYSoFarPL;
    private DataPoint MaxYSoFarPL;
    
    
    //variables for right sweep
    private DataPoint MinYSoFarPR;
    private DataPoint MaxYSoFarPR;
    
    ArrayList<DataPoint> contour = new ArrayList<DataPoint>();
    
    public ArrayList<DataPoint> getContour() {
        return contour;
    }

    //ConvexHullCalc will prune these contours further, hence I grant packege-wide access
    ArrayList<DataPoint> leftUpperContour = new ArrayList<DataPoint>();
    ArrayList<DataPoint> leftBottomContour = new ArrayList<DataPoint>();
    ArrayList<DataPoint> rightUpperContour = new ArrayList<DataPoint>();
    ArrayList<DataPoint> rightBottomContour = new ArrayList<DataPoint>();
    
    public ConturCalc(ArrayList<DataPoint> pointList){
        this.pointList = pointList;
        if (pointList.size() == 0) return;
        leftSweep();
        rightSweep();
        concatenateToContour();
    }
    
    public void leftSweep(){
        //sortList in ascending order
        ArrayList<DataPoint> sortedList = pointList;
        Collections.sort(sortedList, new AscendingPointComparator());
        Iterator<DataPoint> itr = sortedList.iterator();
       
        //at start min and max points are the same and equal leftmost point
        MinYSoFarPL = itr.next();
        MaxYSoFarPL = MinYSoFarPL;
        //lists containing points where y component is monotonously ascending/descending and contains leftmost point
        leftUpperContour.add(MinYSoFarPL);
        leftBottomContour.add(MaxYSoFarPL);
        
        
        while (itr.hasNext() ) {
            //first point -> no action required
            DataPoint p = itr.next();
            if (p.getY() >= MaxYSoFarPL.getY()){
                leftUpperContour.add(p);
                MaxYSoFarPL = p;
            }
            
            if (p.getY() <= MinYSoFarPL.getY()) {
                leftBottomContour.add(p);
                MinYSoFarPL = p;
            }
           
        }
        
    }
    
    public void rightSweep(){
        //sortList in ascending order
        ArrayList<DataPoint> sortedList = pointList;
        Collections.sort(sortedList, new DescendingPointComparator());
        Iterator<DataPoint> itr = sortedList.iterator();
       
        //at start min and max points are the same and equal leftmost point
        MinYSoFarPR = itr.next();
        MaxYSoFarPR = MinYSoFarPR;

        //lists containing points where y component is monotonously ascending/descending and contains rightmost point
        rightUpperContour.add(MaxYSoFarPR);
        rightBottomContour.add(MinYSoFarPR);
        
        while (itr.hasNext() ) {
            //first point -> no action required
            DataPoint p = itr.next();
            //In contrast to left sweep the right contour ends when the next points y coordinate
            //is not strictly higher than the current y-max to prevent an overlap between contours
            if (p.getY() > MaxYSoFarPR.getY()){
                rightUpperContour.add(p);
                MaxYSoFarPR = p;
            }
            
            if (p.getY() < MinYSoFarPR.getY()) {
                rightBottomContour.add(p);
                MinYSoFarPR = p;
            }
        }
       
    }
    
    private void concatenateToContour() {
    	Collections.reverse(rightBottomContour);
    	rightUpperContour.remove(0);
        rightBottomContour.remove(0);
        Collections.reverse(leftUpperContour);
        leftUpperContour.remove(0);
        Collections.addAll(contour, leftBottomContour.toArray(new DataPoint[0]));
        Collections.addAll(contour, rightBottomContour.toArray(new DataPoint[0]));
        Collections.addAll(contour, rightUpperContour.toArray(new DataPoint[0]));
        Collections.addAll(contour, leftUpperContour.toArray(new DataPoint[0]));
       
    }
    
    
}
    
    
    
    
    
    
    
    
    
    


