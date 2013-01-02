/*
 * Justin Baradi
 * Dungeon.java
 * Creates the environment for the entities to work on
 * January 1, 2013 
 * ICS 4U1
 */




package dungMain;

import java.util.Random;
import java.util.Vector;

public class Dungeon {

	
	public static final int MINIMUM_DIMENSION = 20;
	public static final int MAXIMUM_DIMENSION = 100;
	
	int iSeed; //the seed will represent a different spawn point of an enormous map
	boolean isOneExitInstance = false; //check if there is already an exit point in the map in order to go to the next level
	Vector<Vector<DungeonTile>> dtlve2DungeonTiles;
	int iDungeonXSize; //the x co-ordinates of the map you are standing on
	int iDungeonYSize; //the y co-ordinates of the map you are standing on
	Random rngDungeon; //random seed for the dungeon
	
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
		
		for (int iuP1 = 0; iuP1 < iDungeonPointAmt; iuP1 ++){
			for (int iuP2 = 0; iuP2 < iDungeonPointAmt; iuP2 ++){
				makePath(iaPointXWeb[iuP1] , iaPointYWeb[iuP1] , iaPointXWeb[iuP2] , iaPointYWeb[iuP2]);
			}
		}
		
		
		
		
	}
	
	private void makePath(int startX, int startY, int endX, int endY){
		double dLineM = ((double)(endY - startY)) / ((double)(endX - endY)); //Slope
		double dLineB = (startY + 0.5) - (dLineM * (startX + 0.5)); //Y-Intercept
		double yValue;
		double xValue;
		
		for (xValue = (double)startX; xValue < endY + 0.5; xValue += 1.0){
			yValue = (xValue * dLineM) + dLineB;
			dtlve2DungeonTiles.get((int)xValue).get((int)yValue).setTileType(TileType.FLOOR);
			dtlve2DungeonTiles.get((int)xValue).get((int)yValue + 1);
		}
		
		for (yValue = (double)startY; yValue < endX + 0.5; yValue += 1.0){
			xValue = (yValue - dLineB) / (dLineM);
			dtlve2DungeonTiles.get((int)xValue).get((int)yValue).setTileType(TileType.FLOOR);
			dtlve2DungeonTiles.get((int)xValue + 1).get((int)yValue).setTileType(TileType.FLOOR);
		}
		
	}
	
	private void makeChamber() {
		for ()
		
	}
	//
	private void makeRoom() {
		iSeed 
	}
	private void makeHallway() {
	
		
	}
	private void makeExit() {
		if (isOneExitInstance = false) {
			
			
			
			isOneExitInstance = true;
		}
	}
}
