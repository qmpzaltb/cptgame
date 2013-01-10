package dungContent;

import java.awt.Color;

import dungEntity.AnimationType;
import dungEntity.EntitySkeleton;
import dungEntity.SkeletonLimb;

public class SkeletonDirtyBubble extends EntitySkeleton {
//The simplest entity known to man.
	
	
	public SkeletonDirtyBubble(){
		sklaSkeleton = new SkeletonLimb[]{
			new LimbOval(-28 , -28 , 56 , 56), //Bubble base
		};
		

		
	}
	
	@Override
	public void doAnimation(AnimationType animType, long animTime) {
		// TODO Auto-generated method stub

	}

}
