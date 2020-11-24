package com.step.shiviraj;

import com.step.shiviraj.game.Game;
import com.step.shiviraj.presenter.ConsolePresenter;

import java.util.Scanner;

import static java.lang.System.in;

public class TicTacToe {
    public static void main(String[] args) {
        int boardSize = Integer.parseInt(args[0]);
        Game game = new Game(boardSize);
        ConsolePresenter consolePresenter = new ConsolePresenter(System.out::println, new Scanner(in)::nextInt);
        game.run(consolePresenter);
    }
}
