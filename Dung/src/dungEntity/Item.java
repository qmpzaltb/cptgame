package dungEntity;

import dungContent.ControllerItem;
import dungContent.ItemBlueprint;

public class Item extends Entity {

	
	public Item(ItemBlueprint ibp){
		
		encController = new ControllerItem();
	}
}
