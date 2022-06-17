package edu.neu.coe.info6205.sort.elementary;

import java.util.Arrays;
import java.util.Collections;
import java.util.Random;
import java.util.function.Supplier;

/**
 * A source of random information.
 */
class Source {

    public Source(int N, Random random) {
        n = N;
        this.random = random;
    }

    public Source(int N) {
        this(N, new Random());
    }

    /**
     * Method to return a Supplier of an int array where there are n ints and each int is in range -m thru m-1.
     *
     * @param type         a String type that tells whether a sorted, partially sorted, reverse sorted or a random
     *                     array is needed.
     * @return a Supplier which provides a sorted list of distinct ints.
     */

    public Supplier<Integer[]> intsArr(String type) {
        Supplier<Integer[]> supplier = null;
        switch (type) {
            case "random" -> {
                    //Provide the array of size j to supplier
                    supplier = () -> {
                        random = new Random();
                        Integer[] arr = new Integer[n];

                        //Generate the array
                        for (int k = 0; k < n; k++) {
                            arr[k] = random.nextInt(n * 100);
                        }
                        return arr;
                    };
            }
            case "reverse" -> {

                    //Provide the array of size j to supplier
                    supplier = () -> {
                        random = new Random();
                        Integer[] arr = new Integer[n];

                        //Generate the array
                        for (int k = 0; k < n; k++) {
                            arr[k] = random.nextInt(n * 100);
                        }
                        //Sort the array in reverse
                        Arrays.sort(arr, Collections.reverseOrder());
                        return arr;
                    };
            }
            case "partially" -> {
                //Create a partially ordered array and run benchmark test

                    //Provide the array of size j to supplier
                    supplier = () -> {
                        random = new Random();
                        Integer[] arr = new Integer[n];

                        //Generate the array
                        for (int k = 0; k < n; k++) {
                            arr[k] = random.nextInt(n * 100);
                        }

                        //Sort the array
                        Arrays.sort(arr);

                        //Rearrange half the array elements
                        int rearrange = (int) (0.5 * n);

                        //Generate index to rearrange the array
                        for (int i1 = 0; i1 < rearrange; i1++) {
                            int index = random.nextInt(n);
                            arr[index] = random.nextInt(n * 100);
                        }

                        return arr;
                    };
            }
            case "sorted" -> {

                    //Provide the array of size j to supplier
                    supplier = () -> {
                        random = new Random();
                        Integer[] arr = new Integer[n];

                        //Generate the array
                        for (int k = 0; k < n; k++) {
                            arr[k] = random.nextInt(n * 100);
                        }
                        //Sort the array
                        Arrays.sort(arr);
                        return arr;
                    };
            }
        }
        return supplier;
    }

    private final int n;
    private Random random;
}