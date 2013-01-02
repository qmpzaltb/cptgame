package dungMain;

import java.util.Random;
import java.util.Vector;

public class Dungeon {

	
	public static final int MINIMUM_DIMENSION = 20;
	public static final int MAXIMUM_DIMENSION = 100;
	
	Vector<Vector<DungeonTile>> dtlve2DungeonTiles;
	int iDungeonXSize;
	int iDungeonYSize;
	Random rngDungeon;
	
	public Dungeon(int seed){
		rngDungeon = new Random(seed);
		
		iDungeonXSize = rngDungeon.nextInt(MAXIMUM_DIMENSION - MINIMUM_DIMENSION + 1) + MINIMUM_DIMENSION;
		iDungeonYSize = rngDungeon.nextInt(MAXIMUM_DIMENSION - MINIMUM_DIMENSION + 1) + MINIMUM_DIMENSION;
		
		dtlve2DungeonTiles = new Vector<Vector<DungeonTile>>(iDungeonXSize);
		
		for (int iuP1 = 0; iuP1 < iDungeonXSize; iuP1 ++){
			dtlve2DungeonTiles.set(iuP1, new Vector<DungeonTile>(iDungeonYSize));
		}
		
		for (int iuP1 = 0; iuP1 < iDungeonXSize; iuP1 ++){
			for (int iuP2 = 0; iuP2 < iDungeonYSize; iuP2 ++){
				dtlve2DungeonTiles.get(iuP1).set(iuP2 , new DungeonTile(TileType.WALL));
			}
		}
		
		
		
	}
	
	private void makePath(int startX, int startY){
	}
	private void makeChamber(){
	}
	
}
