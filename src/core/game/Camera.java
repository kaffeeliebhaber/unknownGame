package core.game;

import entity.Entity;
import entity.GameObject;
import entity.PositionChangeListener;
import math.Vector;

import java.awt.*;

public class Camera extends GameObject implements PositionChangeListener {

    private final Dimension gameDimension;
    private Entity currentEntity;

    public Camera(int startX, int startY, int cameraWidth, int cameraHeight, Dimension gameDimension) {
        super(startX, startY, cameraWidth, cameraHeight);

        this.gameDimension = gameDimension;
    }

    // Ausrichtung der x-Ausprägung nach links und rechts.
    // Befindet sich die Kamera von der Ausprägung am linken oder rechten Rand des Spiels, werden die X-Ausprägungen auf 0 oder (Spielfeldbreit - Sichtbreit) gesetzt.
    private int alignX(int x) {

        if (x < 0) {
            x = 0;
        } else if (x > gameDimension.width - width) {
            x = gameDimension.width - width;
        }

        return x;
    }

    // Ausrichtung der y-Ausprägung nach oben und unten.
    // Befindet sich die Kamera von der Ausprägung am oberen oder unteren Rand des Spiels, werden die y-Ausprägungen auf 0 oder (Spielfeldhöhe - Sichthöhe) gesetzt.
    private int alignY(int y) {

        if (y < 0) {
            y = 0;
        } else if (y > gameDimension.height - height) {
            y = gameDimension.height - height;
        }

        return y;
    }

    /*
     * Das übergebene Spielobjekt wird als Ankerpunkt gesetzt.
     * Die Kamera entfernt sich als Listener dem noch hinterlegten Spielobjekt.
     * Anschließend wird das neue Spielobjekt gesetzt.
     * Die Kamera registriert sich als Listener dem neuen Spielobjekt.
     */
    public void focusOn(final Entity entity) {

        if (entity != null && this.currentEntity != entity) {

            removePositionChangeListener();
            setEntity(entity);
            centerOnGameObject(this.currentEntity);
        }
    }

    // Entfernt die Kamera als GameObjektListener aus dem aktuellen Spielobjekt (Ankerobjekt).
    private void removePositionChangeListener() {
        if (currentEntity != null) {
            currentEntity.removePositionChangeListener(this);
        }
    }

    // Setzt das übergebene Spielobjekt als aktuelles Ankerobjekt.
    private void setEntity(final Entity entity) {
        this.currentEntity = entity;
        this.currentEntity.addPositionChangeListener(this);
    }

    // Die Kamera wird auf das übergebene Spielobjekt gesetzt.
    private void centerOnGameObject(final Entity entity) {

        //System.out.println("ENTERED");

        // Mittelpunkt des <GameObjects>.
        final Vector entityCenter = entity.getCenterPosition();

        //System.out.println("EntityCenterPosition: " + entityCenter);

        // Camera Position (TOP-LEFT)
        int cX = (int) (entityCenter.x - width * 0.5f);
        int cY = (int) (entityCenter.y - height * 0.5f);

        //System.out.println("BEFORE: CameraX: " + cX + ", CameraY: " + cY);

        // Ausrichten der x-Position | Prüfen, ob die Grenzen erreicht wurden.
        setX(alignX(cX));

        // Ausrichten der y-Position | Prüfen, ob die Grenzen erreicht wurden.
        setY(alignY(cY));

        //System.out.println("(Camera.centerOnGameObject): CameraX: " + cX + ", CameraY: " + cY + ", x: " + x + ", y: " + y);
    }


    @Override
    public void positionChanged(Entity e, int oldX, int oldY, int newX, int newY) {
        centerOnGameObject(e);
    }
}
