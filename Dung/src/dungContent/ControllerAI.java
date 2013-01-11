package dungContent;

import dungEntity.Entity;
import dungEntity.EntityController;
import dungMain.Dungeon;
import dungMain.DungeonGame;
import dungEntity.AnimationType;
import static dungMain.DungeonGame.handleEntity;
import static dungMain.DungeonGame.moveEntity;

public class ControllerAI extends EntityController{

 public static final int MAX_DISTANCE = Dungeon.MAXIMUM_DIMENSION * Dungeon.MAXIMUM_DIMENSION + Dungeon.MINIMUM_DIMENSION * Dungeon.MINIMUM_DIMENSION;
 
 
 private int iFramesSinceLastEnemyCheck;
 private int iFramesUntilNewEnemyCheck = 60;
 
 int iNearestEnemyEntity = -1;
 double dNearestEntityDistanceSquared = MAX_DISTANCE;
 
 @Override
 public boolean isEntityDead() {
  // TODO Auto-generated method stub
  return false;
 }

 @Override
 public void doNextAction() {
  if (iFramesUntilNewEnemyCheck < iFramesSinceLastEnemyCheck){
   iNearestEnemyEntity = -1;
   dNearestEntityDistanceSquared = MAX_DISTANCE;
   
   for (Entity currentEntity : DungeonGame.entveCurrentEntities){
    if (currentEntity.getAlleigance() != handleEntity(iEntityID).getAlleigance()){
     double dDeltaX = Math.abs(currentEntity.getXPos() - handleEntity(iEntityID).getXPos());
     double dDeltaY = Math.abs(currentEntity.getYPos() - handleEntity(iEntityID).getYPos());
     double dDistanceSquared = dDeltaX * dDeltaX + dDeltaY * dDeltaY;
     if (dDistanceSquared < dNearestEntityDistanceSquared){
      iNearestEnemyEntity = currentEntity.iEntityID;
      dNearestEntityDistanceSquared = dDistanceSquared;
     }
    }
   }
   
   iFramesSinceLastEnemyCheck = 0;
   System.out.println("CLOSING IN ON " + iNearestEnemyEntity);
  } else {
   iFramesSinceLastEnemyCheck += 1;
  }
  
  
  if (iNearestEnemyEntity > -1){
   double direction = Math.atan2(
     handleEntity(iNearestEnemyEntity).dXPos - handleEntity(iEntityID).dXPos,
     handleEntity(iEntityID).dYPos - handleEntity(iNearestEnemyEntity).dYPos);
   
   
   handleEntity(iEntityID).setFacingDirection(direction);
   handleEntity(iEntityID).setMovementDirection(direction);
   moveEntity(iEntityID);
   System.out.println("MOVING");
  }
  if (handleEntity(iEntityID).lEntityActionTime <= 0){
	 handleEntity(iEntityID).entityAction = AnimationType.IDLE;
  }
  
  if (DungeonGame.handleEntity(iEntityID).entityAction == AnimationType.IDLE){
     DungeonGame.handleEntity(iEntityID).lEntityActionTime = 45;
     DungeonGame.handleEntity(iEntityID).entityAction = AnimationType.TROLL;
  } else {
   handleEntity(iEntityID).ensSkeleton.doAnimation(handleEntity(iEntityID).entityAction, handleEntity(iEntityID).lEntityActionTime);
  }
  
  
  
  DungeonGame.handleEntity(iEntityID).lEntityActionTime -= 1;
  
 }

 @Override
 public void doIntersectionAction() {
  // TODO Auto-generated method stub
  
 }

}
