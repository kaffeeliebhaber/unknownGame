package gameState;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class GameStateManager {

    // TODO: MAYBE CHANGE FROM 'LIST' TO 'MAP'.
    private Map<GameStateID, GameState> gameStates;
    private GameState currentGameState;

    public GameStateManager() {
        gameStates = new HashMap<GameStateID, GameState>();
    }

    private void executeTransition(final GameState gameState) {

        System.out.println("SET GAMESTATE TO: " + gameState.getGameStateID());

        if (gameState != null) {

            // CALL 'EXIT' METHOD ON CURRENTLY CLOSED GAMESTATE.
            if (currentGameState != null) {

                System.out.println("GAMESTATE (" + currentGameState.getGameStateID() + ") WILL BE EXIT.");
                currentGameState.exit();
            }

            // SET NEW GAMESTATE.
            currentGameState = gameState;
            currentGameState.init();
            currentGameState.entered();

            System.out.println("GAMESTATE (" + currentGameState.getGameStateID() +") WILL BE INIT AND ENTERED.");
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
