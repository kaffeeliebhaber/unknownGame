package gfx;

import java.awt.*;
import java.awt.image.BufferedImage;

public final class BufferedImageHelper {

    public static BufferedImage scale(BufferedImage image, int newWidth, int newHeight) {

        final BufferedImage scaledImage = new BufferedImage(newWidth, newHeight, image.getType());
        final Graphics2D g2D = scaledImage.createGraphics();
        g2D.drawImage(image, 0, 0, newWidth, newHeight, null);
        g2D.dispose();

        return scaledImage;
    }

}
