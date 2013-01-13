package dungUserInterface;


import java.awt.Color;
import java.awt.Insets;

import javax.swing.JFrame;


/**
 * GameWindow:
 * A class that represents the window of the game.
 * The Window is the bridge between the Listeners, the Graphics, and the DungeonGame class.
 * This class deals with the properties of the window that holds the game.
 * This class is the home of the Graphics Thread (also known as the Graphics Loop).
 */
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
  frMainWindow.setSize(1344, 967);
  frMainWindow.setBackground(Color.DARK_GRAY);
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
     System.err.println("Interruption error in the GameWindow class. How?");
     e.printStackTrace();
    }
   }
   
   
  }
 }
 
 
}