package dungUserInterface;


import java.awt.Insets;

import javax.swing.JFrame;

public class GameWindow extends Thread {
	
	public static final String NAME = "Dung";
	
	//boolean runningRenderer;
	
	
	JFrame frMainWindow;
	GameGraphics grGraphicsRenderer;
	GameKeyboardInput kiKeyboardListener;
	GameMouseInput miMouseListener;
	
	public GameWindow(){
		super("DungGFX");
		//runningRenderer = false;
		frMainWindow = new JFrame(NAME);
		frMainWindow.setSize(640 , 480);
		frMainWindow.setResizable(false);
		frMainWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		grGraphicsRenderer = new GameGraphics();
		frMainWindow.add(grGraphicsRenderer);
		//frMainWindow.setUndecorated(true);
		
		kiKeyboardListener = new GameKeyboardInput();
		miMouseListener = new GameMouseInput();
		
		frMainWindow.addKeyListener(kiKeyboardListener);
		frMainWindow.addMouseListener(miMouseListener);
	}
	
	public void show(){
		frMainWindow.setVisible(true);
	}
	public void hide(){
		frMainWindow.setVisible(false);
	}
	
	public int getSizeX(){
		return frMainWindow.getWidth();
	}
	public int getSizeY(){
		return frMainWindow.getHeight();
	}
	public int getPositionX(){
		return frMainWindow.getX();
	}
	public int getPositionY(){
		return frMainWindow.getY();
	}
	public int getInsetTop(){
		return frMainWindow.getInsets().top;
	}
	public int getInsetBottom(){
		return frMainWindow.getInsets().bottom;
	}
	public int getInsetLeft(){
		return frMainWindow.getInsets().left;
	}
	public int getInsetRight(){
		return frMainWindow.getInsets().right;
	}

	public void run() {
		
		long lGfxLoopStartTime;
		long lGfxLoopEndTime;
		long lGfxLoopTimeTaken;
		long lTimeToSleep;
		
		while(true){
			
			lGfxLoopStartTime = System.currentTimeMillis();
			frMainWindow.repaint();
			lGfxLoopEndTime = System.currentTimeMillis();
			lGfxLoopTimeTaken = lGfxLoopEndTime - lGfxLoopStartTime;
			lTimeToSleep = GameSettings.iMSPFOGfx - lGfxLoopTimeTaken;

			if (lTimeToSleep > 0){
				try {
					Thread.sleep(lTimeToSleep);
				} catch (InterruptedException e) {
					System.err.println("Poopsicle sticks in the GameWindow class.");
					e.printStackTrace();
				}
			}
			
			
		}
	}
	
	
}