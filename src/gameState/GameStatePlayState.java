package gameState;

import core.game.Game;
import entity.Player;

import java.awt.*;

public class GameStatePlayState extends GameState {

    private final Game game;
    private Player player;

    public GameStatePlayState(GameStateManager gameStateManager, GameStateID gameStateID, final Game game) {
        super(gameStateManager, gameStateID);

        this.game = game;
    }

    public void init() {

        // CREATE PLAYER
        player = new Player(game);

    }

    public void update() {

        if (player != null) {
            player.update();
        }

        if (game.getKeyHandler().enterPressed) {
            this.gameStateManager.changeTo(GameStateID.PAUSE);
        }

    }

    public void draw(Graphics2D g2D) {
        if (player != null) {
            player.draw(g2D);
        }
    }
}
