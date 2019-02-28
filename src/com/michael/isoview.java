package com.michael;

import org.jetbrains.annotations.NotNull;

import java.security.InvalidParameterException;

import static com.michael.symbolshorthand.*;

@NotNull
public class isoview {

    private char[][] view;
    private int size;


    public isoview(int size) {
        this.size = sizeHandler(size);
        System.gc();
        view = new char[this.size][this.size];
        populate(this.view, true);
    }

    private void populate(char[][] a, boolean ifbkg) {
        if (ifbkg) {
            Drawable background = (x, y) -> {
                return y != 0;
            };
            draw(background, BOX, a);
        }
        Drawable trtriangle = (x, y) -> {
            return x - y > this.size / 2;
        };
        Drawable bltriangle = (x, y) -> {
            return y - x > this.size / 2;
        };
        Drawable tltriangle = (x, y) -> {
            return x + y + 1 < this.size / 2;
        };
        Drawable brtriangle = (x, y) -> {
            return x + y >= this.size * 3 / 2;
        };
        Drawable diamondtip = (x, y) -> {
            return y == 0 && x == (this.size / 2) - 1;
        };
        Drawable larch = (x, y) -> {
            return x + y + 1 == this.size / 2 && y != 0;
        };
        Drawable rarch = (x, y) -> {
            return x - y + 1 == this.size / 2 && y != 0 && x != this.size - 1;
        };
        draw(trtriangle, BLANK, a);
        draw(bltriangle, BLANK, a);
        draw(tltriangle, BLANK, a);
        draw(brtriangle, BLANK, a);
        draw(larch, LSLANT, a);
        draw(diamondtip, TIP, a);
        draw(rarch, RSLANT, a);
    }

    private int sizeHandler(int size) {
        if (size >= 6) {
            if (isEven(size)) {
                return size;
            } else {
                return size + 1;
            }
        }
        return 6;
    }

    public String getLine(int i) {
        return String.copyValueOf(this.view[i]);
    }

    public int getSize() {
        return this.size;
    }

    private void arrayWrite(int x, int y, symbolshorthand s, char[][] a) {
        if (a.length != 0) {
            if (a.length != a[0].length) {
                throw new InvalidParameterException("char[][] passed to arrayWrite is not square");
            }
        } else {
            throw new InvalidParameterException("char[][] passed to arrayWrite is empty");
        }
        if (x > a.length - 1 || x < 0 || y > a.length - 1 || y < 0) {
            throw new InvalidParameterException("Coordinate passed to arrayWrite is out of bounds");
        }
        switch ((s.length() < 0) ? 0 : s.length()) {
            case 0:
                throw new InvalidParameterException("symbolshorthand passed to arrayWrite has negative or zero length");
            case 1:
                a[y][x] = s.toCharArray()[0];
                break;
            default:
                for (int i = 0; i < s.length(); i++) {
                    if (x < a.length) {
                        a[y][x + i] = s.toCharArray()[i];
                    } else {
                        throw new ArrayIndexOutOfBoundsException("Drawable passed to arrayWrite's length has exceeded isoview " + this + "'s size");
                    }
                }
                break;
        }
    }

    private boolean isEven(int i) {
        return (i & 1) != 1;
    }

    private void draw(Drawable d, symbolshorthand s, char[][] a) {
        for (int y = 0; y < a.length; y++) {
            for (int x = 0; x < a.length; x++) {
                if (d.isValid(x, y)) {
                    arrayWrite(x, y, s, a);
                }
            }
        }
    }

    public void protectedDraw(Drawable d, symbolshorthand s) {
        var testbefore = this.view.clone();
        var testafter = this.view.clone();
        for (int y = 0; y < this.size; y++) {
            for (int x = 0; x < this.size; x++) {
                if (d.isValid(x, y)) {
                    arrayWrite(x, y, s, testafter);
                    arrayWrite(x, y, s, testbefore);
                }
            }
        }
        populate(testafter, false);
        if (java.util.Arrays.deepEquals(testafter, testbefore)) {
            draw(d, s, this.view);
        } else {
            throw new InvalidParameterException("Drawing out of bounds on protectedDraw");
        }
    }
}
