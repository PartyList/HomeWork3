public class SickCell extends Cell {
    private final int TOO_MANY_SICK = 2;
    private final int ENOUGH_HEALTHY = 3;
    private final int NOT_ENOUGH_HEALTHY = 2;
    private final int SICK_CELL = 2;
    private final int IS_SICK = 0;

    public SickCell(int row, int column) {
        super(row, column);
    }

    /**
     * This method is to convert the condition to string.
     *
     * @return string for LIVING CONDITION
     */
    @Override
    public String conditionToString() {
        return "S";
    }

    /**
     * returns the numerical value of the cell
     *
     * @return The specified number for each condition from Healthy to Dead
     */
    @Override
    public int hashCode() {
        return SICK_CELL;
    }

    /**
     * Checks if both compared cells (this and other) are
     *
     * @param other
     * @return
     */
    @Override
    public boolean equals(Object other) {
        return (other instanceof SickCell);
    }

    /**
     * Looks unto the neighbors (only healthy and sick neighbors) of the Cell and returns the cell of the new generation accordingly
     *
     * @param healthyNeighbors the amount of healthy neighbors of the cell
     * @param sickNeighbors    the amount of sick neighbors of the cell
     * @return the new cell for the next generation
     */
    public Cell nextGeneration(int healthyNeighbors, int sickNeighbors) {
        //The cell is sick

        if (healthyNeighbors < NOT_ENOUGH_HEALTHY || sickNeighbors > TOO_MANY_SICK || healthyNeighbors > ENOUGH_HEALTHY) {
            //The cell is dying
            return new DyingCell(this.cell_row, this.cell_col);
        } else {
            //The cell is healthy
            return new HealthyCell(this.cell_row, this.cell_col);
        }

    }

    /**
     * A method to see of a cell is healthy or sick.
     *
     * @return 0 when sick , 1 when healthy otherwise 2.
     */
    public int cellHealthyOrSick() {
        return IS_SICK;
    }
}
