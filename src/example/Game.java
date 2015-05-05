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
    Asteroids[] asteroids = new Asteroids[10];
  //public Asteroids asteroid;
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
        //asteroid = new Asteroids(2f, 5f);
        for(int i = 0; i < asteroids.length; i++){
			asteroids[i] = new Asteroids(2f, i);
        }
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
        //asteroid.update(asteroid.asteroidSprite);
        projectile.update(playerObject.getPosition(), playerObject.getRotation());

        startupAnimation.update(delta);

        if(!Menu.paused) {
            playerObject.update(); // Call update method from Player class
           // asteroid.update();
            for(int i = 0; i < asteroids.length; i++){
                asteroids[i].update(asteroids[i].asteroidSprite);
                }
        }
        else
            Menu.paused = false;
    }

    @Override
    public void render(GameContainer gc, StateBasedGame game, Graphics g) throws SlickException
    {
        startupAnimation.draw(100,100);
        playerObject.render(playerObject.playerSprite);
      //  asteroid.render(asteroid.asteroidSprite);

        for(int i = 0; i < asteroids.length; i++){

            asteroids[i].render(asteroids[i].asteroidSprite);
          //Collision detection

          if (asteroids[i].getCollisionBox(asteroids[i].asteroidSprite,10,10,-20,-20).intersects(playerObject.getCollisionBox(playerObject.playerSprite,5,5,-25,-25)))
          {
          System.out.println("COLLISION");
          }
          g.drawRect(asteroids[i].position.x+10, asteroids[i].position.y+10, asteroids[i].asteroidSprite.getWidth()-20, asteroids[i].asteroidSprite.getHeight()-20);
          g.drawRect(playerObject.position.x+5, playerObject.position.y+5, playerObject.playerSprite.getWidth()-25, playerObject.playerSprite.getHeight()-25);
          }

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
