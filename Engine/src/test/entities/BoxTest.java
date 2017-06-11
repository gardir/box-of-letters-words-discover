package test.entities;

import entities.Box;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by gardir on 08.06.17.
 */
public class BoxTest {
    Box lk03_2017;
    Box lk03_2017_2;
    Box lk04_2017;

    @Before
    public void setUp() throws Exception {
        lk03_2017 = new LK3_2017();
        lk03_2017_2 = new LK3_2017();
        lk04_2017 = new LK4_2017();
    }

    @Test
    public void equals() throws Exception {
        assert lk03_2017.equals(lk03_2017_2);
        assert !lk03_2017.equals(lk04_2017);
    }

}