package dungContent;

import java.awt.Color;

import dungEntity.AnimationType;
import dungEntity.EntitySkeleton;
import dungEntity.SkeletonLimb;

public class SkeletonHumanoid extends EntitySkeleton{

	
	
	public SkeletonHumanoid(){
		sklaSkeleton = new SkeletonLimb[]{
			new LimbLine(2f, +5.0, 0.0, +5.0, 0.0),//Right leg
			new LimbLine(2f, -5.0, 0.0, -5.0, 0.0),//Left leg
			new LimbOval(-10.0, -5.0, 20.0, 10.0),//Torso
			new LimbLine(2f, +10.0, 0.0, +15.0, 0.0),//Right reararm
			new LimbLine(2f, -10.0, 0.0, -15.0, 0.0),//Left reararm
			new LimbLine(2f, +15.0, 0.0, +15.0, -5.0),//Right forearm
			new LimbLine(2f, -15.0, 0.0, -15.0, -5.0),//Left forearm
			new LimbOval(-5.0, -5.0, 10.0, 10.0)//Head
		};
		
		sklaSkeleton[7].colLimbColor = Color.YELLOW;
	}
	
	
	
	
	public void doSpecificAnimation(AnimationType animType, int timeSinceAnimStart) {
		// TODO Auto-generated method stub
	}

	public void doMoveAnimation(long timeSinceAnimStart) {
		// TODO Auto-generated method stub
		int iTimeInAnimCycle = (int)(timeSinceAnimStart % 60); //60 because 60. Arbitrary period of the animation.
		sklaSkeleton[0].setDoubleY2(10 * Math.sin(0.104719755119 * iTimeInAnimCycle)); //0.104719755119 = 2PI / 60. Advanced functions, yeah!
		sklaSkeleton[1].setDoubleY2(10 * Math.sin(-0.104719755119 * iTimeInAnimCycle));
	}

	public void doIdleAnimation(long timeSinceAnimStart) {
		sklaSkeleton[0].setDoubleX1(+5.0);
		sklaSkeleton[0].setDoubleY1(0.0);
		sklaSkeleton[0].setDoubleX2(+5.0);
		sklaSkeleton[0].setDoubleY2(0.0);
		sklaSkeleton[1].setDoubleX1(-5.0);
		sklaSkeleton[1].setDoubleY1(0.0);
		sklaSkeleton[1].setDoubleX2(-5.0);
		sklaSkeleton[1].setDoubleY2(0.0);
		
		//And then arms stuff if applicable.
	}

	public void doDeathAnimation(long timeSinceAnimStart) {
		// TODO Auto-generated method stub
	}
	
	
	
}
