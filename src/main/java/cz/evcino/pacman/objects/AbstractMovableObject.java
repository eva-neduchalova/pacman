package cz.evcino.pacman.objects;

import org.joml.Vector3f;

import cz.evcino.pacman.enums.MovementDirection;

public abstract class AbstractMovableObject extends DrawableObject {

    private float locationX = 0;
    private float locationY = 0;
    private float locationZ = 0;

    private MovementDirection direction = null;

    // how far object moves in one loop
    private float speed = 0.1f;


    public abstract float getDiameter();

    public void move() {
        if (getDirection() == null) {
            return;
        }
        switch (getDirection()) {
        case DOWN:
            setLocationY(getLocationY() - getSpeed());
            break;
        case LEFT:
            setLocationX(getLocationX() - getSpeed());
            break;
        case RIGHT:
            setLocationX(getLocationX() + getSpeed());
            break;
        case UP:
            setLocationY(getLocationY() + getSpeed());
        default:
            break;
        }
    }


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

    public MovementDirection getDirection() {
        return direction;
    }

    public void setDirection(MovementDirection direction) {
        this.direction = direction;
    }

    public float getSpeed() {
        return speed;
    }

    public void setSpeed(float speed) {
        this.speed = speed;
    }

    public String toLogString() {
        return "MovableObject [getDirection()=" + getDirection() + ", getSpeed()=" + getSpeed() + ", getLocation()=" + getLocation() + "]";
    }

}
