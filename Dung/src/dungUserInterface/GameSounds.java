package dungUserInterface;

import java.applet.AudioClip;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.JApplet;
import javax.swing.plaf.basic.BasicInternalFrameTitlePane.MaximizeAction;

import dungContent.ColorList;
import dungEntity.KnowledgeType;
import dungMain.DungeonGame;

import sun.audio.AudioPlayer;
import sun.audio.AudioStream;

public class GameSounds {

	private static final float VOLUME_MAX = 6.0f;
	private static final float VOLUME_MIN = -60.0f;
	public static float fCurrentVolume = 0.0f;
	
	private static int iTimeBeforeNextMove = 0;
	private static boolean bNewMoveMade;
	private static String strMove;
	
	//Not sure if we need this to implement multi sounds
	//int[] maxSimultSounds = new int[15];

	public static void playSound(SoundType typeOfSound) {
		switch (typeOfSound){
			case STARTROUND:{
				wavPlayer("STARTROUND.wav"); break;
			}
			case ENDROUND:{
				int rand = (int) (Math.random() * 2);
				if (rand == 0) {
					wavPlayer("ENDROUND.wav"); break;
				} else if (rand == 1) {
					wavPlayer("ENDROUND2.wav"); break;
				}
			}
			case DIRTY:{
				int rand = (int) (Math.random() * 2);
				if (rand == 0) {
					wavPlayer("OHBOY.wav"); break;
				} else if (rand == 1) {
					wavPlayer("OHBOY2.wav"); break;
				}
			}
			case SPREE:{
				wavPlayer("SPREE.wav"); break;
			}
			case SPREE2:{
				wavPlayer("SPREE2.wav"); break;
			}
			case SPREE3:{
				wavPlayer("SPREE3.wav"); break;
			}
			case SPREE4:{
				wavPlayer("SPREE4.wav"); break;
			}
			case SPREE5:{
				wavPlayer("SPREE5.wav"); break;
			}
			case SPREE6:{
				int rand = (int) (Math.random() * 3);
				if (rand == 0) {
					wavPlayer("SANITARY.wav"); break;
				} else if (rand == 1) {
					wavPlayer("SANITARY2.wav"); break;
				} else if (rand == 2) {
					wavPlayer("SANITARY3.wav"); break;
				}
			}
			
			case ACE:{
				wavPlayer("SPOTLESS.wav"); break;
			}
			case GAMEOVER:{
				wavPlayer("GAMEOVER.wav"); break;
			}
			case MOVE:{
				if (bNewMoveMade == false && iTimeBeforeNextMove <= 0) {
					bNewMoveMade = true;
					iTimeBeforeNextMove = 900;
					int rand = (int) (Math.random() * 4);
					if (rand == 0) {
						strMove = "MOVE.wav"; break;
					} else if (rand == 1) {
						strMove = "MOVE2.wav"; break;
					} else if (rand == 2) {
						strMove = "MOVE3.wav"; break;
					} else if (rand == 3) {
						strMove = "MOVE4.wav"; break;
					}
				}
				break;
			}
			case NEWITEM:{
				wavPlayer("NEWITEM.wav"); break;
			}
			case ITEMBROOM:{
				int rand = (int) (Math.random() * 2);
				if (rand == 0) {
					wavPlayer("BROOM.wav"); break;
				} else if (rand == 1) {
					wavPlayer("BROOM2.wav"); break;
				}
			}
			case ITEM:{
				wavPlayer("DUSTER.wav"); break;
			}
			
		}
		
	}
	
	private static void wavPlayer(String file) {
		try {
			InputStream in = new FileInputStream("sounds\\" + file);
	
			// Create an AudioStream object from the input stream.
			try {
				AudioStream as = new AudioStream(in);
				AudioPlayer.player.start(as);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	
	

	private static Clip currentMusic;
	private static boolean musicPlayRequested;
	private static SoundType typeOfMusicRequested;
	
	//Used ONLY if there is no music running in the background
	public static void playIniMusic(SoundType typeOfMusic) {
		playMusicType(typeOfMusic);
		GameEvents.doAction(EventType.LEVELMUSICINC); //comment out to disable play ALL music
	}
	
	public static void requestMusicPlay(SoundType typeOfMusic){
		musicPlayRequested = true;
		typeOfMusicRequested = typeOfMusic;
	}
	
	public static void updateGameSounds() {
		if (musicPlayRequested){
			//waits for the initial music to stop
			if (currentMusic.isActive() == true) {
				if (currentMusic.getFramePosition() >= currentMusic.getFrameLength() - 1) {
					currentMusic.close();
					//then play the selected music
					playMusicType(typeOfMusicRequested);
					musicPlayRequested = false;
					GameEvents.doAction(EventType.LEVELMUSICINC); //comment out to disable play ALL music
				}
			}
		}
		
		//handles next move sound
		if (bNewMoveMade) {
			if (iTimeBeforeNextMove == 900) wavPlayer(strMove);
			if (iTimeBeforeNextMove <= 0) {
				bNewMoveMade = false;
			} else if (iTimeBeforeNextMove > 0) {
				iTimeBeforeNextMove--;
			}
		}
	}
	
	private static void playMusicType(SoundType typeOfMusic){
		switch (typeOfMusic){
		case LAV:{
			musicPlayer("lav.wav"); break;
		}
		case LAV2:{
			musicPlayer("lav2.wav"); break;
		}
		case LAV3:{
			musicPlayer("lav3.wav"); break;
		}
		case LAV4:{
			musicPlayer("lav4.wav"); break;
		}
		case LAVDEF:{
			musicPlayer("lavtowndef.wav"); break;
		}
		case PEGNERD20:{
			musicPlayer("pegboard20.wav"); break;
		}
		}
	}
	
	
	public static void musicPlayer(String file) {
		try {
			System.out.println(DungeonGame.getGamePath() + "\\music\\" + file);
			URL musicURL = new File(DungeonGame.getGamePath() + "\\music\\" + file).toURI().toURL();
	        AudioInputStream audioIn = AudioSystem.getAudioInputStream(musicURL);
	        currentMusic = AudioSystem.getClip();
	        currentMusic.open(audioIn);
			currentMusic.loop(Clip.LOOP_CONTINUOUSLY);
			//System.out.println("The consensus is that the music works.");
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	public static void incVolume() { //Increases volume for music
		FloatControl gainControl = 
			    (FloatControl) currentMusic.getControl(FloatControl.Type.MASTER_GAIN);
		
		fCurrentVolume += 1f;
		fCurrentVolume = Math.min(fCurrentVolume, VOLUME_MAX);
		gainControl.setValue(fCurrentVolume); // Adds to volume by 10 decibels.
	}
	public static void decVolume() { //Decreases volume for music
		FloatControl gainControl = 
			    (FloatControl) currentMusic.getControl(FloatControl.Type.MASTER_GAIN);
		
		fCurrentVolume -= 1f;
		fCurrentVolume = Math.max(fCurrentVolume, VOLUME_MIN);
		gainControl.setValue(fCurrentVolume); // Reduces volume by 1 decibels.
	}
	
	
	public static void stopMusic() {
		currentMusic.stop();
	}
}