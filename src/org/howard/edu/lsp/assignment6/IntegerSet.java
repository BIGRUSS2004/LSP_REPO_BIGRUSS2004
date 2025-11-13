package org.howard.edu.lsp.assignment6;

import java.util.ArrayList;
import java.util.List;

/**
 * Models a mathematical set of integers backed by an ArrayList.
 * No duplicates are allowed and basic set operations are supported.
 */
public class IntegerSet  {
    private List<Integer> set = new ArrayList<Integer>();

    /**
     * Clears the internal representation of the set.
     */
    public void clear() {
        set.clear();
    }

    /**
     * Returns the number of elements in the set.
     *
     * @return number of elements
     */
    public int length() {
        return set.size();
    }

    /**
     * Returns true if the 2 sets are equal, false otherwise.
     * Two sets are equal if they contain all of the same values in any order.
     *
     * @param o object to compare with
     * @return true if contents are the same set of integers
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (!(o instanceof IntegerSet)) {
            return false;
        }

        IntegerSet other = (IntegerSet) o;

        // Same size first
        if (this.length() != other.length()) {
            return false;
        }

        // Since duplicates are not allowed, containsAll both ways
        return this.set.containsAll(other.set) && other.set.containsAll(this.set);
    }

    /**
     * Returns true if the set contains the value, otherwise false.
     *
     * @param value value to look for
     * @return true if value is present
     */
    public boolean contains(int value) {
        return set.contains(value);
    }

    /**
     * Returns the largest item in the set.
     *
     * @return largest integer in the set
     * @throws IllegalStateException if the set is empty
     */
    public int largest() {
        if (set.isEmpty()) {
            throw new IllegalStateException("Cannot get largest from an empty set.");
        }

        int max = set.get(0);
        for (int value : set) {
            if (value > max) {
                max = value;
            }
        }
        return max;
    }

    /**
     * Returns the smallest item in the set.
     *
     * @return smallest integer in the set
     * @throws IllegalStateException if the set is empty
     */
    public int smallest() {
        if (set.isEmpty()) {
            throw new IllegalStateException("Cannot get smallest from an empty set.");
        }

        int min = set.get(0);
        for (int value : set) {
            if (value < min) {
                min = value;
            }
        }
        return min;
    }

    /**
     * Adds an item to the set or does nothing if already present.
     *
     * @param item item to add
     */
    public void add(int item) {
        if (!set.contains(item)) {
            set.add(item);
        }
    }

    /**
     * Removes an item from the set or does nothing if not there.
     *
     * @param item item to remove
     */
    public void remove(int item) {
        // Remove by value, not index
        set.remove(Integer.valueOf(item));
    }

    /**
     * Set union, modifies this to contain all unique elements
     * that are in this set or in other.
     *
     * @param other the other IntegerSet
     */
    public void union(IntegerSet other) {
        for (int value : other.set) {
            if (!this.set.contains(value)) {
                this.set.add(value);
            }
        }
    }

    /**
     * Set intersection, modifies this to contain only elements present
     * in both this set and other.
     *
     * @param other the other IntegerSet
     */
    public void intersect(IntegerSet other) {
        // retainAll keeps only elements that are also in other.set
        set.retainAll(other.set);
    }

    /**
     * Set difference (this \ other), modifies this set to remove all elements
     * that are found in other.
     *
     * @param other the other IntegerSet
     */
    public void diff(IntegerSet other) {
        set.removeAll(other.set);
    }

    /**
     * Set complement, modifies this to become (other \ this).
     * After this call, this set contains elements that are in other
     * but not in the original this set.
     *
     * @param other the other IntegerSet
     */
    public void complement(IntegerSet other) {
        List<Integer> result = new ArrayList<Integer>();
        for (int value : other.set) {
            if (!this.set.contains(value)) {
                result.add(value);
            }
        }
        set.clear();
        set.addAll(result);
    }

    /**
     * Returns true if the set is empty, false otherwise.
     *
     * @return true if there are no elements in the set
     */
    public boolean isEmpty() {
        return set.isEmpty();
    }

    /**
     * Returns a String representation of the set.
     * Elements are shown inside square brackets, for example: [1, 2, 3]
     *
     * @return formatted String for this set
     */
    @Override
    public String toString() {
        return set.toString();
    }
}
