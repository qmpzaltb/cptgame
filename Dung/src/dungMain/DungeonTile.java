package dungMain;

public class DungeonTile {
	
	private static final int DEFAULT_GAS_CAPACITY = 1000000;
	
	private TileType tileType;
	boolean bCanHaveGas;
	private int iGasCapacity;
	//private int iLiquidCapacity;
	private int[] iaGases;
	//private int[] iaLiquids;
	//private int iTemperature;

	//These are indices to the array iaGases. Sortof like enums, maybe not really, but similar.
	public static final int GAS_OXYGEN 			= 0;
	public static final int GAS_CARBON_DIOXIDE 	= 1;
	public static final int GAS_NITROGEN 		= 2;
	public static final int GAS_COUGH 			= 3;
	public static final int GAS_DEATH 			= 4;
	
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

}
