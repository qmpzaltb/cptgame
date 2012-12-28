package dungUserInterface;


import javax.swing.JFrame;

public class GameWindow extends Thread {
	
	public static final String NAME = "Dung";
	
	boolean runningRenderer;
	
	
	JFrame frMainWindow;
	GameGraphics grGraphicsRenderer;
	GameKeyboardInput kiKeyboardListener;
	GameMouseInput miMouseListener;
	
	public GameWindow(){
		runningRenderer = false;
		frMainWindow = new JFrame(NAME);
		frMainWindow.setSize(640 , 480);
		frMainWindow.setResizable(false);
		frMainWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		grGraphicsRenderer = new GameGraphics();
		frMainWindow.add(grGraphicsRenderer);
		
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

	public void run() {
	}
	
	
}