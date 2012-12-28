package dungEntity;

abstract class EntityController {
	
	private int iEntityID;
	
	abstract public boolean isEntityDead();
	abstract public void doNextAction();
	
	public void setEntityID(int entityID){
		iEntityID = entityID;
	}
	
	
}