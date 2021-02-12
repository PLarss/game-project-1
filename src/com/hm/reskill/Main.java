package com.hm.reskill;

import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

import static com.googlecode.lanterna.TextColor.ANSI.BLUE;

public class Main {


    public static void main(String[] args) {

        try {
            DefaultTerminalFactory tf = new DefaultTerminalFactory();
            Terminal terminal = tf.createTerminal();
     //       terminal.setBackgroundColor(BLUE);
            terminal.setCursorVisible(false);

            GamePlan gamePlan = new GamePlan();
            gamePlan.printGamePlan(terminal);

            int random = ThreadLocalRandom.current().nextInt(16,26);
            Position brickPosition1 = new Position(random,1,'\u2593');
            Brick brick1 = new Brick(brickPosition1,'\u2593',TetraBrick.L_SHAPE);

            boolean continueReadingInput = true;

            while(continueReadingInput == true) {
                terminal.setCursorPosition(brickPosition1.getX(), brickPosition1.getY());
                terminal.putCharacter(brick1.getBrickChar());
                terminal.flush();

                KeyStroke keyStroke = null;
                Thread.sleep(300);
                if (terminal.pollInput() != null) {
                    keyStroke = terminal.pollInput();


                    KeyType type = keyStroke.getKeyType();
                    Character c = keyStroke.getCharacter();
                    System.out.println("keyStroke.getKeyType(): " + type
                            + " keyStroke.getCharacter(): " + c);

                    switch (keyStroke.getKeyType()) {
                        case ArrowDown:
                            brick1.moveDown();
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
                    brick1.moveDown();
                }
            }
            terminal.close();
        } catch (
                Exception e) {
            System.out.println(e.getMessage());
        }


    }
}
