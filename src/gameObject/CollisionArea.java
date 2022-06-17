package gameObject;

import core.game.Camera;

import java.awt.*;

public class CollisionArea extends GameObject implements GameObjectPositionChangeListener {

    private final Color drawFillColor = Color.CYAN;

    public CollisionArea(int x, int y, int width, int height) {
        super(x, y, width, height);

    }

    // TODO: METHODE TEMPLATE.
    public boolean intersects(final CollisionArea collisionBox) {

        boolean collision = false;



        return collision;
    }

    public int getXRight() {
        return x + width;
    }

    public int getYBottom() {
        return y + height;
    }

    @Override
    public void positionChanged(GameObject gameObject, int deltaX, int deltaY) {
        translate(deltaX, deltaY);
    }

    public void draw(Graphics2D g2D, Camera camera) {

        g2D.setColor(drawFillColor);
        g2D.drawRect(x - camera.getX(), y - camera.getY(), width, height);

    }
}
