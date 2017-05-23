package cz.evcino.pacman.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cz.evcino.pacman.enums.MovementDirection;
import cz.evcino.pacman.objects.Maze;
import cz.evcino.pacman.objects.MazeLocationStatus;
import cz.evcino.pacman.objects.Pacman;

public class MovementUtils {

    protected static final Logger logger = LoggerFactory.getLogger(MovementUtils.class);

    public static final float DOT_DIAMETER = 0.25f;
    public static final long DOT_SCORE = 100;
    public static final float SUPERDOT_DIAMETER = 0.5f;
    public static final long SUPERDOT_SCORE = 200;
    public static final float PACMAN_DIAMETER = 1f;

    public static boolean canMove(Pacman pacman, Maze maze) {

        float mazeX = getPacmanRelativeXLocation(pacman, maze);
        float mazeY = getPacmanRelativeYLocation(pacman, maze);

        int newMazeX = getPacmanNewRelativeXLocation(mazeX, pacman.getDirection());
        int newMazeY = getPacmanNewRelativeYLocation(mazeY, pacman.getDirection());

        MazeLocationStatus mazeStatus = maze.getValue(newMazeX, newMazeY);
        boolean result = mazeStatus != null && !MazeLocationStatus.WALL.equals(mazeStatus);

        return result;
    }

    public static boolean evaluateDots(Pacman pacman, Maze maze) {

        float mazeX = getPacmanRelativeXLocation(pacman, maze);
        float mazeY = getPacmanRelativeYLocation(pacman, maze);

        int newMazeX = getPacmanNewRelativeXLocation(mazeX, pacman.getDirection());
        int newMazeY = getPacmanNewRelativeYLocation(mazeY, pacman.getDirection());

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

    private static float getPacmanRelativeXLocation(Pacman pacman, Maze maze) {
        float mazeX = (pacman.getLocationX() / Maze.SQUARE_LENGTH + (float)maze.getColumns() / 2);
        if (maze.getColumns() % 2 != 0) {
            mazeX += -0.5f;
        }
        return mazeX;
    }

    private static float getPacmanRelativeYLocation(Pacman pacman, Maze maze) {
        float mazeY = (pacman.getLocationY() / Maze.SQUARE_LENGTH + (float)maze.getRows() / 2);
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

    private static int getPacmanNewRelativeXLocation(float currentMazeX, MovementDirection direction) {
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

    private static int getPacmanNewRelativeYLocation(float currentMazeY, MovementDirection direction) {
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


}
