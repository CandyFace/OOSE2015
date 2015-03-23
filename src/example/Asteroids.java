package example;


import javax.vecmath.Vector2f;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class Asteroids {
	
	public float speed, rotation;
	private Vector2f position;
	public float posX,posY,scale;
	public Image asteroidSprite;
	
	
	public Image tileset;
	
	DeltaTime time = new DeltaTime();
	
	/**
	 * @param float maxSpeed
	 * @param float maxRotation
	 */
	public Asteroids(float maxSpeed, float maxRotation) throws SlickException{
		
		position = new Vector2f();
		
		this.position.x = 50;
        this.position.y = 50;
		scale = 0.3f;
		
		this.speed = maxSpeed;
		this.rotation = maxRotation;
		
		tileset = new Image("graphics/Asteroids.png");
		asteroidSprite = tileset.getSubImage(0, 0, 197, 165);
		asteroidSprite.setCenterOfRotation(asteroidSprite.getWidth() * scale / 2, asteroidSprite.getHeight() * scale / 2); 
		
	}
	
	/**
	 *@return void
	 * Used for variables which needs to be updated all the time.
	 */
	public void update()
	{
		move();
		
	}
	
	/**
	 *@return void
	 * 
	 */
	public void move(){
		
		this.position.x += speed;
        this.position.y += speed;
 
	}
	
	/**
	 *@return void
	 * Used for variables which are to be used for rendering
	 */
	public void render(){
		
		asteroidSprite.draw(position.x,position.y,scale);
		asteroidSprite.rotate(rotation);
	}

}
