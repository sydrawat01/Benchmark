package edu.neu.coe.info6205.sort.elementary;

import edu.neu.coe.info6205.util.*;

import java.util.*;


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

        final double timeRandom = new Benchmark_Timer<Integer[]>(
                description + " (Random)",
                null,
                (x)->insertionSort.sort(array.clone(),0, array.length),
                null
        ).run(array, runs);
        for (TimeLogger timeLogger : timeLoggers) timeLogger.log(timeRandom, n);

        Arrays.sort(array,0 , n/2);
        final double timePartially = new Benchmark_Timer<Integer[]>(
                description + " (Partially)",
                null,
                (x)->insertionSort.sort(array.clone(),0, array.length),
                null
        ).run(array, runs);
        for (TimeLogger timeLogger : timeLoggers) timeLogger.log(timePartially, n);

        Arrays.sort(array);
        final double timeSorted = new Benchmark_Timer<Integer[]>(
                description + " (Sorted)",
                null,
                (x)->insertionSort.sort(array.clone(),0, array.length),
                null
        ).run(array, runs);
        for (TimeLogger timeLogger : timeLoggers) timeLogger.log(timeSorted, n);

        Arrays.sort(array, Collections.reverseOrder());
        final double timeReverse = new Benchmark_Timer<Integer[]>(
                description + " (Reverse)",
                null,
                (x)->insertionSort.sort(array.clone(),0, array.length),
                null
        ).run(array, runs);
        for (TimeLogger timeLogger : timeLoggers) timeLogger.log(timeReverse, n);
    }

    public static void main(String[] args) {
        int runs = 100;
        for(int n=250; n<=16000; n*=2) {
            new InsertionSortBenchmark(n, runs).runBenchmarks(n);
        }
    }

    private final static TimeLogger[] timeLoggers = {
            new TimeLogger("Raw time per run (mSec): ", (time, n) -> time)
    };

    private final int runs;
    private final int n;
    final static LazyLogger logger = new LazyLogger(InsertionSortBenchmark.class);
}
