package dungMain;

import java.awt.geom.Rectangle2D;


/**
 * DungeonTile:
 * A class which defines a tile within a dungeon.
 * A Dungeon object holds a two-dimensional array of objects to define the map.
 * A dungeon tile hold the type of tile that the tile is (wall, floor, edge, entrance, etc...)
 * It is also where potential environmental data of the tile may be stored, if it is ever implemented.
 */
public class DungeonTile {
	
	private static final int DEFAULT_GAS_CAPACITY = 1000000;
	
	public TileType tileType;
	boolean bCanHaveGas;
	private int iGasCapacity;
	//private int iLiquidCapacity;
	private int[] iaGases;
	//private int[] iaLiquids;
	//private int iTemperature;
	private Rectangle2D rctTileShape;

	//These are indices to the array iaGases. Sortof like enums, maybe not really, but similar.
	public static final int GAS_OXYGEN 			= 0;
	public static final int GAS_CARBON_DIOXIDE 	= 1;
	public static final int GAS_NITROGEN 		= 2;
	public static final int GAS_COUGH 			= 3;
	public static final int GAS_DIRTY 			= 4;
	
	private static final int GAS_VARIETY 		= 5;

	//public static final int LIQUID_WATER		= 0;
	//public static final int LIQUID_LAVA 		= 1;
	//public static final int LIQUID_MERCURY	= 2;
	//public static final int LIQUID_SLIME		= 3;
	//public static final int LIQUID_OIL		= 4;
	
	//private static final int LIQUID_VARIETY 	= 5;

	public DungeonTile(TileType type){
		tileType = type;
		
		if (tileType == TileType.FLOOR){
			bCanHaveGas = true;
		}
		
		if (bCanHaveGas){	
			iaGases = new int[GAS_VARIETY];
			iGasCapacity = DEFAULT_GAS_CAPACITY;
		} else {
			iaGases = null;
		}

		//iaLiquids = new int[LIQUID_VARIETY];
	}

	public void setGasPercentOfCapacity(int gasID, double gasPercent){
		iaGases[gasID] = (int)(gasPercent * iGasCapacity);
	}

	public double getGasPressure(){

		int iTotalGas = 0;

		for (int iuGas: iaGases){
			iTotalGas+= iuGas;
		}

		return (((double)(iTotalGas)) / ((double)(iGasCapacity)));
	}

	public double getGasPercentComposition(int gasID){

		int iTotalGas = 0;

		for (int iuGas: iaGases){
			iTotalGas+= iuGas;
		}

		return (((double)(iaGases[gasID])) / ((double)(iTotalGas)));

	}
	
	public void setTileType(TileType type){
		tileType = type;
	}
	public TileType getTileType(){
		return tileType;
	}
	public void initShape(int x, int y){
		rctTileShape = new Rectangle2D.Float(x , y , 1 , 1);
	}
	public Rectangle2D getShape(){
		return rctTileShape;
	}

}
