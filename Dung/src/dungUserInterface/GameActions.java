package dungUserInterface;

/**
 * GameActions:
 * A class which defines all the actions that are controllable by a player through various input devices.
 * The variables here are a combination of enumerations and array indices.
 * Variables here are intended to be used in the baActions array that is located in GameInput.
 * If a key's name is not here, then its corresponding action can not be performed, as it cannot be handled by any input device.
 *
 */
public class GameActions {
	
	//Actions that trigger when the button is down, detrigger when the button is lifted.
	//-1 is if there is no current action
	public static final int MOVE_UP 					= 0;
	public static final int MOVE_DOWN					= 1;
	public static final int MOVE_LEFT 					= 2;
	public static final int MOVE_RIGHT 					= 3;
	public static final int MOVE_FORWARD				= 4;
	public static final int MOVE_BACKWARD				= 5;
	public static final int ROTATE_CLOCKWISE 			= 6;
	public static final int ROTATE_COUNTERCLOCKWISE 	= 7;
	public static final int ATTACK_USE_PRIMARY			= 8; //Attack OR Use, depending on item equipped
	public static final int ATTACK_USE_SECONDARY		= 9; //Attack OR Use, depending on item equipped
	public static final int SPEED_MODIFIER				= 10;
	public static final int STEALTH_MODIFIER			= 11;
	public static final int POWER_MODIFIER 				= 12;
	public static final int ZOOM_IN 					= 13;
	public static final int ZOOM_OUT 					= 14;
	
	public static final int TOP_INDEX_OF_TRIGGERS 		= 14;
	
	//Actions that request to be triggered when the button is pressed, derequest themselves when the action is complete.
	public static final int OPEN_MAIN_MENU 				= 15;
	public static final int OPEN_INVENTORY				= 16;
	public static final int SWAP_TO_EQUIPMENT_1 		= 17;
	public static final int SWAP_TO_EQUIPMENT_2 		= 18;
	public static final int SWAP_TO_EQUIPMENT_3 		= 19;
	public static final int SWAP_TO_EQUIPMENT_4 		= 20;
	public static final int SWAP_TO_EQUIPMENT_5 		= 21;
	public static final int SWAP_TO_EQUIPMENT_6 		= 22;
	public static final int SWAP_TO_EQUIPMENT_7 		= 23;
	public static final int SWAP_TO_EQUIPMENT_8 		= 24;
	public static final int SWAP_TO_EQUIPMENT_9 		= 25;
	public static final int SWAP_TO_EQUIPMENT_10 		= 26;
	public static final int SWAP_TO_EQUIPMENT_PAGE_1	= 27;
	public static final int SWAP_TO_EQUIPMENT_PAGE_2	= 28;
	public static final int SWAP_TO_EQUIPMENT_PAGE_3	= 29;
	public static final int SWAP_TO_EQUIPMENT_PAGE_4	= 30;
	public static final int SWAP_TO_EQUIPMENT_PAGE_5	= 31;
	public static final int SWAP_TO_EQUIPMENT_PAGE_6	= 32;
	public static final int SWAP_TO_EQUIPMENT_PAGE_7	= 33;
	public static final int SWAP_TO_EQUIPMENT_PAGE_8	= 34;
	public static final int SWAP_TO_EQUIPMENT_PAGE_9	= 35;
	public static final int SWAP_TO_EQUIPMENT_PAGE_10	= 36;
	public static final int SWAP_TO_EQUIPMENT_PAGE_11	= 37;
	public static final int SWAP_TO_EQUIPMENT_PAGE_12	= 38;
	public static final int SWAP_TO_EQUIPMENT_PAGE_13	= 39;
	public static final int SWAP_TO_EQUIPMENT_PAGE_14	= 40;
	public static final int SWAP_TO_EQUIPMENT_PAGE_15	= 41;
	public static final int SWAP_TO_EQUIPMENT_PAGE_16	= 42;
	public static final int SWAP_TO_EQUIPMENT_PAGE_17	= 43;
	public static final int SWAP_TO_EQUIPMENT_PAGE_18	= 44;
	public static final int SWAP_TO_EQUIPMENT_PAGE_19	= 45;
	public static final int SWAP_TO_EQUIPMENT_PAGE_20	= 46;
	public static final int SWAP_TO_EQUIPMENT_PAGE_21	= 47;
	public static final int SWAP_TO_EQUIPMENT_PAGE_22	= 48;
	public static final int SWAP_TO_EQUIPMENT_PAGE_23	= 49;
	public static final int SWAP_TO_EQUIPMENT_PAGE_24	= 50;
	public static final int SCROLL_FORWARD_EQUIPMENT	= 51;
	public static final int SCROLL_BACKWARD_EQUIPMENT	= 52;
	public static final int SCROLL_EQUIP_PAGE_FORWARD 	= 53;
	public static final int SCROLL_EQUIP_PAGE_BACKWARD 	= 54;
	public static final int ATTACK_USE_PRIMARY_TRIGGER 	= 55;
	public static final int ATTACK_USE_SECONDARY_TRIGGER= 56;
	public static final int VOLUME_INCREASE				= 57;
	public static final int VOLUME_DECREASE				= 58;
	public static final int VOLUME_MUTE					= 59;
	
	public static final int TOP_INDEX_OF_REQUESTS		= 59;
	
	//Request on button release, derequest on action complete or button press.
	public static final int ATTACK_RELEASE_PRIMARY		= 60; //Special case, triggered when button is released. Uses same key as ATTACK_USE_PRIMARY. Derequest on press as well.
	public static final int ATTACK_RELEASE_SECONDARY	= 61; //Special case, triggered when button is released. Uses same key as ATTACK_USE_SECONDARY. Derequest on press as well.
	
	public static final int TOP_INDEX_OF_RELEASES		= 61;
	
	public static final int ACTIONS_VARIETY 			= 62;
}