package com.hm.reskill;

import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

import static com.googlecode.lanterna.TextColor.ANSI.BLUE;

public class Main {


    public static void main(String[] args) {
        // == Lanterna Labs settings ==

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
            terminal.setCursorPosition(brickPosition1.getX(), brickPosition1.getY());
            terminal.putCharacter(brick1.getBrickChar());
            terminal.flush();

        } catch (
                Exception e) {
            System.out.println(e.getMessage());
        }


    }
}
