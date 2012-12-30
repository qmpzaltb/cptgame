package dungContent;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;

import dungEntity.SkeletonLimb;

public class LimbLine extends SkeletonLimb{

	public float fLimbWidth;
	public double dRelativeLimbX1;
	public double dRelativeLimbX2;
	public double dRelativeLimbY1;
	public double dRelativeLimbY2;
	
	public LimbLine(float limbWidth, double x1, double y1, double x2, double y2){
		fLimbWidth = limbWidth;
		dRelativeLimbX1 = x1;
		dRelativeLimbX2 = x2;
		dRelativeLimbY1 = y1;
		dRelativeLimbY2 = y2;
		colLimbColor = Color.BLUE;
	}
	
	
	public void drawLimb(Graphics2D g) {
		System.out.println("I am a sexy line kind of limb with " + dRelativeLimbX1 + " " + dRelativeLimbX2 + " " + dRelativeLimbY1 + " " + dRelativeLimbY2);
		g.setColor(colLimbColor);
		//g.setStroke(new BasicStroke(fLimbWidth));
		g.drawLine((int)dRelativeLimbX1, (int)dRelativeLimbY1, (int)dRelativeLimbX2, (int)dRelativeLimbY2);
	}

	public double getAttachPointX() {
		// TODO Auto-generated method stub
		return dRelativeLimbX2;
	}

	public double getAttachPointY() {
		// TODO Auto-generated method stub
		return dRelativeLimbY2;
	}


	public void setDoubleX1(double setTo) {
		dRelativeLimbX1 = setTo;
	}


	public void setDoubleX2(double setTo) {
		dRelativeLimbX2 = setTo;
	}


	public void setDoubleY1(double setTo) {
		dRelativeLimbY1 = setTo;
	}


	public void setDoubleY2(double setTo) {
		dRelativeLimbY2 = setTo;
	}

}