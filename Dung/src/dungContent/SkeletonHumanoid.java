package dungContent;

import java.awt.Color;

import dungEntity.AnimationType;
import dungEntity.EntitySkeleton;
import dungEntity.SkeletonLimb;

/**
 * SkeletonHumanoid:
 * A class that defines a human-like entity (a bipedal, upright-standing animal)
 * Entities that may use this skeleton include player characters, cleaning-supplies-merchants, dirt zombies, etc.
 */
public class SkeletonHumanoid extends EntitySkeleton{
	
	public SkeletonHumanoid(){
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
		
	}
	
	
	
	
	public void doAnimation(AnimationType animType, long animTime) {
		switch (animType){
		case MOVE:{
			
			//Equation for sine function: 10sin((2PI/60)x) <-- Clarity purposes.
			
			int iTimeInAnimCycle = (int)(animTime % 60); //60 because 60. Arbitrary period of the animation.
			sklaSkeleton[0].setDoubleY2(10 * Math.sin((2 * Math.PI / 60) * iTimeInAnimCycle)); //0.104719755119 = 2PI / 60. Advanced functions, yeah!
			sklaSkeleton[1].setDoubleY2(10 * Math.sin((-2 * Math.PI / 60) * iTimeInAnimCycle));
			break;
		}
		case IDLE:{
			sklaSkeleton[3].setDoubleX1(+10.0);
			sklaSkeleton[3].setDoubleY1(0.0);
			sklaSkeleton[3].setDoubleX2(+15.0);
			sklaSkeleton[3].setDoubleY2(0.0);
			sklaSkeleton[4].setDoubleX1(-10.0);
			sklaSkeleton[4].setDoubleY1(0.0);
			sklaSkeleton[4].setDoubleX2(-15.0);
			sklaSkeleton[4].setDoubleY2(0.0);
			sklaSkeleton[5].setDoubleX1(+15.0);
			sklaSkeleton[5].setDoubleY1(0.0);
			sklaSkeleton[5].setDoubleX2(+15.0);
			sklaSkeleton[5].setDoubleY2(-5.0);
			sklaSkeleton[6].setDoubleX1(-15.0);
			sklaSkeleton[6].setDoubleY1(0.0);
			sklaSkeleton[6].setDoubleX2(-15.0);
			sklaSkeleton[6].setDoubleY2(-5.0);
			break;
			//And then arms stuff if applicable.
		}
		case ATTACK_SPEAR_RIGHTHAND:{
			int iAnimTime = 45 - (int)(animTime);
			if (iAnimTime < 15){
				sklaSkeleton[3].setDoubleY2(5 * Math.sin((2 * Math.PI / 60) * iAnimTime));
				sklaSkeleton[5].setDoubleY1(5 * Math.sin((2 * Math.PI / 60) * iAnimTime));
				sklaSkeleton[5].setDoubleY2(5 * Math.sin((2 * Math.PI / 60) * iAnimTime) - 5);
			} else if (iAnimTime < 30){
				sklaSkeleton[3].setDoubleY2(-10 * Math.cos((2 * Math.PI / 60) * (iAnimTime - 15)) - 5);
				sklaSkeleton[5].setDoubleY1(-10 * Math.cos((2 * Math.PI / 60) * (iAnimTime - 15)) - 5);
				sklaSkeleton[5].setDoubleY2(-10 * Math.cos((2 * Math.PI / 60) * (iAnimTime - 15)) - 5 - 5);
			} else if (iAnimTime < 45){
				sklaSkeleton[3].setDoubleY2(-5.0 * Math.cos((2 * Math.PI / 60) * (iAnimTime - 30)));
				sklaSkeleton[5].setDoubleY1(-5.0 * Math.cos((2 * Math.PI / 60) * (iAnimTime - 30)));
				sklaSkeleton[5].setDoubleY2(-5.0 * Math.cos((2 * Math.PI / 60) * (iAnimTime - 30)) - 5);
			}
			break;
		}
		
		case ATTACK_SPEAR_LEFTHAND:{
			int iAnimTime = 45 - (int)(animTime);
			if (iAnimTime < 15){
				sklaSkeleton[4].setDoubleY2(5 * Math.sin((2 * Math.PI / 60) * iAnimTime));
				sklaSkeleton[6].setDoubleY1(5 * Math.sin((2 * Math.PI / 60) * iAnimTime));
				sklaSkeleton[6].setDoubleY2(5 * Math.sin((2 * Math.PI / 60) * iAnimTime) - 5);
			} else if (iAnimTime < 30){
				sklaSkeleton[4].setDoubleY2(-10 * Math.cos((2 * Math.PI / 60) * (iAnimTime - 15)) - 5);
				sklaSkeleton[6].setDoubleY1(-10 * Math.cos((2 * Math.PI / 60) * (iAnimTime - 15)) - 5);
				sklaSkeleton[6].setDoubleY2(-10 * Math.cos((2 * Math.PI / 60) * (iAnimTime - 15)) - 5 - 5);
			} else if (iAnimTime < 45){
				sklaSkeleton[4].setDoubleY2(-5.0 * Math.cos((2 * Math.PI / 60) * (iAnimTime - 30)));
				sklaSkeleton[6].setDoubleY1(-5.0 * Math.cos((2 * Math.PI / 60) * (iAnimTime - 30)));
				sklaSkeleton[6].setDoubleY2(-5.0 * Math.cos((2 * Math.PI / 60) * (iAnimTime - 30)) - 5);
			}
			break;
		}
			
		default:
			break;
		}

	}
}
