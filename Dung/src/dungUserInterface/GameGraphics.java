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
		
		
		for (Entity entToRender : DungeonGame.entalCurrentEntities){
			
			dEntityRelativeXShift = entToRender.dXPos * 64;
			dEntityRelativeYShift = entToRender.dYPos * 64;
			dEntityHeadingRotate = entToRender.dHeading;
			System.out.println(dEntityRelativeXShift + " XSHIFT");
			System.out.println(dEntityRelativeYShift + " YSHIFT");
			
			gfx2D.translate((int)(dEntityRelativeXShift), (int)(dEntityRelativeYShift));
			gfx2D.rotate(dEntityHeadingRotate);
			gfx2D.drawString("It has nothing to do with translate.", 100, 100);
			
			entToRender.ensSkeleton.doMoveAnimation(DungeonGame.lCurrentFrame);
			
			for (SkeletonLimb lmbToRender : entToRender.ensSkeleton.sklaSkeleton){
				
				System.err.println("Trying to paint yo");
				lmbToRender.drawLimb(gfx2D);
			}
			gfx2D.rotate((-1) * dEntityHeadingRotate);
			gfx2D.translate((-1) * (int)(dEntityRelativeXShift), (-1) * (int)(dEntityRelativeYShift));
			
			gfx2D.drawString("The skeleton doesn't draw, but this shows up! What's up wit dat?", 269, 300);
			gfx2D.drawString("WHATS UP WIT DAT!?!", 325, 350);
		}
		
		
	}
	
}
