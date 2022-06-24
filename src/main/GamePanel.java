package main;

// IMPORTS: GAME FRAMEWORK
import core.game.Game;
import handler.KeyHandler;
import handler.MouseHandler;

// IMPORTS: SWING
import javax.swing.JPanel;

// IMPORTS: AWT
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class GamePanel extends JPanel implements Runnable {

    public static final int ORIG_TILE_SIZE = 16;
    public static final int SCALE = 3;
    public static final int TILE_SIZE = ORIG_TILE_SIZE * SCALE;
    public static final int MAX_SCREEN_COLS = 16;
    public static final int MAX_SCREEN_ROWS = 12;
    public static final int SCREEN_WIDTH = TILE_SIZE * MAX_SCREEN_COLS;
    public static final int SCREEN_HEIGHT = TILE_SIZE * MAX_SCREEN_ROWS;

    // THREAD
    private Thread gameThread;

    // GAME SETTINGS
    private final int FPS = 60;

    // GAME
    private final Game game;

    // DEBUG
    public static boolean debug;

    public GamePanel(final Game game) {

        this.game = game;

        this.setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));
        this.setBackground(Color.BLACK);
        this.setDoubleBuffered(true);
        this.setFocusable(true);
        this.requestFocus();

        // CREATE AND REGISTER KEYLISTENER.
        this.addKeyListener(new KeyHandler(game));
        this.addMouseMotionListener( new MouseHandler(game));

        // WE MAKE SURE GAME WILL BE INIT CORRECTLY.
        game.init();
    }

    public void start() {

        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run() {

        double drawInterval = 1000000000 / FPS;
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;
        long timer = 0;
        int drawCount = 0;

        while (gameThread != null) {

            currentTime = System.nanoTime();

            long deltaTime = currentTime - lastTime;

            delta += deltaTime / drawInterval;
            timer += deltaTime;
            lastTime = currentTime;

            if (delta >= 1) {
                update();
                repaint();
                delta--;
                drawCount++;
            }

            if (timer >= 1000000000) {

                if (debug) {
                    System.out.println("FPS: " + drawCount);
                }

                drawCount = 0;
                timer = 0;
            }
        }

    }

    public final void update() {
        game.update();
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2d = (Graphics2D) g;
/*
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_OFF);
        g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);


 */
        // RENDER GAME OBJECTS.
        game.draw(g2d);

        g2d.dispose();
    }
}
