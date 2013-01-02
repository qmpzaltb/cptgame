package dungEntity;

public abstract class EntityController {
	
	protected int iEntityID;
	
	abstract public boolean isEntityDead();
	abstract public void doNextAction();
	abstract public void doIntersectionAction();
	
	public void setEntityID(int entityID){
		iEntityID = entityID;
	}
	
	
}