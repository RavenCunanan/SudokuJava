public class Row {
    private Cell[] cells;
    private int rowNumber;

    /**
    * Initialize `cells` as a 9-element array whose elements are of type `Cell`
    * Make sure you also populate this newly created array with Cell instances after you’ve created the array
    *   Hint: the default cell value should be 0; what should the default `isFixed` be?
    * Save `rowNumber` into the corresponding instance variable 
    */
    public Row(int rowNumber) {
        cells = new Cell[9];
        for (int i =0;i<9;i++){
          cells[i]=new Cell(0,false);
        }
      this.rowNumber=rowNumber;
    }

    /**
    * Return the integer value of the cell located at index `column` in this row 
    * Hint: use the `get` method you already wrote for the `Cell` class as part of your answer
    */
    public int get(int column) {
      return cells[column].get();
    }

    /**
    * Clear the cell located at index `column`
    * Hint: use the `clear` method you already wrote for the `Cell` class as part of your answer
    */
    public void clear(int column) {
         cells[column].clear();
    }

    /**
    * Set and fix the value for the cell located at index `column` to `value`
    * Hint: use the `setFixed` method you already wrote for the `Cell` class as part of your answer
    */
    public void setFixed(int column, int value) {
        cells[column].setFixed(value);
    }

    /**
    * Set the value for the cell located at index `column` to `value`. 
    * The cell's `isFixed` property should not change as a result of this method.
    * Hint: use the `set` method you already wrote for the `Cell` class as part of your answer
    */
    public void set(int column, int value) {
       cells[column].set(value);
    }

  
    public boolean hasConflict(boolean verbose) {
       boolean[] found = new boolean[10];
        for (int i = 0; i < cells.length; i++) {
            int cellValue = cells[i].get();
            if (found[cellValue] && cellValue > 0) {
                if (verbose) {
                  System.out.println("Row " + rowNumber + " has multiple " + cellValue + "s.");
                }
                return true; 
            } else {
                found[cellValue] = true;
            }
        }
        return false;
    }
}
