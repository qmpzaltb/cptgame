package dungUserInterface;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

import dungEntity.Entity;
import dungEntity.SkeletonLimb;
import dungMain.DungeonGame;

public class GameGraphics extends JPanel{
	
	private static final long serialVersionUID = 990000001;

	private static int iCanvasXSize;
	private static int iCanvasYSize;
	private static int iCanvasXLoc;
	private static int iCanvasYLoc;
	
	private Font fntGuiFont;
	
	private long lGfxLoopStartTime;
	private long lGfxLoopEndTime;
	
	public GameGraphics(){
		fntGuiFont = new Font("Courier New" , Font.BOLD, 12); //I don't even know. Just make sure its a good, readable, preferrably monospaced, font.
	}
	
	public void paintComponent(Graphics g){
		lGfxLoopStartTime = System.currentTimeMillis();
		Graphics2D gfx2D = (Graphics2D)(g);
		gfx2D.setFont(fntGuiFont);
		gfx2D.setBackground(Color.GRAY);
		double dEntityRelativeXShift;
		double dEntityRelativeYShift;
		double dEntityHeadingRotate;
		double dViewXShift;
		double dViewYShift;
		double dPlayerXPos;
		double dPlayerYPos;
		
		iCanvasXSize = getWidth();
		iCanvasYSize = getHeight();
		iCanvasXLoc = getX();
		iCanvasYLoc = getY();
		
		
		
		if (DungeonGame.iGameReadinessState >= 0){

			Entity playerEntity = DungeonGame.entveCurrentEntities.get(dungContent.ControllerPlayer.iPlayerEntityID);
			dPlayerXPos = playerEntity.dXPos;
			dPlayerYPos = playerEntity.dYPos;
			dViewXShift = iCanvasXSize / 2 - dPlayerXPos * 64;
			dViewYShift = iCanvasYSize / 2 - dPlayerYPos * 64;


			//HERE BEGINS RENDERING OF DUNGEONS
			gfx2D.translate(dViewXShift, dViewYShift);
			gfx2D.translate((-1) * dPlayerXPos * 64, (-1) * dPlayerYPos * 64);

			for (int iuP1 = 0; iuP1 < DungeonGame.dngCurrentDungeon.getXSize(); iuP1 ++){
				for (int iuP2 = 0; iuP2 < DungeonGame.dngCurrentDungeon.getYSize(); iuP2 ++){

					switch (DungeonGame.dngCurrentDungeon.dtlve2DungeonTiles.get(iuP1).get(iuP2).tileType){

					case WALL:{
						gfx2D.setColor(Color.DARK_GRAY);
						drawTile(gfx2D,iuP1,iuP2);
						break;
					}
					case FLOOR:{
						gfx2D.setColor(Color.LIGHT_GRAY);
						drawTile(gfx2D,iuP1,iuP2);
						break;
					}
					case ENTRANCE:{

						break;
					}
					case EXIT:{

						break;
					}


					}

				}
			}

			gfx2D.translate(dPlayerXPos * 64,dPlayerYPos * 64);


			//HERE ENDS RENDERING OF DUNGEONS

			//HERE BEGINS RENDERING OF ENTITIES

			for (Entity entToRender : DungeonGame.entveCurrentEntities){

				if (entToRender.iEntityID == dungContent.ControllerPlayer.iPlayerEntityID){
					entToRender = playerEntity;
				}
				
				dEntityRelativeXShift = entToRender.getXPos() * 64;
				dEntityRelativeYShift = entToRender.getYPos() * 64;
				dEntityHeadingRotate = entToRender.dHeading;

				gfx2D.translate((int)(dEntityRelativeXShift), (int)(dEntityRelativeYShift));
				gfx2D.rotate(dEntityHeadingRotate);

				gfx2D.rotate(Math.PI / -2);
				gfx2D.drawString("8===D    I am so mature..", 10, 4);
				gfx2D.rotate(Math.PI / 2);

				for (SkeletonLimb lmbToRender : entToRender.ensSkeleton.sklaSkeleton){
					lmbToRender.drawLimb(gfx2D);
				}
				gfx2D.rotate((-1) * dEntityHeadingRotate);
				gfx2D.translate((-1) * (int)(dEntityRelativeXShift), (-1) * (int)(dEntityRelativeYShift));



			}

			gfx2D.translate((-1) * dViewXShift, (-1) * dViewYShift);
			//HERE ENDS RENDERING OF ENTITIES

			//HERE BEGINS RENDERING OF GUITHINGS

			//This is a very basic GUI. We will (WE MUST) change it.
			gfx2D.setColor(Color.MAGENTA);
			gfx2D.drawString("MSPFO (gfx): " + (lGfxLoopEndTime - lGfxLoopStartTime) + ", which means that FPS: " + 1000.0 / (lGfxLoopEndTime - lGfxLoopStartTime) , 5, getHeight() - 65);
			gfx2D.drawString("PLAYER X: " + playerEntity.dXPos , 5 , getHeight() - 55);
			gfx2D.drawString("PLAYER Y: " + playerEntity.dYPos , 5 , getHeight() - 45);
			gfx2D.drawString("MSPFO (game): " + DungeonGame.getMillisecondsPerGameplayFrame() + ", which means that FPS: " + 1000.0 / DungeonGame.getMillisecondsPerGameplayFrame() , 5, getHeight() - 35);

			gfx2D.setColor(Color.RED);
			gfx2D.drawString("Health: 69 $SEX ---  PROTIP: PRESS W TO ACTIVATE ANIMATION", 5, getHeight() - 20);
			gfx2D.setColor(Color.BLACK);
			gfx2D.drawString("Other shit: " + playerEntity.dHeading + " rad.", 5, getHeight() - 10);
			//HERE ENDS RENDERING OF GUITHINGS1111
		} else {
			
			
			gfx2D.drawString("Loading...", 20, 20);
			
		}
		lGfxLoopEndTime = System.currentTimeMillis();
	}
	private void drawTile(Graphics2D g, int tileX, int tileY){
		g.fillRect(tileX * 64, tileY * 64, 64, 64);
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
	
	
}
