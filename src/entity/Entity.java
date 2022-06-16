package entity;

import core.game.Camera;

import java.awt.*;
import java.util.LinkedList;
import java.util.List;

public abstract class Entity extends GameObject {

    private List<PositionChangeListener> positionChangeListeners;

    public Entity(int x, int y, int width, int height) {
        super(x, y, width, height);

        positionChangeListeners = new LinkedList<PositionChangeListener>();
    }


    public void translateX(int deltaX) {

        notifyPositionChangeListeners(x + deltaX, y);

        x += deltaX;
    }

    public void translateY(int deltaY) {

        notifyPositionChangeListeners(x, y + deltaY);

        y += deltaY;
    }

    public void translate(int deltaX, int deltaY) {

        notifyPositionChangeListeners(x + deltaX, y + deltaY);

        x += deltaY;
        y += deltaY;
    }

    public void update() {}

    public void draw(Graphics2D g2D, Camera camera) {}

    private void notifyPositionChangeListeners(int newX, int newY) {
        positionChangeListeners.stream().forEach(l -> l.positionChanged(this, x, y, newX, newY));
    }

    public void addPositionChangeListener(final PositionChangeListener l) {
        positionChangeListeners.add(l);
    }

    public void removePositionChangeListener(final PositionChangeListener l) {
        positionChangeListeners.remove(l);
    }
}
