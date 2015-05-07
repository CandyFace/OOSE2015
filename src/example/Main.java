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
    public static AppGameContainer appgc;

    static int WIDTH = 640; // Width the window
	static int HEIGHT = 480; // Height of the window

    public Main(String name) {
        super(name);
        addState(new Menu(menu));
        addState(new Game(game));
        addState(new GameOver(over));
        enterState(menu);
    }

    /**
     *
     * @param gc
     * @throws SlickException
     * Initializes gamestates
     */
    public void initStatesList(GameContainer gc) throws SlickException {
        getState(menu).init(gc, this);
        getState(game).init(gc, this);
        getState(over).init(gc, this);
    }

	public static void main(String[] args)
	{
		try
		{
			appgc = new AppGameContainer(new Main("AvoidStroid"));
			appgc.setDisplayMode(WIDTH, HEIGHT, false);
			appgc.setTargetFrameRate(60);
            appgc.setShowFPS(false);
            appgc.start();
		}
		catch (SlickException ex)
		{
			Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
		}
	}
	
	
}
