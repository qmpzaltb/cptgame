package dungUserInterface;

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
	
	public static final int TOP_INDEX_OF_TRIGGERS 		= 12;
	
	//Actions that request to be triggered when the button is pressed, derequest themselves when the action is complete.
	public static final int OPEN_MAIN_MENU 				= 13;
	public static final int OPEN_INVENTORY				= 14;
	public static final int SWAP_TO_EQUIPMENT_1 		= 15;
	public static final int SWAP_TO_EQUIPMENT_2 		= 16;
	public static final int SWAP_TO_EQUIPMENT_3 		= 17;
	public static final int SWAP_TO_EQUIPMENT_4 		= 18;
	public static final int SWAP_TO_EQUIPMENT_5 		= 19;
	public static final int SWAP_TO_EQUIPMENT_6 		= 20;
	public static final int SWAP_TO_EQUIPMENT_7 		= 21;
	public static final int SWAP_TO_EQUIPMENT_8 		= 22;
	public static final int SWAP_TO_EQUIPMENT_9 		= 23;
	public static final int SWAP_TO_EQUIPMENT_10 		= 24;
	public static final int SWAP_TO_EQUIPMENT_PAGE_1	= 25;
	public static final int SWAP_TO_EQUIPMENT_PAGE_2	= 26;
	public static final int SWAP_TO_EQUIPMENT_PAGE_3	= 27;
	public static final int SWAP_TO_EQUIPMENT_PAGE_4	= 28;
	public static final int SWAP_TO_EQUIPMENT_PAGE_5	= 29;
	public static final int SWAP_TO_EQUIPMENT_PAGE_6	= 30;
	public static final int SWAP_TO_EQUIPMENT_PAGE_7	= 31;
	public static final int SWAP_TO_EQUIPMENT_PAGE_8	= 32;
	public static final int SWAP_TO_EQUIPMENT_PAGE_9	= 33;
	public static final int SWAP_TO_EQUIPMENT_PAGE_10	= 34;
	public static final int SWAP_TO_EQUIPMENT_PAGE_11	= 35;
	public static final int SWAP_TO_EQUIPMENT_PAGE_12	= 36;
	public static final int SWAP_TO_EQUIPMENT_PAGE_13	= 37;
	public static final int SWAP_TO_EQUIPMENT_PAGE_14	= 38;
	public static final int SWAP_TO_EQUIPMENT_PAGE_15	= 39;
	public static final int SWAP_TO_EQUIPMENT_PAGE_16	= 40;
	public static final int SWAP_TO_EQUIPMENT_PAGE_17	= 41;
	public static final int SWAP_TO_EQUIPMENT_PAGE_18	= 42;
	public static final int SWAP_TO_EQUIPMENT_PAGE_19	= 43;
	public static final int SWAP_TO_EQUIPMENT_PAGE_20	= 44;
	public static final int SWAP_TO_EQUIPMENT_PAGE_21	= 45;
	public static final int SWAP_TO_EQUIPMENT_PAGE_22	= 46;
	public static final int SWAP_TO_EQUIPMENT_PAGE_23	= 47;
	public static final int SWAP_TO_EQUIPMENT_PAGE_24	= 48;
	public static final int SCROLL_FORWARD_EQUIPMENT	= 49;
	public static final int SCROLL_BACKWARD_EQUIPMENT	= 50;
	public static final int SCROLL_EQUIP_PAGE_FORWARD 	= 51;
	public static final int SCROLL_EQUIP_PAGE_BACKWARD 	= 52;
	public static final int ATTACK_USE_PRIMARY_TRIGGER 	= 53;
	public static final int ATTACK_USE_SECONDARY_TRIGGER= 54;
	public static final int ZOOM_IN 					= 55;
	public static final int ZOOM_OUT 					= 56;
	
	public static final int TOP_INDEX_OF_REQUESTS		= 56;
	
	//Request on button release, derequest on action complete or button press.
	public static final int ATTACK_RELEASE_PRIMARY		= 57; //Special case, triggered when button is released. Uses same key as ATTACK_USE_PRIMARY. Derequest on press as well.
	public static final int ATTACK_RELEASE_SECONDARY	= 58; //Special case, triggered when button is released. Uses same key as ATTACK_USE_SECONDARY. Derequest on press as well.
	
	public static final int TOP_INDEX_OF_RELEASES		= 58;
	
	public static final int ACTIONS_VARIETY 			= 59;
}