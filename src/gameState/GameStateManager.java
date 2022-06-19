package gameState;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.util.HashMap;
import java.util.Map;

public final class GameStateManager {

    private Map<GameStateID, GameState> gameStates;
    private GameState currentGameState;

    public GameStateManager() {
        gameStates = new HashMap<GameStateID, GameState>();
    }

    private void executeTransition(final GameState gameState) {

        if (gameState != null) {

            // CALL 'EXIT' METHOD ON CURRENTLY CLOSED GAMESTATE.
            if (currentGameState != null) {
                currentGameState.exit();
            }

            // SET NEW GAMESTATE.
            currentGameState = gameState;
            currentGameState.init();
            currentGameState.entered();
        }
    }

    public void changeTo(final GameStateID gameStateID) {
        changeTo(gameStates.get(gameStateID));
    }

    public void changeTo(final GameState gameState) {
        executeTransition(gameState);
    }

    public GameState getCurrentGameState() {
        return currentGameState;
    }

    public boolean isCurrentGameStateConfigured() {
        return currentGameState != null;
    }

    public final void add(final GameState gameState) {
        if (gameState != null) {
            gameStates.put(gameState.getGameStateID(), gameState);
        }
    }

    public final void remove(final GameState gameState) {
        if (gameState != null && gameStates.containsKey(gameState.getGameStateID())) {
            gameStates.remove(gameState);
        }
    }

    public void mouseDragged(MouseEvent e) {
        if (isCurrentGameStateConfigured()) {
            currentGameState.mouseDragged(e);
        }
    }

    public void mouseMoved(MouseEvent e) {
        if (isCurrentGameStateConfigured()) {
            currentGameState.mouseMoved(e);
        }
    }

    public void keyPressed(final int keyCode) {
        if (isCurrentGameStateConfigured()) {
            currentGameState.keyPressed(keyCode);
        }
    }

    public void keyReleased(final int keyCode) {
        if (isCurrentGameStateConfigured()) {
            currentGameState.keyReleased(keyCode);
        }
    }

    public void update() {
        if (isCurrentGameStateConfigured()) {
            currentGameState.update();
        }
    }

    public void draw(Graphics2D g2D) {
        if (isCurrentGameStateConfigured()) {
            currentGameState.draw(g2D);
        }
    }
}
