package example;

import javax.vecmath.Vector2f;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class Asteroids {
	
	public float speed, rotation;
	private Vector2f position;
	public float posX,posY,scale;
	public Image test;
	
	public Image tileset;
	
	public Asteroids(float maxSpeed, float maxRotation) throws SlickException{
		
		position = new Vector2f();
		
		this.position.x = 50;
        this.position.y = 50;
		scale = 0.3f;
		
		this.speed = maxSpeed;
		this.rotation = maxRotation;
		
		tileset = new Image("graphics/Asteroids.png");
		test = tileset.getSubImage(0, 0, 197, 165);
		test.setCenterOfRotation(test.getWidth() * scale / 2, test.getHeight() * scale / 2); 
		
	}
	
	public void move(){
		
		position.x += speed;
        position.y += speed;
 
        
	}
	
	public void render(){
		
		
		test.draw(position.x,position.y,scale);
		test.rotate(rotation);
	}

}
