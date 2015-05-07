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

	public Input input;

    //public AnimationSystem aniSys = new AnimationSystem();
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
        tileset = new Image("graphics/spaceShipSheet.png");
		playerSprite = tileset.getSubImage(0, 0, 64, 64);

        playerSprite.setCenterOfRotation(playerSprite.getWidth() * scale / 2, playerSprite.getHeight() * scale / 2); //Set the origin of the player sprite
    }

    public void scoreCounter()
    {
        //TODO implement an actual score system
        //The player should only gain 50 points when an asteroid has been destroyed.
        //Less points could be given when only partly destroyed aka. splitted.
        if (input.isKeyPressed(Input.KEY_E))
        {
            score += 50;
        }
    }


	/**
	 * @return void
	 * used for updating everything in Player class
	 */
	public float getRotation(){
		return playerSprite.getRotation();
		
	}
	
	public void update(){
        InputControls();
        scoreCounter();
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

	}
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
	}

	if(leftPressed){
		//Set player rotation
		_rotationSpeed = rotationSpeed;
	}
	else { // right
		_rotationSpeed = -rotationSpeed;
	}

	
	}

    void wrapper(Image sprite){
        //Screen wrap
        if(position.y < 0-(sprite.getHeight()*scale)) position.y = Main.HEIGHT;
        if(position.y > Main.HEIGHT) position.y = 0-(sprite.getHeight()*scale);
        if(position.x < 0-(sprite.getWidth()*scale)) position.x = Main.WIDTH;
        if(position.x > Main.WIDTH) position.x = 0-(sprite.getWidth()*scale);
    }
	
	private double calAcceleration(double a, double b)
	{
		return Math.sqrt(Math.pow(a,2) + Math.pow(b,2));
	}

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
