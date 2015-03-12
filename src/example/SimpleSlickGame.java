package example;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;


public class SimpleSlickGame extends BasicGame
{
	
	Player playerObject = new Player();
	public Asteroids asteroid;
	
	public SimpleSlickGame(String gamename)
	{
		super(gamename);
	}

	@Override
	public void init(GameContainer gc) throws SlickException {
		
		asteroid = new Asteroids(2f, 10f);
		playerObject.init(); //Call init method from Player class
		playerObject.input = gc.getInput(); //Init input class
	}

	@Override
	public void update(GameContainer gc, int delta) throws SlickException {
		playerObject.update(delta); // Call update method from Player class
	    asteroid.move();
	       
	}

	@Override
	public void render(GameContainer gc, Graphics g) throws SlickException
	{
		g.drawString("Hello World!", 250, 200);
		g.drawString("This is bad", 200,100);

		asteroid.render();
		
		playerObject.render();//Call render method from Player class
	}

	public static void main(String[] args)
	{
		try
		{
			AppGameContainer appgc;
			appgc = new AppGameContainer(new SimpleSlickGame("Simple Slick Game"));
			appgc.setDisplayMode(640, 480, false);
			//appgc.setTargetFrameRate(60);
			appgc.start();
		}
		catch (SlickException ex)
		{
			Logger.getLogger(SimpleSlickGame.class.getName()).log(Level.SEVERE, null, ex);
		}
	}
	
	
}
