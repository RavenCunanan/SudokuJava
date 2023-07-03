public class Column {
    private Cell[] cells;
    private int columnNumber;

    /**
    * Initialize `cells` as a 9-element array whose elements are of type `Cell`
    * Make sure you also populate this newly created array with Cell instances after you’ve created the array
    *   Hint: the default cell value should be 0; what should the default `isFixed` be?
    * Save `columnNumber` into the corresponding instance variable 
    */
    public Column(int columnNumber) {
        cells = new Cell[9];
        for (int i = 0; i < 9; i++) {
            cells[i] = new Cell(0, false);
        }
        this.columnNumber = columnNumber;
    }

    /**
    * Return the integer value of the cell located at index `row` in this column 
    * Hint: use the `get` method you already wrote for the `Cell` class as part of your answer
    */
    public int get(int row) {
     return cells[row].get();
    }

    /**
    * Clear the cell located at index `row`
    * Hint: use the `clear` method you already wrote for the `Cell` class as part of your answer
    */
    public void clear(int row) {
      cells[row].clear();
    }

    /**
    * Set the value for the cell located at index `row` to `value`. 
    * The cell's `isFixed` property should not change as a result of this method.
    * Hint: use the `set` method you already wrote for the `Cell` class as part of your answer
    */
    public void set(int row, int value) {
      cells[row].set(value);
    }

    /**
    * Set and fix the value for the cell located at index `row` to `value`
    * Hint: use the `setFixed` method you already wrote for the `Cell` class as part of your answer
    */
    public void setFixed(int row, int value) {
      cells[row].setFixed(value);
    }

    /**
    * Determine whether there is a conflict in this column. A conflict arises when
    * a number other than the default value occurs more than once in this column.

    * If `verbose` is true, print out the value that occurs more than once and
    * also print out the `columnNumber` (this printed message can be in any form you like;
    * this is just a suggestion to make debugging easier).
    * Tip: You can add numbers to printed messages like so: `System.out.println("Repeated number: " + 1);`
    *      You can also replace the 1 with a variable like `x`. As long as `x` is not in double quotes,
    *      Java will evaluate x and concatenate your string with the evaluated result of `x`.
    *
    * Hint: you can do this without using anything beyond what we've learned so far in the course.
    * Option 1: use several boolean variables to keep track of which values are in this column
    *           and update them accordingly as you iterate through the cell values
    * Option 2 (harder): use a boolean array to accomplish the same thing
    *
    * Hint: recall that we're using 0 as the default value for cells. In your code,
    *       make sure that two or more 0's in the same column is NOT a conflict.
    */
    public boolean hasConflict(boolean verbose) {
      
      boolean[] found = new boolean[10];
      for (int i = 0; i < cells.length; i++) {
        int cellValue = cells[i].get();
        if (found[cellValue] && cellValue > 0) {
            if (verbose) {
              System.out.println("Column " + columnNumber + " has multiple " + cellValue + "s.");
            }
            return true; // Each cellValue can only appear once
        } else {
            found[cellValue] = true;
        }
      }
      return false;
    }
}
