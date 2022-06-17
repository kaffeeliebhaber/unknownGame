package entity;

import math.Vector;

import java.util.LinkedList;
import java.util.List;

public abstract class GameObject {

    private List<PositionChangeListener> positionChangeListeners;
    protected int x, y;
    protected int width, height;
    protected boolean active;

    public GameObject(int x, int y, int width, int height) {
        setX(x);
        setY(y);
        setWidth(width);
        setHeight(height);

        positionChangeListeners = new LinkedList<PositionChangeListener>();
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
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

    public Vector getCenterPosition() {
        return new Vector((int) (x + width * 0.5f), (int) (y + height * 0.5f));
    }

    public boolean isActive() {
        return active;
    }

    protected void active() {
        active = true;
    }

    protected void deactive() {
        active = false;
    }

    private void notifyPositionChangeListeners(int newX, int newY) {
        positionChangeListeners.stream().forEach(l -> l.positionChanged(this, x, y, newX, newY));
    }

    public void addPositionChangeListener(final PositionChangeListener l) {
        positionChangeListeners.add(l);
    }

    public void removePositionChangeListener(final PositionChangeListener l) {
        positionChangeListeners.remove(l);
    }

    @Override
    public String toString() {
        return "GameObject [x=" + x + ", y=" + y + ", width=" + width + ", height=" + height + "]";
    }

}