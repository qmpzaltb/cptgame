package dungEntity;

/**
 * AnimationType:
 * This enum describes different animation types, which are used within the Entity and EntitySkeleton classes.
 * Every skeleton is NOT expected to handle every animation. Any unhandled animations can simply be handled by the default case.
 */
public enum AnimationType {
	ATTACK_SWORD_RIGHTHAND,
	ATTACK_SWORD_LEFTHAND,
	ATTACK_SPEAR_RIGHTHAND,
	ATTACK_SPEAR_LEFTHAND,
	DEFEND_SHIELD_RIGHTHAND,
	DEFEND_SHIELD_LEFTHAND,
	MOVE,
	IDLE,
	DEATH,
	CREATURE_ATTACK, //Because Creatures also have irresistable urges to attack anyone that isn't like themselves (xenophobia) 
	TROLL,
	USE
}
