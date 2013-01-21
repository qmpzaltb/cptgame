package dungContent;

/**
 * EntityBlueprint:
 * A class that aids in constructing other entities from a centralized "blueprint"
 */
public class EntityBlueprint {
	protected double dRadius;
	protected double dSpeed;
	protected int iAlleigance;
	protected boolean bEntityCollision;
	protected boolean bWallCollision;
	protected double dDamageAreaX;
	protected double dDamageAreaY;
	protected double dDamageAreaRadius;
	
	public EntityBlueprint(double radius, double speed, int alleigance, boolean collidesWithEntities, boolean collidesWithWalls, double damageAreaX, double damageAreaY, double damageAreaRadius){
		dRadius = radius;
		dSpeed = speed;
		iAlleigance = alleigance;
		bEntityCollision = collidesWithEntities;
		bWallCollision = collidesWithWalls;
		dDamageAreaX = damageAreaX;
		dDamageAreaY = damageAreaY;
		dDamageAreaRadius = damageAreaRadius;
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
