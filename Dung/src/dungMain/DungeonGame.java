package dungMain;

import java.util.ArrayList;
import dungUserInterface.GameActions;
import dungUserInterface.GameInput;
import dungUserInterface.GameSettings;
import dungUserInterface.GameWindow;
import dungContent.ContentLibrary;
import dungEntity.Entity;

public class DungeonGame {


	private static GameWindow mainGameWindow; 
	
	public static Dungeon dngCurrentDungeon;
	public static ArrayList<Entity> entalCurrentEntities;

	private static int iMSPFOGmAdj; //Adjusted value for Milliseconds per Frame Operation to account for lag.
	private static long lGameLoopStartTime;
	private static long lGameLoopEndTime;
	private static long lGameLoopTimeTaken;
	public static long lCurrentFrame; //Change this back to private later. Im doing some tests and I need some framing shit.
	private static long lTimeToSleep;
	
	public static void main(String[] args){

		mainGameWindow = new GameWindow();

		GameInput.initGameInput();
		GameSettings.initGameSettings();
		GameSettings.setDefaultKeyBindings();
		mainGameWindow.show();

		
		iMSPFOGmAdj = GameSettings.iMSPFOGm;
		lCurrentFrame = 0;
		
		entalCurrentEntities = new ArrayList<Entity>();
		entalCurrentEntities.add(new Entity(0, ContentLibrary.humanPlayer, 2.0, 2.0, 0.0));
		
		
		mainGameWindow.start();
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
		
		for (Entity toUpdate : entalCurrentEntities){
			toUpdate.encController.doNextAction();
		}

		
		
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
		
		System.out.println(lGameLoopTimeTaken);
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
	
}