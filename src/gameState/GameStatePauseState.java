package gameState;

import core.game.Game;

public class GameStatePauseState extends GameState {

    private final Game game;

    public GameStatePauseState(GameStateManager gameStateManager, GameStateID gameStateID, final Game game) {
        super(gameStateManager, gameStateID);

        this.game = game;
    }

    @Override
    public void update() {

        if (game.getKeyHandler().enterPressed) {
            this.gameStateManager.changeTo(GameStateID.PLAY);
        }

    }

}
