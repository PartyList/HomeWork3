public class SickCell extends HealthyCell {
    final int TOO_MANY_SICK = 2;
    final int ENOUGH_HEALTHY = 3;
    final int NOT_ENOUGH_HEALTHY = 2;
    final int SICK_CELL = 2;
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
     * In case of undefined cell we get -1 (Which defines Cell)
     */
    @Override
    public int hashCode() {
        return SICK_CELL;
    }
    /**
     * Looks unto the neighbors (only healthy and sick neighbors) of the Cell and returns the cell of the new generation accordingly
     * @param healthyNeighbors the amount of healthy neighbors of the cell
     * @param sickNeighbors the amount of sick neighbors of the cell
     * @return the new cell for the next generation
     */
    @Override
    public Cell nextGeneration(int healthyNeighbors, int sickNeighbors) {
        //The cell is sick

        if (healthyNeighbors < NOT_ENOUGH_HEALTHY || sickNeighbors > TOO_MANY_SICK || healthyNeighbors > ENOUGH_HEALTHY) {
            //The cell is dying
            return new DyingCell(this.row, this.column);
        } else {
            //The cell is healthy
            return new HealthyCell(this.row, this.column);
        }

    }
}
