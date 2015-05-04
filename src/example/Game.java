package example;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;


public class Game extends BasicGame
{
	
	Player playerObject = new Player();
	//public Asteroids asteroid;
	
	Asteroids[] asteroids = new Asteroids[10];

	static int WIDTH = 640;
	static int HEIGHT = 480;
	
	public Game(String gamename)
	{
		super(gamename);
	}

	@Override
	public void init(GameContainer gc) throws SlickException {
		
		
		for(int i = 0; i < asteroids.length; i++){
			asteroids[i] = new Asteroids(2f,i);
		}
		//asteroid = new Asteroids(2f, 5f);
		playerObject.init(); //Call init method from Player class
		playerObject.input = gc.getInput(); //Init input class
		
	}

	@Override
	public void update(GameContainer gc, int delta) throws SlickException {
		//time += delta;
		
		//System.out.println(time);
		playerObject.update(); // Call update method from Player class
		for(int i = 0; i < asteroids.length; i++){
	    asteroids[i].update();
		}
	       
	}

	@Override
	public void render(GameContainer gc, Graphics g) throws SlickException
	{
		
		g.drawString("Hello World!", 250, 200);
		g.drawString("This is bad", 200,100);
	
		playerObject.render(playerObject.playerSprite);
		for(int i = 0; i < asteroids.length; i++){
			asteroids[i].render(asteroids[i].asteroidSprite);
		
		//asteroid.getCollisionBox(asteroid.asteroidSprite);
		
		//Collision detection
		if (asteroids[i].getCollisionBox(asteroids[i].asteroidSprite,10,10,-20,-20).intersects(playerObject.getCollisionBox(playerObject.playerSprite,5,5,-25,-25)))
		{
			System.out.println("COLLISION");
			
		}
		g.drawRect(asteroids[i].position.x+10, asteroids[i].position.y+10, asteroids[i].asteroidSprite.getWidth()-20, asteroids[i].asteroidSprite.getHeight()-20);
		g.drawRect(playerObject.position.x+5, playerObject.position.y+5, playerObject.playerSprite.getWidth()-25, playerObject.playerSprite.getHeight()-25);
		//playerObject.render();//Call render method from Player class
		}
	}

	public static void main(String[] args)
	{
		try
		{
			AppGameContainer appgc;
			appgc = new AppGameContainer(new Game("Simple Slick Game"));
			appgc.setDisplayMode(WIDTH, HEIGHT, false);
			appgc.setTargetFrameRate(60);
			appgc.start();
		}
		catch (SlickException ex)
		{
			Logger.getLogger(Game.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

	
	
}
