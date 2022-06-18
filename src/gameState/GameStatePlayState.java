package gameState;

import animation.Direction;
import core.game.Camera;
import gameObject.CollisionArea;
import gameObject.entity.Dummy;
import gameObject.entity.Entity;
import gameObject.entity.Player;
import gameObject.renderer.ImageEntityRenderer;
import gfx.BufferedImageHelper;
import gfx.ImageLoader;
import main.GamePanel;
import tile.TileMap;

import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;

public class GameStatePlayState extends GameState {

    private Player player;
    private TileMap tileMap;
    private Camera camera;

    // TEST OBJECTS
    private Entity staticGameObject;

    public GameStatePlayState(GameStateManager gameStateManager, GameStateID gameStateID) {
        super(gameStateManager, gameStateID);
    }

    public void keyPressed(final int keyCode) {

        if (keyCode == KeyEvent.VK_ENTER) {
            gameStateManager.changeTo(GameStateID.PAUSE);
        }

        if (keyCode == KeyEvent.VK_D) {
            player.moveRight(true);
        }

        // MOVE: LEFT
        if (keyCode == KeyEvent.VK_A) {
            player.moveLeft(true);
        }

        // MOVE: UP
        if (keyCode == KeyEvent.VK_W) {
            player.moveUp(true);
        }

        // MOVE: DOWN
        if (keyCode == KeyEvent.VK_S) {
            player.moveDown(true);
        }

        // TEST CODE: CHECK FOR <focusOn> functionality. It works very well.
        if (keyCode == KeyEvent.VK_F5) {
            camera.focusOn(player);
        }

        if (keyCode == KeyEvent.VK_F6) {
            camera.focusOn(staticGameObject);
        }

    }

    public void keyReleased(final int keyCode) {

        if (keyCode == KeyEvent.VK_D) {
            player.moveRight(false);
        }

        if (keyCode == KeyEvent.VK_A) {
            player.moveLeft(false);
        }

        if (keyCode == KeyEvent.VK_W) {
            player.moveUp(false);
        }

        if (keyCode == KeyEvent.VK_S) {
            player.moveDown(false);
        }

        if (keyCode == KeyEvent.VK_F11) {
            GamePanel.debug = !GamePanel.debug;
        }
    }

    public void init() {

        // TODO: Dient aktuell lediglich zu Testzwecken.
        final BufferedImage playerImageScaled = BufferedImageHelper.scale(ImageLoader.loadImage("player/player.png"), GamePanel.TILE_SIZE, GamePanel.TILE_SIZE);

        // CREATE PLAYER
        player = new Player(100, 100, playerImageScaled.getWidth(), playerImageScaled.getHeight(), 3);
        player.setEntityRenderer(new ImageEntityRenderer(player, playerImageScaled));
        player.setCollisionArea(new CollisionArea(100, 100, GamePanel.TILE_SIZE, GamePanel.TILE_SIZE));

        // CREATE DUMMY OBJECT
        staticGameObject = new Dummy(10, 10, 38, 38, 0);
        staticGameObject.setCollisionArea(new CollisionArea(10, 10, 38, 38));

        // CREATE TILEMANAGER
        tileMap = new TileMap(GamePanel.ORIG_TILE_SIZE);
        tileMap.load();

        // CREATE CAMERA - FOSUC CAMERA ON PLAYER
        camera = new Camera(
                0,
                0,
                GamePanel.SCREEN_WIDTH,
                GamePanel.SCREEN_HEIGHT,
                new Dimension(tileMap.getWidthInTiles() * GamePanel.TILE_SIZE, tileMap.getHeightInTiles() * GamePanel.TILE_SIZE));

        camera.focusOn(player);
    }

    public void update() {

        handlePlayerTileManagerTilesCollision();

        player.update();

        handlerPlayerTileManagerBorderCollision();

    }

    private void handlerPlayerTileManagerBorderCollision() {

        final CollisionArea playerCollisionArea = player.getCollisionArea();

        if (playerCollisionArea.getX() < 0)
            player.translateX(-playerCollisionArea.getX());

        if (playerCollisionArea.getY() < 0)
            player.translateY(-playerCollisionArea.getY());

        if (playerCollisionArea.getXRight() > tileMap.getWidth())
            player.translateX(tileMap.getWidth() - playerCollisionArea.getXRight());

        if (playerCollisionArea.getYBottom() > tileMap.getHeight())
            player.translateY(tileMap.getHeight() - playerCollisionArea.getYBottom());
    }

    private void handlePlayerTileManagerTilesCollision() {

        if (player.isMoveUp() && tileMap.intersects(player, Direction.UP))
            player.setCanMoveUp(false);

        if (player.isMoveRight() && tileMap.intersects(player, Direction.RIGHT))
            player.setCanMoveRight(false);

        if (player.isMoveDown() && tileMap.intersects(player, Direction.DOWN))
            player.setCanMoveDown(false);

        if (player.isMoveLeft() && tileMap.intersects(player, Direction.LEFT))
            player.setCanMoveLeft(false);
    }

    public void draw(Graphics2D g2D) {

        tileMap.draw(g2D, camera);

        staticGameObject.draw(g2D, camera);

        player.draw(g2D, camera);

    }

}
