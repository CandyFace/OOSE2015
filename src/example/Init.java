package example;
import java.awt.Rectangle;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Image;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.geom.Vector2f;

public class Init {
	Vector2f position,
            displacement,
            angle;  //former angle variable.

    float scale;
    float rotationSpeed = 3;
    float _rotationSpeed;
    float speed;
    float direction = 0;
    int maxSpeed = 10;
    float accel = 0.5f;
    float delta = 0.033f; // this is not actual delta...
    float friction = 0.98f;
    int score = 0;
    Image playerSprite;
    Image tileset;

    double noDMGTimer = 3.00; //The timer which makes the player untouchable for x amount of time
    boolean isPlayerHit = false;
    boolean showDebugger = false;


    //Animation shipSS;

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


	public Vector2f getPosition(){
		return position;
	}
	public Vector2f getAngle(){
		
		return angle;
	}
	
	
}
