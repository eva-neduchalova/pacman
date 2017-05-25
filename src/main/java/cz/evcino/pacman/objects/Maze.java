package cz.evcino.pacman.objects;

import cz.evcino.pacman.enums.MazeLocationStatus;

public class Maze {

    public static final float SQUARE_LENGTH = 2f;

    private MazeLocationStatus[][] mazeData;
    private int rows;
    private int columns;

    private int remainingDotsCount = 0;


    public Maze(int mazeRows, int mazeColumns) {
        super();
        this.rows = mazeRows;
        this.columns = mazeColumns;
        this.setMazeData(new MazeLocationStatus[mazeColumns][mazeRows]);
    }

    public MazeLocationStatus[][] getMazeData() {
        return mazeData;
    }

    public void setMazeData(MazeLocationStatus[][] mazeData) {
        this.mazeData = mazeData;
    }

    public MazeLocationStatus getValue(int row, int column) {
        if (row >= columns || column >= rows) {
            return null;
        }
        return mazeData[row][column];
    }

    public void setValue(int row, int column, MazeLocationStatus value) {
        if (MazeLocationStatus.DOT.equals(value) || MazeLocationStatus.POWER_DOT.equals(value)) {
            remainingDotsCount++;
        } else if (MazeLocationStatus.EMPTY.equals(value)) {
            MazeLocationStatus original = getValue(row, column);
            if (MazeLocationStatus.DOT.equals(original) || MazeLocationStatus.POWER_DOT.equals(original)) {
                remainingDotsCount--;
            }
        }
        mazeData[row][column] = value;
    }

    public int getRows() {
        return rows;
    }

    public int getColumns() {
        return columns;
    }


    public int getRemainingDotsCount() {
        return remainingDotsCount;
    }

    public void setRemainingDotsCount(int remainingDotsCount) {
        this.remainingDotsCount = remainingDotsCount;
    }


    public String toLogString() {
        StringBuilder result = new StringBuilder("\n");
        for (int y = getRows() - 1; y >= 0; y--) {
            for (int x = 0; x < getColumns(); x++) {
                MazeLocationStatus status = getValue(x, y);
                if (status == null) {
                    status = MazeLocationStatus.EMPTY;
                }
                result.append(status.getShortcut());
            }
            result.append("\n");
        }
        return result.toString();
    }

}
