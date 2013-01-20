package dungUserInterface;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Window;
import java.awt.geom.AffineTransform;

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
 * GameGraphics:
 * A class that attaches to a JFrame as a component.
 * This class converts the information from the Gameplay thread to graphical information (shapes, text, lines, etc.)
 * This conversion is done through the "paintComponent" method.
 * The drawing of skeleton limbs is done in the Limb's individual classes.
 */

@SuppressWarnings("serial")
public class GameMainMenu extends JPanel{
	public static void main(String[] args){
		//frame.setVisible(true);
	}

	public static void graphics(Graphics g) {
		JFrame frame = new JFrame("Cleansanity");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(351, 469);
		frame.getContentPane().setBackground(Color.BLACK);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);


		String imgStr = "C:\\Users\\Nerman Nicholas\\Documents\\GitHub\\cptgame\\Dung\\images\\logo.gif";


		ImageIcon image = new ImageIcon(imgStr);
		JLabel label1 = new JLabel(" ", image, JLabel.CENTER);
		frame.getContentPane().add(label1);

		frame.validate();

		//mainGameWindow.show();  

	}

}
