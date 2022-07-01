package gameState;

import java.awt.Graphics2D;
import java.awt.event.MouseEvent;

public abstract class GameState {

    protected final GameStateManager gameStateManager;
    private final GameStateID gameStateID;

    public GameState(final GameStateManager gameStateManager, final GameStateID gameStateID) {
        this.gameStateManager = gameStateManager;
        this.gameStateID = gameStateID;
    }

    public final GameStateID getGameStateID() {
        return gameStateID;
    }

    public void init() {}

    public void entered() {}

    public void exit() {}

    public void keyPressed(final int keyCode) {}

    public void keyReleased(final int keyCode) {}

    public void mouseDragged(MouseEvent e) {}

    public void mouseMoved(MouseEvent e) {}

    public void mouseEntered(MouseEvent e) {}

    public void mouseExited(MouseEvent e) {}

    public void update() {}

    public void draw(Graphics2D g2D) {}

    @Override
    public boolean equals(Object o) {

        if (!(o instanceof GameState)) {
            return false;
        }

        GameState gameStateToCheck = (GameState) o;

        return gameStateToCheck.getGameStateID() == gameStateID;
    }
}
