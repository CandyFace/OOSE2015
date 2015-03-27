package example;
import java.awt.Rectangle;

import org.newdawn.slick.Image;
import org.newdawn.slick.geom.Vector2f;

public class Init {
	
	Vector2f position, angle;
	float scale;
	float rotation;
	float speed;
	
	void wrapper(Image sprite){
	//Screen wrap
	if(position.y < 0-(sprite.getHeight()*scale)) position.y = SimpleSlickGame.HEIGHT;
	if(position.y > SimpleSlickGame.HEIGHT) position.y = 0-(sprite.getHeight()*scale);
	if(position.x < 0-(sprite.getWidth()*scale)) position.x = SimpleSlickGame.WIDTH;
	if(position.x > SimpleSlickGame.WIDTH) position.x = 0-(sprite.getWidth()*scale);
	}
	
	Rectangle getCollisionBox(Image sprite, int offsetX, int offsetY, int offsetWidth, int offsetHeight) {
		return new Rectangle((int)position.x+offsetX, (int)position.y+offsetY, sprite.getWidth()+offsetWidth, sprite.getHeight()+offsetHeight);
	}
	
	/**
	 *@return void
	 * Used for variables which are to be used for rendering
	 */
	void render(Image sprite){
		
		sprite.rotate(rotation);
		sprite.draw(position.x,position.y,scale);
	}

}
