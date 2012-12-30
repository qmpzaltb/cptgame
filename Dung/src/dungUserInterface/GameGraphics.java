package dungUserInterface;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

import dungEntity.Entity;
import dungEntity.SkeletonLimb;
import dungMain.DungeonGame;

public class GameGraphics extends JPanel{
	
	private static final long serialVersionUID = 990000001;

	public void paintComponent(Graphics g){
		Graphics2D gfx2D = (Graphics2D)(g);
		gfx2D.setBackground(Color.GRAY);
		double dEntityRelativeXShift;
		double dEntityRelativeYShift;
		
		
		
		for (Entity entToRender : DungeonGame.entalCurrentEntities){
			
			dEntityRelativeXShift = entToRender.dXPos * 64;
			dEntityRelativeYShift = entToRender.dYPos * 64;
			
			gfx2D.translate((int)(dEntityRelativeXShift), (int)(dEntityRelativeYShift));
			for (SkeletonLimb lmbToRender : entToRender.ensSkeleton.sklaSkeleton){
				System.err.println("Trying to paint yo");
				lmbToRender.drawLimb(gfx2D);
			}
			gfx2D.translate((-1) * (int)(dEntityRelativeXShift), (-1) * (int)(dEntityRelativeYShift));
			
			
		}
		
		
	}
	
}
