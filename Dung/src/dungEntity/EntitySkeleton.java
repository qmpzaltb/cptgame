package dungEntity;

/**
 * EntitySkeleton:
 * A abstract class that defines the "skeleton" of an entity.
 * A skeleton is composed of "limbs".
 * The movement and/or transformation of the limbs creates animation.
 */
public abstract class EntitySkeleton {

	public SkeletonLimb[] sklaSkeleton;
	
	
	abstract public void doAnimation(AnimationType animType, long animTime);
	//What this method should do is move the skeleton limbs according to the different animations and such.
	
	
}
