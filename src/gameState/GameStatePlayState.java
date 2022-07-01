package gameState;

import animation.Animation;
import animation.AnimationID;
import animation.AnimationPlayer;
import animation.Direction;
import core.game.Camera;
import gameObject.CollisionArea;
import gameObject.entity.Dummy;
import gameObject.entity.Entity;
import gameObject.entity.Player;
import gameObject.entity.SimpleEntityFactor;
import gameObject.objects.Potion;
import gameObject.objects.SingleTree;
import gameObject.renderer.ImageEntityRenderer;
import gfx.Spritesheet;
import main.GamePanel;
import tile.Tile;
import tile.TileHighlighter;
import tile.TileMap;
import ui.DebugWindow;
import ui.MenuPause;

import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Collection;

public class GameStatePlayState extends GameState {

    // UI
    private DebugWindow debugWindow;
    private MenuPause menuPause;

    // GRAPHICS
    private Spritesheet spritesheetObjects;
    private Spritesheet spritesheetPlayer;
    private Spritesheet spritesheetTiles;
    private Player player;
    private TileMap tileMap;
    private Camera camera;

    // TEST OBJECTS
    private Entity staticGameObject; // TODO: This static object is only for testing purposes.
    private Collection<Entity> entities; // TODO: Create for each type of entity a new list for collect them.

    public GameStatePlayState(GameStateManager gameStateManager, GameStateID gameStateID) {
        super(gameStateManager, gameStateID);
    }

    public void keyPressed(final int keyCode) {

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

        if (keyCode == KeyEvent.VK_F1) {
            tileMap.toggleTileHighlighter();
        }

        if (keyCode == KeyEvent.VK_ENTER) {
            gameStateManager.changeTo(GameStateID.PAUSE);
        }

        if (keyCode == KeyEvent.VK_F11) {
            GamePanel.debug = !GamePanel.debug;
            tileMap.toggleDebugMode();
        }

        if (keyCode == KeyEvent.VK_F4) {
            debugWindow.setVisible(!debugWindow.isVisible());
        }

        if (keyCode == KeyEvent.VK_ESCAPE) {

            //System.out.println("(GameStatePlayState.keyReleased) ESC was released.");
            menuPause.toggleOpen();

        }

        if (keyCode == KeyEvent.VK_UP) {
            if (menuPause.isOpen()) {
                menuPause.moveUp();
            }
        }

        if (keyCode == KeyEvent.VK_DOWN) {
            if (menuPause.isOpen()) {
                menuPause.moveDown();
            }
        }
    }

    public void mouseDragged(MouseEvent e) {}

    public void init() {

        // TODO: Dient aktuell lediglich zu Testzwecken.
        //final BufferedImage playerImageScaled = BufferedImageHelper.scale(ImageLoader.loadImage("player/player.png"), GamePanel.TILE_SIZE, GamePanel.TILE_SIZE);

        entities = new ArrayList<Entity>();

        loadImages();
        setup();

    }

    private void loadImages() {

        spritesheetObjects = new Spritesheet("objects/objects.png", GamePanel.ORIG_TILE_SIZE, GamePanel.ORIG_TILE_SIZE, GamePanel.TILE_SIZE, GamePanel.TILE_SIZE);
        spritesheetObjects.load();

        spritesheetPlayer = new Spritesheet("player/testCharacter/playerSpritesheet.png", GamePanel.ORIG_TILE_SIZE, GamePanel.ORIG_TILE_SIZE, GamePanel.TILE_SIZE, GamePanel.TILE_SIZE);
        spritesheetPlayer.load();

        spritesheetTiles = new Spritesheet("tile/grassTileset.png", GamePanel.ORIG_TILE_SIZE, GamePanel.ORIG_TILE_SIZE, GamePanel.TILE_SIZE, GamePanel.TILE_SIZE);
        spritesheetTiles.load();
    }

    private void setup()  {

        setupEntities();
        setupPlayer();
        setupTileMap();
        setupCamera();
        setupUI();

    }

