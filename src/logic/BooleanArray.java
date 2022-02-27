package logic;

import java.util.Arrays;

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

    public boolean[] toArray() {
        boolean[] copy = new boolean[this.array.length];
        System.arraycopy(this.array, 0, copy, 0, array.length);
        return copy;
    }

    public boolean get(int index) {
        return array[index];
    }

    public void set(int index, boolean value) {
        array[index] = value;
    }

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
