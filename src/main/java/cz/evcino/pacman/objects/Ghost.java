package cz.evcino.pacman.objects;

import org.joml.Vector3f;

import cz.evcino.pacman.enums.GhostStatus;
import cz.evcino.pacman.enums.MovementDirection;

public class Ghost extends AbstractMovableObject {

    public static final float GHOST_DIAMETER = 1f;
    public static final Vector3f[] COLORS = { new Vector3f(1f, 20 / 255f, 147 / 255f), new Vector3f(1f, 145 / 255f, 0f),
            new Vector3f(102 / 255f, 52 / 255f, 153/255f), new Vector3f(194 / 255f, 39 / 255f, 0 / 255f) };
    private static int idCounter = 0;

    private int id;
    private GhostStatus status = GhostStatus.NORMAL;
    private Vector3f color;
    private int timeToGetToNormal = 80;
    private int keepDirectionForFrames = 0;

    public Ghost() {
        super();
        // setSpeed(0.11f);
        this.setId(idCounter++);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public GhostStatus getStatus() {
        return status;
    }

    public void setStatus(GhostStatus status) {
        this.status = status;
    }

    public Vector3f getColor() {
        return color;
    }

    public void setColor(Vector3f color) {
        this.color = color;
    }

    public int getTimeToGetToNormal() {
        return timeToGetToNormal;
    }

    public void setTimeToGetToNormal(int timeToGetToNormal) {
        this.timeToGetToNormal = timeToGetToNormal;
    }


    public int getKeepDirectionForFrames() {
        return keepDirectionForFrames;
    }


    public void setKeepDirectionForFrames(int keepDirectionForFrames) {
        this.keepDirectionForFrames = keepDirectionForFrames;
    }

    @Override
    public float getDiameter() {
        super.move();
        return GHOST_DIAMETER;
    }

    public void move() {
        super.move();
        timeToGetToNormal = Math.max(0, timeToGetToNormal - 1);
        keepDirectionForFrames = Math.max(0, keepDirectionForFrames - 1);
    }

    @Override
    public void setDirection(MovementDirection direction) {
        super.setDirection(direction);
        keepDirectionForFrames = 10;
    }

    @Override
    public String toLogString() {
        StringBuilder result = new StringBuilder("Ghost id=" + id + "; ");
        result.append(super.toLogString());
        return result.toString();
    }

}