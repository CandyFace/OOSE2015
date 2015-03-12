package example;

import javax.vecmath.Vector2f;

import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

public class Player {
	
	public int speed = 15;
	float playerY = 400;
	float playerX = 0;
	float rotationSpeed = 50;
	public Vector2f angle, position;
	public float direction = 0;
	
	public float scale = 0.05f;
	public float rotation = 0;
	
	public Input input;
	DeltaTime time = new DeltaTime();
	
	Image playerSprite;
	
	public void init() throws SlickException
	{
		position = new Vector2f();
		angle = new Vector2f();
		playerSprite = new Image("graphics/playerWhite.png");
		playerSprite.setCenterOfRotation(playerSprite.getWidth() * scale / 2, playerSprite.getHeight() * scale / 2); //Set the origin of the player sprite
		
	}
	
	public void playerMovement() {
		
	
	if(input.isKeyDown(Input.KEY_UP)){
		 this.position.x += angle.x * speed * time.deltaTime();
         this.position.y += angle.y * speed * time.deltaTime();
	}
	if(input.isKeyDown(Input.KEY_D)){
		//Set player rotation
		this.rotation = rotationSpeed * time.deltaTime();
	}
	else if (input.isKeyDown(Input.KEY_A)){
		this.rotation = -rotationSpeed * time.deltaTime();
	}	
		else this.rotation = 0;
	
	}
	
	public void update(){
		playerMovement();
		
		direction = playerSprite.getRotation();
		
		angle.x = (float) Math.cos(Math.toRadians(direction-90));
	    angle.y = (float) Math.sin(Math.toRadians(direction-90));
	    
	}
	
	public void render()
	{
		playerSprite.rotate(rotation);
		playerSprite.draw(position.x,position.y,scale);
	}


}
