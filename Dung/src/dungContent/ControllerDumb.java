package dungContent;

import dungEntity.EntityController;

/**
 * ControllerDumb:
 * A class that defines the actions of "dumb" objects, such as projectiles (water, cleaning product, etc.)
 * The next action of a "dumb" object is to move in the same direction that it was moving before.
 */
public class ControllerDumb extends EntityController{
	
	@Override
	public boolean isEntityDead() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void doNextAction() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void doIntersectionAction() {
		// TODO Auto-generated method stub
		
	}

	
	
}
