package cz.evcino.pacman.objects;

public class Maze {

    public static final float SQUARE_LENGTH = 2f;

    private MazeLocationStatus[][] mazeData;
    private int rows;
    private int columns;


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
        return mazeData[row][column];
    }

    public void setValue(int row, int column, MazeLocationStatus value) {
        mazeData[row][column] = value;
    }

    public int getRows() {
        return rows;
    }

    public int getColumns() {
        return columns;
    }

}
