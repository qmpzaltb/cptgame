package dungMain;

import java.util.Random;
import java.util.Vector;

public class Dungeon {

	
	public static final int MINIMUM_DIMENSION = 20;
	public static final int MAXIMUM_DIMENSION = 100;
	
	int iSeed;
	Vector<Vector<DungeonTile>> dtlve2DungeonTiles;
	int iDungeonXSize;
	int iDungeonYSize;
	Random rngDungeon;
	
	public Dungeon(int seed){
		iSeed = seed;
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
		
		int iDungeonPointAmt = rngDungeon.nextInt(iDungeonXSize + iDungeonYSize / 4) + 5;
		int[] iaPointXWeb = new int[iDungeonPointAmt];
		int[] iaPointYWeb = new int[iDungeonPointAmt];
		
		for (int iuP1 = 0; iuP1 < iDungeonPointAmt; iuP1 ++){
			iaPointXWeb[iuP1] = rngDungeon.nextInt(iDungeonXSize);
			iaPointYWeb[iuP1] = rngDungeon.nextInt(iDungeonYSize);
		}
		
		
		
		
	}
	
	private void makePath(int startX, int startY, int endX, int endY){
		double dLineM = ((double)(endY - startY)) / ((double)(endX - endY)); //Slope
		double dLineB = (startY + 0.5) - (dLineM * (startX + 0.5)); //Y-Intercept
		double yValue;
		double xValue;
		
		for (xValue = (double)startX; xValue < endY + 0.5; xValue += 1.0){
			yValue = (xValue * dLineM) + dLineB;
			dtlve2DungeonTiles.get(xValue);
		}
		
	}
	
	private void makeChamber(){
	}
	
}
