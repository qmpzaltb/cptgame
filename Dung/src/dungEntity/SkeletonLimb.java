package dungEntity;

import java.awt.Color;
import java.awt.Graphics2D;

abstract public class SkeletonLimb {
	
	public Color colLimbColor;
	
	abstract public void drawLimb(Graphics2D g);
	abstract public double getAttachPointX();
	abstract public double getAttachPointY();
	abstract public void setDoubleX1(double setTo);
	abstract public void setDoubleX2(double setTo);
	abstract public void setDoubleY1(double setTo);
	abstract public void setDoubleY2(double setTo);
}