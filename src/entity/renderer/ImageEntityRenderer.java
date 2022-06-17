package entity.renderer;

import core.game.Camera;
import entity.Entity;

import java.awt.*;
import java.awt.image.BufferedImage;

public class ImageEntityRenderer implements EntityRenderer {

    private Entity entity;
    private BufferedImage image;

    public ImageEntityRenderer(final Entity entity, final BufferedImage image) {
        this.entity = entity;
        this.image = image;
    }

    @Override
    public void draw(Graphics2D g2D, Camera camera) {

        g2D.drawImage(image, entity.getX() - camera.getX(), entity.getY() - camera.getY(), null);
    }
}
