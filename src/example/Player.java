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
	private float scale = 0.8f;
	private float rotation = 0;
	private Image playerSprite;

    private boolean keyPressed = false;
    private boolean leftPressed = false;
	
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
		playerSprite = new Image("graphics/Spaceship.gif");
		playerSprite.setCenterOfRotation(playerSprite.getWidth() * scale / 2, playerSprite.getHeight() * scale / 2); //Set the origin of the player sprite
	}
	
	
	/**
	 * @return void
	 * used for updating everything in Player class
	 */
	public void update(){
        InputControls();
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
	if(keyPressed){
		direction = playerSprite.getRotation();

        speed+= accel * delta;
		angle.x += (float) Math.cos(Math.toRadians(direction-90))* speed * delta;
	    angle.y += (float) Math.sin(Math.toRadians(direction-90))* speed * delta;


		if(speed >= maxSpeed){
			speed = maxSpeed;
		}

		position.x += angle.x;
		position.y += angle.y;
		
		angle.x *= friction;
        angle.y *= friction;
        
        //Speed of ship
        System.out.println("Acceleration: " +calAcceleration(angle.x,angle.y)* 10);
		
	}
        if(!keyPressed){
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
         
	if(leftPressed){
		//Set player rotation
		this.rotation = rotationSpeed;
	}
	else if (!leftPressed){ // right
		this.rotation = -rotationSpeed;
	}
	
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

    private void InputControls()
    {
        playerMovement();
        if(input.isKeyDown(Input.KEY_UP)) {
            keyPressed = true;
        }
        if(!input.isKeyDown(Input.KEY_UP)) {
            keyPressed = false;
        }
        if(input.isKeyDown(Input.KEY_RIGHT))
        {
            leftPressed = true;
        }
        else if(input.isKeyDown(Input.KEY_LEFT))
        {
            leftPressed = false;
        }
        else rotation = 0;
    }
}
