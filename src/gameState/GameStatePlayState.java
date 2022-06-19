package gameState;

import animation.Direction;
import core.game.Camera;
import gameObject.CollisionArea;
import gameObject.entity.Dummy;
import gameObject.entity.Entity;
import gameObject.entity.MovableEntity;
import gameObject.entity.Player;
import gameObject.objects.Potion;
import gameObject.renderer.ImageEntityRenderer;
import gfx.BufferedImageHelper;
import gfx.ImageLoader;
import gfx.Spritesheet;
import main.GamePanel;
import tile.TileMap;

import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

public class GameStatePlayState extends GameState {

    // GRAPHICS
    private Spritesheet spritesheetObjects;
    private Player player;
    private TileMap tileMap;
    private Camera camera;

    // TEST OBJECTS
    private Entity staticGameObject;
    private List<Entity> entities;

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

    public void mouseDragged(MouseEvent e) {}

    public void mouseMoved(MouseEvent e) {

        if (GamePanel.debug) {
            //System.out.println("MouseX: " + e.getX() + ", MouseY: " + e.getY());
        }

    }

    public void init() {

        spritesheetObjects = new Spritesheet("objects/objects.png", GamePanel.TILE_SIZE, GamePanel.TILE_SIZE);
        spritesheetObjects.load();

        // TODO: Dient aktuell lediglich zu Testzwecken.
        final BufferedImage playerImageScaled = BufferedImageHelper.scale(ImageLoader.loadImage("player/player.png"), GamePanel.TILE_SIZE, GamePanel.TILE_SIZE);

        // CREATE PLAYER
        player = new Player(100, 100, playerImageScaled.getWidth(), playerImageScaled.getHeight(), 3);
        player.setEntityRenderer(new ImageEntityRenderer(player, playerImageScaled));
        player.setCollisionArea(new CollisionArea(109, 128, 30, 20));

        entities = new ArrayList<Entity>();

        // CREATE DUMMY OBJECT
        staticGameObject = new Dummy(10, 10, 38, 38, 0);
        staticGameObject.setCollisionArea(new CollisionArea(10, 10, 38, 38));

        entities.add(staticGameObject);

        Entity potion = new Potion(200, 200, GamePanel.TILE_SIZE, GamePanel.TILE_SIZE);
        potion.setEntityRenderer(new ImageEntityRenderer(potion, spritesheetObjects.getImageAt(0, 0)));
        potion.setCollisionArea(new CollisionArea(200, 200, 10, 10));

        entities.add(potion);

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

        handlePlayerEntityCollision();

        player.update();

        entities.stream().forEach(e -> e.update());

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

    private void handlePlayerEntityCollision() {

        for (final Entity e : entities) {

            // TODO: Funktioniert so einfach leider nicht wie gewünscht. Ein sliden an den Objekten ist dadurch leider nicht möglich.
            final boolean intersectsPlayer = e.intersects(player);


            if (intersectsPlayer) {

                if (e instanceof MovableEntity) {

                    // TYPE: MOVABLE.

                } else {

                    if (player.isMoveUp()) {
                        player.setCanMoveUp(false);
                    }

                    if (player.isMoveDown()) {
                        player.setCanMoveDown(false);
                    }

                    if (player.isMoveLeft()) {
                        player.setCanMoveLeft(false);
                    }

                    if (player.isMoveRight()) {
                        player.setCanMoveRight(false);
                    }

                }
            }

        }

    }

    public void draw(Graphics2D g2D) {

        tileMap.draw(g2D, camera);

        entities.forEach(e -> e.draw(g2D, camera));

        player.draw(g2D, camera);

    }

}
