package dungEntity;

public abstract class EntitySkeleton {

	public SkeletonLimb[] sklaSkeleton;
	
	
	abstract public void doAnimation(AnimationType animType, long timeSinceAnimStart);
	//What this method should do is move the skeleton limbs according to the different animations and such.
	
	
}
