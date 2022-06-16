package entity;

import core.game.Camera;
import core.game.Game;

import gfx.BufferedImageHelper;
import gfx.ImageLoader;
import main.GamePanel;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Player extends MovableEntity {

    private final Game game;
    private BufferedImage image;

    public Player(final Game game) {

        super(100, 100, GamePanel.TILE_SIZE, GamePanel.TILE_SIZE, 3);
        this.game = game;

        this.getImage();
    }

    private void getImage() {
        image = BufferedImageHelper.scale(ImageLoader.loadImage("player/player.png"), GamePanel.TILE_SIZE, GamePanel.TILE_SIZE);
    }

    public void draw(Graphics2D g2d, final Camera camera) {

        //System.out.println("PX: " + x + ", PY: " + y + " | CX: " + camera.getX() + ", CY: " + camera.getY());
        g2d.drawImage(image, x - camera.getX(), y - camera.getY(), null);

    }
}
