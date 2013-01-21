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

import java.awt.Point;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.ArrayList;
import java.util.Random;
import java.util.Vector;

import sun.audio.*;
import dungContent.ContentLibrary;
import dungContent.ControllerAI;
import dungContent.ControllerItem;
import dungContent.ControllerPlayer;
import dungContent.SkeletonBroom;
import dungContent.SkeletonBubble;
import dungContent.SkeletonDuster;
import dungContent.SkeletonHumanoid;
import dungEntity.Item;
import dungUserInterface.EventType;
import dungUserInterface.GameEvents;
import dungUserInterface.GameSounds;

/**
 * Dungeon:
 * A class which defines a dungeon - otherwise known as the map, or a cave.
 * It is randomly generated by a seed.
 */
public class Dungeon {


	public static final int MINIMUM_DIMENSION = 20;
	public static final int MAXIMUM_DIMENSION = 100;

	int[][] dungSimpGrid;
	
	int iSeed; //the seed will represent a different spawn point of an enormous map
	boolean isOneExitInstance = false; //check if there is already an exit point in the map in order to go to the next level
	public Vector<Vector<DungeonTile>> dtlve2DungeonTiles;
	int iDungeonXSize; //the max range of the x co-ordinates of the map
	int iDungeonYSize; //the max range of the y co-ordinates of the map
	int iSpawnX;	   //the x for the spawn
	int iSpawnY;	   //the y for the spawn
	public static int iExitX;	       //the x for the exit
	public static int iExitY;	       //the y for the exit
	public static int iNumberOfEnemies;
	Random rngDungeon; //random seed for the dungeon
	
	public static int iNextDungeon = 0;

	public Dungeon(int seed){
		DungeonGame.iGameReadinessState -= 1;
		iSeed = seed;
		rngDungeon = new Random(seed);

		//Defines the size of the dungeon according to the seed.
		iDungeonXSize = rngDungeon.nextInt(MAXIMUM_DIMENSION - MINIMUM_DIMENSION + 1) + MINIMUM_DIMENSION;
		iDungeonYSize = rngDungeon.nextInt(MAXIMUM_DIMENSION - MINIMUM_DIMENSION + 1) + MINIMUM_DIMENSION;
		dungSimpGrid = new int[iDungeonXSize][iDungeonYSize];




		//Initializing the DungeonTiles 2D Vector
		dtlve2DungeonTiles = new Vector<Vector<DungeonTile>>(iDungeonXSize);


		for (int iuP1 = 0; iuP1 < iDungeonXSize; iuP1 ++){
			dtlve2DungeonTiles.add( new Vector<DungeonTile>(iDungeonYSize));
		}
		for (int iuP1 = 0; iuP1 < iDungeonXSize; iuP1 ++){
			for (int iuP2 = 0; iuP2 < iDungeonYSize; iuP2 ++){
				dtlve2DungeonTiles.get(iuP1).add(new DungeonTile(TileType.WALL));
				dtlve2DungeonTiles.get(iuP1).get(iuP2).initShape(iuP1, iuP2);
			}
		}


		//Creating the open spaces in the dungeon
		int iDungeonPointAmt = rngDungeon.nextInt(7) + 5;
		System.out.println(iDungeonPointAmt);
		int[] iaPointXWeb = new int[iDungeonPointAmt];
		int[] iaPointYWeb = new int[iDungeonPointAmt];

		//Creates random points within the map.
		for (int iuP1 = 0; iuP1 < iDungeonPointAmt; iuP1 ++){
			iaPointXWeb[iuP1] = rngDungeon.nextInt(iDungeonXSize);
			System.out.println("XWEBP#"+iuP1+": " + iaPointXWeb[iuP1]);
			iaPointYWeb[iuP1] = rngDungeon.nextInt(iDungeonYSize);
			System.out.println("YWEBP#"+iuP1+": " + iaPointYWeb[iuP1]);
		}

		//Connects all of these random points to eachother using the makePath() method.
		for (int iuP1 = 0; iuP1 < iDungeonPointAmt; iuP1 ++){
			for (int iuP2 = 0; iuP2 < iDungeonPointAmt; iuP2 ++){
				System.out.println("Path " + iuP1 + "-" + iuP2);
				makePath(iaPointXWeb[iuP1] , iaPointYWeb[iuP1] , iaPointXWeb[iuP2] , iaPointYWeb[iuP2]);
			}
		}

		makePath(iSpawnX , iSpawnY , iExitX, iExitY); //makes a path from spawn to exit
		
		//All methods required for the game at start
		setSpawn();
		makeWallEdges(); //Creates the edge of the map
		cullLoneTiles(TileType.WALL, 4, TileType.FLOOR, true); //A dungeon-smoothing method.
		dungSimpGrid = getSimpDungGrid(dungSimpGrid);
		setExit();
		spawnEnemies();

		
		GameEvents.doAction(EventType.LEVELMUSICINC);
		//--Plays ALL levels of music
		GameEvents.doAction(EventType.ROUNDSTART);
		
		DungeonGame.iGameReadinessState += 1;
		
		
		//Point[] test = FindPath(dungSimpGrid, iSpawnX, iSpawnY, iExitX, iExitY);
		
		
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


		//Builds a path of FLOOR to the end point by moving tile by tile in the direction of the end point.
		//But there is a chance of going away from the end point, to create non-monotonous paths.
		while (iCurrentX != endX && iCurrentY != endY){
			if (iCurrentX == endX){
				iCurrentY += iVerticalShift;
			} 
			if (iCurrentY == endY){
				iCurrentX += iHorizontalShift;
			} 
			if (!(iCurrentX == endX && iCurrentY == endY)) {
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
			} else {
				break;
			}
			dtlve2DungeonTiles.get(iCurrentX).get(iCurrentY).setTileType(TileType.FLOOR);
		}



	}

	
	
