package logic;

import java.util.ArrayList;
import java.util.HashSet;

/**
 * Abstract class defining behaviour for variable encoders.
 */
public abstract class Encoder {

    /**
     * Create an arraylist of boolean arrays where a given number of elements is true.
     * @param size The size of the array
     * @param numTrue Number of true elements in the array
     * */
    public static ArrayList<BooleanArray> booleanPermutations(int size, int numTrue) {

        // create the initial boolean array from which to derive the permuatations from
        boolean[] current = new boolean[size];
        int trueUsed = 0;
        for (int i = 0; i < size; i++) {
            current[i] = trueUsed < numTrue;
            trueUsed++;
        }

        // create the set of all permutations conforming with the specification
        HashSet<BooleanArray> permutationSet = permutations(new BooleanArray(current), current.length, new HashSet<>());
        return new ArrayList<>(permutationSet);
    }


    /**
     * Generates all permutations of the input array using Heap's algorithm in a recursive manner.
     * */
    protected static HashSet<BooleanArray> permutations(BooleanArray arr, int size, HashSet<BooleanArray> permutations) {

        // base case
        if (size == 1) {
            permutations.add(new BooleanArray(arr));
        }

        // recursive steps
        for (int i = 0; i < size; i++) {
            permutations(arr, size - 1, permutations);

            if (size % 2 == 0) {
                arr.swap(i, size - 1);
            } else {
                arr.swap(0, size - 1);
            }
        }
        return permutations;
    }
}
