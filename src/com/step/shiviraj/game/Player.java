package com.step.shiviraj.game;

public enum Player {
    HUMAN('X'),
    COMPUTER('O');

    private final char symbol;

    Player(char symbol) {
        this.symbol = symbol;
    }

    public char getChar() {
        return this.symbol;
    }

}
