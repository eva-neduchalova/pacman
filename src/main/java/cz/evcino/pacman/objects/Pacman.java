package cz.evcino.pacman.objects;

import cz.evcino.pacman.enums.MovementDirection;

public class Pacman extends AbstractDrawableObject {

    private int extraLives = 3;
    private long score = 0;

    private MovementDirection direction = MovementDirection.UP;
    // how far pacman moves in one "move" round
    float speed = 0.1f;

    public int getExtraLives() {
        return extraLives;
    }

    public void setExtraLives(int extraLives) {
        this.extraLives = extraLives;
    }

    public MovementDirection getDirection() {
        return direction;
    }

    public void setDirection(MovementDirection direction) {
        this.direction = direction;
    }


    public void move() {
        switch (direction) {
        case DOWN:
            setLocationY(getLocationY() - speed);
            break;
        case LEFT:
            setLocationX(getLocationX() - speed);
            break;
        case RIGHT:
            setLocationX(getLocationX() + speed);
            break;
        case UP:
            setLocationY(getLocationY() + speed);
        default:
            break;
        }
    }

    public String toLogString() {
        return "Pacman [extraLives=" + extraLives + ", direction=" + direction + ", speed=" + speed + ", getLocationX()=" + getLocationX()
                + ", getLocationY()=" + getLocationY() + ", getLocationZ()=" + getLocationZ() + "]";
    }

    public long getScore() {
        return score;
    }

    public void setScore(long score) {
        this.score = score;
    }

}
