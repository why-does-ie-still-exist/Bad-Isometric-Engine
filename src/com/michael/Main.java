package com.michael;

import static com.michael.symbolshorthand.*;

public class Main {

    public static void main(String[] args) {
        var view = new isoview(10);
        view.protectedDraw((x, y) -> {
            return x == 5 && y == 4;
        }, SMILE);
        view.protectedDraw((x, y) -> {
            return x == 5 && y == 5;
        }, TABLE);
        for (int i = 0; i < view.getSize(); i++) {
            System.out.println(view.getLine(i));
        }
    }

    public static void drawPlayer()

}
