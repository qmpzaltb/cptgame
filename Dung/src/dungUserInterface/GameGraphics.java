package dungUserInterface;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.AffineTransform;

import javax.swing.JPanel;

import dungContent.ColorList;
import dungContent.ControllerPlayer;
import dungEntity.Entity;
import dungEntity.KnowledgeType;
import dungEntity.SkeletonLimb;
import dungMain.DungeonGame;


/**
 * GameGraphics:
 * A class that attaches to a JFrame as a component.
 * This class converts the information from the Gameplay thread to graphical information (shapes, text, lines, etc.)
 * This conversion is done through the "paintComponent" method.
 * The drawing of skeleton limbs is done in the Limb's individual classes.
 */

@SuppressWarnings("serial")
public class GameGraphics extends JPanel{

	//private static final long serialVersionUID = 990000001; //Required by Eclipse? Why?


	public static final double MAX_ZOOM = 1.85;
	public static final double MIN_ZOOM = 0.01;


	public static boolean bDebugView; 

	private static int iCanvasXSize;
	private static int iCanvasYSize;
	private static int iCanvasXLoc;
	private static int iCanvasYLoc;

	private FontMetrics fmGuiFontSizeFinder;
	private Font fntGuiFont;

	private FontMetrics fmDisplayFontSizeFinder;
	private Font fntDisplayFont;
	private boolean bMiniDisplay;

	private RenderingHints rhiRenderingSettings;
	private static int iAntiAliasingTileSizeAdjustment = 1;

	private long lGfxLoopStartTime;
	private long lGfxLoopEndTime;
	private long lGfxLoopActualMSPFO;

	double dEntityRelativeXShift;
	double dEntityRelativeYShift;
	double dEntityHeadingRotate;
	double dViewXShift;
	double dViewYShift;
	double dPlayerXPos;
	double dPlayerYPos;


	double duLASTXPOS;
	double duCURRENTXPOS;


	private static double dGameZoomScale = 1.0;
	private static double dNextGameZoomScale = dGameZoomScale;

	public GameGraphics(){


		fntGuiFont = new Font("Courier New" , Font.BOLD, 12); //I don't even know. Just make sure its a good, readable, preferrably monospaced, font.
		fntDisplayFont =  new Font("Lucida Sans" , Font.BOLD, 26);
		

		fmGuiFontSizeFinder = getFontMetrics(fntGuiFont);
		fmDisplayFontSizeFinder = getFontMetrics(fntDisplayFont);
		
		rhiRenderingSettings = new RenderingHints(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON); //Smoother shapes, but map becomes buggy.
		//WORKAROUND: I extended tile pixel width/height by one pixel. Still 64 wide though, if you know what I mean.
		rhiRenderingSettings.put(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON); //Smoother text
		rhiRenderingSettings.put(RenderingHints.KEY_FRACTIONALMETRICS, RenderingHints.VALUE_FRACTIONALMETRICS_OFF); //No known effect
		rhiRenderingSettings.put(RenderingHints.KEY_STROKE_CONTROL, RenderingHints.VALUE_STROKE_PURE); //No known effect
		rhiRenderingSettings.put(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_NEAREST_NEIGHBOR); //No known effect
		rhiRenderingSettings.put(RenderingHints.KEY_DITHERING, RenderingHints.VALUE_DITHER_ENABLE); //No known effect

	}

