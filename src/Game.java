public class Game{
    private final int maxGen;
    private final int CONTINUE = 1, CELLS_STABILIZED = -1, ALL_CELLS_DEAD = 0;
    private final Board gameBoard;

    public Game(int row, int col, int seed, int range, int maxGen){
        this.gameBoard = new Board(row,col,seed,range);
        this.maxGen = maxGen;
    }

    /**
     * This method runs the game.
     * It uses stopGame method to know when the game ends.
     * We create newGame because we want to be able to run it again without changing the board.
     */
    public void runGame(){
        Board runBoard = new Board(this.gameBoard);
        int generation = 0;
        int stop = CONTINUE;
        while(generation <= maxGen && stop == CONTINUE){
            System.out.println("Generation " + generation + ":");
            System.out.println(runBoard);
            stop = stopGame(runBoard);
            runBoard.nextGeneration();
            generation++;
        }
        if(generation-1 == maxGen) System.out.println("The generation limitation was reached.");
        if(stop == ALL_CELLS_DEAD) System.out.println("All cells are dead.");
        if(stop == CELLS_STABILIZED){
            System.out.println("Generation " + generation + ":");
            System.out.println(runBoard);
            System.out.println("Cells have stabilized.");
        }
    }

    /**
     * Checks the conditions of the game whether it should stop or not.
     *
     * @return The condition to continue or stop (according to it's ending)
     */
    private int stopGame(Board board){
        if(board.hashCode() == 0){
            return ALL_CELLS_DEAD;
        }
        Board nextBoard = new Board(board);
        nextBoard.nextGeneration();
        if(nextBoard.equals(board)){
            return CELLS_STABILIZED;
        }
        return CONTINUE;
    }
}
