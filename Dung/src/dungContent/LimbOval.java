package dungContent;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;

import dungEntity.SkeletonLimb;

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
		colLimbColor = Color.ORANGE;
	}
	
	
	public void drawLimb(Graphics2D g) {
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


	@Override
	public void setDoubleX1(double setTo) {
		dRelativeLimbX1 = setTo;
	}


	@Override
	public void setDoubleX2(double setTo) {
		dLimbXSize = setTo;
	}


	@Override
	public void setDoubleY1(double setTo) {
		dRelativeLimbY1 = setTo;
	}


	@Override
	public void setDoubleY2(double setTo) {
		dLimbYSize = setTo;
	}

}