package dungEntity;

public class Entity {

	int iEntityID;
	
	double dRadius;
	double dXPos;
	double dYPos;
	double dHeading;
	EntityController encController;
	
	public Entity(int entityID, Entity baseEntity, double xPos, double yPos, double heading){
		
	}
	
	public Entity(int entityID, double radius, EntityController controller){
		
	}
	
}