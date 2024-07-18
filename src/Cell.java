public class Cell {
    final LIVINGCONDITION condition;
    final int row, column;


    public Cell(int row, int column, LIVINGCONDITION condition) {
        this.row = row;
        this.column = column;
        this.condition = condition;
    }

    public Cell(int row, int column) {
        this(row, column, LIVINGCONDITION.HEALTHY);
    }

    /**
     * Looks unto the neighbors (only healthy and sick neighbors) of the Cell and returns the cell of the new generation accordingly
     * @param healthyNeightbors the amount of healthy neightbors of the cell
     * @param sickNeightbors the amount of sick neighbors of the cell
     * @return the new cell for the next generation
     */
    public Cell nextGeneration(int healthyNeightbors, int sickNeightbors) {
        if (this.condition == LIVINGCONDITION.DEAD && healthyNeightbors == 3) {
            return new Cell(this.row, this.column, LIVINGCONDITION.HEALTHY);
        }
        //Cell is Sick
        else if (this.condition == LIVINGCONDITION.DYING) {
            if (healthyNeightbors != 3 || sickNeightbors > 1) {
                return new Cell(this.row, this.column, LIVINGCONDITION.DEAD);
            }
            return new Cell(this.row, this.column, LIVINGCONDITION.HEALTHY);
        } else if (this.condition == LIVINGCONDITION.SICK) {
            if (healthyNeightbors < 2 || sickNeightbors > 3) {
                return new Cell(this.row, this.column, LIVINGCONDITION.DYING);
            } else {
                return new Cell(this.row, this.column, LIVINGCONDITION.HEALTHY);
            }
        } else if (this.condition == LIVINGCONDITION.HEALTHY) {
            if (sickNeightbors > 3 || healthyNeightbors < 2) {
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
     * @override
     */
    public boolean equals(Cell other) {
        return (this.condition == other.condition);
    }

    /**
     * returns the numerical value of the cell
     *
     * @return The specified number for each condition from 0-4.
     * In case of error we get -1
     * @override
     */
    public int hashcode() {
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


