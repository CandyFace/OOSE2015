package example;

import javax.vecmath.Vector2f;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class Asteroids {
	
	public float speed, rotation;
	public Vector2f position;
	
	public Image tileset;
	
	public Asteroids(float speed, float rotation) throws SlickException{
		
		this.speed = speed;
		this.rotation = rotation;
		
		position = new Vector2f();
		
		tileset = new Image("graphics/Asteroids.png");
		
	}

}
