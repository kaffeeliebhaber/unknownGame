package tile;

import animation.Direction;
import core.game.Camera;
import gameObject.entity.MovableEntity;

import java.awt.Graphics2D;
import java.awt.Color;
import java.awt.Point;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

public class TileMap {

    private TileHighlighter tileHighlighter;
    private Tile[] tiles;
    private short[][] world;
    private final int tileSize;
    private boolean debugMode;

    public TileMap(final int tileSize, final int worldTilesX, final int worldTilesY, final String mapPath) {

        this.tileSize = tileSize;

        world = new short[worldTilesX][worldTilesY];

        load(mapPath);
    }

    public void setTileHighlighter(final TileHighlighter tileHighlighter) {
        this.tileHighlighter = tileHighlighter;
    }

    private boolean hasTileHighlighter() {
        return tileHighlighter != null;
    }

    public void toggleTileHighlighter() {
        if (hasTileHighlighter()) {
            tileHighlighter.toggleTileHighlighter();
        }
    }

    public void activateTileHighlighter() {
        if (hasTileHighlighter()) {
            tileHighlighter.activate();
        }
    }

    public void deactivateTileHighlighter() {
        if (hasTileHighlighter()) {
            tileHighlighter.deactivate();
        }
    }

    public void highlight(final Point pointOnScreen) {

        if (hasTileHighlighter()) {
            tileHighlighter.highlight(
                    getColFromX((int) pointOnScreen.getX()),
                    getRowFromY((int) pointOnScreen.getY()));
        }

    }

    public void setTiles(final Tile[] tiles) {
        this.tiles = tiles;
    }

    public boolean intersects(final MovableEntity movableEntity, final Direction checkDirection) {

        boolean collision = false;

        short tileXLeft, tileXRight;
        short tileYTop, tileYBottom;

        switch (checkDirection) {
            case UP:

                tileXLeft = (short) (movableEntity.getCollisionArea().getX() / this.tileSize);
                tileXRight = (short) (movableEntity.getCollisionArea().getXRight() / this.tileSize);
                tileYTop = (short) (movableEntity.getCollisionAreaYTopAfterMoving() / this.tileSize);

                collision = getTile(tileXLeft, tileYTop).isSolid() || getTile(tileXRight, tileYTop).isSolid();

                break;
            case RIGHT:

                tileXRight = (short) (movableEntity.getCollisionAreaXRightAfterMoving() / this.tileSize);
                tileYTop = (short) (movableEntity.getCollisionArea().getY() / this.tileSize);
                tileYBottom = (short) (movableEntity.getCollisionArea().getYBottom() / this.tileSize);

                collision = getTile(tileXRight, tileYTop).isSolid() || getTile(tileXRight, tileYBottom).isSolid();

                break;
            case DOWN:

                tileXLeft = (short) (movableEntity.getCollisionArea().getX() / this.tileSize);
                tileXRight = (short) (movableEntity.getCollisionArea().getXRight() / this.tileSize);
                tileYBottom = (short) (movableEntity.getCollisionAreaYBottomAfterMoving() / this.tileSize);

                collision = getTile(tileXLeft, tileYBottom).isSolid() || getTile(tileXRight, tileYBottom).isSolid();

                break;
            case LEFT:

                tileXLeft = (short) (movableEntity.getCollisionAreaXLeftAfterMoving() / this.tileSize);
                tileYTop = (short) (movableEntity.getCollisionArea().getY() / this.tileSize);
                tileYBottom = (short) (movableEntity.getCollisionArea().getYBottom() / this.tileSize);

                collision = getTile(tileXLeft, tileYTop).isSolid() || getTile(tileXLeft, tileYBottom).isSolid();

                break;
        }

        return collision;
    }

    private void load(final String path) {

        try {

            final InputStream input = getClass().getResourceAsStream(path);
            final BufferedReader reader = new BufferedReader(new InputStreamReader(input));

            String line = reader.readLine();

            int row = 0;

            while (line != null) {

                String[] lineSplitted = line.split(" ");

                for (int col = 0; col < lineSplitted.length; col++) {
                    world[col][row] = Short.parseShort(lineSplitted[col]);
                }

                row++;
                line = reader.readLine();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private int getColFromX(int x) {
        return x / this.tileSize;
    }

    private int getRowFromY(int y) {
        return y / this.tileSize;
    }

    public void draw(Graphics2D g2D, Camera camera) {

        this.drawTileMap(g2D, camera);
        this.drawTileHighlighter(g2D, camera);

    }

    private void drawTileMap(Graphics2D g2D, Camera camera) {
        final int offset = 1;

        int minX = (camera.getX() / this.tileSize) - offset;
        int minY = (camera.getY() / this.tileSize) - offset;

        int maxX = ((camera.getX() + camera.getWidth()) / this.tileSize) + offset;
        int maxY = ((camera.getY() + camera.getHeight()) / this.tileSize) + offset;

        if (minX < 0) minX = 0;
        if (maxX > getWidthInTiles()) maxX = getWidthInTiles();
        if (minY < 0) minY = 0;
        if (maxY > getHeightInTiles()) maxY = getHeightInTiles();

        for (int col = minX; col < maxX; col++) {
            for (int row = minY; row < maxY; row++) {
                g2D.drawImage(
                        tiles[world[col][row]].getImage(),
                        col * this.tileSize - camera.getX(),
                        row * this.tileSize - camera.getY(),
                        null);

                if (isInDebugMode()) {
                    g2D.setColor(Color.MAGENTA);
                    g2D.drawRect(
                            col * this.tileSize - camera.getX(),
                            row * this.tileSize - camera.getY(),
                            this.tileSize,
                            this.tileSize
                    );
                }
            }
        }
    }

    private void drawTileHighlighter(Graphics2D g2D, Camera camera) {
        if (hasTileHighlighter()) {
            tileHighlighter.draw(g2D, camera);
        }
    }

    public int getWidthInTiles() {
        return world[0].length;
    }

    public int getHeightInTiles() {
        return world.length;
    }

    public int getWidth() {
        return getWidthInTiles() * this.tileSize;
    }

    public int getHeight() {
        return getHeightInTiles() * this.tileSize;
    }

    private Tile getTile(final int col, final int row) {
        return tiles[getTileID(col, row)];
    }

    private short getTileID(final int col, final int row) {
        return world[col][row];
    }

    public void toggleDebugMode() {
        debugMode = !debugMode;
    }

    public boolean isInDebugMode() {
        return debugMode;
    }
}