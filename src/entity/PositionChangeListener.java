package entity;

public interface PositionChangeListener {

    void positionChanged(Entity e, int oldX, int oldY, int newX, int newY);

}
