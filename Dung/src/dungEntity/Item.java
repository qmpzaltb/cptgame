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
			dXPos = handleEntity(iParentEntityID).getXPos() + (Math.cos(handleEntity(iParentEntityID).getHeading()) * (sklLimbAttachedTo.getAttachPointX() / 64.0)) - (Math.sin(handleEntity(iParentEntityID).getHeading()) * (sklLimbAttachedTo.getAttachPointY() / 64.0));

		}
		return dXPos;
	}
	public double getDamageXPos(){
		return getXPos() + ((Math.cos(getHeading()) * (dDamageAreaXPos)) - (Math.sin(getHeading()) * (dDamageAreaYPos)));
	}
	
	public double getYPos(){
		if (sklLimbAttachedTo != null && iParentEntityID != -1){
			dYPos = handleEntity(iParentEntityID).getYPos() + (Math.cos(handleEntity(iParentEntityID).getHeading()) * (sklLimbAttachedTo.getAttachPointY() / 64.0)) + (Math.sin(handleEntity(iParentEntityID).getHeading()) * (sklLimbAttachedTo.getAttachPointX() / 64.0));
		}
		return dYPos;
	}
	public double getDamageYPos(){
		System.out.println("YPOS:: " + getYPos());
		System.out.println("DAMAGEYPOS: " + (getYPos() +  ((Math.cos(getHeading()) * (dDamageAreaYPos)) + (Math.sin(getHeading()) * (dDamageAreaXPos)))));
		return getYPos() + ((Math.cos(getHeading()) * (dDamageAreaYPos)) + (Math.sin(getHeading()) * (dDamageAreaXPos)));
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
