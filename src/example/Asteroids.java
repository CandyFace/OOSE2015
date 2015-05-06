package example;

import java.util.Random;

import javax.swing.JOptionPane;

import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Animation;
import org.newdawn.slick.SpriteSheet;

public class Asteroids extends Init {
	
	public Image tileset;
	public Image asteroidSprite;
	private Random rand = new Random();
	private int rndDir = rand.nextInt(360);

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
		scale = 1.0f;
		
		this.speed = maxSpeed;
		this._rotationSpeed = maxRotation;
		
		tileset = new Image("graphics/AsteroidL1.png");
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
}
