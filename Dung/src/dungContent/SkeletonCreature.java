package dungContent;

import java.awt.Color;

import dungEntity.AnimationType;
import dungEntity.EntitySkeleton;
import dungEntity.SkeletonLimb;

/**
 * SkeletonCreature:
 * A class that defines the limbs present in a creature-like entity.
 * The creature is shaped in the form of a standard quadripedal animal.
 * Entities that may use this skeleton include: grimy dogs, dirty cats, potentially rats.
 */
public class SkeletonCreature extends EntitySkeleton{

	public SkeletonCreature(){
		sklaSkeleton = new SkeletonLimb[]{
				new LimbLine(2f, -5.0, 0.0, -5.0, 0.0),//Front Left leg, Limb 0
				new LimbLine(2f, +5.0, 0.0, +5.0, 0.0),//Front Right leg, Limb 1
				new LimbLine(2f, -5.0, 10.0, -5.0, 10.0),//Back Left leg, Limb 2
				new LimbLine(2f, +5.0, 10.0, +5.0, 10.0),//Back Right leg, Limb 3
				new LimbLine(1f,  0.0, 15.0, +0.0, 20.0),//Tail, Limb 4
				new LimbOval(-5.0, -5.0, 10.0, 20.0),//Torso, Limb 5
				new LimbOval(-5.0, -15.0, 10.0, 12.5)//Head, Limb 6
		};

	}

	@Override
	public void doAnimation(AnimationType animType, long animTime) {
		switch (animType){
		case MOVE:{


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
			sklaSkeleton[2].setDoubleY1(10.0);
			sklaSkeleton[2].setDoubleX2(-5.0);
			sklaSkeleton[2].setDoubleY2(10.0);

			//Resets back right leg
			sklaSkeleton[3].setDoubleX1(+5.0);
			sklaSkeleton[3].setDoubleY1(10.0);
			sklaSkeleton[3].setDoubleX2(+5.0);
			sklaSkeleton[3].setDoubleY2(10.0);

			//Resets tail
			sklaSkeleton[4].setDoubleX1(0.0);
			sklaSkeleton[4].setDoubleY1(15.0);
			sklaSkeleton[4].setDoubleX2(0.0);
			sklaSkeleton[4].setDoubleY2(20.0);

			//Resets Torso
			sklaSkeleton[5].setDoubleX1(-5.0);
			sklaSkeleton[5].setDoubleY1(-5.0);
			sklaSkeleton[5].setDoubleX2(10.0);
			sklaSkeleton[5].setDoubleY2(20.0);

			//Resets head
			sklaSkeleton[6].setDoubleX1(-5.0);
			sklaSkeleton[6].setDoubleY1(-15.0);
			sklaSkeleton[6].setDoubleX2(10.0);
			sklaSkeleton[6].setDoubleY2(12.5);
			break;
		}
		case CREATURE_ATTACK:{
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

		default:
			break;
		}


	}

}
