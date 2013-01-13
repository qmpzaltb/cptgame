package dungUserInterface;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;


/**
 * GameKeyboardInput:
 * A class that implements KeyListener, handling all the presses of MouseListener and forwarding them into the boolean array of actions
 * that is parsed by the Gameplay loop at its own leisure.
 */
public class GameKeyboardInput extends GameActions implements KeyListener{


	public GameKeyboardInput (){
		super();
	}

	public void keyPressed(KeyEvent key) {
		// TODO Auto-generated method stub

		//Calculates what happens to what action as a result of a key press.
		int iActionOfButton = GameSettings.iaActionOfButton[key.getKeyCode()];
		if (iActionOfButton >= 0){
			if (iActionOfButton <= TOP_INDEX_OF_TRIGGERS){
				if (GameSettings.bModifiersAreToggled && (iActionOfButton == SPEED_MODIFIER || iActionOfButton == POWER_MODIFIER || iActionOfButton == STEALTH_MODIFIER)){

				} else {
					GameInput.baActions[iActionOfButton] = true;
					if (iActionOfButton == ATTACK_USE_PRIMARY){
						GameInput.baActions[ATTACK_RELEASE_PRIMARY] = false;
						GameInput.baActions[ATTACK_USE_PRIMARY_TRIGGER] = true;
					} else if (iActionOfButton == ATTACK_USE_SECONDARY){
						GameInput.baActions[ATTACK_RELEASE_SECONDARY] = false;
						GameInput.baActions[ATTACK_USE_SECONDARY_TRIGGER] = true;
					}
				}
			} else if (iActionOfButton > TOP_INDEX_OF_TRIGGERS && iActionOfButton <= TOP_INDEX_OF_REQUESTS){
				GameInput.baActions[iActionOfButton] = true;

			}

		}
	}

	public void keyReleased(KeyEvent key) {
		// TODO Auto-generated method stub

		//Calculates what happens to what action as a result of a key release.
		int iActionOfButton = GameSettings.iaActionOfButton[key.getKeyCode()];
		if (iActionOfButton >= 0){
			if (iActionOfButton <= TOP_INDEX_OF_TRIGGERS){
				if (GameSettings.bModifiersAreToggled && (iActionOfButton == SPEED_MODIFIER || iActionOfButton == POWER_MODIFIER || iActionOfButton == STEALTH_MODIFIER)){
					GameInput.baActions[iActionOfButton] = !GameInput.baActions[iActionOfButton];
				} else {
					GameInput.baActions[iActionOfButton] = false;
					if (iActionOfButton == ATTACK_USE_PRIMARY){
						GameInput.baActions[ATTACK_RELEASE_PRIMARY] = true;
					} else if (iActionOfButton == ATTACK_USE_SECONDARY){
						GameInput.baActions[ATTACK_RELEASE_SECONDARY] = true;
					}
				}
			} else if (iActionOfButton <= TOP_INDEX_OF_REQUESTS){
				GameInput.baActions[iActionOfButton] = true;
			}
		}
	}

	public void keyTyped(KeyEvent key) {
		// TODO Auto-generated method stub
	}

}
