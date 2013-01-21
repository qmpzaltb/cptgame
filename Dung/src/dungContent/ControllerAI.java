package dungContent;

import dungEntity.Entity;
import dungEntity.EntityController;
import dungMain.Dungeon;
import dungMain.DungeonGame;
import dungEntity.AnimationType;
import static dungMain.DungeonGame.handleEntity;
import static dungMain.DungeonGame.moveEntity;

/**
 * ControllerAI:
 * A class that controls the actions of the AI entities of the game.
 * This controller makes AI entities charge at the nearest visible entity of another alleigance.
 */
public class ControllerAI extends EntityController{

	//An arbitrary maximum distance that is compared against the distances of other hostile entities.
	public static final int MAX_DISTANCE = Dungeon.MAXIMUM_DIMENSION * Dungeon.MAXIMUM_DIMENSION + Dungeon.MINIMUM_DIMENSION * Dungeon.MINIMUM_DIMENSION;


	//Integers that are used to retrigger the scanning of all entities to determine which one to follow
	private int iFramesSinceLastEnemyCheck;
	private int iFramesUntilNewEnemyCheck = 60;

	//Variables which indicate which entity to follow, and how close this entity is.
	int iNearestEnemyEntity = -1; //-1 indicates "no entity"
	double dNearestEntityDistanceSquared = MAX_DISTANCE;
	
	
	
	
	@Override
	public boolean isEntityDead() {
		if (handleEntity(iEntityID).getIntegrity() <= 0){
			return true;
		}
		return false;
	}

	@Override
	public void doNextAction() {
		
		handleEntity(iEntityID).bDamageAreaActive = true;
		
		//CODE BLOCK:
		//Finding the closest hostile entity to the entity being controlled.
		if (iFramesUntilNewEnemyCheck < iFramesSinceLastEnemyCheck){ 	//When it is time to recalculate which entity is closest,
			
			
			iNearestEnemyEntity = -1; 									//reset the closest entity to "no entity"
			dNearestEntityDistanceSquared = MAX_DISTANCE; 				//and set the distance to pseudo-infinity (relative to the map)

			for (Entity currentEntity : DungeonGame.entveCurrentEntities){ 										//Parse through all the entities
				if (currentEntity.getAlleigance() != handleEntity(iEntityID).getAlleigance() && currentEntity.getAlleigance() != -1){ 					//If the entity being parsed is of a different alleigance,
					double dDeltaX = Math.abs(currentEntity.getXPos() - handleEntity(iEntityID).getXPos()); 	//Find the displacement X...
					double dDeltaY = Math.abs(currentEntity.getYPos() - handleEntity(iEntityID).getYPos());		//Find the displacement Y...
					double dDistanceSquared = dDeltaX * dDeltaX + dDeltaY * dDeltaY;							//Find the displacement^2 of the entities (to avoid the costly sqrt())
					if (dDistanceSquared < dNearestEntityDistanceSquared){	//If it is less than the old closest entity
						iNearestEnemyEntity = currentEntity.iEntityID;		//the new closest entity becomes this entity
						dNearestEntityDistanceSquared = dDistanceSquared;	//and the new closest distance becomes this distance
					}
				}
			}

			iFramesSinceLastEnemyCheck = 0;
			//System.out.println("CLOSING IN ON " + iNearestEnemyEntity); //Debugging message
		} else {
			iFramesSinceLastEnemyCheck += 1;
		}
		//END OF CODE BLOCK

		
		//CODE BLOCK:
		//Move to the nearest hostile entity as determined in the previous code block
		if (iNearestEnemyEntity > -1){ 				//If the nearest hostile entity is an entity (and hence, has an ID)
			double direction = Math.atan2(			//Find the direction that they are in, relative to this entity,
					handleEntity(iNearestEnemyEntity).dXPos - handleEntity(iEntityID).dXPos,
					handleEntity(iEntityID).dYPos - handleEntity(iNearestEnemyEntity).dYPos);


			handleEntity(iEntityID).setFacingDirection(direction);   	//and set this entity's facing direction
			handleEntity(iEntityID).setMovementDirection(direction); 	//and velocity direction accordingly,
			moveEntity(iEntityID); 										//then tell DungeonGame to move this entity.
			//System.out.println("MOVING");//Debugging message
		}
		//END OF CODE BLOCK
		
		//CODE BLOCK:
		//Animate entity depending on what the entity is doing.
		if (handleEntity(iEntityID).lEntityActionTime <= 0){ 			//If the entity's animation is finished,
			handleEntity(iEntityID).entityAction = AnimationType.IDLE; 	//they are idling.
		}

		if (handleEntity(iEntityID).entityAction == AnimationType.IDLE){	//If they are idling, they are eligible for another action
			handleEntity(iEntityID).lEntityActionTime = 45;	
			handleEntity(iEntityID).entityAction = AnimationType.TROLL;
		}
			
		//Finally, the skeleton is told to do the animation that the entity is doing.
		handleEntity(iEntityID).ensSkeleton.doAnimation(handleEntity(iEntityID).entityAction, handleEntity(iEntityID).lEntityActionTime);
		//END OF CODE BLOCK
		

		handleEntity(iEntityID).lEntityActionTime -= 1; //The animation progresses.
	}

	@Override
	public void doIntersectionAction() {
		// TODO Auto-generated method stub

	}

}
