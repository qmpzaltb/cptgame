/* The CPT Game: CLEANSANITY
 * 
 * A program by:
 *   Justin Baradi
 *	 Mark Bouchkeitch
 *   Nerman Nicholas
 * 
 * Software designed for:
 *   ICS 4U1 - Father Michael Goetz Secondary School
 * 
 * 1/7/2013 - 1/21/2013
 */

package dungMain;

import java.awt.Color;
import java.io.File;
import java.util.Vector;

import javax.swing.JOptionPane;

import dungUserInterface.EventType;
import dungUserInterface.GameActions;
import dungUserInterface.GameEvents;
import dungUserInterface.GameGraphics;
import dungUserInterface.GameInput;
import dungUserInterface.GameMainMenu;
import dungUserInterface.GameSettings;
import dungUserInterface.GameSounds;
import dungUserInterface.GameWindow;
import dungContent.*;
import dungEntity.*;


/**
 * DungeonGame:
 * The main class of the game.
 * The DungeonGame class connects the gameplay features of the game to the graphics features of the game - and initializes both as well.
 * The thread that holds the DungeonGame class can be referred to as "the gameplay thread"
 * The DungeonGame class is the home of the Game Loop, and many methods that shorten and help the code in the various Gameplay-related classes (such as handleEntity and handleTile).
 */
public class DungeonGame {

	public static final double DISTANCE_TO_KEEP_FROM_WALL = 0.001;

	private static String strGamePath;

	public static GameWindow mainGameWindow; 

	public static Dungeon dngCurrentDungeon;
	public static Vector<Entity> entveCurrentEntities;

	private static int iMSPFOGmAdj; //Adjusted value for Milliseconds per Frame Operation to account for lag.
	private static long lGameLoopStartTime;
	private static long lGameLoopEndTime;
	private static long lGameLoopTimeTaken;
	private static long lCurrentFrame;
	private static long lTimeToSleep;
	private static long lLastMSPFO;

	public static int iCurrentMapSeed = 42;
	//Good Seeds 4897, 27839,



	public static int iGameReadinessState;



	private static boolean bRenderGame;
	private static boolean bRenderMenu;

