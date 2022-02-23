package logic;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class BooleanArrayTest {

    @Test
    public void testFromArray() {
        BooleanArray array = new BooleanArray(new boolean[] {true, false, true});
        assertEquals(array.length(), 3);
        assertTrue(array.get(0));
        assertFalse(array.get(1));
        assertTrue(array.get(2));
    }

    @Test
    public void testFromBooleanArray() {
        BooleanArray array = new BooleanArray(new boolean[] {true, false, true});
        BooleanArray array1 = new BooleanArray(array);
        assertEquals(array1.length(), 3);
        assertTrue(array1.get(0));
        assertFalse(array1.get(1));
        assertTrue(array1.get(2));
    }

    @Test
    public void testSetter() {
        BooleanArray array = new BooleanArray(3);
        array.set(0, true);
        array.set(1, true);
        array.set(2, false);
        assertEquals(array.length(), 3);
        assertTrue(array.get(0));
        assertTrue(array.get(1));
        assertFalse(array.get(2));
        array.set(2, true);
        assertTrue(array.get(2));
    }

    @Test
    public void testToArray() {
        BooleanArray array = new BooleanArray(new boolean[] {true, false, true});
        boolean[] array1 = array.toArray();
        assertEquals(array1.length, 3);
        assertTrue(array1[0]);
        assertFalse(array1[1]);
        assertTrue(array1[2]);
    }

    @Test
    public void testToString() {
        BooleanArray array = new BooleanArray(new boolean[] {true, false, true});
        assertEquals("[true,false,true]", array.toString());
    }

    @Test
    public void testToStringEmpty() {
        BooleanArray array = new BooleanArray(new boolean[] {});
        assertEquals("[]", array.toString());
    }
}
