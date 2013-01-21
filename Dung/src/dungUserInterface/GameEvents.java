package dungUserInterface;

import dungContent.ControllerPlayer;

public class GameEvents {

	private static int iLevelOfMusic = 0;
	public static int iFrameBeforeRemove;
	public static String strDisplay;

	public static void doAction(EventType typeOfEvent) {
		switch(typeOfEvent) {
		case ROUNDSTART:{
			iFrameBeforeRemove = 120;
			strDisplay = "THE ROUND HAS BEGUN!";
			GameSounds.playSound(SoundType.STARTROUND);
			break;
		}
		case ROUNDEND:{
			iFrameBeforeRemove = 120;
			strDisplay = "DUNGEON CLEANED!";
			GameSounds.playSound(SoundType.ENDROUND);
			break;
		}
		case MOVEMENT:{
			GameSounds.playSound(SoundType.MOVE);
			break;
		}
		case SPREE:{
			int currentSpree = ControllerPlayer.getCleanSpree();
			currentSpree ++;
			if (currentSpree == 3) {
				iFrameBeforeRemove = 120;
				strDisplay = "CLEANING SPREE";
				GameSounds.playSound(SoundType.SPREE);
			} else if (currentSpree == 4) {
				iFrameBeforeRemove = 120;
				strDisplay = "SCOURING";
				GameSounds.playSound(SoundType.SPREE2);
			} else if (currentSpree == 5) {
				iFrameBeforeRemove = 120;
				strDisplay = "UNSTAINABLE";
				GameSounds.playSound(SoundType.SPREE3);
			} else if (currentSpree == 6) {
				iFrameBeforeRemove = 120;
				strDisplay = "PURIFYING";
				GameSounds.playSound(SoundType.SPREE4);
			} else if (currentSpree == 7) {
				iFrameBeforeRemove = 120;
				strDisplay = "C-C-CLEAN-LIKE";
				GameSounds.playSound(SoundType.SPREE5);
			} else if (currentSpree >= 8) {
				iFrameBeforeRemove = 120;
				strDisplay = "SANITARY";
				GameSounds.playSound(SoundType.SPREE6);
			}
			ControllerPlayer.setCleanSpree(currentSpree);
			break;
		}
		case GAMEOVER:{
			iFrameBeforeRemove = 120;
			strDisplay = "GAME OVER";
			GameSounds.playSound(SoundType.GAMEOVER);
			break;
		}
		case NEWITEM:{
			iFrameBeforeRemove = 120;
			strDisplay = "NEW ITEM ACQUIRED";
			GameSounds.playSound(SoundType.NEWITEM);
			break;
		}
		case ACE:{
			iFrameBeforeRemove = 600;
			strDisplay = "SPOTLESS! All enemies has been cleaned! FIND THE EXIT!";
			GameSounds.playSound(SoundType.ACE);
			break;
		}
		case ITEM:{
			iFrameBeforeRemove = 120;
			strDisplay = "";
			GameSounds.playSound(SoundType.ITEM);
			break;
		}
		case SWINGITEM:{
			GameSounds.playSound(SoundType.ITEMBROOM);
			break;
		}

		case LEVELMUSICINC:{
			iLevelOfMusic++;

			if (iLevelOfMusic == 1) {
				GameSounds.playIniMusic(SoundType.LAV); break;
			} else if (iLevelOfMusic == 2) {
				GameSounds.requestMusicPlay(SoundType.LAV2); break;
			} else if (iLevelOfMusic == 3) {
				GameSounds.requestMusicPlay(SoundType.LAV3); break;
			} else if (iLevelOfMusic >= 4) {
				GameSounds.requestMusicPlay(SoundType.LAV4); break;
			}
		}

		default: {
			System.err.println("An error has occurred: no such game event.");
		}
		} 

	}
	private static void showTextEvent(String textToShow) {

	}
}
