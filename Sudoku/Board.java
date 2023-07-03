public class Board {
    private Block[] blocks;
    private Row[] rows;
    private Column[] columns;

    /**
    * Initialize `blocks` as a 9-element array whose elements are of type `Block`.
    *   `blockNumber` starts at 0 in the top left of the board and is enumerated like so:
    *
    *    block0, block1, block2
    *    block3, block4, block5
    *    block6, block7, block8
    *
    * Initialize `rows` as a 9-element array whose elements are of type `Row`. 
    *    `rowNumber` begins at 0 at the top of the board and increases by one for each row below it. i.e.,
    *
    *     row0
    *     row1
    *     row2
    *     ...
    *     row8

    * Initialize `columns` as a 9-element array whose elements are of type `Column`
    *    `columnNumber` begins at 0 at the left of the board and increases by one for each row to the right. i.e.
    *
    *     col0, col1, col2 ... col8
    */
    public Board() {
        blocks = new Block[9];
      rows = new Row[9];
      columns = new Column[9];

      for (int i = 0; i < 9; i++) {
        blocks[i] = new Block(i);
        rows[i] = new Row(i);
        columns[i] = new Column(i);
      }
    }

    /**
    * Map index (row, column) to the corresponding block number. Here is a full illustration
    *
    *          col0   col1   col2   col3   col4   col5   col6   col7   col8
    *   row0  |                    |                   |                   |
    *   row1  |       block0       |       block1      |       block2      |
    *   row2  |____________________|___________________|___________________|
    *   row3  |                    |                   |                   | 
    *   row4  |      block3        |       block4      |      block5       |
    *   row5  |____________________|___________________|___________________|
    *   row6  |                    |                   |                   |
    *   row7  |      block6        |       block7      |      block8       |
    *   row8  |____________________|___________________|___________________|
    *
    *   Hint: recall that division in Java is floor division, meaning we round down to the nearest integer.
    *         For example, 3 / 2 == 1.
    */
    private int calculateBlockNumber(int row, int column) {
       return ((row / 3) * 3) + (column / 3);
    }

    /**
    * Retrieve the integer value of the cell located at `[row][column]`
    * You can select from either `rows`, `columns`, or `blocks`.
    * Hint: remember to use the `get` method you already defined for `Row` / `Column` / `Block`
    */
    public int get(int row, int column) {
      int rowResult = rows[row].get(column);
      int colResult = columns[column].get(row);
      int blockResult = blocks[calculateBlockNumber(row, column)].get(row % 3, column % 3);

      if (rowResult != colResult || colResult != blockResult) {
        throw new IllegalStateException("Inconsistent value between rows / cols / blocks for row " + row +
          " and column " + column + ". Row was " + rowResult + ", col was " + colResult + " block was " + blockResult);
      }
      return rowResult;
    }

    /**
    * Clear the cell located at index [row][column] in the `Row`, `Column`, and `Block` representations
    * Note that `row` and `column` can both be 0 through 8 (inclusive, inclusive).
    * You will need to fill out `calculateBlockNumber` as well as convert `row` and `column`
    *     to coordinates within each block itself.
    * For example, for row = 4, column = 5, we should have `blockNumber = 4` because coordinates (4, 5) map 
    *     to the center block (see the diagram in the comments under the constructor for this class).
    *     Within block4, the (row, column) we should be clearing are (1, 2). See illustration of block4 below
    *
    *            col3    col4    col5
    *      row3 |      |      |
    *      row4 |      |      |   X
    *      row5 |      |      |
    *
    *      Within the local 3x3 block of block4, we can see the index row=1, column=2 corresponds to the global
    *      index row=4,column=5
    *
    * Don't forget to use the `clear` methods you already wrote for `Row`, `Column`, and `Block` as part of your answer

    * Hint: refer back to the slides in the "Operators" lesson that listed all the Java operators. Which one would
    *       be helpful here?
    */
    public void clear(int row, int column) {
        int blockIdx = calculateBlockNumber(row, column);
        blocks[blockIdx].clear(row % 3, column % 3);
        rows[row].clear(column);
        columns[column].clear(row);
    }

    /**
    * Hint: remember to use the `set` method you already defined for `Row`
    */
    private void setRow(int row, int column, int value) {
      rows[row].set(column, value);
    }

      /**
    * Hint: remember to use the `setFixed` method you already defined for `Row`
    */
    private void setRowFixed(int row, int column, int value) {
      rows[row].setFixed(column, value);
    }

    /**
    * Hint: remember to use the `set` method you already defined for `Column`
    */
    private void setColumn(int row, int column, int value) {
      columns[column].set(row, value);
    }

    /**
    * Hint: remember to use the `setFixed` method you already defined for `Column`
    */
    private void setColumnFixed(int row, int column, int value) {
       columns[column].setFixed(row, value);
    }

    /**
    * Hint: remember to use the `set` method you already defined for `Block`
    * Hint: Recall that the `set` method in `Block` only accepts `row` and `column` arguments
    *       that are 0, 1, or 2. Refer back to the slides in the "Operators" lesson that listed 
    *       all the Java operators. Which one would be helpful here?
    */
    private void setBlock(int row, int column, int value) {
      int blockIdx = calculateBlockNumber(row, column);
      blocks[blockIdx].set(row % 3, column % 3, value);
    }

    /**
    * Hint: remember to use the `setFixed` method you already defined for `Block`
    * Hint: Recall that the `setFixed` method in `Block` only accepts `row` and `column` arguments
    *       that are 0, 1, or 2. Refer back to the slides in the "Operators" lesson that listed 
    *       all the Java operators. Which one would be helpful here?
    */
    private void setBlockFixed(int row, int column, int value) {
      int blockIdx = calculateBlockNumber(row, column);
      blocks[blockIdx].setFixed(row % 3, column % 3, value);
    }

    /**
     * Set the cell value at index [row][column] to be `value` in `blocks`, `rows` and `columns`.
     * Hint: remember to use the setRow / setBlock / setColumn methods you already defined in this class
     */
    public void set(int row, int column, int value) {
      setRow(row, column, value);
      setColumn(row, column, value);
      setBlock(row, column, value);
    }
  
    /**
    * Set the cell value at index [row][column] to be `value`. If there is a conflict after setting this value,
    * print out the conflict (hint: set the `verbose` flag in `hasConflict` to `true`) and clear the cell.
    * Hint: remember to use the `set`, `hasConflict`, and `clear` methods in this class as part of your answer
    */
    public void safeSet(int row, int column, int value) {
      set(row, column, value);
      if (hasConflict(true)) {
        clear(row, column);
      }
    }

    /**
     * Set and fix the cell value at index [row][column] to be `value` in `blocks`, `rows` and `columns`.
     * Hint: remember to use the setRowFixed / setColumnFixed / setBlockFixed methods you already defined
     */
    public void setFixed(int row, int column, int value) {
      setRowFixed(row, column, value);
      setColumnFixed(row, column, value);
      setBlockFixed(row, column, value);
    }

    /**
    * Return true if the entire board has been filled in AND has no conflicts.
    * Hint: remember to use the `hasConflict` method in this class
    */
    public boolean isSolved() {
    for (int i = 0; i < 9; i++) {
        for (int j = 0; j < 9; j++) {
          if (rows[i].get(j) == 0) {
            return false;
          }
        }
      }
      return !hasConflict(false);
    }

    /**
    * Return true if there is any conflict within any row, column, or block.
    * Return false otherwise.
    * Hint: remember to use the `hasConflict` method defined in `Block`, `Row`, and `Column`
    */
    public boolean hasConflict(boolean verbose) {
        for (int i = 0; i < blocks.length; i++) {
        if (blocks[i].hasConflict(verbose)) {
          return true;
        }
      }

      for (int i = 0; i < columns.length; i++) {
        if (columns[i].hasConflict(verbose)) {
          return true;
        }
      }

      for (int i = 0; i < rows.length; i++) {
        if (rows[i].hasConflict(verbose)) {
          return true;
        }
      }
      return false;
    }

    /**
    * Return a string representation of the board so users can read the board and play.
    * This method has been done for you.
    */
    public String toString() {
      StringBuilder result = new StringBuilder();
      for (int k = 0; k < 24; k++) {
        result.append("-");
      }
      result.append("-\n");
      
      for (int i = 0; i < 9; i++) {
        result.append("| ");
        for (int j = 0; j < 8; j++) {
          if (rows[i].get(j) == 0) {
            result.append(" ");
          } else {
            result.append(rows[i].get(j));
          }
          result.append(" ");
          if (j == 2 || j == 5) {
            result.append("| ");
          }
        }

        if (rows[i].get(8) == 0) {
          result.append(" ");
        } else {
          result.append(rows[i].get(8));
        }
        result.append(" |\n");

        if ((i + 1) % 3 == 0) {
          for (int k = 0; k < 24; k++) {
            result.append("-");
          }
          result.append("-\n");
        }
      }
      return result.toString();
    }
}
