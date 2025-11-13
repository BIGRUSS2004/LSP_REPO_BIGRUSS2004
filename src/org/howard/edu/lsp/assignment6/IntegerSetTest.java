package org.howard.edu.lsp.assignment6;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;


/**
 * JUnit tests for IntegerSet.
 */
public class IntegerSetTest {

    @Test
    public void testClearLengthAndIsEmpty() {
        IntegerSet set = new IntegerSet();
        assertTrue(set.isEmpty());
        assertEquals(0, set.length());

        set.add(1);
        set.add(2);
        assertFalse(set.isEmpty());
        assertEquals(2, set.length());

        set.clear();
        assertTrue(set.isEmpty());
        assertEquals(0, set.length());
    }

    @Test
    public void testAddNoDuplicates() {
        IntegerSet set = new IntegerSet();
        set.add(1);
        set.add(1);
        set.add(2);

        assertTrue(set.contains(1));
        assertTrue(set.contains(2));
        assertEquals(2, set.length());
    }

    @Test
    public void testRemove() {
        IntegerSet set = new IntegerSet();
        set.add(1);
        set.add(2);
        set.add(3);

        set.remove(2);
        assertFalse(set.contains(2));
        assertEquals(2, set.length());

        // Removing non existing element should do nothing
        set.remove(99);
        assertEquals(2, set.length());
    }

    @Test
    public void testContains() {
        IntegerSet set = new IntegerSet();
        set.add(5);
        assertTrue(set.contains(5));
        assertFalse(set.contains(10));
    }

    @Test
    public void testLargestOnNonEmptySet() {
        IntegerSet set = new IntegerSet();
        set.add(3);
        set.add(10);
        set.add(7);

        assertEquals(10, set.largest());
    }

    @Test
    public void testSmallestOnNonEmptySet() {
        IntegerSet set = new IntegerSet();
        set.add(3);
        set.add(10);
        set.add(7);

        assertEquals(3, set.smallest());
    }

    @Test
    public void testLargestOnEmptySetThrows() {
        IntegerSet set = new IntegerSet();
        assertThrows(IllegalStateException.class, set::largest);
    }

    @Test
    public void testSmallestOnEmptySetThrows() {
        IntegerSet set = new IntegerSet();
        assertThrows(IllegalStateException.class, set::smallest);
    }

    @Test
    public void testEqualsTrueSameElementsDifferentOrder() {
        IntegerSet set1 = new IntegerSet();
        set1.add(1);
        set1.add(2);
        set1.add(3);

        IntegerSet set2 = new IntegerSet();
        set2.add(3);
        set2.add(2);
        set2.add(1);

        assertTrue(set1.equals(set2));
        assertTrue(set2.equals(set1));
    }

    @Test
    public void testEqualsFalseDifferentContents() {
        IntegerSet set1 = new IntegerSet();
        set1.add(1);
        set1.add(2);

        IntegerSet set2 = new IntegerSet();
        set2.add(1);
        set2.add(3);

        assertFalse(set1.equals(set2));
        assertFalse(set2.equals(set1));
    }

    @Test
    public void testUnionDisjointSets() {
        IntegerSet set1 = new IntegerSet();
        set1.add(1);
        set1.add(2);

        IntegerSet set2 = new IntegerSet();
        set2.add(3);
        set2.add(4);

        set1.union(set2);

        assertEquals(4, set1.length());
        assertTrue(set1.contains(1));
        assertTrue(set1.contains(2));
        assertTrue(set1.contains(3));
        assertTrue(set1.contains(4));

        // set2 should not be changed by union
        assertEquals(2, set2.length());
        assertTrue(set2.contains(3));
        assertTrue(set2.contains(4));
    }

    @Test
    public void testUnionWithOverlap() {
        IntegerSet set1 = new IntegerSet();
        set1.add(1);
        set1.add(2);

        IntegerSet set2 = new IntegerSet();
        set2.add(2);
        set2.add(3);

        set1.union(set2);

        assertEquals(3, set1.length());
        assertTrue(set1.contains(1));
        assertTrue(set1.contains(2));
        assertTrue(set1.contains(3));
    }

    @Test
    public void testIntersect() {
        IntegerSet set1 = new IntegerSet();
        set1.add(1);
        set1.add(2);
        set1.add(3);

        IntegerSet set2 = new IntegerSet();
        set2.add(2);
        set2.add(3);
        set2.add(4);

        set1.intersect(set2);

        assertEquals(2, set1.length());
        assertTrue(set1.contains(2));
        assertTrue(set1.contains(3));
        assertFalse(set1.contains(1));
        assertFalse(set1.contains(4));
    }

    @Test
    public void testIntersectDisjoint() {
        IntegerSet set1 = new IntegerSet();
        set1.add(1);
        set1.add(2);

        IntegerSet set2 = new IntegerSet();
        set2.add(3);
        set2.add(4);

        set1.intersect(set2);

        assertTrue(set1.isEmpty());
    }

    @Test
    public void testDiff() {
        IntegerSet set1 = new IntegerSet();
        set1.add(1);
        set1.add(2);
        set1.add(3);

        IntegerSet set2 = new IntegerSet();
        set2.add(2);
        set2.add(4);

        set1.diff(set2);

        assertEquals(2, set1.length());
        assertTrue(set1.contains(1));
        assertTrue(set1.contains(3));
        assertFalse(set1.contains(2));
    }

    @Test
    public void testComplement() {
        IntegerSet base = new IntegerSet();
        base.add(2);
        base.add(4);

        IntegerSet other = new IntegerSet();
        other.add(1);
        other.add(2);
        other.add(3);
        other.add(4);

        // complement should turn base into (other \ base) = {1, 3}
        base.complement(other);

        assertEquals(2, base.length());
        assertTrue(base.contains(1));
        assertTrue(base.contains(3));
        assertFalse(base.contains(2));
        assertFalse(base.contains(4));
    }

    @Test
    public void testComplementWhenThisIsEmpty() {
        IntegerSet base = new IntegerSet();
        IntegerSet other = new IntegerSet();
        other.add(5);
        other.add(6);

        base.complement(other);

        assertEquals(2, base.length());
        assertTrue(base.contains(5));
        assertTrue(base.contains(6));
    }

    @Test
    public void testToStringFormat() {
        IntegerSet set = new IntegerSet();
        assertEquals("[]", set.toString());

        set.add(1);
        set.add(2);
        String s = set.toString();

        assertTrue(s.startsWith("["));
        assertTrue(s.endsWith("]"));
        assertTrue(s.contains("1"));
        assertTrue(s.contains("2"));
    }
}
