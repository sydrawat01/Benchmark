package edu.neu.coe.info6205.sort.elementary;

import edu.neu.coe.info6205.sort.BaseHelper;
import edu.neu.coe.info6205.sort.GenericSort;
import edu.neu.coe.info6205.sort.Helper;
import edu.neu.coe.info6205.util.*;
import org.junit.BeforeClass;

import java.io.IOException;
import java.util.*;
//import java.util.Arrays;
//import java.util.Random;
import java.util.function.Supplier;



public class InsertionSortBenchmark {

    public InsertionSortBenchmark(int n, int runs) {
        this.n = n;
        this.runs = runs;
    }

    public void runBenchmarks(int n) {
        Random r = new Random();
        ArrayList<Integer> list = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            list.add(r.nextInt(n));
        }
        Integer[] array = list.toArray(new Integer[0]);

        System.out.println("InsertionSort Benchmark: N=" + n);
        String description = "Insertion Sort";

        InsertionSort<Integer> insertionSort = new InsertionSort<>();

//        Supplier<Integer[]> supplier = null;
//        supplier = () -> array;

        final double timeRandom = new Benchmark_Timer<Integer[]>(
                description + " (Random)",
                null,
                (x)->insertionSort.sort(array.clone(),0, array.length),
                null
        ).run(array, runs);
        logger.info(Utilities.formatDecimal3Places(timeRandom) + " ms");

//        supplier = () -> {
//                Arrays.sort(array,0 , n/2);
//                return array;
//        };
        Arrays.sort(array,0 , n/2);
        final double timePartially = new Benchmark_Timer<Integer[]>(
                description + " (Partially)",
                null,
                (x)->insertionSort.sort(array.clone(),0, array.length),
                null
        ).run(array, runs);
        logger.info(Utilities.formatDecimal3Places(timePartially) + " ms");
//        for (TimeLogger timeLogger : timeLoggers) timeLogger.log(timePartially, n);


//        supplier = () -> {
//            Arrays.sort(array);
//            return array;
//        };
        Arrays.sort(array);
        final double timeSorted = new Benchmark_Timer<Integer[]>(
                description + " (Sorted)",
                null,
                (x)->insertionSort.sort(array.clone(),0, array.length),
                null
        ).run(array, runs);
        logger.info(Utilities.formatDecimal3Places(timeSorted) + " ms");

//        supplier = () -> {
//                Arrays.sort(array, Collections.reverseOrder());
//                return array;
//        };
        Arrays.sort(array, Collections.reverseOrder());
        final double timeReverse = new Benchmark_Timer<Integer[]>(
                description + " (Reverse)",
                null,
                (x)->insertionSort.sort(array.clone(),0, array.length),
                null
        ).run(array, runs);
        logger.info(Utilities.formatDecimal3Places(timeReverse) + " ms");

    }

    public static void main(String[] args) {
        int runs = 10;
        for(int n=250; n<=16000; n*=2) {
            new InsertionSortBenchmark(n, runs).runBenchmarks(n);
        }
    }

    private final static TimeLogger[] timeLoggers = {
            new TimeLogger("Random Raw time per run (mSec): ", (time, n) -> time)
    };

    private final int runs;
    private final int n;
    final static LazyLogger logger = new LazyLogger(InsertionSortBenchmark.class);
}
