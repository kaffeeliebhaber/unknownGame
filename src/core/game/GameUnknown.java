package core.game;

import gameState.GameState;
import gameState.GameStateID;
import gameState.GameStatePauseState;
import gameState.GameStatePlayState;

public class GameUnknown extends GameBase {

    @Override
    public void init() {

        // DEFINE GAMESTATES
        final GameState playState = new GameStatePlayState(gameStateManager, GameStateID.PLAY, this);
        final GameState pauseState = new GameStatePauseState(gameStateManager, GameStateID.PAUSE, this);

        // ADD GAME STATES TO OUR GAMESTATESMANAGER.
        gameStateManager.add(playState);
        gameStateManager.add(pauseState);

        // CHANGE GAME TO STATE 'PLAYSTATE'.
        gameStateManager.changeTo(playState);
    }

    @Override public void keyPressed(final int keyCode) {
        gameStateManager.keyPressed(keyCode);
    }

    @Override public void keyReleased(final int keyCode) {
        gameStateManager.keyReleased(keyCode);
    }

}
