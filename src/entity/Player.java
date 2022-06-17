package entity;

import core.game.Game;

import main.GamePanel;

public class Player extends MovableEntity {

    public Player(final Game game) {

        super(100, 100, GamePanel.TILE_SIZE, GamePanel.TILE_SIZE, 3);
    }

}
