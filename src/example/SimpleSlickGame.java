package example;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.vecmath.Vector2f;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class SimpleSlickGame extends BasicGame
{
	
	public Image playerSprite;
	public float direction;
	Player playerObject = new Player();
	public Asteroids asteroid;

	
	public SimpleSlickGame(String gamename)
	{
		super(gamename);
	}

	@Override
	public void init(GameContainer gc) throws SlickException {
		
		playerObject.position = new Vector2f();
		playerObject.angle = new Vector2f();
		playerObject.input = gc.getInput(); //Init input class
		playerSprite = new Image("graphics/playerWhite.png");
		playerSprite.setCenterOfRotation(playerSprite.getWidth() * playerObject.scale / 2, playerSprite.getHeight() * playerObject.scale / 2); //Set the origin of the player sprite
		
		 asteroid = new Asteroids(5f,5f);
	}

	@Override
	public void update(GameContainer gc, int i) throws SlickException {
		playerObject.playerMovement();
		
		direction = playerSprite.getRotation();
		
		playerObject.angle.x = (float) Math.cos(Math.toRadians(direction-90));
	    playerObject.angle.y = (float) Math.sin(Math.toRadians(direction-90));
	    
	    asteroid.move();
	    System.out.println(asteroid.rotation);
	    
	    
	}

	@Override
	public void render(GameContainer gc, Graphics g) throws SlickException
	{
		g.drawString("Hello World!", 250, 200);
		g.drawString("This is bad", 200,100);

		asteroid.render();
		
		playerSprite.rotate(playerObject.rotation);
		playerSprite.draw(playerObject.position.x,playerObject.position.y,playerObject.scale); //Draw the player
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
