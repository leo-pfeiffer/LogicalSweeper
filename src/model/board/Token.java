package model.board;

import java.util.HashSet;

/**
 * Enum for the possible Tokens of the ObscuredSweeper game
 * */
public enum Token {

    DANGER('*'),
    UNCOVERED_MINE('-'),
    MINE('m'),
    BLOCK('b'),
    UNKNOWN('?'),
    ONE('1'),
    TWO('2'),
    THREE('3'),
    FOUR('4'),
    FIVE('5'),
    SIX('6'),
    SEVEN('7'),
    EIGHT('8'),
    NINE('9'),
    ZERO('0'),
    ;


    private final char character;

    public static final HashSet<Token> clueTokens = new HashSet<>();

    static {
        clueTokens.add(ONE);
        clueTokens.add(TWO);
        clueTokens.add(THREE);
        clueTokens.add(FOUR);
        clueTokens.add(FIVE);
        clueTokens.add(SIX);
        clueTokens.add(SEVEN);
        clueTokens.add(EIGHT);
        clueTokens.add(NINE);
        clueTokens.add(ZERO);
    }

    Token(char c) {
        this.character = c;
    }

    public char getChar() {
        return this.character;
    }

    public static boolean isClue(char c) {
        for (Token t : clueTokens) {
            if (t.getChar() == c) {
                return true;
            }
        }
        return false;
    }
}
