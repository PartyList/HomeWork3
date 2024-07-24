public class DyingCell extends Cell {
    private final int HEALTHY_NEIGHBORS_TO_BE_HEALTHY = 3;
    private final int TOO_MANY_SICK = 1;
    private final int DYING_CELL = 1;
    private final int INVALID = 2;

    public DyingCell(int row, int column) {
        super(row, column);
    }

    /**
     * This method is to convert the condition to string.
     *
     * @return string for LIVING CONDITION
     */
    @Override
    public String conditionToString() {
        return "D";
    }

    /**
     * returns the numerical value of the cell
     *
     * @return The specified number for each condition from Healthy to Dead
     */
    @Override
    public int hashCode() {
        return DYING_CELL;
    }

    /**
     * Checks if both compared cells (this and other) are
     *
     * @param other
     * @return
     */
    @Override
    public boolean equals(Object other) {
        return (other instanceof DyingCell);
    }

    /**
     * Looks unto the neighbors (only healthy and sick neighbors) of the Cell and returns the cell of the new generation accordingly
     *
     * @param healthyNeighbors the amount of healthy neighbors of the cell
     * @param sickNeighbors    the amount of sick neighbors of the cell
     * @return the new cell for the next generation
     */
    public Cell nextGeneration(int healthyNeighbors, int sickNeighbors) {
        //The cell dies
        if (healthyNeighbors != HEALTHY_NEIGHBORS_TO_BE_HEALTHY || sickNeighbors > TOO_MANY_SICK) {
            return new DeadCell(this.cell_row, this.cell_col);
        }
        //The cell is healthy
        return new HealthyCell(this.cell_row, this.cell_col);
    }

    /**
     * A method to see of a cell is healthy or sick.
     *
     * @return 0 when sick , 1 when healthy otherwise 2.
     */
    public int cellHealthyOrSick() {
        return INVALID;
    }

}
