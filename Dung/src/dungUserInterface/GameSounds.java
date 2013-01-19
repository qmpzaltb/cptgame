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
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.JApplet;

import dungContent.ColorList;
import dungEntity.KnowledgeType;
import dungMain.DungeonGame;

import sun.audio.AudioPlayer;
import sun.audio.AudioStream;

public class GameSounds {

	//Not sure if we need this to implement multi sounds
	//int[] maxSimultSounds = new int[15];

	public static void playSound(SoundType typeOfSound) {

		switch (typeOfSound){
			case STARTROUND:{
				wavPlayer("STARTROUND.wav"); break;
			}
			case ENDROUND:{
				int rand = (int) Math.random() * 2;
				if (rand == 0) {
					wavPlayer("ENDROUND.wav"); break;
				} else if (rand == 1) {
					wavPlayer("ENDROUND2.wav"); break;
				}
			}
			case DIRTY:{
				int rand = (int) Math.random() * 2;
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
			case ACE:{
				wavPlayer("SPOTLESS.wav"); break;
			}
			case GAMEOVER:{
				wavPlayer("GAMEOVER.wav"); break;
			}
			case MOVE:{
				int rand = (int) Math.random() * 4;
				if (rand == 0) {
					wavPlayer("MOVE.wav"); break;
				} else if (rand == 1) {
					wavPlayer("MOVE2.wav"); break;
				} else if (rand == 2) {
					wavPlayer("MOVE3.wav"); break;
				} else if (rand == 3) {
					wavPlayer("MOVE4.wav"); break;
				}
			}
			case NEWWEAPON:{
				wavPlayer("NEWWEAPON.wav"); break;
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
	
	
	
	

	private static AudioClip currentMusic;
	
	public static void musicPlayer(String file) {
		try {
			System.out.println(DungeonGame.getGamePath() + "\\music\\" + file);
			URL musicURL = new File(DungeonGame.getGamePath() + "\\music\\" + file).toURI().toURL();
			currentMusic = JApplet.newAudioClip(musicURL);
			currentMusic.loop();
			System.out.println("The consensus is that the music works.");
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	public static void stopMusic() {
		currentMusic.stop();
	}
}