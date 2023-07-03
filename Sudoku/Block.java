public class Block {
    Cell[][] cells;
    private int blockNumber;

    /**
    * Initialize `cells` as a 3x3 array whose elements are of type `Cell`.
    * Make sure you also populate this newly created array with Cell instances after you’ve created the array
    *   Hint: the default cell value should be 0; what should the default `isFixed` be?
    *   Hint: a 3x3 array can be initialized like so: `new Cell[3][3]`.
    *         Selecting `this.cells[0]`, `this.cells[1]`, or this.cells[2] 
    *         would return an array of type `Cell[]` with length 3
    * Save `blockNumber` into the corresponding instance variable 
    */
    public Block(int blockNumber) {
      cells = new Cell[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                cells[i][j] = new Cell(0, false);
            }
        }
        this.blockNumber = blockNumber;
    }

    /**
    * Return the integer value of the cell located at `cells[row][column]` in this block.
    * `row` and `column` can only be 0, 1, or 2
    * Hint: use the `get` method you already wrote for the `Cell` class as part of your answer
    *       but do NOT use the `get` method from `Row` or `Column`
    * Hint: to access an element in the 0th row and 2nd column of `cells`, write `cells[0][2]`
    */
    public int get(int row, int column) {
      return cells[row][column].get();
    }

    /**
    * Set the value of the cell located at `cells[row][column]` to `value`.
    * `row` and `column` can only be 0, 1, or 2
    * The value of `isFixed` in that cell should not be changed as a result of this call.
    * Hint: use the `set` method you already wrote for the `Cell` class as part of your answer
    *       but do NOT use the `set` method from `Row` or `Column`
    */
    public void set(int row, int column, int value) {
      cells[row][column].set(value);
    }

    /**
    * Set and fix the value of the cell located at `cells[row][column]` to `value`. 
    * `row` and `column` can only be 0, 1, or 2
    * Hint: use the `setFixed` method you already wrote for the `Cell` class as part of your answer
    *       but do NOT use the `setFixed` method from `Row` or `Column`
    */
    public void setFixed(int row, int column, int value) {
      cells[row][column].setFixed(value);
    }
  
    /**
    * Clear the value of cell located at `cells[row][column]`
    * `row` and `column` can only be 0, 1, or 2
    * Hint: use the `clear` method you already wrote for the `Cell` class as part of your answer
    *       but do NOT use the `clear` method from `Row` or `Column`
    */
    public void clear(int row, int column) {
      cells[row][column].clear();
    }

    /**
    * Determine whether there is a conflict in this block. A conflict arises when
    * a number other than the default value occurs more than once in this block.

    * If `verbose` is true, print out the value that occurs more than once and
    * also print out the `blockNumber` (this printed message can be in any form you like;
    * this is just a suggestion to make debugging easier).
    * Tip: You can add numbers to printed messages like so: `System.out.println("Repeated number: " + 1);`
    *      You can also replace the 1 with a variable like `x`. As long as `x` is not in double quotes,
    *      Java will evaluate x and concatenate your string with the evaluated result of `x`.
    *
    * Hint: you can do this without using anything beyond what we've learned so far in the course.
    * Option 1: use several boolean variables to keep track of which values are in this row
    *           and update them accordingly as you iterate through the cell values
    * Option 2 (harder): use a boolean array to accomplish the same thing
    *
    * Hint: recall that we're using 0 as the default value for cells. In your code,
    *       make sure that two or more 0's in the same block is NOT a conflict.
    */
    public boolean hasConflict(boolean verbose) {
      boolean[] found = new boolean[10];
      for (int i = 0; i < cells.length; i++) {
        for (int j = 0; j < cells[0].length; j++) {
            int cellValue = cells[i][j].get();
            if (found[cellValue] && cellValue > 0) {
                if (verbose) {
                    System.out.println("Block " + blockNumber + " has multiple " + cellValue + "s.");
                }
                return true; // Each cellValue can only appear once
            } else {
                found[cellValue] = true;
            }
        }
      }
      return false;
    }
}
