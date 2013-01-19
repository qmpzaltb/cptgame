package dungContent;

import java.awt.BasicStroke;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.awt.geom.AffineTransform;

import dungEntity.SkeletonLimb;

/**
 * LimbPolygon:
 * A class that defines a polygonal limb.
 * The polygon is defined by two arrays of equal length (one array representing x-parts of coordinates, another representing y-parts)
 * Limbs that may require LimbPolygon include items such as water sprayers, crystals, and hair detail.
 * 
 * Its setDouble methods correspond to:
 * X1: the X value of the origin of the limb
 * Y1: the Y value of the origin of the limb
 * X2: the heading of the limb (in radians)
 * Y2: the scale of the limb (relative to an unscaled version of scale 1)
 */
public class LimbPolygon extends SkeletonLimb {

	Polygon plyPolygon;
	double dRelativeLimbX1;
	double dRelativeLimbY1;
	double dHeading;
	double dScale;
	
	
	public LimbPolygon(int[] xPoints, int[] yPoints, double orientation, double scale){
		dHeading = orientation;
		dScale = scale;
		plyPolygon = new Polygon(xPoints,yPoints,xPoints.length);
	}
	
	
	public void drawLimb(Graphics2D g) {
		//Draws the limb according to the object's information
		AffineTransform atrPreRenderTransformations = g.getTransform();
		g.rotate(dHeading);
		g.setColor(colLimbColor);
		g.scale(dScale, dScale);
		g.setStroke(new BasicStroke(1));
		g.fillPolygon(plyPolygon);
		g.setTransform(atrPreRenderTransformations);
	}

	public double getAttachPointX() {
		// TODO Auto-generated method stub
		return 0;
	}

	public double getAttachPointY() {
		return 0;
	}

	public void setDoubleX1(double setTo) {
		dRelativeLimbX1 = setTo;
	}

	public void setDoubleX2(double setTo) {
		dHeading = setTo;
	}

	public void setDoubleY1(double setTo) {
		dRelativeLimbY1 = setTo;
	}

	public void setDoubleY2(double setTo) {
		dScale = setTo;
	}

	public double getAttachHeading() {
		return dHeading;
	}

}