	//Puts a border (void) around the map
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
		
		
	}
	@SuppressWarnings("unused")
	private void makeRoom() {

		
	}
	@SuppressWarnings("unused")
	private void makeHallway() {

		
	}
	
	
	
	
	
	private void setSpawn() {
		Point pntRand = setRandomPoint(TileType.FLOOR,TileType.ENTRANCE); //Creates a random spawn point using another method
		iSpawnX = pntRand.x;
		iSpawnY = pntRand.y;
		
		//TEMPORARY: Sets the player to the first random point created.
		DungeonGame.handleEntity(ControllerPlayer.iPlayerEntityID).dXPos = iSpawnX + 0.5;
		DungeonGame.handleEntity(ControllerPlayer.iPlayerEntityID).dYPos = iSpawnY + 0.5;
		//TEMORARY CODE PLACEMENT TEMPORARY TEMPORARY LEVEL 3 TEMPORARY
	}
	
	private void spawnEnemies() {
		int iNumUntilNextSpawn = (iDungeonXSize / 3) + (iDungeonYSize / 3);
		for (int iuP1 = 0; iuP1 < iDungeonXSize; iuP1 ++){
			for (int iuP2 = 0; iuP2 < iDungeonYSize; iuP2 ++){
				if (dtlve2DungeonTiles.get(iuP1).get(iuP2).getTileType() == TileType.FLOOR) {
					iNumUntilNextSpawn--;
					if (iNumUntilNextSpawn == 0) {
						iNumUntilNextSpawn = (iDungeonXSize / 3) + (iDungeonYSize / 3);
						DungeonGame.addEntity(ContentLibrary.DIRTY_BUBBLE_BLUEPRINT, (iuP1 + 0.5), (iuP2 + 0.5), 0, new ControllerAI(), new SkeletonBubble(), ContentLibrary.DIRTY_BUBBLE_COLORS);
						iNumberOfEnemies++;
					}
				}
			}
		}
	}
	
	private void setExit() {
		
		int minDistance = Math.min(iDungeonXSize, iDungeonYSize) / 2;
		int distanceRendered;
		int randSpawnTile;
		System.out.println("Min Distance from exit point: " + minDistance);
		int iEligibleTilesForExit = 0;
		int iCurrentEligibleFloorTile = 0;

		exitAllLoops : for (int iuP1 = 0; iuP1 < iDungeonXSize; iuP1 ++){
			for (int iuP2 = 0; iuP2 < iDungeonYSize; iuP2 ++){
				if (dtlve2DungeonTiles.get(iuP1).get(iuP2).getTileType() == TileType.FLOOR) {
					
					distanceRendered = Math.abs((iSpawnX + iSpawnY) - (iuP1 + iuP2));
					
					if (distanceRendered > minDistance){
						iEligibleTilesForExit += 1;
					}
					
					
					/*randSpawnTile = (rngDungeon.nextInt(350) + 1); //gives a random chance for a exit point
					if (randSpawnTile > 0 && randSpawnTile <= 20) { //if the chance that the exit point will spawn there is between 1-20 (1 out of 5 chance)
						
						System.out.println("Distance rendered: " + distanceRendered);
						
						
						/*if (distanceRendered > minDistance) { //if the distance between the spawn and the exit is too short, generated one
							dtlve2DungeonTiles.get(iuP1).get(iuP2).setTileType(TileType.EXIT); //then spawn exit point
							isOneExitInstance = true;
							System.out.println(iuP1 + " X and " + iuP2 + " Y is the EXIT LOCATION :D");
							break exitAllLoops; //end the function
						}*/
					//} 
				}

			}
		}
		
		randSpawnTile = (rngDungeon.nextInt(iEligibleTilesForExit) + 1); //gives a random chance for a exit point
		
		
		placingExit : for (int iuP1 = 0; iuP1 < iDungeonXSize; iuP1 ++){
			for (int iuP2 = 0; iuP2 < iDungeonYSize; iuP2 ++){
				if (dtlve2DungeonTiles.get(iuP1).get(iuP2).getTileType() == TileType.FLOOR) {
					
					distanceRendered = Math.abs((iSpawnX + iSpawnY) - (iuP1 + iuP2));
					
					if (distanceRendered > minDistance){
						iCurrentEligibleFloorTile += 1;
					}
					
					if (iCurrentEligibleFloorTile == randSpawnTile){
						dtlve2DungeonTiles.get(iuP1).get(iuP2).setTileType(TileType.EXIT); //then spawn exit point
						isOneExitInstance = true;
						iExitX = iuP1;
						iExitY = iuP2;
						//System.out.println(iuP1 + " X and " + iuP2 + " Y is the EXIT LOCATION :D");
						break placingExit;
					}
				}
			}
		}
		if (isOneExitInstance = false) setRandomPoint(TileType.FLOOR , TileType.EXIT); //if there is no safe distance, render a random point of exit on the map
		isOneExitInstance = true; //set to true, just in case
	}
	
	
	
	//this function returns a simplified version of the dungeon grid (used usually for path finding method)
	private int[][] getSimpDungGrid (int[][] iaGridArray) {
	    for (int[] row : iaGridArray)
	        Arrays.fill(row, 0);
		
		Point[] allwalls = getAllTileCoords(TileType.WALL);
	    Point[] allwalledges = getAllTileCoords(TileType.WALLEDGE);
	    for (int iWallIndex = 0; iWallIndex < allwalls.length - 1; iWallIndex ++) //for every wall, set true as a visited node
	    	iaGridArray[allwalls[iWallIndex].x][allwalls[iWallIndex].y] = 1;
	    for (int iWalledgeIndex = 0; iWalledgeIndex < allwalledges.length - 1; iWalledgeIndex ++)//for every walledge, set true as a visited node
	    	iaGridArray[allwalledges[iWalledgeIndex].x][allwalledges[iWalledgeIndex].y] = 1;
	    
	    return iaGridArray;
	}
	
	//This function replaces a randomly selected tile (that you select) on the map and you can replace it with anything (such as a xp tile or similarly)
	private Point setRandomPoint(TileType tileToReplace, TileType replacingTile){
		int iAmtOfFloor = 0;
		for (int iuP1 = 0; iuP1 < iDungeonXSize; iuP1 ++){
			for (int iuP2 = 0; iuP2 < iDungeonYSize; iuP2 ++){
				if(dtlve2DungeonTiles.get(iuP1).get(iuP2).getTileType() == tileToReplace){
					iAmtOfFloor ++;
				}
			}
		}

		int iFloorOfNewTile = (rngDungeon.nextInt(iAmtOfFloor) + 1);
		iAmtOfFloor = 0;

		setTile : for (int iuP1 = 0; iuP1 < iDungeonXSize; iuP1 ++){
			for (int iuP2 = 0; iuP2 < iDungeonYSize; iuP2 ++){
				if(dtlve2DungeonTiles.get(iuP1).get(iuP2).getTileType() == tileToReplace){
					iAmtOfFloor ++;
				}
				if (iAmtOfFloor == iFloorOfNewTile){
					dtlve2DungeonTiles.get(iuP1).get(iuP2).setTileType(replacingTile);
					return new Point(iuP1, iuP2);
				}

			}
		}
		return null;
	}
	
	//This function retrieves all coordinates that are associate with the tile
	private Point[] getAllTileCoords(TileType typeOfTile) {
		ArrayList<Point> arrofcords = new ArrayList<Point>();
		for (int iuP1 = 0; iuP1 < iDungeonXSize; iuP1 ++){
			for (int iuP2 = 0; iuP2 < iDungeonYSize; iuP2 ++){
				if (dtlve2DungeonTiles.get(iuP1).get(iuP2).getTileType() == typeOfTile) arrofcords.add(new Point(iuP1, iuP2));
			}
		}
		return (arrofcords.toArray(new Point[arrofcords.size()]));
	}
	
	
	
	/*
	
	//-------------------------------------------------------
	//Algorithm for Path Finding using A* by Anthony Zhang
	//Translated and incorporated to Java by Justin Baradi
	//-------------------------------------------------------
	private Point[] FindPath (final int[][] dungGrid, int iStartX, int iStartY, int iTargetX, int iTargetY) {
		
	//start or end position is not passable
	if (dungGrid[iStartX][iStartY] == 1 || dungGrid[iTargetX][iTargetY] == 1) {
		System.out.println("OH BOY ITS NOT WORKING");
		return null; //could not find path
	}
		
    int CurrentScores[][] = new int[iStartX][iStartY]; //map of current scores
    int HeuristicScores[][] = new int[iDungeonXSize][iDungeonYSize]; //map of heuristic scores
    int TotalScores[][] = new int[iDungeonXSize][iDungeonYSize];
    TotalScores[iStartX][iStartY] = 0;

    		
    PriorityQueue<String> OpenHeap = new PriorityQueue<String>(11,
    		new Comparator<String>() {
    	public String compare(String o1, String o2) {
    		int node1x = Integer.parseInt(o1.split("|")[1].trim());
    		int node1y = Integer.parseInt(o1.split("|")[2].trim());
    		int node2x = Integer.parseInt(o2.split("|")[1].trim());
    		int node2y = Integer.parseInt(o2.split("|")[2].trim());
    		if (TotalScores[node1x][node1y] < TotalScores[node2x][node2y])
    			return node1x + "|" + node1y;
    		else
    			return node2x + "|" + node2y;
    	}
    });
    
    OpenHeap.add(iStartX + "|" + iStartY);
    
    boolean OpenMap[][] = new boolean[iDungeonXSize][iDungeonYSize]; 
    OpenMap[iStartX][iStartY] = true;
    boolean VisitedNodes[][] = new boolean[iDungeonXSize][iDungeonYSize]; //map of visited nodes

    Point Parents[][] = new Point[iDungeonXSize][iDungeonYSize]; //mapping of nodes to their parents
    	

    
    while (OpenHeap.size() > 0) //loop while there are entries in the open list
    {
        int iMaxIndex = OpenHeap.size();
        
        //select the node having the lowest total score
        String Node = OpenHeap.poll();
        int NodeX = Integer.parseInt(Node.split("|")[1].trim());
        int NodeY = Integer.parseInt(Node.split("|")[2].trim()); //obtain the minimum value in the heap
        int iIndex = 1;
        int iChildIndex = 2;
        
        OpenMap[NodeX][NodeY] = true; //remove the entry from the open map

        //check if the node is the goal
        if (NodeX == iTargetX && NodeY == iTargetY)
        {
        	Point[] Path = new Point[iDungeonXSize*iDungeonYSize];
        	
            //for (int i = 0; i < (iDungeonXSize*iDungeonYSize); i++)
            //{
            //    Path[i] = new Point(NodeX, NodeY);
            //    break;
            //    
            //    Parents[][] = new Point(iNodeX, iNodeY);
            //    Node = Parents[NodeX][NodeY];
            //    
            //    if (!IsObject(Node))
            //        break;
            //    NodeX = Node.X;
            //    NodeY = Node.Y;
            //}
            
        	for (Point testPoint : Path) {
    			System.out.println(testPoint.x + ", " + testPoint.y);
    		}
            return Path;
        }

        VisitedNodes[NodeX][NodeY] = true;

        if (NodeX > 1)
            ScoreNode(iTargetX,iTargetY,NodeX,NodeY,dungGrid,NodeX - 1,NodeY,OpenHeap,OpenMap,VisitedNodes,CurrentScores,HeuristicScores,TotalScores,Parents);
        if (NodeX < iDungeonXSize)
            ScoreNode(iTargetX,iTargetY,NodeX,NodeY,dungGrid,NodeX + 1,NodeY,OpenHeap,OpenMap,VisitedNodes,CurrentScores,HeuristicScores,TotalScores,Parents);
        if (NodeY > 1)
            ScoreNode(iTargetX,iTargetY,NodeX,NodeY,dungGrid,NodeX,NodeY - 1,OpenHeap,OpenMap,VisitedNodes,CurrentScores,HeuristicScores,TotalScores,Parents);
        if (NodeY < iDungeonYSize)
            ScoreNode(iTargetX,iTargetY,NodeX,NodeY,dungGrid,NodeX,NodeY + 1,OpenHeap,OpenMap,VisitedNodes,CurrentScores,HeuristicScores,TotalScores,Parents);
    }
    
    return null; //could not find a path
	}
	
	
	
	private void ScoreNode(int iTargetX, int iTargetY, int iNodeX, int iNodeY, int[][] dungGrid, int iNextNodeX, int iNextNodeY, PriorityQueue<String> OpenHeap, boolean[][] OpenMap, boolean[][] VisitedNodes, int[][] CurrentScores, int[][] HeuristicScores, int[][] TotalScores, Point[][] Parents)
	{
	    if (dungGrid[iNextNodeX][iNextNodeY] == 1 || VisitedNodes[iNextNodeX][iNextNodeY] == true) //next node is a wall or is in the closed list
	        return;
	        
	    int iBestCurrentScore = CurrentScores[iNodeX][iNodeY] + 1; //add the distance between the current node and the next to the current distance

	    if (!(OpenMap[iNextNodeX][iNextNodeY]))
	    {
	        HeuristicScores[iNextNodeX][iNextNodeY] = Math.abs(iTargetX - iNextNodeX) + Math.abs(iTargetY - iNextNodeY) ; //diagonal distance: Max(Abs(EndX - NextNodeX),Abs(EndY - NextNodeY))

	        CurrentScores[iNextNodeX][iNextNodeY] = iBestCurrentScore;
	        TotalScores[iNextNodeX][iNextNodeY] = iBestCurrentScore + HeuristicScores[iNextNodeX][iNextNodeY];
	        Parents[iNextNodeX][iNextNodeY] = new Point(iNodeX,iNodeY);

	        //append the value to the end of the heap array
	        OpenHeap.add(iNextNodeX + "|" + iNextNodeY);
	        
	        
	    }
	    else if (iBestCurrentScore >= CurrentScores[iNextNodeX][iNextNodeY])
	    {
	        CurrentScores[iNextNodeX][iNextNodeY] = iBestCurrentScore;
	        TotalScores[iNextNodeX][iNextNodeY] = iBestCurrentScore + HeuristicScores[iNextNodeX][iNextNodeY];
	        Parents[iNextNodeX][iNextNodeY] = new Point(iNodeX, iNodeY);
	    }
	}
	
	
	*/
	
	
	
	
	
	
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
