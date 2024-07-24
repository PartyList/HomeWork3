public class HealthyCell extends Cell {
    private final int TOO_MANY_SICK = 3;
    private final int ENOUGH_HEALTHY = 3;
    private final int NOT_ENOUGH_HEALTHY = 2;
    private final int HEALTHY_CELL = 3;
    private final int IS_HEALTHY = 1;

    public HealthyCell(int row, int column) {
        super(row, column);
    }

    /**
     * This method is to convert the condition to string.
     *
     * @return string for LIVING CONDITION
     */
    @Override
    public String conditionToString() {
        return "H";
    }

    /**
     * returns the numerical value of the cell
     *
     * @return The specified number for each condition from Healthy to Dead
     */
    @Override
    public int hashCode() {
        return HEALTHY_CELL;
    }

    /**
     * Checks if both compared cells (this and other) are
     *
     * @param other
     * @return
     */
    @Override
    public boolean equals(Object other) {
        return (other instanceof HealthyCell);
    }

    /**
     * Looks unto the neighbors (only healthy and sick neighbors) of the Cell and returns the cell of the new generation accordingly
     *
     * @param healthyNeighbors the amount of healthy neighbors of the cell
     * @param sickNeighbors    the amount of sick neighbors of the cell
     * @return the new cell for the next generation
     */
    public Cell nextGeneration(int healthyNeighbors, int sickNeighbors) {
        //The cell is healthy
        if (sickNeighbors > TOO_MANY_SICK || healthyNeighbors < NOT_ENOUGH_HEALTHY || healthyNeighbors > ENOUGH_HEALTHY) {
            //the cell is sick
            return new SickCell(this.cell_row, this.cell_col);
        }
        return this;
    }

    /**
     * A method to see of a cell is healthy or sick.
     *
     * @return 0 when sick , 1 when healthy otherwise 2.
     */
    public int cellHealthyOrSick() {
        return IS_HEALTHY;
    }
}