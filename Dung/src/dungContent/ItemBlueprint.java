package dungContent;

/**
 * ItemBlueprint:
 * A class that defines the characteristics of items.
 * Item objects can be constructed with a constructor that accepts ItemBlueprint, allowing for standardized item generation.
 */
public class ItemBlueprint extends EntityBlueprint {
	
	public ItemBlueprint(double damageAreaX, double damageAreaY, double damageAreaRadius){
		super(0,0,-1,false,false,damageAreaX,damageAreaY,damageAreaRadius);
		
	}
	public double getDamageAreaX(){
		return dDamageAreaX;
	}
	public double getDamageAreaY(){
		return dDamageAreaY;
	}
	public double getDamageAreaRadius(){
		return dDamageAreaRadius;
	}
	
	
}
