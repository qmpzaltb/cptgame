package dungUserInterface;

import dungContent.ControllerPlayer;

public class GameEvents {
	
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
			
			
			GameSounds.playSound(SoundType.ENDROUND);
			break;
		}
		case MOVEMENT:{
			
			
			GameSounds.playSound(SoundType.MOVE);
			break;
		}
		case SPREE:{
			int currentSpree = ControllerPlayer.getCleanSpree();
			
			if (currentSpree == 3) {
				
				GameSounds.playSound(SoundType.SPREE);
			} else if (currentSpree == 4) {
				
				GameSounds.playSound(SoundType.SPREE2);
			} else if (currentSpree == 5) {
				
				GameSounds.playSound(SoundType.SPREE3);
			} else if (currentSpree == 6) {
				
				GameSounds.playSound(SoundType.SPREE4);
			} else if (currentSpree == 7) {
				
				GameSounds.playSound(SoundType.SPREE5);
			} else if (currentSpree >= 8) {
				
				GameSounds.playSound(SoundType.SPREE6);
			}
				
			ControllerPlayer.setCleanSpree(currentSpree);
			break;
		}
		case GAMEOVER:{
			
			
			GameSounds.playSound(SoundType.GAMEOVER);
			break;
		}
		case NEWITEM:{
			
			
			GameSounds.playSound(SoundType.NEWITEM);
			break;
		}
		case ITEM:{
			
			
			GameSounds.playSound(SoundType.ITEM);
			break;
		}
		default: {
			System.err.println("An error has occurred: no such game event as the .");
		}
		} 
			
	}
	private static void showTextEvent(String textToShow) {
		
	}
}
