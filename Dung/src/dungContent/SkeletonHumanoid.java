package dungContent;

import java.awt.Color;

import dungEntity.AnimationType;
import dungEntity.EntitySkeleton;
import dungEntity.SkeletonLimb;

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
		
		sklaSkeleton[7].colLimbColor = Color.YELLOW;
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
			sklaSkeleton[0].setDoubleX1(+5.0);
			sklaSkeleton[0].setDoubleY1(0.0);
			sklaSkeleton[0].setDoubleX2(+5.0);
			sklaSkeleton[0].setDoubleY2(0.0);
			sklaSkeleton[1].setDoubleX1(-5.0);
			sklaSkeleton[1].setDoubleY1(0.0);
			sklaSkeleton[1].setDoubleX2(-5.0);
			sklaSkeleton[1].setDoubleY2(0.0);
			break;
			//And then arms stuff if applicable.
		}
		case ATTACK_SPEAR_RIGHTHAND:{
			int iTimeInAnimCycle = 30 - (int)(animTime);
			if (iTimeInAnimCycle < 15){
				sklaSkeleton[3].setDoubleY2(5 * Math.sin((2 * Math.PI / 15) * iTimeInAnimCycle));
				sklaSkeleton[5].setDoubleY1(5 * Math.sin((2 * Math.PI / 15) * iTimeInAnimCycle));
				sklaSkeleton[5].setDoubleY2(5 * Math.sin((2 * Math.PI / 15) * iTimeInAnimCycle) - 5);
			} else if (iTimeInAnimCycle < 30){
				sklaSkeleton[3].setDoubleY2(-5 * Math.cos((2 * Math.PI / 7.5) * iTimeInAnimCycle));
				sklaSkeleton[5].setDoubleY1(-5 * Math.cos((2 * Math.PI / 7.5) * iTimeInAnimCycle));
				sklaSkeleton[5].setDoubleY2(-5 * Math.cos((2 * Math.PI / 7.5) * iTimeInAnimCycle) - 5);
			}
			break;
		}
			
		default:
			break;
		}

	}
}
