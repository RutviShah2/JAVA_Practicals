package FileNIO;

import java.io.*;
import java.util.*;

public class SudokuValidator {

    public static int[][] readSudokuFromFile(String filename) throws IOException {
        int[][] grid = new int[9][9];
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            int row = 0;
            while ((line = br.readLine()) != null && row < 9) {
                line = line.trim();
                if (line.isEmpty()) continue; // skip blank lines

                // Accept lines either as space-separated numbers or contiguous digits
                String[] parts = line.split("\\s+");
                if (parts.length == 9) {
                    for (int col = 0; col < 9; col++) {
                        grid[row][col] = parseCell(parts[col], row, col);
                    }
                } else if (line.length() >= 9) {
                    // treat first 9 characters as digits (useful for files with "123456789")
                    for (int col = 0; col < 9; col++) {
                        grid[row][col] = parseCell(String.valueOf(line.charAt(col)), row, col);
                    }
                } else {
                    throw new IOException("Invalid row format at line " + (row + 1) + ": \"" + line + "\"");
                }
                row++;
            }
            if (row < 9) throw new IOException("File ended early: expected 9 rows, found " + row);
        }
        return grid;
    }

    private static int parseCell(String token, int row, int col) throws IOException {
        token = token.trim();
        if (token.length() == 0) throw new IOException("Empty cell at row " + (row+1) + " col " + (col+1));
        try {
            int v = Integer.parseInt(token);
            if (v < 1 || v > 9) throw new IOException("Cell out of range (1-9) at row " + (row+1) + " col " + (col+1) + ": " + v);
            return v;
        } catch (NumberFormatException e) {
            throw new IOException("Invalid number at row " + (row+1) + " col " + (col+1) + ": \"" + token + "\"");
        }
    }

    private static boolean checkRows(int[][] g, List<String> problems) {
        boolean ok = true;
        for (int r = 0; r < 9; r++) {
            boolean[] seen = new boolean[10];
            for (int c = 0; c < 9; c++) {
                int v = g[r][c];
                if (seen[v]) {
                    problems.add("Duplicate value " + v + " in row " + (r + 1));
                    ok = false;
                }
                seen[v] = true;
            }
            for (int v = 1; v <= 9; v++) {
                if (!seen[v]) {
                    problems.add("Missing value " + v + " in row " + (r + 1));
                    ok = false;
                }
            }
        }
        return ok;
    }

    private static boolean checkColumns(int[][] g, List<String> problems) {
        boolean ok = true;
        for (int c = 0; c < 9; c++) {
            boolean[] seen = new boolean[10];
            for (int r = 0; r < 9; r++) {
                int v = g[r][c];
                if (seen[v]) {
                    problems.add("Duplicate value " + v + " in column " + (c + 1));
                    ok = false;
                }
                seen[v] = true;
            }
            for (int v = 1; v <= 9; v++) {
                if (!seen[v]) {
                    problems.add("Missing value " + v + " in column " + (c + 1));
                    ok = false;
                }
            }
        }
        return ok;
    }

    private static boolean checkSubgrids(int[][] g, List<String> problems) {
        boolean ok = true;
        for (int br = 0; br < 3; br++) {
            for (int bc = 0; bc < 3; bc++) {
                boolean[] seen = new boolean[10];
                for (int r = br * 3; r < br * 3 + 3; r++) {
                    for (int c = bc * 3; c < bc * 3 + 3; c++) {
                        int v = g[r][c];
                        if (seen[v]) {
                            problems.add("Duplicate value " + v + " in 3x3 block starting at row " + (br*3+1) + " col " + (bc*3+1));
                            ok = false;
                        }
                        seen[v] = true;
                    }
                }
                for (int v = 1; v <= 9; v++) {
                    if (!seen[v]) {
                        problems.add("Missing value " + v + " in 3x3 block starting at row " + (br*3+1) + " col " + (bc*3+1));
                        ok = false;
                    }
                }
            }
        }
        return ok;
    }

    public static boolean validateSudoku(int[][] grid, List<String> problems) {
        boolean rOK = checkRows(grid, problems);
        boolean cOK = checkColumns(grid, problems);
        boolean bOK = checkSubgrids(grid, problems);
        return rOK && cOK && bOK;
    }

    private static void printGrid(int[][] g) {
        System.out.println("Sudoku grid:");
        for (int r = 0; r < 9; r++) {
            for (int c = 0; c < 9; c++) {
                System.out.print(g[r][c] + " ");
                if (c % 3 == 2 && c != 8) System.out.print("| ");
            }
            System.out.println();
            if (r % 3 == 2 && r != 8) System.out.println("------+-------+------");
        }
    }

    public static void main(String[] args) {
        String filename = args.length > 0 ? args[0] : "sudoku.txt";
        try {
            int[][] grid = readSudokuFromFile(filename);
            printGrid(grid);

            List<String> problems = new ArrayList<>();
            boolean valid = validateSudoku(grid, problems);

            System.out.println();
            if (valid) {
                System.out.println(" Valid Sudoku solution!");
            } else {
                System.out.println("Invalid Sudoku solution. Problems found:");
                for (String p : problems) {
                    System.out.println(" - " + p);
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading/processing file: " + e.getMessage());
        }
        System.out.println("24DCS120_RUTVI SHAH");
    }
}
