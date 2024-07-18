public class Cell extends Position {
    final LIVINGCONDITION condition;

    public Cell(int row, int column, LIVINGCONDITION condition) {
        super(row, column);
        this.condition = condition;
    }

    /**
     * A method to see of a cell is dead or alive(healthy,sick or dying).
     * @return 0 when dead otherwise 1.
     */
    public int isCellDead(){
        return condition == LIVINGCONDITION.DEAD ? 0 : 1;
    }

    /**
     * !!!!!!!!!!!!!!!!!MAYBE CHANGE THIS TO toString method and override?!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
     * This method is to convert the condition to string.
     * @return string for LIVING CONDITION
     */
    public String conditionToString(){
        LIVINGCONDITION condition = this.condition;
        switch (condition){
            case HEALTHY:
                return "H";
            case SICK:
                return "S";
            case DYING:
                return "D";
            case DEAD:
                return "-";
            default:
                return "";
        }
    }

    /**
     * Looks unto the neighbors (only healthy and sick neighbors) of the Cell and returns the cell of the new generation accordingly
     * @param healthyNeighbors the amount of healthy neighbors of the cell
     * @param sickNeighbors the amount of sick neighbors of the cell
     * @return the new cell for the next generation
     */
    public Cell nextGeneration(int healthyNeighbors, int sickNeighbors) {
        if (this.condition == LIVINGCONDITION.DEAD && healthyNeighbors == 3) {
            return new Cell(this.row, this.column, LIVINGCONDITION.HEALTHY);
        }
        //Cell is Sick
        else if (this.condition == LIVINGCONDITION.DYING) {
            if (healthyNeighbors != 3 || sickNeighbors > 1) {
                return new Cell(this.row, this.column, LIVINGCONDITION.DEAD);
            }
            return new Cell(this.row, this.column, LIVINGCONDITION.HEALTHY);
        } else if (this.condition == LIVINGCONDITION.SICK) {
            if (healthyNeighbors < 2 || sickNeighbors > 3) {
                return new Cell(this.row, this.column, LIVINGCONDITION.DYING);
            } else {
                return new Cell(this.row, this.column, LIVINGCONDITION.HEALTHY);
            }
        } else if (this.condition == LIVINGCONDITION.HEALTHY) {
            if (sickNeighbors > 3 || healthyNeighbors < 2) {
                return new Cell(this.row, this.column, LIVINGCONDITION.SICK);
            }
        }
        return this;
    }

    /**
     * we look if this and other cell have the same condition and returns the result.
     *
     * @param other other cell
     * @return whether this cell and other cell are in the same condition
     */
    @Override
    public boolean equals(Object other) {
        if(other == null || getClass() != other.getClass()) return false;

        return this.condition == ((Cell)other).condition;
    }

    /**
     * returns the numerical value of the cell
     *
     * @return The specified number for each condition from 0-4.
     * In case of error we get -1
     */
    @Override
    public int hashCode() {
        switch (this.condition) {
            case HEALTHY:
                return 0;
            case SICK:
                return 1;
            case DYING:
                return 2;
            case DEAD:
                return 3;
            default:
                return -1;

        }
    }
}


