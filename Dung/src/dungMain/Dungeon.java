/*
 * Mark, Justin, Nerman
 * Justin's Part of the Program - Environment
 * Dungeon.java
 * Creates the environment for the entities to work on
 * January 1, 2013 
 * ICS 4U1
 */

//Things to remember:
//sets spawn point
//DungeonGame.handleEntity(PlayerController.iPlayerEntityID).dXPos = yourXValue;
//DungeonGame.handleEntity(PlayerController.iPlayerEntityID).dXPos = yourYValue;

//sets TileType
//dtlve2DungeonTiles.get(XCOORD).get(YCOORD).setTileType(TileType.TILETYPEHERE);

//gets TileType
//DungeonGame.dngCurrentDungeon.dtlve2DungeonTiles.get(iuP1).get(iuP2).tileType;


package dungMain;

import java.util.Random;
import java.util.Vector;

import dungContent.ControllerPlayer;

/**
 * Dungeon:
 * A class which defines a dungeon - otherwise known as the map, or a cave.
 * It is randomly generated by a seed.
 */
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

		//Defines the size of the dungeon according to the seed.
		iDungeonXSize = rngDungeon.nextInt(MAXIMUM_DIMENSION - MINIMUM_DIMENSION + 1) + MINIMUM_DIMENSION;
		iDungeonYSize = rngDungeon.nextInt(MAXIMUM_DIMENSION - MINIMUM_DIMENSION + 1) + MINIMUM_DIMENSION;


		//System.out.println(iDungeonXSize + " X");
		//System.out.println(iDungeonYSize + " Y"); //Debugging messages


		//CODE BLOCK:
		//Initializing the DungeonTiles 2D Vector
		dtlve2DungeonTiles = new Vector<Vector<DungeonTile>>(iDungeonXSize);

		//System.out.println(dtlve2DungeonTiles.capacity());
		//System.out.println(dtlve2DungeonTiles.size()); //Debugging messages

		for (int iuP1 = 0; iuP1 < iDungeonXSize; iuP1 ++){
			dtlve2DungeonTiles.add( new Vector<DungeonTile>(iDungeonYSize));
		}
		for (int iuP1 = 0; iuP1 < iDungeonXSize; iuP1 ++){
			for (int iuP2 = 0; iuP2 < iDungeonYSize; iuP2 ++){
				dtlve2DungeonTiles.get(iuP1).add(new DungeonTile(TileType.WALL));
				dtlve2DungeonTiles.get(iuP1).get(iuP2).initShape(iuP1, iuP2);
			}
		}
		//END OF CODE BLOCK


		//CODE BLOCK:
		//Creating the open spaces in the dungon
		int iDungeonPointAmt = rngDungeon.nextInt(7) + 5;
		int[] iaPointXWeb = new int[iDungeonPointAmt];
		int[] iaPointYWeb = new int[iDungeonPointAmt];

		//SUB-CODE-BLOCK:
		//Creates random points within the map.
		for (int iuP1 = 0; iuP1 < iDungeonPointAmt; iuP1 ++){
			iaPointXWeb[iuP1] = rngDungeon.nextInt(iDungeonXSize);
			System.out.println("XWEBP#"+iuP1+": " + iaPointXWeb[iuP1]);
			iaPointYWeb[iuP1] = rngDungeon.nextInt(iDungeonYSize);
			System.out.println("YWEBP#"+iuP1+": " + iaPointYWeb[iuP1]);
		}
		//END OF SUB-CODE-BLOCK

		//SUB-CODE-BLOCK:
		//Connects all of these random points to eachother using the makePath() method.
		for (int iuP1 = 0; iuP1 < iDungeonPointAmt; iuP1 ++){
			for (int iuP2 = 0; iuP2 < iDungeonPointAmt; iuP2 ++){
				System.out.println("Path " + iuP1 + "-" + iuP2);
				makePath(iaPointXWeb[iuP1] , iaPointYWeb[iuP1] , iaPointXWeb[iuP2] , iaPointYWeb[iuP2]);
			}
		}
		//END OF SUB-CODE-BLOCK


		//TEMPORARY: Sets the player to the first random point created.
		DungeonGame.handleEntity(ControllerPlayer.iPlayerEntityID).dXPos = iaPointXWeb[0] + 0.5;
		DungeonGame.handleEntity(ControllerPlayer.iPlayerEntityID).dYPos = iaPointYWeb[0] + 0.5;


		makeWallEdges(); //Creates the edge of the map
		cullLoneTiles(TileType.WALL, 4, TileType.FLOOR, true); //A dungeon-smoothing method.
		//END OF CODE BLOCKS

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


		//CODE BLOCK
		//Builds a path of FLOOR to the end point by moving tile by tile in the direction of the end point.
		//But there is a chance of going away from the end point, to create non-monotonous paths.
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
		for (int iDungX = 0; iDungX < iDungeonXSize; iDungX++) {
			dtlve2DungeonTiles.get(iDungX).get(0).setTileType(TileType.WALLEDGE);
			dtlve2DungeonTiles.get(iDungX).get(iDungeonYSize - 1).setTileType(TileType.WALLEDGE);
		}
		for (int iDungY = 0; iDungY < iDungeonYSize; iDungY++) {
			dtlve2DungeonTiles.get(0).get(iDungY).setTileType(TileType.WALLEDGE);
			dtlve2DungeonTiles.get(iDungeonXSize - 1).get(iDungY).setTileType(TileType.WALLEDGE);
		}
	}

	@SuppressWarnings("unused")
	private void makeChamber() {
		//for ()

	}
	//
	@SuppressWarnings("unused")
	private void makeRoom() {

	}
	@SuppressWarnings("unused")
	private void makeHallway() {
		//for (int iuP1 = 0; iuP1 < iDungeonPointAmt; iuP1 ++){

		//}
	}
	@SuppressWarnings("unused")
	private void makeExit() {
		if (isOneExitInstance = false) {



			isOneExitInstance = true;
		}
	}

	private void cullLoneTiles(TileType typeToCull, int minimumNeighbors , TileType typeToCullTo , boolean voidIsANeighbour){

		//Initializes the 2D temporary dungeon tile array.
		//This creates a more accurate tile culling system.
		TileType[][] tempDungeonTile = new TileType[iDungeonXSize][iDungeonYSize];
		for (int iuP1 = 0; iuP1 < iDungeonXSize; iuP1 ++){
			for (int iuP2 = 0; iuP2 < iDungeonYSize; iuP2 ++){
				tempDungeonTile[iuP1][iuP2] = dtlve2DungeonTiles.get(iuP1).get(iuP2).getTileType();
			}	
		}



		//Parse through all the tiles that exist.
		for (int iuP1 = 0; iuP1 < iDungeonXSize; iuP1 ++){
			for (int iuP2 = 0; iuP2 < iDungeonYSize; iuP2 ++){
				if (dtlve2DungeonTiles.get(iuP1).get(iuP2).getTileType() == typeToCull){

					//Eight neighbors to a tile.
					int tileNeighbours = 8;


					//If the tile's neighbor is not a tile of its type, it loses a neighbor.
					//Left neighbour check
					if (iuP1 > 0){
						if (dtlve2DungeonTiles.get(iuP1 - 1).get(iuP2).getTileType() != typeToCull){
							tileNeighbours -= 1;
						}
					} else if (!voidIsANeighbour){
						tileNeighbours -= 1;
					}

					//Right neighbour check
					if (iuP1 < iDungeonXSize){
						if (dtlve2DungeonTiles.get(iuP1 + 1).get(iuP2).getTileType() !=typeToCull){
							tileNeighbours -= 1;
						}
					} else if (!voidIsANeighbour){
						tileNeighbours -= 1;
					}

					//Up neighbour check
					if (iuP2 > 0){
						if (dtlve2DungeonTiles.get(iuP1).get(iuP2 - 1).getTileType() != typeToCull){
							tileNeighbours -= 1;
						}
					} else if (!voidIsANeighbour){
						tileNeighbours -= 1;
					}

					//Down neighbour check
					if (iuP2 < iDungeonYSize){
						if (dtlve2DungeonTiles.get(iuP1).get(iuP2 + 1).getTileType() != typeToCull){
							tileNeighbours -= 1;
						}
					} else if (!voidIsANeighbour){
						tileNeighbours -= 1;
					}

					//UpRight neighbour check
					if ( iuP1 < iDungeonXSize && iuP2 > 0){
						if (dtlve2DungeonTiles.get(iuP1 + 1).get(iuP2 - 1).getTileType() != typeToCull){
							tileNeighbours -= 1;
						}
					} else if (!voidIsANeighbour){
						tileNeighbours -= 1;
					}

					//UpLeft neighbour check
					if (iuP1 > 0 && iuP2 > 0){
						if (dtlve2DungeonTiles.get(iuP1 - 1).get(iuP2 - 1).getTileType() != typeToCull){
							tileNeighbours -= 1;
						}
					}

					//DownLeft neighbour check
					if (iuP1 > 0 && iuP2 < iDungeonYSize){
						if (dtlve2DungeonTiles.get(iuP1 - 1).get(iuP2 + 1).getTileType() != typeToCull){
							tileNeighbours -= 1;
						}
					} else if (!voidIsANeighbour){
						tileNeighbours -= 1;
					}

					//DownRight neighbour check
					if (iuP1 < iDungeonXSize && iuP2 < iDungeonYSize){
						if (dtlve2DungeonTiles.get(iuP1 + 1).get(iuP2 + 1).getTileType() != typeToCull){
							tileNeighbours -= 1;
						}
					} else if (!voidIsANeighbour){
						tileNeighbours -= 1;
					}

					//If not enough neighbours, replace the tile.
					if (tileNeighbours < minimumNeighbors){
						tempDungeonTile[iuP1][iuP2] = (typeToCullTo);
					} 

				}
			}
		}
		
		
		//Sets the map to the temporary dungeon tile 2D-array.
		for (int iuP1 = 0; iuP1 < iDungeonXSize; iuP1 ++){
			for (int iuP2 = 0; iuP2 < iDungeonYSize; iuP2 ++){
				dtlve2DungeonTiles.get(iuP1).get(iuP2).setTileType(tempDungeonTile[iuP1][iuP2]);
			}
		}
		

	}



	public int getXSize(){
		return iDungeonXSize;
	}
	public int getYSize(){
		return iDungeonYSize;
	}

	public static int getSizeXFromSeed(int seed){
		Random rngRandom = new Random(seed);
		return rngRandom.nextInt(MAXIMUM_DIMENSION - MINIMUM_DIMENSION + 1) + MINIMUM_DIMENSION; //Calculates the X Size
	}
	public static int getSizeYFromSeed(int seed){
		Random rngRandom = new Random(seed);
		rngRandom.nextInt(MAXIMUM_DIMENSION - MINIMUM_DIMENSION + 1); //Calculates the X size.
		return rngRandom.nextInt(MAXIMUM_DIMENSION - MINIMUM_DIMENSION + 1) + MINIMUM_DIMENSION; //Calculates the Y Size
	}

}
