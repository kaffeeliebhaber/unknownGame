package tile;

import java.awt.image.BufferedImage;

public class Tile {

    private final BufferedImage image;
    private boolean solid;
    private final int ID;

    public Tile(final BufferedImage image, boolean solid, final int ID) {

        this.image = image;
        this.solid = solid;
        this.ID = ID;
    }

    public boolean isSolid() {
        return solid;
    }

    public BufferedImage getImage() {
        return image;
    }

    @Override
    public String toString() {
        return "ID= " + ID + ", SOLID= " + solid;
    }

}
