package dungContent;

import dungEntity.EntityController;
import dungEntity.AnimationType;
import dungMain.DungeonGame;
import dungUserInterface.GameActions;
import dungUserInterface.GameInput;


public class ControllerPlayer extends EntityController{

	public static int iPlayerEntityID;
	
	@Override
	public boolean isEntityDead() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void setEntityID(int entityID) {
		// TODO Auto-generated method stub
		super.setEntityID(entityID);
		iPlayerEntityID = entityID;
	}
	
	@Override
	public void doNextAction() {
		
		DungeonGame.entveCurrentEntities.get(iEntityID).dHeading = GameInput.getHeading();
		
		
		if (GameInput.baActions[GameActions.MOVE_UP]){
			DungeonGame.entveCurrentEntities.get(iEntityID).bEntityMoving = true;
		} else if (GameInput.baActions[GameActions.MOVE_DOWN]) {
			DungeonGame.entveCurrentEntities.get(iEntityID).bEntityMoving = true;
		} else if (GameInput.baActions[GameActions.MOVE_LEFT]){
			DungeonGame.entveCurrentEntities.get(iEntityID).bEntityMoving = true;
		} else if (GameInput.baActions[GameActions.MOVE_RIGHT]){
			DungeonGame.entveCurrentEntities.get(iEntityID).bEntityMoving = true;
		} else {
			DungeonGame.entveCurrentEntities.get(iEntityID).bEntityMoving = false;
		}
		
		if (DungeonGame.entveCurrentEntities.get(iEntityID).bEntityMoving){
			DungeonGame.entveCurrentEntities.get(iEntityID).lEntityMovingTime ++;
		} else {
			DungeonGame.entveCurrentEntities.get(iEntityID).lEntityMovingTime = 0;
		}
		DungeonGame.entveCurrentEntities.get(iEntityID).ensSkeleton.doAnimation( AnimationType.MOVE , DungeonGame.entveCurrentEntities.get(iEntityID).lEntityMovingTime);
		
	}

	@Override
	public void doIntersectionAction() {
		// TODO Auto-generated method stub
		
	}
	
	
}