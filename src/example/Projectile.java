package example;

import java.util.Random;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
//check player position and angle, 
//one at a time, several objects, list of objects, removed from list depending on time!! 
//graphics, position angle and render, 
//all projectiles should check collision!! thus need not check collision on projectiles outside screen!! 
//a projectile has a countdown timer, should wrap!

//linked list, check collision, call first to last, 
//destroy last after time

public class Projectile{
//update linked list
	
	//variables
	
	private ProjectileObj firstObj;//first in list
	//last in list
	private ProjectileObj lastObj;
	//current(for collision detection)
	private ProjectileObj currentObj;
	
	public Vector2f getPosition(){
		
		return currentObj.pos;
	}
	
	public boolean next(){//sets current to next in list
		boolean state = true;
	
		//check if current == last in list, if so state = false, otherwise current == next in list
		
		
		
		return state;
	}
	
	
	//creating projectileObj, need player position! 
	
	
	public void update(Vector2f playerPos){//move projectiles, create new based on player position and key pressed! 
		
		
	}
	
	
	
	public class ProjectileObj extends Init{//should hold angle and position and timer
	public Vector2f pos;
		
		
		
		public void destroyProjectile(){//when collided
		
	 
	}
		
		
		
	}
	
}
