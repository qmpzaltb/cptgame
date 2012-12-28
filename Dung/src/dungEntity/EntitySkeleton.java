package dungEntity;

public abstract class EntitySkeleton {

	private SkeletonLimb[] sklaSkeleton;
	
	public static int ANIMTYPE_ATTACK_SWORD_RIGHTHAND = 3;
	public static int ANIMTYPE_ATTACK_SPEAR_RIGHTHAND = 4;
	public static int ANIMTYPE_ATTACK_SWORD_LEFTHAND = 5;
	public static int ANIMTYPE_ATTACK_SPEAR_LEFTHAND = 6;
	public static int ANIMTYPE_DEFEND_SHIELD_RIGHTHAND = 7;
	public static int ANIMTYPE_DEFEND_SHIELD_LEFTHAND = 8;
	
	abstract public void doSpecificAnimation(int animType, int timeSinceAnimStart);
	//What this method should do is move the skeleton limbs according to the different animations and such.
	
	abstract public void doWalkAnimation();
	abstract public void doIdleAnimation();
	abstract public void doDeathAnimation();
	
	public SkeletonLimb getLimb(int limbIndex){
		return sklaSkeleton[limbIndex];
	}
	
}
