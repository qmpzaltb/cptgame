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
		DungeonGame.entveCurrentEntities.get(iEntityID).bEntityMoving = false;
		
		if (GameInput.baActions[GameActions.MOVE_UP] && GameInput.baActions[GameActions.MOVE_LEFT]){ //Moves Up Left
			DungeonGame.entveCurrentEntities.get(iEntityID).bEntityMoving = true;
			DungeonGame.entveCurrentEntities.get(iEntityID).dYPos -= DungeonGame.entveCurrentEntities.get(iEntityID).dSpeed / 2; //Up Reduced Speed
			DungeonGame.entveCurrentEntities.get(iEntityID).dXPos -= DungeonGame.entveCurrentEntities.get(iEntityID).dSpeed / 2; //Left Reduced Speed
		} else if (GameInput.baActions[GameActions.MOVE_UP] && GameInput.baActions[GameActions.MOVE_RIGHT]) { //Moves Up Right
			DungeonGame.entveCurrentEntities.get(iEntityID).bEntityMoving = true;
			DungeonGame.entveCurrentEntities.get(iEntityID).dYPos -= DungeonGame.entveCurrentEntities.get(iEntityID).dSpeed / 2; //Up Reduced Speed
			DungeonGame.entveCurrentEntities.get(iEntityID).dXPos += DungeonGame.entveCurrentEntities.get(iEntityID).dSpeed / 2; //Right Reduced Speed
		} else if (GameInput.baActions[GameActions.MOVE_DOWN] && GameInput.baActions[GameActions.MOVE_LEFT]) { //Moves Down Left
			DungeonGame.entveCurrentEntities.get(iEntityID).bEntityMoving = true;
			DungeonGame.entveCurrentEntities.get(iEntityID).dYPos += DungeonGame.entveCurrentEntities.get(iEntityID).dSpeed / 2; //Down Reduced Speed
			DungeonGame.entveCurrentEntities.get(iEntityID).dXPos -= DungeonGame.entveCurrentEntities.get(iEntityID).dSpeed / 2; //Left Reduced Speed
		} else if (GameInput.baActions[GameActions.MOVE_DOWN] && GameInput.baActions[GameActions.MOVE_RIGHT]) { //Moves Down Right
			DungeonGame.entveCurrentEntities.get(iEntityID).bEntityMoving = true;
			DungeonGame.entveCurrentEntities.get(iEntityID).dYPos += DungeonGame.entveCurrentEntities.get(iEntityID).dSpeed / 2; //Down Reduced Speed
			DungeonGame.entveCurrentEntities.get(iEntityID).dXPos += DungeonGame.entveCurrentEntities.get(iEntityID).dSpeed / 2; //Right Reduced Speed
		} else {
			if (GameInput.baActions[GameActions.MOVE_UP]){
				DungeonGame.entveCurrentEntities.get(iEntityID).bEntityMoving = true;
				DungeonGame.entveCurrentEntities.get(iEntityID).dYPos -= DungeonGame.entveCurrentEntities.get(iEntityID).dSpeed;
			}
			if (GameInput.baActions[GameActions.MOVE_LEFT]){
				DungeonGame.entveCurrentEntities.get(iEntityID).bEntityMoving = true;
				DungeonGame.entveCurrentEntities.get(iEntityID).dXPos -= DungeonGame.entveCurrentEntities.get(iEntityID).dSpeed;
			}
			if (GameInput.baActions[GameActions.MOVE_DOWN]) {
				DungeonGame.entveCurrentEntities.get(iEntityID).bEntityMoving = true;
				DungeonGame.entveCurrentEntities.get(iEntityID).dYPos += DungeonGame.entveCurrentEntities.get(iEntityID).dSpeed;
			}
			if (GameInput.baActions[GameActions.MOVE_RIGHT]){
				DungeonGame.entveCurrentEntities.get(iEntityID).bEntityMoving = true;
				DungeonGame.entveCurrentEntities.get(iEntityID).dXPos += DungeonGame.entveCurrentEntities.get(iEntityID).dSpeed;
			}
		}
		
		
		if (DungeonGame.entveCurrentEntities.get(iEntityID).bEntityMoving){
			DungeonGame.entveCurrentEntities.get(iEntityID).lEntityMovingTime ++;
		} else {
			DungeonGame.entveCurrentEntities.get(iEntityID).lEntityMovingTime = 0;
		}
		
		if (DungeonGame.entveCurrentEntities.get(iEntityID).lEntityActionTime <= 0){
			DungeonGame.entveCurrentEntities.get(iEntityID).iEntityAction = -1;
			DungeonGame.entveCurrentEntities.get(iEntityID).lEntityActionTime = 0;
			DungeonGame.entveCurrentEntities.get(iEntityID).ensSkeleton.doAnimation(AnimationType.IDLE, 0);
		}
		
		if (DungeonGame.entveCurrentEntities.get(iEntityID).iEntityAction == -1){
			if (GameInput.baActions[GameActions.ATTACK_USE_PRIMARY]){
					DungeonGame.entveCurrentEntities.get(iEntityID).lEntityActionTime = 30;
					DungeonGame.entveCurrentEntities.get(iEntityID).iEntityAction = GameActions.ATTACK_USE_PRIMARY;
			}
		}
		
		DungeonGame.entveCurrentEntities.get(iEntityID).ensSkeleton.doAnimation(AnimationType.ATTACK_SPEAR_RIGHTHAND, DungeonGame.entveCurrentEntities.get(iEntityID).lEntityActionTime);
		DungeonGame.entveCurrentEntities.get(iEntityID).lEntityActionTime -= 1;
		
		DungeonGame.entveCurrentEntities.get(iEntityID).ensSkeleton.doAnimation( AnimationType.MOVE , DungeonGame.entveCurrentEntities.get(iEntityID).lEntityMovingTime);
		
	}

	@Override
	public void doIntersectionAction() {
		// TODO Auto-generated method stub
		
	}
	
	
}