package com.hm.reskill;

import com.googlecode.lanterna.terminal.Terminal;

import java.util.ArrayList;
import java.util.List;

public class GamePlan {

    private List<Position> gameplan;
    private char[][] currentStatus = {
            {' ', ' ', ' ', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c'},
            {' ', ' ', ' ', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c'},
            {' ', ' ', ' ', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c'},
            {' ', ' ', ' ', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c'},
            {' ', ' ', ' ', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c'},
            {'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c'},
            {'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c'},
            {'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c'},
            {'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c'},
            {'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c'},
            {'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c'},
            {'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c'},
            {'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c'},
            {'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c'},
            {'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c'},
            {'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c'},
            {'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c'},
            {'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c'},
            {'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c'},
            {'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c'},
            {'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c'},
            {'\u2593', '\u2593', '\u2593', '\u2593', '\u2593', '\u2593', '\u2593', '\u2593', '\u2593', '\u2593', '\u2593'}};

    // new char[23][11];

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

    public void printGamePlan(Terminal t) {
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

    public void printCurrentPosition(Brick brick) {
        for (char[] chars : currentStatus) {
            System.out.print("Current row: ");
            for (char c : chars) {
                System.out.print(c);
            }
            System.out.print("\n");
        }
    }

    public boolean isColliding(Brick brick) {
        // Kolla i rad brick.getY om det finns en upptagen plats.
        // == WE have an offset between UI and Char array of 16, always use it when doing calutations for Collision ==
        for (int i = 0; i < currentStatus[brick.getPosition().getY() - 1].length; i++) {
            if (Character.compare(currentStatus[brick.getPosition().getY() - 1][brick.getPosition().getX() - 16], brick.getChar()) == 0) {
                return true;
            }
        }
        System.out.println("\n");
        return false;
    }

    public void setCurrentStatus(Brick brick, Brick oldBrick) {
        // == WE have an offset between UI and Char array of 16, always use it when doing calutations for Collision ==
        if (brick != null) {
            this.currentStatus[brick.getPosition().getY() - 2][brick.getPosition().getX() - 16] = brick.getChar();
        }
        if (oldBrick != null) {
            if ((oldBrick.getPosition().getY() - 1) > 0) {
                this.currentStatus[oldBrick.getPosition().getY() - 2][oldBrick.getPosition().getX() - 16] = ' ';
            }
        }
    }

    public char[][] getCurrentStatus() {
        return currentStatus;
    }
}
