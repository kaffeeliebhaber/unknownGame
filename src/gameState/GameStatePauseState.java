package gameState;

import core.game.Game;

import java.awt.event.KeyEvent;

public class GameStatePauseState extends GameState {

    public GameStatePauseState(GameStateManager gameStateManager, GameStateID gameStateID) {
        super(gameStateManager, gameStateID);
    }

    @Override
    public void keyPressed(final int keyCode) {

        if (keyCode == KeyEvent.VK_ENTER) {
            gameStateManager.changeTo(GameStateID.PLAY);
        }

    }

    @Override
    public void update() {

    }

}
