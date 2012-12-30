package dungUserInterface;

import java.awt.MouseInfo;
import dungMain.DungeonGame;

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
		int iXFromCenter = getMouseX() -DungeonGame.getCenterOfWindowX();
		int iYFromCenter = DungeonGame.getCenterOfWindowY() - getMouseY();

		//System.out.println("X"+iXFromCenter);
		//System.out.println("Y"+iYFromCenter);

		return Math.atan2(iXFromCenter , iYFromCenter);
	}

}
