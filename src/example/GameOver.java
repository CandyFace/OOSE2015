package example;

import org.newdawn.slick.*;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

/**
 * Created by CandyFace on 07/05/15.
 */
public class GameOver extends BasicGameState {

    private StateBasedGame game;
    private int gameId;

    public GameOver(int gameId) {
        this.gameId = gameId;
    }

    @Override
    public void init(GameContainer container, StateBasedGame game) throws SlickException {
        this.game = game;
    }

    @Override
    public void render(GameContainer container, StateBasedGame game, Graphics g)
            throws SlickException {
        g.setColor(Color.white);
        g.drawString("GAME OVER", (Main.WIDTH + 10) / 2.5f, (Main.HEIGHT - 60) / 2.5f);
        g.drawString("Your final score: " + Game.pointCount, (Main.WIDTH - 100) / 2.5f, (Main.HEIGHT) / 2.5f);
        g.drawString("Press ESC to try again ", (Main.WIDTH - 130) / 2.5f, (Main.HEIGHT + 100) / 2.5f);

    }

    @Override
    public void update(GameContainer gc, StateBasedGame game, int delta)
            throws SlickException {
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
                break;
            default:
                break;
        }
    }

    /**
     * @return this.gameId;
     */
    @Override
    public int getID() {

        return this.gameId;
    }
}
