package dungContent;

import dungEntity.AnimationType;
import dungEntity.EntitySkeleton;
import dungEntity.SkeletonLimb;

public class SkeletonDirtyBubble extends EntitySkeleton {

	public SkeletonDirtyBubble(){
		sklaSkeleton = new SkeletonLimb[]{
			new LimbOval(-28 , -28 , 56 , 56), //Bubble base
			new LimbPolygon(0 , new int[]{   } ),
				
				
		};
		
		
		
	}
	
	@Override
	public void doAnimation(AnimationType animType, long animTime) {
		// TODO Auto-generated method stub

	}

}
