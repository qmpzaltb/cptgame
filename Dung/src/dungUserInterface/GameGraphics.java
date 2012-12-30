package dungUserInterface;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

import dungEntity.Entity;
import dungEntity.EntitySkeleton;
import dungEntity.SkeletonLimb;
import dungMain.DungeonGame;

public class GameGraphics extends JPanel{
	
	private static final long serialVersionUID = 990000001;

	public void paintComponent(Graphics g){
		Graphics2D gfx2D = (Graphics2D)(g);
		gfx2D.setBackground(Color.GRAY);
		double dEntityRelativeXShift;
		double dEntityRelativeYShift;
		double dEntityHeadingRotate;
		double dViewXShift;
		double dViewYShift;
		
		for (Entity entToRender : DungeonGame.entalCurrentEntities){
			
			Entity playerEntity = DungeonGame.entalCurrentEntities.get(dungContent.ControllerPlayer.iPlayerEntityID);
			dViewXShift = getWidth() / 2 - playerEntity.dXPos * 64;
			dViewYShift = getHeight() / 2 - playerEntity.dXPos * 64;
			gfx2D.translate(dViewXShift, dViewYShift);
			System.out.println("VXSHIFT: " + dViewXShift);
			System.out.println("VYSHIFT: " + dViewYShift);
			
			//HERE BEGINS RENDERING OF DUNGEONS
			//HERE ENDS RENDERING OF DUNGEONS
			
			//HERE BEGINS RENDERING OF ENTITIES
			dEntityRelativeXShift = entToRender.dXPos * 64;
			dEntityRelativeYShift = entToRender.dYPos * 64;
			dEntityHeadingRotate = entToRender.dHeading;
			
			gfx2D.translate((int)(dEntityRelativeXShift), (int)(dEntityRelativeYShift));
			gfx2D.rotate(dEntityHeadingRotate);
			
			gfx2D.rotate(Math.PI / -2);
			gfx2D.drawString("8===D    I am so mature..", 8, 4);
			gfx2D.rotate(Math.PI / 2);
			
			for (SkeletonLimb lmbToRender : entToRender.ensSkeleton.sklaSkeleton){
				lmbToRender.drawLimb(gfx2D);
			}
			gfx2D.rotate((-1) * dEntityHeadingRotate);
			gfx2D.translate((-1) * (int)(dEntityRelativeXShift), (-1) * (int)(dEntityRelativeYShift));
			
			gfx2D.translate((-1) * dViewXShift, (-1) * dViewYShift);
			//HERE ENDS RENDERING OF ENTITIES
			
			//HERE BEGINS RENDERING OF GUITHINGS
			
			gfx2D.setColor(Color.RED);
			gfx2D.drawString("Health: 69 $SEX ---  PROTIP: PRESS W TO ACTIVATE ANIMATION", 5, getHeight() - 20);
			gfx2D.setColor(Color.BLACK);
			gfx2D.drawString("Other shit: " + playerEntity.dHeading + " rad.", 5, getHeight() - 10);
			//HERE ENDS RENDERING OF GUITHINGS
		}
		
		
	}
	
}