	/**
	 * main:
	 * This method initializes and coordinates the main actions of the game.
	 * Pre: None
	 * Post: The game has been completed
	 */
	public static void main(String[] args){
		JOptionPane.showMessageDialog(null, "CLEANSANITY\n" +
				"Instructions:\n" +
				"Use W, A, S, and D to move the Cleansinator towards the exit (a pulsating GREEN tile)\n" +
				"Clean the forces of dirt by pointing at them and pressing the RIGHT or LEFT MOUSE BUTTONS.\n" +
				"Use COMMA and PERIOD to zoom in and out.\n" +
				"Press SHIFT to toggle sprint.\n" + 
				"Use P and O to increase/decrease the volume.", "CLEANSANITY", JOptionPane.PLAIN_MESSAGE);

		
		
		String strSeedToSetTo = JOptionPane.showInputDialog(null, "Input a game seed:", "CLEANSANITY", JOptionPane.INFORMATION_MESSAGE);
		try{
			iCurrentMapSeed = Integer.parseInt(strSeedToSetTo);
		} catch (NumberFormatException e){
			iCurrentMapSeed = strSeedToSetTo.hashCode();
		}
		
		JOptionPane.showMessageDialog(null, "CLEANSANITY\n" +
				"NOW PLAYING: " + iCurrentMapSeed, "CLEANSANITY", JOptionPane.PLAIN_MESSAGE);

		strGamePath = new File("").getAbsolutePath();

		//Initialization begins
		iGameReadinessState = -1;
		mainGameWindow = new GameWindow(); 

		GameInput.initGameInput();
		GameSettings.initGameSettings();
		GameSettings.setDefaultKeyBindings();
		ColorScheme.initColorScheme();


		iMSPFOGmAdj = GameSettings.iMSPFOGm;
		lCurrentFrame = 0;
		mainGameWindow.start();

		entveCurrentEntities = new Vector<Entity>();
		addEntity(ContentLibrary.PLAYER_BLUEPRINT, 0,0,0, new ControllerPlayer(), new SkeletonHumanoid(), ContentLibrary.PLAYER_COLORS);
		//addEntity(ContentLibrary.RAT_BLUEPRINT, 10,17,0, new ControllerAI(), new SkeletonCreature(), ContentLibrary.CREATURE_COLORS);
		//addEntity(ContentLibrary.DIRTY_BUBBLE_BLUEPRINT, 15,15,0, new ControllerAI(), new SkeletonBubble(), ContentLibrary.DIRTY_BUBBLE_COLORS);
		addItem(new Item(ContentLibrary.DUSTER_BLUEPRINT, new ControllerItem(), new SkeletonDuster(), ContentLibrary.DUSTER_COLORS, 0, handleEntity(0).ensSkeleton.sklaSkeleton[5]), handleEntity(0));
		addItem(new Item(ContentLibrary.BROOM_BLUEPRINT, new ControllerItem(), new SkeletonBroom(), ContentLibrary.BROOM_COLORS, 0, handleEntity(0).ensSkeleton.sklaSkeleton[6]), handleEntity(0));

		
		dngCurrentDungeon = new Dungeon(iCurrentMapSeed);

		iGameReadinessState += 1;
		//Initialization ends
		bRenderMenu = false;
		bRenderGame = true;


		mainGameWindow.show();
		//The Gameplay Loop
		while (true){

			lGameLoopStartTime = System.currentTimeMillis();

			if (bRenderGame){
				doGameLoop();
				ColorScheme.updateColorList();
				lCurrentFrame ++;
			}
			//Do features such as zoom in, zoom out, calling out menus, etc.
			doNonGameplayInput();
			//Checks whether different music needs to be played
			GameSounds.updateGameSounds();
			regulateFramerate();
		}
	}

	
	/**
	 * doGameLoop:
	 * This method coordinates the gameplay actions of the game.
	 * Pre: The game must have been initialized.
	 * Post: The game's current frame has been computed.
	 */
	private static void doGameLoop() {

		//Tells the entity controllers of every entity to do their next action.
		for (Entity toUpdate : entveCurrentEntities){
			if (!toUpdate.isNull()){
				toUpdate.encController.doNextAction();
				if (toUpdate.encController.isEntityDead()){
					removeEntity(toUpdate.iEntityID);
					ControllerPlayer.iEntitiesCleaned ++;
					Dungeon.iNumberOfEnemies--;
					if (Dungeon.iNumberOfEnemies == 0)
						GameEvents.doAction(EventType.ACE);
					else
						GameEvents.doAction(EventType.SPREE);
				}
			}

		}


	}

	
	/**
	 * regulateFramerate:
	 * This method regulates the framerate of the game.
	 * Pre: The game must have been initialized.
	 * Post: The game's framerate has been regulated.
	 */
	private static void regulateFramerate(){
		//CODE BLOCK:
		//Framerate regulator
		lGameLoopEndTime = System.currentTimeMillis();
		lGameLoopTimeTaken = (lGameLoopEndTime - lGameLoopStartTime);
		lLastMSPFO = lGameLoopTimeTaken;

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
				//Thread.yield(); Unknown effects
				Thread.sleep(iMSPFOGmAdj - lGameLoopTimeTaken);
			} catch (InterruptedException e) {
				System.err.println("Who interrupted the main thread's slumber?");
			}
		}
		//END OF CODE BLOCK
	}

	/**
	 * moveEntity:
	 * This method moves an entity and checks the potential move for collisions.
	 * Pre: The game must have been initialized. The entity at the index must not be null.
	 * Post: The entity will have been moved accordingly.
	 */
	public static void moveEntity(int iEntityID){

		boolean bXHandled = false;
		boolean bYHandled = false;
		boolean bCollidesWithWalls = handleEntity(iEntityID).collidesWithWalls();
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
			if (dEntityXShift > 0 && !bXHandled && bCollidesWithWalls){ //Entity is moving right
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
			if (dEntityXShift < 0 && !bXHandled && bCollidesWithWalls){ //Entity is moving left
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
			if (dEntityYShift > 0 && !bYHandled && bCollidesWithWalls){ //Entity is moving down
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
			if (dEntityYShift < 0 && !bYHandled && bCollidesWithWalls){ //Entity is moving up
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


		dNewXPosCenter =  dCurrentXPos + dEntityXShift;
		dNewYPosCenter =  dCurrentYPos + dEntityYShift;
		if (handleEntity(iEntityID).collidesWithEntities()){
			for (int iuP1 = 0; iuP1 < entveCurrentEntities.size(); iuP1 ++){
				if (iuP1 != iEntityID){
					if (handleEntity(iuP1).collidesWithEntities()){
						double distance = (dNewXPosCenter - handleEntity(iuP1).getXPos()) * (dNewXPosCenter - handleEntity(iuP1).getXPos()) + (dNewYPosCenter - handleEntity(iuP1).getYPos()) * (dNewYPosCenter - handleEntity(iuP1).getYPos());
						if (distance < (dCurrentSize + handleEntity(iuP1).getSize()) * (dCurrentSize + handleEntity(iuP1).getSize())){
							dEntityXShift = 0;
							dEntityYShift = 0;
						}
					}
				}
			}
		}

		//Moves the character by shifting their X and Y coordinates.
		handleEntity(iEntityID).shiftXPos(dEntityXShift);
		handleEntity(iEntityID).shiftYPos(dEntityYShift);

	}

	
	/**
	 * intersectsCircleMapTile:
	 * This method checks whether a circle intersects a specified map tile.
	 * Pre: The map tile must exist.
	 * Post: A boolean will be returned indicating whether the shapes collide (true) or do not collide (false)
	 */
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

		//Case: Intersection if an identical circle placed on the map-tile's corners intersects the original circle. //Variable left squared to save processor speed.
		double dCornerCircleIntersectionDistance = (dCircleTileDistanceX - 0.5) * (dCircleTileDistanceX - 0.5) + (dCircleTileDistanceY - 0.5) * (dCircleTileDistanceY - 0.5);
		return (dCornerCircleIntersectionDistance <= circleRadius * circleRadius);

	}

	/**
	 * handleEntity:
	 * This method returns the Entity at the specified index
	 * Pre: The Entity at the index must exist.
	 * Post: The Entity's reference will be returned.
	 */
	public static Entity handleEntity(int entityID){
		return entveCurrentEntities.get(entityID);
	}
	
	/**
	 * handleTile:
	 * This method returns the DungeonTile at the specified indices.
	 * Pre: The DungeonTile at the indices must exist.
	 * Post: The DungeonTile's reference will be returned.
	 */
	public static DungeonTile handleTile(int x, int y){
		return dngCurrentDungeon.dtlve2DungeonTiles.get(x).get(y);
	}

	/**
	 * addEntity:
	 * This method adds an Entity to the game's Entity Vector.
	 * Pre: None.
	 * Post: An Entity will have been added to the Entity Vector.
	 */
	public static int addEntity(EntityBlueprint ebp, double xPos, double yPos, double heading, EntityController controller, EntitySkeleton skeleton, Color[] skeletonColorSet){
		return addEntity(xPos, yPos, ebp.getRadius(), heading, ebp.getAlleigance(), ebp.getSpeed(), ebp.getEntityCollision(), ebp.getWallCollision(), controller, skeleton, skeletonColorSet);
	}

	/**
	 * addEntity:
	 * This method adds an Entity to the game's Entity Vector.
	 * Pre: None.
	 * Post: An Entity will have been added to the Entity Vector. The index it has been added at will be returned.
	 */
	public static int addEntity(double xPos, double yPos, double radius, double heading, int alleigance, double speed, boolean entityCollision, boolean wallCollision, EntityController controller, EntitySkeleton skeleton, Color[] skeletonColorSet){
		for (int iuP1 = 0; iuP1 < entveCurrentEntities.size(); iuP1 ++){
			if (entveCurrentEntities.get(iuP1).isNull()){
				entveCurrentEntities.set(iuP1, new Entity(iuP1, xPos, yPos, radius, heading, alleigance, speed, entityCollision, wallCollision, controller, skeleton, skeletonColorSet));
				return iuP1;
			}
		}

		entveCurrentEntities.add(new Entity(entveCurrentEntities.size(), xPos, yPos, radius, heading, alleigance, speed, entityCollision, wallCollision, controller, skeleton, skeletonColorSet));
		return entveCurrentEntities.size() - 1;

	}

	/**
	 * addItem:
	 * This method adds an Item to the game's Entity Vector.
	 * Pre: None.
	 * Post: An Item will have been added to the Entity Vector. The index it has been added at will be returned.
	 */
	public static int addItem(Item toAdd, Entity addToEntity){

		for (int iuP1 = 0; iuP1 < addToEntity.itmaInventory.length; iuP1 ++){
			if (addToEntity.itmaInventory[iuP1] == null){
				addToEntity.itmaInventory[iuP1] = toAdd;
			}
		}

		for (int iuP1 = 0; iuP1 < entveCurrentEntities.size(); iuP1 ++){
			if (entveCurrentEntities.get(iuP1).isNull()){
				entveCurrentEntities.set(iuP1, toAdd);
				return iuP1;
			}
		}

		entveCurrentEntities.add(toAdd);
		return entveCurrentEntities.size() - 1;

	}

	/**
	 * removeEntity:
	 * This method removes an Entity to the game's Entity Vector.
	 * Pre: An entity must exist at the specified index.
	 * Post: An entity will have been set to null in the Entity Vector.
	 */
	public static void removeEntity(int entityID){
		entveCurrentEntities.set(entityID , ContentLibrary.nullEntity);
	}


	/**
	 * isWalkable:
	 * This method determines whether a tile is walkable or not.
	 * Pre: None.
	 * Post: The TileType's walkability will be returned (true if walkable, false if not walkable).
	 */
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

	/**
	 * doNonGameplayInput:
	 * This method handles the non-gameplay input of the game.
	 * Pre: None.
	 * Post: The game's zoom in/out, volume up/down requests will be handled.
	 */
	private static void doNonGameplayInput(){

		//Handle zoomin/zoomout requests.
		if (GameInput.baActions[GameActions.ZOOM_IN]){
			GameGraphics.increaseZoom();
		}
		if (GameInput.baActions[GameActions.ZOOM_OUT]){
			GameGraphics.decreaseZoom();
		}
		if (GameInput.baActions[GameActions.VOLUME_INCREASE]){
			GameSounds.incVolume();
			GameInput.baActions[GameActions.VOLUME_INCREASE] = false;
		}
		if (GameInput.baActions[GameActions.VOLUME_DECREASE]){
			GameSounds.decVolume();
			GameInput.baActions[GameActions.VOLUME_DECREASE] = false;
		}
		if (GameInput.baActions[GameActions.VIEW_DEBUG]){
			GameGraphics.bDebugView = !(GameGraphics.bDebugView);
			GameInput.baActions[GameActions.VIEW_DEBUG] = false;
		}





	}

	/**
	 * valueInBoundsX:
	 * This method returns the closest value within the x-coordinates of the map.
	 * Pre: The map must exist.
	 * Post: Returns the closest value to the parameters that is in the x-coordinates.
	 */
	public static int valueInBoundsX(int value){
		return Math.max(Math.min(dngCurrentDungeon.iDungeonXSize, value), 0);
	}
	
	/**
	 * valueInBoundsY:
	 * This method returns the closest value within the y-coordinates of the map.
	 * Pre: The map must exist.
	 * Post: Returns the closest value to the parameters that is in the y-coordinates.
	 */
	public static int valueInBoundsY(int value){
		return Math.max(Math.min(dngCurrentDungeon.iDungeonYSize, value), 0);
	}

	/**
	 * isValueInBoundsX:
	 * This method checks whether a value is within the x-coordinates of the map.
	 * Pre: The map must exist.
	 * Post: Returns true if the value is within the x-coordinates, false if not.
	 */
	public static boolean isValueInBoundsX(int value){
		return (value >= 0 && value < dngCurrentDungeon.iDungeonXSize);
	}
	/**
	 * isValueInBoundsY:
	 * This method checks whether a value is within the y-coordinates of the map.
	 * Pre: The map must exist.
	 * Post: Returns true if the value is within the y-coordinates, false if not.
	 */
	public static boolean isValueInBoundsY(int value){
		return (value >= 0 && value < dngCurrentDungeon.iDungeonYSize);
	}

	/**
	 * getCenterOfWindowX:
	 * This method returns the center of the window.
	 * Pre: The window must exist.
	 * Post: Returns the x-coordinate of the window's center.
	 */
	public static int getCenterOfWindowX(){
		return mainGameWindow.getPositionX() + (mainGameWindow.getSizeX() / 2);
	}
	
	/**
	 * getCenterOfWindowY:
	 * This method returns the center of the window.
	 * Pre: The window must exist.
	 * Post: Returns the y-coordinate of the window's center.
	 */
	public static int getCenterOfWindowY(){
		return mainGameWindow.getPositionY() + (mainGameWindow.getSizeY() / 2);
	}
	
	/**
	 * getInsetLocationX:
	 * This method returns the top left corner of the inset.
	 * Pre: The window must exist.
	 * Post: Returns the x-coordinate of the inset's point.
	 */
	public static int getInsetLocationX(){
		return mainGameWindow.getPositionX() + mainGameWindow.getInsetLeft();
	}
	
	/**
	 * getInsetLocationY:
	 * This method returns the top left corner of the inset.
	 * Pre: The window must exist.
	 * Post: Returns the y-coordinate of the inset's point.
	 */
	public static int getInsetLocationY(){
		return mainGameWindow.getPositionY() + mainGameWindow.getInsetTop();
	}

	/**
	 * getWindowSizeX:
	 * This method returns the width of the window.
	 * Pre: The window must exist.
	 * Post: Returns the width of the window.
	 */
	public static int getWindowSizeX(){
		return mainGameWindow.getSizeX();
	}
	
	/**
	 * getWindowSizeY:
	 * This method returns the height of the window.
	 * Pre: The window must exist.
	 * Post: Returns the height of the window.
	 */
	public static int getWindowSizeY(){
		return mainGameWindow.getSizeY();
	}
	
	/**
	 * getMillisecondsPerGameplayFrame:
	 * This method returns the amount of milliseconds it takes to compute the last gameplay frame.
	 * Pre: The game loop must have begun.
	 * Post: Returns the amount of milliseconds it takes to compute the last gameplay frame.
	 */
	public static int getMillisecondsPerGameplayFrame(){
		return iMSPFOGmAdj;
	}
	
	/**
	 * getCurrentFrame:
	 * This method returns the current frame.
	 * Pre: The game loop must have begun.
	 * Post: Returns the current frame.
	 */
	public static long getCurrentFrame(){
		return lCurrentFrame;
	}
	
	/**
	 * getLastMSPFO:
	 * This method returns the amount of milliseconds it takes to compute the second-last gameplay frame.
	 * Pre: The game loop must have begun.
	 * Post: Returns the amount of milliseconds it takes to compute the second-last gameplay frame.
	 */
	public static long getLastMSPFO(){
		return lLastMSPFO;
	}
	
	/**
	 * getGamePath:
	 * This method returns the path to the game's class files.
	 * Pre: None.
	 * Post: Returns the path to the game's class files.
	 */
	public static String getGamePath(){
		return strGamePath;
	}

	/**
	 * isRenderingMenu:
	 * This method returns whether the menu is currently being rendered.
	 * Pre: None.
	 * Post: Returns whether the menu is currently being rendered.
	 */
	public static boolean isRenderingMenu(){
		return bRenderMenu;
	}
	
	/**
	 * isRenderingGame:
	 * This method returns whether the game is currently being rendered.
	 * Pre: None.
	 * Post: Returns whether the game is currently being rendered.
	 */
	public static boolean isRenderingGame(){
		return bRenderGame;
	}
	
	/**
	 * renderMenu:
	 * This sets whether to render the menu or not.
	 * Pre: None.
	 * Post: The menu's 'renderability' has been set.
	 */
	public static void renderMenu(boolean renderState){
		bRenderMenu = renderState;
	}
	
	/**
	 * renderGame:
	 * This sets whether to render the game or not.
	 * Pre: None.
	 * Post: The game's 'renderability' has been set.
	 */
	public static void renderGame(boolean renderState){
		bRenderGame = renderState;
	}

}
