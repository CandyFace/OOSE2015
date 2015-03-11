package example;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

//Det virker! Nu vi klar!

public class SimpleSlickGame extends BasicGame
{
	
	private Image player = null;
	private Color white = new Color(255,255,255);
	private int speed = 10;
	
	
	private float scale = 0.3f;
	private float rotation = 0;
	Input input;

	
	public SimpleSlickGame(String gamename)
	{
		super(gamename);
	}

	@Override
	public void init(GameContainer gc) throws SlickException {
		
		input = gc.getInput(); //Init input class
		
		player = new Image("graphics/playerWhite.png");

		player.setCenterOfRotation(player.getWidth() * scale / 2, player.getHeight() * scale / 2); //Set the origin of the player sprite

	}

	@Override
	public void update(GameContainer gc, int i) throws SlickException {
		
		//Set player rotation
		
		if(input.isKeyDown(Input.KEY_UP)) {
			
		}
		
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
		player.draw(50f,50f,scale); //Draw the player
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
