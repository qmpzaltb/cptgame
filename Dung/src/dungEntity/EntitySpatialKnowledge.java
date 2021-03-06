package dungEntity;

import dungMain.DungeonGame;
import static dungMain.Dungeon.getSizeXFromSeed;
import static dungMain.Dungeon.getSizeYFromSeed;
import static dungMain.DungeonGame.handleEntity;
import static dungMain.DungeonGame.handleTile;
import static dungMain.DungeonGame.isValueInBoundsX;
import static dungMain.DungeonGame.isValueInBoundsY;


/**
 * SpatialKnowledge:
 * A class which defines what is in an entity's vision.
 * When applied to a player character, this represents the "fog of war" that is present in many strategy games.
 * This object is used by the "Controller" series of classes.
 */
public class EntitySpatialKnowledge {

	int iEntityID;
	int iSightRange;
	KnowledgeType[][] spatialKnowledge;
	KnowledgeType[][] tempSpatialKnowledge; //Temp spatial knowledge avoids potential "flickering" in the GameGraphics when it renders vision while the vision is being updated.

	public EntitySpatialKnowledge(int entityID, int sightRange){
		iEntityID = entityID;
		iSightRange = sightRange;

		//CODE BLOCK:
		//Initialization of the two 2D arrays.
		spatialKnowledge = new KnowledgeType[getSizeXFromSeed(DungeonGame.iCurrentMapSeed)][getSizeYFromSeed(DungeonGame.iCurrentMapSeed)];
		tempSpatialKnowledge = new KnowledgeType[spatialKnowledge.length][spatialKnowledge[0].length];
		
		for (int iuP1 = 0; iuP1 < spatialKnowledge.length; iuP1 ++){
			for (int iuP2 = 0; iuP2 < spatialKnowledge[0].length; iuP2 ++){
				spatialKnowledge[iuP1][iuP2] = KnowledgeType.NEVER_VISIBLE;
				tempSpatialKnowledge[iuP1][iuP2] = KnowledgeType.NEVER_VISIBLE;
			}
		}
		//END OF CODE BLOCK

	}


