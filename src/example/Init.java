package example;
import org.newdawn.slick.Image;
import org.newdawn.slick.geom.Vector2f;

public class Init {
	
	protected Vector2f position, angle;// angle = displacement
	protected float scale;
	protected float rotation;//rotation is always zero unless a or d is pressed... thus it is an increment to the sprite's rotation! 
	// means that rotation is added to the sprites current rotation! 
	//but in projectile class it is the absolute rotation
	protected float speed;
	
	void wrapper(Image sprite){
	//Screen wrap
	if(position.y < 0-(sprite.getHeight()*scale)) position.y = SimpleSlickGame.HEIGHT;//resets position when exiting frame
	if(position.y > SimpleSlickGame.HEIGHT) position.y = 0-(sprite.getHeight()*scale);
	if(position.x < 0-(sprite.getWidth()*scale)) position.x = SimpleSlickGame.WIDTH;
	if(position.x > SimpleSlickGame.WIDTH) position.x = 0-(sprite.getWidth()*scale);
	}
	
	/**
	 *@return void
	 * Used for variables which are to be used for rendering
	 */
	void render(Image sprite){
		
		sprite.rotate(rotation);//adds to the maybe already rotated image
		sprite.draw(position.x,position.y,scale);
	
	}

	public Vector2f getPosition(){
		return position;
	}
	public Vector2f getAngle(){
		
		return angle;
	}
	
	
}
