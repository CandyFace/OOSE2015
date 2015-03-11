package example;

import javax.vecmath.Vector2f;
import org.newdawn.slick.Input;

public class Player {
	
	public int speed = 10;
	float playerY = 400;
	float playerX = 0;
	public Vector2f angle, position;
	public float direction = 0;
	
	public float scale = 0.05f;
	public float rotation = 0;
	
	public Input input;
	
	public void playerMovement() {
	
	if(input.isKeyDown(Input.KEY_UP)) {
		 position.x += angle.x * speed;
         position.y += angle.y * speed;
	}
	
	//Set player rotation
	if(input.isKeyDown(Input.KEY_D)){
		rotation = 5;
	}
	else if (input.isKeyDown(Input.KEY_A)){
		rotation = -5;
	}
	else rotation = 0;
	}

}
