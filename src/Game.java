public class Game extends Board{
    final int maxGen;
    final int CONTINUE = 1, CELLS_STABILIZED = -1, ALL_CELLS_DEAD = 0;

    public Game(int row, int col, int range, int seed, int maxGen){
        super(row,col,range,seed);
        this.maxGen = maxGen;

    }



    public void runGame(){
        int generation = 0;
        int stop = CONTINUE;
        while(generation <= maxGen && stop == CONTINUE){
            System.out.println("Generation " + generation + ":");
            System.out.println(this);
            stop = stopGame();
            this.nextGeneration();
            generation++;
        }
        if(generation-1 == maxGen) System.out.println("The generation limitation was reached.");
        if(stop == ALL_CELLS_DEAD) System.out.println("All cells are dead.");
        if(stop == CELLS_STABILIZED){
            System.out.println("Generation " + generation + ":");
            System.out.println(this);
            System.out.println("Cells have stabilized.");
        }
    }

    /**
     * Checks the conditions of the game whether it should stop or not.
     *
     * @return The condition to continue or stop (according to it's ending)
     */
    private int stopGame(){
        Board nextGenerationBoard = new Board(this);
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
