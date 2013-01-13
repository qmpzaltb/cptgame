package dungMain;

/**
 * TileType:
 * An enum that defines the different possible states of a tile.
 * The state's walkability, visibility, and interactionability of a tile type are defined in the switch:case statements that use TileType.
 */
public enum TileType {
	WALL,
	WALLEDGE,
	FLOOR,
	EXIT,
	ENTRANCE
}