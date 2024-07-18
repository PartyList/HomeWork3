public class Game extends Board{
    private final int maxGen;
    private final int CONTINUE = 1, CELLS_STABILIZED = -1, ALL_CELLS_DEAD = 0;

    public Game(int row, int col, int range, int seed, int maxGen){
        super(row,col,range,seed);
        this.maxGen = maxGen;
    }
    public Game(Game game){
        super((Board)game);
        this.maxGen = game.maxGen;

    }

    /**
     * This method runs the game.
     * It uses stopGame method to know when the game ends.
     * We create newGame because we want to be able to run it again without changing the board.
     */
    public void runGame(){
        Game newGame = new Game(this);
        int generation = 0;
        int stop = CONTINUE;
        while(generation <= maxGen && stop == CONTINUE){
            System.out.println("Generation " + generation + ":");
            System.out.println(newGame);
            stop = stopGame(newGame);
            newGame.nextGeneration();
            generation++;
        }
        if(generation-1 == maxGen) System.out.println("The generation limitation was reached.");
        if(stop == ALL_CELLS_DEAD) System.out.println("All cells are dead.");
        if(stop == CELLS_STABILIZED){
            System.out.println("Generation " + generation + ":");
            System.out.println(newGame);
            System.out.println("Cells have stabilized.");
        }
        System.out.println();
    }

    /**
     * Checks the conditions of the game whether it should stop or not.
     *
     * @return The condition to continue or stop (according to it's ending)
     */
    private int stopGame(Game game){
        Board nextGenerationBoard = new Board(game);
        nextGenerationBoard.nextGeneration();
        if(this.equals(nextGenerationBoard)){
            return CELLS_STABILIZED;
        }
        //
        if(((Board)this).hashCode() == 0){
            return ALL_CELLS_DEAD;
        }
        //Game continues
        return CONTINUE;
    }
}
