package example;

import org.newdawn.slick.geom.Vector2f;

import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

public class Player {
	

	private double speed = 0;
	private int maxSpeed = 10;
	private float accel = 0.5f;
	private float delta = 0.033f;
	private float rotationSpeed = 3;
	private Vector2f angle, position;
	private float direction = 0;
	private float friction = 0.98f;
	private float scale = 0.05f;
	private float rotation = 0;
	private Image playerSprite;
	
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
		playerSprite = new Image("graphics/playerWhite.png");
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
	 * Used for rendering all resources in Player class
	 */
	public void render()
	{
		playerSprite.rotate(rotation);
		playerSprite.draw(position.x,position.y,scale);
	}
	
	/**
	 * @return void
	 * Controls key inputs for playerMovement
	 */
	private void playerMovement() {
		
	if(input.isKeyDown(Input.KEY_UP)){
		
		direction = playerSprite.getRotation();
		angle.x += (float) Math.cos(Math.toRadians(direction-90))* speed * delta;
	    angle.y += (float) Math.sin(Math.toRadians(direction-90))* speed * delta;

		speed+= accel * delta;
		if(speed >= maxSpeed){
			speed = maxSpeed;
		}

		position.x += angle.x;
		position.y += angle.y;
		
		angle.x *= friction;
        angle.y *= friction;
        
        //Speed of ship
        System.out.println("Acceleration: " +Math.sqrt(Math.pow(angle.x,2) + Math.pow(angle.y,2)) * 10);
		
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
	
	//Wrap player
	if(position.y < 0-(playerSprite.getHeight()*scale)) position.y = SimpleSlickGame.HEIGHT;
	if(position.y > SimpleSlickGame.HEIGHT) position.y = 0-(playerSprite.getHeight()*scale);
	if(position.x < 0-(playerSprite.getWidth()*scale)) position.x = SimpleSlickGame.WIDTH;
	if(position.x > SimpleSlickGame.WIDTH) position.x = 0-(playerSprite.getWidth()*scale);
	
	}
	
	private double calAcceleration(double a, double b)
	{
		return Math.sqrt(Math.pow(a,2) + Math.pow(b,2));
	}


}
