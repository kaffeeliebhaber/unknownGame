package entity;

import core.game.Camera;
import core.game.Game;
import gfx.BufferedImageHelper;
import gfx.ImageLoader;
import main.GamePanel;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Dummy extends MovableEntity {

    private BufferedImage image;

    public Dummy(int x, int y, int width, int height, int movingSpeed) {
        super(x, y, width, height, movingSpeed);

        image = BufferedImageHelper.scale(ImageLoader.loadImage("player/player.png"), GamePanel.TILE_SIZE, GamePanel.TILE_SIZE);
    }


    public void draw(Graphics2D g2d, final Camera camera) {
        g2d.drawImage(image, x - camera.getX(), y - camera.getY(), null);
    }
}
