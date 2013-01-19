
package dungContent;

import dungEntity.AnimationType;
import dungEntity.EntitySkeleton;
import dungEntity.SkeletonLimb;

public class SkeletonDuster extends EntitySkeleton {

	
	public SkeletonDuster(){
		sklaSkeleton = new SkeletonLimb[] {
				new LimbLine(2.0f, 0.0,0.0,  0.0,-15.0), //Limb 0, handle&stock
				new LimbLine(1f, 0.0,-9.0,  3.0,-7.0), //Limb 1, String, right bottom
				new LimbLine(1f, 0.0,-11.0,  3.0,-9.0), //Limb 2, String, right bottom-middle
				new LimbLine(1f, 0.0,-13.0,  3.0,-11.0), //Limb 3, String, right top-middle
				new LimbLine(1f, 0.0,-15.0,  3.0,-13.0), //Limb 4, String, right top
				new LimbLine(1f, 0.0,-9.0,  -3.0,-7.0), //Limb 5, String, left bottom
				new LimbLine(1f, 0.0,-11.0,  -3.0,-9.0), //Limb 6, String, left bottom-middle
				new LimbLine(1f, 0.0,-13.0,  -3.0,-11.0), //Limb 7, String, left top-middle
				new LimbLine(1f, 0.0,-15.0,  -3.0,-13.0) //Limb 8, String, left top.
		};
		
		
	}
	
	
	public void doAnimation(AnimationType animType, long animTime) {

		switch(animType){
		case IDLE:{
			int iIdleAnimTime = Math.abs((int)(animTime % 60));
			double dSwayMagnitude = -1 * Math.cos((2 * Math.PI / 60) * (iIdleAnimTime));
			sklaSkeleton[1].setDoubleY2(dSwayMagnitude - 4);
			sklaSkeleton[2].setDoubleY2(dSwayMagnitude - 6);
			sklaSkeleton[3].setDoubleY2(dSwayMagnitude - 8);
			sklaSkeleton[4].setDoubleY2(dSwayMagnitude - 10);
			sklaSkeleton[5].setDoubleY2(dSwayMagnitude - 4);
			sklaSkeleton[6].setDoubleY2(dSwayMagnitude - 6);
			sklaSkeleton[7].setDoubleY2(dSwayMagnitude - 8);
			sklaSkeleton[8].setDoubleY2(dSwayMagnitude - 10);
			break;
		}
		
		
		
		
		}

	}

}