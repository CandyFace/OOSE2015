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

    //private AnimationSystem aniSys = new AnimationSystem();
    private Init init = new Init();

    Player playerObject = new Player();
    Asteroids[] asteroids = new Asteroids[10];
	public Projectile projectile;
    private int gameId;

 //   public Animation startupAnimation;
 //   public SpriteSheet shipSS;

    /**
     * @param gameId defines gamestate
     */
    public Game(int gameId) {
        this.gameId = gameId;

    }

    @Override
    public void init(GameContainer gc, StateBasedGame game) throws SlickException {
        this.game = game;
        for(int i = 0; i < asteroids.length; i++){
			asteroids[i] = new Asteroids(2f, i);
        }

        //aniSys.init(); //Call init method from AnimationSystem class
        playerObject.init(); //Call init method from Player class
        playerObject.input = gc.getInput(); //Init input class
        //aniSys.input = gc.getInput(); //init input class

    	projectile = new Projectile();
		projectile.input = gc.getInput(); //Init input class


//Unused sprite animation
/*        shipSS = new SpriteSheet("graphics/SpaceshipSheet.png", 64, 64);

        startupAnimation = new Animation();
        startupAnimation.addFrame(shipSS.getSprite(0, 0), 300);
        startupAnimation.addFrame(shipSS.getSprite(1, 0), 300);
        startupAnimation.addFrame(shipSS.getSprite(2, 0), 300);
        startupAnimation.addFrame(shipSS.getSprite(3, 0), 300);
        startupAnimation.addFrame(shipSS.getSprite(4, 0), 300);
        startupAnimation.addFrame(shipSS.getSprite(5, 0), 300);


        startupAnimation = new Animation(shipSS,
                new int[]{0,0, 1,0, 2,0 ,3,0 ,4,0 ,5,0}, // x, y position taken from SpriteSheet
                new int[]{300, 300, 300, 300, 300, 300}); // duration

*/
    }


    @Override
    public void update(GameContainer gc, StateBasedGame game, int delta) throws SlickException {

        playerObject.update(playerObject.playerSprite);
        projectile.update(playerObject.getPosition(), playerObject.getRotation());

        //aniSys.update(delta);

        if(!Menu.paused) {
            playerObject.update(); // Call update method from Player class
            for(int i = 0; i < asteroids.length; i++){
                asteroids[i].update(asteroids[i].asteroidSprite);
                asteroids[i].update();

                //Collision detection
                if (asteroids[i].getCollisionBox(asteroids[i].asteroidSprite,10,10,-20,-20).intersects(playerObject.getCollisionBox(playerObject.playerSprite,5,5,-25,-25)))
                {
                    //System.out.println("COLLISION");
                    init.isPlayerHit = true;

                }
            }
        }
        else
            Menu.paused = false;
    }

    @Override
    public void render(GameContainer gc, StateBasedGame game, Graphics g) throws SlickException
    {
        //aniSys.render();

        if (playerObject.playerLife <= 0) {
            //stop drawing player

        } else if(playerObject.playerLife > 0) {
            //Continue and check for hit

            if(init.isPlayerHit)
            {
                playerIsDMG(); //if hit, run through playerISDMG void

            }// else render playerSprite

            playerObject.render(playerObject.playerSprite);

        }// end of else if statement

        for(int i = 0; i < asteroids.length; i++){

            asteroids[i].render(asteroids[i].asteroidSprite);
            projectile.render();


            if(init.showDebugger) {
                g.drawRect(asteroids[i].position.x + 10, asteroids[i].position.y + 10, asteroids[i].asteroidSprite.getWidth() - 20, asteroids[i].asteroidSprite.getHeight() - 20);
                g.drawRect(playerObject.position.x + 5, playerObject.position.y + 5, playerObject.playerSprite.getWidth() - 25, playerObject.playerSprite.getHeight() - 25);
            }
          }

        g.drawString("HP: " + playerObject.playerLife, (Main.WIDTH / 3) - 100, (Main.HEIGHT / 3) - 100);
        g.drawString("Score: " + playerObject.score, (Main.WIDTH / 3) - 200, (Main.HEIGHT / 3) - 100);
        g.drawString("Protection Cooldown: " + Math.round(init.noDMGTimer*100.0)/100.0, (Main.WIDTH / 3) , (Main.HEIGHT / 3) - 100);


        //playerObject.render();//Call render method from Player class
    }

    public void playerIsDMG()
    {
        //System.out.println(init.isPlayerHit);
        if(playerObject.playerLife > 0)
        {
            if( init.noDMGTimer == 2.99) {
                playerObject.playerLife -= 1;
                System.out.println("player has taken a hit");
            }
            System.out.println("Cooldown: " + init.noDMGTimer);
        }

        if(playerObject.playerLife <= 3 && init.noDMGTimer <= 0)
        {

            init.noDMGTimer = 3.01; //reset noDMGTimer to 3
            init.isPlayerHit = false; //continues false until cooldown is gone and true.

        }
        init.noDMGTimer -= 0.01; //Counting down from 5 as soon as the void starts
    }

    //Each gamestate is defined by an ID, this return that that.
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
                game.enterState(Main.menu); //Changes state to menu
                Menu.paused = true;
                break;
            case Input.KEY_TAB:
                init.showDebugger = toggleDebugger(init.showDebugger);
                break;
            default:
                break;
        }
    }

    //Toggle method for switching between debugger states
    private boolean toggleDebugger(boolean showDebugger)
    {
        if(showDebugger) //if debugger is enabled, return false
            return false;
        else //vise versa...
            return true;
    }
}
