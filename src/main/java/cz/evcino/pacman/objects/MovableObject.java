package cz.evcino.pacman.objects;

import cz.evcino.pacman.enums.MovementDirection;

public class MovableObject extends DrawableObject {

    private MovementDirection direction = null;

    // how far object moves in one loop
    private float speed = 0.1f;

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


    public String toLogString() {
        return "MovableObject [getDirection()=" + getDirection() + ", getSpeed()=" + getSpeed() + ", getLocation()=" + getLocation() + "]";
    }

}
