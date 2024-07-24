import java.util.Random;

public class Board {
    private final int rows, cols;
    private Cell[][] Cells;


    public Board(int rows, int cols, int seed, int range) {
        this.rows = rows;
        this.cols = cols;
        this.Cells = new Cell[rows][cols];
        initiateBoard(seed, range);
    }

    public Board(Board new_board) {
        this.rows = new_board.rows;
        this.cols = new_board.cols;
        this.Cells = new_board.Cells;
    }

    /**
     * This method is activated when creating board, as we want to create all the cells in the board.
     *
     * @param seed  a seed for the random object.
     * @param range range of numbers to generate.
     */
    private void initiateBoard(int seed, int range) {
        Random rand = new Random(seed);
        int rand_result;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {

                rand_result = rand.nextInt(range);
                // Choosing dead or healthy cell by odd or even random number.
                if (rand_result % 2 == 0)
                    Cells[i][j] = new DeadCell(i, j);
                else
                    Cells[i][j] = new HealthyCell(i, j);

            }
        }
    }


    /**
     * A method to check every neighbor cell whether he is healthy or sick for next generation.
     *
     * @param cell the cell we want to check the neighbors for.
     * @return int array of 2 numbers, the first for the dead, the second for the alive.
     */
    private int[] neighboursCondition(Cell cell) {
        int[] healthySick = new int[3];
        int row = cell.cell_row;
        int col = cell.cell_col;

        //Define the possible positions.
        int[][] neighborPositions = {
                {1, 0}, {0, 1}, {1, 1}, {0, -1}, {1, -1}, {-1, 0}, {-1, -1}, {-1, 1}
        };

        //Check every possibility
        for (int[] pos : neighborPositions) {
            int newRow = row + pos[0];
            int newCol = col + pos[1];
            if (isInBounds(newRow, newCol)) {
                healthySick[Cells[newRow][newCol].cellHealthyOrSick()]++;
            }
        }

        return healthySick;
    }


    /**
     * Checks if the given position is within the bounds of the cell grid. used when checking for neighbours condition.
     *
     * @param row The row index.
     * @param col The column index.
     * @return True if the position is within bounds, false otherwise.
     */
    private boolean isInBounds(int row, int col) {
        return row >= 0 && row < rows && col >= 0 && col < cols;
    }

    /**
     * This method is irritating on each cell and calculates its next generation cell
     * by using the next generation method in Cell class.
     */
    public void nextGeneration() {
        int ALIVE = 1;
        int DEAD = 0;
        Cell current_cell;
        Cell[][] new_cells = new Cell[rows][cols];
        int[] deadAlive; // deadAlive[0] for dead, deadAlive[1] for alive.
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                current_cell = Cells[i][j];
                deadAlive = neighboursCondition(current_cell);
                new_cells[i][j] = current_cell.nextGeneration(deadAlive[ALIVE], deadAlive[DEAD]);
            }
        }
        Cells = new_cells;
    }


    /**
     * This method overrides the equals method.
     * First the method checks if the other object is a board.
     * Then checks whether every cell in 2 boards are the same by using the cell equals method.
     *
     * @param obj the other object we want to check with.
     * @return boolean, true if every cell is the same as the other board's cell
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Board other = (Board) obj; //after class check, can cast.
        if (other.cols != this.cols || other.rows != this.rows) return false; //not the same size --> not same board.
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (!this.Cells[i][j].equals(other.Cells[i][j])) return false; // not the same cell --> not the same board.
            }

        }
        return true;
    }

    /**
     * This method overrides the hashCode method.
     * It calculates every cell's hash and adding it to the total.
     *
     * @return int, the sum of every cell's hashcode
     */
    @Override
    public int hashCode() {
        int total_hash = 0;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                total_hash += this.Cells[i][j].hashCode();
            }
        }
        return total_hash;
    }

    /**
     * This method overrides the toString method.
     * It converts every cell to string to get the string of the board.
     *
     * @return string for the whole board
     */
    @Override
    public String toString() {
        String result = "";
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                result += (this.Cells[i][j].conditionToString());
                // Add a space between columns, but not after the last column to fit the output
                if (j != cols - 1) {
                    result += " ";
                }
            }
            // Add a newline between rows, but not after the last row to fit the output
            if (i != rows - 1) {
                result += "\n";
            }
        }
        return result;
    }
}
