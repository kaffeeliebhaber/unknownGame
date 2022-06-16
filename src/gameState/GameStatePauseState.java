package gameState;

import core.game.Game;

import java.awt.event.KeyEvent;

public class GameStatePauseState extends GameState {

    private final Game game;

    public GameStatePauseState(GameStateManager gameStateManager, GameStateID gameStateID, final Game game) {
        super(gameStateManager, gameStateID);

        this.game = game;
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
