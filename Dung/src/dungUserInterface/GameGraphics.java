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
	
	public GameGraphics(){
		fntGuiFont = new Font("Courier New" , Font.BOLD, 12); //I don't even know. Just make sure its a good, readable, preferrably monospaced, font.
	}
	
	public void paintComponent(Graphics g){
		Graphics2D gfx2D = (Graphics2D)(g);
		gfx2D.setFont(fntGuiFont);
		gfx2D.setBackground(Color.GRAY);
		double dEntityRelativeXShift;
		double dEntityRelativeYShift;
		double dEntityHeadingRotate;
		double dViewXShift;
		double dViewYShift;
		
		iCanvasXSize = getWidth();
		iCanvasYSize = getHeight();
		iCanvasXLoc = getX();
		iCanvasYLoc = getY();
		
		Entity playerEntity = DungeonGame.entveCurrentEntities.get(dungContent.ControllerPlayer.iPlayerEntityID);
		dViewXShift = iCanvasXSize / 2 - playerEntity.dXPos * 64;
		dViewYShift = iCanvasYSize / 2 - playerEntity.dXPos * 64;
		gfx2D.translate(dViewXShift, dViewYShift);
		
		//HERE BEGINS RENDERING OF DUNGEONS
		//HERE ENDS RENDERING OF DUNGEONS
		
		//HERE BEGINS RENDERING OF ENTITIES
		for (Entity entToRender : DungeonGame.entveCurrentEntities){

			

			dEntityRelativeXShift = entToRender.dXPos * 64;
			dEntityRelativeYShift = entToRender.dYPos * 64;
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
		gfx2D.drawString("MSPFO: " + DungeonGame.getMillisecondsPerGameplayFrame() + ", which means that FPS: " + 1000.0 / DungeonGame.getMillisecondsPerGameplayFrame() , 5, getHeight() - 35);
		
		gfx2D.setColor(Color.RED);
		gfx2D.drawString("Health: 69 $SEX ---  PROTIP: PRESS W TO ACTIVATE ANIMATION", 5, getHeight() - 20);
		gfx2D.setColor(Color.BLACK);
		gfx2D.drawString("Other shit: " + playerEntity.dHeading + " rad.", 5, getHeight() - 10);
		//HERE ENDS RENDERING OF GUITHINGS
		
		
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
