package logic;

import java.util.Arrays;

/**
 * An array of boolean values. This is used to define some custom behaviour not available by the primitive.
 */
public class BooleanArray {
    private final boolean[] array;

    public BooleanArray(int size) {
        this.array = new boolean[size];
    }

    public BooleanArray(boolean[] array) {
        this.array = new boolean[array.length];
        System.arraycopy(array, 0, this.array, 0, array.length);
    }

    public BooleanArray(BooleanArray array) {
        this.array = new boolean[array.length()];
        System.arraycopy(array.toArray(), 0, this.array, 0, array.length());
    }

    /**
     * Return a copy of the underlying array
     *
     * @return A new array of boolean values.
     */
    public boolean[] toArray() {
        boolean[] copy = new boolean[this.array.length];
        System.arraycopy(this.array, 0, copy, 0, array.length);
        return copy;
    }

    /**
     * Return the value of the array at the given index
     *
     * @param index The index of the value to get.
     * @return The value of the array at the given index.
     */
    public boolean get(int index) {
        return array[index];
    }

    /**
     * Set the value at the given index to the given value
     *
     * @param index The index of the array element to set.
     * @param value the value to set the array element to.
     */
    public void set(int index, boolean value) {
        array[index] = value;
    }

    /**
     * Return the length of the array
     *
     * @return The length of the array.
     */
    public int length() {
        return array.length;
    }

    /**
     * Swap the elements at the indices idx1 and idx2.
     * */
    public void swap(int idx1, int idx2) {
        boolean temp = this.array[idx1];
        this.array[idx1] = this.array[idx2];
        this.array[idx2] = temp;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BooleanArray that = (BooleanArray) o;
        return Arrays.equals(array, that.array);
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(array);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (int i = 0; i < array.length; i++) {
            sb.append(array[i]);
            if (i < array.length - 1) {
                sb.append(',');
            }
        }
        sb.append("]");
        return sb.toString();
    }
}
