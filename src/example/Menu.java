package example;

import org.newdawn.slick.*;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;

public class Menu extends BasicGameState {

    /**
     * This class should only contain menu variables, functions etc..
     *
     */
    private StateBasedGame game;
    static boolean paused = false;
    private int gameId;
    public static boolean pressedESC = false;

    public Menu(int gameId) {
        this.gameId = gameId;

    }

    @Override
    public void init(GameContainer container, StateBasedGame game)
            throws SlickException {
        this.game = game;

    }

    @Override
    public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {
            g.setColor(Color.white);

        //If boolean paused is true
        // draw text in menu state
        if(paused)
        {
            g.drawString("GAME PAUSED", Main.WIDTH / 2.5f, (Main.HEIGHT - 60) / 2.5f);
            g.drawString("1. Continue", Main.WIDTH / 2.5f, Main.HEIGHT / 2.5f);
            //g.drawString("2. High Scores", Main.WIDTH / 2.5f, (Main.HEIGHT + 60) / 2.5f);
            g.drawString("ESC. Quit", Main.WIDTH / 2.5f, (Main.HEIGHT + 120) / 2.5f);
        }
        else if(Init.gameOver)
        {
            g.drawString("1. Start Over", Main.WIDTH / 2.5f, Main.HEIGHT / 2.5f);
            //g.drawString("2. High Scores", Main.WIDTH / 2.5f, (Main.HEIGHT + 60) / 2.5f);
            g.drawString("ESC. Quit", Main.WIDTH / 2.5f, (Main.HEIGHT + 120) / 2.5f);
        }
        else{
            g.drawString("GAME MENU", Main.WIDTH / 2.5f, (Main.HEIGHT - 60) / 2.5f);
            g.drawString("1. Play Game", Main.WIDTH / 2.5f, Main.HEIGHT / 2.5f);
            //g.drawString("2. High Scores", Main.WIDTH / 2.5f, (Main.HEIGHT + 60) / 2.5f);
            g.drawString("ESC. Quit", Main.WIDTH / 2.5f, (Main.HEIGHT + 120) / 2.5f);
        }

    }

    @Override
    public void update(GameContainer gc, StateBasedGame game, int delta)
            throws SlickException {

        //If boolean true
        //exit game
        if(pressedESC)
        {
            gc.exit();
        }
    }

    @Override
    public int getID() {

        return this.gameId;
    }

    public void keyReleased(int key, char c) {
        switch(key) {
            case Input.KEY_1:
                game.enterState(Main.game, new FadeOutTransition(Color.black), new FadeInTransition(Color.black));
                break;
            case Input.KEY_2:
                // TODO: Implement later
                break;
            case Input.KEY_ESCAPE:
                pressedESC = true;
                break;
            default:
                break;
        }
    }

}