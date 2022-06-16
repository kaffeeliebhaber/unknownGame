package gameState;

import java.awt.*;

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
