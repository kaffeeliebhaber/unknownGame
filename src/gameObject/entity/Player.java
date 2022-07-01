package gameObject.entity;

import animation.AnimationID;
import animation.AnimationPlayer;
import core.game.Camera;

import java.awt.Graphics2D;

public class Player extends MovableEntity {

    private AnimationPlayer animationPlayer;

    public Player(final int x, final int y, final int width, final int height, final int movingSpeed) {
        super(x, y, width, height, movingSpeed);
    }

    public void setAnimationPlayer(final AnimationPlayer animationPlayer) {
        this.animationPlayer = animationPlayer;
    }

    public void update() {

        super.update();

        updateAnimationPlayer();

    }

    private void updateAnimationPlayer() {

        if (!hasAnimationPlayer()) return;

        // UP
        if (isMoveUp() && canMoveUp()) {
            animationPlayer.setAnimation(AnimationID.UP);
        }

        // LEFT
        if (isMoveLeft() && canMoveLeft()) {
            animationPlayer.setAnimation(AnimationID.LEFT);
        }

        // RIGHT
        if (isMoveRight() && canMoveRight()) {
            animationPlayer.setAnimation(AnimationID.RIGHT);
        }

        // DOWN
        if (isMoveDown() && canMoveDown()) {
            animationPlayer.setAnimation(AnimationID.DOWN);
        }

        if (!isMoving()) {
            if (animationPlayer.getCurrentAnimation().getAnimationID() == AnimationID.UP) {
                animationPlayer.setAnimation(AnimationID.IDLE_UP);
            } else {
                animationPlayer.setAnimation(AnimationID.IDLE_DOWN);
            }

        }

        animationPlayer.update();
    }

    private boolean hasAnimationPlayer() {
        return animationPlayer != null;
    }

    public void draw(Graphics2D g2D, Camera camera) {

        if (!hasAnimationPlayer()) {
            super.draw(g2D, camera);
        } else {
            g2D.drawImage(animationPlayer.getImage(), getX() - camera.getX(), getY() - camera.getY(), null);
        }

    }

    public String getClassNameForToString() {
        return "Player";
    }

}
