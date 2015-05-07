package example;

import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Animation;
import org.newdawn.slick.SpriteSheet;

public class Player extends Init{

    private boolean keyPressed = false;
    private boolean leftPressed = false;
    public int playerLife = 3; //How much life should the player start with?
	protected Input input;

	/**
	 *
	 * @throws SlickException
	 * @return void
	 * Used to initialize all variables for usage in player class
	 */
	public void init() throws SlickException
    {
		position = new Vector2f();
		displacement = new Vector2f();
		scale = 0.8f;
        tileset = new Image("SpaceshipSheet.png");
		playerSprite = tileset.getSubImage(0, 0, 64, 64);

        playerSprite.setCenterOfRotation(playerSprite.getWidth() * scale / 2, playerSprite.getHeight() * scale / 2); //Set the origin of the player sprite
    }

    /**
     * @return playerSprite.getRotation()
     */
	public float getRotation(){
		return playerSprite.getRotation();
		
	}

    /**
     * used for updating InputControls and scoreCounter in Player class
     */
	public void update(){
        InputControls(); //Always update inputControls
	}
	
	/**
	 * Controls key inputs for playerMovement
	 */
	private void playerMovement() {

        wrapper(playerSprite);
	    if(keyPressed){

		direction = playerSprite.getRotation();

        speed+= accel * delta;
		displacement.x += (float) Math.cos(Math.toRadians(direction-90))* speed * delta;
	    displacement.y += (float) Math.sin(Math.toRadians(direction-90))* speed * delta;

	    //System.out.println(speed);
	    //System.out.println(maxSpeed);

		if(speed >= maxSpeed){
			speed = maxSpeed;
		}

		position.x += displacement.x;
		position.y += displacement.y;

		displacement.x *= friction;
        displacement.y *= friction;

        //Speed of ship
        //System.out.println("Acceleration: " +calAcceleration(displacement.x,displacement.y)* 10);

	    }// end of if

        if(!keyPressed){
        	speed-= accel * delta;
			
        	if(speed <= 0){
				speed = 0;
			}

			position.x += displacement.x;
			position.y += displacement.y;
			
			displacement.x *= friction;
	        displacement.y *= friction;

	        //Speed of ship
	       //System.out.println("Acceleration: " +calAcceleration(displacement.x,displacement.y)* 10);
	    }// end of if

	    if(leftPressed){
	    	//Set player rotation
	    	_rotationSpeed = getRotationSpeed();
	    }
	    else { // right

            _rotationSpeed = -getRotationSpeed();
	    }

    }//End of playerMovement method

    void wrapper(Image sprite){
        //Screen wrap
        if(position.y < 0-(sprite.getHeight()*scale)) position.y = Main.HEIGHT;
        if(position.y > Main.HEIGHT) position.y = 0-(sprite.getHeight()*scale);
        if(position.x < 0-(sprite.getWidth()*scale)) position.x = Main.WIDTH;
        if(position.x > Main.WIDTH) position.x = 0-(sprite.getWidth()*scale);
    }
	
//	private double calAcceleration(double a, double b)
//	{
//		return Math.sqrt(Math.pow(a,2) + Math.pow(b,2));
//	}

    private void InputControls()
    {
        playerMovement();
        if(input.isKeyDown(Input.KEY_UP) && playerLife >0) {
            keyPressed = true;
        }
        if(!input.isKeyDown(Input.KEY_UP)) {
            keyPressed = false;
        }
        if(input.isKeyDown(Input.KEY_RIGHT) && playerLife >0)
        {
            leftPressed = true;
        }
        else if(input.isKeyDown(Input.KEY_LEFT) && playerLife >0)
        {
            leftPressed = false;
        }
        else _rotationSpeed = 0; //player stops rotating when nothing is pressed.
    }
}
