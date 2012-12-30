package dungContent;

import dungEntity.EntityController;
import dungMain.DungeonGame;
import dungUserInterface.GameInput;

public class ControllerPlayer extends EntityController{

	@Override
	public boolean isEntityDead() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void doNextAction() {
		DungeonGame.entalCurrentEntities.get(0).dHeading = GameInput.getHeading();
	}
	
	
}