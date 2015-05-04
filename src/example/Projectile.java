package example;

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

	private ProjectileObj firstObj;// first in list
	// last in list
	private ProjectileObj lastObj;
	// current(for collision detection)
	private ProjectileObj currentObj;
	private Image projectileSprite;
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
		projectileSprite = new Image("graphics/fire.png");
		float scale = 0.8f;

		projectileSprite.setCenterOfRotation(projectileSprite.getWidth()
				* scale / 2, projectileSprite.getHeight() * scale / 2); // Set
		
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
	public int update(Vector2f playerPos, float playerRotation) {// move
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
		
		
		System.out.println("inside projectile update");

		createProjectiles(playerPos, playerRotation);
		// shoot!! creates new bullets

		System.out.println("inside projectile update 2");

		// ///
		moveProjectiles();

		// if list empty

		// ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

		System.out.println("inside projectile update 3");
		// ///////////// counting projectiles ////////////////////
		System.out.println("number of projectiles in list: "
				+ projectileCount());

		count++;
		return count;
	}

	private int projectileCount() {
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

	private void createProjectiles(Vector2f playerPos, float playerRotation) {
		// ///////creating new projectiles!!!/////////////
		if (input.isKeyDown(Input.KEY_SPACE) && projectileCount <= maxProjectile && !isJammed) {
			System.out.println("Space is pressed");
			
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
		System.out.println("in render");
		if (firstObj == null) {// list empty
			// do nothing
			return false;
		}

		if (firstObj != null) {

			ProjectileObj tempObj = firstObj;
			while (tempObj != lastObj) {
				tempObj.render(projectileSprite);
				tempObj = tempObj.getNext();

			}
			tempObj.render(projectileSprite);
		}
		return true;
	}
	
	
	
	
	
	
	
	
	

	// //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	// //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	// //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	// ///////////////////////////////////////////////////////////////////////////////////////////
	// in class class ///////////////////////////////////////////////////
	public class ProjectileObj {// should hold angle and position and timer
		private Vector2f pos;
		private ProjectileObj nextObj;
		private ProjectileObj prevObj;
		// public
private int timer;
		protected Vector2f position, displacement;
		protected float scale;
		protected float rotation;// rotation is always zero unless a or d is
									// pressed... thus it is an increment to the
									// sprite's rotation!
		// means that rotation is added to the sprites current rotation!
		// but in projectile class it is the absolute rotation
		protected float speed;

		// constructors ///
		public ProjectileObj(ProjectileObj nextObj) {
			this.nextObj = nextObj;

			position = new Vector2f();
			displacement = new Vector2f();
			rotation = 0;
			scale = 0.8f;
			speed = 3;
			
			timer = 0;
		}

		public ProjectileObj() {
			displacement = new Vector2f();
			position = new Vector2f();
			rotation = 0;
			scale = 0.8f;
			speed = 3;

			timer = 0;
		}

		// ///////// methods /////////////
		public void setPos(Vector2f playerPos) {
			float speed = 5;
			float delta = 2;
			// idea is for projectiles to not start in the middle of the player!
			// displacement.x = (float) Math.cos(Math.toRadians(rotation - 90))
			// * speed * delta;
			// displacement.y = (float) Math.sin(Math.toRadians(rotation - 90))
			// * speed * delta;

			// position.x = displacement.x + playerPos.x;// should displace
			// based on player angle!!!!
			// position.y = displacement.x + playerPos.y;
			position.x = playerPos.x;
			
			position.y = playerPos.y;

		}

		public void setRotation(float playerRotation) {
			rotation = playerRotation;// only set when created
		}

		public ProjectileObj getNext() {
			return nextObj;

		}
		public ProjectileObj getPrev(){
			return prevObj;
		}
		public void setPrev(ProjectileObj prevObj){
			this.prevObj = prevObj;
		}
		public void setNext(ProjectileObj nextObj){
			this.nextObj = nextObj;
		}

		public void update() {// moves projectiles
//check if timer has run out.... 
			
			
			wrapper(projectileSprite);

			float speed = 5;
			float delta = 1;

			displacement.x = (float) Math.cos(Math.toRadians(rotation - 90))
					* speed * delta;
			displacement.y = (float) Math.sin(Math.toRadians(rotation - 90))
					* speed * delta;

			position.x += displacement.x;
			position.y += displacement.y;

		timer++;
		}


		void wrapper(Image sprite) {
			// Screen wrap
			if (position.y < 0 - (sprite.getHeight() * scale))
				position.y = SimpleSlickGame.HEIGHT;// resets position when
													// exiting frame
			if (position.y > SimpleSlickGame.HEIGHT)
				position.y = 0 - (sprite.getHeight() * scale);
			if (position.x < 0 - (sprite.getWidth() * scale))
				position.x = SimpleSlickGame.WIDTH;
			if (position.x > SimpleSlickGame.WIDTH)
				position.x = 0 - (sprite.getWidth() * scale);
		}

		/**
		 * @return void Used for variables which are to be used for rendering
		 */
		void render(Image sprite) {

			sprite.setRotation(rotation);
			sprite.draw(position.x, position.y, scale);

		}

	}

}
