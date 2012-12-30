package dungContent;

import dungEntity.EntityController;
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
		DungeonGame.entalCurrentEntities.get(iEntityID).dHeading = GameInput.getHeading();
		
		
		if (GameInput.baActions[GameActions.MOVE_UP]){
			DungeonGame.entalCurrentEntities.get(iEntityID).bEntityMoving = true;
		} else {
			DungeonGame.entalCurrentEntities.get(iEntityID).bEntityMoving = false;
		}
		
		if (DungeonGame.entalCurrentEntities.get(iEntityID).bEntityMoving){
			DungeonGame.entalCurrentEntities.get(iEntityID).lEntityMovingTime ++;
		} else {
			DungeonGame.entalCurrentEntities.get(iEntityID).lEntityMovingTime = 0;
		}
		DungeonGame.entalCurrentEntities.get(iEntityID).ensSkeleton.doMoveAnimation(DungeonGame.entalCurrentEntities.get(iEntityID).lEntityMovingTime);
		
	}
	
	
}