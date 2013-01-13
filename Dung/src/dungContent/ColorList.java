package dungContent;

import java.awt.Color;

/**
 * ColorList:
 * A class solely for the easy and simple access of Color objects that are used in the game.
 * Most Color objects are public, static, and final.
 * The non-final objects change color dependant on the time, as done by the ColorScheme class.
 */
public class ColorList {
	
	//Player character Color objects
	public static final Color DENIM					= new Color(0  , 0  , 128);
	public static final Color WHITE_SHIRT			= new Color(255, 245, 238);
	public static final Color BROWN_SKIN			= new Color(210, 105, 30 );
	public static final Color PALE_SKIN				= new Color(255, 222, 173);
	public static final Color LEATHER				= new Color(139, 69 , 19 );
	public static final Color IVORY					= new Color(255, 255, 240);
	public static final Color GOLD					= new Color(255, 215, 0  );
	public static final Color KHAKI					= new Color(240, 230, 140);
	public static final Color SILVER				= new Color(245, 245, 245);
	
	public static final Color TRANSPARENT_DIRTY 	= new Color(159, 114, 57 , 200);
	public static final Color TRANSPARENT_GRAY 		= new Color(128, 128, 128, 128);
	
	//GUI Color objects
	public static final Color GUI_TRANSPARENT_GRAY	= new Color(128, 128, 128, 200);
	public static final Color GUI_BLACK				= new Color(255, 255, 255);
	public static final Color GUI_RED				= new Color(226, 0  , 0  );
	
	//Terrain Color objects
	public static final Color WALL					= new Color( 64,  64,  64);
	public static final Color WALL_FOG_OF_WAR 		= new Color( 32,  32,  32);
	public static final Color FLOOR					= new Color(192, 192, 192);
	public static final Color FLOOR_FOG_OF_WAR		= new Color( 96,  96,  96);
	public static final Color VOID					= new Color(148,   0, 211);
	public static final Color VOID_FOG_OF_WAR		= new Color( 74,   0, 105);
	public static final Color UNDISCOVERED			= new Color(  0,   0,   0); //Also the color of the background.
	
	public static Color dynamicVoid					= VOID;
	
	//Experimental Color objects
	public static final Color WINDOWS_95_TEAL = new Color (0,128,129);
	public static final Color WINDOWS_95_DARK_GRAY = new Color (192,192,192);
	
	
}