package example;

import org.newdawn.slick.geom.Vector2f;

import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

public class Player extends Init{
	

	private int maxSpeed = 10;
	private float accel = 0.5f;
	private float delta = 0.033f;
	private float rotationSpeed = 3;
	private float direction = 0;
	private float friction = 0.98f;
	Image playerSprite;
	
	public Input input;

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
		scale = 0.8f;
		playerSprite = new Image("graphics/Spaceship.gif");
		playerSprite.setCenterOfRotation(playerSprite.getWidth() * scale / 2, playerSprite.getHeight() * scale / 2); //Set the origin of the player sprite
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
	 * Controls key inputs for playerMovement
	 */
	private void playerMovement() {
		
		wrapper(playerSprite);
		
	if(input.isKeyDown(Input.KEY_UP)){
		
		direction = playerSprite.getRotation();
		angle.x += (float) Math.cos(Math.toRadians(direction-90))* speed * delta;
	    angle.y += (float) Math.sin(Math.toRadians(direction-90))* speed * delta;
	    
	    System.out.println(speed);
	    System.out.println(maxSpeed);
	    
		speed+= accel * delta;
		if(speed >= maxSpeed){
			speed = maxSpeed;
		}

		position.x += angle.x;
		position.y += angle.y;
		
		angle.x *= friction;
        angle.y *= friction;
       
		
	}
         else if(!input.isKeyDown(Input.KEY_UP)){
        	speed-= accel * delta;
			
        	if(speed <= 0){
				speed = 0;
			}

			position.x += angle.x;
			position.y += angle.y;
			
			angle.x *= friction;
	        angle.y *= friction; 
	        
	        //Speed of ship
	        System.out.println("Acceleration: " +calAcceleration(angle.x,angle.y)* 10);
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
	
	private double calAcceleration(double a, double b)
	{
		return Math.sqrt(Math.pow(a,2) + Math.pow(b,2));
	}


}
