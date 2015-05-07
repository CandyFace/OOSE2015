package example;

import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.Random;

import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
//check player position and angle, 
//one at a time, several objects, list of objects, removed from list depending on time!! 
//graphics, position angle and render, 
//all projectiles should check collision!! thus need not check collision on projectiles outside screen!! 
//a projectile has a countdown timer, should wrap!

//linked list, check collision, call first to last, 
//destroy last after time

public class Projectile {
	// update linked list

	// variables
	private int count;

	public ProjectileObj firstObj;// first in list
	// last in list
	private ProjectileObj lastObj;
	// current(for collision detection)
	private ProjectileObj currentObj;
	
private int maxProjectile;
	
	public Input input;

	private int projectileCount;
private long msCount;
private int msDist;
private long lastMs;
private boolean isJammed;
	
	
	// //
	// //////////////////////////////////////////////////////////////////////////////////////
	// /////////////////////////////////////////////////////////////////////////////////////////
	// constructor
	public Projectile() throws SlickException {// initialize, list starts empty!
	
		
		maxProjectile = 5;
		
		projectileCount = 0;
		count = 0;
		
		msCount = 0;
		msDist = 500;
		lastMs = 0;
		isJammed = false;
		
	}

	// ///////////////////////////////////////////////////////////////
	// /////////////////// methods ///////////////////////////////////////////
	public Vector2f getPosition() {// position of current obj

		return currentObj.pos;
	}

	// ///// collision detection //////////////
	public boolean next() {// sets current to next in list
		boolean state = true;

		// check if current == last in list, if so state = false, otherwise
		// current == next in list
		return state;
	}

	// /////////////////////////////////////
	// creating projectileObj, need player position!
	public int update(Vector2f playerPos, float playerRotation) throws SlickException {// move
																	// pressed!
		// 2 parts, create new bullets, update old bullets, new bullets based on
		// player position!!!

		
		if(msCount < msDist) {
			msCount = System.currentTimeMillis() -lastMs; //counter
			
		}
		
		else {
			msCount = 0;
		lastMs = System.currentTimeMillis();
		isJammed = false;
		}
	//	lastMs;
		
		
		//System.out.println("inside projectile update");

		createProjectiles(playerPos, playerRotation);
		// shoot!! creates new bullets

		//System.out.println("inside projectile update 2");

		// ///
		moveProjectiles();

		// if list empty

		// ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

		//System.out.println("inside projectile update 3");
		// ///////////// counting projectiles ////////////////////
		//System.out.println("number of projectiles in list: "
				//+ projectileCount());

		count++;
		return count;
	}

	public int projectileCount() {
		projectileCount = 0;
		// loop through list
		if (firstObj != null) {

			ProjectileObj tempObj = firstObj;
			while (tempObj != lastObj) {
				projectileCount++;
				tempObj = tempObj.getNext();

			}
			projectileCount++;

		}

		return projectileCount;
	}
	
	public ProjectileObj[] getProjectileObjList(){
		
		
		if(projectileCount() != 0){
		ProjectileObj[] projectileObjList = new ProjectileObj[projectileCount];
		projectileObjList[0] = firstObj;
		currentObj = firstObj;
		for(int i = 0;i<projectileCount-1;i++){
			projectileObjList[i+1] = currentObj.getNext();  
			currentObj = currentObj.getNext();
		}
		currentObj = firstObj;
		
		return projectileObjList;
		} 
		return null;
	}
	
	


	private void moveProjectiles() {//also checks timer, and maybe delete last projectile

		if (firstObj != null) {

			ProjectileObj tempObj = firstObj;
			while (tempObj != lastObj) {
				tempObj.update();
				tempObj = tempObj.getNext();

			}
			tempObj.update();//last in list
			if(tempObj.timer > 300){
				if(tempObj.getPrev() != null){
					
				
				lastObj = tempObj.getPrev();
				lastObj.setNext(null);
				}
				else{//when only one obj in list
					firstObj = null;//resets list
					lastObj = null;
				}
			}
		}

	}

	private void createProjectiles(Vector2f playerPos, float playerRotation) throws SlickException {
		// ///////creating new projectiles!!!/////////////
		if (input.isKeyPressed(Input.KEY_SPACE) && projectileCount <= maxProjectile && !isJammed) {
			//System.out.println("Space is pressed");
			
			isJammed = true;
			
			if (firstObj != null) {// list NOT empty
				firstObj = new ProjectileObj(firstObj);// new projectile
														// pointing at earlier
														// projectile
				firstObj.getNext().setPrev(firstObj);//makes earlier projectile also point at newly created projectile!!!
			} else {// list empty
				firstObj = new ProjectileObj();
				lastObj = firstObj;

			}

			firstObj.setRotation(playerRotation);
			firstObj.setPos(playerPos);
		}
		// ////////////////

	}

	// ////////////////////////////////// render
	// ///////////////////////////////////////////////

	public boolean render() {// loops through list and render all
		// if list empty
		//System.out.println("in render");
		if (firstObj == null) {// list empty
			// do nothing
			return false;
		}

		if (firstObj != null) {

			ProjectileObj tempObj = firstObj;
			while (tempObj != lastObj) {
				tempObj.render();
				tempObj = tempObj.getNext();

			}
			tempObj.render();
		}
		return true;
	}
	
	
	
	
	
	
	
	
	


	
}
