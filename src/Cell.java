public class Cell extends Position {
    final LIVINGCONDITION condition;

    public Cell(int row, int column, LIVINGCONDITION condition) {
        super(row, column);
        this.condition = condition;
    }

    /**
     * A method to see of a cell is healthy or sick.
     * @return 0 when sick , 1 when healthy otherwise 2.
     */
    public int cellHealthyOrSick(){
        if(condition == LIVINGCONDITION.HEALTHY)
            return 1;
        else if(condition == LIVINGCONDITION.SICK)
            return 0;
        else
            return 2;
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
        //The cell is Dead.
        if (this.condition == LIVINGCONDITION.DEAD && healthyNeighbors == 3) {
            //Bring the cell to life.
            return new Cell(this.row, this.column, LIVINGCONDITION.HEALTHY);
        }
        //The cell is dying
        else if (this.condition == LIVINGCONDITION.DYING) {
            //The cell dies
            if (healthyNeighbors != 3 || sickNeighbors > 1) {
                return new Cell(this.row, this.column, LIVINGCONDITION.DEAD);
            }
            //The cell is healthy
            return new Cell(this.row, this.column, LIVINGCONDITION.HEALTHY);
        }
        //The cell is sick
        else if (this.condition == LIVINGCONDITION.SICK) {
            if (healthyNeighbors < 2 || sickNeighbors > 2 || healthyNeighbors > 3) {
                //The cell is dying
                return new Cell(this.row, this.column, LIVINGCONDITION.DYING);
            } else {
                //The cell is healthy
                return new Cell(this.row, this.column, LIVINGCONDITION.HEALTHY);
            }
        }
        //The cell is healthy
        else if (this.condition == LIVINGCONDITION.HEALTHY) {
            if (sickNeighbors > 3 || healthyNeighbors < 2||healthyNeighbors > 3) {
                //the cell is sick
                return new Cell(this.row, this.column, LIVINGCONDITION.SICK);
            }
        }
        //The cell stays the same
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
                return 3;
            case SICK:
                return 2;
            case DYING:
                return 1;
            case DEAD:
                return 0;
            default:
                return -1;

        }
    }
}


