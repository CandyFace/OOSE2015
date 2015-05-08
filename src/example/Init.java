package example;
import java.awt.Rectangle;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Vector2f;

public class Init {
	public Vector2f position,
            displacement;

   public float scale; //Player and asteroid uses this for controlling the size.
   public float rotationSpeed = 3; //How fast should the player rotate in given direction
   public float _rotationSpeed;
   public float speed;
   public float direction = 0; // Saves the player rotation, to be used later
   public int maxSpeed = 10; // This one is odd.. but 10 means 161 max speed. Look in the player class in playerMovement for clarification.
   public float accel = 0.5f; // How fast should the player accelerate?
   public float delta = 0.033f; // this is not actual delta...
   public float friction = 0.98f; // How much friction should there be when the player starts pressing a key?
   public Image playerSprite; // The sprite defining the player
   public Image tileset; // The spritesheet having all the player image information.
   public boolean showFPS = false; // Show FPS for debugger
   public double noDMGTimer = 3.00; //The timer which makes the player untouchable for x amount of time
   public boolean isPlayerHit = false; //If hit.. start taking damage
   public boolean showDebugger = false; //If true, show debugger
    public static boolean gameOver = false;

    public void init() throws SlickException
    {

        rotationSpeed = 3; //How fast should the player rotate in given direction
       direction = 0; // Saves the player rotation, to be used later
       maxSpeed = 10; // This one is odd.. but 10 means 161 max speed. Look in the player class in playerMovement for clarification.
       accel = 0.5f; // How fast should the player accelerate?
       delta = 0.033f; // this is not actual delta...
       friction = 0.98f; // How much friction should there be when the player starts pressing a key?
       showFPS = false; // Show FPS for debugger
       noDMGTimer = 3.00; //The timer which makes the player untouchable for x amount of time
       isPlayerHit = false; //If hit.. start taking damage
       showDebugger = false; //If true, show debugger
    }

    /**
     *
     * @param sprite
     * @param offsetX
     * @param offsetY
     * @param offsetWidth
     * @param offsetHeight
     * @return new Rectangle
     */
	Rectangle getCollisionBox(Image sprite, int offsetX, int offsetY, int offsetWidth, int offsetHeight) {
		return new Rectangle((int)position.x+offsetX, (int)position.y+offsetY, sprite.getWidth()+offsetWidth, sprite.getHeight()+offsetHeight);
	}

	/**
	 *@param sprite
	 * Used for variables which are to be used for updating
	 */
    void update(Image sprite){

        sprite.rotate(_rotationSpeed); //updates the rotation speed
    }

    /**
     *@param sprite
     * Used for variables which are to be used for rendering
     */
	void render(Image sprite) {

            sprite.draw(position.x, position.y, scale); // draws player sprite
	}

    /**
     * @return position
     */
	public Vector2f getPosition(){
		return position;
	}

    /**
     *
     * @return rotationSpeed
     */
    public float getRotationSpeed()
    {
        return rotationSpeed;
    }
	
}