	public void updateKnowledge(){
		
		//CODE BLOCK:
		//Assumes that the entity cannot see anymore
		for (int iuP1 = 0; iuP1 < tempSpatialKnowledge.length; iuP1 ++){
			for (int iuP2 = 0; iuP2 < tempSpatialKnowledge[0].length; iuP2 ++){
				switch (spatialKnowledge[iuP1][iuP2]){
				case IS_VISIBLE:{
					tempSpatialKnowledge[iuP1][iuP2] = KnowledgeType.WAS_VISIBLE; 
					break;
				}
				case NEVER_VISIBLE:{
					break;
				}
				case WAS_VISIBLE:{
					break;
				}
				}
			}
		}
		
		//Initializes the recursion
		updateKnowledge(iSightRange , Direction.ALL_DIRECTIONS , (int)handleEntity(iEntityID).getXPos() , (int)handleEntity(iEntityID).getYPos());
		//Copies the temporary array to the actual array.
		System.arraycopy(tempSpatialKnowledge, 0, spatialKnowledge, 0, tempSpatialKnowledge.length);
		
	}

	
	private void updateKnowledge(double sightStrength, Direction sightDirection, int x, int y){
		//This recursion shoots out rays of "vision" that get weaker as they move on.
		sightStrength = adjustedSightValue(sightStrength, x , y); //For walls, that are non-sight-permeable (by default)
		if (sightStrength > 0){ //If the vision ray has "strength" left
			switch (sightDirection){
			case UP_RIGHT:{
				if (isValueInBoundsY(y - 1)){
					updateKnowledge(sightStrength - 1 , Direction.UP, x , y - 1);
				}
				if (isValueInBoundsX(x + 1)){
					updateKnowledge(sightStrength - 1 , Direction.RIGHT, x + 1, y);
				}
				if (isValueInBoundsX(x + 1) && isValueInBoundsY(y - 1) && sightStrength > 1){
					updateKnowledge(sightStrength - 1.4142, Direction.UP_RIGHT, x + 1, y - 1); //1.4142 is sqrt(2)
				}
				break;
			}
			case UP_LEFT:{
				if (isValueInBoundsY(y - 1)){
					updateKnowledge(sightStrength - 1 , Direction.UP, x , y - 1);
				}
				if (isValueInBoundsX(x - 1)){
					updateKnowledge(sightStrength - 1 , Direction.LEFT, x - 1, y);
				}
				if (isValueInBoundsX(x - 1) && isValueInBoundsY(y - 1) && sightStrength > 1){
					updateKnowledge(sightStrength - 1.4142, Direction.UP_LEFT, x - 1, y - 1);
				}
				break;
			}
			case DOWN_RIGHT:{
				if (isValueInBoundsY(y + 1)){
					updateKnowledge(sightStrength - 1 , Direction.DOWN, x , y + 1);
				}
				if (isValueInBoundsX(x + 1)){
					updateKnowledge(sightStrength - 1 , Direction.RIGHT, x + 1, y);
				}
				if (isValueInBoundsX(x + 1) && isValueInBoundsY(y + 1) && sightStrength > 1){
					updateKnowledge(sightStrength - 1.4142, Direction.DOWN_RIGHT, x + 1, y + 1);
				}
				break;

			}
			case DOWN_LEFT:{
				if (isValueInBoundsY(y + 1)){
					updateKnowledge(sightStrength - 1 , Direction.DOWN, x , y + 1);
				}
				if (isValueInBoundsX(x - 1)){
					updateKnowledge(sightStrength - 1 , Direction.LEFT, x - 1, y);
				}
				if (isValueInBoundsX(x - 1) && isValueInBoundsY(y + 1) && sightStrength > 1){
					updateKnowledge(sightStrength - 1.4142, Direction.DOWN_LEFT, x - 1, y + 1);
				}
				break;
			}
			case UP:{
				if (isValueInBoundsY(y - 1)){
					updateKnowledge(sightStrength - 1 , Direction.UP, x , y - 1);
				}
				break;
			}
			case DOWN:{
				if (isValueInBoundsY(y + 1)){
					updateKnowledge(sightStrength - 1 , Direction.DOWN, x , y + 1);
				}
				break;
			}
			case LEFT:{
				if (isValueInBoundsX(x - 1)){
					updateKnowledge(sightStrength - 1 , Direction.LEFT, x - 1, y);
				}
				break;
			}
			case RIGHT:{
				if (isValueInBoundsX(x + 1)){
					updateKnowledge(sightStrength - 1 , Direction.RIGHT, x + 1, y);
				}
				break;
			}
			case ALL_DIRECTIONS:{
				if (isValueInBoundsY(y - 1)){
					updateKnowledge(sightStrength - 1 , Direction.UP, x , y - 1);
				}
				if (isValueInBoundsX(x + 1)){
					updateKnowledge(sightStrength - 1 , Direction.RIGHT, x + 1, y);
				}
				if (isValueInBoundsX(x + 1) && isValueInBoundsY(y - 1) && sightStrength > 1){
					updateKnowledge(sightStrength - 1.4142, Direction.UP_RIGHT, x + 1, y - 1);
				}
				if (isValueInBoundsX(x - 1) && isValueInBoundsY(y - 1) && sightStrength > 1){
					updateKnowledge(sightStrength - 1.4142, Direction.UP_LEFT, x - 1, y - 1);
				}
				if (isValueInBoundsY(y + 1)){
					updateKnowledge(sightStrength - 1 , Direction.DOWN, x , y + 1);
				}
				if (isValueInBoundsX(x - 1)){
					updateKnowledge(sightStrength - 1 , Direction.LEFT, x - 1, y);
				}
				if (isValueInBoundsX(x - 1) && isValueInBoundsY(y + 1) && sightStrength > 1){
					updateKnowledge(sightStrength - 1.4142, Direction.DOWN_LEFT, x - 1, y + 1);
				}
				if (isValueInBoundsX(x + 1) && isValueInBoundsY(y + 1) && sightStrength > 1){
					updateKnowledge(sightStrength - 1.4142, Direction.DOWN_RIGHT, x + 1, y + 1);
				}
				break;
			}

			}
		}
		
		tempSpatialKnowledge[x][y] = KnowledgeType.IS_VISIBLE;

	}



	private static double adjustedSightValue(double currentSightValue, int x, int y){
		//Basic sight value mechanism. If x-ray goggles are a thing, this is where they would affect seeing through walls.
		if (DungeonGame.isWalkable(handleTile(x,y).getTileType())){
			return currentSightValue; 
		} else {
			return 0;
		}
	}

	
	public KnowledgeType getKnowledgeOfTile(int x, int y){
		return spatialKnowledge[x][y];
	}
	
	public boolean entityIsVisible(int entityID){
		if (spatialKnowledge[(int)handleEntity(entityID).getXPos()][(int)handleEntity(entityID).getYPos()] == KnowledgeType.IS_VISIBLE){
			return true;
		} 
		return false;
	}


}

enum Direction{
	UP,
	UP_RIGHT,
	RIGHT,
	DOWN_RIGHT,
	DOWN,
	DOWN_LEFT,
	LEFT,
	UP_LEFT,
	ALL_DIRECTIONS
}
