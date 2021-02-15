package com.hm.reskill;

import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;

import java.io.IOException;
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

            // == Skapa ny bricka
            Brick brick = createNewBrick();
            Brick oldBrick = null;
            boolean continueReadingInput = true;

            while(continueReadingInput == true) {

                // == Refresh screen for new round ==
                printOnScreen(terminal, brick, oldBrick);

                KeyStroke keyStroke = null;
                do {
                    Thread.sleep(50);
                    keyStroke = terminal.pollInput();
                    boolean createNew = moveDown(terminal, brick, gamePlan);
                    if (createNew) {
                        // TODO: Create new and dispose old;
                        brick = null;
                        oldBrick = null;
                        removeFullRows(terminal, gamePlan);
                        brick = createNewBrick();
                    }

                } while (keyStroke == null);


                // TODO: CREATE A VARIABLE FOR Thread.sleep to increase gamespeed over time.
                // TODO: CREATE SOME SORT OF POINTS MECHANISM

                KeyType type = keyStroke.getKeyType();
                Character c = keyStroke.getCharacter();
             //   System.out.println("keyStroke.getKeyType(): " + type
             //            + " keyStroke.getCharacter(): " + c);


                // == Avsluta programmet om man matar in q
                if (c == Character.valueOf('q')) {
                    continueReadingInput = false;
                    printMessage(terminal);
                    Thread.sleep(1500);
                    terminal.bell();
                    terminal.close();
                }


                oldBrick = new Brick(new Position(brick.getPosition().getX(), brick.getPosition().getY(), ' '));
                switch (keyStroke.getKeyType()) {
                     case ArrowDown:
                         moveDown(terminal, brick, gamePlan);
                         break;
                      case ArrowLeft:
                         moveLeft(terminal, brick, gamePlan);
                         break;
                     case ArrowRight:
                         moveRight(terminal, brick, gamePlan);
                         break;
                }
            // TODO: CHECK IF FULLROWS AND REMOVE ROW

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

    public static void removeFullRows(Terminal terminal, GamePlan gamePlan) throws Exception{
        // TODO: uppdatera arrayen i GamePlan.java för att ta bort en rad. Se till att flytta ner allt oxå.

        // TODO: när den nya arrayen är uppdaterad i Gameplan.java så rita ut den i Lanterna
        // här är en array. hoppa till alla positioner och sedan skriv ut vår '\2593' när hela loopen är genomspolad
        // så kan man avsluta med terminal.flush
        gamePlan.getCurrentStatus();
        //terminal.setCursorPosition();
        //terminal.putCharacter();
        terminal.flush();
    }

    public static boolean isColliding(Brick brick, GamePlan gamePlan) {
        boolean isColliding = gamePlan.isColliding(brick);


        return isColliding;
    }

    public static boolean moveDown(Terminal terminal, Brick brick, GamePlan gamePlan) throws Exception{
        Brick oldBrick = getOldBrick(brick);
        if (!isColliding(brick, gamePlan)) {
            brick.moveDown();
            gamePlan.setCurrentStatus(brick, oldBrick);
        } else {
            // Thread.sleep(10000);
            return true;
        }
        printOnScreen(terminal, brick, oldBrick);
        return false;
    }

    public static boolean moveRight(Terminal terminal, Brick brick, GamePlan gamePlan) throws Exception {
        Brick oldBrick = getOldBrick(brick);
        if (!isColliding(brick, gamePlan)) {
            brick.moveRight();
            gamePlan.setCurrentStatus(brick, oldBrick);
        } else {
            return true;
        }
        printOnScreen(terminal, brick, oldBrick);
        return false;
    }

    public static boolean moveLeft(Terminal terminal, Brick brick, GamePlan gamePlan) throws Exception {
        Brick oldBrick = getOldBrick(brick);
        if (! isColliding(brick, gamePlan)){
            brick.moveLeft();
            gamePlan.setCurrentStatus(brick, oldBrick);
        } else{
            return true;
        }
        printOnScreen(terminal, brick, oldBrick);
        return false;
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

    public static void printMessage(Terminal terminal) throws Exception {
        terminal.putCharacter('G');
        terminal.putCharacter('A');
        terminal.putCharacter('M');
        terminal.putCharacter('E');
        terminal.putCharacter(' ');
        terminal.putCharacter('O');
        terminal.putCharacter('V');
        terminal.putCharacter('E');
        terminal.putCharacter('R');
        terminal.putCharacter('!');
        terminal.flush();
    }
}
