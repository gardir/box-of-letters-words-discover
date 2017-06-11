package entities;

import java.util.HashMap;
import java.util.LinkedList;

/**
 * Created by gardir on 24.05.17.
 */
public class Box {
    private final Word[] words;
    private Letter[][] letters;
    private HashMap<Character, LinkedList<Letter>> firstLetters;

    public Box(char[][] boxOfLetters, String[] listOfWords) {
        this.letters = new Letter[boxOfLetters.length][];
        this.firstLetters = Letter.createLetters(letters, boxOfLetters);
        Letter.buildNeighbours(letters);
        this.words = createWords(listOfWords);
    }

    private static Word[] createWords(String[] wordlist) {
        Word[] words = new Word[wordlist.length];
        for (int i=0; i<wordlist.length; i++) {
            words[i] = new Word(wordlist[i]);
        }
        return words;
    }

    public String solve() {
        for (Letter[] letters: this.letters) {
            for (Letter l: letters) {
                l.markUnused();
            }
        }
        for (Word w: this.words) {
            char firstLetter = w.getFirstLetter();
            LinkedList<Letter> candidates = firstLetters.get(firstLetter);
            for (Letter candidate: candidates) {
                String word = w.getWord();
                if (candidate.evaluate(word)) {
                    candidate.markUsed(word.length());
                    w.markFound(candidate, candidate.getLastDirection());
                    continue;
                }
            }
        }
        for (Word word: this.words) {
            if (word.notFound()) {
                return word.getWord();
            }
        }
        return "Unsolvable";
    }

    public String getPrint() {
        StringBuilder matrix = new StringBuilder();
        for (int r=0; r<this.letters.length; r++) {
            Letter[] row = this.letters[r];
            for (int column = 0; column < row.length; column++) {
                matrix.append(" " + row[column].getToken());
                if (column + 1 != row.length) {
                    matrix.append(" |");
                } else {
                    matrix.append("\n");
                }
            }
            if (r + 1 != this.letters.length) {
                for (int i = 0; i < row.length; i++) {
                    if (i + 1 != row.length) {
                        matrix.append("---|");
                    } else {
                        matrix.append("---\n");
                    }
                }
            }
        }
        return matrix.toString();
    }

    public boolean equals(Object obj) {
        if ( obj instanceof Box ) {
            Box other_box = (Box) obj;
            if ( other_box.letters.length != this.letters.length ) {
                // Unequal number of rows
                return false;
            }
            // Equal number of rows, thus check each row
            for ( int row_index=0; row_index < this.letters.length; row_index++ ) {
                Letter[] this_row = this.letters[row_index];
                Letter[] other_row = other_box.letters[row_index];
                if ( this_row.length != other_row.length ) {
                    // Unequal number of columns
                    return false;
                }
                // Equal number of columns, check each column
                for ( int column_index=0; column_index < this_row.length; column_index++ ) {
                    Letter this_letter = this_row[column_index];
                    Letter other_letter = other_row[column_index];
                    if ( !this_letter.equals( other_letter ) ) {
                        // Letters was unequal
                        return false;
                    }
                }
            }
            // If no unequalities are found, they are equal
            return true;
        }
        return super.equals(obj);
    }

}
