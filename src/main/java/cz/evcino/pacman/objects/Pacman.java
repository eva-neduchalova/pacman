package cz.evcino.pacman.objects;

public class Pacman extends MovableObject {

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

}
