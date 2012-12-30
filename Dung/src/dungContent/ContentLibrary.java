package dungContent;

import dungEntity.Entity;

public class ContentLibrary {
	public static final Entity humanPlayer = new Entity(-1, 0.7, new ControllerPlayer(), new SkeletonHumanoid());
}
