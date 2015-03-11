package example;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.vecmath.Vector2f;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

public class SimpleSlickGame extends BasicGame
{
	
	private Image player = null;
	private int speed = 5;
	
	public Vector2f angle, position;
	public float direction = 0;
	
	private float scale = 0.3f;
	private float rotation = 0;
	Input input;

	
	public SimpleSlickGame(String gamename)
	{
		super(gamename);
	}

	@Override
	public void init(GameContainer gc) throws SlickException {
		
		position = new Vector2f();
		angle = new Vector2f();
		
		input = gc.getInput(); //Init input class
		
		player = new Image("graphics/playerWhite.png");

		player.setCenterOfRotation(player.getWidth() * scale / 2, player.getHeight() * scale / 2); //Set the origin of the player sprite

	}

	@Override
	public void update(GameContainer gc, int i) throws SlickException {
		
		direction = player.getRotation();
		
		angle.x = (float) Math.cos(Math.toRadians(direction-90));
	    angle.y = (float) Math.sin(Math.toRadians(direction-90));
		
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

	@Override
	public void render(GameContainer gc, Graphics g) throws SlickException
	{
		g.drawString("Hello World!", 250, 200);
		g.drawString("This is bad", 200,100);
		
		player.rotate(rotation); //Rotate the player
		player.draw(position.x,position.y,scale); //Draw the player
	}

	public static void main(String[] args)
	{
		try
		{
			AppGameContainer appgc;
			appgc = new AppGameContainer(new SimpleSlickGame("Simple Slick Game"));
			appgc.setDisplayMode(640, 480, false);
			appgc.start();
		}
		catch (SlickException ex)
		{
			Logger.getLogger(SimpleSlickGame.class.getName()).log(Level.SEVERE, null, ex);
		}
	}
}
