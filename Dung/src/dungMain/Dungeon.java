package dungMain;

import java.util.Random;

public class Dungeon {

	
	public static final int MINIMUM_DIMENSION = 20;
	public static final int MAXIMUM_DIMENSION = 100;
	
	DungeonTile[][] dungeonTiles;
	Random rngDungeon;
	
	public Dungeon(int seed){
		rngDungeon = new Random(seed);
		dungeonTiles = new DungeonTile[rngDungeon.nextInt(MAXIMUM_DIMENSION - MINIMUM_DIMENSION + 1) + MINIMUM_DIMENSION][rngDungeon.nextInt(MAXIMUM_DIMENSION - MINIMUM_DIMENSION + 1) + MINIMUM_DIMENSION];
		for (int iuP1 = 0; iuP1 < dungeonTiles.length; iuP1 ++){
			for (int iuP2 = 0; iuP2 < dungeonTiles[0].length; iuP2 ++){
				dungeonTiles[iuP1][iuP2] = new DungeonTile(DungeonTile.TYPE_WALL);
			}
		}
		
		
		
		
	}
	
	private void makePath(int startX, int startY){
	}
	private void makeChamber(){
	}
	
}
