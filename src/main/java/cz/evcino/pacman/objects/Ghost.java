package cz.evcino.pacman.objects;

import org.joml.Vector3f;

import cz.evcino.pacman.enums.GhostStatus;
import cz.evcino.pacman.enums.MovementDirection;

public class Ghost extends AbstractMovableObject {

    public static final float GHOST_OBJECT_SCALE = 1f;
    public static final Vector3f[] COLORS = { new Vector3f(1f, 20 / 255f, 147 / 255f), new Vector3f(1f, 145 / 255f, 0f),
            new Vector3f(102 / 255f, 52 / 255f, 153 / 255f), new Vector3f(194 / 255f, 39 / 255f, 0 / 255f) };
    private static int idCounter = 0;

    private int id;
    private GhostStatus status = GhostStatus.CHASE;
    private Vector3f color;
    private int timeToGetToNormal = 800;
    private int keepDirectionForFrames = 0;

    public Ghost() {
        super();
        // setSpeed(0.11f);
        this.setId(idCounter++);
        setTimeToGetToNormal(800 / (id + 1));
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
        setTimeToGetToNormal(400);
    }

    public Vector3f getColor() {
        return color;
    }
    
    public Vector3f getApplicableColor() {
        if (GhostStatus.BLIND.equals(status)) {
            return new Vector3f(1f);
        }
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
        return GHOST_OBJECT_SCALE;
    }

    public void move() {
        super.move();
        timeToGetToNormal = Math.max(0, timeToGetToNormal - 1);
        keepDirectionForFrames = Math.max(0, keepDirectionForFrames - 1);
        if (timeToGetToNormal == 0) {
            if (status == GhostStatus.CHASE || status == GhostStatus.BLIND) {
                setStatus(GhostStatus.NORMAL);
            } else if (status == GhostStatus.NORMAL) {
                setStatus(GhostStatus.CHASE);
            }
        }
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

    @Override
    public void setLocationX(float locationX) {
        super.setLocationX(locationX);
        if (id == 1) {
            System.out.println(toLogString());
        }
    }

    @Override
    public void setLocationY(float locationY) {
        super.setLocationY(locationY);
        if (id == 1) {
            System.out.println(toLogString());
        }
    }


}