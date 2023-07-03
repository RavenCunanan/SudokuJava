public class Cell {
    private int value;
    private boolean isFixed; 
  
    /**
    * Save the arguments into the instance variables.
    */
    public Cell(int value, boolean isFixed) {
      this.value=value;
      this.isFixed=isFixed;
    }

    /**
    * Return the value of this cell.
    */
    public int get() {
      return value;
    }

    /**
    * Set the value of this cell to `value` if `isFixed` is false
    * Otherwise, do nothing if `isFixed` is true
    */
    public void set(int value) {
      if(!isFixed)
        this.value=value;
    }

    /**
    * If `isFixed` is `false`, set the value of the cell to 0
    * Otherwise, do nothing if `isFixed` is true.
    */
    public void clear() {
      if (!isFixed)
            this.value = 0;
    }
  
    /**
    * Set the value of this cell to `value` if 
    *   (a) `isFixed` is false OR 
    *   (b) this is the first time the cell has been assigned a value (i.e., the current value is 0)
    * Set `isFixed` to `true` in this method. `isFixed` should never be set 
    * to `false` again after it is set to `true`
    */
    public void setFixed(int value) {
      if (!isFixed || this.value == 0) {
            isFixed = true;
            this.value = value;
        }
    }
}
