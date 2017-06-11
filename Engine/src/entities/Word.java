package entities;

/**
 * Created by gardir on 24.05.17.
 */
public class Word {

    private final String word;
    private boolean found = false;
    private Letter letter;
    private Letter.DIRECTIONS direction;

    public Word (String word) {
        this.word = word.toUpperCase();
    }


    public char getFirstLetter() {
        return word.charAt(0);
    }

    public String getWord() {
        return word;
    }

    public void markFound(Letter l, Letter.DIRECTIONS d) {
        this.letter = l;
        this.direction = d;
        this.found = true;
    }

    public boolean notFound() {
        return !found;
    }
}
