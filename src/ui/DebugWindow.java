package ui;

import gameObject.entity.Player;
import main.GamePanel;

import java.awt.Graphics2D;

import java.awt.Color;

public class DebugWindow {


    // TILES TO RENDER
    // OBJECTS TO RENDER


    private final int x, y;
    private final int width, height;
    private final Color backgroundColor = new Color(0, 0, 0, 210);
    private boolean visible;

    // DATA
    private Player player;
    private long updateTime;
    private long drawTime;
    private int numOfEntities;

    public DebugWindow(final int x, final int y, final int width, final int height) {

        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;

    }

    public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    private String getPlayerInfo() {
        return "Player: x = "+ player.getX() + ", y = " + player.getY() + "; Col = " + (player.getX()  / GamePanel.TILE_SIZE) + ", row = " + (player.getY()  / GamePanel.TILE_SIZE);
    }

    public void setUpdateTime(long updateTime) {
        this.updateTime = updateTime;
    }

    public void setDrawTime(long drawTime) {
        this.drawTime = drawTime;
    }

    public void setNumOfEntities(int numOfEntities) {
        this.numOfEntities = numOfEntities;
    }

    public void draw(final Graphics2D g2D) {

        if (isVisible()) {

            // PRINT: BACKGROUND
            g2D.setColor(backgroundColor);
            g2D.fillRect(x, y, width, height);

            // PRINT: PLAYER INFO
            g2D.setColor(Color.GRAY);

            drawPlayerInfo(g2D, x + 10, y + 20);
            drawUpdateTime(g2D, x + 10, y + 40);
            drawDrawTime(g2D, x + 10, y + 60);
            drawNumOfEntities(g2D, x + 10, y + 80);
            drawGeneralInfo(g2D, x + 300, y + 20);
        }

    }

    private void drawUpdateTime(final Graphics2D g2D, final int x, final int y) {
        g2D.drawString(updateTime + " ns", x , y);
    }

    private void drawDrawTime(final Graphics2D g2D, final int x, final int y) {
        g2D.drawString(drawTime + " ns", x , y);
    }

    private void drawNumOfEntities(final Graphics2D g2D, final int x, final int y) {
        g2D.drawString("Current number of entities: " + numOfEntities, x , y);
    }

    private void drawPlayerInfo(final Graphics2D g2D, final int x, final int y) {
        if (player != null) {
            g2D.drawString(getPlayerInfo(), x , y);
        }
    }

    private void drawGeneralInfo(final Graphics2D g2D, final int x, final int y) {


        g2D.drawString("F5: Focus (camera) on player.", x , y);
        g2D.drawString("F6: Focus (camera) on static object", x , y + 20);
        g2D.drawString("F11: Toggle debug mode", x , y + 40);
    }

}
