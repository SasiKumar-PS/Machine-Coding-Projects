package Model;

public class Board {
    private int dimension;
    private Cell[][] cells;

    public Board(int dimension) {
        this.dimension = dimension;
        // while initiating board, creating cell objects with indexes
        Cell[][] newCells = new Cell[dimension][dimension];
        for(int i=0; i<dimension; i++) {
            for(int j=0; j<dimension; j++) {
                newCells[i][j] = new Cell(i,j);
            }
        }
        this.cells = newCells;
    }

    public int getDimension() {
        return dimension;
    }

    public void setDimension(int dimension) {
        this.dimension = dimension;
    }

    public Cell[][] getCells() {
        return cells;
    }

    public void setCells(Cell[][] cells) {
        this.cells = cells;
    }

}
