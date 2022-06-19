package gameObject.objects;

import gameObject.entity.Entity;

public class Potion extends Entity {

    public Potion(int x, int y, int width, int height) {
        super(x, y, width, height);
    }

    public String getClassNameForToString() {
        return "Potion";
    }
}
