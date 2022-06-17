package entity.renderer;

import core.game.Camera;
import entity.Entity;

import java.awt.*;

public class DefaultEntityRenderer implements EntityRenderer {

    private final Color defaultFillColor = Color.MAGENTA;
    private final Entity entity;

    public DefaultEntityRenderer(final Entity entity) {
        this.entity = entity;
    }

    @Override
    public void draw(Graphics2D g2D, Camera camera) {
        g2D.setColor(defaultFillColor);
        g2D.fillRect(entity.getX() - camera.getX(), entity.getY() - camera.getY(), entity.getWidth(), entity.getHeight());
    }
}
