package com.hm.reskill;

import java.util.Random;

public class Brick {

    private Position position;
    private char brickChar;
    private TetraBrick tetraBrick;

    public Brick(Position position, char brickChar, TetraBrick tetraBrick) {
        this.position = position;
        this.brickChar = brickChar;
        this.tetraBrick = tetraBrick;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public char getBrickChar() {
        return brickChar;
    }

    public void setBrickChar(char brickChar) {
        this.brickChar = brickChar;
    }

    public TetraBrick getTetraBrick() {
        return tetraBrick;
    }

    public void setTetraBrick(TetraBrick tetraBrick) {
        this.tetraBrick = tetraBrick;
    }

    public void createBrick(){

    }

}
