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
	
	public Asteroids(float maxSpeed, float maxRotation) throws SlickException{
		
		position = new Vector2f();
		
		this.position.x = 50;
        this.position.y = 50;
		scale = 0.3f;
		
		this.speed = maxSpeed * time.deltaTime();
		this.rotation = maxRotation * time.deltaTime();
		
		tileset = new Image("graphics/Asteroids.png");
		asteroidSprite = tileset.getSubImage(0, 0, 197, 165);
		asteroidSprite.setCenterOfRotation(asteroidSprite.getWidth() * scale / 2, asteroidSprite.getHeight() * scale / 2); 
		
	}
	
	public void move(){
		
		this.position.x += speed * time.deltaTime();
        this.position.y += speed * time.deltaTime();
 
	}
	
	public void render(){
		
		
		asteroidSprite.draw(position.x,position.y,scale);
		asteroidSprite.rotate(rotation);
	}

}
