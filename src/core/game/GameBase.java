package core.game;

import gameState.GameStateManager;

import java.awt.*;

public abstract class GameBase implements Game {

    protected final GameStateManager gameStateManager;

    public GameBase() {
        gameStateManager = new GameStateManager();
    }

    public void keyPressed(final int keyCode) {}

    public void keyReleased(final int keyCode) {}

    public void update() {
        gameStateManager.update();
    }

    public void draw(Graphics2D g2D) {
        gameStateManager.draw(g2D);
    }

}
