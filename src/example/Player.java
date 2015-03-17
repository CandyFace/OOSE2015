package example;

import javax.vecmath.Vector2f;

import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

public class Player {
	
	public double speed = 0;
	int maxSpeed = 10;
	float accel = 1f;
	float playerY = 400;
	float delta = 0.033f;
	float playerX = 0;
	float rotationSpeed = 5;
	public Vector2f angle, currentAngle, position;
	public float direction = 0;
	private float friction = 0.98f;
	
	public float scale = 0.05f;
	public float rotation = 0;
	
	public Input input;
	//DeltaTime time = new DeltaTime();
	
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
		currentAngle = new Vector2f();
		playerSprite = new Image("graphics/playerWhite.png");
		playerSprite.setCenterOfRotation(playerSprite.getWidth() * scale / 2, playerSprite.getHeight() * scale / 2); //Set the origin of the player sprite
	}
	
	/**
	 * @return void
	 * Controls key inputs for playerMovement
	 */
	public void playerMovement() {
		
	if(input.isKeyDown(Input.KEY_UP)){
		
		direction = playerSprite.getRotation();
		angle.x += (float) Math.cos(Math.toRadians(direction-90))* speed * delta;
	    angle.y += (float) Math.sin(Math.toRadians(direction-90))* speed * delta;

		speed+= accel * delta;
		if(speed >= maxSpeed){
			speed = maxSpeed;
		}
			
		System.out.println(speed);
		//System.out.println(speed);
		
		position.x += angle.x;
		position.y += angle.y;
		
		angle.x *= friction;
        angle.y *= friction;
		
	}
         else if(input.isKeyDown(Input.KEY_UP) == false){	
        	speed-= accel * delta;
			if(speed <= 0){
				speed = 0;
			}
			System.out.println(speed);
						
			//System.out.println(speed);
			position.x += angle.x;
			position.y += angle.y;
			
			angle.x *= friction;
	        angle.y *= friction; 
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
