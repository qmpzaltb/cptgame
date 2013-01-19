package dungEntity;

import java.awt.Color;
import dungContent.ControllerItem;
import dungContent.ItemBlueprint;
import static dungMain.DungeonGame.handleEntity;


/**
 * Item:
 * A class designed to provide specific functionality for items that regular Entities cannot provide.
 */
public class Item extends Entity {
	
	private SkeletonLimb sklLimbAttachedTo;
	private int iParentEntityID;
	
	public Item(ItemBlueprint ibp, ControllerItem controller, EntitySkeleton itemSkeleton, Color[] itemColorSet, int parentEntityID, SkeletonLimb limbGrabbed){
		
		super(-2, ibp, -1000, -1000, 0, controller, itemSkeleton, itemColorSet);
		attachLimb(limbGrabbed);
		encController = controller;
		dRadius = ibp.getRadius();
		iParentEntityID = parentEntityID;
		
	}
	
	public Item(ItemBlueprint ibp, ControllerItem controller, EntitySkeleton itemSkeleton, Color[] itemColorSet){
		this(ibp, controller, itemSkeleton, itemColorSet, -1, null);
	}
	
	public int getParentID(){
		return iParentEntityID;
	}
	
	public void setParentID(int parentID){
		iParentEntityID = parentID;
	}
	
	public void attachLimb(SkeletonLimb limbToAttachTo){
		sklLimbAttachedTo = limbToAttachTo;
	}
	public void detachLimb(){
		sklLimbAttachedTo = null;
	}
	
	public double getXPos(){
		if (sklLimbAttachedTo != null && iParentEntityID != -1){
			dXPos = handleEntity(iParentEntityID).getXPos() + Math.cos(getHeading()) * (sklLimbAttachedTo.getAttachPointX() / 64) - Math.sin(getHeading()) * (sklLimbAttachedTo.getAttachPointY() / 64);
		}
		return dXPos;
	}
	
	public double getYPos(){
		if (sklLimbAttachedTo != null && iParentEntityID != -1){
			dYPos = handleEntity(iParentEntityID).getYPos() - Math.cos(Math.PI + getHeading()) * (sklLimbAttachedTo.getAttachPointY() / 64) + Math.sin(getHeading()) * (sklLimbAttachedTo.getAttachPointX() / 64);
		}
		return dYPos;
	}
	
	public double getHeading(){
		if (sklLimbAttachedTo != null && iParentEntityID != -1){
			dHeading = handleEntity(iParentEntityID).getHeading() + sklLimbAttachedTo.getAttachHeading();
		}
		return dHeading;
	}
	
	public void giveIdentity(int entityID){
		iEntityID = entityID;
	}
	
}
