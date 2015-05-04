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
	
	public Player playerObject = new Player();
	public Asteroids asteroid;
	public Projectile projectile;
	
	static int WIDTH = 640;
	static int HEIGHT = 480;
	
	public SimpleSlickGame(String gamename)
	{
		super(gamename);
	}

	@Override
	public void init(GameContainer gc) throws SlickException {//constructor
		
		asteroid = new Asteroids(2f, 5f);
		playerObject.init(); //Call init method from Player class
		playerObject.input = gc.getInput(); //Init input class
		projectile = new Projectile();
		projectile.input = gc.getInput(); //Init input class
	}

	//////////////////// methods ////////////////////////////
	@Override
	public void update(GameContainer gc, int delta) throws SlickException {
		//time += delta;
		
		//System.out.println(time);
		playerObject.update(); // Call update method from Player class
	    asteroid.update();
	  
	    System.out.print("number of times ");
	      System.out.println("Projectile update run :"+ 
	    projectile.update(playerObject.getPosition(), playerObject.getRotation()));
	}

	@Override
	public void render(GameContainer gc, Graphics g) throws SlickException
	{
		
		g.drawString("Hello World!", 250, 200);
		g.drawString("This is bad", 200,100);
	
		playerObject.render(playerObject.playerSprite);
		asteroid.render(asteroid.asteroidSprite);
		
		
		System.out.println("Main render");
	    System.out.println(projectile.render());
		
		
		//playerObject.render();//Call render method from Player class
	}

	public static void main(String[] args)
	{
		try
		{
			AppGameContainer appgc;
			appgc = new AppGameContainer(new SimpleSlickGame("Simple Slick Game"));
			appgc.setDisplayMode(WIDTH, HEIGHT, false);
			appgc.setTargetFrameRate(60);
			appgc.start();
		}
		catch (SlickException ex)
		{
			Logger.getLogger(SimpleSlickGame.class.getName()).log(Level.SEVERE, null, ex);
		}
	}
	
	
}
