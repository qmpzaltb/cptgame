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
			new LimbLine(2f, +15.0, 0.0, +15.0, 5.0),//Right forearm
			new LimbLine(2f, -15.0, 0.0, -15.0, 5.0),//Left forearm
			new LimbOval(-5.0, -5.0, 10.0, 10.0)//Head
		};
		
		sklaSkeleton[7].colLimbColor = Color.YELLOW;
	}
	
	
	
	
	public void doSpecificAnimation(AnimationType animType, int timeSinceAnimStart) {
		// TODO Auto-generated method stub
	}

	public void doMoveAnimation(long timeSinceAnimStart) {
		// TODO Auto-generated method stub
	}

	public void doIdleAnimation(long timeSinceAnimStart) {
		// TODO Auto-generated method stub
	}

	public void doDeathAnimation(long timeSinceAnimStart) {
		// TODO Auto-generated method stub
	}
	
	
	
}
