package dungContent;

import java.awt.Color;
import dungMain.DungeonGame;

/**
 * ColorScheme:
 * A class that controls the "animation" of colors in the ColorList class.
 * Colors are animated at game initialization and placed in an array.
 * At gametime, the appropriate colour for the current time is taken from the array 
 * and placed into the appropriate colour in ColorList.
 */
public class ColorScheme {

	private static Color[] colaVoidScheme;
	private static final int VOID_SCHEME_LENGTH = 360;
	private static final Color VOID_SCHEME_FIRST = new Color(148,0,211);
	private static final Color VOID_SCHEME_SECOND = new Color(25,25,112);
	
	private static Color[] colaEntranceScheme;
	private static final int ENTRANCE_SCHEME_LENGTH = 180;
	private static final Color ENTRANCE_SCHEME_FIRST = new Color(255,255,0);
	private static final Color ENTRANCE_SCHEME_SECOND = ColorList.FLOOR;
	
	private static Color[] colaExitScheme;
	private static final int EXIT_SCHEME_LENGTH = 45;
	private static final Color EXIT_SCHEME_FIRST = new Color(102,205,0);
	private static final Color EXIT_SCHEME_SECOND = ColorList.FLOOR;
	
	public static void initColorScheme(){
		colaVoidScheme = new Color[VOID_SCHEME_LENGTH];
		
		//A piecewise function consisting of two Cosine functions to model colour gradienting (or animation).
		for (int sampleTime = 0; sampleTime < VOID_SCHEME_LENGTH; sampleTime ++){
			if (sampleTime < VOID_SCHEME_LENGTH / 2){
				int iNewR = (int)((VOID_SCHEME_FIRST.getRed() - VOID_SCHEME_SECOND.getRed()) * Math.cos((2 * Math.PI / (VOID_SCHEME_LENGTH * 2)) * sampleTime) + VOID_SCHEME_SECOND.getRed());
				int iNewG = (int)((VOID_SCHEME_FIRST.getGreen() - VOID_SCHEME_SECOND.getGreen()) * Math.cos((2 * Math.PI / (VOID_SCHEME_LENGTH * 2)) * sampleTime) + VOID_SCHEME_SECOND.getGreen());
				int iNewB = (int)((VOID_SCHEME_FIRST.getBlue() - VOID_SCHEME_SECOND.getBlue()) * Math.cos((2 * Math.PI / (VOID_SCHEME_LENGTH * 2)) * sampleTime) + VOID_SCHEME_SECOND.getBlue());
				colaVoidScheme[sampleTime] = new Color(iNewR, iNewG, iNewB);
			} else {
				sampleTime -= VOID_SCHEME_LENGTH / 2;
				int iNewR = (int)((VOID_SCHEME_SECOND.getRed() - VOID_SCHEME_FIRST.getRed()) * Math.cos((2 * Math.PI / (VOID_SCHEME_LENGTH * 2)) * sampleTime) + VOID_SCHEME_FIRST.getRed());
				int iNewG = (int)((VOID_SCHEME_SECOND.getGreen() - VOID_SCHEME_FIRST.getGreen()) * Math.cos((2 * Math.PI / (VOID_SCHEME_LENGTH * 2)) * sampleTime) + VOID_SCHEME_FIRST.getGreen());
				int iNewB = (int)((VOID_SCHEME_SECOND.getBlue() - VOID_SCHEME_FIRST.getBlue()) * Math.cos((2 * Math.PI / (VOID_SCHEME_LENGTH * 2)) * sampleTime) + VOID_SCHEME_FIRST.getBlue());
				sampleTime += VOID_SCHEME_LENGTH / 2;
				colaVoidScheme[sampleTime] = new Color(iNewR, iNewG, iNewB);
			}
			
		}
		
		colaEntranceScheme = new Color[ENTRANCE_SCHEME_LENGTH];
		
		//A piecewise function consisting of two Cosine functions to model colour gradienting (or animation).
		for (int sampleTime = 0; sampleTime < ENTRANCE_SCHEME_LENGTH; sampleTime ++){
			
			if (sampleTime < ENTRANCE_SCHEME_LENGTH / 2){
				int iNewR = (int)((ENTRANCE_SCHEME_FIRST.getRed() - ENTRANCE_SCHEME_SECOND.getRed()) * Math.cos((2 * Math.PI / (ENTRANCE_SCHEME_LENGTH * 2)) * sampleTime) + ENTRANCE_SCHEME_SECOND.getRed());
				int iNewG = (int)((ENTRANCE_SCHEME_FIRST.getGreen() - ENTRANCE_SCHEME_SECOND.getGreen()) * Math.cos((2 * Math.PI / (ENTRANCE_SCHEME_LENGTH * 2)) * sampleTime) + ENTRANCE_SCHEME_SECOND.getGreen());
				int iNewB = (int)((ENTRANCE_SCHEME_FIRST.getBlue() - ENTRANCE_SCHEME_SECOND.getBlue()) * Math.cos((2 * Math.PI / (ENTRANCE_SCHEME_LENGTH * 2)) * sampleTime) + ENTRANCE_SCHEME_SECOND.getBlue());
				colaEntranceScheme[sampleTime] = new Color(iNewR, iNewG, iNewB);
			} else {
				sampleTime -= ENTRANCE_SCHEME_LENGTH / 2;
				int iNewR = (int)((ENTRANCE_SCHEME_SECOND.getRed() - ENTRANCE_SCHEME_FIRST.getRed()) * Math.cos((2 * Math.PI / (ENTRANCE_SCHEME_LENGTH * 2)) * sampleTime) + ENTRANCE_SCHEME_FIRST.getRed());
				int iNewG = (int)((ENTRANCE_SCHEME_SECOND.getGreen() - ENTRANCE_SCHEME_FIRST.getGreen()) * Math.cos((2 * Math.PI / (ENTRANCE_SCHEME_LENGTH * 2)) * sampleTime) + ENTRANCE_SCHEME_FIRST.getGreen());
				int iNewB = (int)((ENTRANCE_SCHEME_SECOND.getBlue() - ENTRANCE_SCHEME_FIRST.getBlue()) * Math.cos((2 * Math.PI / (ENTRANCE_SCHEME_LENGTH * 2)) * sampleTime) + ENTRANCE_SCHEME_FIRST.getBlue());
				sampleTime += ENTRANCE_SCHEME_LENGTH / 2;
				colaEntranceScheme[sampleTime] = new Color(iNewR, iNewG, iNewB);
			}
		}
		
		colaExitScheme = new Color[EXIT_SCHEME_LENGTH];
		//A piecewise function consisting of two Cosine functions to model colour gradienting (or animation).
				for (int sampleTime = 0; sampleTime < EXIT_SCHEME_LENGTH; sampleTime ++){
					if (sampleTime < EXIT_SCHEME_LENGTH / 2){
						int iNewR = (int)((EXIT_SCHEME_FIRST.getRed() - EXIT_SCHEME_SECOND.getRed()) * Math.cos((2 * Math.PI / (EXIT_SCHEME_LENGTH * 2)) * sampleTime) + EXIT_SCHEME_SECOND.getRed());
						int iNewG = (int)((EXIT_SCHEME_FIRST.getGreen() - EXIT_SCHEME_SECOND.getGreen()) * Math.cos((2 * Math.PI / (EXIT_SCHEME_LENGTH * 2)) * sampleTime) + EXIT_SCHEME_SECOND.getGreen());
						int iNewB = (int)((EXIT_SCHEME_FIRST.getBlue() - EXIT_SCHEME_SECOND.getBlue()) * Math.cos((2 * Math.PI / (EXIT_SCHEME_LENGTH * 2)) * sampleTime) + EXIT_SCHEME_SECOND.getBlue());
						colaExitScheme[sampleTime] = new Color(iNewR, iNewG, iNewB);
					} else {
						sampleTime -= EXIT_SCHEME_LENGTH / 2;
						int iNewR = (int)((EXIT_SCHEME_SECOND.getRed() - EXIT_SCHEME_FIRST.getRed()) * Math.cos((2 * Math.PI / (EXIT_SCHEME_LENGTH * 2)) * sampleTime) + EXIT_SCHEME_FIRST.getRed());
						int iNewG = (int)((EXIT_SCHEME_SECOND.getGreen() - EXIT_SCHEME_FIRST.getGreen()) * Math.cos((2 * Math.PI / (EXIT_SCHEME_LENGTH * 2)) * sampleTime) + EXIT_SCHEME_FIRST.getGreen());
						int iNewB = (int)((EXIT_SCHEME_SECOND.getBlue() - EXIT_SCHEME_FIRST.getBlue()) * Math.cos((2 * Math.PI / (EXIT_SCHEME_LENGTH * 2)) * sampleTime) + EXIT_SCHEME_FIRST.getBlue());
						sampleTime += EXIT_SCHEME_LENGTH / 2;
						colaExitScheme[sampleTime] = new Color(iNewR, iNewG, iNewB);
					}
					
				}
		
		
		
		
		
		
		
	}
	
	public static void updateColorList(){
		ColorList.dynamicVoid = colaVoidScheme[(int)(DungeonGame.getCurrentFrame() % colaVoidScheme.length)];
		ColorList.dynamicEntrance = colaEntranceScheme[(int)(DungeonGame.getCurrentFrame() % colaEntranceScheme.length)];
		ColorList.dynamicExit = colaExitScheme[(int)(DungeonGame.getCurrentFrame() % colaExitScheme.length)];
	}
	
}
