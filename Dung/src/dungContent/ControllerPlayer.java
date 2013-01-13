package dungContent;

import dungEntity.EntityController;
import dungEntity.AnimationType;
import dungEntity.EntitySpatialKnowledge;
import dungMain.DungeonGame;
import static dungMain.DungeonGame.handleEntity;
import dungUserInterface.GameActions;
import dungUserInterface.GameInput;

/**
 * ControllerPlayer:
 * A class that converts the input information from the dungUserInterface package to entity movement.
 * This class is designed to be compatible with the player-centric nature of the GameGraphics class - 
 * (hence the static variables).
 * WARNING: This class should only be the controller for one entity.
 */
public class ControllerPlayer extends EntityController{

	public static int iPlayerEntityID;
	
	
	private static EntitySpatialKnowledge spkKnowledge;
	private int currentX; //For updating EntitySpatialKnowledge in a conservative matter (i.e., not every frame, but only when the tile changes.)
	private int currentY;
	
	public ControllerPlayer(){
		super();
		spkKnowledge = new EntitySpatialKnowledge(iPlayerEntityID, 5);
		currentX = -1;
		currentY = -1;
	}
	
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
	
	
	public void doNextAction() {
		handleEntity(iEntityID).dHeading = GameInput.getHeading();
		handleEntity(iEntityID).bEntityMoving = false;
		
		if (currentX != (int)handleEntity(iEntityID).getXPos() || currentY != (int)handleEntity(iEntityID).getYPos()){
			currentX = (int)handleEntity(iEntityID).getXPos();
			currentY = (int)handleEntity(iEntityID).getYPos();
			spkKnowledge.updateKnowledge();
		}
		
		
		
		//MOVEMENT HANDLING FROM INPUT BEGINS
		if (GameInput.baActions[GameActions.MOVE_UP] && GameInput.baActions[GameActions.MOVE_LEFT]){ //Moves Up Left
			handleEntity(iEntityID).bEntityMoving = true;
			handleEntity(iEntityID).setMovementDirection(Math.PI / (-4));
		} else if (GameInput.baActions[GameActions.MOVE_UP] && GameInput.baActions[GameActions.MOVE_RIGHT]) { //Moves Up Right
			handleEntity(iEntityID).bEntityMoving = true;
			handleEntity(iEntityID).setMovementDirection(Math.PI / (4));
		} else if (GameInput.baActions[GameActions.MOVE_DOWN] && GameInput.baActions[GameActions.MOVE_LEFT]) { //Moves Down Left
			handleEntity(iEntityID).bEntityMoving = true;
			handleEntity(iEntityID).setMovementDirection( 3 * Math.PI / (-4));
		} else if (GameInput.baActions[GameActions.MOVE_DOWN] && GameInput.baActions[GameActions.MOVE_RIGHT]) { //Moves Down Right
			handleEntity(iEntityID).bEntityMoving = true;
			handleEntity(iEntityID).setMovementDirection( 3 * Math.PI / (4));
		} else if (GameInput.baActions[GameActions.MOVE_UP]){
			handleEntity(iEntityID).bEntityMoving = true;
			handleEntity(iEntityID).setMovementDirection(0);
		} else if (GameInput.baActions[GameActions.MOVE_LEFT]){
			handleEntity(iEntityID).bEntityMoving = true;
			handleEntity(iEntityID).setMovementDirection(Math.PI / -2);
		} else if (GameInput.baActions[GameActions.MOVE_DOWN]) {
			handleEntity(iEntityID).bEntityMoving = true;
			handleEntity(iEntityID).setMovementDirection(Math.PI);
		} else if (GameInput.baActions[GameActions.MOVE_RIGHT]){
			handleEntity(iEntityID).bEntityMoving = true;
			handleEntity(iEntityID).setMovementDirection(Math.PI / 2);
		} else if (GameInput.baActions[GameActions.MOVE_FORWARD]){
			handleEntity(iEntityID).bEntityMoving = true;
			handleEntity(iEntityID).setMovementDirection(handleEntity(iEntityID).dHeading);
		} else if (GameInput.baActions[GameActions.MOVE_BACKWARD]){
			handleEntity(iEntityID).bEntityMoving = true;
			handleEntity(iEntityID).setMovementDirection(handleEntity(iEntityID).dHeading + Math.PI);
		}

		//Glitch : the speed modified will continue even if shift is not pressed. This is done by holding shift, then pressing and holding down any WASD keys, then releasing the shift key.
		//That is a feature, not a glitch. Instead of being a push-to-sprint, the game can also be a toggle-to-sprint. This is controllable in GameSettings with the boolean bModifiersAreToggled.
		//Why did I make this feature? Key ghosting. My keyboard can't sprint up-right, because it doesnt allow those three keys to be pressed at the same time.
		if(GameInput.baActions[GameActions.SPEED_MODIFIER]){
			handleEntity(iEntityID).dMovementMagnitude = handleEntity(iEntityID).dNormalSpeed * 6;
		} else {
			handleEntity(iEntityID).dMovementMagnitude = handleEntity(iEntityID).dNormalSpeed;
		}
		//END OF MOVEMENT HANDLING
		
		
		//BEGINNING OF ANIMATING MOVEMENT
		if (handleEntity(iEntityID).bEntityMoving){
			handleEntity(iEntityID).lEntityMovingTime ++;
			DungeonGame.moveEntity(iEntityID);
		} else {
			handleEntity(iEntityID).lEntityMovingTime = 0;
		}
		
		handleEntity(iEntityID).ensSkeleton.doAnimation( AnimationType.MOVE , handleEntity(iEntityID).lEntityMovingTime);
		
		//END OF ANIMATING MOVEMENT
		
		
		//BEGINNING OF ANIMATING SPECIAL ACTIONS
		if (handleEntity(iEntityID).lEntityActionTime <= 0){
			handleEntity(iEntityID).entityAction = AnimationType.IDLE;
			handleEntity(iEntityID).lEntityActionTime = 0;
			handleEntity(iEntityID).ensSkeleton.doAnimation(AnimationType.IDLE, 0);
		}
		
		if (handleEntity(iEntityID).entityAction == AnimationType.IDLE){
			if (GameInput.baActions[GameActions.ATTACK_USE_PRIMARY]){
					handleEntity(iEntityID).lEntityActionTime = 45;
					handleEntity(iEntityID).entityAction = AnimationType.ATTACK_SPEAR_RIGHTHAND;
			}
			if (GameInput.baActions[GameActions.ATTACK_USE_SECONDARY]){
				handleEntity(iEntityID).lEntityActionTime = 45;
				handleEntity(iEntityID).entityAction = AnimationType.ATTACK_SPEAR_LEFTHAND;
		}
		} else {
			handleEntity(iEntityID).ensSkeleton.doAnimation(handleEntity(iEntityID).entityAction, handleEntity(iEntityID).lEntityActionTime);
			
		}
		
		
		
		handleEntity(iEntityID).lEntityActionTime -= 1;
		//END OF ANIMATING SPECIAL ACTIONS
		
		
		
	}

	@Override
	public void doIntersectionAction() {
		// TODO Auto-generated method stub
		
	}
	
	public static EntitySpatialKnowledge getKnowledge(){
		return spkKnowledge;
	}
	
	
}