package dungEntity;

import java.awt.Color;
import java.awt.Graphics2D;

/**
 * SkeletonLimb:
 * An abstract class that defines a "limb".
 * A limb is a part of the entity's visible body that is drawn onto the screen.
 * The transformation/movement of the limbs is done by the "setDouble" methods.
 * They most often correspond to coordinates, yet sometimes correspond to object width, object rotation, and object scale.
 * To understand what the "setDouble" methods truly modify, view the method in the "Limb" classes (they are non-abstract extensions of SkeletonLimb)
 */
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