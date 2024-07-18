import java.util.Random;

public class Board {
    final Cell[][] Cells;
    final int rows,cols;

    public Board(int rows, int cols,int range, int seed) {
        this.rows = rows;
        this.cols = cols;
        this.Cells = new Cell[rows][cols];
        initiateBoard(seed,range);
    }

    /**
     * This method is activated when creating board, as we want to create all the cells in the board.
     * @param seed
     * @param range
     */
    private void initiateBoard(int seed, int range){
        Random rand = new Random(seed);
        int rand_result;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                rand_result = rand.nextInt(range);
                // Choosing dead or healthy cell by odd or even random number.
                if (rand_result % 2 == 0)
                    Cells[i][j] = new Cell(i, j, LIVINGCONDITION.HEALTHY);
                else
                    Cells[i][j] = new Cell(i, j, LIVINGCONDITION.DEAD);

            }
        }
    }

    /**
     * A method to check every neighbor cell whether he is alive or dead for next generation.
     * @param cell
     * @return int array of 2 numbers, the first for the dead, the second for the alive.
     */
    private int[] neighboursCondition(Cell cell){
        int [] dead_alive = new int[2];
        int row = cell.row;
        int col = cell.column;

        //check all neighbours
        if(row + 1 < rows) //down
            dead_alive[Cells[row+1][col].isCellDead()]++;
        if(col + 1 < cols)//right
            dead_alive[Cells[row][col+1].isCellDead()]++;
        if(row + 1 < rows && col + 1 < cols)//down&right
            dead_alive[Cells[row+1][col+1].isCellDead()]++;
        if(col - 1 >= 0)//left
            dead_alive[Cells[row][col-1].isCellDead()]++;
        if(row + 1 < rows && col - 1 >= 0)//down&left
            dead_alive[Cells[row+1][col-1].isCellDead()]++;
        if(row - 1 >= 0)//up
            dead_alive[Cells[row-1][col].isCellDead()]++;
        if(row - 1 >= 0 && col - 1 >= 0)//up&left
            dead_alive[Cells[row-1][col-1].isCellDead()]++;
        if(row - 1 >= 0 && col + 1 < cols)//up&right
            dead_alive[Cells[row-1][col+1].isCellDead()]++;

        return dead_alive;
    }

    /**
     * This method is irritating on each cell and calculates its next generation cell
     * by using the next generation method in Cell class.
     */
    public void nextGeneration(){
        Cell current_cell;
        Cell new_cell;
        int [] dead_alive = new int[2];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                current_cell = Cells[i][j];
                dead_alive = neighboursCondition(current_cell);
                new_cell = current_cell.nextGeneration(dead_alive[1],dead_alive[0]);
                Cells[i][j] = new_cell;
            }
        }
    }


    /**
     * This method overrides the equals method.
     * First the method checks if the other object is a board.
     * Then checks whether every cell in 2 boards are the same by using the cell equals method.
     * @param obj
     * @return boolean, true if every cell is the same as the other board's cell
     */
    @Override
    public boolean equals(Object obj) {
        if(this == obj) return true;
        if(obj == null || getClass() != obj.getClass()) return false;
        Board board = (Board) obj;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (!this.Cells[i][j].equals(board.Cells[i][j])) return false;
            }

        }
        return true;
    }

    /**
     * This method overrides the hashCode method.
     * It calculates every cell's hash and adding it to the total.
     * @return int, the sum of every cell's hashcode
     */
    @Override
    public int hashCode(){
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
     * @return string for the whole board
     */
    @Override
    public String toString(){
        String str = "";
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                str += (this.Cells[i][j]).conditionToString() + " ";
            }
            str += "\n"; // maybe remove at last, check in the end!!!!
        }
        return str;
    }
}
