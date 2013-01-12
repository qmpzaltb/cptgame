package dungContent;

import java.awt.Graphics2D;
import java.awt.Polygon;
import java.awt.geom.AffineTransform;

import dungEntity.SkeletonLimb;

public class LimbPolygon extends SkeletonLimb {

	Polygon plyPolygon;
	double dRelativeLimbX1;
	double dRelativeLimbY1;
	double dHeading;
	double dScale;
	
	
	public LimbPolygon(int[] xPoints, int[] yPoints, double orientation, double scale){
		dHeading = orientation;
		plyPolygon = new Polygon(xPoints,yPoints,xPoints.length);
	}
	
	
	@Override
	public void drawLimb(Graphics2D g) {
		// TODO Auto-generated method stub
		AffineTransform atrPreRenderTransformations = g.getTransform();
		g.rotate(dHeading);
		g.setColor(colLimbColor);
		g.scale(dScale, dScale);
		g.fillPolygon(plyPolygon);
		g.setTransform(atrPreRenderTransformations);
	}

	@Override
	public double getAttachPointX() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double getAttachPointY() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void setDoubleX1(double setTo) {
		// TODO Auto-generated method stub
		dRelativeLimbX1 = setTo;
	}

	@Override
	public void setDoubleX2(double setTo) {
		// TODO Auto-generated method stub
		dHeading = setTo;
	}

	@Override
	public void setDoubleY1(double setTo) {
		// TODO Auto-generated method stub
		dRelativeLimbY1 = setTo;
	}

	@Override
	public void setDoubleY2(double setTo) {
		// TODO Auto-generated method stub
		dScale = setTo;
	}

}
