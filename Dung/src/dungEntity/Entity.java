package dungEntity;

public class Entity {
	
	/* Entity List. -- This is just to refer to what entity is what and its corresponding code.
	 * ----------------------------------------------------------------------------------------
	 * 
	 * Important Note: Lets not make too many entities... keep in mind we need to make images for these and animations. ALSO IMPORTANT THING! should we make a package for entityAnimations? 
	 * 
	 * #### MONSTERS:
	 *                 Unicorn                                             ---- 0001
	 *                 Skeleton                                            ---- 0002
	 *                 Creeper :D:D:D:D:D:D:D:D:D:D:D                      ---- 0003
	 *                 Robot                                               ---- 0004
	 *                 Zombie                                              ---- 0005
	 *                 Dwarf                                               ---- 0006
	 *                 Gool                                                ---- 0007
	 *                 Bat                                                 ---- 0008
	 *                 Wolf                                                ---- 0009
	 *                 Dog                                                 ---- 0010
	 *                 Shark                                               ---- 0011
	 *                 Whale                                               ---- 0012
	 *                 Pikachu                                             ---- 0013
	 * #### DUMB: --QUESTION: Can you dual weild? Because if you can do we count 2 swords as a seperate entity or do we combine 2 entities D: I suggest we don't do this because it will complicate things
	 *                 Bow                                                 ---- 0014
	 *                 Arrow                                               ---- 0015
	 *                 Boots                                               ---- 0016
	 *                 Armor                                               ---- 0017
	 *                 Caps???????????????? TF2 ANYBODY? :D                ---- 0018
	 *                 Gun                                                 ---- 0019
	 *                 Sword                                               ---- 0020
	 *                 Sheild                                              ---- 0021
	 *                 Broken Bear Bottle                                  ---- 0022
	 *                 Unicorn Horn                                        ---- 0023
	 *                 Bible (Yes Mark, for bonus marks from the teacher we need to add biblic morality) :)
	 *                 
	 *                                        
	 * #### PLAYER:
	 *                 Current PC Player (If we decide on doing a network) ---- 00?? 
	 * 
	 */
	private int iEntityID;
	private int iEntityStatus; //This will be health and stuff (40% health remaining and stuff...) The reason why I named this EntityStatus because entities like arrows can be like 50% used (this means arrow is broken and done with) or 50% unused (you haven't shot this arrow yet)
	private int iEntityAction; //The entity's current action. Try to use the values from GameActions from here.
	private long lEntityActionTimeLeft; //Time left in the Entity's current action
	//Should we have a variable called EntityCurrAction? and use a coding system (like used in EntityID) to make specific actions like Attack, Heal, Defend, Move, Use Item/Potion, Call Chuck Norris, etc...
	
	private double dRadius;
	private double dXPos;
	private double dYPos;
	private double dHeading;
	
	EntityController encController;
	
	public Entity(int entityID, Entity baseEntity, double xPos, double yPos, double heading){
		
	}
	
	public Entity(int entityID, double radius, EntityController controller){
		
	}
	
}