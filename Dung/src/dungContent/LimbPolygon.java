package dungContent;

import java.awt.Graphics2D;
import java.awt.Polygon;
import dungEntity.SkeletonLimb;

public class LimbPolygon extends SkeletonLimb {

	Polygon plyPolygon;
	double dHeading;
	
	public LimbPolygon(double orientation, int[] xPoints, int[] yPoints){
		dHeading = orientation;
		plyPolygon = new Polygon(xPoints,yPoints,xPoints.length);
	}
	
	
	@Override
	public void drawLimb(Graphics2D g) {
		// TODO Auto-generated method stub
		g.rotate(dHeading);
		g.setColor(colLimbColor);
		g.fillPolygon(plyPolygon);
		g.rotate(-dHeading);
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

	}

	@Override
	public void setDoubleX2(double setTo) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setDoubleY1(double setTo) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setDoubleY2(double setTo) {
		// TODO Auto-generated method stub

	}

}
