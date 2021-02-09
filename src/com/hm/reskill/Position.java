package com.hm.reskill;

public class Position {
        private int x;
        private int y;
        private char aChar;

    public Position(int x, int y, char aChar) {
        this.x = x;
        this.y = y;
        this.aChar = aChar;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public char getaChar() {
        return aChar;
    }
}