    private void setupPlayer() {

        final int frameDelay = 30;
        final AnimationPlayer animationPlayer = new AnimationPlayer();

        // IDLE
        animationPlayer.addAnimation(new Animation(
                new BufferedImage[]{
                        spritesheetPlayer.getImageByIndex(0),
                        spritesheetPlayer.getImageByIndex(1)}, frameDelay, AnimationID.IDLE_DOWN));

        animationPlayer.addAnimation(new Animation(
                new BufferedImage[]{
                        spritesheetPlayer.getImageByIndex(2),
                        spritesheetPlayer.getImageByIndex(3)}, frameDelay, AnimationID.IDLE_UP));

        // RIGHT
        animationPlayer.addAnimation(new Animation(
                new BufferedImage[]{
                        spritesheetPlayer.getImageByIndex(8),
                        spritesheetPlayer.getImageByIndex(9)}, frameDelay, AnimationID.RIGHT));

        // LEFT
        animationPlayer.addAnimation(new Animation(
                new BufferedImage[]{
                        spritesheetPlayer.getImageByIndex(10),
                        spritesheetPlayer.getImageByIndex(11)}, frameDelay, AnimationID.LEFT));

        // UP
        animationPlayer.addAnimation(new Animation(
                new BufferedImage[]{
                        spritesheetPlayer.getImageByIndex(12),
                        spritesheetPlayer.getImageByIndex(13)}, frameDelay, AnimationID.DOWN));

        // DOWN
        animationPlayer.addAnimation(new Animation(
                new BufferedImage[]{
                        spritesheetPlayer.getImageByIndex(14),
                        spritesheetPlayer.getImageByIndex(15)}, frameDelay, AnimationID.UP));

        animationPlayer.setAnimation(AnimationID.IDLE_DOWN);

        //animationPlayer.play();

        player = new Player(100, 100, GamePanel.TILE_SIZE, GamePanel.TILE_SIZE, 3);
        player.setCollisionArea(new CollisionArea(109, 128, 30, 20));
        player.setAnimationPlayer(animationPlayer);

        entities.add(player);

    }

    private void setupTileMap() {

        tileMap = new TileMap(GamePanel.TILE_SIZE, 50, 50, "/maps/map01.txt");
        //tileMap.setTileHighlighter(new TileHighlighter(GamePanel.TILE_SIZE, 4));
        tileMap.setTiles(spritesheetTiles.convertInTileArray());

    }

    private void setupCamera() {

        // CREATE CAMERA - FOSUC CAMERA ON PLAYER
        camera = new Camera(
                0,
                0,
                GamePanel.SCREEN_WIDTH,
                GamePanel.SCREEN_HEIGHT,
                new Dimension(tileMap.getWidthInTiles() * GamePanel.TILE_SIZE, tileMap.getHeightInTiles() * GamePanel.TILE_SIZE));

        camera.focusOn(player);

    }

    private void setupEntities() {

        // STATIC GAME OBJECT.
        staticGameObject = new Dummy(300, 150, 38, 38, 0);
        staticGameObject.setCollisionArea(new CollisionArea(staticGameObject.getX(), staticGameObject.getY(), staticGameObject.getWidth(), staticGameObject.getHeight()));

        entities.add(staticGameObject);
        entities.add(SimpleEntityFactor.createPotion(200, 200, spritesheetObjects.getImageByIndex(0)));

        final BufferedImage singleTreeImage = spritesheetObjects.getImageByIndex(1);
        final int shiftX = 20;
        final int shiftY = 40;

        entities.add(SimpleEntityFactor.createSingleTree(400 + shiftX + 0 * GamePanel.TILE_SIZE, 450 + shiftY, singleTreeImage));
        entities.add(SimpleEntityFactor.createSingleTree(400 + shiftX + 1 * GamePanel.TILE_SIZE, 450 + shiftY, singleTreeImage));
        entities.add(SimpleEntityFactor.createSingleTree(400 + shiftX + 2 * GamePanel.TILE_SIZE, 450 + shiftY, singleTreeImage));
        entities.add(SimpleEntityFactor.createSingleTree(400 + shiftX + 3 * GamePanel.TILE_SIZE, 450 + shiftY, singleTreeImage));

        entities.add(SimpleEntityFactor.createSingleTree(400 + 0 * GamePanel.TILE_SIZE, 450, singleTreeImage));
        entities.add(SimpleEntityFactor.createSingleTree(400 + 1 * GamePanel.TILE_SIZE, 450, singleTreeImage));
        entities.add(SimpleEntityFactor.createSingleTree(400 + 2 * GamePanel.TILE_SIZE, 450, singleTreeImage));
        entities.add(SimpleEntityFactor.createSingleTree(400 + 3 * GamePanel.TILE_SIZE, 450, singleTreeImage));

    }

