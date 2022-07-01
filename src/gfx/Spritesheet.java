package gfx;

import tile.Tile;

import java.awt.image.BufferedImage;

public class Spritesheet {

    private int tileWidth;
    private int tileHeight;

    private BufferedImage spritesheet;

    private BufferedImage[][] subImages;

    private int rows;
    private int cols;

    private int gameTileWidth;
    private int gameTileHeight;

    public Spritesheet(final String path, int tileWidth, int tileHeight, int gameTileWidth, int gameTileHeight)  {
        this(ImageLoader.loadImage(path), tileWidth, tileHeight, gameTileWidth, gameTileHeight);
    }

    public Spritesheet(final BufferedImage spritesheet, int tileWidth, int tileHeight, int gameTileWidth, int gameTileHeight) {
        this.spritesheet = spritesheet;
        this.tileWidth = tileWidth;
        this.tileHeight = tileHeight;

        this.rows = spritesheet.getHeight() / tileHeight;
        this.cols = spritesheet.getWidth() / tileWidth;

        this.gameTileWidth = gameTileWidth;
        this.gameTileHeight = gameTileHeight;

        subImages = new BufferedImage[rows][cols];

    }

    public void load() {
        if (spritesheet == null) {
            return;
        }

        for (int y = 0; y < rows; y++) {
            for (int x = 0; x < cols; x++) {

                final BufferedImage unscaledImage = spritesheet.getSubimage(x * tileWidth, y * tileHeight, tileWidth, tileHeight);
                final BufferedImage scaledImage = BufferedImageHelper.scale(unscaledImage, gameTileWidth, gameTileHeight);
                subImages[y][x] = scaledImage;
            }
        }
    }

    public BufferedImage getImageByIndex(int index) {
        return getImageAt(index / cols, index % cols);
    }

    public BufferedImage getImageAt(int row, int col) {

        try {
            return subImages[row][col];
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("row: " + row + ", col: " + col);
        }
        return null;
    }

    public BufferedImage[] getImages(int row) {
        return subImages[row];
    }

    public BufferedImage getSpritesheet() {
        return spritesheet;
    }

    public int getNumberOfTiles() {
        return subImages.length * subImages[0].length;
    }

    public Tile[] convertInTileArray() {

        final int numberOfTiles = getNumberOfTiles();
        final Tile[] tiles = new Tile[numberOfTiles];

        for (int i = 0; i < numberOfTiles; i++) {
            tiles[i] = new Tile(getImageByIndex(i), false, i);
        }

        return tiles;
    }
}
