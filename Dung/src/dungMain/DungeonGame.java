/* The CPT Game: "Hygienator" OR "Cleanifier" OR "Cleansination" OR <<YOUR NAME FOR THE GAME HERE>>
 * 
 * A program by:
 *   Justin Baradi
 *	 Mark Bouchkeitch
 *   Nerman Nicholas
 * 
 * Software designed for:
 *   ICS 4U1 - Father Michael Goetz Secondary School
 * 
 * <<INSERT APPROPRIATE DATE HERE>>
 * <<INSERT OTHER INFORMATION HERE IF NEEDED>>  
 */

package dungMain;

import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.geom.Ellipse2D;
import java.util.Vector;

import dungUserInterface.GameActions;
import dungUserInterface.GameGraphics;
import dungUserInterface.GameInput;
import dungUserInterface.GameSettings;
import dungUserInterface.GameWindow;
import dungContent.ContentLibrary;
import dungEntity.Entity;

/**
 * DungeonGame:
 * The main class of the game.
 * The DungeonGame class connects the gameplay features of the game to the graphics features of the game - and initializes both as well.
 * The thread that holds the DungeonGame class can be referred to as "the gameplay thread"
 * The DungeonGame class is the home of the Game Loop, and many methods that shorten and help the code in the various Gameplay-related classes (such as handleEntity and handleTile).
 */
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
	
	public static int iCurrentMapSeed = 71;
	
	public static final double DISTANCE_TO_KEEP_FROM_WALL = 0.001;

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
		entveCurrentEntities.add(new Entity(0, ContentLibrary.humanPlayer, 2.5, 2.5, 0.0 , 0));
		entveCurrentEntities.add(new Entity(1, ContentLibrary.nermanCreature , 4.0, 4.0, 0.0 , 1));
		entveCurrentEntities.add(new Entity(2, ContentLibrary.dirtyBubble, 10.0 , 10.0 , 0.0 , 1));


		dngCurrentDungeon = new Dungeon(iCurrentMapSeed);

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
		
		boolean bXHandled = false;
		boolean bYHandled = false;
		//X and Y displacements of the entity, provided that there will be no collisions.
		double dEntityXShift = Math.sin(handleEntity(iEntityID).getMovementDirection()) * handleEntity(iEntityID).dMovementMagnitude;
		double dEntityYShift = (-1) * Math.cos(handleEntity(iEntityID).getMovementDirection()) * handleEntity(iEntityID).dMovementMagnitude;
		
		//The pre-movement xposition, yposition, and circle radius.
		double dCurrentXPos = handleEntity(iEntityID).getXPos();
		double dCurrentYPos = handleEntity(iEntityID).getYPos();
		double dCurrentSize = handleEntity(iEntityID).getSize();

		//Hypothetical post-movement coordinates of the circle's bounding box
		double dNewXPosCenter = dCurrentXPos + dEntityXShift;
		double dNewXPosRight = dNewXPosCenter + dCurrentSize;
		double dNewXPosLeft = dNewXPosCenter - dCurrentSize;
		double dNewYPosCenter = dCurrentYPos + dEntityYShift;
		double dNewYPosTop = dNewYPosCenter - dCurrentSize;
		double dNewYPosBot = dNewYPosCenter + dCurrentSize;

		//Handling right-way movement
		if (dNewXPosCenter < dngCurrentDungeon.getXSize() - dCurrentSize){
			if (dEntityXShift > 0 && !bXHandled){ //Entity is moving right
				rightMovingCollisions: for (int iuP1 = (int)dNewXPosLeft; iuP1 <= (int)dNewXPosRight; iuP1 += 1){
					for (int iuP2 = valueInBoundsY((int)(dCurrentYPos - dCurrentSize)); iuP2 <= valueInBoundsY((int)(dCurrentYPos + dCurrentSize)); iuP2 += 1){
						if (!isWalkable(handleTile(iuP1,iuP2).getTileType())){
							if (intersectsCircleMapTile(dNewXPosCenter, dCurrentYPos, dCurrentSize, iuP1, iuP2)){
								dEntityXShift = Math.max((iuP1 - dCurrentXPos) - (dCurrentSize + DISTANCE_TO_KEEP_FROM_WALL), 0);
								break rightMovingCollisions;

							}
						}
					}
				}
			bXHandled = true;
			}
		} else {
			dEntityXShift = 0;
		}

		
		//Handling left-way movement
		if (dNewXPosCenter > 0 + dCurrentSize){
			if (dEntityXShift < 0 && !bXHandled){ //Entity is moving left
				leftMovingCollisions: for (int iuP1 = (int)dNewXPosRight; iuP1 >= (int)dNewXPosLeft; iuP1 -= 1){
					for (int iuP2 = valueInBoundsY((int)(dCurrentYPos - dCurrentSize)); iuP2 <= valueInBoundsY((int)(dCurrentYPos + dCurrentSize)); iuP2 += 1){
						if (!isWalkable(handleTile(iuP1,iuP2).getTileType())){
							if (intersectsCircleMapTile(dNewXPosCenter, dCurrentYPos, dCurrentSize, iuP1, iuP2)){
								dEntityXShift = Math.min((iuP1 + 1 - dCurrentXPos) + (dCurrentSize + DISTANCE_TO_KEEP_FROM_WALL) , 0);
								break leftMovingCollisions;

							}
						}
					}
				}
			bXHandled = true;
			}
		} else {
			dEntityXShift = 0;
		}

		//Handling down-way movement
		if (dNewYPosCenter < dngCurrentDungeon.getYSize() - dCurrentSize){
			if (dEntityYShift > 0 && !bYHandled){ //Entity is moving down
				downMovingCollisions: for (int iuP1 = (int)dNewYPosTop; iuP1 <= (int)dNewYPosBot; iuP1 += 1){
					for (int iuP2 = valueInBoundsX((int)(dCurrentXPos - dCurrentSize)); iuP2 <= valueInBoundsX((int)(dCurrentXPos + dCurrentSize)); iuP2 += 1){
						if (!isWalkable(handleTile(iuP2,iuP1).getTileType())){
							if (intersectsCircleMapTile(dCurrentXPos, dNewYPosCenter, dCurrentSize, iuP2, iuP1)){
								dEntityYShift = Math.max((iuP1 - dCurrentYPos) - (dCurrentSize + DISTANCE_TO_KEEP_FROM_WALL) , 0);
								break downMovingCollisions;

							}
						}
					}
				}
			bYHandled = true;
			}
		} else {
			dEntityYShift = 0;
		}

		//Handling up-way movement
		if (dNewYPosCenter > 0 + dCurrentSize){
			if (dEntityYShift < 0 && !bYHandled){ //Entity is moving up
				upMovingCollisions: for (int iuP1 = (int)dNewYPosBot; iuP1 >= (int)dNewYPosTop; iuP1 -= 1){
					for (int iuP2 = valueInBoundsX((int)(dCurrentXPos - dCurrentSize)); iuP2 <= valueInBoundsX((int)(dCurrentXPos + dCurrentSize)); iuP2 += 1){
						if (!isWalkable(handleTile(iuP2,iuP1).getTileType())){
							if (intersectsCircleMapTile(dCurrentXPos, dNewYPosCenter, dCurrentSize, iuP2, iuP1)){
								dEntityYShift = Math.min((iuP1 + 1 - dCurrentYPos) + (dCurrentSize + DISTANCE_TO_KEEP_FROM_WALL) , 0);
								break upMovingCollisions;

							}
						}
					}
				}
			bYHandled = true;
			}
		} else {
			dEntityYShift = 0;
		}


		handleEntity(iEntityID).shiftXPos(dEntityXShift);
		handleEntity(iEntityID).shiftYPos(dEntityYShift);

	}
	private static boolean intersectsCircleMapTile(double circleX, double circleY, double circleRadius, int mapX, int mapY){

		//Distance from circle center to map tile center (non-negative)
		double dCircleTileDistanceX = Math.abs(circleX - (mapX + 0.5));
		double dCircleTileDistanceY = Math.abs(circleY - (mapY + 0.5));

		//Case: no intersection when the circle's bounding box does not intersect the tile's bounding box.
		if (dCircleTileDistanceX > (0.5 + circleRadius)){
			return false;
		}
		if (dCircleTileDistanceY > (0.5 + circleRadius)){
			return false;
		}

		//Case: Intersection when the circle's center lies inside the box
		if (dCircleTileDistanceX <= 0.5){
			return true;
		}
		if (dCircleTileDistanceY <= 0.5){
			return true;
		}

		//Case: Intersection if an identical circle placed on the map-tile's corners intersects the original circle. //Left squared to save processor speed.
		double dCornerCircleIntersectionDistance = (dCircleTileDistanceX - 0.5) * (dCircleTileDistanceX - 0.5) + (dCircleTileDistanceY - 0.5) * (dCircleTileDistanceY - 0.5);
		return (dCornerCircleIntersectionDistance <= circleRadius * circleRadius);

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
			return true;
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

	public static int valueInBoundsX(int value){
		return Math.max(Math.min(dngCurrentDungeon.iDungeonXSize, value), 0);
	}
	public static int valueInBoundsY(int value){
		return Math.max(Math.min(dngCurrentDungeon.iDungeonYSize, value), 0);
	}
	
	public static boolean isValueInBoundsX(int value){
		return (value >= 0 && value < dngCurrentDungeon.iDungeonXSize);
	}
	public static boolean isValueInBoundsY(int value){
		return (value >= 0 && value < dngCurrentDungeon.iDungeonYSize);
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