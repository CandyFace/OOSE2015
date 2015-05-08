package example;

import java.util.Random;

import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class Asteroids extends Init {
	
	public Image tileset;
	public Image asteroidSprite;
	private Random rand = new Random();
	private int rndDir = rand.nextInt(360);
	public boolean destroyed = false;

	/**
	 * @param maxSpeed float
	 * @param maxRotation float
	 */
	public Asteroids(float maxSpeed, float maxRotation) throws SlickException{
		int rndX = rand.nextInt(Main.WIDTH) + 1;
		int rndY = rand.nextInt(Main.HEIGHT) + 1;
		
		//50 is the maximum and the 1 is our minimum 
		
		position = new Vector2f();
		displacement = new Vector2f();
		
		this.position.x = rndX;
        this.position.y = rndY;
        scale = rand.nextFloat() + 0.5f;
		//scale = 1.0f;
		
		this.speed = maxSpeed;
		this._rotationSpeed = maxRotation;
		
		tileset = new Image("AsteroidL1.png");
		asteroidSprite = tileset.getSubImage(0, 0, 64, 64);
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
	private void move(){
		
		displacement.x = (float) Math.cos(Math.toRadians(rndDir))* speed;
	    displacement.y = (float) Math.sin(Math.toRadians(rndDir)) * speed;
		
		this.position.x += displacement.x;
        this.position.y += displacement.y;
        
        wrapper(asteroidSprite);
	}

    void wrapper(Image sprite){
        //Screen wrap
        if(position.y < 0-(sprite.getHeight()*scale)) position.y = Main.HEIGHT;
        if(position.y > Main.HEIGHT) position.y = 0-(sprite.getHeight()*scale);
        if(position.x < 0-(sprite.getWidth()*scale)) position.x = Main.WIDTH;
        if(position.x > Main.WIDTH) position.x = 0-(sprite.getWidth()*scale);
    }
}
