package cz.evcino.pacman.objects;

import org.joml.Vector3f;

public class AbstractDrawableObject {

    private float locationX = 0;
    private float locationY = 0;
    private float locationZ = 0;

    public float getLocationX() {
        return locationX;
    }

    public void setLocationX(float locationX) {
        this.locationX = locationX;
    }

    public float getLocationY() {
        return locationY;
    }

    public void setLocationY(float locationY) {
        this.locationY = locationY;
    }

    public float getLocationZ() {
        return locationZ;
    }

    public void setLocationZ(float locationZ) {
        this.locationZ = locationZ;
    }

    public Vector3f getLocation() {
        return new Vector3f(locationX, locationY, locationZ);
    }

    public void setLocation(Vector3f location) {
        setLocationX(location.x);
        setLocationY(location.y);
        setLocationZ(location.z);
    }

}
