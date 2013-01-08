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
		DungeonGame.handleEntity(iEntityID).dHeading = GameInput.getHeading();
		DungeonGame.handleEntity(iEntityID).bEntityMoving = false;
		
		
		
		
		//MOVEMENT HANDLING FROM INPUT BEGINS
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
		} else if (GameInput.baActions[GameActions.MOVE_UP]){
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
		} else if (GameInput.baActions[GameActions.MOVE_FORWARD]){
			DungeonGame.handleEntity(iEntityID).bEntityMoving = true;
			DungeonGame.handleEntity(iEntityID).setMovementDirection(DungeonGame.handleEntity(iEntityID).dHeading);
		} else if (GameInput.baActions[GameActions.MOVE_BACKWARD]){
			DungeonGame.handleEntity(iEntityID).bEntityMoving = true;
			DungeonGame.handleEntity(iEntityID).setMovementDirection(DungeonGame.handleEntity(iEntityID).dHeading + Math.PI);
		}

		//Glitch : the speed modified will continue even if shift is not pressed. This is done by holding shift, then pressing and holding down any WASD keys, then releasing the shift key.
		//That is a feature, not a glitch. Instead of being a push-to-sprint, the game can also be a toggle-to-sprint. This is controllable in GameSettings with the boolean bModifiersAreToggled.
		//Why did I make this feature? Key ghosting. My keyboard can't sprint up-right, because it doesnt allow those three keys to be pressed at the same time.
		if(GameInput.baActions[GameActions.SPEED_MODIFIER]){
			DungeonGame.handleEntity(iEntityID).dMovementMagnitude = DungeonGame.handleEntity(iEntityID).dNormalSpeed *2;
		} else {
			DungeonGame.handleEntity(iEntityID).dMovementMagnitude = DungeonGame.handleEntity(iEntityID).dNormalSpeed;
		}
		//END OF MOVEMENT HANDLING
		
		
		//BEGINNING OF ANIMATING MOVEMENT
		if (DungeonGame.handleEntity(iEntityID).bEntityMoving){
			DungeonGame.handleEntity(iEntityID).lEntityMovingTime ++;
			DungeonGame.moveEntity(iEntityID);
		} else {
			DungeonGame.handleEntity(iEntityID).lEntityMovingTime = 0;
		}
		
		DungeonGame.handleEntity(iEntityID).ensSkeleton.doAnimation( AnimationType.MOVE , DungeonGame.handleEntity(iEntityID).lEntityMovingTime);
		
		//END OF ANIMATING MOVEMENT
		
		
		//BEGINNING OF ANIMATING SPECIAL ACTIONS
		if (DungeonGame.handleEntity(iEntityID).lEntityActionTime <= 0){
			DungeonGame.handleEntity(iEntityID).entityAction = AnimationType.IDLE;
			DungeonGame.handleEntity(iEntityID).lEntityActionTime = 0;
			DungeonGame.handleEntity(iEntityID).ensSkeleton.doAnimation(AnimationType.IDLE, 0);
		}
		
		if (DungeonGame.handleEntity(iEntityID).entityAction == AnimationType.IDLE){
			if (GameInput.baActions[GameActions.ATTACK_USE_PRIMARY]){
					DungeonGame.handleEntity(iEntityID).lEntityActionTime = 45;
					DungeonGame.handleEntity(iEntityID).entityAction = AnimationType.ATTACK_SPEAR_RIGHTHAND;
			}
			if (GameInput.baActions[GameActions.ATTACK_USE_SECONDARY]){
				DungeonGame.handleEntity(iEntityID).lEntityActionTime = 45;
				DungeonGame.handleEntity(iEntityID).entityAction = AnimationType.ATTACK_SPEAR_LEFTHAND;
		}
		} else {
			DungeonGame.handleEntity(iEntityID).ensSkeleton.doAnimation(DungeonGame.handleEntity(iEntityID).entityAction, DungeonGame.handleEntity(iEntityID).lEntityActionTime);
			
		}
		
		DungeonGame.handleEntity(iEntityID).lEntityActionTime -= 1;
		//END OF ANIMATING SPECIAL ACTIONS
		
		
		
	}

	@Override
	public void doIntersectionAction() {
		// TODO Auto-generated method stub
		
	}
	
	
}