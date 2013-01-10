package dungContent;

import java.awt.Color;

import dungEntity.AnimationType;
import dungEntity.EntitySkeleton;
import dungEntity.SkeletonLimb;

public class SkeletonDirtyBubble extends EntitySkeleton {

	public SkeletonDirtyBubble(){
		sklaSkeleton = new SkeletonLimb[]{
			new LimbOval(-28 , -28 , 56 , 56), //Bubble base
			new LimbPolygon(0 , new int[]{ -10, -7, -8 } , new int[]{ 4 , 2 , 6 } ),
				
				
		};
		
		sklaSkeleton[0].colLimbColor = new Color(159 , 114 , 57 , 200);
		sklaSkeleton[1].colLimbColor = Color.CYAN;
		
	}
	
	@Override
	public void doAnimation(AnimationType animType, long animTime) {
		// TODO Auto-generated method stub

	}

}
