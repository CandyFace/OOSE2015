package example;

import javax.vecmath.Vector2f;

import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

public class Player {
	
	public int speed = 4;
	float playerY = 400;
	float playerX = 0;
	float rotationSpeed = 10;
	public Vector2f angle, position;
	public float direction = 0;
	
	public float scale = 0.05f;
	public float rotation = 0;
	
	public Input input;
	DeltaTime time = new DeltaTime();
	
	Image playerSprite;
	
	/**
	 * 
	 * @throws SlickException
	 * @return void
	 * Used to initialize all variables for usage in player class
	 */
	public void init() throws SlickException
	{
		position = new Vector2f();
		angle = new Vector2f();
		playerSprite = new Image("graphics/playerWhite.png");
		playerSprite.setCenterOfRotation(playerSprite.getWidth() * scale / 2, playerSprite.getHeight() * scale / 2); //Set the origin of the player sprite
		
	}
	
	/**
	 * @return void
	 * Controls key inputs for playerMovement
	 */
	public void playerMovement() {
		
	if(input.isKeyDown(Input.KEY_UP)){
		 this.position.x += angle.x * speed;
         this.position.y += angle.y * speed;
	}
	if(input.isKeyDown(Input.KEY_D)){
		//Set player rotation
		this.rotation = rotationSpeed;
	}
	else if (input.isKeyDown(Input.KEY_A)){
		this.rotation = -rotationSpeed;
	}	
		else this.rotation = 0;
	
	}
	
	/**
	 * @return void
	 * used for updating everything in Player class
	 */
	public void update(){
		playerMovement();
		
		direction = playerSprite.getRotation();
		
		angle.x = (float) Math.cos(Math.toRadians(direction-90));
	    angle.y = (float) Math.sin(Math.toRadians(direction-90));
	    
	}
	
	/**
	 * @return void
	 * Used for rendering all resources in Player class
	 */
	public void render()
	{
		playerSprite.rotate(rotation);
		playerSprite.draw(position.x,position.y,scale);
	}


}
