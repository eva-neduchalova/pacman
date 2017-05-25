package cz.evcino.pacman.objects;

public class Pacman extends AbstractMovableObject {

    public static final float PACMAN_OBJECT_SCALE = 0.8f;

    private int extraLives = 3;
    private long score = 0;


    public int getExtraLives() {
        return extraLives;
    }

    public void setExtraLives(int extraLives) {
        this.extraLives = extraLives;
    }

    public long getScore() {
        return score;
    }

    public void setScore(long score) {
        this.score = score;
    }

    @Override
    public float getDiameter() {
        return PACMAN_OBJECT_SCALE;
    }

}
