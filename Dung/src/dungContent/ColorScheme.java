package dungContent;

import java.awt.Color;
import dungMain.DungeonGame;

public class ColorScheme {

	private static Color[] colaVoidScheme;
	private static final int VOID_SCHEME_LENGTH = 360;
	private static final Color VOID_SCHEME_FIRST = new Color(148,0,211);
	private static final Color VOID_SCHEME_SECOND = new Color(208,32,144);
	// 255, 20, 147 peak,
	// 148, 0, 211 low.
	
	
	public static void initColorScheme(){
		colaVoidScheme = new Color[VOID_SCHEME_LENGTH];
		
		for (int sampleTime = 0; sampleTime < VOID_SCHEME_LENGTH; sampleTime ++){
			if (sampleTime < VOID_SCHEME_LENGTH / 2){
				int iNewR = (int)((VOID_SCHEME_FIRST.getRed() - VOID_SCHEME_SECOND.getRed()) * Math.cos((2 * Math.PI / (VOID_SCHEME_LENGTH * 2)) * sampleTime) + VOID_SCHEME_SECOND.getRed());
				int iNewG = (int)((VOID_SCHEME_FIRST.getGreen() - VOID_SCHEME_SECOND.getGreen()) * Math.cos((2 * Math.PI / (VOID_SCHEME_LENGTH * 2)) * sampleTime) + VOID_SCHEME_SECOND.getGreen());
				int iNewB = (int)((VOID_SCHEME_FIRST.getBlue() - VOID_SCHEME_SECOND.getBlue()) * Math.cos((2 * Math.PI / (VOID_SCHEME_LENGTH * 2)) * sampleTime) + VOID_SCHEME_SECOND.getBlue());
				System.out.println("R:" + iNewR + " G:" + iNewG + " B:" + iNewB);
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
		
		
		
		
		
		
		
	}
	
	public static void updateColorList(){
		ColorList.dynamicVoid = colaVoidScheme[(int)(DungeonGame.getCurrentFrame() % colaVoidScheme.length)];
	}
	
}
