package com.hm.reskill;

public class Brick {

    private Position position;
    private TetraBrick tetraBrick;

    // == Constructor ==
    public Brick(Position position, TetraBrick tetraBrick) {
        this.position = position;
        this.tetraBrick = tetraBrick;
    }

    public Brick(Position position) {
        this.position = position;
    }

    // == Functions ==
    public void moveDown (){
        this.position.moveDown();
        System.out.println("Brick.Java move down");
    }

    public void moveLeft (){
        this.position.moveLeft();
        System.out.println("Brick.Java move left");
    }

    public void moveRight (){
        this.position.moveRight();
        System.out.println("Brick.Java move right");
    }

    public char getChar(){
        return this.position.getaChar();
    }
    // == GETTERS / SETTERS ==
    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }
}
