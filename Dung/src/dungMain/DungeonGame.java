package dungMain;

import java.util.Vector;

import dungUserInterface.GameActions;
import dungUserInterface.GameGraphics;
import dungUserInterface.GameInput;
import dungUserInterface.GameSettings;
import dungUserInterface.GameWindow;
import dungContent.ContentLibrary;
import dungEntity.Entity;

public class DungeonGame {


	private static GameWindow mainGameWindow; 

	public static Dungeon dngCurrentDungeon;
	public static Vector<Entity> entveCurrentEntities;
	
	private static int iMSPFOGmAdj; //Adjusted value for Milliseconds per Frame Operation to account for lag.
	private static long lGameLoopStartTime;
	private static long lGameLoopEndTime;
	private static long lGameLoopTimeTaken;
	private static long lCurrentFrame;
	private static long lTimeToSleep;

	public static int iGameReadinessState;

	public static void main(String[] args){

		iGameReadinessState = -1;
		mainGameWindow = new GameWindow();

		GameInput.initGameInput();
		GameSettings.initGameSettings();
		GameSettings.setDefaultKeyBindings();
		mainGameWindow.show();


		iMSPFOGmAdj = GameSettings.iMSPFOGm;
		lCurrentFrame = 0;
		mainGameWindow.start();

		entveCurrentEntities = new Vector<Entity>();
		entveCurrentEntities.add(new Entity(0, ContentLibrary.humanPlayer, 2.5, 2.5, 0.0));
		entveCurrentEntities.add(new Entity(1, ContentLibrary.nermanCreature , 4.0, 4.0, 0.0));

		dngCurrentDungeon = new Dungeon(-1);

		iGameReadinessState += 1;
		while (true){
			doGameLoop();
			lCurrentFrame ++;
		}

	}

	private static void doGameLoop() {

		lGameLoopStartTime = System.currentTimeMillis();

		/*
		 * for (counter; parsing through the entity array list; counter++){
		 * 		entityarraylist.get(counter).doNextAction();
		 * 		if (entityarraylist.get(counter).isDead()){
		 * 			entityarraylist.delete(counter);
		 *      }
		 * }
		 */

		for (Entity toUpdate : entveCurrentEntities){
			toUpdate.encController.doNextAction();
		}

		doNonGameplayInput();

		//Not pseudocode. This is testing stuff.
		//if (GameInput.baActions[GameActions.ATTACK_USE_PRIMARY]){
		//	System.out.println("ATTACK");
		//}


		lGameLoopEndTime = System.currentTimeMillis();
		lGameLoopTimeTaken = (lGameLoopEndTime - lGameLoopStartTime);

		//Framerate regulator for stable gameplay.
		if (lCurrentFrame % GameSettings.iFPSRegulationPeriod == 0){ //Every "GameSettings.iFPSRegulationPeriod" amount of frames,
			if (iMSPFOGmAdj < lGameLoopTimeTaken){ //If the current framerate is insufficient,
				iMSPFOGmAdj ++; //slow the game down.
			} else if (lGameLoopTimeTaken > GameSettings.iMSPFOGm){ //But if the framerate is slower than the max and the framerate is more than sufficient,
				iMSPFOGmAdj --; //speed the game up.
			}
		}

		//System.out.println("ms for gameplay frame: " + lGameLoopTimeTaken);
		lTimeToSleep = iMSPFOGmAdj - lGameLoopTimeTaken;
		if (lTimeToSleep > 0){ //Because we don't want to sleep for negative times. Because we don't know what that does.
			try {
				Thread.sleep(iMSPFOGmAdj - lGameLoopTimeTaken);
			} catch (InterruptedException e) {
				System.err.println("Who interrupted the main thread's slumber?");
			}
		}

	}

	public static void moveEntity(int iEntityID){
		//TODO if (wall then block all collisions or lose 20% of health per half second)
		//TODO Finish this method with map collision detection, entity collision detection.
		double dEntityXShift = Math.sin(handleEntity(iEntityID).getMovementDirection()) * handleEntity(iEntityID).dMovementMagnitude;
		double dEntityYShift = (-1) * Math.cos(handleEntity(iEntityID).getMovementDirection()) * handleEntity(iEntityID).dMovementMagnitude;
		double dCurrentXPos = handleEntity(iEntityID).getXPos();
		double dCurrentYPos = handleEntity(iEntityID).getYPos();
		double dCurrentSize = handleEntity(iEntityID).getSize();

		double dNewXPosCenter = dCurrentXPos + dEntityXShift;
		double dNewXPosRight = dNewXPosCenter + dCurrentSize;
		double dNewXPosLeft = dNewXPosCenter - dCurrentSize;
		double dNewYPosCenter = dCurrentYPos + dEntityYShift;
		double dNewYPosTop = dNewYPosCenter - dCurrentSize;
		double dNewYPosBot = dNewYPosCenter + dCurrentSize;
		
		if (dNewXPosRight < DungeonGame.dngCurrentDungeon.getXSize() && dNewXPosLeft >= 0 && dNewYPosBot < DungeonGame.dngCurrentDungeon.getYSize() && dNewYPosTop >= 0){

		}
		else {
			dEntityXShift = 0;
			dEntityYShift = 0;
		}
		
		if (!isWalkable(handleTile((int)(dNewXPosCenter), (int)(dNewYPosCenter)).getTileType())){

		}

		handleEntity(iEntityID).shiftXPos(dEntityXShift);
		handleEntity(iEntityID).shiftYPos(dEntityYShift);

	}

	public static Entity handleEntity(int entityID){
		return entveCurrentEntities.get(entityID);
	}
	public static DungeonTile handleTile(int x, int y){
		return dngCurrentDungeon.dtlve2DungeonTiles.get(x).get(y);
	}


	public static boolean isWalkable(TileType type){
		switch (type){
		//added the wall types just in case you guys wanted to do something different when the player walks near or over it
		case WALL:{
			return false;
		}
		case WALLEDGE:{
			return false;
		}
		case FLOOR:{
			return true;
		}
		case ENTRANCE:{
			return true;
		}
		case EXIT:{
			return true;
		}
		default:{
			return false;
		}



		}
	}
	
	private static void doNonGameplayInput(){
		if (GameInput.baActions[GameActions.ZOOM_IN]){
			GameGraphics.increaseZoom();
			//GameInput.baActions[GameActions.ZOOM_IN] = false;
		}
		if (GameInput.baActions[GameActions.ZOOM_OUT]){
			GameGraphics.decreaseZoom();
			//GameInput.baActions[GameActions.ZOOM_OUT] = false;
		}
	}



	public static int getCenterOfWindowX(){
		return mainGameWindow.getPositionX() + (mainGameWindow.getSizeX() / 2);
	}
	public static int getCenterOfWindowY(){
		return mainGameWindow.getPositionY() + (mainGameWindow.getSizeY() / 2);
	}
	public static int getInsetLocationX(){
		return mainGameWindow.getPositionX() + mainGameWindow.getInsetLeft();
	}
	public static int getInsetLocationY(){
		return mainGameWindow.getPositionY() + mainGameWindow.getInsetTop();
	}

	public static int getWindowSizeX(){
		return mainGameWindow.getSizeX();
	}
	public static int getWindowSizeY(){
		return mainGameWindow.getSizeY();
	}
	public static int getMillisecondsPerGameplayFrame(){
		return iMSPFOGmAdj;
	}

}