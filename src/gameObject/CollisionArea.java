package gameObject;

import core.game.Camera;

import java.awt.Graphics2D;
import java.awt.Color;

public class CollisionArea extends GameObject implements GameObjectPositionChangeListener {

    private final Color drawFillColor = Color.CYAN;

    public CollisionArea(int x, int y, int width, int height) {
        super(x, y, width, height);
    }

    public boolean intersects(final CollisionArea collisionArea) {

        boolean conditionLeft = this.getX() < collisionArea.getXRight();
        boolean conditionRight = this.getXRight() > collisionArea.getX();
        boolean conditionTop = this.getY() < collisionArea.getYBottom();
        boolean conditionBottom = this.getYBottom() > collisionArea.getY();

        return conditionLeft && conditionRight && conditionTop && conditionBottom;
    }

    @Override
    public void positionChanged(GameObject gameObject, int deltaX, int deltaY) {
        translate(deltaX, deltaY);
    }

    public void draw(Graphics2D g2D, Camera camera) {

        g2D.setColor(drawFillColor);
        g2D.drawRect(x - camera.getX(), y - camera.getY(), width, height);

    }

    @Override
    public String getClassNameForToString() {
        return "CollisionArea";
    }
}
