/*
 * Mark, Justin, Nerman
 * Justin's Part of the Program - Environment
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
	public Vector<Vector<DungeonTile>> dtlve2DungeonTiles;
	int iDungeonXSize; //the max range of the x co-ordinates of the map
	int iDungeonYSize; //the max range of the y co-ordinates of the map
	Random rngDungeon; //random seed for the dungeon
	
	public Dungeon(int seed){
		DungeonGame.iGameReadinessState -= 1;
		iSeed = seed;
		rngDungeon = new Random(seed);
		iDungeonXSize = rngDungeon.nextInt(MAXIMUM_DIMENSION - MINIMUM_DIMENSION + 1) + MINIMUM_DIMENSION;
		iDungeonYSize = rngDungeon.nextInt(MAXIMUM_DIMENSION - MINIMUM_DIMENSION + 1) + MINIMUM_DIMENSION;
		
		
		System.out.println(iDungeonXSize + " X");
		System.out.println(iDungeonYSize + " Y");
		
		dtlve2DungeonTiles = new Vector<Vector<DungeonTile>>(iDungeonXSize);
		
		System.out.println(dtlve2DungeonTiles.capacity());
		System.out.println(dtlve2DungeonTiles.size());
		
		for (int iuP1 = 0; iuP1 < iDungeonXSize; iuP1 ++){
			dtlve2DungeonTiles.add( new Vector<DungeonTile>(iDungeonYSize));
		}
		
		for (int iuP1 = 0; iuP1 < iDungeonXSize; iuP1 ++){
			for (int iuP2 = 0; iuP2 < iDungeonYSize; iuP2 ++){
				dtlve2DungeonTiles.get(iuP1).add(new DungeonTile(TileType.WALL));
			}
		}
		
		int iDungeonPointAmt = rngDungeon.nextInt(7) + 5;
		int[] iaPointXWeb = new int[iDungeonPointAmt];
		int[] iaPointYWeb = new int[iDungeonPointAmt];
		
		
		for (int iuP1 = 0; iuP1 < iDungeonPointAmt; iuP1 ++){
			iaPointXWeb[iuP1] = rngDungeon.nextInt(iDungeonXSize);
			System.out.println("XWEBP#"+iuP1+": " + iaPointXWeb[iuP1]);
			iaPointYWeb[iuP1] = rngDungeon.nextInt(iDungeonYSize);
			System.out.println("YWEBP#"+iuP1+": " + iaPointYWeb[iuP1]);
		}
		
		for (int iuP1 = 0; iuP1 < iDungeonPointAmt; iuP1 ++){
			for (int iuP2 = 0; iuP2 < iDungeonPointAmt; iuP2 ++){
				System.out.println("Path " + iuP1 + "-" + iuP2);
				makePath(iaPointXWeb[iuP1] , iaPointYWeb[iuP1] , iaPointXWeb[iuP2] , iaPointYWeb[iuP2]);
			}
		}
		
		
		DungeonGame.iGameReadinessState += 1;
	}
	
	private void makePath(int startX, int startY, int endX, int endY){
		
		dtlve2DungeonTiles.get(startX).get(startY).setTileType(TileType.FLOOR);
		dtlve2DungeonTiles.get(endX).get(endY).setTileType(TileType.FLOOR);
		
		
		
		int iVerticalShift = 1;
		int iHorizontalShift = 1;
		
		int iCurrentX = startX;
		int iCurrentY = startY;
		
		if (startX > endX){
			iHorizontalShift = -1;
		}
		if (startY > endY){
			iVerticalShift = -1;
		}
		
		double dRandPerc;
		
		while (iCurrentX != endX && iCurrentY != endY){
			if (iCurrentX == endX){
				iCurrentY += iVerticalShift;
			} else if (iCurrentY == endY){
				iCurrentX += iHorizontalShift;
			} else {
				dRandPerc = rngDungeon.nextDouble();
				if (dRandPerc < 0.5){
					if (dRandPerc < 0.125 && iCurrentX > 0 && iCurrentX < iDungeonXSize - 1){
						iCurrentX -= iHorizontalShift;
					} else {
						iCurrentX += iHorizontalShift;
					}
				} else {
					if (dRandPerc < 0.625 && iCurrentY > 0 && iCurrentY < iDungeonYSize - 1){
						iCurrentY -= iVerticalShift;
					} else {
						iCurrentY += iVerticalShift;
					}
					
				}
			}
			
			dtlve2DungeonTiles.get(iCurrentX).get(iCurrentY).setTileType(TileType.FLOOR);
			
			
		}
		
		
		
	}
	
	//Puts a border around the map,
	private void makeWallEdges() {
		for()
		
	}
	
	private void makeChamber() {
		//for ()
		
	}
	//
	private void makeRoom() {
		
	}
	private void makeHallway() {
		//for (int iuP1 = 0; iuP1 < iDungeonPointAmt; iuP1 ++){
			
		//}
	}
	private void makeExit() {
		if (isOneExitInstance = false) {
			
			
			
			isOneExitInstance = true;
		}
	}
	
	
	
	public int getXSize(){
		return iDungeonXSize;
	}
	public int getYSize(){
		return iDungeonYSize;
	}
	
	
}
