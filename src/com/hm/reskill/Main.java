package com.hm.reskill;

import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;

public class Main {


    public static void main(String[] args) {
        // == Lanterna Labs settings ==

        try {
            DefaultTerminalFactory tf = new DefaultTerminalFactory();
            Terminal terminal = tf.createTerminal();

            GamePlan gamePlan = new GamePlan();
            gamePlan.printGamePlan(terminal);

        } catch (
                Exception e) {
            System.out.println(e.getMessage());
        }


    }
}
