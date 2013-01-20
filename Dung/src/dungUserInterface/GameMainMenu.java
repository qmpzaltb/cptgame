package dungUserInterface;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.Window;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import dungContent.ColorList;
import dungContent.ControllerPlayer;
import dungEntity.Entity;
import dungEntity.KnowledgeType;
import dungEntity.SkeletonLimb;


/**
 * GameMainMenu:
 * A class that handles the menu screen.
 */

@SuppressWarnings("serial")
public class GameMainMenu extends JPanel{

	File filImage;

	Image imgLogo;

	
	public GameMainMenu(){
		filImage = new File("\\images\\logo.gif");
		try {
			imgLogo = ImageIO.read(filImage);
			System.out.println("IMAGE READ");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	
	public void paintComponent(Graphics2D gfx2D){

		gfx2D.setColor(Color.WHITE);
		gfx2D.fillRect(10, 10, 1000, 10);
		gfx2D.drawImage(imgLogo, 20, 20, 200, 100, null);

	}

}
