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


            int random = ThreadLocalRandom.current().nextInt(16,26);
            Brick brick = new Brick(new Position(random,1, '\u2593'),TetraBrick.L_SHAPE);

            boolean continueReadingInput = true;

            while(continueReadingInput == true) {

                // == Refresh screen for new round ==
                printOnScreen(terminal, brick);
                Brick oldBrick = new Brick(new Position(brick.getPosition().getX(), brick.getPosition().getY(), ' '));

                KeyStroke keyStroke = null;
                // TODO: CREATE A VARIABLE FOR Thread.sleep to increase gamespeed over time.
                // TODO: CREATE SOME SORT OF POINTS MECHANISM
                Thread.sleep(700);

                if (terminal.pollInput() != null) {

                    keyStroke = terminal.pollInput();

                    KeyType type = keyStroke.getKeyType();
                    Character c = keyStroke.getCharacter();
                    System.out.println("keyStroke.getKeyType(): " + type
                            + " keyStroke.getCharacter(): " + c);


                    switch (keyStroke.getKeyType()) {
                        case ArrowDown:
                            brick.moveDown();
                            break;
           /*         case ArrowUp:
                        brickPosition1.getY() -=1;
                        break;
                    case ArrowLeft:
                        brickPosition1.getY() -=1;
                        break;
                    case ArrowRight:
                        brickPosition1.getX() +=1;
                        break; */
                    }


                } else {
                    brick.moveDown();
                }

                // == REMOVE OLD BRICKS ON SCREEN ==
                printOnScreen(terminal, oldBrick);

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
    public static void printOnScreen(Terminal terminal, Brick brick) throws Exception{
        terminal.setCursorPosition(brick.getPosition().getX(), brick.getPosition().getY());
        terminal.putCharacter(brick.getChar());
        terminal.flush();
    }
}
