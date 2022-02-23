package logic;

import java.util.ArrayList;
import java.util.HashSet;

public abstract class Encoder {

    public static ArrayList<BooleanArray> booleanPermutations(int size, int numTrue) {

        boolean[] current = new boolean[size];
        int trueUsed = 0;
        for (int i = 0; i < size; i++) {
            current[i] = trueUsed < numTrue;
            trueUsed++;
        }

        HashSet<BooleanArray> permutationSet = permutations(new BooleanArray(current), current.length, new HashSet<>());
        return new ArrayList<>(permutationSet);
    }


    /**
     * Generates all permutations of the input array using Heap's algorithm.
     * */
    protected static HashSet<BooleanArray> permutations(BooleanArray arr, int size, HashSet<BooleanArray> permutations) {
        if (size == 1) {
            permutations.add(new BooleanArray(arr));
        }
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
