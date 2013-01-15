<<<<<<< HEAD
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
=======
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
>>>>>>> ea62cde162f853a00347753b2708afe341b5e0f9
