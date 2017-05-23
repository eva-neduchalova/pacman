package cz.evcino.pacman.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cz.evcino.pacman.enums.MovementDirection;
import cz.evcino.pacman.objects.Ghost;
import cz.evcino.pacman.objects.Maze;
import cz.evcino.pacman.objects.MazeLocationStatus;
import cz.evcino.pacman.objects.MovableObject;
import cz.evcino.pacman.objects.Pacman;

public class MovementUtils {

    protected static final Logger logger = LoggerFactory.getLogger(MovementUtils.class);

    public static final float DOT_DIAMETER = 0.25f;
    public static final long DOT_SCORE = 100;
    public static final float SUPERDOT_DIAMETER = 0.5f;
    public static final long SUPERDOT_SCORE = 200;
    public static final float PACMAN_DIAMETER = 1f;

    private static final float TOLERANCE = 0.2f;


    public static boolean canMove(MovableObject movable, Maze maze) {

        float mazeX = getRelativeXLocation(movable, maze);
        float mazeY = getRelativeYLocation(movable, maze);

        int newMazeX = getNewRelativeXLocation(mazeX, movable.getDirection());
        int newMazeY = getNewRelativeYLocation(mazeY, movable.getDirection());

        MazeLocationStatus mazeStatus = maze.getValue(newMazeX, newMazeY);
        boolean result = mazeStatus == null || !MazeLocationStatus.WALL.equals(mazeStatus);

        return result;
    }

    public static boolean evaluateDots(Pacman pacman, Maze maze) {

        float mazeX = getRelativeXLocation(pacman, maze);
        float mazeY = getRelativeYLocation(pacman, maze);

        int newMazeX = getNewRelativeXLocation(mazeX, pacman.getDirection());
        int newMazeY = getNewRelativeYLocation(mazeY, pacman.getDirection());

        MazeLocationStatus mazeStatus = maze.getValue(newMazeX, newMazeY);

        if (MazeLocationStatus.DOT.equals(mazeStatus) || MazeLocationStatus.POWER_DOT.equals(mazeStatus)) {
            double distance = Math.hypot(mazeX - newMazeX, mazeY - newMazeY);
            float distanceThreshold = (PACMAN_DIAMETER + (MazeLocationStatus.DOT.equals(mazeStatus) ? DOT_DIAMETER : SUPERDOT_DIAMETER))
                    / 2;
            if (distance < distanceThreshold) {
                pacman.setScore(pacman.getScore() + (MazeLocationStatus.DOT.equals(mazeStatus) ? DOT_SCORE : SUPERDOT_SCORE));
                maze.setValue(newMazeX, newMazeY, MazeLocationStatus.EMPTY);
                // TODO - superdot sideeffects
                return true;
            }
        }


        // logger.info(pacman.toLogString());
        // logger.info(String.format("pacman at location [%f, %f], moving towards [%d, %d]. allowed=%s", mazeX, mazeY, newMazeX, newMazeY,
        // result));

        return false;
    }

    private static float getRelativeXLocation(MovableObject movable, Maze maze) {
        float mazeX = (movable.getLocationX() / Maze.SQUARE_LENGTH + (float)maze.getColumns() / 2);
        if (maze.getColumns() % 2 != 0) {
            mazeX += -0.5f;
        }
        return mazeX;
    }

    private static float getRelativeYLocation(MovableObject movable, Maze maze) {
        float mazeY = (movable.getLocationY() / Maze.SQUARE_LENGTH + (float)maze.getRows() / 2);
        if (maze.getRows() % 2 != 0) {
            mazeY += -0.5f;
        }
        return mazeY;
    }

    public static float getAbsoluteYLocation(int indexY, Maze maze) {
        return (indexY - (float)maze.getRows() / 2) * Maze.SQUARE_LENGTH;
    }

    public static float getAbsoluteXLocation(int indexX, Maze maze) {
        return (indexX - (float)maze.getColumns() / 2) * Maze.SQUARE_LENGTH;
    }

    private static int getNewRelativeXLocation(float currentMazeX, MovementDirection direction) {
        if (direction == null) {
            return Math.round(currentMazeX);
        }
        switch (direction) {
        case DOWN:
            return Math.round(currentMazeX);
        case LEFT:
            return (int)Math.round(Math.floor(currentMazeX));
        case RIGHT:
            return (int)Math.round(Math.ceil(currentMazeX));
        case UP:
            return Math.round(currentMazeX);
        }
        return Math.round(currentMazeX);
    }

    private static int getNewRelativeYLocation(float currentMazeY, MovementDirection direction) {
        if (direction == null) {
            return Math.round(currentMazeY);
        }
        switch (direction) {
        case DOWN:
            return (int)Math.round(Math.floor(currentMazeY));
        case LEFT:
            return Math.round(currentMazeY);
        case RIGHT:
            return Math.round(currentMazeY);
        case UP:
            return (int)Math.round(Math.ceil(currentMazeY));
        }
        return Math.round(currentMazeY);
    }

    public static boolean canGhostChangeDirection(Ghost ghost, Maze maze) {
        return !canMove(ghost, maze) || isAtCrossRoad(ghost, maze);
    }

    private static boolean isAtCrossRoad(Ghost ghost, Maze maze) {
        MovementDirection direction = ghost.getDirection();
        if (direction == null) {
            return true;
        }

        float mazeX = getRelativeXLocation(ghost, maze);
        float mazeY = getRelativeYLocation(ghost, maze);
        
        if (mazeX - Math.floor(mazeX) < TOLERANCE && mazeY - Math.floor(mazeY) < TOLERANCE) {
            // ok
        } else {
            return false;
        }

        MovementDirection[] directionsToCheck;

        if (MovementDirection.UP.equals(direction) || MovementDirection.DOWN.equals(direction)) {
            directionsToCheck = new MovementDirection[] { MovementDirection.LEFT, MovementDirection.RIGHT };
        } else {
            // check up and down
            directionsToCheck = new MovementDirection[] { MovementDirection.UP, MovementDirection.DOWN };
        }

        for (MovementDirection dir : directionsToCheck) {
            int newMazeX = getNewRelativeXLocation(mazeX, dir);
            int newMazeY = getNewRelativeYLocation(mazeY, dir);
            MazeLocationStatus mazeStatus = maze.getValue(newMazeX, newMazeY);
            if (mazeStatus == null || !MazeLocationStatus.WALL.equals(mazeStatus)) {
                return true;
            }
        }

        return false;
    }

    public static void selectGhostDirection(Ghost ghost, Maze maze) {
        List<MovementDirection> availableDirections = new ArrayList<>();
        MovementDirection[] directionsToCheck = MovementDirection.values();

        float mazeX = getRelativeXLocation(ghost, maze);
        float mazeY = getRelativeYLocation(ghost, maze);

        for (MovementDirection dir : directionsToCheck) {
            int newMazeX = getNewRelativeXLocation(mazeX, dir);
            int newMazeY = getNewRelativeYLocation(mazeY, dir);
            MazeLocationStatus mazeStatus = maze.getValue(newMazeX, newMazeY);
            if (mazeStatus == null || !MazeLocationStatus.WALL.equals(mazeStatus)) {
                availableDirections.add(dir);
            }
        }

        if (availableDirections.isEmpty()) {
            ghost.setDirection(null);
        }

        int index = new Random().nextInt(availableDirections.size());
        ghost.setDirection(availableDirections.get(index));

    }


}
