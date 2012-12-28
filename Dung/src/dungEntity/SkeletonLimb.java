package dungEntity;

public class SkeletonLimb {

	public static int LIMB_TYPE_OVAL = 1;
	public static int LIMB_TYPE_LINE = 2;
		
	private int iLimbType;
	private double dRelativeLimbX1; //Relative to the center of the skeleton, if the skeleton was facing up.
	private double dRelativeLimbY1; //Relative to the center of the skeleton, if the skeleton was facing up.
	
	private double dRelativeLimbX2; //X1Y1 and X2Y2 to make lines and ovals and such.
	private double dRelativeLimbY2;
	
	private double dLimbSize; //Width of a line, size of a circle. That kind of size.
	
	public SkeletonLimb(int limbType, double x1, double y1, double x2, double y2, double size){
		iLimbType = limbType;
		dRelativeLimbX1 = x1;
		dRelativeLimbY1 = y1;
		dRelativeLimbX2 = x2;
		dRelativeLimbY2 = y2;
	}
	
}