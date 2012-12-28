package dungEntity;

abstract class EntityController {
	
	private int iEntityID;
	
	abstract public boolean isEntityDead();
	abstract public void doNextAction();
	abstract public void doIntersectingEntity(int otherEntityID);
	
	public void setEntityID(int entityID){
		iEntityID = entityID;
	}
	
	
}