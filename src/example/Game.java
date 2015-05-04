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


    /**
     * @param gameId
     * defines gamestate
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
        
    }


    @Override
    public void update(GameContainer gc, StateBasedGame game, int delta) throws SlickException {
        //time += delta;

        //System.out.println(time);
        playerObject.update(playerObject.playerSprite);
        asteroid.update(asteroid.asteroidSprite);
        projectile.update(playerObject.getPosition(), playerObject.getRotation());
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
     * @param key
     * @param c
     * returns gameState to menu
     */
    public void keyReleased(int key, char c) {
        switch(key) {
            case Input.KEY_ESCAPE:
                game.enterState(Main.menu);
                Menu.paused = true;
                break;
            default:
                break;
        }
    }

}
