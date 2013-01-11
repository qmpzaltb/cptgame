package dungContent;

import java.awt.Color;
import dungEntity.Entity;

public class ContentLibrary {
	public static final Entity humanPlayer =
			new Entity(
					-1, 0.35, 0.03,
					new ControllerPlayer(),
					new SkeletonHumanoid(),
					new Color[]{
						ColorList.DARK_BLUE_DENIM,
						ColorList.DARK_BLUE_DENIM,
						ColorList.WHITE_SHIRT,
						ColorList.WHITE_SHIRT,
						ColorList.WHITE_SHIRT,
						ColorList.BROWN_SKIN,
						ColorList.BROWN_SKIN,
						ColorList.BROWN_SKIN
						}
					);

	public static final Entity nermanCreature =
			new Entity(
					-1, 0.4, 0.02,
					new ControllerDumb(),
					new SkeletonCreature(),
					new Color[]{
						Color.YELLOW,
						Color.CYAN,
						Color.BLUE,
						Color.MAGENTA,
						Color.ORANGE,
						Color.RED,
						ColorList.WINDOWS_95_TEAL
					}
					);
	
	public static final Entity dirtyBubble =
			new Entity(
					-1, 0.45 , 0.025,
					new ControllerAI(),
					new SkeletonBubble(),
					new Color[]{
						ColorList.TRANSPARENT_DIRTY
					}
					);
	
	
	
	
	
	
	
	

	
}