	public void renderGameGraphics(Graphics2D gfx2D){
		Entity playerEntity = DungeonGame.entveCurrentEntities.get(dungContent.ControllerPlayer.iPlayerEntityID);
		
		
		dPlayerXPos = playerEntity.dXPos;
		dPlayerYPos = playerEntity.dYPos;
		dViewXShift = (iCanvasXSize / 2) * (1/dGameZoomScale) - (dPlayerXPos * 64);
		dViewYShift = (iCanvasYSize / 2) * (1/dGameZoomScale) - (dPlayerYPos * 64) ;
		

		//CODE BLOCK:
		//Rendering of Dungeons
		gfx2D.translate(dViewXShift, dViewYShift);


		for (int iuP1 = 0; iuP1 < DungeonGame.dngCurrentDungeon.getXSize(); iuP1 ++){
			for (int iuP2 = 0; iuP2 < DungeonGame.dngCurrentDungeon.getYSize(); iuP2 ++){
				KnowledgeType tileKnowledge = ControllerPlayer.getKnowledge().getKnowledgeOfTile(iuP1, iuP2);
				//tileKnowledge = KnowledgeType.IS_VISIBLE; //Uncomment to disable fog of war
				if (tileKnowledge == KnowledgeType.NEVER_VISIBLE){
					gfx2D.setColor(ColorList.UNDISCOVERED);
					drawTile(gfx2D,iuP1,iuP2);
				} else {
					switch (DungeonGame.dngCurrentDungeon.dtlve2DungeonTiles.get(iuP1).get(iuP2).tileType){
					case WALL:{
						if (tileKnowledge == KnowledgeType.WAS_VISIBLE){
							gfx2D.setColor(ColorList.WALL_FOG_OF_WAR);
						} else{
							gfx2D.setColor(ColorList.WALL);
						}
						drawTile(gfx2D,iuP1,iuP2);
						break;
					}
					case WALLEDGE:{
						if (tileKnowledge == KnowledgeType.WAS_VISIBLE){
							gfx2D.setColor(ColorList.VOID_FOG_OF_WAR);
						} else{
							gfx2D.setColor(ColorList.dynamicVoid);
						}
						drawTile(gfx2D,iuP1,iuP2);
						break;
					}
					case FLOOR:{
						if (tileKnowledge == KnowledgeType.WAS_VISIBLE){
							gfx2D.setColor(ColorList.FLOOR_FOG_OF_WAR);
						} else{
							gfx2D.setColor(ColorList.FLOOR);
						}
						drawTile(gfx2D,iuP1,iuP2);
						break;
					}
					case ENTRANCE:{
						if (tileKnowledge == KnowledgeType.WAS_VISIBLE){
							gfx2D.setColor(ColorList.ENTRANCE_FOG_OF_WAR);
						} else{
							gfx2D.setColor(ColorList.dynamicEntrance);
						}
						drawTile(gfx2D,iuP1,iuP2);
						break;
					}
					case EXIT:{
						if (tileKnowledge == KnowledgeType.WAS_VISIBLE){
							gfx2D.setColor(ColorList.EXIT_FOG_OF_WAR);
						} else{
							gfx2D.setColor(ColorList.dynamicExit);
						}
						drawTile(gfx2D,iuP1,iuP2);
						break;
					}
					}
				}

			}

		}
		//END OF CODE BLOCK


		//CODE BLOCK:
		//Rendering of Entities



		for (int iuP1 = 0; iuP1 < DungeonGame.entveCurrentEntities.size(); iuP1 ++){
			Entity entToRender = DungeonGame.entveCurrentEntities.get(iuP1);

			/*
			if (iuP1 == 1){
				duLASTXPOS = duCURRENTXPOS;
				duCURRENTXPOS = entToRender.dXPos;
				System.out.println("DELTAXPOS: " + (duCURRENTXPOS - duLASTXPOS));
			}*/ //Information about jitter bug. Uncomment to diagnose.

			if (!DungeonGame.entveCurrentEntities.get(iuP1).isNull()){

				AffineTransform transf = gfx2D.getTransform();
				dEntityRelativeXShift = entToRender.getXPos() * 64;
				dEntityRelativeYShift = entToRender.getYPos() * 64;
				dEntityHeadingRotate = entToRender.getHeading();

				gfx2D.translate((dEntityRelativeXShift), (dEntityRelativeYShift));
				gfx2D.rotate(dEntityHeadingRotate);

				//gfx2D.rotate(Math.PI / -2);
				//gfx2D.setColor(Color.RED);
				//gfx2D.drawString("This is where I point my squirt bottle of hyper-chlorine windex ammonia solution.", 10, 4); //Debugging message (to show heading of entities)
				//gfx2D.rotate(Math.PI / 2);

				//Renders the limbs of entities
				for (SkeletonLimb lmbToRender : entToRender.ensSkeleton.sklaSkeleton){
					lmbToRender.drawLimb(gfx2D);
				}

				gfx2D.setStroke(new BasicStroke());
				//gfx2D.rotate((-1) * dEntityHeadingRotate);
				//gfx2D.translate((-1) * (dEntityRelativeXShift), (-1) * (dEntityRelativeYShift));
				gfx2D.setTransform(transf);

			}
		}

		gfx2D.translate((-1) * dViewXShift, (-1) * dViewYShift);
		//END OF CODE BLOCK



		//CODE BLOCK:
		//Rendering of the GUI
		gfx2D.scale(1 / dGameZoomScale, 1 /  dGameZoomScale); //Resets the zooming scale.


		//This is a very basic GUI. We will (WE MUST) change it.
		gfx2D.setColor(ColorList.GUI_TRANSPARENT_GRAY);
		gfx2D.fillRect(0, getHeight() - 100, 400, 105);
		gfx2D.setColor(Color.GREEN);

		if (bDebugView) {
			gfx2D.drawString("PLAYER X: " + playerEntity.dXPos , 5 , getHeight() - 90);
			gfx2D.drawString("PLAYER Y: " + playerEntity.dYPos , 5 , getHeight() - 80);
			gfx2D.drawString("MSPFO (gfx): " + (lGfxLoopActualMSPFO) + ", which means that FPS: " + 1000.0 / (lGfxLoopActualMSPFO) , 5, getHeight() - 70);
			gfx2D.drawString("MSPFO (game): " + DungeonGame.getLastMSPFO() + ", which means that FPS: " + 1000.0 / DungeonGame.getLastMSPFO() , 5, getHeight() - 60);
		}
		gfx2D.setColor(ColorList.GUI_RED);
		gfx2D.drawString("Health: " + DungeonGame.handleEntity(ControllerPlayer.iPlayerEntityID).getIntegrity(), 5, getHeight() - 40);
		gfx2D.setColor(ColorList.PALE_SKIN);
		gfx2D.drawString("Stamina: " + ControllerPlayer.getStamina(), 5, getHeight() - 30);
		gfx2D.setColor(ColorList.GOLD);
		gfx2D.drawString("Volume of Music (in dB): " + GameSounds.fCurrentVolume , 5, getHeight() - 20);

		if (bDebugView) {
			gfx2D.setColor(ColorList.GUI_BLACK);
			gfx2D.drawString("Heading : " + playerEntity.dHeading + " rad.", 5, getHeight() - 20);
			gfx2D.drawString("Scale : " + dGameZoomScale , 5, getHeight() - 10);
		}
		
		if (GameEvents.iFrameBeforeRemove > 0) {
			gfx2D.setColor(ColorList.dynamicVoid);
			gfx2D.setFont(fntDisplayFont);
			
			gfx2D.drawString(GameEvents.strDisplay, getWidth() / 2 - (fmDisplayFontSizeFinder.stringWidth(GameEvents.strDisplay) / 2) , getHeight() / 4);
			
			
			GameEvents.iFrameBeforeRemove -= 1;
			if (GameEvents.iFrameBeforeRemove <= 0) {
				GameEvents.strDisplay = "";
			}
		}

		//END OF CODE BLOCK


	}

