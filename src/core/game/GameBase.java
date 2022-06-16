package core.game;

import gameState.GameStateManager;
import handler.KeyHandler;

import java.awt.*;

public abstract class GameBase implements Game {

    protected final GameStateManager gameStateManager;
    protected KeyHandler keyHandler;

    public GameBase() {
        gameStateManager = new GameStateManager();
    }

    public void setKeyHandler(final KeyHandler keyHandler) {
        this.keyHandler = keyHandler;
    }

    public KeyHandler getKeyHandler() {
        return keyHandler;
    }

    public void update() {
        gameStateManager.update();
    }

    public void draw(Graphics2D g2D) {
        gameStateManager.draw(g2D);
    }

}
