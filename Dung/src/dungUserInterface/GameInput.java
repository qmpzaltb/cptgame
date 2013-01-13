package dungUserInterface;

import java.awt.MouseInfo;
import dungMain.DungeonGame;

/**
 * GameInput:
 * A class that acts as a "hub" for all the Listening classes.
 * This class houses the boolean array baActions, which holds the information of whether an action is desired by the player or not.
 * An actions's desirability is then used by the Gameplay loop to control the player, control the view, enter menus, etc.
 */
public class GameInput extends GameActions{
	
	public static boolean[] baActions;
	
	public static void initGameInput(){
		baActions = new boolean[ACTIONS_VARIETY];
	}
	
	public static int getMouseX(){
		return MouseInfo.getPointerInfo().getLocation().x;
	}
	public static int getMouseY(){
		return MouseInfo.getPointerInfo().getLocation().y;
	}
	
	public static double getHeading(){ // where 0.0PI heading is up, 0.5PI heading is right, 1.0PI heading is down -1.0PI heading is down, -0.5PI heading is right.
		int iXFromCenter = getMouseX() - (DungeonGame.getInsetLocationX() + GameGraphics.getXSize()/2);
		int iYFromCenter = (DungeonGame.getInsetLocationY() + (GameGraphics.getYSize()/2)) - getMouseY();

		//System.out.println("X"+iXFromCenter);
		//System.out.println("Y"+iYFromCenter); //Debugging messages

		return Math.atan2(iXFromCenter , iYFromCenter);
	}

}
