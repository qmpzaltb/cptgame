package dungContent;

import java.awt.Color;

import dungEntity.AnimationType;
import dungEntity.EntitySkeleton;
import dungEntity.SkeletonLimb;

public class SkeletonCreature extends EntitySkeleton{
	public SkeletonCreature(){
		sklaSkeleton = new SkeletonLimb[]{
				new LimbLine(2f, +5.0, 0.0, +5.0, 0.0),//Right leg, Limb 0
				new LimbLine(2f, -5.0, 0.0, -5.0, 0.0),//Left leg, Limb 1
				new LimbOval(-10.0, -5.0, 20.0, 10.0),//Torso, Limb 2
				new LimbLine(2f, +10.0, 0.0, +15.0, 0.0),//Right reararm, Limb 3
				new LimbLine(2f, -10.0, 0.0, -15.0, 0.0),//Left reararm, Limb 4
				new LimbLine(2f, +15.0, 0.0, +15.0, -5.0),//Right forearm, Limb 5
				new LimbLine(2f, -15.0, 0.0, -15.0, -5.0),//Left forearm, Limb 6
				new LimbOval(-5.0, -5.0, 10.0, 10.0)//Head
		};

		sklaSkeleton[7].colLimbColor = Color.YELLOW;
	}
		
	@Override
	public void doAnimation(AnimationType animType, long timeSinceAnimStart) {
		// TODO Auto-generated method stub
		
	}

}
