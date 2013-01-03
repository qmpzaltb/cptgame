package dungContent;

import java.awt.Color;

import dungEntity.AnimationType;
import dungEntity.EntitySkeleton;
import dungEntity.SkeletonLimb;

public class SkeletonCreature extends EntitySkeleton{
	
	public SkeletonCreature(){
		sklaSkeleton = new SkeletonLimb[]{
				new LimbLine(2f, -5.0, 0.0, -5.0, 0.0),//Front Left leg, Limb 0
				new LimbLine(2f, +5.0, 0.0, +5.0, 0.0),//Front Right leg, Limb 1
				new LimbLine(2f, -5.0, 0.0, -5.0, 0.0),//Back Left leg, Limb 2
				new LimbLine(2f, +5.0, 0.0, +5.0, 0.0),//Back Right leg, Limb 3
				new LimbOval(-10.0, -5.0, 20.0, 10.0),//Torso, Limb 4
				new LimbOval(-5.0, -5.0, 10.0, 10.0)//Head, Limb 5
		};

		sklaSkeleton[0].colLimbColor = Color.BLACK;
		sklaSkeleton[1].colLimbColor = Color.BLACK;
		sklaSkeleton[2].colLimbColor = Color.BLACK;
		sklaSkeleton[3].colLimbColor = Color.BLACK;
		sklaSkeleton[4].colLimbColor = Color.RED;
		sklaSkeleton[5].colLimbColor = Color.RED;
	}
		
	@Override
	public void doAnimation(AnimationType animType, long animTime) {
		switch (animType){
		case MOVE:{
			
			//Equation for sine function: 10sin((2PI/60)x) <-- Clarity purposes.
			
			int iTimeInAnimCycle = (int)(animTime % 60); //60 because 60. Arbitrary period of the animation.
			
			//four legged animals basically walk like regular humans but they got an extra pair of feet! (Source: Me imitating an animal walking) ... This is why I just made two copies of the humanoid walking, because that is how the legs for creatures move
			sklaSkeleton[0].setDoubleY2(10 * Math.sin((2 * Math.PI / 60) * iTimeInAnimCycle)); 
			sklaSkeleton[1].setDoubleY2(10 * Math.sin((-2 * Math.PI / 60) * iTimeInAnimCycle));
			sklaSkeleton[2].setDoubleY2(10 * Math.sin((2 * Math.PI / 60) * iTimeInAnimCycle)); 
			sklaSkeleton[3].setDoubleY2(10 * Math.sin((-2 * Math.PI / 60) * iTimeInAnimCycle));
			break;
		}
		case IDLE:{
			//Why do we reset everything? -- Because when a creature attacks, it pulls its torso back and then jumps at the humanoid
			//Resets front left leg
			sklaSkeleton[0].setDoubleX1(-5.0);
			sklaSkeleton[0].setDoubleY1(0.0);
			sklaSkeleton[0].setDoubleX2(-5.0);
			sklaSkeleton[0].setDoubleY2(0.0);
			
			//Resets front right leg
			sklaSkeleton[1].setDoubleX1(+5.0);
			sklaSkeleton[1].setDoubleY1(0.0);
			sklaSkeleton[1].setDoubleX2(+5.0);
			sklaSkeleton[1].setDoubleY2(0.0);
			
			//Resets back left leg
			sklaSkeleton[2].setDoubleX1(-5.0);
			sklaSkeleton[2].setDoubleY1(0.0);
			sklaSkeleton[2].setDoubleX2(-5.0);
			sklaSkeleton[2].setDoubleY2(0.0);
			
			//Resets back right leg
			sklaSkeleton[3].setDoubleX1(+5.0);
			sklaSkeleton[3].setDoubleY1(0.0);
			sklaSkeleton[3].setDoubleX2(+5.0);
			sklaSkeleton[3].setDoubleY2(0.0);
			
			//Resets Torso
			sklaSkeleton[4].setDoubleX1(-10.0);
			sklaSkeleton[4].setDoubleY1(-5.0);
			sklaSkeleton[4].setDoubleX2(20.0);
			sklaSkeleton[4].setDoubleY2(10.0);
			
			//Resets head
			sklaSkeleton[5].setDoubleX1(-5.0);
			sklaSkeleton[5].setDoubleY1(-5.0);
			sklaSkeleton[5].setDoubleX2(10.0);
			sklaSkeleton[5].setDoubleY2(10.0);
			break;
		}
		case CREATURE_ATTACK:{
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
