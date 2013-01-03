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
			DungeonGame.handleEntity(iEntityID).bEntityMoving = true;
			DungeonGame.handleEntity(iEntityID).setMovementDirection(Math.PI / (-4));
		} else if (GameInput.baActions[GameActions.MOVE_UP] && GameInput.baActions[GameActions.MOVE_RIGHT]) { //Moves Up Right
			DungeonGame.handleEntity(iEntityID).bEntityMoving = true;
			DungeonGame.handleEntity(iEntityID).setMovementDirection(Math.PI / (4));
		} else if (GameInput.baActions[GameActions.MOVE_DOWN] && GameInput.baActions[GameActions.MOVE_LEFT]) { //Moves Down Left
			DungeonGame.handleEntity(iEntityID).bEntityMoving = true;
			DungeonGame.handleEntity(iEntityID).setMovementDirection( 3 * Math.PI / (-4));
		} else if (GameInput.baActions[GameActions.MOVE_DOWN] && GameInput.baActions[GameActions.MOVE_RIGHT]) { //Moves Down Right
			DungeonGame.handleEntity(iEntityID).bEntityMoving = true;
			DungeonGame.handleEntity(iEntityID).setMovementDirection( 3 * Math.PI / (4));
		} else {
			if (GameInput.baActions[GameActions.MOVE_UP]){
				DungeonGame.handleEntity(iEntityID).bEntityMoving = true;
				DungeonGame.handleEntity(iEntityID).setMovementDirection(0);
			} else if (GameInput.baActions[GameActions.MOVE_LEFT]){
				DungeonGame.handleEntity(iEntityID).bEntityMoving = true;
				DungeonGame.handleEntity(iEntityID).setMovementDirection(Math.PI / -2);
			} else if (GameInput.baActions[GameActions.MOVE_DOWN]) {
				DungeonGame.handleEntity(iEntityID).bEntityMoving = true;
				DungeonGame.handleEntity(iEntityID).setMovementDirection(Math.PI);
			} else if (GameInput.baActions[GameActions.MOVE_RIGHT]){
				DungeonGame.handleEntity(iEntityID).bEntityMoving = true;
				DungeonGame.handleEntity(iEntityID).setMovementDirection(Math.PI / 2);
			}
		}

		
		if (DungeonGame.handleEntity(iEntityID).bEntityMoving){
			DungeonGame.handleEntity(iEntityID).lEntityMovingTime ++;
			DungeonGame.moveEntity(iEntityID);
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