package com.michael;

public enum symbolshorthand {
    TIP("/\\"),
    LSLANT("//"),
    RSLANT(`\\`),
    BOX("["),
    BLANK(" "),
    SMILE(`😊`),
    TABLE(`┬─┬`);

    private char[] chars;

    symbolshorthand(String s) {
        this.chars = s.toCharArray();
    }

    public char[] toCharArray() {
        return this.chars;
    }

    public int length() {
        return this.chars.length;
    }
}