    private void setupUI() {

        final int windowHeightInTileSize = 3;

        debugWindow = new DebugWindow(
                0,
                GamePanel.TILE_SIZE * (GamePanel.MAX_SCREEN_ROWS - windowHeightInTileSize),
                GamePanel.SCREEN_WIDTH,
                GamePanel.TILE_SIZE * windowHeightInTileSize);

        debugWindow.setPlayer(player);

        menuPause = new MenuPause();
    }

    public void update() {

        if (menuPause.isOpen()) {
            menuPause.update();
            return;
        }

        long startTime = System.nanoTime();

        handlePlayerCollisionTileMap();

        handlePlayerCollisionEntities();

        entities.stream().forEach(e -> e.update());

        handlePlayerCollisionWorldBounds();

        debugWindow.setUpdateTime(System.nanoTime() - startTime);
        debugWindow.setNumOfEntities(entities.size());



    }

    private void handlePlayerCollisionWorldBounds() {

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

    private void handlePlayerCollisionTileMap() {

        if (player.isMoveUp() && tileMap.intersects(player, Direction.UP))
            player.setCanMoveUp(false);

        if (player.isMoveRight() && tileMap.intersects(player, Direction.RIGHT))
            player.setCanMoveRight(false);

        if (player.isMoveDown() && tileMap.intersects(player, Direction.DOWN))
            player.setCanMoveDown(false);

        if (player.isMoveLeft() && tileMap.intersects(player, Direction.LEFT))
            player.setCanMoveLeft(false);
    }

    private void handlePlayerCollisionEntities() {

        for (final Entity e : entities) {

            if (e == player) continue;

            if (player.isMoveUp() && player.canMoveUp() && e.intersects(player, Direction.UP)) {
                player.setCanMoveUp(false);
            }

            if (player.isMoveDown() && player.canMoveDown() && e.intersects(player, Direction.DOWN)) {
                player.setCanMoveDown(false);
            }

            if (player.isMoveLeft() && player.canMoveLeft() && e.intersects(player, Direction.LEFT)) {
                player.setCanMoveLeft(false);
            }

            if (player.isMoveRight() && player.canMoveRight() && e.intersects(player, Direction.RIGHT)) {
                player.setCanMoveRight(false);
            }
        }
    }

    public void draw(Graphics2D g2D) {



        long startTime = System.nanoTime();

        tileMap.draw(g2D, camera);


        entities
                .stream()
                .sorted((e1, e2) -> new Integer(e1.getYBottom()).compareTo(e2.getYBottom()))
                .forEach(e -> e.draw(g2D, camera));


        debugWindow.setDrawTime(System.nanoTime() - startTime);
        debugWindow.draw(g2D);

        if (menuPause.isOpen()) {
            menuPause.draw(g2D);
        }


    }

    @Override public void mouseMoved(MouseEvent e) { tileMap.highlight(camera.getWorldPointFromScreenPoint(e.getPoint())); }

    @Override public void mouseEntered(MouseEvent e) { tileMap.activateTileHighlighter(); }

    @Override public void mouseExited(MouseEvent e) { tileMap.deactivateTileHighlighter(); }

}
