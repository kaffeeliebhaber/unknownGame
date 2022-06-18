package gameObject;

import math.Vector;

import java.util.LinkedList;
import java.util.List;

public abstract class GameObject {

    private List<GameObjectPositionChangeListener> gameObjectPositionChangeListeners;
    protected int x, y;
    protected int width, height;
    protected boolean active;

    public GameObject(int x, int y, int width, int height) {
        setX(x);
        setY(y);
        setWidth(width);
        setHeight(height);

        gameObjectPositionChangeListeners = new LinkedList<GameObjectPositionChangeListener>();
    }

    protected void setX(int x) {
        this.x = x;
    }

    protected void setY(int y) {
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

    public int getXRight() {
        return x + width;
    }

    public int getYBottom() {
        return y + height;
    }

    public void translateX(int deltaX) {

        setX(x + deltaX);

        notifyPositionChangeListeners(deltaX, 0);
    }

    public void translateY(int deltaY) {

        setY(y + deltaY);

        notifyPositionChangeListeners(0, deltaY);
    }

    public void translate(int deltaX, int deltaY) {

        setX(x + deltaX);
        setY(y + deltaY);

        notifyPositionChangeListeners(deltaX, deltaY);
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

    private void notifyPositionChangeListeners(int deltaX, int deltaY) {
        gameObjectPositionChangeListeners.stream().forEach(l -> l.positionChanged(this, deltaX, deltaY));
    }

    public void addPositionChangeListener(final GameObjectPositionChangeListener l) {
        gameObjectPositionChangeListeners.add(l);
    }

    public void removePositionChangeListener(final GameObjectPositionChangeListener l) {
        gameObjectPositionChangeListeners.remove(l);
    }

    @Override
    public String toString() {
        return getClassNameForToString() + " [x=" + x + ", y=" + y + ", width=" + width + ", height=" + height + "]";
    }

    public String getClassNameForToString() {
        return "GameObject";
    }

}