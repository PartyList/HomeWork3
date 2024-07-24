public abstract class Cell extends Position {

    public Cell(int row, int column) {
        super(row, column);
    }

    /**
     * This method is to convert the condition to string.
     *
     * @return string for LIVING CONDITION
     */
    public abstract String conditionToString();

    /**
     * Looks unto the neighbors (only healthy and sick neighbors) of the Cell and returns the cell of the new generation accordingly
     *
     * @param healthyNeighbors the amount of healthy neighbors of the cell
     * @param sickNeighbors    the amount of sick neighbors of the cell
     * @return the new cell for the next generation
     */
    public abstract Cell nextGeneration(int healthyNeighbors, int sickNeighbors);

    /**
     * A method to see of a cell is healthy or sick.
     *
     * @return 0 when sick , 1 when healthy otherwise 2.
     */
    public abstract int cellHealthyOrSick();
}
