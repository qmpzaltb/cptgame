package dungContent;

import dungEntity.AnimationType;
import dungEntity.EntitySkeleton;
import dungEntity.SkeletonLimb;

public class SkeletonBroom extends EntitySkeleton{

	/**
	 * SkeletonHumanoid:
	 * A class that defines a human-like entity (a bipedal, upright-standing animal)
	 * Entities that may use this skeleton include player characters, cleaning-supplies-merchants, dirt zombies, etc.
	 */
	public SkeletonBroom(){

		//Initializes the "limbs" of the skeleton in their default position.
		sklaSkeleton = new SkeletonLimb[]{
				new LimbLine(1.5f, 0.0, 15.0, 0.0, -25.0),//leg, Limb 0
				new LimbPolygon(new int []{0,2,4,5,-5,-4,-2},new int []{0-25,-1-25,-5-25,-8-25,-8-25,-5-25,-1-25}, 0, 1) //Everything else, Limb 1
		};

	}




	public void doAnimation(AnimationType animType, long animTime) {
		switch (animType){
		case USE:{

			break;
		}	
		default:
			break;
		}
	}
}