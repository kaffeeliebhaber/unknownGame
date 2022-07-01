package gameObject.entity;

import gameObject.CollisionArea;
import gameObject.objects.Potion;
import gameObject.objects.SingleTree;
import gameObject.renderer.ImageEntityRenderer;
import main.GamePanel;

import java.awt.image.BufferedImage;

public class SimpleEntityFactor {

    public static Entity createSingleTree(final int x, final int y, final BufferedImage image) {

        Entity singleTree =  new SingleTree(x, y, GamePanel.TILE_SIZE, GamePanel.TILE_SIZE);
        singleTree.setEntityRenderer(new ImageEntityRenderer(singleTree, image));
        singleTree.setCollisionArea(
                new CollisionArea(
                        singleTree.getX() + 5 * GamePanel.SCALE,
                        singleTree.getY() + GamePanel.TILE_SIZE - 3 * GamePanel.SCALE,
                        5 * GamePanel.SCALE,
                        3 * GamePanel.SCALE));

        return singleTree;
    }

    public static Entity createPotion(final int x, final int y, final BufferedImage image) {

        Entity potion = new Potion(x, y, GamePanel.TILE_SIZE, GamePanel.TILE_SIZE);
        potion.setEntityRenderer(new ImageEntityRenderer(potion, image));
        potion.setCollisionArea(new CollisionArea(potion.getX(), potion.getY(), 10 * GamePanel.SCALE, 10 * GamePanel.SCALE));

        return potion;
    }

}
