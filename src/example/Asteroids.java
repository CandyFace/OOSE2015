package example;

import java.util.Random;

import org.newdawn.slick.geom.Vector2f;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class Asteroids {
	
	public float speed, rotation;
	private Vector2f position, angle;
	public float posX,posY,scale;
	public Image asteroidSprite;
	
	Random rand = new Random();
	
	int rndDir = rand.nextInt(360);
	
	public Image tileset;
	
	DeltaTime time = new DeltaTime();
	
	/**
	 * @param float maxSpeed
	 * @param float maxRotation
	 */
	public Asteroids(float maxSpeed, float maxRotation) throws SlickException{
		
		
		int rndX = rand.nextInt(SimpleSlickGame.WIDTH) + 1;
		int rndY = rand.nextInt(SimpleSlickGame.HEIGHT) + 1;
		
		//50 is the maximum and the 1 is our minimum 
		
		position = new Vector2f();
		angle = new Vector2f();
		
		this.position.x = rndX;
        this.position.y = rndY;
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
		
		angle.x = (float) Math.cos(Math.toRadians(rndDir))* speed;
	    angle.y = (float) Math.sin(Math.toRadians(rndDir)) * speed;
		
		this.position.x += angle.x;
        this.position.y += angle.y;
        

    	//Wrap player
    	if(position.y < 0-(asteroidSprite.getHeight()*scale)) position.y = SimpleSlickGame.HEIGHT;
    	if(position.y > SimpleSlickGame.HEIGHT) position.y = 0-(asteroidSprite.getHeight()*scale);
    	if(position.x < 0-(asteroidSprite.getWidth()*scale)) position.x = SimpleSlickGame.WIDTH;
    	if(position.x > SimpleSlickGame.WIDTH) position.x = 0-(asteroidSprite.getWidth()*scale);
 
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
