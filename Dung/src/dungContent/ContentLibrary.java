package dungContent;

import dungEntity.Entity;

public class ContentLibrary {
	public static final Entity humanPlayer = new Entity(-1, 0.7, new ControllerPlayer(), new SkeletonHumanoid(), 0.03);
	public static final Entity nermanAbomination = new Entity(-1, 0.8, new ControllerDumb(), new SkeletonCreature(), 0.02);
	
}
