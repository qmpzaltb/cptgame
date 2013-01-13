package dungEntity;

import dungMain.DungeonGame;
import dungMain.TileType;
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

	public EntitySpatialKnowledge(int entityID, int sightRange){
		iEntityID = entityID;
		iSightRange = sightRange;

		spatialKnowledge = new KnowledgeType[getSizeXFromSeed(DungeonGame.iCurrentMapSeed)][getSizeYFromSeed(DungeonGame.iCurrentMapSeed)];
		for (int iuP1 = 0; iuP1 < spatialKnowledge.length; iuP1 ++){
			for (int iuP2 = 0; iuP2 < spatialKnowledge[0].length; iuP2 ++){
				spatialKnowledge[iuP1][iuP2] = KnowledgeType.NEVER_VISIBLE;
			}
		}

	}


	public void updateKnowledge(){
		for (int iuP1 = 0; iuP1 < spatialKnowledge.length; iuP1 ++){
			for (int iuP2 = 0; iuP2 < spatialKnowledge[0].length; iuP2 ++){
				switch (spatialKnowledge[iuP1][iuP2]){
				case IS_VISIBLE:{
					spatialKnowledge[iuP1][iuP2] = KnowledgeType.WAS_JUST_VISIBLE;
					break;
				}
				case NEVER_VISIBLE:{
					break;
				}
				case WAS_JUST_VISIBLE:{
					spatialKnowledge[iuP1][iuP2] = KnowledgeType.WAS_VISIBLE;
					break;
				}
				case WAS_VISIBLE:{
					break;
				}
				}
			}
		}
		
		
		updateKnowledge(iSightRange , Direction.ALL_DIRECTIONS , (int)handleEntity(iEntityID).getXPos() , (int)handleEntity(iEntityID).getYPos());
	}

	private void updateKnowledge(double sightStrength, Direction sightDirection, int x, int y){
		sightStrength = adjustedSightValue(sightStrength, x , y);
		if (sightStrength > 0){
			switch (sightDirection){
			case UP_RIGHT:{
				if (isValueInBoundsY(y - 1)){
					updateKnowledge(sightStrength - 1 , Direction.UP, x , y - 1);
				}
				if (isValueInBoundsX(x + 1)){
					updateKnowledge(sightStrength - 1 , Direction.RIGHT, x + 1, y);
				}
				if (isValueInBoundsX(x + 1) && isValueInBoundsY(y - 1)){
					updateKnowledge(sightStrength - 1.4142, Direction.UP_RIGHT, x + 1, y - 1);
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
				if (isValueInBoundsX(x - 1) && isValueInBoundsY(y - 1)){
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
				if (isValueInBoundsX(x + 1) && isValueInBoundsY(y + 1)){
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
				if (isValueInBoundsX(x - 1) && isValueInBoundsY(y + 1)){
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
				if (isValueInBoundsX(x + 1) && isValueInBoundsY(y - 1)){
					updateKnowledge(sightStrength - 1.4142, Direction.UP_RIGHT, x + 1, y - 1);
				}
				if (isValueInBoundsX(x - 1) && isValueInBoundsY(y - 1)){
					updateKnowledge(sightStrength - 1.4142, Direction.UP_LEFT, x - 1, y - 1);
				}
				if (isValueInBoundsY(y + 1)){
					updateKnowledge(sightStrength - 1 , Direction.DOWN, x , y + 1);
				}
				if (isValueInBoundsX(x - 1)){
					updateKnowledge(sightStrength - 1 , Direction.LEFT, x - 1, y);
				}
				if (isValueInBoundsX(x - 1) && isValueInBoundsY(y + 1)){
					updateKnowledge(sightStrength - 1.4142, Direction.DOWN_LEFT, x - 1, y + 1);
				}
				if (isValueInBoundsX(x + 1) && isValueInBoundsY(y + 1)){
					updateKnowledge(sightStrength - 1.4142, Direction.DOWN_RIGHT, x + 1, y + 1);
				}
				break;
			}

			}
		}
		
		spatialKnowledge[x][y] = KnowledgeType.IS_VISIBLE;

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
