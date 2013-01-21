package dungEntity;

import java.awt.Color;

import dungContent.EntityBlueprint;
import dungMain.DungeonGame;

/**
 * Entity:
 * A class that defines any entity.
 * An entity is any object that is capable of movement.
 * Entities are controlled by their EntityController objects.
 * Entities are drawn by their EntitySkeleton objects.
 * The "original" entities are defined in the ContentLoader class.
 * All entities that exist in the game should be entities that are reconstructed with the constructor that accepts Entity (the original entities from ContentLoader).
 */
public class Entity {
	
	
	
	public int iEntityID; //The Entity's index in the vector of entities. Used for objects that are a part of the entity to modify the entity's characteristics.
	protected boolean bIsNull;
	
	protected boolean bCollidesWithEntities;
	protected boolean bCollidesWithWalls;
	
	public int iEntityIntegrityMax;
	public int iEntityIntegrityCurrent; //This will be health and stuff (40% health remaining and stuff...) The reason why I named this EntityStatus because entities like arrows can be like 50% used (this means arrow is broken and done with) or 50% unused (you haven't shot this arrow yet)
	//The reason why I renamed it to EntityIntegrity is because it can be applied to any object - how integritous(?)
	//Or maybe we should just call it health? Or hygeine?
	public long lEntityActionTime; //Time left in the Entity's current action
	public boolean bEntityMoving;
	public long lEntityMovingTime;
	
	public AnimationType entityAction; //The entity's current action. Use the enums from AnimationType.
	
	public double dRadius;
	public double dXPos;
	public double dYPos;
	public double dHeading;
	public double dNormalSpeed;
	
	public boolean bDamageAreaActive;
	public double dDamageAreaXPos;
	public double dDamageAreaYPos;
	public double dDamageAreaRadius;
	
	private int iAlleigance;
	
	public double dMovementDirection;
	public double dMovementMagnitude;
	
	public EntityController encController;
	public EntitySkeleton ensSkeleton;
	
	public Item[] itmaInventory;
	
	public Entity(){
		bIsNull = true;
	}
	
	public Entity(int entityID, EntityBlueprint ebp, double xPos, double yPos, double heading, EntityController controller, EntitySkeleton skeleton, Color[] skeletonColorSet){
		this(entityID, xPos, yPos, ebp.getRadius(), heading, ebp.getAlleigance(), ebp.getSpeed(), ebp.getEntityCollision(), ebp.getWallCollision(), controller, skeleton, skeletonColorSet);
	}
	
	public Entity(int entityID, double xPos, double yPos, double radius, double heading, int alleigance, double speed, boolean entityCollision, boolean wallCollision, EntityController controller, EntitySkeleton skeleton, Color[] skeletonColorSet){
		
		iEntityIntegrityMax = 100;
		iEntityIntegrityCurrent = iEntityIntegrityMax;
		
		iEntityID = entityID;
		dXPos = xPos;
		dYPos = yPos;
		dRadius = radius;
		dHeading = heading;
		iAlleigance = alleigance;
		dNormalSpeed = speed;
		dMovementMagnitude = speed;
		encController = controller;
		encController.setEntityID(entityID);
		ensSkeleton = skeleton;
		bCollidesWithEntities = entityCollision;
		bCollidesWithWalls = wallCollision;
		
		itmaInventory = controller.initializeInventory();
		
		//Colours the limbs according to the color array.
		for (int iuP1 = 0; iuP1 < ensSkeleton.sklaSkeleton.length; iuP1 ++){
			ensSkeleton.sklaSkeleton[iuP1].colLimbColor = skeletonColorSet[iuP1];
		}
		bIsNull = false;
	}
	
	public double getXPos(){
		return dXPos;
	}
	public double getYPos(){
		return dYPos;
	}
	public double getHeading(){
		return dHeading;
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
	public void setFacingDirection(double direction){
		dHeading = direction;
	}
	public double getNormalSpeed(){
		return dNormalSpeed;
	}
	public double getSize(){
		return dRadius;
	}
	public int getAlleigance(){
		return iAlleigance;
	}
	public void setAlleigance(byte alleigance){
		iAlleigance = alleigance;
	}
	 public boolean isNull(){
		 return bIsNull;
	 }
	 public boolean collidesWithEntities(){
		 return bCollidesWithEntities;
	 }
	public boolean collidesWithWalls(){
		return bCollidesWithWalls;
	}
	
	public void shiftXPos(double shift){
		dXPos += shift;
	}
	public void shiftYPos(double shift){
		dYPos += shift;
	}
	
	public Item getItem(int index){
		return itmaInventory[index];
	}
	
	public void addItem(Item toAdd, int index){
		
	}
	
	public int addItem(Item toAdd){
		return DungeonGame.addItem(toAdd, this);
	}
	
	
	public int getIntegrity(){
		return iEntityIntegrityCurrent;
	}
	public int getMaxIntegrity(){
		return iEntityIntegrityMax;
	}
	public void incrementIntegrity(int integrityIncrement){
		iEntityIntegrityCurrent += integrityIncrement;
		iEntityIntegrityCurrent = Math.min(iEntityIntegrityMax, iEntityIntegrityCurrent);
		iEntityIntegrityCurrent = Math.max(0, iEntityIntegrityCurrent);
	}
	
}