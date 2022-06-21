package gameObject.objects;

import gameObject.entity.Entity;

public class SingleTree extends Entity {

    public SingleTree(int x, int y, int width, int height) {
        super(x, y, width, height);
    }

    public String getClassNameForToString() {
        return "SingleTree";
    }
}
