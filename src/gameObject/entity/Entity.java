package gameObject.entity;

import animation.Direction;
import core.game.Camera;
import gameObject.CollisionArea;
import gameObject.GameObject;
import gameObject.renderer.DefaultEntityRenderer;
import gameObject.renderer.EntityRenderer;
import main.GamePanel;

import java.awt.Graphics2D;

public abstract class Entity extends GameObject {

    private CollisionArea collisionArea;
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

    public CollisionArea getCollisionArea() {
        return collisionArea;
    }

    public boolean intersects(final Entity entity) {
        return getCollisionArea().intersects(entity.getCollisionArea());
    }

    public boolean intersects(final MovableEntity movableEntity, final Direction movingDirection) {
        return getCollisionArea().intersects(movableEntity.getCollisionAreaAfterMoving(movingDirection));
    }


    public void update() {}

    public void draw(Graphics2D g2D, Camera camera) {
        entityRenderer.draw(g2D, camera);

        if (collisionArea != null && GamePanel.debug) {
            collisionArea.draw(g2D, camera);
        }
    }

    public void setCollisionArea(final CollisionArea collisionArea) {
        this.collisionArea = collisionArea;
        this.addPositionChangeListener(collisionArea);
    }

}