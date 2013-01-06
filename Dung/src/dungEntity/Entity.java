package dungEntity;

public class Entity {
	
	public int iEntityID;
	
	
	public int iEntityIntegrity; //This will be health and stuff (40% health remaining and stuff...) The reason why I named this EntityStatus because entities like arrows can be like 50% used (this means arrow is broken and done with) or 50% unused (you haven't shot this arrow yet)
	//The reason why I renamed it to EntityIntegrity is because it can be applied to any object - how integritous(?) is the arrow? the human? the laser bullet?
	//Or maybe we should just call it health?
	public int iEntityAction; //The entity's current action. Try to use the values from GameActions from here.
	public long lEntityActionTime; //Time left in the Entity's current action
	//Should we have a variable called EntityCurrAction? and use a coding system (like used in EntityID) to make specific actions like Attack, Heal, Defend, Move, Use Item/Potion, Call Chuck Norris, etc...
	public boolean bEntityMoving;
	public long lEntityMovingTime;
	
	
	public double dRadius;
	public double dXPos;
	public double dYPos;
	public double dHeading;
	public double dNormalSpeed;
	
	public double dMovementDirection;
	public double dMovementMagnitude;
	
	public EntityController encController;
	public EntitySkeleton ensSkeleton;
	public Entity(int entityID, Entity baseEntity, double xPos, double yPos, double heading){
		dRadius = baseEntity.dRadius;
		dNormalSpeed = baseEntity.dNormalSpeed;
		encController = baseEntity.encController;
		encController.setEntityID(entityID);
		ensSkeleton = baseEntity.ensSkeleton;
		dXPos = xPos;
		dYPos = yPos;
		dHeading = heading;
		dMovementMagnitude = dNormalSpeed; //TEMPORARY LINE. THIS SHOULD GO ELSEWHERE?
	}
	
	public Entity(int entityID, double radius, EntityController controller, EntitySkeleton skeleton, double speed){
		iEntityID = entityID;
		dRadius = radius;
		dNormalSpeed = speed;
		encController = controller;
		encController.setEntityID(entityID);
		ensSkeleton = skeleton;
	}
	
	public double getXPos(){
		return dXPos;
	}
	public double getYPos(){
		return dYPos;
	}
	public double getMovementDirection(){
		return dMovementDirection;
	}
	public double getMovementMagnitude(){
		return dMovementMagnitude;
	}
	public void setMovementDirection(double direction){
		dMovementDirection = direction;
	}
	public double getNormalSpeed(){
		return dNormalSpeed;
	}
	public double getSize(){
		return dRadius;
	}
	
	
	public void shiftXPos(double shift){
		dXPos += shift;
	}
	public void shiftYPos(double shift){
		dYPos += shift;
	}
}