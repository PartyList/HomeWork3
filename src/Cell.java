public class Cell extends Position {
    final int CELL = -1;
    final int HEALTHY_CELL = 3;
    final int SICK_CELL = 2;
    final int HEALTHY = 1;
    final int SICK = 0;
    final int ERROR = 2;
    public Cell(int row, int column) {
        super(row, column);
    }


    /**
     * This method is to convert the condition to string.
     *
     * @return string for LIVING CONDITION
     */
    public String conditionToString() {
        return "";
    }

    public boolean equals(Object other) {
        return (other != null && this.getClass() == other.getClass());
    }

    /**
     * returns the numerical value of the cell
     *
     * @return The specified number for each condition from Healthy to Dead
     * In case of undefined cell we get -1 (Which defines Cell)
     */
    @Override
    public int hashCode() {
        return CELL;
    }


    /**
     * Looks unto the neighbors (only healthy and sick neighbors) of the Cell and returns the cell of the new generation accordingly
     *
     * @param healthyNeighbors the amount of healthy neighbors of the cell
     * @param sickNeighbors    the amount of sick neighbors of the cell
     * @return the new cell for the next generation
     */
    public Cell nextGeneration(int healthyNeighbors, int sickNeighbors) {
        return this;
    }

    /**
     *   A method to see of a cell is healthy or sick.
     *
     *   @return 0 when sick , 1 when healthy otherwise 2.
     *
     */
    public int cellHealthyOrSick() {
        if (this.hashCode() == HEALTHY_CELL)
            return HEALTHY;
        else if (this.hashCode() == SICK_CELL)
            return SICK;

        return ERROR;

    }
}
