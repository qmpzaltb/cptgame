package dungEntity;

public abstract class EntitySkeleton {

	public SkeletonLimb[] sklaSkeleton;
	
	
	abstract public void doSpecificAnimation(AnimationType animType, int timeSinceAnimStart);
	//What this method should do is move the skeleton limbs according to the different animations and such.
	
	abstract public void doMoveAnimation(long timeSinceAnimStart);
	abstract public void doIdleAnimation(long timeSinceAnimStart);
	abstract public void doDeathAnimation(long timeSinceAnimStart);
	
	
}
