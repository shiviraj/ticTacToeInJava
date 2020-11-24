package com.step.shiviraj.game;

import java.util.Arrays;
import java.util.stream.IntStream;

public class Board {

    private final char[][] board;
    public final int size;

    public Board(int size) {
        this.board = new char[size][size];
        this.size = size;
    }

    public String print() {
        StringBuilder sb = new StringBuilder("");
        for (char[] row : board) {
            for (char c : row) {
                sb.append(c).append("|");
            }
            sb.append("\n");
        }
        return sb.toString();

    }

    public void fill(int move, char symbol) {
        int rowNo = findRow(move);
        int colNo = findCol(move);
        this.board[rowNo][colNo] = symbol;
    }

    private int findCol(int move) {
        return (move - 1) % board.length;
    }

    private int findRow(int move) {
        return (move - 1) / board.length;
    }

    public boolean isValidMove(int move) {
        int row = findRow(move);
        int col = findCol(move);
        try {
            return board[row][col] == '\u0000';
        } catch (ArrayIndexOutOfBoundsException e) {
            return false;
        }
    }

    public boolean isWin() {
        return isRowsWin() || isColsWin() || isDimensionsWin();
    }

    private boolean isDimensionsWin() {
        return isFirstDimensionalEqual() || isSecondDimensionalEqual();
    }

    private boolean isSecondDimensionalEqual() {
        int lastCol = board.length - 1;
        for (int col = 0; col < board.length; col++)
            if (board[lastCol - col][col] != board[lastCol][0] || board[lastCol - col][col] == '\u0000') return false;
        return true;
    }

    private boolean isFirstDimensionalEqual() {
        for (int col = 0; col < board.length; col++)
            if (board[col][col] != board[0][0] || board[col][col] == '\u0000') return false;
        return true;
    }

    private boolean isColsWin() {
        return IntStream.range(0, board.length).anyMatch(this::isColsEqual);
    }

    private boolean isColsEqual(int col) {
        for (char[] chars : board) {
            if (chars[col] != board[0][col] || chars[col] == '\u0000') return false;
        }
        return true;
    }

    private boolean isRowsWin() {
        return Arrays.stream(board).anyMatch(this::areAllElementsEqual);
    }

    private boolean areAllElementsEqual(char[] row) {
        for (char c : row) {
            if (c != row[0] || c == '\u0000') return false;
        }
        return true;
    }

    public boolean isPlayerOneWin() {
        int filledPositions = 0;
        for (char[] row : board) {
            for (char c : row)
                if (c != '\u0000')
                    filledPositions++;
        }
        return filledPositions % 2 == 1;
    }

    public boolean isSpaceAvailable() {
        for (char[] row : board)
            for (char c : row) {
                if (c == '\u0000') return true;
            }
        return false;
    }

    public void setChar(int row, int col, char c) {
        board[row][col] = c;
    }

    public boolean isEmptyChar(int row, int col) {
        return board[row][col] == '\u0000';
    }
}