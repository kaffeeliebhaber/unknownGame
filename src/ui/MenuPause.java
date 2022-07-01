package ui;

import java.awt.Graphics2D;
import java.awt.Color;

public class MenuPause {

    public static final String OPTION_BACK = "Zurück";
    public static final String OPTION_OPTIONS = "Optionen";
    public static final String OPTION_EXIT = "Beenden";

    public static final String SELECTION_LEFT = "> "; // Selection: left
    public static final String SELECTION_RIGHT = " <"; // Selection: right

    private short selectedIndex;
    private boolean open;

    public MenuPause() {}

    public void show() {
        open = true;
    }

    public void close() {
        open =  false;
    }

    public boolean isOpen() {
        return open;
    }

    public void moveUp() {

        selectedIndex--;
        arrangeSelectedIndex();

    }

    public void moveDown() {
        selectedIndex++;
        arrangeSelectedIndex();
    }

    private void arrangeSelectedIndex() {

        if (selectedIndex < 0) {
            selectedIndex = 2;
        } else if (selectedIndex > 2) {
            selectedIndex = 0;
        }

    }

    public void toggleOpen() {

        if (isOpen()) {
            close();
        } else {
            show();
        }

    }

    public void select() {}

    public void update() {

        if (isOpen()) {

        }

    }

    public void draw(Graphics2D g2D) {

        if (isOpen()) {

            g2D.setColor(new Color(0, 0, 0, 200));
            g2D.fillRect(50, 50, 300, 500);

            int marginY = 40;
            int posX = 100;
            int posY = 100;

            g2D.setColor(Color.WHITE);
            g2D.drawString(selectedIndex == 0 ? SELECTION_LEFT + OPTION_BACK + SELECTION_RIGHT : OPTION_BACK, posX, posY + 0 * marginY);
            g2D.drawString(selectedIndex == 1 ? SELECTION_LEFT + OPTION_OPTIONS + SELECTION_RIGHT : OPTION_OPTIONS, posX, posY + 1 * marginY);
            g2D.drawString(selectedIndex == 2 ? SELECTION_LEFT + OPTION_EXIT + SELECTION_RIGHT : OPTION_EXIT, posX, posY + 2 * marginY);

        }

    }

}
