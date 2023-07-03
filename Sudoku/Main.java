import java.util.Scanner;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.ArrayList;

public class Main {
  public static void main(String[] args) {
    Board solution = generateRandomSolution(0, 0, new Board());
    Board puzzle = clearRandomCells(solution, 58);
    System.out.println(puzzle);
    System.out.println("Collumns and Rows go from 0-8 ");
    System.out.println("Commands are : set, clear, solve, new");
    System.out.println("For set and clear you need to put in column number, row number, and the number to be inserted, Example: set 2 5 7");
    

    Scanner userInput = new Scanner(System.in);
    while (!puzzle.isSolved()) {
      System.out.println("Enter command: ");
      String[] cmd = userInput.nextLine().split(" ");
      int row = 0;
      int col = 0;
      if (cmd.length >= 3) {
        row = Integer.parseInt(cmd[1]);
        col = Integer.parseInt(cmd[2]);
      }
      if (cmd[0].equals("set")) {
        int value = Integer.parseInt(cmd[3]);
        puzzle.safeSet(row, col, value);
      } else if (cmd[0].equals("clear")) {
        puzzle.clear(row, col);
      } else if (cmd[0].equals("solve")) {
        puzzle = solution;
      } else if (cmd[0].equals("new")) {
        solution = generateRandomSolution(0, 0, new Board());
        puzzle = clearRandomCells(solution, 58);
      } else {
        System.out.println("Command not recognized");
        continue;
      }
      System.out.println(puzzle);
    }
    System.out.println("Congratulations! You have solved the Sudoku puzzle. Click the \"Run\" button to get a new puzzle");
  }

  public static Board generateRandomSolution(int row, int col, Board board) {
    List<Integer> nums = Arrays.asList(new Integer[] { 1, 2, 3, 4, 5, 6, 7, 8, 9 });
    Collections.shuffle(nums);
    for (int i = 0; i < 9; i++) {
      board.set(row, col, nums.get(i));
      if (!board.hasConflict(false)) {
        if ((row == 8 && col == 8)) {
          return board;
        } else if (col == 8) {
          if (generateRandomSolution(row + 1, 0, board) != null) {
            return board;
          }
        } else if (generateRandomSolution(row, col + 1, board) != null) {
          return board;
        }
      }
    }
    board.clear(row, col);
    return null;
  }

  public static Board clearRandomCells(Board board, int numToRemove) {
    Board result = new Board();
    List<Integer> indices = new ArrayList<>();
    for (int n = 0; n < 81; n++) {
      indices.add(n);
    }
    Collections.shuffle(indices);
    indices = indices.subList(0, numToRemove);

    for (int r = 0; r < 9; r++) {
      for (int c = 0; c < 9; c++) {
        if (!indices.contains(r * 9 + c)) {
          result.setFixed(r, c, board.get(r, c));
        }
      }
    }
    return result;
  }
}