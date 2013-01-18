package dungEntity;


/**
 * EntityController:
 * An abstract class that is extended by other controllers.
 * EntityControllers are classes that decide the next action of the entity.
 * This may be based on criteria ranging from what buttons the player has pressed to what the enemy entities see.
 */
public abstract class EntityController {
	
	protected int iEntityID;
	
	abstract public boolean isEntityDead();
	abstract public void doNextAction();
	abstract public void doIntersectionAction();
	
	public Item[] initializeInventory(){
		return null;
	}
	public void setEntityID(int entityID){
		iEntityID = entityID;
	}
	
	
}