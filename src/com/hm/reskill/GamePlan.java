package com.hm.reskill;

import com.googlecode.lanterna.terminal.Terminal;

import java.util.ArrayList;
import java.util.List;

public class GamePlan {

    private List<Position> gameplan;

    public GamePlan() {
        this.gameplan = new ArrayList<>();
        for (int i = 0; i < 23; i++) {
            gameplan.add(new Position(15, i, '\u2588'));
            gameplan.add(new Position(26, i, '\u2588'));
        }

        for (int i = 15; i < 26; i++) {
            gameplan.add(new Position(i, 0, '\u2588'));
            gameplan.add(new Position(i, 23, '\u2588'));
        }
    }

    public void printGamePlan(Terminal t){
        try {
            for (Position p : this.gameplan) {
                t.setCursorPosition(p.getX(), p.getY());
                t.putCharacter(p.getaChar());

            }
            t.flush();
        } catch (Exception e) {
            System.out.println("PrintGame: " + e.getMessage());
        }
    }

}
