package entity;

import core.game.Camera;
import entity.renderer.DefaultEntityRenderer;
import entity.renderer.EntityRenderer;

import java.awt.*;

public abstract class Entity extends GameObject {

    private EntityRenderer entityRenderer;

    public Entity(int x, int y, int width, int height) {
        super(x, y, width, height);

        setEntityRenderer(new DefaultEntityRenderer(this));
    }

    public void setEntityRenderer(final EntityRenderer entityRenderer) {
        if (entityRenderer != null) {
            this.entityRenderer = entityRenderer;
        }
    }

    public void update() {}

    public void draw(Graphics2D g2D, Camera camera) {
        entityRenderer.draw(g2D, camera);
    }


}
