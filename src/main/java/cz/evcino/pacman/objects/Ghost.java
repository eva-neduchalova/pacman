package cz.evcino.pacman.objects;

import org.joml.Vector3f;

public class Ghost extends MovableObject {

    public static final Vector3f[] COLORS = { new Vector3f(0f, 1f, 0f), new Vector3f(1f, 0f, 0f) };

    private Vector3f color;

    public Vector3f getColor() {
        return color;
    }

    public void setColor(Vector3f color) {
        this.color = color;
    }

}
