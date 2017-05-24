package cz.evcino.pacman.objects;

import org.joml.Vector3f;

public class DrawableObject {

    private float defaultDefaultLocationX = 0;
    private float defaultDefaultLocationY = 0;
    private float defaultDefaultLocationZ = 0;

    public float getDefaultLocationX() {
        return defaultDefaultLocationX;
    }

    public void setDefaultLocationX(float defaultDefaultLocationX) {
        this.defaultDefaultLocationX = defaultDefaultLocationX;
    }

    public float getDefaultLocationY() {
        return defaultDefaultLocationY;
    }

    public void setDefaultLocationY(float defaultDefaultLocationY) {
        this.defaultDefaultLocationY = defaultDefaultLocationY;
    }

    public float getDefaultLocationZ() {
        return defaultDefaultLocationZ;
    }

    public void setDefaultLocationZ(float defaultDefaultLocationZ) {
        this.defaultDefaultLocationZ = defaultDefaultLocationZ;
    }

    public Vector3f getDefaultLocation() {
        return new Vector3f(defaultDefaultLocationX, defaultDefaultLocationY, defaultDefaultLocationZ);
    }

    public void setDefaultLocation(Vector3f defaultDefaultLocation) {
        setDefaultLocationX(defaultDefaultLocation.x);
        setDefaultLocationY(defaultDefaultLocation.y);
        setDefaultLocationZ(defaultDefaultLocation.z);
    }

}
