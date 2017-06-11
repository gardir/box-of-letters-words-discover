package entities;

import java.util.HashMap;
import java.util.LinkedList;

/**
 * Created by gardir on 24.05.17.
 */
public class Letter {

    private static final char UNUSED_TOKEN = ' ';
    private DIRECTIONS lastDirection;
    private boolean used = true;

    public static HashMap<Character, LinkedList<Letter>> createLetters(Letter[][] letters, char[][] boxOfLetters) {
        HashMap<Character, LinkedList<Letter>> firstLetters = new HashMap<>();
        for (int r=0; r<boxOfLetters.length; r++) {
            char[] row = boxOfLetters[r];
            Letter[] rowLetters = new Letter[row.length];
            for (int c=0; c<row.length; c++) {
                char token = Character.toUpperCase(row[c]);
                Letter letter = new Letter(token, r, c);
                rowLetters[c] = letter;
                if (!firstLetters.containsKey(token)) {
                    firstLetters.put(token, new LinkedList<>());
                }
                firstLetters.get(token).add(letter);
            }
            letters[r] = rowLetters;
        }
        return firstLetters;
    }

    public static void buildNeighbours(Letter[][] letters) {
        for (int r=0; r<letters.length; r++) {
            for (int c=0; c<letters[r].length; c++) {
                Letter letter = letters[r][c];
                if (r == 0) {
                    // first row
                    letter.giveMiddleNeighbours(letters[r]);
                    letter.giveLowerNeighbours(letters[r+1]);
                } else if (r+1 == letters.length) {
                    // last row
                    letter.giveUpperNeighbours(letters[r-1]);
                    letter.giveMiddleNeighbours(letters[r]);
                } else {
                    // middle row
                    letter.giveUpperNeighbours(letters[r-1]);
                    letter.giveMiddleNeighbours(letters[r]);
                    letter.giveLowerNeighbours(letters[r+1]);
                }
            }
        }
    }

    public DIRECTIONS getLastDirection() {
        return lastDirection;
    }

    public char getToken() {
        if (used) {
            return token;
        } else {
            return UNUSED_TOKEN;
        }
    }

    public void markUsed(int length) {
        this.markUsed(lastDirection, length-1);
    }

    public void markUsed(DIRECTIONS d, int count) {
        this.used = true;
        if (count > 0) {
            neighbours.get(d).markUsed(d, count-1);
        }
    }

    public void markUnused() {
        this.used = false;
    }

    public enum DIRECTIONS {
        NW, N, NE, W, E, SW, S, SE
    }

    public final char token;
    private final int row;
    private final int column;
    private HashMap<DIRECTIONS, Letter> neighbours = new HashMap<>();

    public Letter (char token, int row, int column) {
        this.token = Character.toUpperCase(token);
        this.row = row;
        this.column = column;
    }

    public void giveUpperNeighbours(Letter[] row) {
        if ( this.column != 0 ) {
            neighbours.put(DIRECTIONS.NW, row[this.column - 1 ]);
        }
        neighbours.put(DIRECTIONS.N, row[this.column]);
        if ( this.column + 1 != row.length ) {
            neighbours.put(DIRECTIONS.NE, row[this.column + 1 ]);
        }
    }

    public void giveMiddleNeighbours(Letter[] row) {
        if ( this.column != 0 ) {
            neighbours.put(DIRECTIONS.W, row[this.column - 1]);
        }
        if ( this.column+1 != row.length) {
            neighbours.put(DIRECTIONS.E, row[this.column + 1]);
        }
    }

    public void giveLowerNeighbours(Letter[] row) {
        if ( this.column != 0 ) {
            neighbours.put(DIRECTIONS.SW, row[this.column - 1 ]);
        }
        neighbours.put(DIRECTIONS.S, row[this.column]);
        if ( this.column + 1 != row.length ) {
            neighbours.put(DIRECTIONS.SE, row[this.column + 1 ]);
        }
    }

    public boolean findWordInDirection(String word, DIRECTIONS direction) {
        // Start looking from index 0
        return this.findWordInDirection(word, direction, 0);
    }

    public boolean findWordInDirection(String word, DIRECTIONS direction, int index) {
        if ( word.length() == index + 1 ) {
            // Last character in word, return result from this Letter
            return this.equalsCharacter( word.charAt(index) );
        } else if ( word.length() > index &&
                this.equalsCharacter( word.charAt(index) ) &&
                    neighbours.containsKey(direction)) {
            // Still character in words, and this Letter equaled character at current position
            lastDirection = direction;
            return neighbours.get(direction).findWordInDirection(word, direction, index + 1 );
        }
        // word is shorter than current index or not identical to next letter
        return false;
    }

    private boolean equalsCharacter(char c) {
        return this.token == c || this.token == Character.toUpperCase( c );
    }

    public boolean evaluate(String word) {
        for (DIRECTIONS d : DIRECTIONS.values()) {
            if (this.findWordInDirection(word, d)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean equals (Object o) {
        if ( o instanceof Character ) {
            Character other_token = (Character) o;
            return this.token == other_token || this.token == Character.toUpperCase(other_token);
        } else if ( o instanceof Letter ) {
            Letter other_letter = (Letter) o;
            return this.equalsCharacter( other_letter.token );
        }
        return super.equals(o);
    }

}
