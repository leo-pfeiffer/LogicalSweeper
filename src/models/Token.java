package models;

public enum Token {

    DANGER('*'),
    UNCOVERED_MINE('-'),
    MINE('m'),
    BLOCK('b'),
    UNKNOWN('?'),
    ;


    private final char character;

    Token(char c) {
        this.character = c;
    }

    public char getChar() {
        return this.character;
    }
}
