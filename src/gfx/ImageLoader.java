package gfx;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class ImageLoader {

    public static BufferedImage loadImage(final String path) {

        BufferedImage image = null;

        try {

            image = ImageIO.read(ImageLoader.class.getClassLoader().getResourceAsStream(path));

        } catch (IOException e) {
            e.printStackTrace();
        }

        return image;
    }

}
