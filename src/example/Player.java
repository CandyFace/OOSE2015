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
		scale = 0.8f;
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

	}
	
	/**
	 * @return void
	 * Controls key inputs for playerMovement
	 */
	private void playerMovement() {

        wrapper(playerSprite);
	    if(keyPressed){

		direction = playerSprite.getRotation();

        speed+= accel * delta;
		angle.x += (float) Math.cos(Math.toRadians(direction-90))* speed * delta;
	    angle.y += (float) Math.sin(Math.toRadians(direction-90))* speed * delta;

	    System.out.println(speed);
	    System.out.println(maxSpeed);

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
