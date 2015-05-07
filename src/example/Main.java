package example;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;


public class Main extends StateBasedGame
{
    public static final int menu = 1;
    public static final int game = 2;
    public static final int over = 4;
    public static final int highScore = 3;

	static int WIDTH = 640;
	static int HEIGHT = 480;

    public Main(String name) {
        super(name);
        addState(new Menu(menu));
        addState(new Game(game));
        addState(new GameOver(over));
        enterState(menu);
    }


    public void initStatesList(GameContainer gc) throws SlickException {
        getState(menu).init(gc, this);
        getState(game).init(gc, this);
    }

	public static void main(String[] args)
	{
		try
		{
			AppGameContainer appgc;
			appgc = new AppGameContainer(new Main("AvoidAsteroid"));
			appgc.setDisplayMode(WIDTH, HEIGHT, false);
			appgc.setTargetFrameRate(60);
			appgc.start();
		}
		catch (SlickException ex)
		{
			Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
		}
	}
	
	
}
