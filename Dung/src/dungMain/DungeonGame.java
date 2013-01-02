package dungMain;

import java.util.Vector;
import dungUserInterface.GameActions;
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
		
		dngCurrentDungeon = new Dungeon(69);
		
		iGameReadinessState += 1;
		while (true){
			doGameLoop();
			lCurrentFrame ++;
		}
		
	}
	
	public static void doGameLoop() {
		
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

		
		
		//Not pseudocode. This is testing stuff.
		//if (GameInput.baActions[GameActions.ATTACK_USE_PRIMARY]){
		//	System.out.println("ATTACK");1
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
				System.err.println("HOW DID THIS HAPPEN I DID NOT INTERRUPT ANYTHING DAMMIT NERMAN YOU CHANGED THE CODE AROUND DIDN'T YOU GAAAAH NOW I HAVE TO FIX IT. HOLY. THIS IS THE OPPOSITE OF BLESSED. MY DOLLARS DO NOT BESTOW THEMSELVES UPON YOU.");
			}
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