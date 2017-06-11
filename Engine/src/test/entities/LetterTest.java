package test.entities;


import entities.Letter;
import org.junit.runner.JUnitCore;

import static entities.Letter.DIRECTIONS.*;


/**
 * Created by gardir on 24.05.17.
 */
public class LetterTest extends JUnitCore {

    Letter one;
    Letter one_equals;
    Letter two;
    Letter norwegian;
    Letter[][] letters;
    public static final char[][] letterBox = new char[][]{
            new char[]{'x', 'h', 't', 'i'},
            new char[]{'i', 'e', 'r', 'g'},
            new char[]{'l', 'i', 't', 't'},
            new char[]{'v', 'v', 'n', 'a'}
    };

    @org.junit.Before
    public void setUp() throws Exception {
        one = new Letter('a', 0, 0);
        one_equals = new Letter('a', 0, 0);
        two = new Letter('x', 0, 0);
        norwegian = new Letter('ø', 0, 0);
        letters = new Letter[letterBox.length][];
        Letter.createLetters(letters, letterBox);
        Letter.buildNeighbours(letters);
    }

    @org.junit.Test
    public void equalEvaluate() {
        assert one.equals(one);
        assert two.equals(two);
        assert one.equals(one_equals);
        assert !one.equals(two);
    }

    @org.junit.Test
    public void singleEvaluate() throws Exception {
        assert one.findWordInDirection("a", E);
        assert !one.findWordInDirection("ab", E);
        assert !one.findWordInDirection("b", E);
        assert one.findWordInDirection("la", E, 1);
        assert two.findWordInDirection("x", E);
        assert !two.findWordInDirection("xyz", E);
        assert !two.findWordInDirection("y", E);
        assert two.findWordInDirection("wx", E, 1);
        assert norwegian.findWordInDirection("ø", E);
        assert !norwegian.findWordInDirection("øredøv", E);
        assert !norwegian.findWordInDirection("ære", E);
        assert norwegian.findWordInDirection("dø", E, 1);
    }

    @org.junit.Test
    public void neighbourEvaluate() throws Exception {
        assert letters[0][1].findWordInDirection("hei", S);
        assert letters[2][0].findWordInDirection("litt", E);
        assert letters[1][3].findWordInDirection("grei", W);
        assert !letters[1][1].findWordInDirection("tatt", NE);
    }

    @org.junit.Test
    public void evaluate() throws Exception {
        assert letters[0][1].evaluate("hei");
        assert !letters[0][0].evaluate("hei");
        assert letters[2][0].evaluate("litt");
        assert letters[1][3].evaluate("grei");
        assert !letters[1][1].evaluate("tatt");

    }

}