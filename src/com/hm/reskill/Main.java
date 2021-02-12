package com.hm.reskill;

import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;

import java.util.concurrent.ThreadLocalRandom;

public class Main {

    public static void main(String[] args) {


        try {
            // == INIT Google Lanterna ==
            DefaultTerminalFactory tf = new DefaultTerminalFactory();
            Terminal terminal = tf.createTerminal();
            terminal.setCursorVisible(false);

            // == INIT GAMEPLAN ==
            GamePlan gamePlan = new GamePlan();
            gamePlan.printGamePlan(terminal);

            Brick brick = createNewBrick();
            Brick oldBrick = null;
            boolean continueReadingInput = true;

            while(continueReadingInput == true) {

                // == Refresh screen for new round ==
                printOnScreen(terminal, brick, oldBrick);

                KeyStroke keyStroke = null;
                do {
                    Thread.sleep(900);
                    keyStroke = terminal.pollInput();
                    moveDown(terminal, brick);
                } while (keyStroke == null);

                // TODO: CREATE A VARIABLE FOR Thread.sleep to increase gamespeed over time.
                // TODO: CREATE SOME SORT OF POINTS MECHANISM

                KeyType type = keyStroke.getKeyType();
                Character c = keyStroke.getCharacter();
                System.out.println("keyStroke.getKeyType(): " + type
                         + " keyStroke.getCharacter(): " + c);

                oldBrick = new Brick(new Position(brick.getPosition().getX(), brick.getPosition().getY(), ' '));
                switch (keyStroke.getKeyType()) {
                     case ArrowDown:
                         moveDown(terminal, brick);
                         break;
                      case ArrowLeft:
                         moveLeft(terminal, brick);
                         break;
                     case ArrowRight:
                         moveRight(terminal, brick);
                         //brick.moveRight();
                          break;
                }

                // == REMOVE OLD BRICKS ON SCREEN ==
                // printOnScreen(terminal, brick, oldBrick);

            }

            // == Close some resources ==

            terminal.close();
        } catch (
                Exception e) {
            System.out.println(e.getMessage());
        }
        finally {

        }
    }

    public static void moveDown(Terminal terminal, Brick brick) throws Exception{
        Brick oldBrick = getOldBrick(brick);
        brick.moveDown();
        printOnScreen(terminal, brick, oldBrick);
    }

    public static void moveRight(Terminal terminal, Brick brick) throws Exception {
        Brick oldBrick = getOldBrick(brick);
        brick.moveRight();
        printOnScreen(terminal, brick, oldBrick);
    }

    public static void moveLeft(Terminal terminal, Brick brick) throws Exception {
        Brick oldBrick = getOldBrick(brick);
        brick.moveLeft();
        printOnScreen(terminal, brick, oldBrick);
    }

    public static Brick getOldBrick(Brick currentBrick) {
        Brick oldBrick = new Brick(new Position(currentBrick.getPosition().getX(), currentBrick.getPosition().getY(), ' '));
        return oldBrick;
    }

    public static Brick createNewBrick() {
        Brick brick = new Brick(new Position(ThreadLocalRandom.current().nextInt(16,26),1, '\u2593'),TetraBrick.L_SHAPE);
        return brick;
    }

    public static void printOnScreen(Terminal terminal, Brick brick, Brick oldBrick) throws Exception{
        if (oldBrick != null) {
            terminal.setCursorPosition(oldBrick.getPosition().getX(), oldBrick.getPosition().getY());
            terminal.putCharacter(oldBrick.getChar());
        }

        terminal.setCursorPosition(brick.getPosition().getX(), brick.getPosition().getY());
        terminal.putCharacter(brick.getChar());
        terminal.flush();
    }
}
