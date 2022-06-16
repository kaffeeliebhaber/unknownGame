package handler;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener {

    /*
        TODO / ÜBERLEGUNGEN:

        > MACHT ES SINN, ÜBER EINE ART 'CONSUMED' NACHZUDENKEN?
        > Eventuell könnte man den KeyListener auch im Game-Core definieren und per keyPressed() und keyReleased()
            an den jeweiligen GameState weiterleiten...


     */


    public boolean upPressed, downPressed, leftPressed, rightPressed, enterPressed;

    // BUTTON 'F12' IS USED FOR TOGGLE 'DEBUG' INFORMATION.
    public boolean f12Pressed;

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

        final int keyCode = e.getKeyCode();

        if (keyCode == KeyEvent.VK_W) {
            upPressed = true;
        }

        if (keyCode == KeyEvent.VK_A) {
            leftPressed = true;
        }

        if (keyCode == KeyEvent.VK_S) {
            downPressed = true;
        }

        if (keyCode == KeyEvent.VK_D) {
            rightPressed = true;
        }

        if (keyCode == KeyEvent.VK_ENTER) {
            enterPressed = true;
        }

        if (keyCode == KeyEvent.VK_F12) {
            f12Pressed = true;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        final int keyCode = e.getKeyCode();

        if (keyCode == KeyEvent.VK_W) {
            upPressed = false;
        }

        if (keyCode == KeyEvent.VK_A) {
            leftPressed = false;
        }

        if (keyCode == KeyEvent.VK_S) {
            downPressed = false;
        }

        if (keyCode == KeyEvent.VK_D) {
            rightPressed = false;
        }

        if (keyCode == KeyEvent.VK_ENTER) {
            enterPressed = false;
        }

        if (keyCode == KeyEvent.VK_F12) {
            f12Pressed = false;
        }
    }
}
