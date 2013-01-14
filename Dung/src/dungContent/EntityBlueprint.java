package dungContent;

/**
 * EntityBlueprint:
 * A class that aids in constructing other entities from a centralized "blueprint"
 */
public class EntityBlueprint {
	private double dRadius;
	private double dSpeed;
	private int iAlleigance;
	private boolean bEntityCollision;
	private boolean bWallCollision;
	
	public EntityBlueprint(double radius, double speed, int alleigance, boolean collidesWithEntities, boolean collidesWithWalls){
		dRadius = radius;
		dSpeed = speed;
		iAlleigance = alleigance;
		bEntityCollision = collidesWithEntities;
		bWallCollision = collidesWithWalls;
	}
	
	public double getRadius(){
		return dRadius;
	}
	public double getSpeed(){
		return dSpeed;
	}
	public int getAlleigance(){
		return iAlleigance;
	}
	public boolean getEntityCollision(){
		return bEntityCollision;
	}
	public boolean getWallCollision(){
		return bWallCollision;
	}
}
