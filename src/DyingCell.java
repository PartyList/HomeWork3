public class DyingCell extends Cell {
    final int HEALTHY_NEIGHBORS_TO_BE_HEALTHY =3;
    final int TOO_MANY_SICK = 1;
    final int DYING_CELL = 1;
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
     * In case of undefined cell we get -1 (Which defines Cell)
     */
    @Override
    public int hashCode() {
        return DYING_CELL;
    }

    /**
     * Looks unto the neighbors (only healthy and sick neighbors) of the Cell and returns the cell of the new generation accordingly
     * @param healthyNeighbors the amount of healthy neighbors of the cell
     * @param sickNeighbors the amount of sick neighbors of the cell
     * @return the new cell for the next generation
     */
    @Override
    public Cell nextGeneration(int healthyNeighbors, int sickNeighbors) {
        //The cell dies
        if (healthyNeighbors != HEALTHY_NEIGHBORS_TO_BE_HEALTHY || sickNeighbors > TOO_MANY_SICK) {
            return new DeadCell(this.row, this.column);
        }
        //The cell is healthy
        return new HealthyCell(this.row, this.column);
    }


}
