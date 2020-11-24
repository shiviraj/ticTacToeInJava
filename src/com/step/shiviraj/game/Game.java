package com.step.shiviraj.game;

import com.step.shiviraj.ai.ComputerPlayer;
import com.step.shiviraj.presenter.Presenter;

public class Game {
    private final Board board;
    private final int boardSize;
    private final ComputerPlayer computerPlayer;

    public Game(int boardSize) {
        this.board = new Board(boardSize);
        this.boardSize = boardSize;
        computerPlayer = new ComputerPlayer();
    }

    public void run(Presenter presenter) {
        presenter.print(board.print());

        for (int i = 0; i < boardSize * boardSize; i++) {

            int move = computerPlayer.playMove(board);
            playMove(presenter, Player.COMPUTER, move);
            if (isGameOver()) {
                declareResult(presenter, Player.COMPUTER);
                return;
            }

            int userMove = getMove(presenter);
            playMove(presenter, Player.HUMAN, userMove);
            if (isGameOver()) {
                declareResult(presenter, Player.HUMAN);
                return;
            }
        }

    }

    private void declareResult(Presenter presenter, Player player) {
        if (board.isWin()) declareWinner(presenter, player);
        presenter.print("Game Tie");
    }

    private boolean isGameOver() {
        return board.isWin() || !board.isSpaceAvailable();
    }

    private void playMove(Presenter presenter, Player player, int move) {
        board.fill(move, player.getChar());
        presenter.print(board.print());

    }

    private void declareWinner(Presenter presenter, Player player) {
        String result = player == Player.HUMAN ? "won" : "Loss";
        presenter.print("You " + result);
    }

    private int getMove(Presenter presenter) {
        presenter.print("Enter your move:");
        int move = presenter.getUserMove();
        if (board.isValidMove(move)) {
            return move;
        } else {
            presenter.print("Please Enter correct number:");
            return getMove(presenter);
        }
    }

}
