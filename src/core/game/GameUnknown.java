package core.game;

import gameState.GameState;
import gameState.GameStateID;
import gameState.GameStatePauseState;
import gameState.GameStatePlayState;

import java.awt.event.MouseEvent;

public class GameUnknown extends GameBase {

    @Override
    public void init() {

        // DEFINE GAMESTATES
        final GameState playState = new GameStatePlayState(gameStateManager, GameStateID.PLAY);
        final GameState pauseState = new GameStatePauseState(gameStateManager, GameStateID.PAUSE);

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

    @Override public void mouseDragged(MouseEvent e) { gameStateManager.mouseDragged(e); }

    @Override public void mouseMoved(MouseEvent e) { gameStateManager.mouseMoved(e); }

    @Override public void mouseEntered(MouseEvent e) { gameStateManager.mouseEntered(e); }

    @Override public void mouseExited(MouseEvent e) { gameStateManager.mouseExited(e); }

}