	public void paintComponent(Graphics g){

		lGfxLoopStartTime = System.currentTimeMillis(); //FPS count begins.

		//Initializes the graphics
		dGameZoomScale = dNextGameZoomScale;
		iAntiAliasingTileSizeAdjustment = Math.max((int)Math.round(1 / dGameZoomScale) , 1);
		Graphics2D gfx2D = (Graphics2D)(g); //Graphics2D objects have more capabilities than Graphics objects
		gfx2D.setRenderingHints(rhiRenderingSettings);
		gfx2D.setFont(fntGuiFont);
		gfx2D.setBackground(ColorList.UNDISCOVERED);

		gfx2D.scale(dGameZoomScale,  dGameZoomScale);

		iCanvasXSize = getWidth();
		iCanvasYSize = getHeight();
		iCanvasXLoc = getX();
		iCanvasYLoc = getY();

		if (DungeonGame.iGameReadinessState >= 0){ //When the game is being played (i.e., not loading)
			if (DungeonGame.isRenderingGame()){
				renderGameGraphics(gfx2D);
			}
		} else {
			//Loading screen.
			gfx2D.setColor(Color.WHITE);
			gfx2D.drawString("Loading...", getWidth() / 2, getHeight() / 2);
		}

		lGfxLoopEndTime = System.currentTimeMillis(); //FPS count ends
		lGfxLoopActualMSPFO = lGfxLoopEndTime - lGfxLoopStartTime;

		gfx2D.dispose(); //Helps with Java garbage-collection issues that can cause lag.

	}
	private void drawTile(Graphics2D g, int tileX, int tileY){
		//Draws a map tile
		g.fillRect(tileX * 64, tileY * 64, 64 + iAntiAliasingTileSizeAdjustment, 64 + iAntiAliasingTileSizeAdjustment);
	}
	private void displayEventText(Graphics2D g, String text){
		
		
		
	}

	public static int getXLoc(){
		return iCanvasXLoc;
	}
	public static int getYLoc(){
		return iCanvasYLoc;
	}
	public static int getXSize(){
		return iCanvasXSize;
	}
	public static int getYSize(){
		return iCanvasYSize;
	}

	public static void increaseZoom(){
		if (dNextGameZoomScale > MIN_ZOOM){
			dNextGameZoomScale -= 0.01;
		}
	}
	public static void decreaseZoom(){
		if (dNextGameZoomScale < MAX_ZOOM){
			dNextGameZoomScale += 0.01;
		}
	}
	public static void resetZoom(){
		dNextGameZoomScale = 1;
	}

}
