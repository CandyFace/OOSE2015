package example;

import org.newdawn.slick.*;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

/**
 * Created by CandyFace on 01/04/15.
 *
 * SimpleSlickGame has been splitted into Main and Game
 * use this class for future implementations
 */

public class Game extends BasicGameState {

    private StateBasedGame game;

    Player playerObject = new Player();
    public Asteroids asteroid;
	public Projectile projectile;
    private int gameId;

    public Animation startupAnimation;
    public SpriteSheet shipSS;

    /**
     * @param gameId defines gamestate
     */
    public Game(int gameId) {
        this.gameId = gameId;

    }

    @Override
    public void init(GameContainer gc, StateBasedGame game) throws SlickException {
        this.game = game;
        asteroid = new Asteroids(2f, 5f);
        playerObject.init(); //Call init method from Player class
        playerObject.input = gc.getInput(); //Init input class

    	projectile = new Projectile();
		projectile.input = gc.getInput(); //Init input class

        shipSS = new SpriteSheet("graphics/SpaceshipSheet.png", 64, 64);

        startupAnimation = new Animation();
   /*     startupAnimation.addFrame(shipSS.getSprite(0, 0), 300);
        startupAnimation.addFrame(shipSS.getSprite(1, 0), 300);
        startupAnimation.addFrame(shipSS.getSprite(2, 0), 300);
        startupAnimation.addFrame(shipSS.getSprite(3, 0), 300);
        startupAnimation.addFrame(shipSS.getSprite(4, 0), 300);
        startupAnimation.addFrame(shipSS.getSprite(5, 0), 300);
        */

        startupAnimation = new Animation(shipSS,
                new int[]{0,0, 1,0, 2,0 ,3,0 ,4,0 ,5,0}, // x, y position taken from SpriteSheet
                new int[]{300, 300, 300, 300, 300, 300}); // duration



    }


    @Override
    public void update(GameContainer gc, StateBasedGame game, int delta) throws SlickException {
        playerObject.update(playerObject.playerSprite);
        asteroid.update(asteroid.asteroidSprite);
        projectile.update(playerObject.getPosition(), playerObject.getRotation());

        startupAnimation.update(delta);

        if(!Menu.paused) {
            playerObject.update(); // Call update method from Player class
            asteroid.update();
        }
        else
            Menu.paused = false;
    }

    @Override
    public void render(GameContainer gc, StateBasedGame game, Graphics g) throws SlickException
    {
        startupAnimation.draw(100,100);
        playerObject.render(playerObject.playerSprite);
        asteroid.render(asteroid.asteroidSprite);

        g.drawString("Score: " + playerObject.score, (Main.WIDTH / 3) - 200, (Main.HEIGHT / 3) - 100);

        projectile.render();
        //playerObject.render();//Call render method from Player class
    }

    @Override
    public int getID()
    {
        return this.gameId;
    }

    /**
     *
     * @param key defines that a key is needed.
     * @param c expects only one char
     */
    public void keyReleased(int key, char c) {
        switch(key) {
            case Input.KEY_ESCAPE:
                game.enterState(Main.menu); //Goto main when pressing ESC
                Menu.paused = true;
                break;
            default:
                break;
        }
    }

}
