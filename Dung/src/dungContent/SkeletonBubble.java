package dungContent;

import dungEntity.AnimationType;
import dungEntity.EntitySkeleton;
import dungEntity.SkeletonLimb;

/**
 * SkeletonBubble:
 * A class that defines the skeleton for a bubble-like entity.
 * This can be used by a varying type of bubbles, such as dirty bubbles, dirt-splosive bubbles, etc...
 * It can also be potentially used for particle effects.
 */
public class SkeletonBubble extends EntitySkeleton {
	//The simplest entity known to man.


	public SkeletonBubble(){
		sklaSkeleton = new SkeletonLimb[]{
				new LimbOval(-35 , -35 , 70 , 70), //Bubble base
		};



	}

	@Override
	public void doAnimation(AnimationType animType, long animTime) {
		switch (animType){
		case TROLL:{
			int iAnimTime = 45 - (int)(animTime);
			sklaSkeleton[0].setDoubleX1(-10 * Math.sin((2 * Math.PI / 90) * iAnimTime) - 35);
			sklaSkeleton[0].setDoubleY1(-10 * Math.sin((2 * Math.PI / 90) * iAnimTime) - 35);
			sklaSkeleton[0].setDoubleX2(20 * Math.sin((2 * Math.PI / 90) * iAnimTime) + 70);
			sklaSkeleton[0].setDoubleY2(20 * Math.sin((2 * Math.PI / 90) * iAnimTime) + 70);
			break;
		}
		case IDLE:{
			break;  
		}
		default:{

			break;
		}
		}

	}

}
