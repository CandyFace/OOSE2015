package example;
import org.newdawn.slick.Image;
import org.newdawn.slick.geom.Vector2f;

public class Init {
	
	private Vector2f position, angle;
	private float scale;
	private float rotation;
	private float speed;
	
	void wrapper(Image sprite){
	//Screen wrap
	if(position.y < 0-(sprite.getHeight()*scale)) position.y = SimpleSlickGame.HEIGHT;
	if(position.y > SimpleSlickGame.HEIGHT) position.y = 0-(sprite.getHeight()*scale);
	if(position.x < 0-(sprite.getWidth()*scale)) position.x = SimpleSlickGame.WIDTH;
	if(position.x > SimpleSlickGame.WIDTH) position.x = 0-(sprite.getWidth()*scale);
	}
	
	/**
	 *@return void
	 * Used for variables which are to be used for rendering
	 */
	void render(Image sprite){
		
		sprite.rotate(rotation);
		sprite.draw(position.x,position.y,scale);
	}

	public Vector2f getPosition(){
		return position;
	}
	public Vector2f getAngle(){
		
		return angle;
	}
	
	
}
