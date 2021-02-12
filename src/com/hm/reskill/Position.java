package com.hm.reskill;

public class Position {
        private int x;
        private int y;
        private char aChar;
        private int[] cord;

    public Position(int x, int y, char aChar) {
        this.x = x;
        this.y = y;
        this.aChar = aChar;
    }

    public Position(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void moveDown(){
        this.y = this.y + 1;
        System.out.println("Position.Java move down");
    }

    public void moveLeft(){
        if (this.x > 16) {
            this.x = this.x - 1;
        }
        System.out.println("Position.Java move left");
    }

    public void moveRight(){
        if (this.x < 25) {
            this.x = this.x + 1;
        }
        System.out.println("Position.Java move right");
    }
    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setaChar(char aChar) {
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
