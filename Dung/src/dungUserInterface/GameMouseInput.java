package dungUserInterface;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
/**
 * GameMouseInput:
 * A class that implements MouseListener, handling all the presses of MouseListener and forwarding them into the boolean array of actions
 * that is parsed by the Gameplay loop at its own leisure.
 */
public class GameMouseInput extends GameActions implements MouseListener {

	@Override
	public void mouseClicked(MouseEvent mev) {
		// TODO Auto-generated method stub
		mev.getButton();
	}

	@Override
	public void mouseEntered(MouseEvent mev) {
		// TODO Auto-generated method stub
	}

	@Override
	public void mouseExited(MouseEvent mev) {
		// TODO Auto-generated method stub
	}

	@Override
	public void mousePressed(MouseEvent mev) {
		// TODO Auto-generated method stub
		int iActionOfButton = -1;

		if (mev.getButton() == MouseEvent.BUTTON1){
			iActionOfButton = GameSettings.iaActionOfButton[260];
		} else if (mev.getButton() == MouseEvent.BUTTON2){
			iActionOfButton = GameSettings.iaActionOfButton[261];
		} else if (mev.getButton() == MouseEvent.BUTTON3){
			iActionOfButton = GameSettings.iaActionOfButton[262];
		}

		if (iActionOfButton >= 0){
			if (iActionOfButton <= TOP_INDEX_OF_TRIGGERS){
				GameInput.baActions[iActionOfButton] = true;
				if (iActionOfButton == ATTACK_USE_PRIMARY){
					GameInput.baActions[ATTACK_RELEASE_PRIMARY] = false;
				} else if (iActionOfButton == ATTACK_USE_SECONDARY){
					GameInput.baActions[ATTACK_RELEASE_SECONDARY] = false;
				}
			} else if (iActionOfButton > TOP_INDEX_OF_TRIGGERS && iActionOfButton <= TOP_INDEX_OF_REQUESTS){
				GameInput.baActions[iActionOfButton] = true;
			}
		}

	}

	@Override
	public void mouseReleased(MouseEvent mev) {
		// TODO Auto-generated method stub
		int iActionOfButton = -1;

		if (mev.getButton() == MouseEvent.BUTTON1){
			iActionOfButton = GameSettings.iaActionOfButton[260];
		} else if (mev.getButton() == MouseEvent.BUTTON2){
			iActionOfButton = GameSettings.iaActionOfButton[261];
		} else if (mev.getButton() == MouseEvent.BUTTON3){
			iActionOfButton = GameSettings.iaActionOfButton[262];
		}
		if (iActionOfButton >= 0){
			if (iActionOfButton <= TOP_INDEX_OF_TRIGGERS){
				GameInput.baActions[iActionOfButton] = false;
				if (iActionOfButton == ATTACK_USE_PRIMARY){
					GameInput.baActions[ATTACK_RELEASE_PRIMARY] = true;
				} else if (iActionOfButton == ATTACK_USE_SECONDARY){
					GameInput.baActions[ATTACK_RELEASE_SECONDARY] = true;
				}
			} else if (iActionOfButton <= TOP_INDEX_OF_REQUESTS){
				GameInput.baActions[iActionOfButton] = true;
			}
		}

	}


}