package animation;

import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Map;

public class AnimationPlayer {

    private Map<AnimationID, Animation> animations;
    private Animation currentAnimation;

    public AnimationPlayer() {
        animations = new HashMap<AnimationID, Animation>();
    }

    public void update() {

        if (currentAnimation != null) {
            currentAnimation.update();
        }
    }

    public void setAnimation(final AnimationID animationID) {

        if (animations.containsKey(animationID)) {
            currentAnimation = animations.get(animationID);
        }
    }

    public void play() {

        if (hasCurrentAnimation()) {
            currentAnimation.play();
        }
    }

    private boolean hasCurrentAnimation() {
        return currentAnimation != null;
    }

    public void addAnimation(final Animation animation) {

        if (animation != null) {
            animations.put(animation.getAnimationID(), animation);
        }

    }

    public Animation getCurrentAnimation() {
        return currentAnimation;
    }

    public BufferedImage getImage() {
        return currentAnimation.getImage();
    }

}
