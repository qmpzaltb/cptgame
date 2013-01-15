package dungEntity;


/**
 * KnowledgeType:
 * This enum describes the type of states that a tile may be described as by an entity.
 * Never visible defines a tile that was never before seen by an entity.
 * Was visible defines a tile that was seen by an entity, but never seen afterwards.
 * Was just visible defines a tile that was just recently seen by an entity. It is used to avoid potential flickering effects in the graphics.
 * Is visible defines a tile that is currently in an entity's sight.
 */
public enum KnowledgeType {
	NEVER_VISIBLE,
	WAS_VISIBLE,
	IS_VISIBLE
}
