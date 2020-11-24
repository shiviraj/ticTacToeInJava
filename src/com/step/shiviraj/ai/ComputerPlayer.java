package com.step.shiviraj.ai;

import com.step.shiviraj.game.Board;
import com.step.shiviraj.game.Player;

public class ComputerPlayer {

    public int playMove(Board board) {
        return findBestMove(board);
    }

    private int findBestMove(Board board) {
        int bestScore = -1000;
        int bestMove = 0;

        for (int row = 0; row < board.size; row++) {
            for (int col = 0; col < board.size; col++) {
                if (board.isEmptyChar(row, col)) {
                    int score = getScore(board, row, col, Player.COMPUTER.getChar(), 0, false);
                    if (score > bestScore) {
                        bestScore = score;
                        bestMove = generateMove(board, row, col);
                    }
                }
            }
        }
        return bestMove;
    }

    private int getScore(Board board, int row, int col, char symbol, int depth, boolean isMaximising) {
        board.setChar(row, col, symbol);
        int score = miniMax(board, depth, isMaximising);
        board.setChar(row, col, '\u0000');
        return score;
    }

    private int miniMax(Board board, int depth, boolean isMaximising) {
        if (board.isWin()) return board.isPlayerOneWin() ? 10 : -10;
        if (!board.isSpaceAvailable()) return 0;

        if (isMaximising) {
            return findBestScore(board, depth, Player.COMPUTER.getChar(), false, Math::max);

        } else {
            return findBestScore(board, depth, Player.HUMAN.getChar(), true, Math::min);
        }
    }

    private int findBestScore(Board board, int depth, char symbol, boolean isMaximising, MinMax minMax) {
        int bestScore = isMaximising ? 1000 : -1000;

        for (int row = 0; row < board.size; row++) {
            for (int col = 0; col < board.size; col++) {
                if (board.isEmptyChar(row, col)) {
                    int score = getScore(board, row, col, symbol, depth + 1, isMaximising);
                    bestScore = minMax.apply(score, bestScore);
                }
            }
        }
        return bestScore;
    }

    private int generateMove(Board board, int row, int col) {
        return row * board.size + col + 1;
    }

}