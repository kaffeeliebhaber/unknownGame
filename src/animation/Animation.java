package animation;

import java.awt.image.BufferedImage;

public class Animation {

    private final AnimationID animationID;

    // Enthält alle Bildinformationen
    private BufferedImage[] images;

    // Definiert die Anzahl vom Frames, die verstreichen muss, damit der nächste Frame angezeigt werden soll.
    private int frameDelay;

    // Gibt die aktuelle Anzahl der bereits verstrichenen Frames an.
    private int currentFrameDelay;

    // Gibt den Aktuellen Frame an
    private int currentFrame;

    // Gibt an den Status der Animation an. Default: aus (false).
    private boolean isRunning;

    public Animation(final BufferedImage[] images, final int frameDelay, final AnimationID animationID) {

        this.images = images;
        this.frameDelay = frameDelay;
        this.animationID = animationID;
        this.currentFrameDelay = 0;
        this.currentFrame = 0;

    }

    public AnimationID getAnimationID() {
        return animationID;
    }

    public void play() {
        isRunning = true;
    }

    public void stop() {
        isRunning = false;
    }

    private boolean isRunning() {

        return isRunning;

    }

    public void update() {

        //if (isRunning()) {
        if (true) {

            currentFrameDelay++;

            if (currentFrameDelay == frameDelay) {
                currentFrameDelay = 0;
                currentFrame++;

                if (currentFrame == images.length) {
                    currentFrame = 0;
                }
            }


        }

    }

    public BufferedImage getImage() {
        return images[currentFrame];
    }


}
