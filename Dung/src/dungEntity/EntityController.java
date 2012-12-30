package dungEntity;

public abstract class EntityController {
	
	protected int iEntityID;
	
	abstract public boolean isEntityDead();
	abstract public void doNextAction();
	
	public void setEntityID(int entityID){
		iEntityID = entityID;
	}
	
	
}