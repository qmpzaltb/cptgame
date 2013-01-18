package dungContent;

import java.awt.BasicStroke;
import java.awt.Graphics2D;

import dungEntity.SkeletonLimb;

/**
 * LimbOval:
 * A class that defines an ovaloid/circular type of limb.
 * e.g., torsos, heads, eyes.
 * This limb is drawn by drawing an oval in a bounding box.
 * The bounding box is defined by a top-left-most coordinate, and the width and the height of the bounding box.
 * 
 * Its setDouble methods correspond to:
 * X1: the X value of the top-left-most coordinate of the bounding box
 * Y1: the Y value of the top-left-most coordinate of the bounding box
 * X2: the width of the bounding box
 * Y2: the height of the bounding box
 */
public class LimbOval extends SkeletonLimb{

	public double dRelativeLimbX1;
	public double dRelativeLimbY1;
	public double dLimbXSize;
	public double dLimbYSize;
	
	public LimbOval(double x1, double y1, double xSize, double ySize){
		dRelativeLimbX1 = x1;
		dRelativeLimbY1 = y1;
		dLimbXSize = xSize;
		dLimbYSize = ySize;
	}
	
	
	public void drawLimb(Graphics2D g) {
		//Draws the limb according to the object's information
		g.setColor(colLimbColor);
		g.setStroke(new BasicStroke(1));
		g.fillOval((int)dRelativeLimbX1, (int)dRelativeLimbY1, (int)dLimbXSize, (int)dLimbYSize);
	}

	public double getAttachPointX() {
		// TODO Auto-generated method stub
		return dRelativeLimbX1;
	}

	public double getAttachPointY() {
		// TODO Auto-generated method stub
		return dRelativeLimbY1;
	}


	public void setDoubleX1(double setTo) {
		dRelativeLimbX1 = setTo;
	}


	public void setDoubleX2(double setTo) {
		dLimbXSize = setTo;
	}


	public void setDoubleY1(double setTo) {
		dRelativeLimbY1 = setTo;
	}


	public void setDoubleY2(double setTo) {
		dLimbYSize = setTo;
	}

	public double getAttachHeading() {
		return 0;
	}

}