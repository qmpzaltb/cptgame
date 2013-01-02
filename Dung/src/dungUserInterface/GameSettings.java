package dungUserInterface;

import java.awt.event.KeyEvent;

public class GameSettings {
	
	public static int iMSPFOGfx = 16; //Milliseconds Per Frame Operation for Graphics
	public static int iMSPFOGm = 16; //Milliseconds Per Frame Operation for Gameplay
	
	public static int iFPSRegulationPeriod = 100; // Every "this" amount of frames, regulate the framerate accordingly.

	public static int[] iaActionOfButton;
	// From 0 - 255, reserved for indices of VK keys.
	// 260 - Left Click (Mouse Button 1)
	// 261 - Middle Mouse Button Click (Mouse Button 2)
	// 262 - Right Click (Mouse Button 3)
	// 263 - Scroll Up
	// 264 - Scroll Down
	// 265 - Mouse Button 4
	// 266 - Mouse Button 5
	// 267 - Mouse Button 6
	
	public static void initGameSettings(){
		iaActionOfButton = new int[300];
		for (int iuP1 = 0; iuP1 < iaActionOfButton.length; iuP1 ++){
			iaActionOfButton[iuP1] = -1;
		}
	}
	
	public static void setDefaultKeyBindings(){
		iaActionOfButton[KeyEvent.VK_W] 		= GameActions.MOVE_UP;
		iaActionOfButton[KeyEvent.VK_A] 		= GameActions.MOVE_LEFT;
		iaActionOfButton[KeyEvent.VK_S] 		= GameActions.MOVE_DOWN;
		iaActionOfButton[KeyEvent.VK_D] 		= GameActions.MOVE_RIGHT;
		iaActionOfButton[KeyEvent.VK_UP] 		= GameActions.MOVE_FORWARD;
		iaActionOfButton[KeyEvent.VK_DOWN] 		= GameActions.MOVE_BACKWARD;
		iaActionOfButton[KeyEvent.VK_LEFT] 		= GameActions.ROTATE_COUNTERCLOCKWISE;
		iaActionOfButton[KeyEvent.VK_RIGHT] 	= GameActions.ROTATE_CLOCKWISE;
		iaActionOfButton[KeyEvent.VK_SHIFT] 	= GameActions.SPEED_MODIFIER;
		iaActionOfButton[KeyEvent.VK_CONTROL] 	= GameActions.STEALTH_MODIFIER;
		iaActionOfButton[KeyEvent.VK_ALT] 		= GameActions.POWER_MODIFIER;
		iaActionOfButton[KeyEvent.VK_ESCAPE] 	= GameActions.OPEN_MAIN_MENU;
		iaActionOfButton[KeyEvent.VK_F] 		= GameActions.OPEN_INVENTORY;
		iaActionOfButton[KeyEvent.VK_1] 		= GameActions.SWAP_TO_EQUIPMENT_1;
		iaActionOfButton[KeyEvent.VK_2] 		= GameActions.SWAP_TO_EQUIPMENT_2;
		iaActionOfButton[KeyEvent.VK_3] 		= GameActions.SWAP_TO_EQUIPMENT_3;
		iaActionOfButton[KeyEvent.VK_4] 		= GameActions.SWAP_TO_EQUIPMENT_4;
		iaActionOfButton[KeyEvent.VK_5] 		= GameActions.SWAP_TO_EQUIPMENT_5;
		iaActionOfButton[KeyEvent.VK_6] 		= GameActions.SWAP_TO_EQUIPMENT_6;
		iaActionOfButton[KeyEvent.VK_7] 		= GameActions.SWAP_TO_EQUIPMENT_7;
		iaActionOfButton[KeyEvent.VK_8] 		= GameActions.SWAP_TO_EQUIPMENT_8;
		iaActionOfButton[KeyEvent.VK_9] 		= GameActions.SWAP_TO_EQUIPMENT_9;
		iaActionOfButton[KeyEvent.VK_0] 		= GameActions.SWAP_TO_EQUIPMENT_10;
		iaActionOfButton[KeyEvent.VK_F1] 		= GameActions.SWAP_TO_EQUIPMENT_PAGE_1;
		iaActionOfButton[KeyEvent.VK_F2] 		= GameActions.SWAP_TO_EQUIPMENT_PAGE_2;
		iaActionOfButton[KeyEvent.VK_F3] 		= GameActions.SWAP_TO_EQUIPMENT_PAGE_3;
		iaActionOfButton[KeyEvent.VK_F4] 		= GameActions.SWAP_TO_EQUIPMENT_PAGE_4;
		iaActionOfButton[KeyEvent.VK_F5] 		= GameActions.SWAP_TO_EQUIPMENT_PAGE_5;
		iaActionOfButton[KeyEvent.VK_F6] 		= GameActions.SWAP_TO_EQUIPMENT_PAGE_6;
		iaActionOfButton[KeyEvent.VK_F7] 		= GameActions.SWAP_TO_EQUIPMENT_PAGE_7;
		iaActionOfButton[KeyEvent.VK_F8] 		= GameActions.SWAP_TO_EQUIPMENT_PAGE_8;
		iaActionOfButton[KeyEvent.VK_F9] 		= GameActions.SWAP_TO_EQUIPMENT_PAGE_9;
		iaActionOfButton[KeyEvent.VK_F10] 		= GameActions.SWAP_TO_EQUIPMENT_PAGE_10;
		iaActionOfButton[KeyEvent.VK_F11] 		= GameActions.SWAP_TO_EQUIPMENT_PAGE_11;
		iaActionOfButton[KeyEvent.VK_F12] 		= GameActions.SWAP_TO_EQUIPMENT_PAGE_12;
		iaActionOfButton[260] 					= GameActions.ATTACK_USE_PRIMARY;
		iaActionOfButton[262] 					= GameActions.ATTACK_USE_SECONDARY;
		iaActionOfButton[KeyEvent.VK_E]			= GameActions.SCROLL_FORWARD_EQUIPMENT;
		iaActionOfButton[KeyEvent.VK_Q]			= GameActions.SCROLL_BACKWARD_EQUIPMENT;
		iaActionOfButton[KeyEvent.VK_T]			= GameActions.SCROLL_EQUIP_PAGE_FORWARD;
		iaActionOfButton[KeyEvent.VK_R]			= GameActions.SCROLL_EQUIP_PAGE_BACKWARD;
	}
	
	
}
